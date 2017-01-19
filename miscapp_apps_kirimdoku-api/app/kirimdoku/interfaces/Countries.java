package kirimdoku.interfaces;

import java.util.List;

import models.Country;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

import static play.data.Form.form;
import kirimdoku.helpers.CountriesHelper;
import kirimdoku.helpers.CountriesRequestHelper;
import kirimdoku.helpers.SecurityAgentMobileAuthenticator;
import controllers.LoggerAction;
import controllers.helpers.Definition;
import controllers.helpers.ResponseHelper;
import play.Logger;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;

@Security.Authenticated(SecurityAgentMobileAuthenticator.class)
@With(LoggerAction.class)
public class Countries extends Controller {

	public static Result getList() {
		Logger.info("----------------------");
		Logger.info(":: GET COUNTRY LIST :: ");
		Logger.info("======================");
		response().setContentType("application/json");
		Result result = null;
		try {
			List<Country> countries = models.Country.find.fetch("currency").orderBy("name").findList();
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(countries);
			ObjectNode response = Json.newObject();
			response.put("status", Definition.STATUS_OK);
			response.put("message","Get countries success");
			response.put("countries", objectMapper.readTree(json));
			Logger.info("Country Receive List Result " + response.toString());
			result = ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			result = ok(ResponseHelper.createResponse(Definition.STATUS_UNKNOWN, e.getMessage()));
			Logger.info("Exception : " + e.getMessage());
		}
		Logger.info("--------------------");
		return result;
	}
	
	public static Result getReceiveList() {
		Logger.info("------------------------------");
		Logger.info(":: GET COUNTRY RECEIVE LIST :: ");
		Logger.info("==============================");
		response().setContentType("application/json");
		Result result = null;
		try {
			Form<CountriesRequestHelper> helper = form(CountriesRequestHelper.class).bindFromRequest();
			Logger.info("Country Receive List Request " + helper.data());
			if (helper.hasErrors()) {
				result = unauthorized(ResponseHelper.createResponse(
						Definition.STATUS_INVALIDPARAMS, "Invalid parameters",
						helper.errorsAsJson()));
				Logger.info("Invalid parameters : " + helper.errorsAsJson());
			} else {
				List<CountriesHelper> countries = models.Forex.getReceiveCountryListByForex(helper.get().user, helper.get().user.corporate.country);
				ObjectMapper objectMapper = new ObjectMapper();
				String json = objectMapper.writeValueAsString(countries);
				ObjectNode response = Json.newObject();
				response.put("status", Definition.STATUS_OK);
				response.put("message","Get countries receive success");
				response.put("countries", objectMapper.readTree(json));
				Logger.info("Country Receive List Result " + response.toString());
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
