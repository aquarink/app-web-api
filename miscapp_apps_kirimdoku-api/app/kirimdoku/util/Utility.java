package kirimdoku.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

import kirimdoku.helpers.BillPaymentDataHelper;
import kirimdoku.helpers.BillPaymentTPGHelper;
import kirimdoku.helpers.CashInInquiryHelper;
import kirimdoku.helpers.CashInRemitHelper;
import kirimdoku.helpers.InquiryBillPaymentHelper;
import kirimdoku.helpers.KirimDokuHttpConnection;
import kirimdoku.helpers.ResponseBillPaymentHelper;
import models.Channel;
import models.Corporate;
import models.Country;
import models.Currency;
import models.CustomerAccount;
import models.Fee;
import models.ForexReference;
import models.Transaction;
import models.TransactionFee;
import models.TransactionInquiry;
import models.TransactionLog;
import models.User;
import models.Voucher;
import play.Configuration;
import play.Logger;
import play.libs.Json;
import controllers.CashIn.InquiryForm;
import controllers.helpers.AdditionalParameter;
import controllers.helpers.BankDepositHelper;
import controllers.helpers.Definition;
import controllers.helpers.EncryptionHelper;
import controllers.helpers.ForexHelper;
import controllers.helpers.HashWithSHA1;
import controllers.helpers.TransferResponse;
import controllers.tokens.TransactionInquiryToken;

public class Utility {
	
	private static final Configuration CONFIG_API = play.Play.application().configuration().getConfig("tpg");
	public static SimpleDateFormat datetimeFormatShow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat dateFormatShow = new SimpleDateFormat("yyyy-MM-dd");
	public static final String URL_TPG = CONFIG_API.getString("base_url") + "/transfer";
	public static final int TIMEOUT_TPG = 75000;
	public static final String sharedKey = "9187a294cc";
	public static final String encKey = "1234567890abcdef";
	
	public static void processFormInquiry(CashInInquiryHelper helper) {
		// Need any additional process? since its already handled on the form
		// onValidate()
	}

	public static void processFormForex(CashInInquiryHelper helper) {
		helper.forexRef = getLastForexReference(helper.corporate,
				helper.senderCurrency, helper.beneficiaryCurrency);
	}

