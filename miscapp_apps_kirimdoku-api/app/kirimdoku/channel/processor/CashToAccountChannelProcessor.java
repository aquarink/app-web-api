package kirimdoku.channel.processor;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.PersistenceException;

import models.Transaction;
import models.TransactionFee;
import models.TransactionInquiry;
import models.User;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

import controllers.actors.SuspiciousActor;
import controllers.helpers.Definition;
import controllers.helpers.ResponseHelper;
import controllers.tokens.TransactionToken;
import kirimdoku.helpers.CashInInquiryHelper;
import kirimdoku.helpers.CashOutCollectHelper;
import kirimdoku.helpers.CashOutInquiryHelper;
import kirimdoku.helpers.CashOutValidateHelper;
import kirimdoku.helpers.CashInRemitHelper;
import kirimdoku.util.Plugin;
import kirimdoku.util.Utility;
import play.Logger;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.Results;

public class CashToAccountChannelProcessor extends BaseChannelProcessor {

	@Override
	public Result Ping() {
		ObjectNode result = Json.newObject();
		result.put("status", "0");
		result.put("message", "PING OK");
		return play.mvc.Results.ok(result);
	}

	@Override
	public Result CashInInquiry(CashInInquiryHelper helper) {
		Logger.info("-----------------------------------------------");
		Logger.info(":: PROCESSOR CASH TO ACCOUNT CHANNEL INQUIRY :: ");
		Logger.info("===============================================");
		
		// Create result object
		ObjectNode result = Json.newObject();
		try {
			// Do some logic processing from outside this method scope
			Utility.processFormInquiry(helper);
			Utility.processFormForex(helper);

			if (helper.forexRef == null) {
				result = ResponseHelper.createResponse(Definition.STATUS_UNSUPPORTED, "No forex found for destination country");
				return Results.ok(result);
			}

			Utility.processFormFund(helper);
			Utility.processFormFee(helper);

			//START PLUGIN
			ObjectNode resultPlugin = Plugin.corporateHasExceedSendLimit(helper.corporate, helper.senderAmount);
			if(resultPlugin != null) return Results.ok(resultPlugin);
			
			resultPlugin = Plugin.operatorHasExceedSendLimit(helper.user, helper.senderAmount);
			if(resultPlugin != null) return Results.ok(resultPlugin);
			//END PLUGIN
			
			// Start to generate and build Response
			ObjectNode inquiry = Json.newObject();
			inquiry.put("idToken", helper.idToken);

			ObjectNode fund = Json.newObject();
			ObjectNode fundOrigin = Json.newObject();
			fundOrigin.put("amount", helper.senderAmount);
			fundOrigin.put("currency", helper.senderCurrency.code);
			fund.put("origin", fundOrigin);

			ObjectNode fundFees = Json.newObject();
			ArrayNode fundFeesComponents = Json.newObject().arrayNode();
			BigDecimal total = new BigDecimal(0);
			BigDecimal additionalFee = new BigDecimal(0);
			for (TransactionFee fee : helper.fees) {
				ObjectNode feeNode = Json.newObject();
				feeNode.put("description", fee.description);
				feeNode.put("amount", fee.amount);
				fundFeesComponents.add(feeNode);
				total = total.add(fee.amount);
				additionalFee = additionalFee.add(fee.additionalFee);
			}
			helper.beneficiaryAmount = helper.beneficiaryAmount.subtract(additionalFee);
			
			ObjectNode fundDestination = Json.newObject();
			fundDestination.put("amount", helper.beneficiaryAmount);
			fundDestination.put("currency", helper.beneficiaryCurrency.code);
			fund.put("destination", fundDestination);
			
			fundFees.put("total", total);
			fundFees.put("currency", helper.senderCurrency.code);
			fundFees.put("components", fundFeesComponents);
			fundFees.put("additionalFee", additionalFee);
			fund.put("fees", fundFees);
			inquiry.put("fund", fund);

			inquiry.put("senderCountry", Json.toJson(helper.senderCountry));
			inquiry.put("senderCurrency", Json.toJson(helper.senderCurrency));
			inquiry.put("beneficiaryCountry",
					Json.toJson(helper.beneficiaryCountry));
			inquiry.put("beneficiaryCurrency",
					Json.toJson(helper.beneficiaryCurrency));

			inquiry.put("channel", Json.toJson(helper.channel));
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setDateFormat(Utility.datetimeFormatShow);
			inquiry.put("forexReference", objectMapper.valueToTree(helper.forexRef));

			// Final result
			result.put("status", 0);
			result.put("message", "Inquiry succeed");

			TransactionInquiry trxInq = new TransactionInquiry();
			trxInq.typeId = TransactionInquiry.Type.CASHIN_INQUIRY;
			trxInq.user = helper.user;
			trxInq.requestTime = new Date();
			trxInq.forexReference = helper.forexRef;
			trxInq.beneficiaryAmount = helper.beneficiaryAmount;
			trxInq.save();

			String inquiryCode = TransactionInquiry.formatAsCode(trxInq);
			inquiry.put("idToken", inquiryCode);

			result.put("inquiry", inquiry);
			Logger.info("result : " + result.toString());
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
			result = ResponseHelper.createResponse(Definition.STATUS_UNSUPPORTED,e.getMessage());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			result = ResponseHelper.createResponse(Definition.STATUS_ILLEGALPARAMS,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result = ResponseHelper.createResponse(Definition.STATUS_UNKNOWN,e.getMessage());
		}
		return Results.ok(result);
	}

	@Override
	public Result CashInRemit(CashInRemitHelper helper) {
		Logger.info("---------------------------------------------");
		Logger.info(":: PROCESSOR CASH TO ACCOUNT CHANNEL REMIT :: ");
		Logger.info("=============================================");
		ObjectNode result = Json.newObject();
		try {
			// Process any validations and filling informations to form
			if (helper.inquiry.forexReference != null) {
				helper.forexReference = helper.inquiry.forexReference;
			} else {
				Utility.processFormForex(helper);
			}
			Utility.processFormFund(helper);
			Utility.processFormFee(helper);

			//START PLUGIN
			ObjectNode resultPlugin = Plugin.corporateHasExceedSendLimit(helper.corporate, helper.senderAmount);
			if(resultPlugin != null) return Results.ok(resultPlugin);
			
			resultPlugin = Plugin.operatorHasExceedSendLimit(helper.agent, helper.senderAmount);
			if(resultPlugin != null) return Results.ok(resultPlugin);
			//END PLUGIN
			
			BigDecimal additionalFee = new BigDecimal(0);
			for (TransactionFee fee : helper.fees) {
				additionalFee = additionalFee.add(fee.additionalFee);
			}
			helper.beneficiaryAmount = helper.beneficiaryAmount.subtract(additionalFee);
			
			// save form to db
			Transaction trx = new Transaction();
			trx.createdTime = new Date();
			trx.corporate = helper.corporate;
			trx.agent = helper.agent;
			trx.channel = helper.channel;
			trx.sender = helper.sender;
			trx.senderCountry = helper.senderCountry;
			trx.senderCurrency = helper.senderCurrency;
			trx.senderAmount = helper.senderAmount;
			trx.beneficiary = helper.beneficiary;
			trx.beneficiaryCountry = helper.beneficiaryCountry;
			trx.beneficiaryCurrency = helper.beneficiaryCurrency;
			trx.beneficiaryAmount = helper.beneficiaryAmount;
			trx.forexReference = helper.forexReference;
			trx.beneficiaryCity = helper.beneficiaryCity;
			trx.cashInTime = Calendar.getInstance();
			trx.status = Transaction.STATUS_PENDING;
			trx.senderNote = helper.senderNote;
			trx.sendTrxId = helper.sendTrxId;
			trx.auth1 = helper.auth1;
			if (helper.cashInTime != null) {
				trx.cashInTime.setTime(helper.cashInTime);
			}
			trx.beneficiaryAccount = helper.beneficiaryAccount;
			
			TransactionInquiry inquiry = TransactionInquiry.findByCode(helper.inquiry.idToken);
			if (helper.beneficiaryAmount.compareTo(inquiry.beneficiaryAmount) != 0) {
				result.put(Definition.FIELD_RESPONSE_STATUS,
						Definition.STATUS_ILLEGALSECURITY);
				result.put(Definition.FIELD_RESPONSE_MESSAGE,
						"Invalid Amount");
				return Results.ok(result);
			}
			
			if ((helper.sendTrxId != null) && (!helper.sendTrxId.isEmpty())) {
				Transaction t = models.Transaction.find.where()
						.eq("corporate", helper.corporate)
						.eq("sendTrxId", helper.sendTrxId).findUnique();
				if (t != null) {
					result.put(Definition.FIELD_RESPONSE_STATUS,
							Definition.STATUS_ILLEGALSECURITY);
					result.put(Definition.FIELD_RESPONSE_MESSAGE,
							"Duplicate sendTrxId Reference");
					return Results.ok(result);
				}
			}

			Result rulesResult = Transaction.checkSendRules(trx,
					helper.agent);
			if (rulesResult != null) {
				Logger.info("rulesResult !null");
				return rulesResult;
			}
			
			// save transaction
			Logger.debug("Attempting to save remit to db.. "+Json.toJson(helper.beneficiaryAccount));
			trx.save();

			// save additional transaction fees to db
			for (TransactionFee fee : helper.fees) {
				fee.transaction = trx;
				fee.save();
			}

			// Bind the inquiry to transaction
			helper.inquiry.transaction = trx;
			helper.inquiry.save();

			// Call background actor to check for this transaction
			SuspiciousActor.getRefInstance().tell(trx);
			
			//Update Credit Limit Operator
			User.updateCreditLastBalance(trx.agent, trx.senderAmount);
			
			// create remit result json object
			ObjectNode remit = Json.newObject();
			remit.put("transactionId", TransactionToken.fromTransaction(trx).toString());
			result.put("status", Definition.STATUS_OK);
			result.put("message", "Remit accepted");
			result.put("remit", remit);
		} catch (UnsupportedOperationException e) {
			result = ResponseHelper.createResponse(Definition.STATUS_UNSUPPORTED,e.getMessage());
		} catch (IllegalArgumentException e) {
			result = ResponseHelper.createResponse(Definition.STATUS_ILLEGALPARAMS,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result = ResponseHelper.createResponse(Definition.STATUS_UNKNOWN,e.getMessage());
		}
		// Response result object
		return Results.ok(result);
	}

	@Override
	public Result CashOutInquiry(CashOutInquiryHelper helper) {
		Logger.info("-------------------------------------------------------");
		Logger.info(":: PROCESSOR CASH TO ACCOUNT CHANNEL CASHOUT INQUIRY :: ");
		Logger.info("=======================================================");
		ObjectNode result = Json.newObject();
		try {
			Result rulesResult = Transaction.checkReceiveRules(helper.transaction, helper.user);
			if(rulesResult != null) {
				return rulesResult;
			}
			
			result.put("status", Definition.STATUS_OK);
			result.put("message", "Inquiry succeed");
			
			TransactionInquiry trxInq = new TransactionInquiry();
			trxInq.typeId = TransactionInquiry.Type.CASHOUT_INQUIRY;
			trxInq.user = helper.user;
			trxInq.requestTime = new Date();
			trxInq.transaction = helper.transaction;
			trxInq.save();
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setDateFormat(Utility.datetimeFormatShow);
			result.put("inquiry", objectMapper.valueToTree(trxInq));
			Logger.info("Manage CashOut Inquiry Result "+result.toString());
		} catch (Exception e) {
			e.printStackTrace();
			result = ResponseHelper.createResponse(Definition.STATUS_UNKNOWN, e.getMessage());
		}
		Logger.info("------------------");
		return Results.ok(result);
	}

	@Override
	public Result CashOutValidate(CashOutValidateHelper helper) {
		Logger.info("--------------------------------------------------------");
		Logger.info(":: PROCESSOR CASH TO ACCOUNT CHANNEL CASHOUT VALIDATE :: ");
		Logger.info("========================================================");
		ObjectNode result = Json.newObject();
		try {
			helper.inquiry.generateValidationId();
			helper.inquiry.save();
			result.put("status", Definition.STATUS_OK);
			result.put("message", "Ok");
			ObjectNode validate = Json.newObject();
			validate.put("validationId", helper.inquiry.validationId);
			result.put("validation", validate);
			Logger.info("Processor CashOut Validate Result "+result.toString());
		} catch (Exception e) {
			e.printStackTrace();
			result = ResponseHelper.createResponse(Definition.STATUS_UNKNOWN, e.getMessage());
		}
		Logger.info("------------------");
		return Results.ok(result);
	}

	@Override
	public Result CashOutCollect(CashOutCollectHelper helper) {
		Logger.info("-------------------------------------------------------");
		Logger.info(":: PROCESSOR CASH TO ACCOUNT CHANNEL CASHOUT COLLECT :: ");
		Logger.info("=======================================================");
		ObjectNode result = Json.newObject();
		Map<String, String> mapChannel = new HashMap<String, String>();
		try{
			String channelCode = helper.user.corporate.configuration.channelCode;
			if (channelCode != null && !channelCode.trim().equals("")) {
				String[] channel = channelCode.split(";");
				for (int i = 0; i < channel.length; i++) {
					mapChannel.put(channel[i], channel[i]);
				}
			}
		} catch (Exception e) { }
		if (mapChannel.containsKey(helper.inquiry.transaction.channel.code)) {
			if (helper.inquiry.transaction.auth1.equals(helper.auth2)) {
				helper.inquiry.generateCollectId();
				helper.inquiry.transaction.beneficiaryAgent = helper.user;
				helper.inquiry.transaction.cashOutTime = Calendar.getInstance();	
				if (helper.cashOutTime != null) {
					helper.inquiry.transaction.cashOutTime.setTime(helper.cashOutTime);
				}
				helper.inquiry.transaction.receiveTrxId = helper.receiveTrxId;
				helper.inquiry.transaction.status = Transaction.STATUS_PAID;
				try{
					helper.inquiry.transaction.save();
					
					helper.inquiry.save();
					
					result.put("status", Definition.STATUS_OK);
					result.put("message", "Ok");
		
					ObjectNode collect = Json.newObject();
					collect.put("referenceId", helper.inquiry.collectId);
		
					result.put("collect", collect);
		
					SuspiciousActor.getRefInstance().tell(helper.inquiry.transaction);
					Logger.info("Manage CashOut Collect Result "+result.toString());
					return Results.ok(result);
				}catch(PersistenceException ex) {
					ex.printStackTrace();
					result = ResponseHelper.createResponse(Definition.STATUS_INVALIDPARAMS, "Paramater already exists");
					return Results.ok(result);
				}
			} else {
				result = ResponseHelper.createResponse(Definition.STATUS_UNAUTHORIZED, "Invalid auth1");
				return Results.unauthorized(result);
			}
		} else {
			result = ResponseHelper.createResponse(Definition.STATUS_UNAUTHORIZED, "Restricted by transaction channel");
			return Results.unauthorized(result);
		}
	}

}
