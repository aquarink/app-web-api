package kirimdoku.channel.batchprocessor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import models.Channel;
import models.Customer;
import models.ForexReference;
import models.Label;
import models.Transaction;
import models.TransactionFee;
import models.TransactionInquiry;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.node.ObjectNode;

import controllers.actors.SuspiciousActor;
import controllers.helpers.AdditionalParameter;
import controllers.helpers.Definition;
import controllers.helpers.ResponseHelper;
import controllers.helpers.TransferResponse;
import controllers.tokens.CustomerToken;
import controllers.tokens.TransactionToken;
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
	public Result CashInInquiry(TransactionInquiry inquiry) {
		// Create result object
		ObjectNode result = Json.newObject();
		try {
			
			ForexReference forexReference = ForexReference.findByCorporateCurrencyOriginDestination(
					inquiry.user.corporate.code, 
					inquiry.sendingCurrency.code, 
					inquiry.receivingCurrency.code);

			if (forexReference == null) {
				result = ResponseHelper.createResponse(Definition.STATUS_UNSUPPORTED, "No forex found for destination country");
				return Results.ok(result);
			}

			Utility.processFormFund(inquiry, BigDecimal.valueOf(forexReference.rate));
			
			Channel channel = Channel.findByCode("04");
			List<TransactionFee> fees = Utility.processFormFee(
					inquiry.user.corporate, 
					inquiry.sendingCountry, 
					inquiry.receivingCountry, 
					channel,
					inquiry.sendingAmount,
					inquiry.sendingCurrency,
					inquiry.receivingCurrency);

			//START PLUGIN
			ObjectNode resultPlugin = Plugin.corporateHasExceedSendLimit(inquiry.user.corporate, inquiry.sendingAmount);
			if(resultPlugin != null) return Results.ok(resultPlugin);
			
			resultPlugin = Plugin.operatorHasExceedSendLimit(inquiry.user, inquiry.sendingAmount);
			if(resultPlugin != null) return Results.ok(resultPlugin);
			//END PLUGIN

			BigDecimal total = new BigDecimal(0);
			BigDecimal additionalFee = new BigDecimal(0);
			for (TransactionFee fee : fees) {
				total = total.add(fee.amount);
				additionalFee = additionalFee.add(fee.additionalFee);
			}
			
			inquiry.beneficiaryAmount = inquiry.beneficiaryAmount.subtract(additionalFee);
			inquiry.beneficiaryAmount = inquiry.beneficiaryAmount.setScale(1, RoundingMode.FLOOR);
			
			/* CFNAME FUND INQUIRY */
			String cfName = inquiry.channel != null ? inquiry.channel.cfnames : "";
			Logger.debug("Channel Code           : "+inquiry.channel.code);
			Logger.debug("Channel Name           : "+inquiry.channel.name);
			Logger.debug("Channel cfName         : "+inquiry.channel.cfnames);
			cfName = cfName.replace("kirimdoku.channel.processor.", "kirimdoku.channel.fund.");
			Class<kirimdoku.channel.fund.BaseChannelProcessor> classProcessor = (Class<kirimdoku.channel.fund.BaseChannelProcessor>) Class.forName(cfName, true, kirimdoku.channel.fund.BankDepositProcessor.class.getClassLoader());
			kirimdoku.channel.fund.BaseChannelProcessor processor = classProcessor.newInstance();
			Logger.debug("Router loaded "+processor.getClass());
			TransferResponse responseInquiry = processor.FundInquiry(inquiry);
			
			if (responseInquiry.referenceStatus != null && responseInquiry.referenceStatus.equals("0000")) {
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				objectMapper.configure(Feature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
				AdditionalParameter additionalParameter = null;
				if (inquiry.channel.additionalParam != null && !inquiry.channel.additionalParam.equals("")) {
					additionalParameter = (AdditionalParameter) objectMapper.readValue(inquiry.channel.additionalParam, AdditionalParameter.class);
				}
				additionalParameter = new AdditionalParameter();
				additionalParameter.accessToken = responseInquiry.accessToken;
				additionalParameter.systrace = responseInquiry.systrace;
				String additionalParamTmp = objectMapper.writeValueAsString(additionalParameter);
				Channel.updateAdditionalParam(inquiry.channel.code, additionalParamTmp);
				
				inquiry.validationId = responseInquiry.trackingId;
				inquiry.dokuWalletName = responseInquiry.receiverName;
				inquiry.status = TransactionInquiry.TransactionInquiryStatus.SUCCESS.getCode();
				inquiry.state = TransactionInquiry.TransactionInquiryState.DONE.getCode();
				inquiry.inquiryResponseCode = responseInquiry.status+"";
				inquiry.inquiryResponseMessage = responseInquiry.statusMessage;
				
				//SENDER
				Customer customerSender = Customer.find.where()
						.eq("firstName", inquiry.senderFirstName)
						.eq("lastName", inquiry.senderLastName)
						.eq("phoneNumber", inquiry.senderPhoneNumber)
						.eq("personalIdType", inquiry.senderPersonalIdType)
						.eq("personalId", inquiry.senderPersonalId)
						.eq("birthDate", inquiry.senderDateOfBirth).setMaxRows(1).findUnique();
				if (customerSender == null) {
					customerSender = new Customer();
					customerSender.firstName = inquiry.senderFirstName;
					customerSender.lastName = inquiry.senderLastName;
					customerSender.phoneNumber = inquiry.senderPhoneNumber;
					customerSender.personalIdType = inquiry.senderPersonalIdType;
					customerSender.personalId = inquiry.senderPersonalId;
					customerSender.birthDate = java.sql.Date.valueOf(Utility.dateFormatShow.format(inquiry.senderDateOfBirth));
					customerSender.country = inquiry.senderCountry;
					customerSender.save();
				}
				inquiry.senderCid = customerSender.getIdToken();
				
				Customer customerBeneficiary = Customer.find.where()
						.eq("firstName", inquiry.beneficiaryFirstName)
						.eq("lastName", inquiry.beneficiaryLastName)
						.eq("phoneNumber", inquiry.beneficiaryPhoneNumber).setMaxRows(1).findUnique();
				if (customerBeneficiary == null) {
					customerBeneficiary = new Customer();
					customerBeneficiary.firstName = inquiry.beneficiaryFirstName;
					customerBeneficiary.lastName = inquiry.beneficiaryLastName;
					customerBeneficiary.phoneNumber = inquiry.beneficiaryPhoneNumber;
					customerBeneficiary.country = inquiry.beneficiaryCountry;
					customerBeneficiary.save();
				}
				inquiry.beneficiaryCid = customerBeneficiary.getIdToken();
				inquiry.update();
				
				result.put("status", 0);
				result.put("message", "Inquiry succeed");
			} else {
				inquiry.status = TransactionInquiry.TransactionInquiryStatus.FAILED.getCode();
				inquiry.state = TransactionInquiry.TransactionInquiryState.DONE.getCode();
				inquiry.inquiryResponseCode = responseInquiry.status+"";
				inquiry.inquiryResponseMessage = responseInquiry.statusMessage;
				inquiry.update();
				result.put("status", "1");
				result.put("message", responseInquiry.referenceMessage);
			}
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
		Logger.info("Cash To Wallet Processor Inquiry Result " + result.toString());
		return Results.ok(result);
	}
	
	@Override
	public Result CashInRemit(TransactionInquiry inquiry) {
		ObjectNode result = Json.newObject();
		try {
			// Process any validations and filling informations to form
			
			//START PLUGIN
			ObjectNode resultPlugin = Plugin.corporateHasExceedSendLimit(inquiry.user.corporate, inquiry.sendingAmount);
			if(resultPlugin != null) return Results.ok(resultPlugin);
			
			resultPlugin = Plugin.operatorHasExceedSendLimit(inquiry.user, inquiry.sendingAmount);
			if(resultPlugin != null) return Results.ok(resultPlugin);
			
			//END PLUGIN
			
			// save sender to db
			Customer sender = Customer.find.byId(CustomerToken.fromString(inquiry.senderCid).id);
			
			// save beneficiary to db
			Customer beneficiary = Customer.find.byId(CustomerToken.fromString(inquiry.beneficiaryCid).id);
			
			// save beneficiary to db
			List<Label> labels = new ArrayList<Label>();
			
			ObjectNode rulesResult = Transaction.checkSendRules(sender.id, beneficiary.id, inquiry.channel.code, inquiry.user, inquiry.sendingCurrency, inquiry.sendingAmount, labels);
			
			// save trx form to db
			Transaction transaction = new Transaction();
			transaction.createdTime = new Date();
			transaction.corporate = inquiry.user.corporate;
			transaction.agent = inquiry.user;
			transaction.channel = inquiry.channel;
			transaction.sender = sender;
			transaction.senderCountry = inquiry.sendingCountry;
			transaction.senderCurrency = inquiry.sendingCurrency;
			transaction.senderAmount = inquiry.sendingAmount;
			transaction.beneficiary = beneficiary;
			transaction.beneficiaryCountry = inquiry.receivingCountry;
			transaction.beneficiaryCurrency = inquiry.receivingCurrency;
			transaction.beneficiaryAmount = inquiry.beneficiaryAmount;
			transaction.forexReference = inquiry.forexReference;
			//transaction.beneficiaryCity = ;
			transaction.cashInTime = Calendar.getInstance();
			//transaction.senderNote = inquiry.senderNote;
			//transaction.sendTrxId = helper.sendTrxId;
			//transaction.auth1 = helper.auth1;
			transaction.status = models.Transaction.STATUS_UNPAID;
			transaction.labels = labels;
			transaction.remitResponseCode = Definition.STATUS_UNKNOWN+"";
			transaction.remitResponseMessage = "FAILED";
			transaction.batch = inquiry.batch;
			
			// save transaction
			Logger.debug("Attempting to save remit cash to account to db..");
			transaction.save();
			inquiry.transaction = transaction;
			inquiry.update();
			
			if (rulesResult != null) {
				transaction.remitResponseCode = rulesResult.get(Definition.FIELD_RESPONSE_STATUS).getValueAsText();
				transaction.remitResponseMessage = rulesResult.get(Definition.FIELD_RESPONSE_MESSAGE).getTextValue();
				transaction.update();
				return Results.ok(rulesResult);
			}
			
			List<TransactionFee> transactionFees = Utility.processFormFee(inquiry);
			
			// save additional transaction fees to db
			for (TransactionFee fee : transactionFees) {
				fee.transaction = transaction;
				fee.save();
			}

			/* CFNAME FUND REMIT */
			String cfName = inquiry.channel != null ? inquiry.channel.cfnames : "";
			Logger.debug("Channel Code           : "+inquiry.channel.code);
			Logger.debug("Channel Name           : "+inquiry.channel.name);
			Logger.debug("Channel cfName         : "+inquiry.channel.cfnames);
			cfName = cfName.replace("kirimdoku.channel.processor.", "kirimdoku.channel.fund.");
			Class<kirimdoku.channel.fund.BaseChannelProcessor> classProcessor = (Class<kirimdoku.channel.fund.BaseChannelProcessor>) Class.forName(cfName, true, kirimdoku.channel.fund.BankDepositProcessor.class.getClassLoader());
			kirimdoku.channel.fund.BaseChannelProcessor processor = classProcessor.newInstance();
			Logger.debug("Router loaded "+processor.getClass());
			processor.FundRemit(inquiry);
			SuspiciousActor.getRefInstance().tell(transaction);
			
			// create remit result json object
			ObjectNode remit = Json.newObject();
			remit.put("transactionId", TransactionToken.fromTransaction(transaction).toString());
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

}
