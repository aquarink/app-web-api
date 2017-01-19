package controllers;

import be.objectify.deadbolt.java.actions.Restrictions;
import be.objectify.deadbolt.java.actions.And;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import static play.data.Form.*;
import views.html.fee.admin_index;
import java.util.List;

@Security.Authenticated(SecuredMain.class)
@Restrictions({@And("admin"), @And("finance")})
public class Fee extends Controller {
	
	@Restrictions({@And("admin"), @And("finance"), @And("admin_operasional")})
    public static Result admin_index(String corporateCode) {
    	models.Corporate corporate = models.Corporate.find.byId(corporateCode);
    	Form<models.Fee> filterForm = form(models.Fee.class).bindFromRequest();
    	
    	if(filterForm.data().isEmpty()) {
	    	models.Fee dFee = new models.Fee();
	    	dFee.senderCountry = corporate.country;
			dFee.currency = corporate.currency;
    		filterForm = filterForm.fill(dFee);
    	}
    	
    	List<models.Fee> fees = models.Fee.find.where()
    			.eq("corporate", corporate)
    			.eq("senderCountry.code", filterForm.apply("senderCountry.code").value())
    			.eq("beneficiaryCountry.code", filterForm.apply("beneficiaryCountry.code").value())
    			.eq("channel.code", filterForm.apply("channel.code").value()) // TODO
    			.order("senderCountry,beneficiaryCountry ASC").findList(); // TODO
    	
    	models.Fee newFee = null;
    	if(!filterForm.data().isEmpty()) {
	    	newFee = new models.Fee();
	    	newFee.senderCountry = models.Country.find.byId(filterForm.field("senderCountry.code").value());
	    	newFee.beneficiaryCountry = models.Country.find.byId(filterForm.field("beneficiaryCountry.code").value());
	    	newFee.currency = models.Currency.find.byId(filterForm.field("currency.code").value());
	    	newFee.channel = models.Channel.find.byId(filterForm.field("channel.code").value());
	    	newFee.beneficiaryCurrency = models.Currency.find.byId(filterForm.field("beneficiaryCurrency.code").value());
    	}
        return ok(admin_index.render(filterForm, corporate, fees, newFee));
    }
    
	@Restrictions({@And("admin"), @And("finance"), @And("admin_operasional")})
    public static Result create(String corporateCode) {
    	Form<models.Fee> form = form(models.Fee.class).bindFromRequest();
    	if (form.hasErrors()) {
    		return internalServerError(form.errorsAsJson());
    	}
    	if (form.get().id > 0) {
    		form.get().update();
    	} else {
    		form.get().save();
    	}
    	return ok(Json.toJson(form.get()));
    }
    
	@Restrictions({@And("admin"), @And("finance"), @And("admin_operasional")})
    public static Result destroy(String corporateCode, Long id) {
    	models.Fee fee = models.Fee.find.byId(id);
    	if (fee != null) {
    		fee.delete();
    		return ok();
    	}
    	return internalServerError();
    }
}
