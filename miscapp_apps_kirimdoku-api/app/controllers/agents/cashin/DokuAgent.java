package controllers.agents.cashin;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import kirimdoku.channel.processor.BillPaymentProcessor;
import kirimdoku.helpers.BillDetail;
import kirimdoku.helpers.BillDetailDescription;
import kirimdoku.helpers.BillPaymentInquiryResponse;
import kirimdoku.util.Plugin;
import kirimdoku.util.Utility;
import models.Fee;
import models.Transaction;
import models.TransactionFee;
import models.TransactionInquiry;
import models.TransactionInquiry.TransactionInquiryState;
import models.TransactionInquiry.TransactionInquiryStatus;
import models.User;
import models.Voucher;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

import play.Logger;
import play.libs.Json;
import play.mvc.Http.Request;
import play.mvc.Result;
import play.mvc.Results;
import controllers.CashIn.InquiryForm;
import controllers.CashIn.RemitForm;
import controllers.actors.SuspiciousActor;
//import controllers.actors.SuspiciousActor;
//import controllers.actors.TPGActor;
import controllers.helpers.Definition;
import controllers.helpers.FeeHelper;
import controllers.helpers.ForexHelper;
import controllers.helpers.HashWithSHA1;
import controllers.helpers.TransferResponse;
import controllers.tokens.TransactionToken;

public class DokuAgent extends BaseAgent {
	
	protected final String sharedKey = "9187a294cc";
	
	@Override
	public Result onPing(Request request) {
		ObjectNode result = Json.newObject();
		result.put("status", "0");
		result.put("message", "PING OK");
		return play.mvc.Results.ok(result);
	}

