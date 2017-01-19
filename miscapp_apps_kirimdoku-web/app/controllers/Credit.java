package controllers;

import be.objectify.deadbolt.java.actions.Restrictions;
import be.objectify.deadbolt.java.actions.And;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import static play.data.Form.*;
import views.html.credit.admin_index;

@Security.Authenticated(SecuredMain.class)
@Restrictions({@And("admin"), @And("finance")})
public class Credit extends Controller {
	
    public static Result admin_index(String corporateCode) {
    	models.Corporate corporate = models.Corporate.find.byId(corporateCode);
    	if(corporate != null) {
	    	Form<models.Corporate> corporateForm = form(models.Corporate.class).fill(corporate);
	    	
	        return ok(admin_index.render(corporateForm));
    	} else {
    		return notFound();
    	}
    }
    
    public static Result admin_update(String corporateCode) {
    	models.Corporate corporate = models.Corporate.find.byId(corporateCode);
    	Form<models.Corporate> corporateForm = form(models.Corporate.class).fill(corporate).bindFromRequest();
    	if(corporateForm.hasErrors()) {
    		return badRequest(corporateForm.errors().toString());
    	}
    	corporate.creditLimit = corporateForm.get().creditLimit;
    	if(corporateForm.get().creditAlertLimit.intValue() > corporateForm.get().creditLimit.intValue()) return badRequest("Accumulation alert shall not be higher than accumulation limit");
    	corporate.creditAlertLimit = corporateForm.get().creditAlertLimit;
    	if(corporateForm.get().customerSendLimit.intValue() > corporateForm.get().creditLimit.intValue()) return badRequest("Customer accumulation alert shall not be higher than accumulation alert limit");
    	corporate.customerSendLimit = corporateForm.get().customerSendLimit;
    	corporate.update();
    	return ok("Data saved");
    }
}
