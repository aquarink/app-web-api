package controllers;

import static play.data.Form.form;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import models.CustomerRelate;

import org.apache.commons.io.IOUtils;

import com.avaje.ebean.Page;

import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.customer.create;
import views.html.customer.edit;
import views.html.customer.list;
import views.html.customer.summary;
import views.html.customer.relate;
import views.html.customer.addrelate;
import au.com.bytecode.opencsv.CSVWriter;
import be.objectify.deadbolt.java.actions.And;
import be.objectify.deadbolt.java.actions.Restrictions;

@Security.Authenticated(SecuredMain.class)
@Restrictions({@And("admin"), @And("mainagent"), @And("supervisor"), @And("operator") })
public class Customer extends Controller {

	public static Result GO_HOME = redirect(routes.Customer.list(0, "firstName", "asc", ""));

	public static Result list(int page, String sortBy, String order, String filter) {
		return ok(list.render(models.Customer.page(page, 10, sortBy, order, filter), sortBy, order, filter));
	}
	
	public static Result csv(String sortBy, String order, String filter) {
		String csvExportPath = "/tmp/kirimdoku-export-customer.csv";
		
		List<models.Customer> customers = models.Customer.csv(sortBy, order, filter);
		if(customers.isEmpty()) return notFound();
		
		CSVWriter csvWriter;
		try {
			csvWriter = new CSVWriter(new FileWriter(csvExportPath));
			String head[] = new String[] { 
	            	"Customer ID",
	            	"First Name",
	            	"Last Name",
	            	"Nationality",
	            	"City",
	            	"Personal ID Type",
	            	"Personal ID Number",
	            	"Gender",
	            	"Date of Birth",
	            	"Address",
	            	"Postal Code",
	            	"Phone Number"
	            };
			csvWriter.writeNext(head);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			for (models.Customer c : customers) {
				String gender = "";
				if (c.gender == models.Customer.Gender.MALE) gender = "Male";
				else if (c.gender == models.Customer.Gender.FEMALE) gender = "Female";
				String dateBirth = c.birthDate != null ? sdf.format(c.birthDate) : "";
				String[] cs = {c.getIdToken(), c.firstName, c.lastName, c.country.name, c.cityName, c.personalIdType, c.personalId, gender, dateBirth, c.address, c.postalCode, c.phoneNumber};
				csvWriter.writeNext(cs);
			}
			csvWriter.close();
			return ok(IOUtils.toByteArray(new FileInputStream(new java.io.File(csvExportPath)))).as("text/csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return internalServerError();
	}

	public static Result create() {
		Form<models.Customer> form = form(models.Customer.class);
		return ok(create.render(form));
	}

	public static Result edit(Long id) {
		models.Customer customer = models.Customer.find.byId(id);
		Form<models.Customer> form = form(models.Customer.class).fill(customer);
		return ok(edit.render(id, form));
	}

	public static Result save() {
		Form<models.Customer> customerForm = form(models.Customer.class).bindFromRequest();

		if (customerForm.hasErrors()) {
			Logger.debug("CustomerForm errors " + customerForm.errorsAsJson());
			return badRequest(create.render(customerForm));
		}
		customerForm.get().save();
		flash("success", "Customer " + customerForm.get().firstName + " " + customerForm.get().lastName + " has been created");
		return GO_HOME;
	}

	// TODO: Refactor this Temporary Function, karena masih bingung cara
	// ngebedain request ajax atau bukan di play
	public static Result saveAjax() {
		Form<models.Customer> customerForm = form(models.Customer.class).bindFromRequest();

		if (customerForm.hasErrors()) {
			Logger.debug("CustomerForm errors " + customerForm.errorsAsJson());
			return badRequest();
		}
		customerForm.get().save();
		models.Customer customer = customerForm.get();
		return ok(customer.id.toString());
	}

	public static Result update(Long id) {
		models.Customer customer = models.Customer.find.byId(id);
		Form<models.Customer> customerForm = form(models.Customer.class).bindFromRequest();
		if (customerForm.hasErrors()) {
			Logger.error(customerForm.errorsAsJson().toString());
			return badRequest(edit.render(id, customerForm));
		}
		customerForm.get().update(id);
		flash("success", "Customer " + customerForm.get().firstName + " " + customerForm.get().lastName + " has been updated");
		return GO_HOME;
	}

	public static Result summary(Long id) {
		models.Customer customers = models.Customer.find.fetch("country").fetch("personalIdCountry").where().eq("id", id).findUnique();
		return ok(summary.render(customers));
	}
	
	public static Result relate(Long id, int page, String sortBy, String order, String filter) {
		try {
			models.Customer customer = models.Customer.find.byId(id);
			Page<models.Customer> customers = models.Customer.pageRelate(customer, page, 10, sortBy, order, filter);
			return ok(relate.render(customers, customer, sortBy, order, filter));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list(0, "", "", "");
	}
	
	public static Result addRelate(Long id, int page, String sortBy, String order, String filter) {
		try {
			models.Customer customer = models.Customer.find.byId(id);
			return ok(addrelate.render(customer, models.Customer.pageAddRelate(customer, page, 10, sortBy, order, filter), sortBy, order, filter));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list(0, "", "", "");
	}
	
	public static Result deleteRelate(Long id, Long idRelate) {
		try {
			models.Customer customer = models.Customer.find.byId(id);
			models.Customer customerRelate = models.Customer.find.byId(idRelate);
			if (CustomerRelate.getCustomerRelateByCustomerIdAndCustomerRelateId(customer, customerRelate)) {
				Logger.debug("Success delete relate "+id+" : "+idRelate);
			}
			
			Page<models.Customer> customers = models.Customer.pageRelate(customer, 0, 10, "", "", "");
			return ok(relate.render(customers, customer, "", "", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list(0, "", "", "");
	}
	
	public static Result addRelateAction() {
		try {
			Form<AddRelateCustomer> form = form(AddRelateCustomer.class).bindFromRequest();
			Logger.debug("request : "+form.data());
			if (form.hasErrors()) {
				Logger.error(form.errorsAsJson().toString());
			}
			models.Customer customer = models.Customer.find.byId(form.get().id);
			for (Long customerId : form.get().customerId) {
				CustomerRelate customerRelate = new CustomerRelate();
				customerRelate.id = CustomerRelate.getSeq();
				customerRelate.customer = customer;
				customerRelate.customerRelate = models.Customer.find.byId(customerId);
				customerRelate.createDate = new Date();
				customerRelate.status = CustomerRelate.ECustomerRelateStatus.ACTIVE.code();
				customerRelate.save();
			}
			return ok("SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok("FAILED");
	}
	
	public static class AddRelateCustomer {
		public long id;
		public List<Long> customerId;
	}
	
	
}