	@Override
	public Result onInquiry(Request request, InquiryForm form) {
		// Create result object
		ObjectNode result =  Json.newObject();
		try {
			// Do some logic processing from outside this method scope
			processFormInquiry(form, true);
			processFormForex(form);

			if (form.forexRef == null) {
				result.put(Definition.FIELD_RESPONSE_STATUS,
						Definition.STATUS_UNSUPPORTED);
				result.put(Definition.FIELD_RESPONSE_MESSAGE,
						"No forex found for destination country");
				return Results.ok(result);
			}

			processFormFund(form);
			processFormFee(request, form);

			// Start to generate and build Response
			ObjectNode inquiry = Json.newObject();
			inquiry.put("idToken", form.idToken);
			BigDecimal additionalFee = new BigDecimal(0);
			ObjectNode fund = Json.newObject();

			ObjectNode origin = Json.newObject();
			origin.put("amount", form.senderAmount);
			origin.put("currency", form.senderCurrency.code);
			fund.put("origin", origin);

			ObjectNode fees = Json.newObject();
			ArrayNode fundFeesComponents = Json.newObject().arrayNode();
			BigDecimal total = new BigDecimal(0);
			
			for (TransactionFee fee : form.fees) {
				ObjectNode feeNode = Json.newObject();
				feeNode.put("description", fee.description);
				feeNode.put("amount", fee.amount);
				fundFeesComponents.add(feeNode);
				total = total.add(fee.amount);
				additionalFee = additionalFee.add(fee.additionalFee);
			}
			//if (form.sendType.equals("senderAmount"))
			form.beneficiaryAmount = form.beneficiaryAmount.subtract(additionalFee);
			if (form.channel.code.equals("07")) {
				form.beneficiaryAmount = form.beneficiaryAmount.setScale(2, RoundingMode.FLOOR);
			}
			ObjectNode destination = Json.newObject();
			destination.put("amount", form.beneficiaryAmount);
			destination.put("currency", form.beneficiaryCurrency.code);
			fund.put("destination", destination);
			
			fees.put("total", total);
			fees.put("currency", form.senderCurrency.code);
			fees.put("components", fundFeesComponents);
			fees.put("additionalFee", additionalFee);
			fund.put("fees", fees);
			inquiry.put("fund", fund);

			inquiry.put("senderCountry", Json.toJson(form.senderCountry));
			inquiry.put("senderCurrency", Json.toJson(form.senderCurrency));
			inquiry.put("beneficiaryCountry",
					Json.toJson(form.beneficiaryCountry));
			inquiry.put("beneficiaryCurrency",
					Json.toJson(form.beneficiaryCurrency));

			inquiry.put("channel", Json.toJson(form.channel));
			inquiry.put("forexReference", Json.toJson(form.forexRef));
			
			// Final result
			result.put("status", 0);
			result.put("message", "Inquiry succeed");
			
			TransactionInquiry trxInq = new TransactionInquiry();
			trxInq.typeId = TransactionInquiry.Type.CASHIN_INQUIRY;
			trxInq.user = form.user;
			trxInq.requestTime = new Date();
			trxInq.forexReference = form.forexRef;
			trxInq.beneficiaryAmount = form.beneficiaryAmount;
			trxInq.sendingAmount = form.senderAmount;
			trxInq.forexReference = form.forexRef;
			trxInq.channel = form.channel;

			trxInq.save();
			trxInq.refresh();
			String inquiryCode = TransactionInquiry.formatAsCode(trxInq);
			inquiry.put("idToken", inquiryCode);
			
			if (form.channel.code.equals("04") && form.beneficiaryWalletId != null && !form.beneficiaryWalletId.equals("")) {
				form.idToken = inquiryCode;
				TransferResponse responseInquiry = Utility.fundInquiryCashToWallet(form);
				inquiry.put("beneficiaryWalletId", form.beneficiaryWalletId);
				inquiry.put("beneficiaryWalletName", form.beneficiaryWalletName);
				if (!responseInquiry.referenceStatus.equals("0000")) {
					trxInq.status = TransactionInquiryStatus.FAILED.getCode();
					trxInq.state = TransactionInquiryState.DONE.getCode();
					trxInq.inquiryResponseCode = responseInquiry.referenceStatus;
					trxInq.inquiryResponseMessage = responseInquiry.referenceMessage;
					result.put("status", "1");
					result.put("message", responseInquiry.referenceMessage);
				} else {
					trxInq.status = TransactionInquiryStatus.SUCCESS.getCode();
					trxInq.state = TransactionInquiryState.DONE.getCode();
					trxInq.inquiryResponseCode = responseInquiry.referenceStatus;
					trxInq.inquiryResponseMessage = responseInquiry.referenceMessage;
					trxInq.accountType = form.channel.code;
					trxInq.dokuWalletId = form.beneficiaryWalletId;
					trxInq.dokuWalletName = form.beneficiaryWalletName;
					trxInq.validationId = responseInquiry.trackingId;
				}
				trxInq.update();
			} else if (form.channel.code.equals("10")) {
				if (form.billPayment != null &&
						form.billPayment.billPaymentService != null && 
						form.billPayment.selectBill != null &&
						form.billPayment.selectOperator != null &&
						form.billPayment.accountNumber != null) {
					String additionalParam = form.channel.additionalParam;
					if (!additionalParam.contains(form.billPayment.selectOperator)) {
						result.put("status", "1");
						result.put("message", "Invalid parameter bill payment ["+form.billPayment.selectOperator+"]");
						return Results.ok(result);
					}
					Logger.debug("billPaymentService     : "+form.billPayment.billPaymentService);
					Logger.debug("selectBill             : "+form.billPayment.selectBill);
					Logger.debug("selectOperator         : "+form.billPayment.selectOperator);
					Logger.debug("accountNumber          : "+form.billPayment.accountNumber);
					Logger.debug("systrace               : "+trxInq.id);
					form.trackingId = trxInq.id.toString();
					form.idToken = inquiryCode;
					TransferResponse responseInquiry = Utility.fundInquiryBillPayment(form);
					if (!responseInquiry.referenceStatus.equals("0000")) {
						trxInq.status = TransactionInquiryStatus.SUCCESS.getCode();
						trxInq.state = TransactionInquiryState.DONE.getCode();
						trxInq.inquiryResponseCode = responseInquiry.referenceStatus;
						trxInq.inquiryResponseMessage = responseInquiry.statusMessage;
						result.put("status", "1");
						result.put("message", responseInquiry.referenceMessage);
					} else {
						trxInq.status = TransactionInquiryStatus.FAILED.getCode();
						trxInq.state = TransactionInquiryState.DONE.getCode();
						trxInq.inquiryResponseCode = responseInquiry.referenceStatus;
						trxInq.inquiryResponseMessage = responseInquiry.statusMessage;
						
						ObjectMapper objectMapper = new ObjectMapper();
						objectMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
						objectMapper.configure(Feature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
						BillPaymentInquiryResponse billPaymentInquiryResponse = (BillPaymentInquiryResponse) objectMapper.readValue(responseInquiry.referenceMessage, BillPaymentInquiryResponse.class);
						if (form.billPayment.selectOperator.equals("9950102")) {
							billPaymentInquiryResponse.billdetails = new ArrayList<BillDetail>();
							List<BillDetailDescription> billDetailDescriptions1 = new ArrayList<BillDetailDescription>();
							billDetailDescriptions1.add(new BillDetailDescription("DENOM", "20000"));
							billPaymentInquiryResponse.billdetails.add(new BillDetail(BillPaymentProcessor.adminFeePln, "1", "360", null, billDetailDescriptions1, null, "20 Ribu", (20000.00+BillPaymentProcessor.adminFeePln)));
							List<BillDetailDescription> billDetailDescriptions2 = new ArrayList<BillDetailDescription>();
							billDetailDescriptions2.add(new BillDetailDescription("DENOM", "50000"));
							billPaymentInquiryResponse.billdetails.add(new BillDetail(BillPaymentProcessor.adminFeePln, "1", "360", null, billDetailDescriptions2, null, "50 Ribu", (50000.00+BillPaymentProcessor.adminFeePln)));
							List<BillDetailDescription> billDetailDescriptions3 = new ArrayList<BillDetailDescription>();
							billDetailDescriptions3.add(new BillDetailDescription("DENOM", "100000"));
							billPaymentInquiryResponse.billdetails.add(new BillDetail(BillPaymentProcessor.adminFeePln, "1", "360", null, billDetailDescriptions3, null, "100 Ribu", (100000.00+BillPaymentProcessor.adminFeePln)));
							List<BillDetailDescription> billDetailDescriptions4 = new ArrayList<BillDetailDescription>();
							billDetailDescriptions4.add(new BillDetailDescription("DENOM", "200000"));
							billPaymentInquiryResponse.billdetails.add(new BillDetail(BillPaymentProcessor.adminFeePln, "1", "360", null, billDetailDescriptions4, null, "200 Ribu", (200000.00+BillPaymentProcessor.adminFeePln)));
							List<BillDetailDescription> billDetailDescriptions5 = new ArrayList<BillDetailDescription>();
							billDetailDescriptions5.add(new BillDetailDescription("DENOM", "500000"));
							billPaymentInquiryResponse.billdetails.add(new BillDetail(BillPaymentProcessor.adminFeePln, "1", "360", null, billDetailDescriptions5, null, "500 Ribu", (500000.00+BillPaymentProcessor.adminFeePln)));
							List<BillDetailDescription> billDetailDescriptions6 = new ArrayList<BillDetailDescription>();
							billDetailDescriptions6.add(new BillDetailDescription("DENOM", "1000000"));
							billPaymentInquiryResponse.billdetails.add(new BillDetail(BillPaymentProcessor.adminFeePln, "1", "360", null, billDetailDescriptions6, null, "1 Juta", (1000000.00+BillPaymentProcessor.adminFeePln)));
						}
						
						for (BillDetail billDetail : billPaymentInquiryResponse.billdetails) {
							BigDecimal totalAmount = BigDecimal.valueOf(billDetail.totalamount).setScale(6, RoundingMode.FLOOR);
							totalAmount = totalAmount.add(additionalFee);
							billDetail.totalamount = totalAmount.doubleValue();
							billDetail.originTotalAmount = ForexHelper.convertOriginCurrency(totalAmount, form.forexRef).setScale(6, RoundingMode.FLOOR).doubleValue();
						}
						
						result.put("billPayment", Json.toJson(billPaymentInquiryResponse));
						
						trxInq.accountType = form.channel.code;
						trxInq.accountName = form.billPayment.selectOperator;
						trxInq.validationId = form.trackingId;
						trxInq.beneficiaryAmount = BigDecimal.ZERO;
						trxInq.collectId = billPaymentInquiryResponse.inquiry_ID;
						trxInq.update();
					}
				} else {
					result.put("status", "1");
					result.put("message", "Invalid parameter bill payment");
					return Results.ok(result);
				}
			} else if (form.channel.code.equals("06") && form.beneficiaryAccount != null && !form.beneficiaryAccount.bank.code.equals("BNIAIDJA")) {
				form.idToken = inquiryCode;
				TransferResponse responseInquiry = Utility.fundInquiryAccountTransfer(form);
				if (responseInquiry.referenceStatus.equals("015")) {
					trxInq.status = TransactionInquiryStatus.SUCCESS.getCode();
					trxInq.state = TransactionInquiryState.DONE.getCode();
					trxInq.inquiryResponseCode = responseInquiry.referenceStatus;
					trxInq.inquiryResponseMessage = responseInquiry.referenceMessage;
					
					trxInq.accountType = form.channel.code;
					trxInq.accountId = form.beneficiaryAccount.number;
					if (responseInquiry.referenceMessage.startsWith("Beneficary name is invalid - ")) {
						String accountName = responseInquiry.referenceMessage.substring(29);
						form.beneficiaryAccount.name = accountName;
					}
					trxInq.accountName = form.beneficiaryAccount.name;
					inquiry.put("beneficiaryAccount", Json.toJson(form.beneficiaryAccount));
					trxInq.update();
				} else {
					trxInq.status = TransactionInquiryStatus.FAILED.getCode();
					trxInq.state = TransactionInquiryState.DONE.getCode();
					trxInq.inquiryResponseCode = responseInquiry.referenceStatus;
					trxInq.inquiryResponseMessage = responseInquiry.referenceMessage;
					
					result.put("status", "1");
					result.put("message", "Invalid Inquiry "+form.channel.name);
					return Results.ok(result);
				}
			} else if (form.channel.code.equals("07") && form.beneficiaryAccount != null) {
				form.idToken = inquiryCode;
				trxInq.accountType = form.channel.code;
				trxInq.accountId = form.beneficiaryAccount.number;
				trxInq.accountName = form.beneficiaryAccount.name;
				TransferResponse responseInquiry = Utility.fundInquiryBankDeposit(form);
				if (responseInquiry.status == 0) {
					trxInq.status = TransactionInquiryStatus.SUCCESS.getCode();
					trxInq.state = TransactionInquiryState.DONE.getCode();
					trxInq.inquiryResponseCode = responseInquiry.referenceStatus;
					trxInq.inquiryResponseMessage = responseInquiry.referenceMessage;
					
					trxInq.validationId = responseInquiry.accessToken;
					trxInq.accountName = responseInquiry.receiverName;
					form.beneficiaryAccount.name = responseInquiry.receiverName;
					trxInq.update();
				} else {
					trxInq.status = TransactionInquiryStatus.FAILED.getCode();
					trxInq.state = TransactionInquiryState.DONE.getCode();
					trxInq.inquiryResponseCode = responseInquiry.referenceStatus;
					trxInq.inquiryResponseMessage = responseInquiry.referenceMessage;
					
					result.put("status", "1");
					result.put("message", responseInquiry.referenceMessage);
					trxInq.update();
					return Results.ok(result);
				}
				inquiry.put("beneficiaryAccount", Json.toJson(form.beneficiaryAccount));
			}
			result.put("inquiry", inquiry);
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
			result.put("status", Definition.STATUS_UNSUPPORTED);
			result.put("message", e.getMessage());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			result.put("status", Definition.STATUS_ILLEGALPARAMS);
			result.put("message", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", Definition.STATUS_UNKNOWN);
			result.put("message", e.getMessage());
		}
		Logger.debug("Inquiry result "+result.toString());
		return Results.ok(result);
	}

	@Override
	public Result onRemit(Request request, RemitForm form) {
		// Create result object
		ObjectNode result =  Json.newObject();
		try {
			// Process any validations and filling informations to form
			if(form.inquiry.forexReference != null) {
				form.forexReference = form.inquiry.forexReference;
			} else {
				processFormForex(form);
			}
			
			processFormFund(form);
			processFormFee(request, form);
			
			//START PLUGIN
			ObjectNode resultPlugin = Plugin.corporateHasExceedSendLimit(form.corporate, form.senderAmount);
			if(resultPlugin != null) return Results.ok(resultPlugin);
			
			resultPlugin = Plugin.operatorHasExceedSendLimit(form.agent, form.senderAmount);
			if(resultPlugin != null) return Results.ok(resultPlugin);
			//END PLUGIN
			
			BigDecimal additionalFee = new BigDecimal(0);
			for (TransactionFee fee : form.fees) {
				additionalFee = additionalFee.add(fee.additionalFee);
			}
			
			if (form.sendType.equals("senderAmount"))
			form.beneficiaryAmount = form.beneficiaryAmount.subtract(additionalFee);
			
			if (form.channel.code.equals("07")) {
				form.beneficiaryAmount = form.beneficiaryAmount.setScale(2, RoundingMode.FLOOR);
			}
			
			// save form to db
			Transaction trx = new Transaction();
			trx.createdTime = new Date();
			trx.corporate = form.corporate;
			trx.agent = form.agent;
			trx.channel = form.channel;
			trx.sender = form.sender;
			trx.senderCountry = form.senderCountry;
			trx.senderCurrency = form.senderCurrency;
			trx.senderAmount = form.senderAmount;
			trx.beneficiary = form.beneficiary;
			trx.beneficiaryCountry = form.beneficiaryCountry;
			trx.beneficiaryCurrency = form.beneficiaryCurrency;
			trx.beneficiaryAmount = form.beneficiaryAmount;
			trx.forexReference = form.forexReference;
			trx.beneficiaryCity = form.beneficiaryCity;
			trx.cashInTime = Calendar.getInstance();
			trx.status = Transaction.STATUS_PENDING;
			trx.senderNote = form.senderNote;
			trx.sendTrxId = form.sendTrxId;
			trx.auth1 = form.auth1;
			trx.remitResponseCode = Transaction.STATUS_UNKNOWN+"";
			trx.remitResponseMessage = "FAILED";
			if (form.cashInTime != null) {
				trx.cashInTime.setTime(form.cashInTime);
			}
			
			TransactionInquiry inquiry = TransactionInquiry.findByCode(form.inquiry.idToken);
			
			Logger.debug("Amount : "+form.beneficiaryAmount+" >< "+inquiry.beneficiaryAmount);
			if (!form.channel.code.equals("10"))
			if (form.beneficiaryAmount.compareTo(inquiry.beneficiaryAmount) != 0) {
				result.put(Definition.FIELD_RESPONSE_STATUS,
						Definition.STATUS_ILLEGALSECURITY);
				result.put(Definition.FIELD_RESPONSE_MESSAGE,
						"Invalid Amount");
				trx.remitResponseCode = Definition.STATUS_ILLEGALSECURITY+"";
				trx.remitResponseMessage = "Invalid Amount";
				trx.save();
				return Results.ok(result);
			}
			
			if((form.sendTrxId != null) && (!form.sendTrxId.isEmpty())) {
				Transaction t = models.Transaction.find.where().eq("corporate", trx.corporate).eq("sendTrxId", trx.sendTrxId).findUnique();
				if (t != null) {
					result.put(Definition.FIELD_RESPONSE_STATUS, Definition.STATUS_ILLEGALSECURITY);
					result.put(Definition.FIELD_RESPONSE_MESSAGE, "Duplicate sendTrxId Reference");
					trx.remitResponseCode = Definition.STATUS_ILLEGALSECURITY+"";
					trx.remitResponseMessage = "Duplicate sendTrxId Reference";
					trx.save();
					return Results.ok(result);
				}
			}
			
			Result rulesResult = Transaction.checkSendRules(trx, form.agent);
			if(rulesResult != null) {
				trx.remitResponseCode = Definition.STATUS_ILLEGALSECURITY+"";
				trx.remitResponseMessage = "Failed passing rule";
				trx.save();
				return rulesResult;
			}
			
			if ("02".equals(trx.channel.code)) {
				// save transaction
				Logger.debug("Attempting to save remit to db..");
				trx.save();
				
				User.updateCreditLastBalance(trx.agent, trx.senderAmount);
				
				// save additional transaction fees to db
				for (TransactionFee fee : form.fees) {
					fee.transaction = trx;
					fee.save();
				}

				// Bind the inquiry to transaction
				form.inquiry.transaction = trx;
				form.inquiry.save();

				// Call background actor to check for this transaction
				SuspiciousActor.getRefInstance().tell(trx);

				// create remit result json object
				ObjectNode remit = Json.newObject();
				remit.put("transactionId", TransactionToken.fromTransaction(trx).toString());
				result.put("status", Definition.STATUS_OK);
				result.put("message", "Remit accepted");
				result.put("remit", remit);
			} else if ("21".equals(trx.channel.code)) {
				if (form.voucherCode != null && !form.voucherCode.equals("")) {
					Voucher voucher = checkVoucher(form);
					if (voucher != null) {
						trx.voucherCode = form.voucherCode;
						voucher.voucher_status = false;
						voucher.save();
						
						// save transaction
						Logger.debug("Attempting to save remit to db..");
						trx.save();
						
						User.updateCreditLastBalance(trx.agent, trx.senderAmount);
						
						// save additional transaction fees to db
						for (TransactionFee fee : form.fees) {
							fee.transaction = trx;
							fee.save();
						}
						// Bind the inquiry to transaction
						form.inquiry.transaction = trx;
						form.inquiry.save();
						
						// Call background actor to check for this transaction
						SuspiciousActor.getRefInstance().tell(trx);

						// create remit result json object
						ObjectNode remit = Json.newObject();
						remit.put("transactionId", TransactionToken.fromTransaction(trx).toString());
						result.put("status", Definition.STATUS_OK);
						result.put("message", "Remit accepted");
						result.put("remit", remit);
						
					} else {
						Logger.debug("Invalid Voucher Code");
						
						result.put(Definition.FIELD_RESPONSE_STATUS, Definition.STATUS_INVALIDPARAMS);
						result.put(Definition.FIELD_RESPONSE_MESSAGE, "Invalid Voucher Code");
						return Results.ok(result);
						
					}
				} else {
					Logger.debug("Invalid Voucher Code");
					
					result.put(Definition.FIELD_RESPONSE_STATUS, Definition.STATUS_INVALIDPARAMS);
					result.put(Definition.FIELD_RESPONSE_MESSAGE, "Invalid Voucher Code");
					return Results.ok(result);
				}
				
			} else if ("22".equals(trx.channel.code)) {
				if (form.voucherCode != null && !form.voucherCode.equals("")) {
					Voucher voucher = checkVoucher(form);
					if (voucher != null) {
						trx.voucherCode = form.voucherCode;
						voucher.voucher_status = false;
						voucher.save();
						trx.beneficiaryAccount = form.beneficiaryAccount;
						
						// save transaction
						Logger.debug("Attempting to save remit to db.. "+Json.toJson(form.beneficiaryAccount));
						trx.save();
						
						User.updateCreditLastBalance(trx.agent, trx.senderAmount);

						// save additional transaction fees to db
						for (TransactionFee fee : form.fees) {
							fee.transaction = trx;
							fee.save();
						}

						// Bind the inquiry to transaction
						form.inquiry.transaction = trx;
						form.inquiry.save();

						// Call background actor to check for this transaction
						SuspiciousActor.getRefInstance().tell(trx);
						
						// create remit result json object
						ObjectNode remit = Json.newObject();
						remit.put("transactionId", TransactionToken.fromTransaction(trx).toString());
						result.put("status", Definition.STATUS_OK);
						result.put("message", "Remit accepted");
						result.put("remit", remit);
						
					} else {
						Logger.debug("Invalid Voucher Code");
						
						result.put(Definition.FIELD_RESPONSE_STATUS, Definition.STATUS_INVALIDPARAMS);
						result.put(Definition.FIELD_RESPONSE_MESSAGE, "Invalid Voucher Code");
						return Results.ok(result);
					}
				} else {
					Logger.debug("Invalid Voucher Code");
					
					result.put(Definition.FIELD_RESPONSE_STATUS, Definition.STATUS_INVALIDPARAMS);
					result.put(Definition.FIELD_RESPONSE_MESSAGE, "Invalid Voucher Code");
					return Results.ok(result);
				}
				
			} else if ("05".equals(trx.channel.code)) {
				trx.beneficiaryAccount = form.beneficiaryAccount;
				
				// save transaction
				Logger.debug("Attempting to save remit to db.. "+Json.toJson(form.beneficiaryAccount));
				trx.save();
				
				User.updateCreditLastBalance(trx.agent, trx.senderAmount);
				
				// save additional transaction fees to db
				for (TransactionFee fee : form.fees) {
					fee.transaction = trx;
					fee.save();
				}

				// Bind the inquiry to transaction
				form.inquiry.transaction = trx;
				form.inquiry.save();

				// Call background actor to check for this transaction
				SuspiciousActor.getRefInstance().tell(trx);
				
				// create remit result json object
				ObjectNode remit = Json.newObject();
				remit.put("transactionId", TransactionToken.fromTransaction(trx).toString());
				result.put("status", Definition.STATUS_OK);
				result.put("message", "Remit accepted");
				result.put("remit", remit);
			} else if ("06".equals(trx.channel.code)) {
				trx.beneficiaryAccount = form.beneficiaryAccount;
				trx.status = models.Transaction.STATUS_PENDING;
				
				// save transaction
				Logger.debug("Attempting to save remit cash to account to db..");
				trx.save();

				// save additional transaction fees to db
				for (TransactionFee fee : form.fees) {
					fee.transaction = trx;
					fee.save();
				}

				// Bind the inquiry to transaction
				form.inquiry.transaction = trx;
				form.inquiry.save();

				// Call background actor to check for this transaction
				//TPGActor.getRefInstance().tell(trx);
				
				Utility.fundTransferAccountTransfer(trx);
				SuspiciousActor.getRefInstance().tell(trx);
				
				// create remit result json object
				ObjectNode remit = Json.newObject();
				remit.put("transactionId", TransactionToken
						.fromTransaction(trx).toString());
				result.put("status", Definition.STATUS_OK);
				result.put("message", "Remit accepted");
				result.put("remit", remit);
			} else if ("03".equals(trx.channel.code)) {
				trx.beneficiaryAccount = form.beneficiaryAccount;
				
				Logger.debug("Attempting to save remit cash to account to db..");
				
				trx.status = models.Transaction.STATUS_PENDING;
				// save transaction
				trx.save();

				// save additional transaction fees to db
				for (TransactionFee fee : form.fees) {
					fee.transaction = trx;
					fee.save();
				}

				// Bind the inquiry to transaction
				form.inquiry.transaction = trx;
				form.inquiry.save();

				// Call background actor to check for this transaction
				//TPGActor.getRefInstance().tell(trx);
				
				Utility.fundTransferCTC(trx);
				SuspiciousActor.getRefInstance().tell(trx);

				// create remit result json object
				ObjectNode remit = Json.newObject();
				remit.put("transactionId", TransactionToken
						.fromTransaction(trx).toString());
				result.put("status", Definition.STATUS_OK);
				result.put("message", "Remit accepted");
				result.put("remit", remit);
			} else if ("04".equals(trx.channel.code)) {
				trx.beneficiaryWalletId = inquiry.dokuWalletId;
				trx.beneficiaryWalletName = inquiry.dokuWalletName;
				trx.status = models.Transaction.STATUS_PENDING;
				
				// save transaction
				Logger.debug("Attempting to save remit cash to account to db..");
				trx.save();

				// save additional transaction fees to db
				for (TransactionFee fee : form.fees) {
					fee.transaction = trx;
					fee.save();
				}

				// Bind the inquiry to transaction
				form.inquiry.transaction = trx;
				form.inquiry.save();

				// Call background actor to check for this transaction
				//TPGActor.getRefInstance().tell(trx);
				Utility.fundTransferDokuWallet(trx);
				SuspiciousActor.getRefInstance().tell(trx);

				// create remit result json object
				ObjectNode remit = Json.newObject();
				remit.put("transactionId", TransactionToken
						.fromTransaction(trx).toString());
				result.put("status", Definition.STATUS_OK);
				result.put("message", "Remit accepted");
				result.put("remit", remit);
			} else if ("10".equals(trx.channel.code)) {
				trx.paymentData = Json.toJson(form.billPayment).toString();
				trx.status = models.Transaction.STATUS_PENDING;
				trx.beneficiaryAmount = form.beneficiaryAmount.setScale(0, RoundingMode.FLOOR);
				trx.senderNote = "Beli Pulsa & Bayar Tagihan";
				
				// save transaction
				Logger.debug("Attempting to save remit cash to account to db..");
				trx.save();

				// save additional transaction fees to db
				for (TransactionFee fee : form.fees) {
					fee.transaction = trx;
					fee.save();
				}

				// Bind the inquiry to transaction
				form.inquiry.transaction = trx;
				form.inquiry.accountId = form.billPayment.accountNumber;
				form.inquiry.accountType = form.billPayment.selectDenom;
				form.inquiry.beneficiaryAmount = form.beneficiaryAmount.setScale(0, RoundingMode.FLOOR);
				form.inquiry.save();

				Utility.fundTransferBillPayment(trx);
				
				// create remit result json object
				ObjectNode remit = Json.newObject();
				remit.put("paymentData", Json.parse(trx.paymentData));
				remit.put("transactionId", TransactionToken
						.fromTransaction(trx).toString());
				result.put("status", Definition.STATUS_OK);
				result.put("message", "Remit accepted");
				result.put("remit", remit);
			} else if ("07".equals(trx.channel.code)) {
				trx.beneficiaryAccount = form.beneficiaryAccount;
				trx.status = models.Transaction.STATUS_PENDING;
				// save transaction
				Logger.debug("Attempting to save remit cash to account to db..");
				trx.save();

				// save additional transaction fees to db
				for (TransactionFee fee : form.fees) {
					fee.transaction = trx;
					fee.save();
				}

				// Bind the inquiry to transaction
				form.inquiry.transaction = trx;
				form.inquiry.save();

				// Call background actor to check for this transaction
				Utility.fundTransferBankDeposit(trx);
				SuspiciousActor.getRefInstance().tell(trx);

				// create remit result json object
				ObjectNode remit = Json.newObject();
				remit.put("paymentData", Json.parse(trx.paymentData));
				remit.put("transactionId", TransactionToken
						.fromTransaction(trx).toString());
				result.put("status", Definition.STATUS_OK);
				result.put("message", "Remit accepted");
				result.put("remit", remit);
			}
		} catch (UnsupportedOperationException e) {
			result.put("status", Definition.STATUS_UNSUPPORTED);
			result.put("message", e.getMessage());
		} catch (IllegalArgumentException e) {
			result.put("status", Definition.STATUS_ILLEGALPARAMS);
			result.put("message", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", Definition.STATUS_UNKNOWN);
			result.put("message", e.getMessage());
		}
		Logger.debug("Remit result "+result.toString());
		return Results.ok(result);
	}

	private static Voucher checkVoucher(RemitForm form) {
		Voucher voucher = null;
		try {
			if (form.voucherCode != null && !form.voucherCode.trim().equals("")) {
				Logger.debug(":: CHECKING VOUCHER CODE");
				String voucherCode = HashWithSHA1.SHA1(form.voucherCode.trim());
				Logger.debug("Voucher Code ["+voucherCode+"]");
				voucher = Voucher.find.where().eq("code", voucherCode).findUnique();
				if (voucher != null) {
					Logger.debug("Voucher Code found ["+form.voucherCode+"]");
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
	
	/**
	 * Internal functions for processing form inquiry
	 * 
	 * @param form
	 *            of the inquiry request (can also be used for RemitForm because
	 *            RemitForm is subclass of InquiryForm)
	 */
	private static void processFormInquiry(InquiryForm form, boolean create) {
		// Need any additional process? since its already handled on the form
		// onValidate()
	}

	/**
	 * Internal function for processing fee and fill out into the current param
	 * form
	 * 
	 * @param form
	 *            of the inquiry request (can also be used for RemitForm because
	 *            RemitForm is subclass of InquiryForm)
	 */
	private static void processFormFee(Request request, InquiryForm form) {
		List<Fee> fees = FeeHelper.getFeeTransaction(form, form.senderCurrency);

		form.fees = new ArrayList<TransactionFee>();
		for (Fee fee : fees) {
			TransactionFee transactionFee = new TransactionFee(null, fee);
			form.fees.add(transactionFee);
		}
	}

	private static void processFormFee(Request request, RemitForm form) {
		List<Fee> fees = FeeHelper.getFeeTransaction(form, form.senderCurrency);

		form.fees = new ArrayList<TransactionFee>();
		for (Fee fee : fees) {
			TransactionFee transactionFee = new TransactionFee(null, fee);
			form.fees.add(transactionFee);
		}
	}

	/**
	 * Internal function for processing forex, objective is taking the last
	 * forex references and later so that can be used to calculate fund
	 * 
	 * @param form
	 *            of the inquiry request (can also be used for RemitForm because
	 *            RemitForm is subclass of InquiryForm)
	 */
	private static void processFormForex(InquiryForm form) {
		form.forexRef = ForexHelper.getLastForexReference(form.corporate,
				form.senderCurrency, form.beneficiaryCurrency);
	}

	private static void processFormForex(RemitForm form) {
		Logger.debug("Attemp to retrieve form forex : " + form.corporate.code
				+ " - " + form.senderCurrency.code);
		form.forexReference = ForexHelper.getLastForexReference(form.corporate,
				form.senderCurrency, form.beneficiaryCurrency);
	}

	/**
	 * Internal function for processing and doing any calculations based on fee
	 * and forex that are processed from processFormFee and processFormForex
	 * 
	 * @param form
	 *            of the inquiry request (can also be used for RemitForm because
	 *            RemitForm is subclass of InquiryForm)
	 */
	private static void processFormFund(InquiryForm form) {
		if (form.senderAmount != null) {
			form.senderAmount = form.senderAmount.setScale(6,
					BigDecimal.ROUND_FLOOR);
			if (form.forexRef != null) {
				form.beneficiaryAmount = form.senderAmount.multiply(BigDecimal
						.valueOf(form.forexRef.rate));
				form.beneficiaryAmount = form.beneficiaryAmount.setScale(6,
						BigDecimal.ROUND_FLOOR);
			}
		} else if (form.beneficiaryAmount != null) {
			form.beneficiaryAmount = form.beneficiaryAmount.setScale(6,
					BigDecimal.ROUND_FLOOR);
			if (form.forexRef != null) {
				form.senderAmount = form.beneficiaryAmount.divide(
						BigDecimal.valueOf(form.forexRef.rate), 6);
				form.senderAmount = form.senderAmount.setScale(6,
						BigDecimal.ROUND_FLOOR);
			}
		}
	}

	private static void processFormFund(RemitForm helper) {
		try {
			Logger.debug(">> PROCESS FORM FUND REMIT");
			Logger.debug("SENDER AMOUNT : "+helper.senderAmount);
			Logger.debug("BENEFY AMOUNT : "+helper.beneficiaryAmount);
			Logger.debug("SEND TYPE     : "+helper.sendType);
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
	
}
