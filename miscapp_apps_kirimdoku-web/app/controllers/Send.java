package controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import be.objectify.deadbolt.java.actions.Restrictions;
import be.objectify.deadbolt.java.actions.And;
import controllers.helpers.ApiHelper;
import controllers.helpers.SessionHelper;
import controllers.helpers.ApiHelper.ApiException;
import models.Channel;
import models.Corporate;
import models.Customer;
import models.Corporate.CorporateStatistic;
import models.CustomerAccount;
import models.CustomerRelate;
import models.TransactionInquiry;
import models.forms.CashInForm;
import models.forms.CashInInquiryForm;

import org.apache.commons.mail.EmailException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.node.ObjectNode;

import com.avaje.ebean.Page;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.api.templates.Html;
import play.libs.Json;
import play.libs.WS.Response;
import views.html.send.*;

@Security.Authenticated(SecuredMain.class)
@Restrictions({ @And("mainagent"), @And("supervisor"), @And("operator") })
public class Send extends Controller {

	public static Result create() {
		String token = UUID.randomUUID().toString();
		SessionHelper.putToken(token);
		Logger.debug("Tracking ID : "+token);
		
		Form<models.forms.CashInForm> transactionForm = form(models.forms.CashInForm.class).bindFromRequest(request());
		Logger.debug("Send create request : "+transactionForm.data());
		if(transactionForm.data().isEmpty()) {
			CashInForm fillForm = new CashInForm();
			fillForm.senderCountry = SessionHelper.getUser().corporate.country;
			fillForm.senderCurrency = SessionHelper.getUser().corporate.currency;
			transactionForm = transactionForm.fill(fillForm);
		}
		
		models.Corporate corporate = SessionHelper.getUser().corporate;
		corporate.refresh();
		CorporateStatistic cs = corporate.getStatistic();
		if(cs.hasExceedCreditLimit()) {
			flash("error", "YOUR CREDIT LEFT HAS REACH ITS LIMIT, PLEASE CONTACT YOUR FINANCE FOR SETTLEMENT.");
		} else if(cs.hasExceedCreditAlertLimit()) {
			flash("warning", "YOUR CREDIT LEFT HAS REACH ALERT LIMIT, PLEASE CONTACT YOUR FINANCE FOR SETTLEMENT.");
		}
		
		String channelCode = "";
		
		transactionForm.get().trackingId = token;
		
		TreeMap<String, String> mapChannel = new TreeMap<String, String>();
		try{
			channelCode = corporate.configuration.channelCode;
			
			if (channelCode != null && !channelCode.trim().equals("")) {
				Object[] channel = channelCode.split(";");
				List<Channel> channels = null;
				channels = Channel.find.where().in("code", channel).orderBy("code").findList();
				for (Iterator<Channel> iterator = channels.iterator(); iterator.hasNext();) {
					Channel channelItem = (Channel) iterator.next();
					if (!channelItem.code.equals("10"))
					mapChannel.put(channelItem.code, channelItem.name);
				}
			}
		} catch (Exception e) {
			channelCode = "empty";
		}
		
		if (channelCode == null || channelCode == "") channelCode = "empty";
		return ok(create.render(transactionForm, channelCode, mapChannel));
	}
	
	public static Result createBillPayment() {
		String token = UUID.randomUUID().toString();
		SessionHelper.putToken(token);
		Logger.debug("Tracking ID : "+token);
		
		Form<models.forms.CashInForm> transactionForm = form(models.forms.CashInForm.class).bindFromRequest(request());
		Logger.debug("Send create bill payment request : "+transactionForm.data());
		if(transactionForm.data().isEmpty()) {
			CashInForm fillForm = new CashInForm();
			fillForm.senderCountry = SessionHelper.getUser().corporate.country;
			fillForm.senderCurrency = SessionHelper.getUser().corporate.currency;
			transactionForm = transactionForm.fill(fillForm);
		}
		
		models.Corporate corporate = SessionHelper.getUser().corporate;
		corporate.refresh();
		CorporateStatistic cs = corporate.getStatistic();
		if(cs.hasExceedCreditLimit()) {
			flash("error", "YOUR CREDIT LEFT HAS REACH ITS LIMIT, PLEASE CONTACT YOUR FINANCE FOR SETTLEMENT.");
		} else if(cs.hasExceedCreditAlertLimit()) {
			flash("warning", "YOUR CREDIT LEFT HAS REACH ALERT LIMIT, PLEASE CONTACT YOUR FINANCE FOR SETTLEMENT.");
		}
		
		transactionForm.get().trackingId = token;
		String channelCode = "";
		
		TreeMap<String, String> mapChannel = new TreeMap<String, String>();
		try{
			channelCode = corporate.configuration.channelCode;
			
			if (channelCode != null && !channelCode.trim().equals("")) {
				Object[] channel = channelCode.split(";");
				List<Channel> channels = null;
				channels = Channel.find.where().in("code", channel).orderBy("code").findList();
				for (Iterator<Channel> iterator = channels.iterator(); iterator.hasNext();) {
					Channel channelItem = (Channel) iterator.next();
					if (channelItem.code.equals("10")) {
						mapChannel.put(channelItem.code, channelItem.name);
						transactionForm.get().channel = channelItem;
					}
				}
			}
		} catch (Exception e) {
			channelCode = "empty";
		}
		
		if (channelCode == null || channelCode == "") channelCode = "empty";
		return ok(create_bill_payment.render(transactionForm, channelCode, mapChannel));
	}
	
