package controllers.actors;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.RoundingMode;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import kirimdoku.helpers.BillPaymentDataHelper;
import kirimdoku.helpers.BillPaymentTPGHelper;
import kirimdoku.util.Utility;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.node.ObjectNode;

import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.QueryIterator;
import com.avaje.ebean.TxType;
import com.avaje.ebean.annotation.Transactional;

import controllers.helpers.AdditionalParameter;
import controllers.helpers.BankDepositHelper;
import controllers.helpers.Definition;
import controllers.helpers.EncryptionHelper;
import controllers.helpers.HashWithSHA1;
import controllers.helpers.TransferResponse;
import controllers.tokens.TransactionInquiryToken;
import models.Transaction;
import models.TransactionInquiry;
import models.TransactionLog;
import models.User;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import play.Logger;
//import play.db.ebean.Transactional;
import play.libs.Akka;
import play.libs.Json;

public class TPGActor extends UntypedActor {

	private static ActorRef refInstance;
	private final int TIMEOUT_TPG = 75000;
	protected final String sharedKey = "9187a294cc";
	
	public static ActorRef getRefInstance() {
		if (refInstance == null) {
			refInstance = Akka.system().actorOf(new Props(TPGActor.class));
		}
		return refInstance;
	}
	
	@Override
	@Transactional(type = TxType.REQUIRED)
	public void onReceive(Object message) throws Exception {
		Logger.debug("TPGActor onReceive "+message);
		
		if(message instanceof models.Transaction) {
			Transaction transaction = (Transaction) message;
			if((transaction.status == Transaction.STATUS_PENDING) || (transaction.status == Transaction.STATUS_UNPAID)) {
				this.fundTransfer(transaction);
			}
		} else if("check".equals(message)) {
			this.runCheckTransactions();
		}
	}

	@Transactional(type = TxType.REQUIRED)
	private void runCheckTransactions() {
		Logger.debug("Running transactions checking...");
		ExpressionList<Transaction> where = models.Transaction.find.where();
		where.gt("channel.code", "06");
		where.setMaxRows(100);
		where.setOrderBy("id");
		
		for(QueryIterator<Transaction> trxIterator = where.findIterate(); trxIterator.hasNext();) {
			models.Transaction transaction = trxIterator.next();
			if((transaction.status == Transaction.STATUS_PENDING) || (transaction.status == Transaction.STATUS_UNPAID)) {
				this.fundTransfer(transaction);
			}
		}
	}
	
