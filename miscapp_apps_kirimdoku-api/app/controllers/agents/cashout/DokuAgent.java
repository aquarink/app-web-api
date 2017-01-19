package controllers.agents.cashout;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.persistence.PersistenceException;
import models.Transaction;
import models.TransactionInquiry;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;
import play.libs.Json;
import play.mvc.Http.Request;
import play.mvc.Result;
import play.mvc.Results;
import controllers.CashOut.CollectForm;
import controllers.CashOut.InquiryForm;
import controllers.CashOut.ValidateForm;
import controllers.actors.SuspiciousActor;
import controllers.helpers.Definition;
import controllers.helpers.ResponseHelper;
import controllers.tokens.TransactionToken;

public class DokuAgent extends BaseAgent {

	@Override
	public Result onPing(Request request) {
		ObjectNode result = Json.newObject();
		result.put("status", "0");
		result.put("message", "PING OK");
		return play.mvc.Results.ok(result);
	}

	@Override
	public Result onInquiry(Request request, InquiryForm form) {
		
		Transaction transaction = null;
		if((form.transaction.idToken != null) && !form.transaction.idToken.isEmpty()) {
			TransactionToken trxToken = TransactionToken.fromString(form.transaction.idToken());
			if(trxToken == null) {
				return Results.ok(ResponseHelper.createResponse(Definition.STATUS_INVALIDPARAMS, "Invalid transaction id token"));
			}
			
			transaction = Transaction.findByToken(trxToken);
		} else if((form.transaction.sendTrxId != null) && !form.transaction.sendTrxId.isEmpty()) {
			transaction = models.Transaction.find.where().eq("corporate", form.corporate).eq("sendTrxId", form.transaction.sendTrxId).findUnique();
		}
		
		if (transaction == null) {
			return Results.ok(ResponseHelper.createResponse(Definition.STATUS_INVALIDPARAMS, "Invalid transaction id"));
		}
		
		if(true) {
			Result rulesResult = Transaction.checkReceiveRules(transaction, form.user);
			if(rulesResult != null) {
				return rulesResult;
			}
		}
		
		ObjectNode result = ResponseHelper.createResponse();
		result.put("status", Definition.STATUS_OK);
		result.put("message", "Inquiry succeed");
		
		// Save trx inquiry to db
		TransactionInquiry trxInq = new TransactionInquiry();
		trxInq.typeId = TransactionInquiry.Type.CASHOUT_INQUIRY;
		trxInq.user = form.user;
		trxInq.requestTime = new Date();
		trxInq.transaction = transaction;
		trxInq.save();

		result.put("inquiry", Json.toJson(trxInq));
		return Results.ok(result);
	}
	
	@Override
	public Result onValidate(Request request, ValidateForm form, Transaction transaction) {
		ObjectNode result = ResponseHelper.createResponse();

		int validateStatus = -1;
		validateStatus = validateAuth(transaction, form);
		if (validateStatus == 0) {
			form.inquiry.generateValidationId();
			form.inquiry.save();
			
			result.put("status", Definition.STATUS_OK);
			result.put("message", "Ok");

			ObjectNode validate = Json.newObject();
			validate.put("validationId", form.inquiry.validationId);

			result.put("validation", validate);

			return Results.ok(result);
		} else if (validateStatus == 3) {
			result.put("status", Definition.STATUS_BLOCKED);
			result.put("message", "Transaction has been blocked");
			return Results.unauthorized(result);
		} else if (validateStatus == 4) {
			result.put("status", Definition.STATUS_UNAUTHORIZED);
			result.put("message", "Invalid PIN");
			return Results.unauthorized(result);
		} else {
			result.put("status", Definition.STATUS_UNAUTHORIZED);
			result.put("message", "Invalid authorization");
			return Results.unauthorized(result);
		}
	}

	@Override
	public Result onCollect(Request request, CollectForm form, Transaction transaction) {
		ObjectNode result = ResponseHelper.createResponse();
		
		System.out.println("channel code : "+form.user.corporate.configuration.channelCode);
		
		Map<String, String> mapChannel = new HashMap<String, String>();
		try{
			String channelCode = form.user.corporate.configuration.channelCode;
			
			if (channelCode != null && !channelCode.trim().equals("")) {
				String[] channel = channelCode.split(";");
				for (int i = 0; i < channel.length; i++) {
					mapChannel.put(channel[i], channel[i]);
				}
			}
		} catch (Exception e) { }
		
		if (mapChannel.containsKey(transaction.channel.code)) {
			if (transaction.auth1.equals(form.auth2)) {
				form.inquiry.generateCollectId();
				form.inquiry.transaction.beneficiaryAgent = form.user;
				form.inquiry.transaction.cashOutTime = Calendar.getInstance();	
				if (form.cashOutTime != null) {
					form.inquiry.transaction.cashOutTime.setTime(form.cashOutTime);
				}
				form.inquiry.transaction.receiveTrxId = form.receiveTrxId;
				form.inquiry.transaction.status = Transaction.STATUS_PAID;
				try{
					form.inquiry.transaction.save();
					
					form.inquiry.save();
					
					result.put("status", Definition.STATUS_OK);
					result.put("message", "Ok");
		
					ObjectNode collect = Json.newObject();
					collect.put("referenceId", form.inquiry.collectId);
		
					result.put("collect", collect);
		
					SuspiciousActor.getRefInstance().tell(form.inquiry.transaction);
					return Results.ok(result);
				
				}catch(PersistenceException ex) {
					//ex.printStackTrace();
					System.out.println("Paramater already exists");
					result.put("status", Definition.STATUS_INVALIDPARAMS);
					result.put("message", "Paramater already exists");
					return Results.ok(result);
				}
			} else {
				result.put("status", Definition.STATUS_UNAUTHORIZED);
				result.put("message", "Invalid auth1");
				return Results.unauthorized(result);
			}
		} else {
			result.put("status", Definition.STATUS_UNAUTHORIZED);
			result.put("message", "Restricted by transaction channel");
			return Results.unauthorized(result);
		}
	}