	public static Result inquiryBillPayment() {
		Logger.debug("INQUIRY BILL PAYMENT");
		Form<models.forms.CashInInquiryForm> transactionForm = form(models.forms.CashInInquiryForm.class).bindFromRequest();
		System.out.println("INQUIRY BILL PAYMENT REQUEST : "+transactionForm.data());
		if(transactionForm.hasErrors()) {
			return forbidden(transactionForm.errorsAsJson());
		}
		
		CashInInquiryForm inqForm = transactionForm.get();
		
		JsonNode result = ApiHelper.CashIn.inquiry(inqForm);
		return ok(result);
	}
 	
	public static Result inquiry() {
		Logger.debug("INQUIRY");
		Form<models.forms.CashInInquiryForm> transactionForm = form(models.forms.CashInInquiryForm.class).bindFromRequest();
		
		if(transactionForm.hasErrors()) {
			return forbidden(transactionForm.errorsAsJson());
		}
		
		CashInInquiryForm inqForm = transactionForm.get();
		
		if("senderAmount".equals(inqForm.sendType)) {
			inqForm.beneficiaryAmount = null;
		}
		if("beneficiaryAmount".equals(inqForm.sendType)) {
			inqForm.senderAmount = null;
		}
		
		JsonNode result = ApiHelper.CashIn.inquiry(inqForm);
		return ok(result);
	}
	
	
	public static Result customerLookup() {
		DynamicForm form = new DynamicForm().bindFromRequest();
		try {
			String idToken = null;
			if((form.get("sender.idToken") != null) && (!form.get("sender.idToken").isEmpty())) {
				idToken = String.valueOf(form.get("sender.idToken"));
			} else if((form.get("beneficiary.idToken") != null) && (!form.get("beneficiary.idToken").isEmpty())) {
				idToken = String.valueOf(form.get("beneficiary.idToken"));
			} else if((form.get("idToken") != null) && (!form.get("idToken").isEmpty())) {
				idToken = String.valueOf(form.get("idToken"));
			} else {
				Customer customer = null;
				if(form.get("sender.idToken") != null) {
					SimpleDateFormat dateFormatShow = new SimpleDateFormat("yyyy-MM-dd");
					customer = models.Customer.findByMatch(form.get("sender.country.code"), form.get("sender.firstName"), form.get("sender.lastName"), dateFormatShow.parse(form.get("sender.birthDate")));
				} else if(form.get("beneficiary.idToken") != null) {
					customer = models.Customer.findByMatch(form.get("beneficiary.country.code"), form.get("beneficiary.firstName"), form.get("beneficiary.lastName"), null);
				}
				if(customer != null) {
					idToken = customer.getIdToken();
				}
			}
			if((idToken != null) && (!idToken.isEmpty())) {
				Response response = ApiHelper.Customer.fetchCustomer(idToken);
				if((response != null) && (response.getStatus() == Http.Status.OK)) {
					return ok(response.asJson());
				}
			} else {
				// handle search for other or what?
				Logger.debug("Handler search customer? "+form.get().getData());
			}
			return notFound();
		} catch(Exception e) {
			e.printStackTrace();
			return badRequest(e.getMessage());
		}
	}
	
