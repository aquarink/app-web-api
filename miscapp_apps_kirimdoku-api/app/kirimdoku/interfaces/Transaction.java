package kirimdoku.interfaces;

import static play.data.Form.form;
import java.math.BigDecimal;
import java.util.List;
import kirimdoku.helpers.ReportTransactionHelper;
import kirimdoku.helpers.SecurityAgentMobileAuthenticator;
import kirimdoku.helpers.TransactionLookUpHelper;
import kirimdoku.util.Utility;
import models.TransactionFee;
import models.TransactionLog;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import com.avaje.ebean.Page;
import controllers.LoggerAction;
import controllers.helpers.Definition;
import controllers.helpers.ResponseHelper;
import controllers.tokens.AgentToken;
import controllers.tokens.CustomerToken;
import controllers.tokens.TransactionToken;
import play.Logger;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;

@Security.Authenticated(SecurityAgentMobileAuthenticator.class)
@With(LoggerAction.class)
public class Transaction extends Controller {

	public static Result getTransaction() {
		Logger.info("----------------------");
		Logger.info(":: TRANSACTION SHOW :: ");
		Logger.info("======================");
		Result result = null;
		try {
			Form<TransactionLookUpHelper> helper = form(
					TransactionLookUpHelper.class).bindFromRequest();
			Logger.info("Transaction Show Request " + helper.data());
			if (helper.hasErrors()) {
				result = unauthorized(ResponseHelper.createResponse(
						Definition.STATUS_INVALIDPARAMS, "Invalid parameters",
						helper.errorsAsJson()));
				Logger.info("Invalid parameters : " + helper.errorsAsJson());
			} else {
				ObjectNode response = Json.newObject();
				if (helper.get().transaction != null) {
					response.put("status", Definition.STATUS_OK);
					response.put("message", "Transaction info success");
					ObjectMapper objectMapper = new ObjectMapper();
					objectMapper.setDateFormat(Utility.datetimeFormatShow);
					JsonNode transactionNode = objectMapper.valueToTree(helper.get().transaction);
					response.put("transaction", transactionNode);
					try {
						String TPG_RESPONSE = TransactionLog.getMessage(helper.get().transaction.id);
						response.put("transactionlog",
								Json.parse(TPG_RESPONSE));
						Logger.info("Transaction Show Result "
								+ response.toString());
					} catch (Exception e) {
						Logger.info("Get tpg reponse failed : "
								+ e.getMessage());
					}
				} else {
					response = ResponseHelper.createResponse(
							Definition.STATUS_NOTFOUND,
							"Transaction not found");
				}
				Logger.info("Transaction Show Result " + response.toString());
				result = ok(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = ok(ResponseHelper.createResponse(
					Definition.STATUS_UNKNOWN, e.getMessage()));
			Logger.info("Exception : " + e.getMessage());
		}
		Logger.info("--------------------");
		return result;
	}

	public static Result summary(String id) {
		Logger.info("-------------------------");
		Logger.info(":: TRANSACTION SUMMARY :: ");
		Logger.info("=========================");
		Result result = null;
		try {
			Form<TransactionLookUpHelper> helper = form(
					TransactionLookUpHelper.class).bindFromRequest();
			Logger.info("Transaction Summary Request " + helper.data());
			if (helper.hasErrors()) {
				result = unauthorized(ResponseHelper.createResponse(
						Definition.STATUS_INVALIDPARAMS, "Invalid parameters",
						helper.errorsAsJson()));
				Logger.info("Invalid parameters : " + helper.errorsAsJson());
			} else {
				// TRANSACTION
				models.Transaction transaction = helper.get().transaction;
				ObjectNode transactionNode = Json.newObject();
				transactionNode.put("id", helper.get().token.toString());
				transactionNode.put("status", transaction.status);
				transactionNode.put("createdTime",
						transaction.createdTime.getTime());
				if (transaction.cashInTime != null) {
					transactionNode.put("cashInTime", Utility.datetimeFormatShow.format(transaction.cashInTime));
				}
				if (transaction.cashOutTime != null) {
					transactionNode.put("cashOutTime", transaction.cashOutTime
							.getTime().getTime());
				}
				if (transaction.senderNote != null) {
					transactionNode.put("senderNote", transaction.senderNote);
				}

				// AGENT
				ObjectNode agent = Json.newObject();
				AgentToken agentToken = AgentToken.fromUser(transaction.agent);
				agent.put("code", agentToken.toString());
				agent.put("country", Json.toJson(transaction.agent.country));
				ObjectNode agentUser = Json.newObject();
				agentUser.put("username;", transaction.agent.email);
				agentUser.put("name", transaction.agent.firstName + " "
						+ transaction.agent.lastName);
				agent.put("user", agentUser);
				transactionNode.put("agent", agent);

				// CHANNEL
				ObjectNode channel = Json.newObject();
				channel.put("code", transaction.channel.code);
				channel.put("name", transaction.channel.name);
				transactionNode.put("channel", channel);

				// SENDER
				ObjectNode sender = Json.newObject();
				CustomerToken customerTokenSender = CustomerToken
						.fromCustomer(transaction.sender);
				sender.put("id", customerTokenSender.toString());
				sender.put("firstName", transaction.sender.firstName);
				sender.put("lastName", transaction.sender.lastName);
				sender.put("personalIdType", transaction.sender.personalIdType);
				sender.put("personalId", transaction.sender.personalId);
				if (transaction.sender.birthDate != null)
					sender.put("birthDate",
							transaction.sender.birthDate.toString());
				sender.put("phoneNumber", transaction.sender.phoneNumber);
				sender.put("gender", String.valueOf(transaction.sender.gender)
						.toUpperCase());
				sender.put("country", Json.toJson(transaction.sender.country));
				sender.put("cityName", transaction.sender.cityName);
				sender.put("address", transaction.sender.address);
				sender.put("postalCode", transaction.sender.postalCode);
				sender.put("email", transaction.sender.email);
				transactionNode.put("sender", sender);

				// BENEFICIARY
				ObjectNode beneficiary = Json.newObject();
				CustomerToken customerTokenBeneficiary = CustomerToken
						.fromCustomer(transaction.beneficiary);
				beneficiary.put("id", customerTokenBeneficiary.toString());
				beneficiary.put("firstName", transaction.beneficiary.firstName);
				beneficiary.put("lastName", transaction.beneficiary.lastName);
				beneficiary.put("personalIdType",
						transaction.beneficiary.personalIdType);
				beneficiary.put("personalId",
						transaction.beneficiary.personalId);
				if (transaction.beneficiary.birthDate != null)
					beneficiary.put("birthDate",
							transaction.beneficiary.birthDate.toString());
				beneficiary.put("phoneNumber",
						transaction.beneficiary.phoneNumber);
				beneficiary
						.put("gender", String
								.valueOf(transaction.sender.gender)
								.toUpperCase());
				beneficiary.put("country",
						Json.toJson(transaction.beneficiary.country));
				beneficiary.put("cityName", transaction.beneficiary.cityName);
				beneficiary.put("address", transaction.beneficiary.address);
				beneficiary.put("postalCode",
						transaction.beneficiary.postalCode);
				beneficiary.put("email", transaction.beneficiary.email);
				transactionNode.put("beneficiary", beneficiary);

				// FUND
				ObjectNode fund = Json.newObject();
				ObjectNode fundOrigin = Json.newObject();
				fundOrigin.put("country",
						Json.toJson(transaction.senderCountry));
				fundOrigin.put("currency", transaction.senderCurrency.code);
				fundOrigin.put("amount", transaction.senderAmount);
				fund.put("origin", fundOrigin);

				ObjectNode fundDestination = Json.newObject();
				fundDestination.put("country",
						Json.toJson(transaction.beneficiaryCountry));
				fundDestination.put("currency",
						transaction.beneficiaryCurrency.code);
				fundDestination.put("amount", transaction.beneficiaryAmount);
				fund.put("destination", fundDestination);

				ObjectNode fundFees = Json.newObject();
				List<TransactionFee> fees = TransactionFee
						.findByTransaction(transaction);
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
				transactionNode.put("fund", fund);

				ObjectNode response = Json.newObject();
				response.put("transaction", transactionNode);
				response.put("status", Definition.STATUS_OK);
				response.put("message", "Transaction info success");
				Logger.info("Transaction Summary Result " + response.toString());
				result = ok(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = ok(ResponseHelper.createResponse(
					Definition.STATUS_UNKNOWN, e.getMessage()));
			Logger.info("Exception : " + e.getMessage());
		}
		Logger.info("--------------------");
		return result;
	}

	public static Result getTransactionList() {
		Logger.info("--------------------------");
		Logger.info(":: GET TRANSACTION LIST :: ");
		Logger.info("==========================");
		response().setContentType("application/json");
		Result result = null;
		try {
			Form<ReportTransactionHelper> helper = form(
					ReportTransactionHelper.class).bindFromRequest();
			Logger.info("Get Transaction List Request " + helper.data());
			if (helper.hasErrors()) {
				result = unauthorized(ResponseHelper.createResponse(
						Definition.STATUS_INVALIDPARAMS, "Invalid parameters",
						helper.errorsAsJson()));
				Logger.info("Invalid parameters : " + helper.errorsAsJson());
			} else {
				Page<models.Transaction> pageTransaction = models.Transaction
						.reportPage(helper.get());
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.setDateFormat(Utility.datetimeFormatShow);
				ObjectNode response = Json.newObject();
				
				ObjectNode transactionsNode = Json.newObject();
				ArrayNode arrayNode = transactionsNode.putArray("transactions");
				if (pageTransaction != null) {
					for (models.Transaction transaction : pageTransaction.getList()) {
						String jsonTransaction = objectMapper.writeValueAsString(transaction);
						ObjectNode transactionNode = (ObjectNode) Json.parse(jsonTransaction);
						try {
							Logger.debug("Channel Code : "+transaction.channel.code);
							String TPG_RESPONSE = TransactionLog.getMessage(transaction.id);
							transactionNode.put("transactionlog", Json.parse(TPG_RESPONSE));
						} catch (Exception e) {
						}
						arrayNode.add(transactionNode);
					}
				}
				response.put("status", Definition.STATUS_OK);
				response.put("message","Get transaction list success");
				response.put("transactions", arrayNode);
				response.put("totalRowCount", helper.get().totalRowCount);
				response.put("totalPageCount", helper.get().totalPageCount);
				Logger.info("Get Transaction List Result " + response.toString());
				result = ok(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = ok(ResponseHelper.createResponse(
					Definition.STATUS_UNKNOWN, e.getMessage()));
			Logger.info("Exception : " + e.getMessage());
		}
		Logger.info("--------------------");
		return result;
	}

	public static Result getTransactionRefund() {
		Logger.info("----------------------------");
		Logger.info(":: GET TRANSACTION REFUND :: ");
		Logger.info("============================");
		Result result = null;
		try {
			Form<TransactionLookUpHelper> helper = form(
					TransactionLookUpHelper.class).bindFromRequest();
			Logger.info("Get Transaction Refund Request " + helper.data());
			if (helper.hasErrors()) {
				result = unauthorized(ResponseHelper.createResponse(
						Definition.STATUS_INVALIDPARAMS, "Invalid parameters",
						helper.errorsAsJson()));
				Logger.info("Invalid parameters : " + helper.errorsAsJson());
			} else {
				TransactionToken transactionToken = TransactionToken
						.fromString(helper.get().transactionId);
				ObjectNode response = Json.newObject();
				if (transactionToken != null) {
					models.Transaction transaction = models.Transaction.find
							.fetch("beneficiaryAccount")
							.fetch("beneficiaryAccount.bank").where()
							.eq("id", transactionToken.transactionId)
							.eq("corporate", helper.get().corporate)
							.findUnique();
					if (transaction != null) {
						if (transaction.status == models.Transaction.STATUS_PENDING
								|| transaction.status == models.Transaction.STATUS_PENDING_REFUND) {
							JsonNode transactionNode = Json.toJson(transaction);
							response.put("transaction", transactionNode);
							response.put("status", Definition.STATUS_OK);
							response.put("message",
									"Transaction refund info success");
						} else {
							response = ResponseHelper.createResponse(
									Definition.STATUS_UNSUPPORTED,
									"Unable to Refund");
						}
					} else {
						response = ResponseHelper.createResponse(
								Definition.STATUS_NOTFOUND,
								"Transaction not found");
					}
				} else {
					response = ResponseHelper.createResponse(
							Definition.STATUS_NOTFOUND,
							"Transaction token is invalid");
				}
				Logger.info("Get Transaction Refund Result " + response.toString());
				result = ok(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = ok(ResponseHelper.createResponse(
					Definition.STATUS_UNKNOWN, e.getMessage()));
			Logger.info("Exception : " + e.getMessage());
		}
		Logger.info("--------------------");
		return result;
	}

	public static Result getTransactionChange() {
		Logger.info("----------------------------");
		Logger.info(":: GET TRANSACTION CHANGE :: ");
		Logger.info("============================");
		Result result = null;
		try {
			Form<TransactionLookUpHelper> helper = form(
					TransactionLookUpHelper.class).bindFromRequest();
			Logger.info("Get Transaction Change Request " + helper.data());
			if (helper.hasErrors()) {
				result = unauthorized(ResponseHelper.createResponse(
						Definition.STATUS_INVALIDPARAMS, "Invalid parameters",
						helper.errorsAsJson()));
				Logger.info("Invalid parameters : " + helper.errorsAsJson());
			} else {
				TransactionToken transactionToken = TransactionToken
						.fromString(helper.get().transactionId);
				ObjectNode response = Json.newObject();
				if (transactionToken != null) {
					models.Transaction transaction = models.Transaction.find
							.fetch("beneficiaryAccount")
							.fetch("beneficiaryAccount.bank").where()
							.eq("id", transactionToken.transactionId)
							.eq("corporate", helper.get().corporate)
							.findUnique();
					if (transaction != null) {
						if (transaction.status == models.Transaction.STATUS_PENDING) {
							JsonNode transactionNode = Json.toJson(transaction);
							response.put("transaction", transactionNode);
							response.put("status", Definition.STATUS_OK);
							response.put("message",
									"Transaction change info success");
						} else {
							response = ResponseHelper.createResponse(
									Definition.STATUS_UNSUPPORTED,
									"Unable to Change Beneficiary Info");
						}
					} else {
						response = ResponseHelper.createResponse(
								Definition.STATUS_NOTFOUND,
								"Transaction not found");
					}
				} else {
					response = ResponseHelper.createResponse(
							Definition.STATUS_NOTFOUND,
							"Transaction token is invalid");
				}
				Logger.info("Get Transaction Change Result " + response.toString());
				result = ok(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = ok(ResponseHelper.createResponse(
					Definition.STATUS_UNKNOWN, e.getMessage()));
			Logger.info("Exception : " + e.getMessage());
		}
		Logger.info("--------------------");
		return result;
	}

	public static Result getTransactionLocked() {
		Logger.info("----------------------------");
		Logger.info(":: GET TRANSACTION LOCKED :: ");
		Logger.info("============================");
		Result result = null;
		try {
			Form<TransactionLookUpHelper> helper = form(
					TransactionLookUpHelper.class).bindFromRequest();
			Logger.info("Get Transaction Locked Request " + helper.data());
			if (helper.hasErrors()) {
				result = unauthorized(ResponseHelper.createResponse(
						Definition.STATUS_INVALIDPARAMS, "Invalid parameters",
						helper.errorsAsJson()));
				Logger.info("Invalid parameters : " + helper.errorsAsJson());
			} else {
				TransactionToken transactionToken = TransactionToken
						.fromString(helper.get().transactionId);
				ObjectNode response = Json.newObject();
				if (transactionToken != null) {
					models.Transaction transaction = models.Transaction.find
							.fetch("beneficiaryAccount")
							.fetch("beneficiaryAccount.bank").where()
							.eq("id", transactionToken.transactionId)
							.eq("corporate", helper.get().corporate)
							.findUnique();
					if (transaction != null) {
						if (transaction.status == models.Transaction.STATUS_LOCKED
								&& transaction.beneficiaryAgent == null
								&& transaction.channel.code.equals("02")) {
							JsonNode transactionNode = Json.toJson(transaction);
							response.put("transaction", transactionNode);
							response.put("status", Definition.STATUS_OK);
							response.put("message",
									"Transaction locked info success");
						} else {
							response = ResponseHelper.createResponse(
									Definition.STATUS_UNSUPPORTED,
									"Unable to Unlock Transaction");
						}
					} else {
						response = ResponseHelper.createResponse(
								Definition.STATUS_NOTFOUND,
								"Transaction not found");
					}
				} else {
					response = ResponseHelper.createResponse(
							Definition.STATUS_NOTFOUND,
							"Transaction token is invalid");
				}
				Logger.info("Get Transaction Locked Result " + response.toString());
				result = ok(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = ok(ResponseHelper.createResponse(
					Definition.STATUS_UNKNOWN, e.getMessage()));
			Logger.info("Exception : " + e.getMessage());
		}
		Logger.info("--------------------");
		return result;
	}

}
