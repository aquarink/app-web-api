package kirimdoku.channel.processor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import models.Transaction;
import models.TransactionFee;
import models.TransactionInquiry;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import controllers.helpers.Definition;
import controllers.helpers.ForexHelper;
import controllers.helpers.ResponseHelper;
import controllers.helpers.TransferResponse;
import controllers.tokens.TransactionToken;
import kirimdoku.helpers.BillDetail;
import kirimdoku.helpers.BillDetailDescription;
import kirimdoku.helpers.BillPaymentInquiryResponse;
import kirimdoku.helpers.CashInInquiryHelper;
import kirimdoku.helpers.CashInRemitHelper;
import kirimdoku.helpers.CashOutCollectHelper;
import kirimdoku.helpers.CashOutInquiryHelper;
import kirimdoku.helpers.CashOutValidateHelper;
import kirimdoku.util.Plugin;
import kirimdoku.util.Utility;
import play.Configuration;
import play.Logger;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.Results;

public class BillPaymentProcessor extends BaseChannelProcessor {
	
	private static final Configuration CONFIG_ADMINFEE = play.Play.application().configuration().getConfig("admin.fee");
	public static final double adminFeePln = CONFIG_ADMINFEE != null && CONFIG_ADMINFEE.getDouble("pln") != null ? CONFIG_ADMINFEE.getDouble("pln") : 0;
	
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
			
			if (helper.billPayment == null ||
					helper.billPayment.billPaymentService == null || 
					helper.billPayment.selectBill == null ||
					helper.billPayment.selectOperator == null ||
					helper.billPayment.accountNumber == null) {
				result.put("status", "1");
				result.put("message", "Invalid parameter bill payment");
				return Results.ok(result);
			}
			
			String additionalParam = helper.channel.additionalParam;
			if (!additionalParam.contains(helper.billPayment.selectOperator)) {
				result.put("status", "1");
				result.put("message", "Invalid parameter bill payment ["+helper.billPayment.selectOperator+"]");
				return Results.ok(result);
			}
				
			Utility.processFormFund(helper);
			Utility.processFormFee(helper);

			// Start to generate and build Response
			ObjectNode inquiry = Json.newObject();
			inquiry.put("idToken", helper.idToken);
			BigDecimal additionalFee = new BigDecimal(0);
			
			ObjectNode fund = Json.newObject();
			ObjectNode fundOrigin = Json.newObject();
			fundOrigin.put("amount", helper.senderAmount);
			fundOrigin.put("currency", helper.senderCurrency.code);
			fund.put("origin", fundOrigin);

			ObjectNode fundDestination = Json.newObject();
			fundDestination.put("amount", helper.beneficiaryAmount);
			fundDestination.put("currency", helper.beneficiaryCurrency.code);
			fund.put("destination", fundDestination);

			ObjectNode fundFees = Json.newObject();
			ArrayNode fundFeesComponents = Json.newObject().arrayNode();
			BigDecimal total = new BigDecimal(0);
			for (TransactionFee fee : helper.fees) {
				ObjectNode feeNode = Json.newObject();
				feeNode.put("description", fee.description);
				feeNode.put("amount", fee.amount);
				fundFeesComponents.add(feeNode);
				total = total.add(fee.amount);
				additionalFee = additionalFee.add(fee.additionalFee);
			}
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
			trxInq.channel = helper.channel;
			trxInq.save();
			String inquiryCode = TransactionInquiry.formatAsCode(trxInq);
			inquiry.put("idToken", inquiryCode);
			
