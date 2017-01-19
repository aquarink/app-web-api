package kirimdoku.channel.processor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import models.Channel;
import models.CustomerAccount;
import models.Transaction;
import models.TransactionFee;
import models.TransactionInquiry;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

import controllers.actors.SuspiciousActor;
import controllers.actors.TPGActor;
import controllers.helpers.AdditionalParameter;
import controllers.helpers.Definition;
import controllers.helpers.EncryptionHelper;
import controllers.helpers.HashWithSHA1;
import controllers.helpers.ResponseHelper;
import controllers.helpers.TransferResponse;
import controllers.tokens.TransactionToken;
import kirimdoku.helpers.CashInInquiryHelper;
import kirimdoku.helpers.CashInRemitHelper;
import kirimdoku.helpers.CashOutCollectHelper;
import kirimdoku.helpers.CashOutInquiryHelper;
import kirimdoku.helpers.CashOutValidateHelper;
import kirimdoku.util.Plugin;
import kirimdoku.util.Utility;
import play.Logger;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.Results;

public class CashToWalletProcessor extends BaseChannelProcessor {
	
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
			trxInq.sendingAmount = helper.senderAmount;
			trxInq.forexReference = helper.forexRef;
			trxInq.channel = helper.channel;
			trxInq.save();
			String inquiryCode = TransactionInquiry.formatAsCode(trxInq);
			inquiry.put("idToken", inquiryCode);

			helper.idToken = inquiryCode;
			TransferResponse responseInquiry = fundInquiry(helper);
			inquiry.put("beneficiaryWalletId", helper.beneficiaryWalletId);
			inquiry.put("beneficiaryWalletName", helper.beneficiaryWalletName);
			if (responseInquiry.referenceStatus == null || !responseInquiry.referenceStatus.equals("0000")) {
				result.put("status", "1");
				result.put("message", responseInquiry.referenceMessage);
			} else {
				TransactionInquiry transactionInquiry = TransactionInquiry.find.byId(trxInq.id);
				transactionInquiry.accountType = helper.channel.code;
				transactionInquiry.dokuWalletId = helper.beneficiaryWalletId;
				transactionInquiry.dokuWalletName = helper.beneficiaryWalletName;
				transactionInquiry.validationId = responseInquiry.trackingId;
				transactionInquiry.save();
			}
			
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

	private TransferResponse fundInquiry(CashInInquiryHelper form) {
		TransferResponse response = new TransferResponse();
		response.status = 11;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(Feature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
		try {
			ObjectNode post = Json.newObject();
			post.put("channelCode", form.channel.code);
			post.put("currencyCode", form.beneficiaryCurrency.code);
			post.put("beneficiaryAmount", form.beneficiaryAmount);
			
			CustomerAccount beneficiaryAccount = new CustomerAccount();
			beneficiaryAccount.number = form.beneficiaryWalletId;
			post.put("beneficiaryAccount",(new ObjectMapper()).valueToTree(beneficiaryAccount));
			post.put("cid", form.channel.cid);
			post.put("accountId", form.channel.accountId);
			String sharedKeyEnc = form.channel.sharedKey != null ? EncryptionHelper.encrypt(form.channel.sharedKey, Utility.encKey) : "";
			post.put("sharedKey", sharedKeyEnc);
			AdditionalParameter additionalParameter = null;
			Channel channel = Channel.findByCode(form.channel.code);
			if (channel != null && channel.additionalParam != null && !channel.additionalParam.equals("")) {
				additionalParameter = (AdditionalParameter) objectMapper.readValue(channel.additionalParam, AdditionalParameter.class);
			}
			String accessToken = "default";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmsszzz");
			String systrace = channel.code+simpleDateFormat.format(new Date());
			if (additionalParameter != null && additionalParameter.accessToken != null && additionalParameter.systrace != null) {
				accessToken = additionalParameter.accessToken;
				systrace = additionalParameter.systrace;
			}
			post.put("accessToken", accessToken);
			post.put("systrace", systrace);
			post.put("activity", form.activity);
			post.put("referenceId", form.idToken);
			post.put("senderNote", "DO_INQUIRY");
			
			String words = form.channel.code + form.beneficiaryAmount.setScale(4, RoundingMode.FLOOR) + form.idToken + Utility.sharedKey;
			Logger.debug("Words Formula          : "+words);
			words = HashWithSHA1.SHA1(words);
			Logger.debug("Words Request          : "+words);
			post.put("words", words);
			Logger.debug("Param Request          : "+post.toString());
			String inquiryResponse = Utility.postHttp(post.toString());
			
			if (inquiryResponse != null) {
				response = objectMapper.readValue(inquiryResponse, TransferResponse.class);
				if (response.referenceStatus != null && response.referenceStatus.equals("0000")) {
					additionalParameter = new AdditionalParameter();
					additionalParameter.accessToken = response.accessToken;
					additionalParameter.systrace = response.systrace;
					String additionalParamTmp = objectMapper.writeValueAsString(additionalParameter);
					Channel.updateAdditionalParam(form.channel.code, additionalParamTmp);
					form.beneficiaryWalletName = response.receiverName;
					form.trackingId = response.trackingId;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	@Override
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
			helper.beneficiaryAccount = null;
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
			trx.beneficiaryWalletId = inquiry.accountId;
			trx.beneficiaryWalletName = helper.beneficiaryWalletName;
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
			TPGActor.getRefInstance().tell(trx);
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
