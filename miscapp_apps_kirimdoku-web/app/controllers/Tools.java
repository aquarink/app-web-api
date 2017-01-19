package controllers;

import static play.data.Form.form;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import models.forms.CashInInquiryForm;
import org.apache.commons.mail.EmailException;
import org.codehaus.jackson.JsonNode;
import play.data.*;
import play.data.validation.Constraints;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import be.objectify.deadbolt.java.actions.And;
import be.objectify.deadbolt.java.actions.Restrictions;
import controllers.helpers.ApiHelper;
import controllers.helpers.SessionHelper;
import views.html.tools.documentation;
import views.html.tools.feedback;
import views.html.tools.check_rate;
import views.html.tools.check_rate_result;

@Security.Authenticated(SecuredMain.class)
@Restrictions({@And("admin"), @And("mainagent"), @And("supervisor"), @And("operator")})
public class Tools extends Controller {

    public static Result documentation() {
        return ok(documentation.render());
    }

    public static Result feedback() {
		Form<FeedbackForm> feedbackForm = form(FeedbackForm.class);
		
		return ok(feedback.render(feedbackForm));
	}
    
    
    public static Result feedbackSubmit() {
		Form<FeedbackForm> feedbackForm = form(FeedbackForm.class).bindFromRequest();
    	
    	if (feedbackForm.hasErrors()) {
    		flash(feedbackForm.errors().toString());
    		return badRequest(feedback.render(feedbackForm));
    	}
    	
    	try {
			models.User user = SessionHelper.getUser();
    		String recipient = play.Play.application().configuration().getConfig("email").getString("feedback_recipient");
    		String subject = "FEEDBACK: "+feedbackForm.get().subject;
    		String body = "Dear MTS Customer Service, \n\nYou have new feedback. Here are the details:\n\nUser ID: "+user.getIdToken()+"\nName: "+user.fullName()+"\nPrivilege: "+user.getRoles().get(0).getName()+"\nCorporate: "+user.corporate.name+"\nCountry: "+user.country.name+"\n\nMessage\n"+feedbackForm.get().body;
    		ApiHelper.sendPlainEmail(null, InternetAddress.parse(recipient)[0], subject, body);
    		flash("success", "Feedback email has been sent, our customer service will respond to you quickly regarding your feedback");
    	} catch (EmailException e) {
    		e.printStackTrace();
    		flash("error", e.getMessage());
    		return internalServerError(feedback.render(feedbackForm));
    	} catch (AddressException e) {
    		e.printStackTrace();
    		flash("error", e.getMessage());
    		return internalServerError(feedback.render(feedbackForm));
		} catch (UnsupportedEncodingException e) {
    		e.printStackTrace();
    		flash("error", e.getMessage());
    		return internalServerError(feedback.render(feedbackForm));
		}
    	
		return ok(feedback.render(feedbackForm));
    }

	public static Result checkRate() {
		Map<String, String> defaultMap = new HashMap<String, String>();
		defaultMap.put("senderCountry.code", SessionHelper.getUser().corporate.country.code);
		defaultMap.put("senderCurrency.code", SessionHelper.getUser().corporate.country.currency.code);
        Form<CashInInquiryForm> fxForm = form(CashInInquiryForm.class).bind(defaultMap);

        return ok(check_rate.render(fxForm));
    }
	
	public static Result checkRateSubmit() {
        Form<CashInInquiryForm> fxForm = form(CashInInquiryForm.class).bindFromRequest();
        if (fxForm.hasErrors()) {
            return badRequest("Errors "+fxForm.errors());
		}
        
        JsonNode result = ApiHelper.CashIn.inquiry(fxForm.get());
        return ok(check_rate_result.render(fxForm, result));
	}
    
	
	
    public static class FeedbackForm {
    	 @Constraints.Required
         public String subject;
    	 
    	 @Constraints.Required
         public String body;
	}

}