	public static Result customerLookupRelate() {
		DynamicForm form = new DynamicForm().bindFromRequest();
		try {
			String idToken = null;
			Customer customer = null;
			if((form.get("sender.idToken") != null) && (!form.get("sender.idToken").isEmpty())) {
				idToken = String.valueOf(form.get("sender.idToken"));
				
				if(form.get("sender.idToken") != null) {
					SimpleDateFormat dateFormatShow = new SimpleDateFormat("yyyy-MM-dd");
					customer = models.Customer.findByToken(idToken);
				} 
				if(customer != null) {
					idToken = customer.getIdToken();
				}
			}
			if((idToken != null) && (!idToken.isEmpty())) {
//				Response response = ApiHelper.Customer.fetchCustomer(idToken);
//				if((response != null) && (response.getStatus() == Http.Status.OK)) {
//					return ok(response.asJson());
//				}
				
				ObjectNode result = Json.newObject();
				result.put("status", 0);
				result.put("message", "Customer info success");
				result.put("customer", Json.toJson(customer));
				
				List<Customer> list = CustomerRelate.getCustomersRelateByCustomerId(customer);
				
				result.put("customerReceiver", Json.toJson(list));
				
				return ok(result);
			} else {
				// handle search for other or what?
				Logger.debug("Handler search customer? "+form.get().getData());
			}
			return notFound();
		} catch(Exception e) {
			e.printStackTrace();
			return badRequest(e.getMessage());
		}
	}
	
	public static Result verify() {
		Form<models.forms.CashInForm> transactionForm = form(models.forms.CashInForm.class).bindFromRequest(request());
		if(transactionForm.hasErrors()) {
			return ok(verify.render("error", "You have form validation errors, please recheck your forms again "+transactionForm.errors()));
		}
		
		if((transactionForm.get().senderAmount != null) && (transactionForm.get().senderAmount.doubleValue() > SessionHelper.getUser().corporate.customerSendLimit.doubleValue())) {
			return ok(verify.render("error", "Sending amount exceeding sending limit, please proceed with KYC process and contact your supervisor"));
		}
		models.Corporate corporate = SessionHelper.getUser().corporate;

		if((transactionForm.get().sender.idToken != null) && (!transactionForm.get().sender.idToken.isEmpty())) {
			models.Customer sender = models.Customer.findByToken(transactionForm.get().sender.idToken);
			if(sender != null) {
				models.CustomerBan ban1 = models.CustomerBan.findByCustomer(sender);
				if(ban1 != null) {
					return ok(verify.render("error", "Sender customer "+ban1.customer.fullName()+" is in banned list, please contact your supervisor"));
				}
			}
			if ((corporate.configuration != null) && (sender.getStatistic(1) != null)) {
				Logger.debug("HVT           : "+corporate.configuration.allowHighValueTransfer);
				Logger.debug("Sender Amount : "+transactionForm.get().senderAmount);
				Logger.debug("Statistic     : "+sender.getStatistic(1).hasExceedSendLimit(transactionForm.get().senderAmount));
				if((!corporate.configuration.allowHighValueTransfer) && sender.getStatistic(1).hasExceedSendLimit(transactionForm.get().senderAmount)) {
					return ok(verify.render("error", "Sender customer "+sender.fullName()+" has exceeded its customer limit, please contact your supervisor"));
				}
			}
		} else {
			if ((corporate.configuration != null) && (transactionForm.get().sender.getStatistic(1) != null)) {
				if((!corporate.configuration.allowHighValueTransfer) && transactionForm.get().sender.getStatistic(1).hasExceedSendLimit(transactionForm.get().senderAmount)) {
					return ok(verify.render("error", "Sender customer "+transactionForm.get().sender.fullName()+" has exceeded its customer limit, please contact your supervisor"));
				}
			}
			
		}
		if (true) {
			//Logger.debug("Transaction check ban " + transactionForm.get().sender.birthDate);
			models.CustomerBan ban2 = models.CustomerBan.findPositiveMatch(transactionForm.get().sender);
			if(ban2 != null) {
				return ok(verify.render("error", "Sender with a name "+ban2.firstName+" "+ban2.lastName+" is an positive match, please contact your supervisor"));
			}
		}
		
//		//TODO Verify transaction validation
//		if(transactionForm.get().sender.personalIdIssueDate.after(transactionForm.get().sender.personalIdExpireDate)) {
//			return ok(verify.render("error", "Sender ID issue issue/expired date shall be correct (within justified range and expired date shall no less date than issue date)"));
//		}
		return ok();
	}
	
