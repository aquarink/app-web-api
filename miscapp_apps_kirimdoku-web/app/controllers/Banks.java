package controllers;

import static play.data.Form.form;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import models.Bank;

import org.apache.commons.io.IOUtils;

import au.com.bytecode.opencsv.CSVWriter;
import be.objectify.deadbolt.java.actions.And;
import be.objectify.deadbolt.java.actions.Restrictions;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.banks.list;
import views.html.banks.summary;
import views.html.banks.edit;
import views.html.banks.create;

@Security.Authenticated(SecuredMain.class)
@Restrictions({@And("admin"), @And("mainagent"), @And("supervisor"), @And("operator") })
public class Banks extends Controller {
	
	public static Result GO_HOME = redirect(routes.Banks.list(0, "name", "asc", ""));
	
	public static Result list(int page, String sortBy, String order, String filter) {
		return ok(list.render(models.Bank.page(page, 10, sortBy, order, filter), sortBy, order, filter));
	}
	
	public static Result csv(String sortBy, String order, String filter) {
		String csvExportPath = "/tmp/kirimdoku-export-bank.csv";
		
		List<models.Bank> banks = models.Bank.csv(sortBy, order, filter);
		if(banks.isEmpty()) return notFound();
		
		CSVWriter csvWriter;
		try {
			csvWriter = new CSVWriter(new FileWriter(csvExportPath));
			String head[] = new String[] { 
	            	"ID",
	            	"Swift Code",
	            	"Name",
	            	"City",
	            	"Country",
	            	"Group",
	            	"Province"
	            };
			csvWriter.writeNext(head);
			for (models.Bank c : banks) {
				String[] cs = {c.id, c.code, c.name, c.city, c.countryCode, c.groupBank, c.province};
				csvWriter.writeNext(cs);
			}
			csvWriter.close();
			return ok(IOUtils.toByteArray(new FileInputStream(new java.io.File(csvExportPath)))).as("text/csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return internalServerError();
	}
	
	public static Result summary(String id) {
		models.Bank bank = models.Bank.find.where().eq("id", id).findUnique();
		return ok(summary.render(bank));
	}
	
	public static Result edit(String id) {
		models.Bank bank = models.Bank.find.byId(id);
		Form<models.Bank> form = form(models.Bank.class).fill(bank);
		return ok(edit.render(id, form));
	}
	
	public static Result update(String id) {
		try {
			Form<models.Bank> form = form(models.Bank.class).bindFromRequest();
			if (form.hasErrors()) {
				Logger.error(form.errorsAsJson().toString());
				return badRequest(edit.render(id, form));
			}
			form.get().update();
			flash("success", "Bank " + form.get().id + " " + form.get().name + " has been updated");
		} catch (Exception e) {
			e.printStackTrace();
			flash("error", "Failed update bank");
		}

		return GO_HOME;
	}
	
	public static Result create() {
		Form<models.Bank> form = form(models.Bank.class);
		if (request().method().equals("POST")) {
			form = form(models.Bank.class).bindFromRequest();
		}
		System.out.println("FORM : "+form);
		return ok(create.render(form));
	}
	
	public static Result save() {
		try {
			Form<models.Bank> form = form(models.Bank.class).bindFromRequest();
			if (form.hasErrors()) {
				Logger.debug("Bank Form errors " + form.errorsAsJson());
				return badRequest(create.render(form));
			}
			models.Bank bank = Bank.find.byId(form.get().id);
			if (bank == null) {
				form.get().save();
				flash("success", "Bank " + form.get().id + " " + form.get().name + " has been created");
			} else {
				flash("error", "Bank Id allready exist");
				return create();
			}
		} catch (Exception e) {
			e.printStackTrace();
			flash("error", "Failed save bank");
		}
		return GO_HOME;
	}
	
	public static Result delete(String id) {
		try {
//			models.Bank bank = models.Bank.find.byId(id);
//
//			if (bank == null) {
//				Logger.debug("Invalid id bank");
//				return badRequest("Invalid id bank");
//			}
//			bank.delete();
//			flash("success", "Bank " + id + " has been deleted");
			
			flash("error", "Failed delete bank");
		} catch (Exception e) {
			e.printStackTrace();
			flash("error", "Failed delete bank");
		}

		return GO_HOME;
	}
	
	
}
