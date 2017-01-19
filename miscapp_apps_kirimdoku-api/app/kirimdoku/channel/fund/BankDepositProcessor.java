package kirimdoku.channel.fund;

import java.math.RoundingMode;

import kirimdoku.helpers.KirimDokuHttpConnection;
import kirimdoku.util.Utility;
import models.Bank;
import models.CustomerAccount;
import models.Transaction;
import models.TransactionInquiry;
import models.TransactionLog;
import models.User;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.node.ObjectNode;
import controllers.helpers.AdditionalParameter;
import controllers.helpers.BankDepositHelper;
import controllers.helpers.Definition;
import controllers.helpers.EncryptionHelper;
import controllers.helpers.HashWithSHA1;
import controllers.helpers.TransferResponse;
import play.Logger;
import play.libs.Json;

public class BankDepositProcessor extends BaseChannelProcessor {

	@Override
	public TransferResponse FundInquiry(TransactionInquiry inquiry) {
		TransferResponse response = new TransferResponse();
		response.status = 11;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(Feature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
		try {
			ObjectNode post = Json.newObject();
			post.put("channelCode", inquiry.channel.code);
			post.put("currencyCode", inquiry.receivingCurrency.code);
			
			post.put("activity", Definition.DO_INQUIRY);
			post.put("referenceId", inquiry.getIdToken());
			post.put("senderNote", "DO_INQUIRY");
			post.put("beneficiaryAmount", inquiry.beneficiaryAmount.setScale(2, RoundingMode.FLOOR));
			
			CustomerAccount beneficiaryAccount = new CustomerAccount();
			Bank bank = Bank.find.byId(inquiry.accountBankId);
			beneficiaryAccount.bank = bank;
			beneficiaryAccount.city = inquiry.accountBankCity;
			beneficiaryAccount.name = inquiry.accountName;
			beneficiaryAccount.number = inquiry.accountId;
			post.put("beneficiaryAccount", Json.toJson(beneficiaryAccount));
			
			BankDepositHelper bankDepositHelper = new BankDepositHelper();
			String sharedKeyEnc = inquiry.channel.sharedKey != null ? EncryptionHelper.encrypt(inquiry.channel.sharedKey, Utility.encKey) : "";
			post.put("sharedKey", sharedKeyEnc);
			AdditionalParameter additionalParameter = (AdditionalParameter) objectMapper.readValue(inquiry.channel.additionalParam, AdditionalParameter.class);
			if (additionalParameter != null) {
				bankDepositHelper.chainMallId = additionalParameter.chainMallId;
			}

			bankDepositHelper.mallId = inquiry.channel.cid;
			bankDepositHelper.accountNumber = inquiry.channel.accountId;
			bankDepositHelper.accountName = "DOKU";
			bankDepositHelper.inquiryId = inquiry.idToken;
			bankDepositHelper.currency = "IDR";
			bankDepositHelper.amount = inquiry.beneficiaryAmount.setScale(2, RoundingMode.FLOOR)+"";
			bankDepositHelper.channelCode = inquiry.channel.code;
			post.put("bankDepositHelper", Json.toJson(bankDepositHelper));

			
			String words = inquiry.channel.code + inquiry.beneficiaryAmount.setScale(4, RoundingMode.FLOOR) + inquiry.getIdToken() + Utility.sharedKey;
			Logger.debug("Words Formula          : "+words);
			words = HashWithSHA1.SHA1(words);
			Logger.debug("Words Request          : "+words);
			post.put("words", words);
			Logger.debug("Param Request          : "+post.toString());
			String inquiryResponse = Utility.postHttp(post.toString());
			
			if (inquiryResponse != null) {
				response = objectMapper.readValue(inquiryResponse, TransferResponse.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.debug("Exception : "+e.getMessage());
		}
		Logger.info("Cash To Account Processor Fund Inquiry Result " + response.toString());
		return response;
	}

	@Override
	public void FundRemit(TransactionInquiry inquiry) {
		Logger.debug("--------------------------------------------------");
		Logger.debug("::Fund Transfer transaction "+inquiry.transaction.idToken());
		Logger.debug("==================================================");
		BankDepositHelper bankDepositHelper = new BankDepositHelper();
		try {
			Logger.debug("channelCode            : "+inquiry.transaction.channel.code);
			Logger.debug("currencyCode           : "+inquiry.transaction.beneficiaryCurrency.code);
			
			ObjectNode post = Json.newObject();
			post.put("channelCode", inquiry.transaction.channel.code);
			post.put("currencyCode", inquiry.transaction.beneficiaryCurrency.code);
            if((inquiry.transaction.senderNote != null) && (!inquiry.transaction.senderNote.isEmpty())) {
                post.put("senderNote", inquiry.transaction.senderNote);
            } else {
                post.put("senderNote", "cash-to-account transfer");
            }
            Logger.debug("beneficiaryAmount      : "+inquiry.transaction.beneficiaryAmount);
			post.put("beneficiaryAmount", inquiry.transaction.beneficiaryAmount);
			
			post.put("cid", inquiry.transaction.channel.cid);
			post.put("accountId", inquiry.transaction.channel.accountId);
			String sharedKeyEnc = inquiry.transaction.channel.sharedKey != null ? EncryptionHelper.encrypt(inquiry.transaction.channel.sharedKey, Utility.encKey) : "";
			post.put("sharedKey", sharedKeyEnc);
			post.put("activity", Definition.DO_PAYMENT);
			
			bankDepositHelper.mallId = inquiry.transaction.channel.cid;
			bankDepositHelper.accountNumber = inquiry.transaction.channel.accountId;
			bankDepositHelper.accountName = "DOKU";
			
			bankDepositHelper.currency = "IDR";
			bankDepositHelper.amount = inquiry.transaction.beneficiaryAmount.setScale(2)+"";
			bankDepositHelper.channelCode = inquiry.transaction.channel.code;
			
			bankDepositHelper.trxCode = inquiry.validationId;
			bankDepositHelper.inquiryId = inquiry.getIdToken();
			
			post.put("bankDepositHelper", Json.toJson(bankDepositHelper));
			post.put("beneficiaryAccount", Json.toJson(inquiry.transaction.beneficiaryAccount));
			post.put("referenceId", inquiry.transaction.idToken);
			post.put("mobileNo", inquiry.transaction.beneficiary.phoneNumber);
			post.put("lang", inquiry.transaction.beneficiary.country.code);
			String words = inquiry.transaction.channel.code + inquiry.transaction.beneficiaryAmount.setScale(4, RoundingMode.FLOOR) + inquiry.transaction.idToken + Utility.sharedKey;
			//Logger.debug("Words Formula          : "+words);
			words = HashWithSHA1.SHA1(words);
			Logger.debug("Words Request          : "+words);
			post.put("words", words);
					
			Logger.debug("Fund Transfer transaction Post parameters "+post);
            TransactionLog.insert(inquiry.transaction, inquiry.transaction.agent, "TPG_REQUEST", post.toString());

			//HTTP URL CONNECTION
            KirimDokuHttpConnection connection = Utility.httpConnection(post.toString(), Utility.URL_TPG, Utility.TIMEOUT_TPG, "application/json");
			Logger.debug("Http Response Code     : "+connection.responseCode);
            if (connection.responseCode == 200) {
                Logger.debug("Result                 : "+connection.responseMessage);
    			JsonNode resultJson = Json.parse(connection.responseMessage);
    			Logger.debug("--------------------------------------------------");
    			Logger.debug("Fund Transfer transaction Post result (json) "+resultJson);
    			TransactionLog.insert(inquiry.transaction, inquiry.transaction.agent, "TPG_RESPONSE", resultJson.toString());
                
    			Number status = resultJson.path("status").getNumberValue();
    			if ((status != null) && status.intValue() == 0) {
                    Logger.debug("Fund Transfer transaction change status to PAID : "+status);
                    inquiry.transaction.status = Transaction.STATUS_PAID;
					TransferResponse response = (TransferResponse) (new ObjectMapper()).readValue(connection.responseMessage, TransferResponse.class);
					if (response != null) {
						bankDepositHelper.responseCode = response.referenceStatus != null ? response.referenceStatus : "";
						bankDepositHelper.responseMsg = response.referenceMessage;
						inquiry.transaction.remitResponseCode = response.referenceStatus != null ? response.referenceStatus : response.referenceStatus;
						inquiry.transaction.remitResponseMessage = response.referenceMessage != null ? response.referenceMessage : response.statusMessage;
					}
					inquiry.transaction.paymentData = Json.toJson(bankDepositHelper).toString();
					inquiry.transaction.update();
    				User.updateCreditLastBalance(inquiry.transaction.agent, inquiry.transaction.senderAmount);
    			} else {
                    Logger.debug("Fund Transfer transaction change status to UNPAID : "+status);
                    inquiry.transaction.status = Transaction.STATUS_UNPAID;
					TransferResponse response = (TransferResponse) (new ObjectMapper()).readValue(connection.responseMessage, TransferResponse.class);
					if (response != null) {
						bankDepositHelper.responseCode = response.referenceStatus != null ? response.referenceStatus : "";
						bankDepositHelper.responseMsg = response.referenceMessage;
						inquiry.transaction.remitResponseCode = response.referenceStatus != null ? response.referenceStatus : response.referenceStatus;
						inquiry.transaction.remitResponseMessage = response.referenceMessage != null ? response.referenceMessage : response.statusMessage;
					}
					inquiry.transaction.paymentData = Json.toJson(bankDepositHelper).toString();
					inquiry.transaction.update();
    			}
            } else if (connection.responseCode == 404) {
            	inquiry.transaction.remitResponseMessage = "Http Response Code "+connection.responseCode+" "+connection.responseMessage;
            	inquiry.transaction.update();
				TransactionLog.insert(inquiry.transaction, inquiry.transaction.agent, "TPG_RESPONSE", "Http Response Code "+connection.responseCode+" "+connection.responseMessage);
			} else if (connection.responseCode == 408) {
				inquiry.transaction.remitResponseMessage = "Http Response Code "+connection.responseCode+" "+connection.responseMessage;
				inquiry.transaction.update();
				TransactionLog.insert(inquiry.transaction, inquiry.transaction.agent, "TPG_RESPONSE", "Http Response Code "+connection.responseCode+" "+connection.responseMessage);
			} else {
				inquiry.transaction.remitResponseMessage = "Unknown Http Response Code "+connection.responseCode+" "+connection.responseMessage;
				inquiry.transaction.update();
			}
		} catch (Exception e) {
			e.printStackTrace();
			inquiry.transaction.remitResponseMessage = "Unknown Error "+e.getMessage();
			inquiry.transaction.update();
            TransactionLog.insert(inquiry.transaction, inquiry.transaction.agent, "TPG_RESPONSE", e.getMessage());
		}
		
		Logger.debug("--------------------------------------------------");
	}

}
