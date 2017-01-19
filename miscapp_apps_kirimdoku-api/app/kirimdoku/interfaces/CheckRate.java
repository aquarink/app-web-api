package kirimdoku.interfaces;

import static play.data.Form.form;
import java.math.BigDecimal;
import java.util.List;
import models.ForexReference;
import models.TransactionFee;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import kirimdoku.helpers.CheckRateHelper;
import kirimdoku.helpers.SecurityAgentMobileAuthenticator;
import kirimdoku.util.Utility;
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
public class CheckRate extends Controller {
	
	public static Result process() {
		Logger.info("------------------------");
		Logger.info(":: CHECK RATE PROCESS :: ");
		Logger.info("========================");
		Result result = null;
		try {
			Form<CheckRateHelper> helper = form(CheckRateHelper.class)
					.bindFromRequest();
			Logger.info("Check Rate Process Request " + helper.data());
			if (helper.hasErrors()) {
				result = unauthorized(ResponseHelper.createResponse(
						Definition.STATUS_INVALIDPARAMS, "Invalid parameters",
						helper.errorsAsJson()));
				Logger.info("Invalid parameters : " + helper.errorsAsJson());
			} else {
				ObjectNode response = Json.newObject();
				ForexReference forexReference = Utility.getLastForexReference(helper.get().corporate, helper.get().senderCurrency, helper.get().beneficiaryCurrency);
				if (forexReference == null) {
					response = ResponseHelper.createResponse(Definition.STATUS_UNSUPPORTED, "No forex found for destination country");
				} else {
					BigDecimal beneficiaryAmount = Utility.formFund(helper.get().senderAmount, forexReference);
					List<TransactionFee> transactionFees = Utility.formFee(helper.get().corporate, helper.get().senderCountry, helper.get().beneficiaryCountry, helper.get().channel, helper.get().senderAmount, helper.get().senderCurrency, helper.get().beneficiaryCurrency);

					//sender
					ObjectNode fund = Json.newObject();
					ObjectNode fundOrigin = Json.newObject();
					fundOrigin.put("amount", helper.get().senderAmount);
					fundOrigin.put("currency", helper.get().senderCurrency.code);
					fund.put("origin", fundOrigin);
					
					//fundDestination
					ObjectNode fundDestination = Json.newObject();
					fundDestination.put("amount", beneficiaryAmount);
					fundDestination.put("currency", helper.get().beneficiaryCurrency.code);
					fund.put("destination", fundDestination);
					
					//fundFees
					ObjectNode fundFees = Json.newObject();
					ArrayNode fundFeesComponents = Json.newObject().arrayNode();
					BigDecimal total = new BigDecimal(0);
					for (TransactionFee fee : transactionFees) {
						ObjectNode feeNode = Json.newObject();
						feeNode.put("description", fee.description);
						feeNode.put("amount", fee.amount);
						fundFeesComponents.add(feeNode);
						total = total.add(fee.amount);
					}
					fundFees.put("total", total);
					fundFees.put("currency", helper.get().senderCurrency.code);
					fundFees.put("components", fundFeesComponents);
					fund.put("fees", fundFees);
					
					//rates
					ObjectNode rates = Json.newObject();
					rates.put("fund", fund);
					rates.put("senderCountry", Json.toJson(helper.get().senderCountry));
					rates.put("senderCurrency", Json.toJson(helper.get().senderCurrency));
					rates.put("beneficiaryCountry", Json.toJson(helper.get().beneficiaryCountry));
					rates.put("beneficiaryCurrency", Json.toJson(helper.get().beneficiaryCurrency));
					rates.put("channel", Json.toJson(helper.get().channel));
					
					ObjectMapper objectMapper = new ObjectMapper();
					objectMapper.setDateFormat(Utility.datetimeFormatShow);
					rates.put("forexReference", objectMapper.valueToTree(forexReference));
	
					// Final result
					response.put("status", 0);
					response.put("message", "Check Rates succeed");
					response.put("rates", rates);
				}
				Logger.info("Check Rate Process Result : " + response.toString());
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