	/**
	 * The process of validating pin and its kind of logic
	 *
	 * @return int status of validation
	 * <pre>
	 * -1 - Invalid params, internal error, etc
	 * 0 - Status ok
	 * 1 - Invalid parameteres
	 * 2 - Unauthorized validation
	 * 3 - PIN blocked
	 * 4 - PIN is not match
	 * </pre>
	 */
	private int validateAuth(Transaction transaction, ValidateForm form) {
		String realAuth1 = transaction.auth1;
		String tryAuth1 = form.auth1;
		
		// Check various parameters that could make error
		if ((realAuth1 == null) || (tryAuth1 == null)) return -1;
		if ((tryAuth1.length() < 4) || (tryAuth1.length() > 32)) {
			// avoid any illegal burst
			tryAuth1 = "0";
		}
		
		if (transaction.status == Transaction.STATUS_PENDING) {
			if (realAuth1.equals(tryAuth1)) {
				return 0;
			} else {
				int invalidCount = TransactionInquiry.findRowCountByInvalidAuth(transaction)+1;
				form.inquiry.invalidAuthCount += 1;
				form.inquiry.save();
				if (invalidCount >= 3) {
					transaction.status = Transaction.STATUS_LOCKED;
					transaction.save();
					return 3;
				}
				return 4;
			}
		} else {
			return 2;
		}
	}

	private JsonNode getDummyInquiry() {

		ObjectNode inquiry = Json.newObject();
		inquiry.put("inquiryId", 821821);
		inquiry.put("transactionId", "T0AAAMYS02000034");
		inquiry.put("transactionDate", "2012-07-30 14:20:12+07:00");

		ObjectNode agent = Json.newObject();
		agent.put("code", "CC-3245678");
		agent.put("country", "MY");
		ObjectNode agentUser = Json.newObject();
		agentUser.put("username", "ada.bertugh2@yahoo.com");
		agentUser.put("name", "Ada Bertugh");

		agent.put("user", agentUser);
		inquiry.put("agent", agent);

		ObjectNode sender = Json.newObject();
		sender.put("id", "01272617218");
		sender.put("firstName", "John");
		sender.put("lastName", "Doe");
		sender.put("personalIdType", "Citizen");
		sender.put("personalId", "12374839111");
		sender.put("birthDate", "1975-08-14");
		sender.put("birthPlace", "Baturang");
		sender.put("msisdn", "618122234566");
		sender.put("gender", "male");
		sender.put("country", "MY");
		sender.put("city", "Johor");
		sender.put("address", "Jln. johor selatan 4");
		sender.put("postalCode", "4023");
		sender.put("emailAddress", "john.doe@yahoo.com");
		inquiry.put("sender", sender);

		ObjectNode beneficiary = Json.newObject();
		beneficiary.put("id", "082727129121");
		beneficiary.put("firstName", "Sumina");
		beneficiary.put("lastName", "Rejo");
		beneficiary.put("personalIdType", "Citizen");
		beneficiary.put("personalId", "12374839111");
		beneficiary.put("birthDate", "1978-01-12");
		beneficiary.put("birthPlace", "solo");
		beneficiary.put("msisdn", "62876554433312");
		beneficiary.put("gender", "male");
		beneficiary.put("country", "ID");
		beneficiary.put("city", "Solo");
		beneficiary.put("address", "Jln. solo pelang No. 77 RT03/RW04");
		beneficiary.put("postalCode", "12001");
		sender.put("emailAddress", "sumina.rejo@yahoo.com");
		inquiry.put("beneficiary", beneficiary);

		ObjectNode fund = Json.newObject();
		ObjectNode fundSource = Json.newObject();
		fundSource.put("currency", "MYR");
		fundSource.put("amount", 250.52);
		fund.put("source", fundSource);

		ObjectNode fundDestination = Json.newObject();
		fundDestination.put("currency", "IDR");
		Random random = new Random();
		fundDestination.put("currency", "IDR");
		fundDestination.put("amount", Math.round((100000 + random.nextInt(1000000)) / 100) * 100);
		fund.put("destination", fundDestination);

		ObjectNode fundFees = Json.newObject();
		fundFees.put("currency", "IDR");
		fundFees.put("adminFee", 1500);
		fundFees.put("agentFee", 1000);
		fundFees.put("taxes", 400);
		fundFees.put("total", 2200);

		fund.put("fees", fundFees);
		inquiry.put("fund", fund);

		return inquiry;
	}
}