			Logger.debug("billPaymentService     : "+helper.billPayment.billPaymentService);
			Logger.debug("selectBill             : "+helper.billPayment.selectBill);
			Logger.debug("selectOperator         : "+helper.billPayment.selectOperator);
			Logger.debug("accountNumber          : "+helper.billPayment.accountNumber);
			Logger.debug("systrace               : "+trxInq.id);
			helper.trackingId = trxInq.id.toString();
			helper.idToken = inquiryCode;
			TransferResponse responseInquiry = Utility.fundInquiryBillPayment(helper);
			if (responseInquiry.referenceStatus == null || !responseInquiry.referenceStatus.equals("0000")) {
				result.put("status", "1");
				result.put("message", responseInquiry.referenceMessage);
			} else {
				objectMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				objectMapper.configure(Feature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
				BillPaymentInquiryResponse billPaymentInquiryResponse = (BillPaymentInquiryResponse) objectMapper.readValue(responseInquiry.referenceMessage, BillPaymentInquiryResponse.class);
				if (helper.billPayment.selectOperator.equals("9950102")) {
					billPaymentInquiryResponse.billdetails = new ArrayList<BillDetail>();
					List<BillDetailDescription> billDetailDescriptions1 = new ArrayList<BillDetailDescription>();
					billDetailDescriptions1.add(new BillDetailDescription("DENOM", "20000"));
					billPaymentInquiryResponse.billdetails.add(new BillDetail(adminFeePln, "1", "360", null, billDetailDescriptions1, null, "20 Ribu", (20000.00+adminFeePln)));
					List<BillDetailDescription> billDetailDescriptions2 = new ArrayList<BillDetailDescription>();
					billDetailDescriptions2.add(new BillDetailDescription("DENOM", "50000"));
					billPaymentInquiryResponse.billdetails.add(new BillDetail(adminFeePln, "1", "360", null, billDetailDescriptions2, null, "50 Ribu", (50000.00+adminFeePln)));
					List<BillDetailDescription> billDetailDescriptions3 = new ArrayList<BillDetailDescription>();
					billDetailDescriptions3.add(new BillDetailDescription("DENOM", "100000"));
					billPaymentInquiryResponse.billdetails.add(new BillDetail(adminFeePln, "1", "360", null, billDetailDescriptions3, null, "100 Ribu", (100000.00+adminFeePln)));
					List<BillDetailDescription> billDetailDescriptions4 = new ArrayList<BillDetailDescription>();
					billDetailDescriptions4.add(new BillDetailDescription("DENOM", "200000"));
					billPaymentInquiryResponse.billdetails.add(new BillDetail(adminFeePln, "1", "360", null, billDetailDescriptions4, null, "200 Ribu", (200000.00+adminFeePln)));
					List<BillDetailDescription> billDetailDescriptions5 = new ArrayList<BillDetailDescription>();
					billDetailDescriptions5.add(new BillDetailDescription("DENOM", "500000"));
					billPaymentInquiryResponse.billdetails.add(new BillDetail(adminFeePln, "1", "360", null, billDetailDescriptions5, null, "500 Ribu", (500000.00+adminFeePln)));
					List<BillDetailDescription> billDetailDescriptions6 = new ArrayList<BillDetailDescription>();
					billDetailDescriptions6.add(new BillDetailDescription("DENOM", "1000000"));
					billPaymentInquiryResponse.billdetails.add(new BillDetail(adminFeePln, "1", "360", null, billDetailDescriptions6, null, "1 Juta", (1000000.00+adminFeePln)));
				}
				
				for (BillDetail billDetail : billPaymentInquiryResponse.billdetails) {
					BigDecimal totalAmount = BigDecimal.valueOf(billDetail.totalamount);
					totalAmount = totalAmount.add(additionalFee);
					billDetail.totalamount = totalAmount.doubleValue();
					billDetail.originTotalAmount = ForexHelper.convertOriginCurrency(totalAmount, helper.forexRef).setScale(6, RoundingMode.FLOOR).doubleValue();
				}
				
				result.put("billPayment", Json.toJson(billPaymentInquiryResponse));
				
				TransactionInquiry transactionInquiry = TransactionInquiry.find.byId(trxInq.id);
				transactionInquiry.accountType = helper.channel.code;
				transactionInquiry.accountName = helper.billPayment.selectOperator;
				transactionInquiry.validationId = helper.trackingId;
				transactionInquiry.beneficiaryAmount = BigDecimal.ZERO;
				transactionInquiry.collectId = billPaymentInquiryResponse.inquiry_ID;
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
			BigDecimal amountBill = new BigDecimal(helper.billPayment.amount);
			amountBill = amountBill.subtract(additionalFee);
			BigDecimal beneficiaryAmount = amountBill;
			
			trx.senderAmount = helper.senderAmount;
			trx.beneficiary = helper.beneficiary;
			trx.beneficiaryCountry = helper.beneficiaryCountry;
			trx.beneficiaryCurrency = helper.beneficiaryCurrency;
			trx.beneficiaryAmount = beneficiaryAmount;
			trx.forexReference = helper.forexReference;
			trx.beneficiaryCity = helper.beneficiaryCity;
			trx.cashInTime = Calendar.getInstance();
			trx.status = Transaction.STATUS_PENDING;
			trx.senderNote = "Beli Pulsa & Bayar Tagihan";
			trx.sendTrxId = helper.sendTrxId;
			trx.auth1 = helper.auth1;
			if (helper.cashInTime != null) {
				trx.cashInTime.setTime(helper.cashInTime);
			}
			trx.paymentData = Json.toJson(helper.billPayment).toString();
			trx.status = models.Transaction.STATUS_PENDING;
			
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
			helper.inquiry.accountId = helper.billPayment.accountNumber;
			helper.inquiry.accountType = helper.billPayment.selectDenom;
			helper.inquiry.beneficiaryAmount = amountBill;
			helper.inquiry.save();

			Utility.fundTransferBillPayment(trx);
			
			// create remit result json object
			ObjectNode remit = Json.newObject();
			remit.put("paymentData", Json.parse(trx.paymentData));
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result CashOutValidate(CashOutValidateHelper helper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result CashOutCollect(CashOutCollectHelper helper) {
		// TODO Auto-generated method stub
		return null;
	}

}
