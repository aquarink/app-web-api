package kirimdoku.interfaces;

import static play.data.Form.form;
import kirimdoku.helpers.CustomerHelper;
import kirimdoku.helpers.CustomerLookUpHelper;
import kirimdoku.helpers.ReportCustomerHelper;
import kirimdoku.helpers.SecurityAgentMobileAuthenticator;
import kirimdoku.util.Utility;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import com.avaje.ebean.Page;
import controllers.LoggerAction;
import controllers.helpers.Definition;
import controllers.helpers.ResponseHelper;
import controllers.tokens.CustomerToken;
import play.Logger;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;

@Security.Authenticated(SecurityAgentMobileAuthenticator.class)
@With(LoggerAction.class)
public class Customer extends Controller {

	public static Result getCustomer() {
		Logger.info("---------------------");
		Logger.info(":: CUSTOMER LOOKUP :: ");
		Logger.info("=====================");
		Result result = null;
		try {
			Form<CustomerLookUpHelper> helper = form(CustomerLookUpHelper.class).bindFromRequest();
			Logger.info("Customer LookUp Request " + helper.data());
			if (helper.hasErrors()) {
				result = unauthorized(ResponseHelper.createResponse(
						Definition.STATUS_INVALIDPARAMS, "Invalid parameters",
						helper.errorsAsJson()));
				Logger.info("Invalid parameters : " + helper.errorsAsJson());
			} else {
				CustomerToken token = CustomerToken.fromString(helper.get().idToken);
				ObjectNode response = Json.newObject();
		    	if(token != null) {
		            models.Customer customer = models.Customer.find.byId(token.id);
					if (customer != null) {
						JsonNode customerNode = Json.toJson(customer);
						response.put("status", Definition.STATUS_OK);
						response.put("message", "Customer info success");
						response.put("customer", customerNode);
					} else {
						response.put("status", Definition.STATUS_NOTFOUND);
						response.put("message", "Customer not found");
					}
		    	} else {
		    		response.put("status", Definition.STATUS_NOTFOUND);
		    		response.put("message", "Invalid Customer ID");
		    	}
		    	Logger.info("Get Customer LookUp Result " + response.toString());
		    	result = ok(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = ok(ResponseHelper.createResponse(Definition.STATUS_UNKNOWN, e.getMessage()));
			Logger.info("Exception : " + e.getMessage());
		}
		Logger.info("--------------------");
		return result;
    }
	
	public static Result getCustomerList() {
		Logger.info("-----------------------");
		Logger.info(":: GET CUSTOMER LIST :: ");
		Logger.info("=======================");
		response().setContentType("application/json");
		Result result = null;
		try {
			Form<ReportCustomerHelper> helper = form(
					ReportCustomerHelper.class).bindFromRequest();
			Logger.info("Get Customer List Request " + helper.data());
			if (helper.hasErrors()) {
				result = unauthorized(ResponseHelper.createResponse(
						Definition.STATUS_INVALIDPARAMS, "Invalid parameters",
						helper.errorsAsJson()));
				Logger.info("Invalid parameters : " + helper.errorsAsJson());
			} else {
				Page<models.Customer> pageCustomer = models.Customer.reportPage(helper.get());
				String json = Json.toJson(pageCustomer.getList()).toString();
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.setDateFormat(Utility.datetimeFormatShow);
				ObjectNode response = Json.newObject();
				response.put("status", Definition.STATUS_OK);
				response.put("message","Get customer list success");
				response.put("customers", objectMapper.readTree(json));
				response.put("totalRowCount", helper.get().totalRowCount);
				response.put("totalPageCount", helper.get().totalPageCount);
				Logger.info("Get Customer List Result " + response.toString());
				result = ok(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = ok(ResponseHelper.createResponse(Definition.STATUS_UNKNOWN, e.getMessage()));
			Logger.info("Exception : " + e.getMessage());
		}
		Logger.info("--------------------");
		return result;
	}
	
	public static Result addCustomer() {
		Logger.info("------------------");
		Logger.info(":: ADD CUSTOMER :: ");
		Logger.info("==================");
		Result result = null;
		try {
	        Form<CustomerHelper> helper = form(CustomerHelper.class).bindFromRequest();
			Logger.info("Add Customer Request " + helper.data());
	        if(helper.hasErrors()) {
	        	result = unauthorized(ResponseHelper.createResponse(
						Definition.STATUS_INVALIDPARAMS, "Invalid parameters",
						helper.errorsAsJson()));
	        	Logger.info("Invalid parameters : " + helper.errorsAsJson());
	        } else {
		        models.Customer customer = helper.get().customer;
		        customer.save();
		        ObjectNode response = ResponseHelper.createResponse(Definition.STATUS_OK, "Customer has been created");
		        Logger.info("Add Customer Result " + response.toString());
		        result = ok(response);
	        }
		} catch (Exception e) {
			e.printStackTrace();
			result = ok(ResponseHelper.createResponse(Definition.STATUS_UNKNOWN, e.getMessage()));
			Logger.info("Exception : " + e.getMessage());
		}
        return result;
	}
	
	public static Result getCustomerSenderList() {
		Logger.info("------------------------------");
		Logger.info(":: GET CUSTOMER SENDER LIST :: ");
		Logger.info("==============================");
		response().setContentType("application/json");
		Result result = null;
		try {
			Form<ReportCustomerHelper> helper = form(
					ReportCustomerHelper.class).bindFromRequest();
			Logger.info("Get Customer Sender List Request " + helper.data());
			if (helper.hasErrors()) {
				result = unauthorized(ResponseHelper.createResponse(
						Definition.STATUS_INVALIDPARAMS, "Invalid parameters",
						helper.errorsAsJson()));
				Logger.info("Invalid parameters : " + helper.errorsAsJson());
			} else {
				Page<models.Customer> pageCustomer = models.Customer.reportPage(helper.get());
				String json = Json.toJson(pageCustomer.getList()).toString();
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.setDateFormat(Utility.datetimeFormatShow);
				ObjectNode response = Json.newObject();
				response.put("customers", objectMapper.readTree(json));
				response.put("totalRowCount", helper.get().totalRowCount);
				response.put("totalPageCount", helper.get().totalPageCount);
				Logger.info("Get Customer Sender List Result " + response.toString());
				result = ok(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = ok(ResponseHelper.createResponse(Definition.STATUS_UNKNOWN, e.getMessage()));
			Logger.info("Exception : " + e.getMessage());
		}
		Logger.info("--------------------");
		return result;
	}
	
}