	public static void processFormFund(CashInInquiryHelper form) {
		try {
			Logger.debug(">> PROCESS FORM FUND INQUIRY");
			Logger.debug("SENDER AMOUNT          : "+form.senderAmount);
			Logger.debug("BENEFY AMOUNT          : "+form.beneficiaryAmount);
			Logger.debug("SEND TYPE              : "+form.sendType);
			if (form.sendType.equals("senderAmount")) {
				form.beneficiaryAmount = BigDecimal.ZERO.setScale(6);
				form.senderAmount = form.senderAmount.setScale(6,
						BigDecimal.ROUND_FLOOR);
				if (form.forexRef != null) {
					form.beneficiaryAmount = form.senderAmount.multiply(BigDecimal
							.valueOf(form.forexRef.rate));
					form.beneficiaryAmount = form.beneficiaryAmount.setScale(6,
							BigDecimal.ROUND_FLOOR);
				}
			} else if (form.sendType.equals("beneficiaryAmount")) {
				form.senderAmount = BigDecimal.ZERO.setScale(6);
				form.beneficiaryAmount = form.beneficiaryAmount.setScale(6,
						BigDecimal.ROUND_FLOOR);
				if (form.forexRef != null) {
					form.senderAmount = form.beneficiaryAmount.divide(
							BigDecimal.valueOf(form.forexRef.rate), 6);
					form.senderAmount = form.senderAmount.setScale(6,
							BigDecimal.ROUND_FLOOR);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void processFormFund(TransactionInquiry inquiry, BigDecimal rate) {
		try {
			Logger.debug(">> PROCESS FORM FUND INQUIRY");
			Logger.debug("SENDER AMOUNT          : "+inquiry.sendingAmount);
			Logger.debug("BENEFY AMOUNT          : "+inquiry.beneficiaryAmount);
			Logger.debug("SEND TYPE              : "+inquiry.sendType);
			if (inquiry.sendType == 'S') {
				inquiry.beneficiaryAmount = BigDecimal.ZERO.setScale(6);
				inquiry.sendingAmount = inquiry.sendingAmount.setScale(6,BigDecimal.ROUND_FLOOR);
				inquiry.beneficiaryAmount = inquiry.sendingAmount.multiply(rate);
				inquiry.beneficiaryAmount = inquiry.beneficiaryAmount.setScale(6, BigDecimal.ROUND_FLOOR);
			} else if (inquiry.sendType == 'B') {
				inquiry.sendingAmount = BigDecimal.ZERO.setScale(6);
				inquiry.beneficiaryAmount = inquiry.beneficiaryAmount.setScale(6, BigDecimal.ROUND_FLOOR);
				inquiry.sendingAmount = inquiry.beneficiaryAmount.divide(rate, 6);
				inquiry.sendingAmount = inquiry.sendingAmount.setScale(6,BigDecimal.ROUND_FLOOR);
			}
			Logger.debug("SENDER AMOUNT          : "+inquiry.sendingAmount);
			Logger.debug("BENEFY AMOUNT          : "+inquiry.beneficiaryAmount);
			Logger.debug("SEND TYPE              : "+inquiry.sendType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static BigDecimal formFund(BigDecimal senderAmount, ForexReference forexReference) {
		BigDecimal beneficiaryAmount = BigDecimal.ZERO;
		try {
			if (senderAmount != null) {
				senderAmount = senderAmount.setScale(6,
						BigDecimal.ROUND_FLOOR);
				if (forexReference != null) {
					beneficiaryAmount = senderAmount.multiply(BigDecimal
							.valueOf(forexReference.rate));
					beneficiaryAmount = beneficiaryAmount.setScale(6,
							BigDecimal.ROUND_FLOOR);
				}
			} else if (beneficiaryAmount != null) {
				beneficiaryAmount = beneficiaryAmount.setScale(6,
						BigDecimal.ROUND_FLOOR);
				if (forexReference != null) {
					senderAmount = beneficiaryAmount.divide(
							BigDecimal.valueOf(forexReference.rate), 6);
					senderAmount = senderAmount.setScale(6,
							BigDecimal.ROUND_FLOOR);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return beneficiaryAmount;
	}
	
	public static void processFormFee(CashInInquiryHelper form) {
		List<Fee> fees = getFeeTransaction(form.corporate, form.senderCountry, form.beneficiaryCountry, form.channel, form.senderAmount, form.senderCurrency, form.beneficiaryCurrency);
		form.fees = new ArrayList<TransactionFee>();
		BigDecimal totalFee = new BigDecimal(0);
		for (Fee fee : fees) {
			totalFee = totalFee.add(fee.amount);
			TransactionFee transactionFee = new TransactionFee(null, fee);
			form.fees.add(transactionFee);
		}
	}
	
	public static List<TransactionFee> processFormFee(Corporate corporate, Country senderCountry, Country beneficiaryCountry, Channel channel, BigDecimal senderAmount, Currency senderCurrency, Currency beneficiaryCurrency) {
		try {
			List<Fee> fees = getFeeTransaction(corporate, senderCountry, beneficiaryCountry, channel, senderAmount, senderCurrency, beneficiaryCurrency);
			List<TransactionFee> result = new ArrayList<TransactionFee>();
			BigDecimal totalFee = new BigDecimal(0);
			for (Fee fee : fees) {
				totalFee = totalFee.add(fee.amount);
				TransactionFee transactionFee = new TransactionFee(null, fee);
				result.add(transactionFee);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<TransactionFee> formFee(Corporate corporate, Country senderCountry, Country beneficiaryCountry, Channel channel, BigDecimal senderAmount, Currency senderCurrency, Currency beneficiaryCurrency) {
		List<TransactionFee> transactionFees = new ArrayList<TransactionFee>();
		try {
			List<Fee> fees = getFeeTransaction(corporate, senderCountry, beneficiaryCountry, channel, senderAmount, senderCurrency, beneficiaryCurrency);
			transactionFees = new ArrayList<TransactionFee>();
			BigDecimal totalFee = new BigDecimal(0);
			for (Fee fee : fees) {
				totalFee = totalFee.add(fee.amount);
				TransactionFee transactionFee = new TransactionFee(null, fee);
				transactionFees.add(transactionFee);
			}
			return transactionFees;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Fee> getFeeTransaction(Corporate corporate, Country senderCountry, Country beneficiaryCountry, Channel channel, BigDecimal senderAmount, Currency senderCurrency, Currency beneficiaryCurrency) {
		List<Fee> fees = Fee.findFees(corporate, senderCountry, beneficiaryCountry, channel, senderAmount, senderCurrency, beneficiaryCurrency);
		return fees;
	}
	
	public static void processFormFee(CashInRemitHelper helper) {
		List<Fee> fees = getFeeTransaction(helper, helper.senderCurrency);

		helper.fees = new ArrayList<TransactionFee>();

		BigDecimal totalFee = new BigDecimal(0);
		for (Fee fee : fees) {
			totalFee = totalFee.add(fee.amount);

			TransactionFee transactionFee = new TransactionFee(null, fee);
			helper.fees.add(transactionFee);
		}
	}
	
	public static List<TransactionFee> processFormFee(TransactionInquiry helper) {
		try {
			List<Fee> fees = getFeeTransaction(
					helper.user.corporate, 
					helper.sendingCountry, 
					helper.receivingCountry, 
					helper.channel,
					helper.sendingAmount,
					helper.sendingCurrency,
					helper.receivingCurrency
					);

			List<TransactionFee> transactionFees = new ArrayList<TransactionFee>();

			BigDecimal totalFee = new BigDecimal(0);
			for (Fee fee : fees) {
				totalFee = totalFee.add(fee.amount);

				TransactionFee transactionFee = new TransactionFee(null, fee);
				transactionFees.add(transactionFee);
			}
			
			return transactionFees;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<Fee> getFeeTransaction(CashInRemitHelper helper, Currency currency) {
		List<Fee> fees = Fee.findFees(helper.corporate, helper.senderCountry,
				helper.beneficiaryCountry, helper.channel, helper.senderAmount, helper.senderCurrency, helper.beneficiaryCurrency);
		return fees;
	}
	
//	public static List<Fee> getFeeTransaction(Corporate corporate, Country senderCountry, Country beneficiaryCountry, Channel channel, BigDecimal senderAmount, Currency senderCurrency, Currency beneficiaryCurrency) {
//		List<Fee> fees = Fee.findFees(corporate, senderCountry,
//				beneficiaryCountry, channel, senderAmount, senderCurrency, beneficiaryCurrency);
//		return fees;
//	}
	
	public static void processFormForex(CashInRemitHelper helper) {
		Logger.debug("Attemp to retrieve form forex : " + helper.corporate.code
				+ " - " + helper.senderCurrency.code);
		helper.forexReference = ForexHelper.getLastForexReference(helper.corporate,
				helper.senderCurrency, helper.beneficiaryCurrency);
	}
	
	public static void processFormFund(CashInRemitHelper helper) {
		try {
			Logger.debug(">> PROCESS FORM FUND REMIT");
			Logger.debug("SENDER AMOUNT          : "+helper.senderAmount);
			Logger.debug("BENEFY AMOUNT          : "+helper.beneficiaryAmount);
			Logger.debug("SEND TYPE              : "+helper.sendType);
			if (helper.sendType.equals("senderAmount")) {
				helper.beneficiaryAmount = BigDecimal.ZERO.setScale(6);
				helper.senderAmount = helper.senderAmount.setScale(6,
						BigDecimal.ROUND_FLOOR);
				if (helper.forexReference != null) {
					helper.beneficiaryAmount = helper.senderAmount.multiply(BigDecimal
							.valueOf(helper.forexReference.rate));
					helper.beneficiaryAmount = helper.beneficiaryAmount.setScale(6,
							BigDecimal.ROUND_FLOOR);
				}
			} else if (helper.sendType.equals("beneficiaryAmount")) {
				helper.senderAmount = BigDecimal.ZERO.setScale(6);
				helper.beneficiaryAmount = helper.beneficiaryAmount.setScale(6,
						BigDecimal.ROUND_FLOOR);
				if (helper.forexReference != null) {
					helper.senderAmount = helper.beneficiaryAmount.divide(
							BigDecimal.valueOf(helper.forexReference.rate), 6);
					helper.senderAmount = helper.senderAmount.setScale(6,
							BigDecimal.ROUND_FLOOR);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.debug("PROCESS FORM FUND FAILED : "+e.getMessage());
		}
	}
	
	public static ForexReference getLastForexReference(Corporate corporate, Currency origin, Currency destination) {
		ForexReference ref = ForexReference.findByCurrency(corporate, origin, destination);
		
		return ref;
	}
	
	public static Voucher checkVoucher(CashInRemitHelper helper) {
		Voucher voucher = null;
		try {
			if (helper.voucherCode != null && !helper.voucherCode.trim().equals("")) {
				Logger.debug(":: CHECKING VOUCHER CODE");
				String voucherCode = HashWithSHA1.SHA1(helper.voucherCode.trim());
				Logger.debug("Voucher Code ["+voucherCode+"]");
				voucher = Voucher.find.where().eq("code", voucherCode).findUnique();
				if (voucher != null) {
					Logger.debug("Voucher Code found ["+helper.voucherCode+"]");
					if (voucher.voucher_status) {
						Logger.debug("Voucher Code active");
						return voucher;
					} else {
						Logger.debug("Voucher Code not active");
						return null;
					}
				} else {
					Logger.debug("Voucher Code not found");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return voucher;
	}
	
	public static String postHttp(String parameter) {
		try {
			//HTTP URL CONNECTION
			KirimDokuHttpConnection connection = httpConnection(parameter, URL_TPG, TIMEOUT_TPG, "application/json");
			Logger.debug("Http Response Code     : "+connection.responseCode);
            if (connection.responseCode == 200) {
                Logger.debug("Result                 : "+connection.responseMessage);
    			JsonNode resultJson = Json.parse(connection.responseMessage);
    			Logger.debug("--------------------------------------------------");
    			Logger.debug("Fund Transfer transaction Post result (json) "+resultJson);
    			return connection.responseMessage;
            } else {
            	Logger.debug("Http Response Code "+connection.responseCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static TransferResponse fundInquiryCashToWallet(InquiryForm form) {
		TransferResponse response = new TransferResponse();
		response.status = 11;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(Feature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
		try {
			ObjectNode post = Json.newObject();
			post.put("channelCode", form.channel.code);
			post.put("currencyCode", form.beneficiaryCurrency.code);
			
			BigDecimal amount = BigDecimal.ZERO;
			if (form.sendType.equals("beneficiaryAmount")) {
				amount = amount.add(form.beneficiaryAmount);
			}else if (form.sendType.equals("senderAmount")) {
				amount = amount.add(form.beneficiaryAmount);
			}
			
			post.put("beneficiaryAmount", amount.setScale(6, RoundingMode.FLOOR));
			
			CustomerAccount beneficiaryAccount = new CustomerAccount();
			beneficiaryAccount.number = form.beneficiaryWalletId;
			post.put("beneficiaryAccount",(new ObjectMapper()).valueToTree(beneficiaryAccount));
			post.put("cid", form.channel.cid);
			post.put("accountId", form.channel.accountId);
			String sharedKeyEnc = form.channel.sharedKey != null ? EncryptionHelper.encrypt(form.channel.sharedKey, Utility.encKey) : "";
			post.put("sharedKey", sharedKeyEnc);
			AdditionalParameter additionalParameter = null;
			if (form.channel.additionalParam != null && !form.channel.additionalParam.equals("")) {
				additionalParameter = (AdditionalParameter) objectMapper.readValue(form.channel.additionalParam, AdditionalParameter.class);
			}
			String accessToken = "default";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			String systrace = simpleDateFormat.format(new Date());
			if (additionalParameter != null) {
				accessToken = additionalParameter.accessToken != null ? additionalParameter.accessToken : "default";
				systrace = additionalParameter.systrace != null ? additionalParameter.systrace : systrace;
			}
			post.put("accessToken", accessToken);
			post.put("systrace", systrace);
			post.put("activity", Definition.DO_INQUIRY);
			post.put("referenceId", form.idToken);
			post.put("senderNote", "DO_INQUIRY");
			
			String words = form.channel.code + amount.setScale(4, RoundingMode.FLOOR) + form.idToken + Utility.sharedKey;
			Logger.debug("Words Formula          : "+words);
			words = HashWithSHA1.SHA1(words);
			Logger.debug("Words Request          : "+words);
			post.put("words", words);
			Logger.debug("Param Request          : "+post.toString());
			String inquiryResponse = Utility.postHttp(post.toString());
			if (inquiryResponse != null) {
				response = objectMapper.readValue(inquiryResponse, TransferResponse.class);
				Logger.debug("Response : "+inquiryResponse);
				if (response.referenceStatus.equals("0000")) {
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
	
	public static TransferResponse fundInquiryBillPayment(InquiryForm form) {
		TransferResponse response = new TransferResponse();
		response.status = 11;
		try {
			ObjectNode post = Json.newObject();
			post.put("channelCode", form.channel.code);
			post.put("currencyCode", form.beneficiaryCurrency.code);
			
			BigDecimal amount = BigDecimal.ZERO;
			if (form.sendType != null) {
				if (form.sendType.equals("beneficiaryAmount")) {
					amount = amount.add(form.beneficiaryAmount);
				}else if (form.sendType.equals("senderAmount")) {
					amount = amount.add(form.beneficiaryAmount);
				}
			}
			
			post.put("beneficiaryAmount", amount.setScale(6, RoundingMode.FLOOR));
			
			BillPaymentTPGHelper billPaymentTPGHelper = new BillPaymentTPGHelper();
			billPaymentTPGHelper.mallId = form.channel.accountId;
			billPaymentTPGHelper.billerId = form.billPayment.selectOperator;
			billPaymentTPGHelper.accountNumber = form.billPayment.accountNumber;
			int systrace = Integer.parseInt(form.trackingId);
			billPaymentTPGHelper.systrace = systrace+"";
			billPaymentTPGHelper.channelCode = form.channel.cid;
			post.put("billPayment", Json.toJson(billPaymentTPGHelper));
			String sharedKeyEnc = form.channel.sharedKey != null ? EncryptionHelper.encrypt(form.channel.sharedKey, Utility.encKey) : "";
			post.put("sharedKey", sharedKeyEnc);
			post.put("referenceId", form.idToken);
			post.put("activity", Definition.DO_INQUIRY);
			post.put("senderNote", "DO_INQUIRY");
			
			String words = form.channel.code + amount.setScale(4, RoundingMode.FLOOR) + form.idToken + Utility.sharedKey;
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
				if (response.referenceStatus.equals("0000")) {
					//
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	public static TransferResponse fundInquiryBillPayment(CashInInquiryHelper form) {
		TransferResponse response = new TransferResponse();
		response.status = 11;
		try {
			ObjectNode post = Json.newObject();
			post.put("channelCode", form.channel.code);
			post.put("currencyCode", form.beneficiaryCurrency.code);
			post.put("beneficiaryAmount", BigDecimal.ZERO.setScale(6, RoundingMode.FLOOR));
			
			BillPaymentTPGHelper billPaymentTPGHelper = new BillPaymentTPGHelper();
			billPaymentTPGHelper.mallId = form.channel.accountId;
			billPaymentTPGHelper.billerId = form.billPayment.selectOperator;
			billPaymentTPGHelper.accountNumber = form.billPayment.accountNumber;
			int systrace = Integer.parseInt(form.trackingId);
			billPaymentTPGHelper.systrace = systrace+"";
			billPaymentTPGHelper.channelCode = form.channel.cid;
			post.put("billPayment", Json.toJson(billPaymentTPGHelper));
			String sharedKeyEnc = form.channel.sharedKey != null ? EncryptionHelper.encrypt(form.channel.sharedKey, Utility.encKey) : "";
			post.put("sharedKey", sharedKeyEnc);
			post.put("referenceId", form.idToken);
			post.put("activity", Definition.DO_INQUIRY);
			post.put("senderNote", "DO_INQUIRY");
			
			String words = form.channel.code + BigDecimal.ZERO.setScale(4, RoundingMode.FLOOR) + form.idToken + Utility.sharedKey;
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
				if (response.referenceStatus.equals("0000")) {
					//
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	public static TransferResponse inquiryBillPayment(InquiryBillPaymentHelper helper) {
		TransferResponse response = new TransferResponse();
		response.status = 11;
		try {
			ObjectNode post = Json.newObject();
			Channel channel = Channel.findByCode("10");
			post.put("channelCode", channel.code);
			post.put("currencyCode", "IDR");
			post.put("beneficiaryAmount", BigDecimal.ZERO.setScale(6, RoundingMode.FLOOR));
			
			BillPaymentTPGHelper billPaymentTPGHelper = new BillPaymentTPGHelper();
			billPaymentTPGHelper.mallId = channel.accountId;
			billPaymentTPGHelper.billerId = helper.selectOperator;
			billPaymentTPGHelper.accountNumber = helper.accountNumber;
			int systrace = (int) Math.floor(Math.random()*999999);
			helper.systrace = systrace+"";
			billPaymentTPGHelper.systrace = helper.systrace;
			billPaymentTPGHelper.channelCode = channel.cid;
			post.put("billPayment", Json.toJson(billPaymentTPGHelper));
			String sharedKeyEnc = channel.sharedKey != null ? EncryptionHelper.encrypt(channel.sharedKey, Utility.encKey) : "";
			post.put("sharedKey", sharedKeyEnc);
			post.put("referenceId", systrace);
			post.put("activity", Definition.DO_INQUIRY);
			post.put("senderNote", "DO_INQUIRY");
			
			String words = channel.code + BigDecimal.ZERO.setScale(4, RoundingMode.FLOOR) + systrace + Utility.sharedKey;
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
				if (response.referenceStatus.equals("0000")) {
					//
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	public static void fundTransferBillPayment(Transaction transaction) {
		Logger.debug("--------------------------------------------------");
		Logger.debug("::Fund Transfer transaction "+transaction.idToken());
		Logger.debug("==================================================");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(Feature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
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
			
			BillPaymentTPGHelper billPaymentTPGHelper = new BillPaymentTPGHelper();
			billPaymentTPGHelper.mallId = transaction.channel.accountId;
			TransactionInquiry inquiry = TransactionInquiry.findTransactionInquiryByTransactionIdUser(transaction.id, transaction.agent);
			if (inquiry != null) {
				billPaymentTPGHelper.accountNumber = inquiry.accountId;
				billPaymentTPGHelper.billerId = inquiry.accountName;
				billPaymentTPGHelper.inquiryId = inquiry.collectId;
				billPaymentTPGHelper.systrace = inquiry.validationId;
				billPaymentTPGHelper.amount = transaction.beneficiaryAmount.setScale(1)+"";
				billPaymentTPGHelper.billId = inquiry.accountType;
				billPaymentTPGHelper.channelCode = transaction.channel.cid;
				post.put("billPayment", Json.toJson(billPaymentTPGHelper));
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
            KirimDokuHttpConnection connection = httpConnection(post.toString(), URL_TPG, TIMEOUT_TPG, "application/json");
			Logger.debug("Http Response Code     : "+connection.responseCode);
            if (connection.responseCode == 200) {
                Logger.debug("Result                 : "+connection.responseMessage);
    			JsonNode resultJson = Json.parse(connection.responseMessage);
    			Logger.debug("--------------------------------------------------");
    			Logger.debug("Fund Transfer transaction Post result (json) "+resultJson);
    			TransactionLog.insert(transaction, transaction.agent, "TPG_RESPONSE", resultJson.toString());
                
    			Number status = resultJson.path("status").getNumberValue();
    			TransferResponse response = (TransferResponse) (new ObjectMapper()).readValue(connection.responseMessage, TransferResponse.class);
    			if (response != null) {
	    			if ((status != null) && status.intValue() == 0) {
						if (response.referenceStatus.equals("0000")) {
							Logger.debug("Fund Transfer transaction change status to PAID : "+status);
		    				transaction.status = Transaction.STATUS_PAID;
		    				BillPaymentDataHelper billPaymentDataHelper = null;
		    				try {
								billPaymentDataHelper = (BillPaymentDataHelper) (new ObjectMapper()).readValue(transaction.paymentData,BillPaymentDataHelper.class);
		    				} catch (Exception e) {
		    					Logger.debug("Failed parsing billPaymentData >> "+e.getMessage());
		    				}
		    				if (billPaymentDataHelper == null) {
		    					billPaymentDataHelper = new BillPaymentDataHelper();
		    				}
							billPaymentDataHelper.hostReferenceNumber = response.trackingId;
				            try {
	    						ResponseBillPaymentHelper responseBillPaymentHelper = objectMapper.readValue(response.referenceMessage, ResponseBillPaymentHelper.class);
	    						Logger.debug("bill_INFO >> "+responseBillPaymentHelper.bill_INFO.toString());
	    						Logger.debug("date_OF_SETTLEMENT >> "+responseBillPaymentHelper.date_OF_SETTLEMENT);
	    						Logger.debug("host_REFERENCE_NUMBER >> "+responseBillPaymentHelper.host_REFERENCE_NUMBER.toString());
	    						Logger.debug("responsecode >> "+responseBillPaymentHelper.responsecode.toString());
	    						Logger.debug("responsemsg >> "+responseBillPaymentHelper.responsemsg.toString());
	    						Logger.debug("additional_DATA >> "+responseBillPaymentHelper.additional_DATA.toString());
	    						Logger.debug("body >> "+responseBillPaymentHelper.body.toString());
	    						Logger.debug("footer >> "+responseBillPaymentHelper.footer.toString());
	    						Logger.debug("header >> "+responseBillPaymentHelper.header.toString());
	    			            
	    			            billPaymentDataHelper.bill_INFO = responseBillPaymentHelper.bill_INFO.toString();
	    			            billPaymentDataHelper.date_OF_SETTLEMENT = responseBillPaymentHelper.date_OF_SETTLEMENT;
	    			            billPaymentDataHelper.host_REFERENCE_NUMBER = responseBillPaymentHelper.host_REFERENCE_NUMBER;
	    			            billPaymentDataHelper.additional_DATA = responseBillPaymentHelper.additional_DATA;
	    			            billPaymentDataHelper.body = responseBillPaymentHelper.body;
	    			            billPaymentDataHelper.footer = responseBillPaymentHelper.footer;
	    			            billPaymentDataHelper.header = responseBillPaymentHelper.header;
	    			            billPaymentDataHelper.responsecode = responseBillPaymentHelper.responsecode;
	    			            billPaymentDataHelper.responsemsg = responseBillPaymentHelper.responsemsg;
				            } catch (Exception e) {
				            	Logger.debug("Failed parsing responseBillPayment >> "+e.getMessage());
				            }
				            if (billPaymentDataHelper.selectOperator.equals("9950102"))
				            try {
				            	for (String body : billPaymentDataHelper.body) {
									if (body.startsWith("STROOM/TOKEN")) {
										String token = body.split("==")[1];
										billPaymentDataHelper.token = token;
										break;
									}
								}
							} catch (Exception e) {
								Logger.debug("Invalid get token body >> "+e.getMessage());
							}
				            
				            transaction.paymentData = Json.toJson(billPaymentDataHelper).toString();
				            Logger.debug("PAYMENT DATA >> "+transaction.paymentData);
						}
						transaction.remitResponseCode = response.referenceStatus != null ? response.referenceStatus : response.referenceStatus;
    					transaction.remitResponseMessage = response.statusMessage != null ? response.statusMessage : "SUCCESS";
	    				transaction.update();
	    				User.updateCreditLastBalance(transaction.agent, transaction.senderAmount);
	    			} else {
	                    Logger.debug("Fund Transfer transaction change status to UNPAID : "+status);
	    				transaction.status = Transaction.STATUS_UNPAID;
	    				
	    				//*****
	    				BillPaymentDataHelper billPaymentDataHelper = null;
	    				try {
							billPaymentDataHelper = (BillPaymentDataHelper) (new ObjectMapper()).readValue(transaction.paymentData,BillPaymentDataHelper.class);
	    				} catch (Exception e) {
	    					Logger.debug("Failed parsing billPaymentData >> "+e.getMessage());
	    				}
	    				if (billPaymentDataHelper == null) {
	    					billPaymentDataHelper = new BillPaymentDataHelper();
	    				}
			            billPaymentDataHelper.responsecode = response.referenceStatus;
			            billPaymentDataHelper.responsemsg = response.referenceMessage;
			            transaction.remitResponseCode = response.referenceStatus != null ? response.referenceStatus : response.referenceStatus;
			            transaction.remitResponseMessage = response.statusMessage != null ? response.statusMessage : "SUCCESS";
			            transaction.paymentData = Json.toJson(billPaymentDataHelper).toString();
			            Logger.debug("PAYMENT DATA >> "+transaction.paymentData);
	    				//******
	    				
	    				transaction.update();
	    			}
    			} else {
                    Logger.debug("Fund Transfer transaction change status to UNPAID : "+status);
    				transaction.status = Transaction.STATUS_UNPAID;
    				transaction.update();
    			}
            } else if (connection.responseCode == 404) {
				TransactionLog.insert(transaction, transaction.agent, "TPG_RESPONSE", "Http Response Code "+connection.responseCode+" "+connection.responseMessage);
			} else if (connection.responseCode == 408) {
				TransactionLog.insert(transaction, transaction.agent, "TPG_RESPONSE", "Http Response Code "+connection.responseCode+" "+connection.responseMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
            TransactionLog.insert(transaction, transaction.agent, "TPG_RESPONSE", e.getMessage());
		}
		Logger.debug("--------------------------------------------------");
	}
	
	public static TransferResponse fundInquiryAccountTransfer(InquiryForm form) {
		TransferResponse response = new TransferResponse();
		response.status = 11;
		try {
			ObjectNode post = Json.newObject();
			post.put("channelCode", form.channel.code);
			post.put("currencyCode", form.beneficiaryCurrency.code);
			
			BigDecimal amount = BigDecimal.ZERO;
			if (form.sendType.equals("beneficiaryAmount")) {
				amount = amount.add(form.beneficiaryAmount);
			}else if (form.sendType.equals("senderAmount")) {
				amount = amount.add(form.beneficiaryAmount);
			}
			
			post.put("activity", Definition.DO_INQUIRY);
			post.put("referenceId", form.idToken);
			post.put("senderNote", "DO_INQUIRY");
			post.put("beneficiaryAmount", amount.setScale(6, RoundingMode.FLOOR));
			form.beneficiaryAccount.name = "DOKU NSIA";
			post.put("beneficiaryAccount", Json.toJson(form.beneficiaryAccount));
			
			String words = form.channel.code + amount.setScale(4, RoundingMode.FLOOR) + form.idToken + Utility.sharedKey;
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
	
	public static TransferResponse fundInquiryBankDeposit(InquiryForm form) {
		TransferResponse response = new TransferResponse();
		response.status = 11;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(Feature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
		try {
			ObjectNode post = Json.newObject();
			post.put("channelCode", form.channel.code);
			post.put("currencyCode", form.beneficiaryCurrency.code);
			
			BigDecimal amount = BigDecimal.ZERO;
			if (form.sendType.equals("beneficiaryAmount")) {
				amount = amount.add(form.beneficiaryAmount);
			}else if (form.sendType.equals("senderAmount")) {
				amount = amount.add(form.beneficiaryAmount);
			}
			
			post.put("activity", Definition.DO_INQUIRY);
			post.put("referenceId", form.idToken);
			post.put("senderNote", "DO_INQUIRY");
			post.put("beneficiaryAmount", amount.setScale(2, RoundingMode.FLOOR));
			
			BankDepositHelper bankDepositHelper = new BankDepositHelper();
			String sharedKeyEnc = form.channel.sharedKey != null ? EncryptionHelper.encrypt(form.channel.sharedKey, Utility.encKey) : "";
			post.put("sharedKey", sharedKeyEnc);
			AdditionalParameter additionalParameter = (AdditionalParameter) objectMapper.readValue(form.channel.additionalParam, AdditionalParameter.class);
			if (additionalParameter != null) {
				bankDepositHelper.chainMallId = additionalParameter.chainMallId;
			}
			
			TransactionInquiry inquiry = TransactionInquiry.findByCode(form.idToken);
			if (inquiry != null) {
				bankDepositHelper.mallId = form.channel.cid;
				bankDepositHelper.accountNumber = form.channel.accountId;
				bankDepositHelper.accountName = "DOKU";
				bankDepositHelper.inquiryId = inquiry.idToken;
				bankDepositHelper.currency = "IDR";
				bankDepositHelper.amount = form.beneficiaryAmount.setScale(2, RoundingMode.FLOOR)+"";
				bankDepositHelper.channelCode = form.channel.code;
				post.put("bankDepositHelper", Json.toJson(bankDepositHelper));
			}
			post.put("beneficiaryAccount", Json.toJson(form.beneficiaryAccount));
			
			String words = form.channel.code + amount.setScale(4, RoundingMode.FLOOR) + form.idToken + Utility.sharedKey;
			Logger.debug("Words Formula          : "+words);
			words = HashWithSHA1.SHA1(words);
			Logger.debug("Words Request          : "+words);
			post.put("words", words);
			Logger.debug("Param Request          : "+post.toString());
			String inquiryResponse = Utility.postHttp(post.toString());
			
			if (inquiryResponse != null) {
				response = objectMapper.readValue(inquiryResponse, TransferResponse.class);
				Logger.debug("Response : "+inquiryResponse);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	public static TransferResponse fundInquiryAccountTransfer(CashInInquiryHelper form, BigDecimal additionalFee) {
		TransferResponse response = new TransferResponse();
		response.status = 11;
		try {
			ObjectNode post = Json.newObject();
			post.put("channelCode", form.channel.code);
			post.put("currencyCode", form.beneficiaryCurrency.code);
			
			BigDecimal amount = BigDecimal.ZERO;
			if (form.sendType.equals("beneficiaryAmount")) {
				amount = amount.add(form.beneficiaryAmount);
			}else if (form.sendType.equals("senderAmount")) {
				amount = amount.add(form.beneficiaryAmount);
			}
			
			post.put("activity", Definition.DO_INQUIRY);
			post.put("referenceId", form.idToken);
			post.put("senderNote", "DO_INQUIRY");
			post.put("beneficiaryAmount", amount.setScale(6, RoundingMode.FLOOR));
			form.beneficiaryAccount.name = "DOKU NSIA";
			post.put("beneficiaryAccount", Json.toJson(form.beneficiaryAccount));
			
			String words = form.channel.code + amount.setScale(4, RoundingMode.FLOOR) + form.idToken + Utility.sharedKey;
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
	
	public static TransferResponse fundInquiryBankDeposit(CashInInquiryHelper form, BigDecimal additionalFee) {
		TransferResponse response = new TransferResponse();
		response.status = 11;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(Feature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
		try {
			ObjectNode post = Json.newObject();
			post.put("channelCode", form.channel.code);
			post.put("currencyCode", form.beneficiaryCurrency.code);
			
			BigDecimal amount = BigDecimal.ZERO;
			if (form.sendType.equals("beneficiaryAmount")) {
				amount = amount.add(form.beneficiaryAmount);
			}else if (form.sendType.equals("senderAmount")) {
				amount = amount.add(form.beneficiaryAmount);
			}
			
			post.put("activity", Definition.DO_INQUIRY);
			post.put("referenceId", form.idToken);
			post.put("senderNote", "DO_INQUIRY");
			post.put("beneficiaryAmount", amount.setScale(2, RoundingMode.FLOOR));
			post.put("beneficiaryAccount", Json.toJson(form.beneficiaryAccount));
			
			BankDepositHelper bankDepositHelper = new BankDepositHelper();
			String sharedKeyEnc = form.channel.sharedKey != null ? EncryptionHelper.encrypt(form.channel.sharedKey, Utility.encKey) : "";
			post.put("sharedKey", sharedKeyEnc);
			AdditionalParameter additionalParameter = (AdditionalParameter) objectMapper.readValue(form.channel.additionalParam, AdditionalParameter.class);
			if (additionalParameter != null) {
				bankDepositHelper.chainMallId = additionalParameter.chainMallId;
			}
			TransactionInquiry inquiry = TransactionInquiry.findByCode(form.idToken);
			if (inquiry != null) {
				bankDepositHelper.mallId = form.channel.cid;
				bankDepositHelper.accountNumber = form.channel.accountId;
				bankDepositHelper.accountName = "DOKU";
				bankDepositHelper.inquiryId = inquiry.idToken;
				bankDepositHelper.currency = "IDR";
				bankDepositHelper.amount = form.beneficiaryAmount.setScale(2, RoundingMode.FLOOR)+"";
				bankDepositHelper.channelCode = form.channel.code;
				post.put("bankDepositHelper", Json.toJson(bankDepositHelper));
			}
			
			String words = form.channel.code + amount.setScale(4, RoundingMode.FLOOR) + form.idToken + Utility.sharedKey;
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
		}
		
		return response;
	}
	
	public static void fundTransferBankDeposit(Transaction transaction) {
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
			post.put("beneficiaryAmount", transaction.beneficiaryAmount.setScale(2, RoundingMode.FLOOR));
			post.put("cid", transaction.channel.cid);
			post.put("accountId", transaction.channel.accountId);
			String sharedKeyEnc = transaction.channel.sharedKey != null ? EncryptionHelper.encrypt(transaction.channel.sharedKey, Utility.encKey) : "";
			post.put("sharedKey", sharedKeyEnc);
			post.put("activity", Definition.DO_PAYMENT);
			
			bankDepositHelper.mallId = transaction.channel.cid;
			bankDepositHelper.accountNumber = transaction.channel.accountId;
			bankDepositHelper.accountName = "DOKU";
			
			bankDepositHelper.currency = "IDR";
			bankDepositHelper.amount = transaction.beneficiaryAmount.setScale(2, RoundingMode.FLOOR)+"";
			bankDepositHelper.channelCode = transaction.channel.code;
			
			TransactionInquiry inquiry = TransactionInquiry.findTransactionInquiryByTransactionIdUser(transaction.id, transaction.agent);
			if (inquiry != null) {
				bankDepositHelper.trxCode = inquiry.validationId;
				TransactionInquiryToken transactionInquiryToken = TransactionInquiryToken.fromTransactionInquiry(inquiry);
				bankDepositHelper.inquiryId = transactionInquiryToken.toString();
			}
			post.put("bankDepositHelper", Json.toJson(bankDepositHelper));
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
            KirimDokuHttpConnection connection = httpConnection(post.toString(), URL_TPG, TIMEOUT_TPG, "application/json");
			Logger.debug("Http Response Code     : "+connection.responseCode);
            if (connection.responseCode == 200) {
                Logger.debug("Result                 : "+connection.responseMessage);
    			JsonNode resultJson = Json.parse(connection.responseMessage);
    			Logger.debug("--------------------------------------------------");
    			Logger.debug("Fund Transfer transaction Post result (json) "+resultJson);
    			TransactionLog.insert(transaction, transaction.agent, "TPG_RESPONSE", resultJson.toString());
                
    			Number status = resultJson.path("status").getNumberValue();
    			if ((status != null) && status.intValue() == 0) {
                    Logger.debug("Fund Transfer transaction change status to PAID : "+status);
    				transaction.status = Transaction.STATUS_PAID;
					TransferResponse response = (TransferResponse) (new ObjectMapper()).readValue(connection.responseMessage, TransferResponse.class);
					if (response != null) {
						bankDepositHelper.responseCode = response.referenceStatus != null ? response.referenceStatus : "";
						bankDepositHelper.responseMsg = response.referenceMessage;
						transaction.remitResponseCode = response.referenceStatus != null ? response.referenceStatus : response.referenceStatus;
    					transaction.remitResponseMessage = response.referenceMessage != null ? response.referenceMessage : response.statusMessage;
					}
					transaction.paymentData = Json.toJson(bankDepositHelper).toString();
    				transaction.save();
    				User.updateCreditLastBalance(transaction.agent, transaction.senderAmount);
    			} else {
                    Logger.debug("Fund Transfer transaction change status to UNPAID : "+status);
    				transaction.status = Transaction.STATUS_UNPAID;
					TransferResponse response = (TransferResponse) (new ObjectMapper()).readValue(connection.responseMessage, TransferResponse.class);
					if (response != null) {
						bankDepositHelper.responseCode = response.referenceStatus != null ? response.referenceStatus : "";
						bankDepositHelper.responseMsg = response.referenceMessage;
						transaction.remitResponseCode = response.referenceStatus != null ? response.referenceStatus : response.referenceStatus;
    					transaction.remitResponseMessage = response.referenceMessage != null ? response.referenceMessage : response.statusMessage;
					}
					transaction.paymentData = Json.toJson(bankDepositHelper).toString();
    				transaction.save();
    			}
            } else if (connection.responseCode == 404) {
				TransactionLog.insert(transaction, transaction.agent, "TPG_RESPONSE", "Http Response Code "+connection.responseCode+" "+connection.responseMessage);
			} else if (connection.responseCode == 408) {
				TransactionLog.insert(transaction, transaction.agent, "TPG_RESPONSE", "Http Response Code "+connection.responseCode+" "+connection.responseMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
            TransactionLog.insert(transaction, transaction.agent, "TPG_RESPONSE", e.getMessage());
		}
		Logger.debug("--------------------------------------------------");
	}
	
	public static void fundTransferAccountTransfer(Transaction transaction) {
		Logger.debug("--------------------------------------------------");
		Logger.debug("::Fund Transfer transaction "+transaction.idToken());
		Logger.debug("==================================================");
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
			KirimDokuHttpConnection connection = httpConnection(post.toString(), URL_TPG, TIMEOUT_TPG, "application/json");
            if (connection.responseCode == 200) {
                Logger.debug("Result                 : "+connection.responseMessage);
    			JsonNode resultJson = Json.parse(connection.responseMessage);
    			Logger.debug("--------------------------------------------------");
    			Logger.debug("Fund Transfer transaction Post result (json) "+resultJson);
    			
    			TransactionLog.insert(transaction, transaction.agent, "TPG_RESPONSE", resultJson.toString());
                
    			Number status = resultJson.path("status").getNumberValue();
    			if ((status != null) && status.intValue() == 0) {
                    Logger.debug("Fund Transfer transaction change status to PAID : "+status);
    				transaction.status = Transaction.STATUS_PAID;
    				try {
    					transaction.remitResponseCode = !resultJson.path("referenceStatus").isMissingNode() ? resultJson.path("referenceStatus").getValueAsText() : transaction.remitResponseCode;
    					transaction.remitResponseMessage = !resultJson.path("statusMessage").isMissingNode() ? resultJson.path("statusMessage").getValueAsText() : transaction.remitResponseMessage;
					} catch (Exception e) {
						e.printStackTrace();
					}
    				transaction.update();
    				User.updateCreditLastBalance(transaction.agent, transaction.senderAmount);
    			} else {
                    Logger.debug("Fund Transfer transaction change status to UNPAID : "+status);
    				transaction.status = Transaction.STATUS_UNPAID;
    				try {
    					transaction.remitResponseCode = !resultJson.path("referenceStatus").isMissingNode() ? resultJson.path("referenceStatus").getValueAsText() : transaction.remitResponseCode;
    					transaction.remitResponseMessage = !resultJson.path("statusMessage").isMissingNode() ? resultJson.path("statusMessage").getValueAsText() : transaction.remitResponseMessage;
					} catch (Exception e) {
						e.printStackTrace();
					}
    				transaction.update();
    			}
            } else if (connection.responseCode == 404) {
				TransactionLog.insert(transaction, transaction.agent, "TPG_RESPONSE", "Http Response Code "+connection.responseCode+" "+connection.responseMessage);
				transaction.remitResponseMessage = "Http Response Code "+connection.responseCode+" "+connection.responseMessage;
				transaction.update();
            } else if (connection.responseCode == 408) {
				TransactionLog.insert(transaction, transaction.agent, "TPG_RESPONSE", "Http Response Code "+connection.responseCode+" "+connection.responseMessage);
				transaction.remitResponseMessage = "Http Response Code "+connection.responseCode+" "+connection.responseMessage;
				transaction.update();
            }
		} catch (Exception e) {
			e.printStackTrace();
			transaction.remitResponseMessage = "Unknown Error "+e.getMessage();
			transaction.update();
            TransactionLog.insert(transaction, transaction.agent, "TPG_RESPONSE", e.getMessage());
		}
		
		Logger.debug("--------------------------------------------------");
	}
	
	public static KirimDokuHttpConnection httpConnection(String parameter, String urls, int timeout, String contentType) {
		KirimDokuHttpConnection result = new KirimDokuHttpConnection();
		try {
			//HTTP URL CONNECTION
            Logger.debug("--------------------------------------------------");
            Logger.debug("URL                    : "+urls);
            Logger.debug("Connection Timeout     : "+timeout);
            Logger.debug("==================================================");
			URL url = new URL(urls);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(timeout);
			connection.setReadTimeout(timeout);
			connection.addRequestProperty("Content-Type", contentType != null ? contentType : "application/json");
			
			OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(parameter);
            wr.flush();
			String hasil = "";
			Logger.debug("Http Response Code     : "+connection.getResponseCode());
			result.responseCode = connection.getResponseCode();
            if (connection.getResponseCode() == 200) {

                BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    hasil = hasil + line;
                }
                
                Logger.debug("Result                 : "+hasil);
    			Logger.debug("--------------------------------------------------");
    			Logger.debug("Fund Transfer transaction Post result (json) "+hasil);
    			connection.disconnect();
    			result.responseMessage = hasil;
    			result.status = true;
    			return result;
            } else {
            	Logger.debug("Http Response Code "+connection.getResponseCode());
			}
		} catch (ConnectException e) {
			e.printStackTrace();
			Logger.debug("Failed - Connection refused");
			result.responseMessage = "Failed - Connection refused";
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			Logger.debug("Failed - Read timed out");
			result.responseMessage = "Failed - Connection refused";
		} catch (Exception e) {
			e.printStackTrace();
			result.responseMessage = "Failed - "+e.getMessage();
		}
		
		return result;
	}
	
	public static void fundTransferDokuWallet(Transaction transaction) {
		Logger.debug("--------------------------------------------------");
		Logger.debug("::Fund Transfer transaction "+transaction.idToken());
		Logger.debug("==================================================");
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
			KirimDokuHttpConnection connection = httpConnection(post.toString(), URL_TPG, TIMEOUT_TPG, "application/json");
            if (connection.responseCode == 200) {
                Logger.debug("Result                 : "+connection.responseMessage);
    			JsonNode resultJson = Json.parse(connection.responseMessage);
    			Logger.debug("--------------------------------------------------");
    			Logger.debug("Fund Transfer transaction Post result (json) "+resultJson);
    			
    			TransactionLog.insert(transaction, transaction.agent, "TPG_RESPONSE", resultJson.toString());
                
    			Number status = resultJson.path("status").getNumberValue();
    			if ((status != null) && status.intValue() == 0) {
                    Logger.debug("Fund Transfer transaction change status to PAID : "+status);
    				transaction.status = Transaction.STATUS_PAID;
    				try {
    					transaction.remitResponseCode = !resultJson.path("referenceStatus").isMissingNode() ? resultJson.path("referenceStatus").getValueAsText() : transaction.remitResponseCode;
    					transaction.remitResponseMessage = !resultJson.path("statusMessage").isMissingNode() ? resultJson.path("statusMessage").getValueAsText() : transaction.remitResponseMessage;
					} catch (Exception e) {
						e.printStackTrace();
					}
    				transaction.update();
    				User.updateCreditLastBalance(transaction.agent, transaction.senderAmount);
    			} else {
                    Logger.debug("Fund Transfer transaction change status to UNPAID : "+status);
    				transaction.status = Transaction.STATUS_UNPAID;
    				try {
    					transaction.remitResponseCode = !resultJson.path("referenceStatus").isMissingNode() ? resultJson.path("referenceStatus").getValueAsText() : transaction.remitResponseCode;
    					transaction.remitResponseMessage = !resultJson.path("statusMessage").isMissingNode() ? resultJson.path("statusMessage").getValueAsText() : transaction.remitResponseMessage;
					} catch (Exception e) {
						e.printStackTrace();
					}
    				transaction.update();
    			}
            } else if (connection.responseCode == 404) {
				TransactionLog.insert(transaction, transaction.agent, "TPG_RESPONSE", "Http Response Code "+connection.responseCode+" "+connection.responseMessage);
				transaction.remitResponseMessage = "Http Response Code "+connection.responseCode+" "+connection.responseMessage;
				transaction.update();
            } else if (connection.responseCode == 408) {
				TransactionLog.insert(transaction, transaction.agent, "TPG_RESPONSE", "Http Response Code "+connection.responseCode+" "+connection.responseMessage);
				transaction.remitResponseMessage = "Http Response Code "+connection.responseCode+" "+connection.responseMessage;
				transaction.update();
            }
		} catch (Exception e) {
			e.printStackTrace();
			transaction.remitResponseMessage = "Unknown Error "+e.getMessage();
			transaction.update();
            TransactionLog.insert(transaction, transaction.agent, "TPG_RESPONSE", e.getMessage());
		}
		
		Logger.debug("--------------------------------------------------");
	}
	
	public static void fundTransferCTC(Transaction transaction) {
		Logger.debug("--------------------------------------------------");
		Logger.debug("::Fund Transfer transaction "+transaction.idToken());
		Logger.debug("==================================================");
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
			KirimDokuHttpConnection connection = httpConnection(post.toString(), URL_TPG, TIMEOUT_TPG, "application/json");
            if (connection.responseCode == 200) {
                Logger.debug("Result                 : "+connection.responseMessage);
    			JsonNode resultJson = Json.parse(connection.responseMessage);
    			Logger.debug("--------------------------------------------------");
    			Logger.debug("Fund Transfer transaction Post result (json) "+resultJson);
    			
    			TransactionLog.insert(transaction, transaction.agent, "TPG_RESPONSE", resultJson.toString());
                
    			Number status = resultJson.path("status").getNumberValue();
    			if ((status != null) && status.intValue() == 0) {
                    Logger.debug("Fund Transfer transaction change status to PAID : "+status);
    				transaction.status = Transaction.STATUS_PAID;
    				try {
    					transaction.remitResponseCode = !resultJson.path("referenceStatus").isMissingNode() ? resultJson.path("referenceStatus").getValueAsText() : transaction.remitResponseCode;
    					transaction.remitResponseMessage = !resultJson.path("statusMessage").isMissingNode() ? resultJson.path("statusMessage").getValueAsText() : transaction.remitResponseMessage;
					} catch (Exception e) {
						e.printStackTrace();
					}
    				transaction.update();
    				User.updateCreditLastBalance(transaction.agent, transaction.senderAmount);
    			} else {
                    Logger.debug("Fund Transfer transaction change status to UNPAID : "+status);
    				transaction.status = Transaction.STATUS_UNPAID;
    				try {
    					transaction.remitResponseCode = !resultJson.path("referenceStatus").isMissingNode() ? resultJson.path("referenceStatus").getValueAsText() : transaction.remitResponseCode;
    					transaction.remitResponseMessage = !resultJson.path("statusMessage").isMissingNode() ? resultJson.path("statusMessage").getValueAsText() : transaction.remitResponseMessage;
					} catch (Exception e) {
						e.printStackTrace();
					}
    				transaction.update();
    			}
            } else if (connection.responseCode == 404) {
				TransactionLog.insert(transaction, transaction.agent, "TPG_RESPONSE", "Http Response Code "+connection.responseCode+" "+connection.responseMessage);
				transaction.remitResponseMessage = "Http Response Code "+connection.responseCode+" "+connection.responseMessage;
				transaction.update();
            } else if (connection.responseCode == 408) {
				TransactionLog.insert(transaction, transaction.agent, "TPG_RESPONSE", "Http Response Code "+connection.responseCode+" "+connection.responseMessage);
				transaction.remitResponseMessage = "Http Response Code "+connection.responseCode+" "+connection.responseMessage;
				transaction.update();
            }
		} catch (Exception e) {
			e.printStackTrace();
			transaction.remitResponseMessage = "Unknown Error "+e.getMessage();
			transaction.update();
            TransactionLog.insert(transaction, transaction.agent, "TPG_RESPONSE", e.getMessage());
		}
		
		Logger.debug("--------------------------------------------------");
	}
}
