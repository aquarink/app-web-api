package controllers;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;
import java.util.ArrayList;

import kirimdoku.helpers.CallBackHelper;
import kirimdoku.helpers.KirimDokuHttpConnection;
import kirimdoku.util.Utility;
import models.TransactionFee;
import models.TransactionLog;
import models.User;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

import play.Logger;
import play.data.Form;
import static play.data.Form.form;
import play.data.validation.Constraints;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;
import controllers.helpers.Definition;
import controllers.helpers.ResponseHelper;
import controllers.helpers.SecurityAgentAuthenticator;
import controllers.tokens.AgentToken;
import controllers.tokens.CustomerToken;
import controllers.tokens.TransactionToken;

/**
 * Class wrappers of action for retrieving status/information about transaction
 * 
 * @author fauziassegaff
 * 
 */
@With(LoggerAction.class)
public class Transaction extends Controller {

	/**
	 *  Show information regarding transaction, and return as json object serialized (which can be deserialized)
	 *  It will use form object as the request informationn, do any necessary validations and check, retrieve from database, and response as json node
	 */
	
	@Security.Authenticated(SecurityAgentAuthenticator.class)
	public static Result show(String id) {
		// Create result object
		ObjectNode result = Json.newObject();

		try {
			TransactionToken transactionToken = TransactionToken.fromString(id);
			if(transactionToken != null) { 
				Logger.debug("Resulting transaction detils "+transactionToken.toString());
				models.Transaction transaction = models.Transaction.find
				        .fetch("channel")
				        .fetch("corporate")
				        .fetch("corporate.country")
				        .fetch("corporate.operation")
				        .fetch("corporate.finance")
				        .fetch("corporate.settlement")
				        .fetch("corporate.configuration")
				        .fetch("senderCountry")
				        .fetch("beneficiaryCountry")
				        .fetch("forexReference")
				        .fetch("forexReference.forex")
				        .fetch("sender")
				        .fetch("beneficiary")
				        .fetch("beneficiaryAccount")
				        .fetch("beneficiaryAccount.bank")
				        .fetch("agent")
				        .fetch("agent.country")
						.where().eq("id", transactionToken.transactionId).findUnique();
				if (transaction != null) {
					JsonNode transactionNode = Json.toJson(transaction);
					if (transaction.channel.code.equals("10")) {
						((ObjectNode)transactionNode).put("paymentData", transaction.paymentData);
					}
					result.put("transaction", transactionNode);
					result.put("status", Definition.STATUS_OK);
					result.put("message", "Transaction info success");
					try {
						String TPG_RESPONSE = TransactionLog.getMessage(transaction.id);
						result.put("transactionlog", Json.parse(TPG_RESPONSE));
					} catch (Exception e) {
						//e.printStackTrace();
					}
				} else {
					result.put("status", Definition.STATUS_NOTFOUND);
					result.put("message", "Transaction not found");
				}
			} else {
				result.put("status", Definition.STATUS_NOTFOUND);
				result.put("message", "Transaction token is invalid");
			}
		} catch (IllegalArgumentException e) {
			result.put("status", Definition.STATUS_ILLEGALPARAMS);
			result.put("message", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", Definition.STATUS_UNKNOWN);
			result.put("message", e.getMessage());
		}
		Logger.debug("Show Transaction result >> "+result.toString());
		return ok(result);
	}
	
	
	/**
	 *  Show information regarding transaction,
	 *  It will use form object as the request informationn, do any necessary validations and check, retrieve from database, and response as json node
	 */
	@Security.Authenticated(SecurityAgentAuthenticator.class)
	public static Result summary(String id) {
		Form<ShowForm> form = form(ShowForm.class).bindFromRequest();
		Logger.debug("Summary Transaction request : "+form.data());
		if (form.hasErrors()) {
			return unauthorized(ResponseHelper.createResponse(Definition.STATUS_INVALIDPARAMS, "Invalid parameters", form.errorsAsJson()));
		}

		// Create result object
		ObjectNode result =  Json.newObject();

		try {
			models.Transaction transaction = form.get().transaction;
			
			ObjectNode transactionNode = Json.newObject();
			transactionNode.put("id", form.get().token.toString());
			transactionNode.put("status", transaction.status);
			transactionNode.put("createdTime", transaction.createdTime.getTime());
			if (transaction.cashInTime != null) {
				transactionNode.put("cashInTime", Utility.datetimeFormatShow.format(transaction.cashInTime.getTime()));
			}
			if (transaction.cashOutTime != null) {
				transactionNode.put("cashOutTime", transaction.cashOutTime.getTime().getTime());
			}
			if (transaction.senderNote != null) {
				transactionNode.put("senderNote", transaction.senderNote);
			}

			ObjectNode agent = Json.newObject();
			{
				AgentToken agentToken = AgentToken.fromUser(transaction.agent);
				agent.put("code", agentToken.toString());
//				agent.put("country", transaction.senderCountry.code);
				agent.put("country", Json.toJson(transaction.agent.country));
				ObjectNode agentUser = Json.newObject();
				{
					agentUser.put("username;", transaction.agent.email);
					agentUser.put("name", transaction.agent.firstName + " " + transaction.agent.lastName);
					agent.put("user", agentUser);
				}
				transactionNode.put("agent", agent);
			}
			
			ObjectNode channel = Json.newObject();
			{
				channel.put("code", transaction.channel.code);
				channel.put("name", transaction.channel.name);
				transactionNode.put("channel", channel);
			}

			ObjectNode sender = Json.newObject();
			{
				CustomerToken customerToken = CustomerToken.fromCustomer(transaction.sender);
				sender.put("id", customerToken.toString());
				sender.put("firstName", transaction.sender.firstName);
				sender.put("lastName", transaction.sender.lastName);
				sender.put("personalIdType", transaction.sender.personalIdType);
				sender.put("personalId", transaction.sender.personalId);
				if (transaction.sender.birthDate != null)
					sender.put("birthDate", transaction.sender.birthDate.toString());
                // if (transaction.sender.birthPlace != null)
                    // sender.put("birthPlace", transaction.sender.birthPlace);
				sender.put("phoneNumber", transaction.sender.phoneNumber);
				sender.put("gender", String.valueOf(transaction.sender.gender).toUpperCase());
				sender.put("country", Json.toJson(transaction.sender.country));
				sender.put("cityName", transaction.sender.cityName);
				sender.put("address", transaction.sender.address);
				sender.put("postalCode", transaction.sender.postalCode);
				sender.put("email", transaction.sender.email);
				transactionNode.put("sender", sender);
			}

			ObjectNode beneficiary = Json.newObject();
			{
				CustomerToken customerToken = CustomerToken.fromCustomer(transaction.beneficiary);
				beneficiary.put("id", customerToken.toString());
				beneficiary.put("firstName", transaction.beneficiary.firstName);
				beneficiary.put("lastName", transaction.beneficiary.lastName);
				beneficiary.put("personalIdType", transaction.beneficiary.personalIdType);
				beneficiary.put("personalId", transaction.beneficiary.personalId);
				if (transaction.beneficiary.birthDate != null)
					beneficiary.put("birthDate", transaction.beneficiary.birthDate.toString());
                // if (transaction.beneficiary.birthPlace != null)
                    // beneficiary.put("birthPlace", transaction.beneficiary.birthPlace);
				beneficiary.put("phoneNumber", transaction.beneficiary.phoneNumber);
				beneficiary.put("gender", String.valueOf(transaction.sender.gender).toUpperCase());
				beneficiary.put("country", Json.toJson(transaction.beneficiary.country));
				beneficiary.put("cityName", transaction.beneficiary.cityName);
				beneficiary.put("address", transaction.beneficiary.address);
				beneficiary.put("postalCode", transaction.beneficiary.postalCode);
				beneficiary.put("email", transaction.beneficiary.email);
				transactionNode.put("beneficiary", beneficiary);
			}

			ObjectNode fund = Json.newObject();
			{
				ObjectNode fundOrigin = Json.newObject();
				fundOrigin.put("country", Json.toJson(transaction.senderCountry));
				fundOrigin.put("currency", transaction.senderCurrency.code);
				fundOrigin.put("amount", transaction.senderAmount);
				fund.put("origin", fundOrigin);

				ObjectNode fundDestination = Json.newObject();
				fundDestination.put("country", Json.toJson(transaction.beneficiaryCountry));
				fundDestination.put("currency", transaction.beneficiaryCurrency.code);
				fundDestination.put("amount", transaction.beneficiaryAmount);
				fund.put("destination", fundDestination);

				ObjectNode fundFees = Json.newObject();
				{
					List<TransactionFee> fees = TransactionFee.findByTransaction(transaction);
					ArrayNode fundFeesComponents = Json.newObject().arrayNode();
					BigDecimal total = new BigDecimal(0);
					for (TransactionFee fee : fees) {
						ObjectNode feeNode = Json.newObject();
						feeNode.put("description", fee.description);
						feeNode.put("amount", fee.amount);
						fundFeesComponents.add(feeNode);
						total = total.add(fee.amount);
					}
					fundFees.put("total", total);
					fundFees.put("currency", transaction.senderCurrency.code);
					fundFees.put("components", fundFeesComponents);
					fund.put("fees", fundFees);
				}
				transactionNode.put("fund", fund);
			}
			result.put("transaction", transactionNode);
			try {
				Logger.debug("Channel Code : "+transaction.channel.code);
				String TPG_RESPONSE = TransactionLog.getMessage(transaction.id);
				((ObjectNode)transactionNode).put("transactionlog", Json.parse(TPG_RESPONSE));
			} catch (Exception e) {
				//e.printStackTrace();
			}
			
			result.put("status", Definition.STATUS_OK);
			result.put("message", "Transaction info success");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			result.put("status", Definition.STATUS_ILLEGALPARAMS);
			result.put("message", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", Definition.STATUS_UNKNOWN);
			result.put("message", e.getMessage());
		}
		Logger.debug("Summary Transaction result : "+result.toString());
		return ok(result);
	}

	public static class ShowForm extends BaseForm {

		@Constraints.Required
		public String transactionId;
		
		/** Private fields used for internal use */
		private TransactionToken token;
		public models.Transaction transaction;

		public String validate() {
			token = TransactionToken.fromString(transactionId);
			if(token != null) {
				transaction = models.Transaction.findByToken(token);
				if (transaction == null) {
					return "Transaction could not be found";
				}
			} else {
				return "Transaction id is invalid";
			}

			return null;
		}
	}
	
	
	@Security.Authenticated(SecurityAgentAuthenticator.class)
	public static Result search() {
		Form<SearchForm> form = form(SearchForm.class).bindFromRequest();
		if (form.hasErrors()) {
			return unauthorized(ResponseHelper.createResponse(Definition.STATUS_INVALIDPARAMS, "Invalid parameters", form.errorsAsJson()));
		}
		
		ObjectNode results = ResponseHelper.createResponse();
		
		List<models.Transaction> transactions = new ArrayList<models.Transaction>();
		if (form.get().sendTrxId != null) {
			models.Transaction t = models.Transaction.find.where().eq("sendTrxId", form.get().sendTrxId).setMaxRows(1).findUnique();
			if (t != null) transactions.add(t);
		}
		if (form.get().receiveTrxId != null) {
			models.Transaction t = models.Transaction.find.where().eq("receiveTrxId", form.get().receiveTrxId).setMaxRows(1).findUnique();
			if (t != null) transactions.add(t);
		}
		
		results.put("status", 0);
		results.put("message", "list transactions by query, found "+transactions.size());
		ArrayNode transactionsNode = Json.newObject().arrayNode();
		for (models.Transaction transaction : transactions) {
			JsonNode tNode = Json.toJson(transaction);
			transactionsNode.add(tNode);
		}
		results.put("transactions", transactionsNode);
		return ok(results);
	}
	
	public static Result callback() {
		Logger.info("--------------------------");
		Logger.info(":: CALLBACK TRANSACTION :: ");
		Logger.info("==========================");
		try {
			Form<CallBackHelper> requestHelper = form(CallBackHelper.class).bindFromRequest();
			Logger.debug("Data callback >> "+requestHelper.data());
			if (requestHelper.hasErrors()) {
				Logger.debug("Invalid parameters, "+requestHelper.errorsAsJson());
				Logger.debug("Callback >> FAILED");
				return ok("FAILED");
			}
			if (requestHelper.get().transactionInquiry != null) {
				ObjectNode transactionLog = Json.newObject();
				Integer status = null;
				int statusCode = 11;
				String statusMessage = "Failed";
				if (requestHelper.get().getRESPONSECODE().equals("0000")) {
					Logger.debug("Set status paid");
					status = models.Transaction.STATUS_PAID;
					statusCode = 0;
					statusMessage = "Processed";
				} else {
					Logger.debug("Switching RC : "+requestHelper.get().getRESPONSECODE());
					status = models.Transaction.STATUS_UNPAID;
					statusCode = 11;
					Logger.debug("Set status unpaid");
				}
				if (status != requestHelper.get().transactionInquiry.transaction.status) {
					if (status == models.Transaction.STATUS_PAID && requestHelper.get().transactionInquiry.transaction.status != models.Transaction.STATUS_PAID) {
						User.updateCreditLastBalance(requestHelper.get().transactionInquiry.transaction.agent, requestHelper.get().transactionInquiry.transaction.senderAmount);
					} else if (status != models.Transaction.STATUS_PAID && requestHelper.get().transactionInquiry.transaction.status == models.Transaction.STATUS_PAID){
						User.updateCreditLastBalance(requestHelper.get().transactionInquiry.transaction.agent, requestHelper.get().transactionInquiry.transaction.senderAmount.negate());
					}
					transactionLog.put("referenceStatus", requestHelper.get().getRESPONSECODE());
					transactionLog.put("referenceMessage", requestHelper.get().getRESPONSEMESSAGE());
					transactionLog.put("channelCode", "10");
					Logger.debug("TPG_RESPONSE : "+transactionLog.toString());
					TransactionLog.insert(requestHelper.get().transactionInquiry.transaction, requestHelper.get().transactionInquiry.transaction.agent, "TPG_RESPONSE", transactionLog.toString());
					requestHelper.get().transactionInquiry.transaction.status = status;
					requestHelper.get().transactionInquiry.transaction.update();
				}
				String parameter = "transactionId="+URLEncoder.encode(requestHelper.get().transactionInquiry.transaction.idToken(), "utf-8")+"&statusCode="+statusCode+"&statusMessage="+URLEncoder.encode(statusMessage, "utf-8");
				String urls = requestHelper.get().transactionInquiry.transaction.corporate.configuration.notifyUrl;
				int timeout = 60000;
				String contentType = "application/x-www-form-urlencoded";
				KirimDokuHttpConnection connection = Utility.httpConnection(parameter, urls, timeout, contentType);
				Logger.debug("Response : "+connection.responseCode+" >> "+connection.responseMessage);
				Logger.debug("Callback >> SUCCESS");
				return ok("SUCCESS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.debug("Callback exception >> "+e.getMessage());
		}
		Logger.debug("Callback >> FAILED");
		return ok("FAILED");
	}
	
	public static class SearchForm extends BaseForm {
		public String sendTrxId;
		public String receiveTrxId;
	}

}
