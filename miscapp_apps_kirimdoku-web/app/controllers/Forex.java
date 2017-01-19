package controllers;

import be.objectify.deadbolt.java.actions.Restrictions;
import be.objectify.deadbolt.java.actions.And;
import models.ForexReference;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import static play.data.Form.*;
import views.html.forex.admin_index;
import views.html.forex.table;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Security.Authenticated(SecuredAdmin.class)
public class Forex extends Controller {
	
	@Restrictions({@And("admin"), @And("finance")})
    public static Result admin_index_main() {
			return admin_index("DOK", "", "");
		}
	@Restrictions({@And("admin"), @And("finance"), @And("admin_operasional")})
    public static Result admin_index(String corporateCode, String origin, String destination) {
    	models.Corporate corporate = models.Corporate.find.byId(corporateCode);
    	models.Forex dForex = new models.Forex();
    	dForex.corporate = corporate;
    	dForex.origin = corporate.country.currency;
    	Form<models.Forex> filterForm = form(models.Forex.class).fill(dForex);
    	
		List<models.Forex> forexs = models.Forex.find.where().eq("corporate", corporate).findList();
		List<Form<models.Forex>> forexForms = new ArrayList<Form<models.Forex>>();
		for(models.Forex forex : forexs) {
			forexForms.add(form(models.Forex.class).fill(forex));
		}
		
        return ok(admin_index.render(corporate, filterForm, forexForms));
    }

	@Restrictions({@And("admin"), @And("finance"), @And("admin_operasional")})
    public static Result admin_create(String corporateCode) {
    	models.Corporate corporate = models.Corporate.find.byId(corporateCode);
    	Form<models.Forex> form = form(models.Forex.class).bindFromRequest();
    	
    	if (form.hasErrors()) {
    		return internalServerError(form.errorsAsJson());
    	}
    	models.Forex existingForex = models.Forex.findByCorporate(corporate, models.Currency.findByCode(form.get().origin.code), models.Currency.findByCode(form.get().destination.code));
    	if (existingForex == null) {
	    	form.get().rate = 0;
	    	form.get().spread = 0;
	    	form.get().createdTime = new Date();
	    	form.get().save();
    	}	
    	return redirect(routes.Forex.admin_index(corporateCode, form.get().origin.code, form.get().destination.code));
    }
    
	@Restrictions({@And("admin"), @And("finance"), @And("admin_operasional")})
    public static Result admin_update(String corporateCode, Long id) {
//    	models.Corporate corporate = models.Corporate.find.byId(corporateCode);
    	models.Forex forex = models.Forex.find.byId(id);
    	Form<models.Forex> form = form(models.Forex.class).fill(forex).bindFromRequest();
    	
    	if (form.hasErrors()) {
    		return internalServerError(form.errorsAsJson());
    	}
    	
    	form.get().createdTime = new Date();
    	form.get().update();
    	
    	ForexReference forexRef = new ForexReference();
    	forexRef.createdTime = form.get().createdTime;
    	forexRef.forex = form.get();
    	forexRef.initialRate = form.get().rate;
    	forexRef.rate = form.get().getSpreadRate();
    	forexRef.save();
    	
    	return ok("Saved");
    }
    
	@Restrictions({@And("admin"), @And("finance"), @And("admin_operasional")})
    public static Result admin_destroy(String corporateCode, Long id) {
    	models.Forex forex = models.Forex.find.where().eq("corporate.code", corporateCode).eq("id", id).findUnique();
    	if (forex != null) {
    		try {
    			List<ForexReference> refs = models.ForexReference.find.where().eq("forex", forex).findList();
    			for(ForexReference ref : refs) {
    				ref.delete();
    			}
    			forex.delete();
	    		return ok("deleted");
    		} catch(Exception e) {
    			e.printStackTrace();
    			System.out.println("UE : Unable to delete forex");
    			return badRequest("Unable to delete forex");
    		}
    	}
    	return internalServerError();
    }
	
	@Restrictions({@And("admin"), @And("finance"), @And("mainagent"), @And("operator"), @And("supervisor"), @And("admin_operasional")})
	public static Result table(String corporateCode) {
    	models.Corporate corporate = models.Corporate.find.byId(corporateCode);
    	List<models.Forex> forexs = models.Forex.find.fetch("origin").fetch("destination").where().eq("corporate", corporate).findList();
    	List<models.Currency> currencies = new ArrayList<models.Currency>();
    	for(Iterator<models.Forex> it = forexs.iterator(); it.hasNext(); ) {
    		models.Forex forex = it.next();
    		if(!currencies.contains(forex.origin)) {
    			currencies.add(forex.origin);
    		}
    		if(!currencies.contains(forex.destination)) {
    			currencies.add(forex.destination);
    		}
    	}
		return ok(table.render(corporate, currencies));
	}
}
