package controllers;


import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

import play.libs.Json;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.*;

import controllers.helpers.Definition;
import controllers.helpers.ResponseHelper;
import controllers.helpers.SecurityAgentAuthenticator;
import controllers.tokens.CustomerToken;
import controllers.tokens.TransactionToken;

@Security.Authenticated(SecurityAgentAuthenticator.class)
@With(LoggerAction.class)
public class Customer extends Controller {
	 
    public static Result GO_HOME = ok();

    public static Result index() {
        return GO_HOME;
    }

    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
//            list.render(
//                Customer.page(page, 10, sortBy, order, filter),
//                sortBy, order, filter
//            )
        );
    }
    
    public static Result show(String idToken) {
		ObjectNode result = ResponseHelper.createResponse();
		try {
	    	CustomerToken token = CustomerToken.fromString(idToken);
	    	if(token != null) {
	            models.Customer customer = models.Customer.find.byId(token.id);
				if (customer != null) {
					JsonNode customerNode = Json.toJson(customer);
		
					result.put("status", Definition.STATUS_OK);
					result.put("message", "Customer info success");
					result.put("customer", customerNode);
				} else {
					result.put("status", Definition.STATUS_NOTFOUND);
					result.put("message", "Customer not found");
				}
	    	} else {
				result.put("status", Definition.STATUS_NOTFOUND);
				result.put("message", "Invalid Customer ID");
	    	}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", Definition.STATUS_UNKNOWN);
			result.put("message", e.getMessage());
		}
		return ok(result);
    }
    

    
    public static Result update(Long id) {
        Form<models.Customer> CustomerForm = form(models.Customer.class).bindFromRequest();
        if(CustomerForm.hasErrors()) {
            return badRequest();
        }
        CustomerForm.get().update(id);
        flash("success", "Customer " + CustomerForm.get().fullName() + " has been updated");
        return GO_HOME;
    }
    
    
    public static Result save() {
        Form<Customer> CustomerForm = form(Customer.class).bindFromRequest();
        if(CustomerForm.hasErrors()) {
            return badRequest();
        }
        CustomerForm.get().save();
        flash("success", "Customer has been created");
        return GO_HOME;
    }
    
    public static Result delete(Long id) {
        models.Customer.find.ref(id).delete();
        flash("success", "Customer has been deleted");
        return GO_HOME;
    }

}
