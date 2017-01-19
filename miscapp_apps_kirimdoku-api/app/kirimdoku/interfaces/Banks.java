package kirimdoku.interfaces;

import java.util.List;
import models.Bank;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import kirimdoku.helpers.BankHelper;
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
import static play.data.Form.form;

@Security.Authenticated(SecurityAgentMobileAuthenticator.class)
@With(LoggerAction.class)
public class Banks extends Controller {
	
	public static Result getList() {
		Logger.info("-------------------");
		Logger.info(":: GET BANK LIST :: ");
		Logger.info("===================");
		response().setContentType("application/json");
		Result result = null;
		try {
			Form<BankHelper> helper = form(BankHelper.class).bindFromRequest();
			Logger.info("Get List Request "+helper.data());
			if (helper.hasErrors()) {
				result = unauthorized(ResponseHelper.createResponse(
						Definition.STATUS_INVALIDPARAMS, "Invalid parameters",
						helper.errorsAsJson()));
				Logger.info("Invalid parameters : " + helper.errorsAsJson());
			} else {
				ObjectNode response = Json.newObject();
				if (helper.get().countryCode != null && !helper.get().countryCode.equals("")) {
					if (helper.get().groupName != null && !helper.get().groupName.equals("")) {
						List<Bank> banks = models.Bank.getList(helper.get().countryCode, helper.get().groupName, helper.get().provinceName, helper.get().cityName, helper.get().swiftCode, helper.get().bankName);
						ObjectMapper objectMapper = new ObjectMapper();
						String json = objectMapper.writeValueAsString(banks);
						response.put("status", Definition.STATUS_OK);
						response.put("message","Get banks success");
						response.put("banks", objectMapper.readTree(json));
					} else {
						response = ResponseHelper.createResponse(Definition.STATUS_INVALIDPARAMS, "Invalid group name");
					}
				} else {
					response = ResponseHelper.createResponse(Definition.STATUS_INVALIDPARAMS, "Invalid country code");
				}
				Logger.info("Get Bank List Result " + response.toString());
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
	
	public static Result getGroupBankList() {
		Logger.info("-------------------------");
		Logger.info(":: GET GROUP BANK LIST :: ");
		Logger.info("=========================");
		response().setContentType("application/json");
		Result result = null;
		try {
			Form<BankHelper> helper = form(BankHelper.class).bindFromRequest();
			Logger.info("Get Group Bank List Request "+helper.data());
			if (helper.hasErrors()) {
				result = unauthorized(ResponseHelper.createResponse(
						Definition.STATUS_INVALIDPARAMS, "Invalid parameters",
						helper.errorsAsJson()));
				Logger.info("Invalid parameters : " + helper.errorsAsJson());
			} else {
				List<String> groupBankList = models.Bank.getGroupBankList(helper.get().countryCode);
				ObjectMapper objectMapper = new ObjectMapper();
				String json = objectMapper.writeValueAsString(groupBankList);
				ObjectNode response = Json.newObject();
				response.put("status", Definition.STATUS_OK);
				response.put("message","Get groups bank success");
				response.put("groupsBank", objectMapper.readTree(json));
				Logger.info("Get Group Bank List Result " + response.toString());
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
	
	public static Result getProvinceBankList() {
		Logger.info("----------------------------");
		Logger.info(":: GET PROVINCE BANK LIST :: ");
		Logger.info("============================");
		response().setContentType("application/json");
		Result result = null;
		try {
			Form<BankHelper> helper = form(BankHelper.class).bindFromRequest();
			Logger.info("Get Province Bank List Request "+helper.data());
			if (helper.hasErrors()) {
				result = unauthorized(ResponseHelper.createResponse(
						Definition.STATUS_INVALIDPARAMS, "Invalid parameters",
						helper.errorsAsJson()));
				Logger.info("Invalid parameters : " + helper.errorsAsJson());
			} else {
				List<String> provinceBankList = models.Bank.getProvinceBankList(helper.get().countryCode, helper.get().groupName);
				ObjectMapper objectMapper = new ObjectMapper();
				String json = objectMapper.writeValueAsString(provinceBankList);
				ObjectNode response = Json.newObject();
				response.put("status", Definition.STATUS_OK);
				response.put("message","Get provinces bank success");
				response.put("provincesBank", objectMapper.readTree(json));
				Logger.info("Get Province Bank List Result " + response.toString());
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
	
	public static Result getCityBankList() {
		Logger.info("------------------------");
		Logger.info(":: GET CITY BANK LIST :: ");
		Logger.info("========================");
		response().setContentType("application/json");
		Result result = null;
		try {
			Form<BankHelper> helper = form(BankHelper.class).bindFromRequest();
			Logger.info("Get City Bank List Request "+helper.data());
			if (helper.hasErrors()) {
				result = unauthorized(ResponseHelper.createResponse(
						Definition.STATUS_INVALIDPARAMS, "Invalid parameters",
						helper.errorsAsJson()));
				Logger.info("Invalid parameters : " + helper.errorsAsJson());
			} else {
				List<String> cityBankList = models.Bank.getCityBankList(helper.get().countryCode, helper.get().groupName, helper.get().provinceName);
				ObjectMapper objectMapper = new ObjectMapper();
				String json = objectMapper.writeValueAsString(cityBankList);
				ObjectNode response = Json.newObject();
				response.put("status", Definition.STATUS_OK);
				response.put("message","Get cities bank success");
				response.put("citiesBank", objectMapper.readTree(json));
				Logger.info("Get City Bank List Result " + response.toString());
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
	
	public static Result getDataBank() {
		Logger.info("-------------------");
		Logger.info(":: GET DATA BANK :: ");
		Logger.info("===================");
		response().setContentType("application/json");
		Result result = null;
		try {
			Form<BankHelper> helper = form(BankHelper.class).bindFromRequest();
			Logger.info("Get Data Bank Request "+helper.data());
			if (helper.hasErrors()) {
				result = unauthorized(ResponseHelper.createResponse(
						Definition.STATUS_INVALIDPARAMS, "Invalid parameters",
						helper.errorsAsJson()));
				Logger.info("Invalid parameters : " + helper.errorsAsJson());
			} else {
				List<Bank> banks = models.Bank.getDataBankList(helper.get().channelCode, helper.get().countryCode);
				ObjectMapper objectMapper = new ObjectMapper();
				String json = objectMapper.writeValueAsString(banks);
				ObjectNode response = Json.newObject();
				response.put("status", Definition.STATUS_OK);
				response.put("message","Get Data Bank success");
				response.put("data", objectMapper.readTree(json));
				Logger.info("Get Data Bank Result " + response.toString());
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
