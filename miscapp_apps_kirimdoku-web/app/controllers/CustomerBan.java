package controllers;

import static play.data.Form.form;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import views.html.customerban.admin_interdictions;
import views.html.customerban.admin_list;
import au.com.bytecode.opencsv.CSVReader;
import be.objectify.deadbolt.java.actions.And;
import be.objectify.deadbolt.java.actions.Restrictions;
import com.avaje.ebean.Page;
import com.avaje.ebean.annotation.Transactional;

@Security.Authenticated(SecuredMain.class)
@Restrictions({@And("admin"), @And("mainagent"), @And("supervisor"), @And("operator") })
public class CustomerBan extends Controller {

	public static Result GO_HOME = redirect(routes.CustomerBan.admin_list(0, "id", "desc", ""));
	
	@Transactional(readOnly=true)
	public static Result admin_list(int page, String sortBy, String order, String filter) {
		Form<models.Customer> filterForm = form(models.Customer.class).bindFromRequest();
		
		Page<models.CustomerBan> bans = models.CustomerBan.pageCustomerBans(page, 10, sortBy, order, filter);
		return ok(admin_list.render(filterForm, bans, sortBy, order, filter));
	}
	
	public static Result admin_add() {
		Form<models.CustomerBan> filterForm = form(models.CustomerBan.class).bindFromRequest();
		models.CustomerBan customerBan = null;
		
		models.Customer findCustomer = models.Customer.findByToken(filterForm.get().customer.idToken);
		if(findCustomer != null) {
			customerBan = new models.CustomerBan();
			customerBan.customer = findCustomer;
			customerBan.country = findCustomer.country;
			customerBan.firstName = findCustomer.firstName;
			customerBan.lastName = findCustomer.lastName;
			customerBan.birthDate = findCustomer.birthDate;
		}
		
		if(customerBan != null) {
			customerBan.reason = filterForm.apply("reason").value();
			customerBan.created = new Date();
			customerBan.save();
		}
		return GO_HOME;
	}
	
	public static Result admin_unban(Long id) {
		models.CustomerBan customerBan = models.CustomerBan.find.byId(id);
		if(customerBan != null) {
			customerBan.delete();
		}
		return GO_HOME;
	}
	
	

	@Transactional(readOnly=true)
	public static Result admin_interdictions(int page, String sortBy, String order, String filter) {
		Form<models.Customer> filterForm = form(models.Customer.class).bindFromRequest();
		
		Page<models.CustomerBan> bans = models.CustomerBan.pageInterdictions(page, 10, sortBy, order, filter);
		return ok(admin_interdictions.render(filterForm, bans, sortBy, order, filter));
	}
	
	public static Result admin_interdictionsUpload() {
		// Buffer all the registered country?
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Http.MultipartFormData body = request().body().asMultipartFormData();
		Http.MultipartFormData.FilePart uploadFile = body.getFile("uploadFile");
		if((uploadFile != null) && (uploadFile.getContentType().equalsIgnoreCase("text/csv"))) {
			try {
				CSVReader reader = new CSVReader(new FileReader(uploadFile.getFile()));
				String[] header;
			    header = reader.readNext();
			    if((header == null) || (header.length != 5)) throw new Exception("CSV Template header doesn't match, please recheck your csv template file");
			    
				String [] line;
			    while ((line = reader.readNext()) != null) {
			    	try {
				    	String id = line[0];
				    	String countryCode = line[1];
				    	String firstName = line[2];
				    	String lastName = line[3];
				    	String birthDate = line[4];
				    	// Normalize values
				    	firstName = firstName.trim();
				    	lastName = lastName.trim();
				    	
				    	models.CustomerBan cb = null;
				    	if ((id != null) && (!id.isEmpty())) {
				    		cb = models.CustomerBan.find.byId(Long.valueOf(id));
				    	}
				    	if (cb == null) {
				    		cb = models.CustomerBan.find.where().eq("country.code", countryCode).eq("firstName", firstName).eq("lastName", lastName).eq("birthDate", df.parse(birthDate)).findUnique();
				    		if(cb == null) {
					    		cb = new models.CustomerBan();
						    	cb.created = new Date();
				    		}
				    	}
				    	
				    	// Prefetch values
				    	cb.firstName = firstName;
				    	cb.lastName = lastName;
				    	cb.country = models.Country.find.byId(countryCode);
				    	cb.birthDate = df.parse(birthDate);
				    	cb.modified = new Date();
				        System.out.println("CB = "+cb+" - "+cb.id);
				    	
				        cb.save();
				    	
				    	// Process values
				        System.out.println("Processing... "+id+" - "+countryCode+" - "+firstName+" "+lastName+" - "+birthDate);
			    	} catch(Exception e) {
						e.printStackTrace();
			    	}
			    }
				flash("success", "Data interdiction has been processed");
				return redirect(routes.CustomerBan.admin_interdictions(0, "country.code", "asc", ""));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				flash("error", "Unable to read file");
			} catch (IOException e) {
				e.printStackTrace();
				flash("error", "Internal error on reading file, Please re-check your file");
			} catch (Exception e) {
				e.printStackTrace();
				flash("error", e.getMessage());
			}
		} else {
			flash("error", "Unable to recognize file format type");
		}
		return redirect(routes.CustomerBan.admin_interdictions(0, "country.code", "asc", ""));
	}
	
	@Restrictions({@And("admin")})
	public static Result admin_interdictionsDelete(Long id) {
		models.CustomerBan cb = models.CustomerBan.find.byId(id);
		cb.delete();
		return ok();
	}
}