	public static Result confirm() {
		Form<models.forms.CashInForm> transactionForm = form(models.forms.CashInForm.class).bindFromRequest(request());
		String err = null;
	
		if (transactionForm.hasErrors()) {
			Logger.debug("Transaction form errors: " + transactionForm.errorsAsJson());
			return ok(confirm.render("Transaction information is invalid", null));
		}
		
		String tokenRequest = transactionForm.get().trackingId;
		String token = SessionHelper.getToken();
		Logger.debug("Request param token : "+tokenRequest+" >< "+token);
		SessionHelper.removeToken();
		if (!token.equals(tokenRequest)) {
			Logger.debug("Invalid Token Request, route to send form!!!");
			return redirect(routes.Send.create());
		}
		
		transactionForm.get().inquiry = new TransactionInquiry();
		transactionForm.get().inquiry.idToken = transactionForm.data().get("idToken") != null ? transactionForm.data().get("idToken") : "";
		models.User user = SessionHelper.getUser();
		transactionForm.get().supervisor = user.supervisor;
		if((transactionForm.get().sender.idToken != null) && (!transactionForm.get().sender.idToken.isEmpty())) {
			models.Customer sender = models.Customer.findByToken(transactionForm.get().sender.idToken);
			if(sender.getStatistic(1).hasExceedSendLimit(transactionForm.get().senderAmount)) {
				//flash("error", "Sender customer "+sender.fullName()+" has exceeded its customer limit");
				err = "Sender customer "+sender.fullName()+" has exceeded its customer limit";
			}
		}
		
		if(SessionHelper.getUser().agentHasExceedSendLimit(transactionForm.get().senderAmount)) {
			err = "Sender agent has exceeded its credit limit";
		}
		
		/*if((transactionForm.get().beneficiary.idToken != null) && (!transactionForm.get().beneficiary.idToken.isEmpty())) {
			models.Customer beneficiary = models.Customer.findByToken(transactionForm.get().beneficiary.idToken);
			if(beneficiary.getStatistic().hasExceedBeneficiaryLimit(transactionForm.get().beneficiaryAmount)) {
				flash("warning", "Receiver customer "+beneficiary.fullName()+" has exceeded its customer limit "+beneficiary.getStatistic().totalBeneficiaryAmount);
			}
		}*/
		
		if (transactionForm.get().channel.code.equals("03")) {
			Logger.debug("inputTCODE    : "+transactionForm.get().beneficiary.tcode);
			Logger.debug("inputMode	    : "+transactionForm.get().beneficiary.inputMode);
			Logger.debug("inputPINYIN   : "+transactionForm.get().beneficiary.pinyin);
			String inputTCODE = transactionForm.get().beneficiary.tcode;
			while (!inputTCODE.endsWith(";") && inputTCODE.length() > 0) {
				inputTCODE = inputTCODE.substring(0,inputTCODE.length()-1);
			}
			transactionForm.data().put("beneficiary.tcode",inputTCODE);
			
			String inputPINYIN = transactionForm.get().beneficiary.pinyin;
			while (!inputPINYIN.endsWith(";") && inputPINYIN.length() > 0) {
				inputPINYIN = inputPINYIN.substring(0,inputPINYIN.length()-1);
			}
			transactionForm.data().put("beneficiary.pinyin",inputPINYIN);
			
			Logger.debug("inputTCODE    : "+inputTCODE);
			Logger.debug("inputPINYIN   : "+inputPINYIN);
			
		} else if (transactionForm.get().channel.code.equals("04")) {
			CashInInquiryForm inqForm = new CashInInquiryForm();
			inqForm.sendType = transactionForm.get().sendType;
			if (transactionForm.get().sendType.equals("beneficiaryAmount")) {
				inqForm.senderAmount = null;
				inqForm.beneficiaryAmount = transactionForm.get().beneficiaryAmount.add(transactionForm.get().additionalFee);
			} else if (transactionForm.get().sendType.equals("senderAmount")) {
				inqForm.beneficiaryAmount = null;
				inqForm.senderAmount = transactionForm.get().senderAmount;
			}
			
			inqForm.beneficiaryCountry = transactionForm.get().beneficiaryCountry;
			inqForm.beneficiaryCurrency = transactionForm.get().beneficiaryCurrency;
			inqForm.channel = transactionForm.get().channel;
			
			inqForm.senderCountry = transactionForm.get().senderCountry;
			inqForm.senderCurrency = transactionForm.get().senderCurrency;
			
			inqForm.activity = 1; //DO_INQUIRY
			inqForm.beneficiaryWalletId = transactionForm.get().beneficiaryWalletId;
			JsonNode result = ApiHelper.CashIn.inquiry(inqForm);
			Logger.debug("RESULT : "+result.toString());
			if (result.get("status") != null && result.get("status").toString().equals("0")) {
				if (!result.get("inquiry").get("beneficiaryWalletName").toString().equals("null")) {
					transactionForm.get().beneficiaryWalletName = result.get("inquiry").get("beneficiaryWalletName").getTextValue();
					transactionForm.get().inquiry.idToken = result.get("inquiry").get("idToken").getTextValue();
				} else {
					err = "INQUIRY DOKU WALLET FAILED";
				}
			} else {
				err = "Transfer Wallet : "+result.get("message").getTextValue();
			}
		} else if (transactionForm.get().channel.code.equals("06") && transactionForm.get().beneficiaryAccount != null && !transactionForm.get().beneficiaryAccount.bank.code.equals("BNIAIDJA")) {
			try {
				CashInInquiryForm inqForm = new CashInInquiryForm();
				inqForm.sendType = transactionForm.get().sendType;
				if (transactionForm.get().sendType.equals("beneficiaryAmount")) {
					inqForm.senderAmount = null;
					inqForm.beneficiaryAmount = transactionForm.get().beneficiaryAmount.add(transactionForm.get().additionalFee);
				} else if (transactionForm.get().sendType.equals("senderAmount")) {
					inqForm.beneficiaryAmount = null;
					inqForm.senderAmount = transactionForm.get().senderAmount;
				}
				
				inqForm.beneficiaryCountry = transactionForm.get().beneficiaryCountry;
				inqForm.beneficiaryCurrency = transactionForm.get().beneficiaryCurrency;
				inqForm.channel = transactionForm.get().channel;
				
				inqForm.senderCountry = transactionForm.get().senderCountry;
				inqForm.senderCurrency = transactionForm.get().senderCurrency;
				
				inqForm.activity = 1; //DO_INQUIRY
				inqForm.beneficiaryAccount = transactionForm.get().beneficiaryAccount;
				JsonNode result = ApiHelper.CashIn.inquiry(inqForm);
				Logger.debug("RESULT : "+result.toString());
				if (result.get("status") != null && result.get("status").toString().equals("0")) {
					ObjectMapper objectMapper = new ObjectMapper();
					objectMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
					objectMapper.configure(Feature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
				
					Logger.debug("Customer Account : "+result.get("inquiry").get("beneficiaryAccount").toString());
					CustomerAccount beneficiaryAccount = (CustomerAccount) objectMapper.readValue(result.get("inquiry").get("beneficiaryAccount").toString(), CustomerAccount.class);
					transactionForm.get().beneficiaryAccount = beneficiaryAccount;
					transactionForm.get().inquiry.idToken = result.get("inquiry").get("idToken").getTextValue();
				} else {
					err = "Transfer "+transactionForm.get().channel.name+" : "+result.get("message").getTextValue();
				}
			} catch (JsonParseException e) {
				Logger.debug("JsonParseException : "+e.getMessage());
			} catch (JsonMappingException e) {
				Logger.debug("JsonMappingException : "+e.getMessage());
			} catch (IOException e) {
				Logger.debug("IOException : "+e.getMessage());
			}
		} else if (transactionForm.get().channel.code.equals("07") && transactionForm.get().beneficiaryAccount != null) {
			try {
				CashInInquiryForm inqForm = new CashInInquiryForm();
				inqForm.sendType = transactionForm.get().sendType;
				if (transactionForm.get().sendType.equals("beneficiaryAmount")) {
					inqForm.senderAmount = null;
					inqForm.beneficiaryAmount = transactionForm.get().beneficiaryAmount.add(transactionForm.get().additionalFee);
				} else if (transactionForm.get().sendType.equals("senderAmount")) {
					inqForm.beneficiaryAmount = null;
					inqForm.senderAmount = transactionForm.get().senderAmount;
				}
				
				inqForm.sender = transactionForm.get().sender;
				
				inqForm.beneficiaryCountry = transactionForm.get().beneficiaryCountry;
				inqForm.beneficiaryCurrency = transactionForm.get().beneficiaryCurrency;
				inqForm.channel = transactionForm.get().channel;
				
				inqForm.senderCountry = transactionForm.get().senderCountry;
				inqForm.senderCurrency = transactionForm.get().senderCurrency;
				
				inqForm.activity = 1; //DO_INQUIRY
				inqForm.beneficiaryAccount = transactionForm.get().beneficiaryAccount;
				inqForm.beneficiaryAccount.name = transactionForm.get().beneficiary.fullName();
				JsonNode result = ApiHelper.CashIn.inquiry(inqForm);
				Logger.debug("RESULT : "+result.toString());
				if (result.get("status") != null && result.get("status").toString().equals("0")) {
					ObjectMapper objectMapper = new ObjectMapper();
					objectMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
					objectMapper.configure(Feature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
				
					Logger.debug("Customer Account : "+result.get("inquiry").get("beneficiaryAccount").toString());
					CustomerAccount beneficiaryAccount = (CustomerAccount) objectMapper.readValue(result.get("inquiry").get("beneficiaryAccount").toString(), CustomerAccount.class);
					
					transactionForm.get().beneficiaryAccount = beneficiaryAccount;
					transactionForm.get().inquiry.idToken = result.get("inquiry").get("idToken").getTextValue();
				} else {
					err = "Transfer "+transactionForm.get().channel.name+" : "+result.get("message").getTextValue();
				}
			} catch (JsonParseException e) {
				Logger.debug("JsonParseException : "+e.getMessage());
			} catch (JsonMappingException e) {
				Logger.debug("JsonMappingException : "+e.getMessage());
			} catch (IOException e) {
				Logger.debug("IOException : "+e.getMessage());
			}
		}
		
		return ok(confirm.render(err, transactionForm));
	}
	
	public static Result confirmBillPayment() {
		Form<models.forms.CashInForm> transactionForm = form(models.forms.CashInForm.class).bindFromRequest(request());
		String err = null;
	
		if (transactionForm.hasErrors()) {
			Logger.debug("Transaction form errors: " + transactionForm.errorsAsJson());
			return ok(confirm.render("Transaction information is invalid", null));
		}
		
		String tokenRequest = transactionForm.get().trackingId;
		String token = SessionHelper.getToken();
		Logger.debug("Request param token : "+tokenRequest+" >< "+token);
		SessionHelper.removeToken();
		if (!token.equals(tokenRequest)) {
			Logger.debug("Invalid Token Request, route to send form!!!");
			return redirect(routes.Send.createBillPayment());
		}
		
		transactionForm.get().inquiry = new TransactionInquiry();
		transactionForm.get().inquiry.idToken = transactionForm.data().get("idToken") != null ? transactionForm.data().get("idToken") : "";
		models.User user = SessionHelper.getUser();
		transactionForm.get().supervisor = user.supervisor;
		if((transactionForm.get().sender.idToken != null) && (!transactionForm.get().sender.idToken.isEmpty())) {
			models.Customer sender = models.Customer.findByToken(transactionForm.get().sender.idToken);
			if(sender.getStatistic(1).hasExceedSendLimit(transactionForm.get().senderAmount)) {
				//flash("error", "Sender customer "+sender.fullName()+" has exceeded its customer limit");
				err = "Sender customer "+sender.fullName()+" has exceeded its customer limit";
			}
		}
		
		if(SessionHelper.getUser().agentHasExceedSendLimit(transactionForm.get().senderAmount)) {
			err = "Sender agent has exceeded its credit limit";
		}
		
		if (transactionForm.get().channel.code.equals("10")) {
			Corporate corporate = Corporate.find.byId(transactionForm.get().corporate.code);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date birthDate = null;
			try {
				birthDate = simpleDateFormat.parse("1980-01-01");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Customer customerSender = null;
			Customer customerBeneficiary = null;
			try {
				customerSender = Customer.find.where()
						.eq("firstName", corporate.name)
						.eq("lastName", "SENDER")
						.eq("phoneNumber", corporate.phoneNumber)
						.eq("personalIdType", "OTHER")
						.eq("personalId", corporate.licenseNumber)
						.eq("birthDate", birthDate).setMaxRows(1).findUnique();
				System.out.println("SENDER : "+Json.toJson(customerSender));
				customerBeneficiary = Customer.find.where()
						.eq("firstName", corporate.name)
						.eq("lastName", "BENEFICIARY")
						.eq("phoneNumber", corporate.phoneNumber)
						.eq("personalIdType", "OTHER")
						.eq("personalId", corporate.licenseNumber)
						.eq("birthDate", birthDate).setMaxRows(1).findUnique();
				System.out.println("BENEFICIARY : "+Json.toJson(customerBeneficiary));
			} catch (Exception e) {
				
			}
			String customerIdSender = customerSender != null ? customerSender.idToken : "";
			String customerIdBeneficiary = customerBeneficiary != null ? customerBeneficiary.idToken : "";
			System.out.println("SENDER ID : "+customerIdSender);
			System.out.println("BENEFICIARY ID : "+customerIdBeneficiary);
			transactionForm.get().sender.idToken = customerIdSender;
			transactionForm.data().put("sender.firstName",corporate.name);
			transactionForm.data().put("sender.lastName","SENDER");
			transactionForm.data().put("sender.phoneNumber",corporate.phoneNumber);
			transactionForm.data().put("sender.country.code",corporate.country.code);
			transactionForm.data().put("sender.personalIdType","OTHER");
			transactionForm.data().put("sender.personalId",corporate.licenseNumber);
			transactionForm.data().put("sender.birthDate","1980-01-01");
			
			transactionForm.get().beneficiary.idToken = customerIdBeneficiary;
			transactionForm.data().put("beneficiary.firstName",corporate.name);
			transactionForm.data().put("beneficiary.lastName", "BENEFICIARY");
			transactionForm.data().put("beneficiary.phoneNumber",corporate.phoneNumber);
			transactionForm.data().put("beneficiary.country.code",corporate.country.code);
			transactionForm.data().put("beneficiary.personalIdType","OTHER");
			transactionForm.data().put("beneficiary.personalId",corporate.licenseNumber);
			transactionForm.data().put("beneficiary.birthDate","1980-01-01");
			Integer denom = Integer.parseInt(transactionForm.get().billPayment.selectDenom)+1;
			transactionForm.data().put("billPayment.selectDenom", denom.toString());
			System.out.println("SENDER ID : "+transactionForm.get().sender.idToken);
			System.out.println("BENEFICIARY ID : "+transactionForm.get().beneficiary.idToken);
		}
		
		return ok(confirm_bill_payment.render(err, transactionForm));
	}

	public static Result process() {
		Logger.debug("INQUIRY");
		String err = "";
		try {
			Form<CashInForm> transactionForm = form(CashInForm.class).bindFromRequest();
	
			if (transactionForm.hasErrors()) {
				err = "Transaction information is invalid";
				Logger.debug("Transaction form errors: " + transactionForm.errorsAsJson());
				return badRequest(transactionForm.errorsAsJson());
			}
			Logger.debug("transactionForm : "+transactionForm.data());

			JsonNode result = ApiHelper.CashIn.remit(transactionForm.get());
			if (result.path("status").getIntValue() == 0) {
				String id = result.path("remit").path("transactionId").getTextValue();
				
				if (result.has("warning")) {
					flash("warning", result.path("warning").getTextValue());
				}
				
				return redirect(routes.Send.receipt(id));
			} else {
				err = result.path("message").getTextValue();
				Logger.error("Server response failed : "+result.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			err = e.getMessage();
			Logger.error("Exception " + e.getMessage());
		}
		return ok(process.render(err, null));
	}
	
	public static Result processBillPayment() {
		Logger.debug("PROCESS BILL PAYMENT");
		String err = "";
		try {
			Form<CashInForm> transactionForm = form(CashInForm.class).bindFromRequest();
	
			if (transactionForm.hasErrors()) {
				err = "Transaction information is invalid";
				Logger.debug("Transaction form errors: " + transactionForm.errorsAsJson());
				return badRequest(transactionForm.errorsAsJson());
			}
			Logger.debug("transactionForm : "+transactionForm.data());

			JsonNode result = ApiHelper.CashIn.remit(transactionForm.get());
			if (result.path("status").getIntValue() == 0) {
				String id = result.path("remit").path("transactionId").getTextValue();
				
				if (result.has("warning")) {
					flash("warning", result.path("warning").getTextValue());
				}
				
				Logger.debug("Response : "+result.toString());
				return redirect(routes.Send.receiptBillPayment(id));
			} else {
				err = result.path("message").getTextValue();
				Logger.error("Server response failed : "+result.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			err = e.getMessage();
			Logger.error("Exception " + e.getMessage());
		}
		return ok(process.render(err, null));
	}
	
	public static Result receipt(String idToken) {
		try {
			models.Transaction transaction = ApiHelper.getTransaction(idToken);
			transaction.senderAmount.setScale(6, RoundingMode.FLOOR);
			transaction.beneficiaryAmount.setScale(6, RoundingMode.FLOOR);
			return ok(receipt.render(null, transaction));
		} catch (ApiException e) {
			return ok(receipt.render(e.getMessage(), null));
		}
	}
	
	public static Result receiptBillPayment(String idToken) {
		try {
			models.Transaction transaction = ApiHelper.getTransaction(idToken);
			transaction.senderAmount.setScale(6, RoundingMode.FLOOR);
			transaction.beneficiaryAmount.setScale(6, RoundingMode.FLOOR);
			return ok(receipt_bill_payment.render(null, transaction));
		} catch (ApiException e) {
			return ok(receipt_bill_payment.render(e.getMessage(), null));
		}
	}
	
	public static Result receiptPrint(String id) {
		JsonNode result = ApiHelper.transactionDetail(id);
		return ok(receipt_print.render(result));
	}

	public static Result receiptEmail(String id) {
		JsonNode result = ApiHelper.transactionDetail(id);
		return ok(receipt_email.render(result));
	}

	public static Result summary() {
		Form<CashInForm> transactionForm = form(CashInForm.class).bindFromRequest();

		if (transactionForm.hasErrors()) {
			Logger.debug("Sum Transaction form errors: " + transactionForm.errorsAsJson());
			// Skip validations and leave it to API
		}

		try {
			JsonNode result = ApiHelper.CashIn.inquiry(transactionForm.get());

			return ok(summary.render(transactionForm, result));
		} catch (Exception e) {
			// TODO: An all internal engine exception/error
			Logger.error("Exception " + e.getMessage());
			return badRequest("Unable to build summary request");
		}
	}

	/**
	 * Quick helper to search customer based on the customerid/name and return
	 * quick html
	 */
	public static Result customerSearch(String query) {
		Page<models.Customer> customers = models.Customer.findByQuickSearch(query);

		return ok(customer_search.render(customers));
	}

	public static Result customerNew() {
		Form<models.Customer> form = form(models.Customer.class);
		return ok(create_customer.render(form, false, null));
	}

	public static Result customerEdit(String id) {
		// TODO: Convert cust id to string?
		Long l = Long.parseLong(id);

		models.Customer customer = models.Customer.find.byId(l);
		Form<models.Customer> customerForm = form(models.Customer.class).fill(customer);
		return ok(create_customer.render(customerForm, true, customer.country.code));
	}

	public static Result customerSave() {
		Form<models.Customer> customerForm = form(models.Customer.class).bindFromRequest();

		ObjectNode result = Json.newObject();
		if (customerForm.hasErrors()) {
			Logger.debug("CustomerForm errors " + customerForm.errorsAsJson());
			result.put("status", 1);
			result.put("message", "Customer validation failed, please check back your input");
			result.put("errors", customerForm.errorsAsJson());
			return ok(result);
		}
		if (customerForm.get().id != null) {
			customerForm.get().update();
		} else {
			customerForm.get().save();
		}
		result.put("status", 0);
		result.put("customer", Json.toJson(customerForm.get()));
		return ok(result);
	}

	public static Result sendTransactionViaEmail(String id, String recipient) {
		// Send email
//		models.Customer sender = models.Customer.find.byId(transactionForm.get().sender.id);
		
		Logger.debug("Tyrying to send transaction to "+recipient);
		if (recipient == null) {
			// TODO get from beneficiary email
			flash("error", "Unidentified sender email");
		}
		
		try {
			JsonNode result = ApiHelper.transactionDetail(id);
			Html html = receipt_email.render(result);
			
			InternetAddress[] tos = InternetAddress.parse(recipient);
			if ((tos.length>0)) {
				InternetAddress to = tos[0];
				to.validate();
				//TODO Check if valid
				ApiHelper.sendHtmlEmail(null, tos[0], "Bukti transaksi pengiriman uang", html);
				flash("success", "Transaction email has been sent to sender email <strong>"+recipient.replaceAll("<", "(").replaceAll(">", ")")+"</strong>");
			} else {
//				flash("error", "Unable to send email to "+recipient);
			}
		} catch (EmailException e) {
			e.printStackTrace();
			Logger.error(e.getMessage(), e);
			flash("error", "Unable to send email to "+recipient);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			Logger.error(e.getMessage(), e);
			flash("error", "Unable to send email to "+recipient);
		} catch (AddressException e) {
			e.printStackTrace();
			Logger.error(e.getMessage(), e);
			flash("error", "Unable to send email to "+recipient);
		}
		
		return redirect(routes.Send.receipt(id));
	}
}