	//@Transactional
	private void fundTransfer(Transaction transaction) {
		Logger.debug("--------------------------------------------------");
		Logger.debug("::Fund Transfer transaction "+transaction.idToken());
		Logger.debug("==================================================");
		BankDepositHelper bankDepositHelper = new BankDepositHelper();
		try {
			Logger.debug("channelCode            : "+transaction.channel.code);
			Logger.debug("currencyCode           : "+transaction.beneficiaryCurrency.code);
			
			ObjectNode post = Json.newObject();
			post.put("channelCode", transaction.channel.code);
			post.put("currencyCode", transaction.beneficiaryCurrency.code);
            if((transaction.senderNote != null) && (!transaction.senderNote.isEmpty())) {
                post.put("senderNote", transaction.senderNote);
            } else {
                post.put("senderNote", "cash-to-account transfer");
            }
            Logger.debug("beneficiaryAmount      : "+transaction.beneficiaryAmount);
			post.put("beneficiaryAmount", transaction.beneficiaryAmount);
			
			post.put("cid", transaction.channel.cid);
			post.put("accountId", transaction.channel.accountId);
			String sharedKeyEnc = transaction.channel.sharedKey != null ? EncryptionHelper.encrypt(transaction.channel.sharedKey, Utility.encKey) : "";
			post.put("sharedKey", sharedKeyEnc);
			post.put("activity", Definition.DO_PAYMENT);
			
			AdditionalParameter additionalParameter = null;
			if (transaction.channel.code.equals("04")) {
				if (transaction.channel.additionalParam != null && !transaction.channel.additionalParam.equals("")) {
					ObjectMapper objectMapper = new ObjectMapper();
					objectMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
					objectMapper.configure(Feature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
					additionalParameter = (AdditionalParameter) objectMapper.readValue(transaction.channel.additionalParam, AdditionalParameter.class);
				}
				String accessToken = "default";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
				String systrace = simpleDateFormat.format(new Date());
				if (additionalParameter != null) {
					accessToken = additionalParameter.accessToken;
					systrace = additionalParameter.systrace;
				}
				post.put("accessToken", accessToken);
				post.put("systrace", systrace);
				TransactionInquiry inquiry = TransactionInquiry.findTransactionInquiryByTransactionIdUser(transaction.id, transaction.agent);
				if (inquiry != null)
				post.put("trackingId", inquiry.validationId);
			} else if (transaction.channel.code.equals("10")) {
				BillPaymentTPGHelper billPaymentTPGHelper = new BillPaymentTPGHelper();
				billPaymentTPGHelper.mallId = transaction.channel.accountId;
				TransactionInquiry inquiry = TransactionInquiry.findTransactionInquiryByTransactionIdUser(transaction.id, transaction.agent);
				if (inquiry != null) {
					billPaymentTPGHelper.accountNumber = inquiry.accountId;
					billPaymentTPGHelper.billerId = inquiry.accountName;
					billPaymentTPGHelper.inquiryId = inquiry.collectId;
					billPaymentTPGHelper.systrace = inquiry.validationId;
					billPaymentTPGHelper.amount = inquiry.beneficiaryAmount.setScale(1, RoundingMode.FLOOR)+"";
					billPaymentTPGHelper.billId = inquiry.accountType;
					billPaymentTPGHelper.channelCode = transaction.channel.cid;
					post.put("billPayment", Json.toJson(billPaymentTPGHelper));
				}
			} else if (transaction.channel.code.equals("07")) {
				bankDepositHelper.mallId = transaction.channel.accountId;
				TransactionInquiry inquiry = TransactionInquiry.findTransactionInquiryByTransactionIdUser(transaction.id, transaction.agent);
				if (inquiry != null) {
					bankDepositHelper.mallId = transaction.channel.cid;
					bankDepositHelper.accountNumber = transaction.channel.accountId;
					bankDepositHelper.accountName = transaction.beneficiaryAccount.name;
					TransactionInquiryToken transactionInquiryToken = TransactionInquiryToken.fromTransactionInquiry(inquiry);
					bankDepositHelper.inquiryId = transactionInquiryToken.toString();
					bankDepositHelper.trxCode = inquiry.validationId;
					bankDepositHelper.currency = "IDR";
					bankDepositHelper.amount = transaction.beneficiaryAmount.setScale(2, RoundingMode.FLOOR)+"";
					bankDepositHelper.channelCode = transaction.channel.code;
					post.put("bankDepositHelper", Json.toJson(bankDepositHelper));
				}
			}
			
			post.put("beneficiaryAccount", Json.toJson(transaction.beneficiaryAccount));
			post.put("referenceId", transaction.idToken);
			post.put("mobileNo", transaction.beneficiary.phoneNumber);
			post.put("lang", transaction.beneficiary.country.code);
			String words = transaction.channel.code + transaction.beneficiaryAmount.setScale(4, RoundingMode.FLOOR) + transaction.idToken + sharedKey;
			//Logger.debug("Words Formula          : "+words);
			words = HashWithSHA1.SHA1(words);
			Logger.debug("Words Request          : "+words);
			post.put("words", words);
					
			Logger.debug("Fund Transfer transaction Post parameters "+post);
            TransactionLog.insert(transaction, transaction.agent, "TPG_REQUEST", post.toString());

			//HTTP URL CONNECTION
            Logger.debug("--------------------------------------------------");
            Logger.debug("URL                    : "+Utility.URL_TPG);
            Logger.debug("Connection Timeout     : "+TIMEOUT_TPG);
            Logger.debug("==================================================");
			URL url = new URL(Utility.URL_TPG);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(TIMEOUT_TPG);
			connection.setReadTimeout(TIMEOUT_TPG);
			connection.addRequestProperty("Content-Type", "application/json");
			
			OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(post.toString());
            wr.flush();
			String hasil = "";
			Logger.debug("Http Response Code     : "+connection.getResponseCode());
            if (connection.getResponseCode() == 200) {

                BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    hasil = hasil + line;
                }
                
                Logger.debug("Result                 : "+hasil);
    			JsonNode resultJson = Json.parse(hasil);
    			Logger.debug("--------------------------------------------------");
    			Logger.debug("Fund Transfer transaction Post result (json) "+resultJson);
    			connection.disconnect();
    			
    			TransactionLog.insert(transaction, transaction.agent, "TPG_RESPONSE", resultJson.toString());
                
    			Number status = resultJson.path("status").getNumberValue();
    			if ((status != null) && status.intValue() == 0) {
                    Logger.debug("Fund Transfer transaction change status to PAID : "+status);
    				transaction.status = Transaction.STATUS_PAID;
    				if (transaction.channel.code.equals("10")) {
    					TransferResponse response = (TransferResponse) (new ObjectMapper()).readValue(hasil, TransferResponse.class);
    					if (response.referenceStatus.equals("0000")) {
    						Logger.debug("Payment Data >> "+transaction.paymentData);
    						BillPaymentDataHelper billPaymentDataHelper = (BillPaymentDataHelper) (new ObjectMapper()).readValue(transaction.paymentData,BillPaymentDataHelper.class);
    						billPaymentDataHelper.hostReferenceNumber = response.trackingId;
    						transaction.paymentData = Json.toJson(billPaymentDataHelper).toString();
    					}
    				} else if  (transaction.channel.code.equals("07")) {
    					TransferResponse response = (TransferResponse) (new ObjectMapper()).readValue(hasil, TransferResponse.class);
    					if (response != null) {
    						bankDepositHelper.responseCode = response.referenceStatus;
    						bankDepositHelper.responseMsg = response.referenceMessage;
    					}
						transaction.paymentData = Json.toJson(bankDepositHelper).toString();
    				}
    				transaction.save();
    				User.updateCreditLastBalance(transaction.agent, transaction.senderAmount);
    			} else {
                    Logger.debug("Fund Transfer transaction change status to UNPAID : "+status);
    				transaction.status = Transaction.STATUS_UNPAID;
    				transaction.save();
    			}
            } else if (connection.getResponseCode() == 404) {
				TransactionLog.insert(transaction, transaction.agent, "TPG_RESPONSE", "Http Response Code "+connection.getResponseCode()+" "+connection.getResponseMessage());
			} else if (connection.getResponseCode() == 408) {
				TransactionLog.insert(transaction, transaction.agent, "TPG_RESPONSE", "Http Response Code "+connection.getResponseCode()+" "+connection.getResponseMessage());
			}
		} catch (ConnectException e) {
			e.printStackTrace();
			TransactionLog.insert(transaction, transaction.agent, "TPG_RESPONSE", "Failed - Connection refused");
			Logger.debug("Failed - Connection refused");
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			TransactionLog.insert(transaction, transaction.agent, "TPG_RESPONSE", "Failed - Read timed out");
			Logger.debug("Failed - Read timed out");
		} catch (Exception e) {
			e.printStackTrace();
            TransactionLog.insert(transaction, transaction.agent, "TPG_RESPONSE", e.getMessage());
		}
		
		Logger.debug("--------------------------------------------------");
	}
	
}
