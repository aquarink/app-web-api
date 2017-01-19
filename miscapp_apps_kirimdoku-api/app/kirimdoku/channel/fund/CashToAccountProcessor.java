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
import org.codehaus.jackson.node.ObjectNode;
import controllers.helpers.Definition;
import controllers.helpers.EncryptionHelper;
import controllers.helpers.HashWithSHA1;
import controllers.helpers.TransferResponse;
import play.Logger;
import play.libs.Json;

public class CashToAccountProcessor extends BaseChannelProcessor {

	@Override
	public TransferResponse FundInquiry(TransactionInquiry inquiry) {
		TransferResponse response = new TransferResponse();
		response.status = 11;
		try {
			ObjectNode post = Json.newObject();
			post.put("channelCode", inquiry.channel.code);
			post.put("currencyCode", inquiry.receivingCurrency.code);
			
			post.put("activity", Definition.DO_INQUIRY);
			post.put("referenceId", inquiry.getIdToken());
			post.put("senderNote", "DO_INQUIRY");
			post.put("beneficiaryAmount", inquiry.beneficiaryAmount.setScale(6, RoundingMode.FLOOR));
			
			CustomerAccount beneficiaryAccount = new CustomerAccount();
			Bank bank = Bank.find.byId(inquiry.accountBankId);
			beneficiaryAccount.bank = bank;
			beneficiaryAccount.city = inquiry.accountBankCity;
			beneficiaryAccount.name = "DOKU NSIA";
			beneficiaryAccount.number = inquiry.accountId;
			post.put("beneficiaryAccount", Json.toJson(beneficiaryAccount));
			
			String words = inquiry.channel.code + inquiry.beneficiaryAmount.setScale(4, RoundingMode.FLOOR) + inquiry.getIdToken() + Utility.sharedKey;
			Logger.debug("Words Formula          : "+words);
			words = HashWithSHA1.SHA1(words);
			Logger.debug("Words Request          : "+words);
			post.put("words", words);
			Logger.debug("Param Request          : "+post.toString());
			String inquiryResponse = Utility.postHttp(post.toString());
			
			if (inquiryResponse != null) {
				ObjectMapper objectMapper = new ObjectMapper();
				response = objectMapper.readValue(inquiryResponse, TransferResponse.class);
				Logger.debug("Response : "+inquiryResponse);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}

	@Override
	public void FundRemit(TransactionInquiry inquiry) {
		Logger.debug("--------------------------------------------------");
		Logger.debug("::Fund Transfer transaction "+inquiry.transaction.idToken());
		Logger.debug("==================================================");
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
    				try {
    					inquiry.transaction.remitResponseCode = !resultJson.path("referenceStatus").isMissingNode() ? resultJson.path("referenceStatus").getValueAsText() : inquiry.transaction.remitResponseCode;
    					inquiry.transaction.remitResponseMessage = !resultJson.path("statusMessage").isMissingNode() ? resultJson.path("statusMessage").getValueAsText() : inquiry.transaction.remitResponseMessage;
					} catch (Exception e) {
						e.printStackTrace();
					}
    				inquiry.transaction.update();
    				User.updateCreditLastBalance(inquiry.transaction.agent, inquiry.transaction.senderAmount);
    			} else {
                    Logger.debug("Fund Transfer transaction change status to UNPAID : "+status);
                    inquiry.transaction.status = Transaction.STATUS_UNPAID;
    				try {
    					inquiry.transaction.remitResponseCode = !resultJson.path("referenceStatus").isMissingNode() ? resultJson.path("referenceStatus").getValueAsText() : inquiry.transaction.remitResponseCode;
    					inquiry.transaction.remitResponseMessage = !resultJson.path("statusMessage").isMissingNode() ? resultJson.path("statusMessage").getValueAsText() : inquiry.transaction.remitResponseMessage;
					} catch (Exception e) {
						e.printStackTrace();
					}
    				inquiry.transaction.update();
    			}
            } else if (connection.responseCode == 404) {
				TransactionLog.insert(inquiry.transaction, inquiry.transaction.agent, "TPG_RESPONSE", "Http Response Code "+connection.responseCode+" "+connection.responseMessage);
			} else if (connection.responseCode == 408) {
				TransactionLog.insert(inquiry.transaction, inquiry.transaction.agent, "TPG_RESPONSE", "Http Response Code "+connection.responseCode+" "+connection.responseMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
            TransactionLog.insert(inquiry.transaction, inquiry.transaction.agent, "TPG_RESPONSE", e.getMessage());
		}
		
		Logger.debug("--------------------------------------------------");
	}

}
