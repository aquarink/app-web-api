package kirimdoku.channel.processor;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import models.Transaction;
import models.TransactionFee;
import models.TransactionInquiry;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

import com.avaje.ebean.TxType;
import com.avaje.ebean.annotation.Transactional;


import controllers.actors.SuspiciousActor;
//import controllers.actors.SuspiciousActor;
//import controllers.actors.TPGActor;
import controllers.helpers.Definition;
import controllers.helpers.ResponseHelper;
import controllers.helpers.TransferResponse;
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

public class CashToAccountProcessor extends BaseChannelProcessor {

	@Override
	public Result Ping() {
		ObjectNode result = Json.newObject();
		result.put("status", "0");
		result.put("message", "PING OK");
		return play.mvc.Results.ok(result);
	}

	@Override
	public Result CashInInquiry(CashInInquiryHelper helper) {
		// Create result object
		ObjectNode result = Json.newObject();
		try {
			// Do some logic processing from outside this method scope
			if (helper.sendType == null || helper.sendType.trim().equals("")) {
				result = ResponseHelper.createResponse(Definition.STATUS_INVALIDPARAMS, "No sendType found");
				return Results.ok(result);
			}
			if (helper.beneficiaryAccount == null) {
				result = ResponseHelper.createResponse(Definition.STATUS_INVALIDPARAMS, "No beneficiaryAccount found");
				return Results.ok(result);
			}
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
			trxInq.id = TransactionInquiry.getSeq();
			trxInq.typeId = TransactionInquiry.Type.CASHIN_INQUIRY;
			trxInq.user = helper.user;
			trxInq.requestTime = new Date();
			trxInq.forexReference = helper.forexRef;
			trxInq.beneficiaryAmount = helper.beneficiaryAmount;
			trxInq.save();

			String inquiryCode = TransactionInquiry.formatAsCode(trxInq);
			inquiry.put("idToken", inquiryCode);

			if (helper.beneficiaryAccount != null && !helper.beneficiaryAccount.bank.code.equals("BNIAIDJA")) {
				Logger.debug("INQUIRY ID : "+inquiryCode);
				helper.idToken = inquiryCode;
				TransferResponse responseInquiry = Utility.fundInquiryAccountTransfer(helper,additionalFee);
				Logger.debug("RESPONSE : "+Json.toJson(responseInquiry));
				if (responseInquiry.referenceStatus.equals("015")) {
					TransactionInquiry transactionInquiry = trxInq;
					transactionInquiry.accountType = helper.channel.code;
					transactionInquiry.accountId = helper.beneficiaryAccount.number;
					if (responseInquiry.referenceMessage.startsWith("Beneficary name is invalid - ")) {
						String accountName = responseInquiry.referenceMessage.substring(29);
						helper.beneficiaryAccount.name = accountName;
					}
					transactionInquiry.accountName = helper.beneficiaryAccount.name;
					inquiry.put("beneficiaryAccount", Json.toJson(helper.beneficiaryAccount));
					transactionInquiry.update();
				} else {
					result.put("status", "1");
					result.put("message", "Invalid Inquiry "+helper.channel.name);
					return Results.ok(result);
				}
			}
			
			result.put("inquiry", inquiry);
			Logger.info("Cash To Account Processor Inquiry Result " + result.toString());
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
	@Transactional
	public Result CashInRemit(CashInRemitHelper helper) {
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
			trx.status = models.Transaction.STATUS_PENDING;
			
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
			Logger.debug("Attempting to save remit cash to account to db..");
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
			//TPGActor.getRefInstance().tell(trx);
			//SuspiciousActor.getRefInstance().tell(trx);
			Utility.fundTransferAccountTransfer(trx);
			SuspiciousActor.getRefInstance().tell(trx);
			
			// create remit result json object
			ObjectNode remit = Json.newObject();
			remit.put("transactionId", TransactionToken
					.fromTransaction(trx).toString());
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
		ObjectNode result = Json.newObject();
		result = ResponseHelper.createResponse(Definition.STATUS_UNSUPPORTED, "Unsupported channel");
		return Results.ok(result);
	}

	@Override
	public Result CashOutValidate(CashOutValidateHelper helper) {
		ObjectNode result = Json.newObject();
		result = ResponseHelper.createResponse(Definition.STATUS_UNSUPPORTED, "Unsupported channel");
		return Results.ok(result);
	}

	@Override
	public Result CashOutCollect(CashOutCollectHelper helper) {
		ObjectNode result = Json.newObject();
		result = ResponseHelper.createResponse(Definition.STATUS_UNSUPPORTED, "Unsupported channel");
		return Results.ok(result);
	}

}
