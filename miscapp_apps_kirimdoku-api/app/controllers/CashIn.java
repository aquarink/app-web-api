package controllers;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import kirimdoku.helpers.BillPaymentDataHelper;
import kirimdoku.helpers.TransactionRemit;
import models.Channel;
import models.ChannelBank;
import models.Corporate;
import models.Country;
import models.Currency;
import models.Customer;
import models.CustomerAccount;
import models.ForexReference;
import models.TransactionFee;
import models.TransactionInquiry;
import models.User;

import org.codehaus.jackson.node.ObjectNode;

import play.Logger;
import play.data.validation.Constraints;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;
import play.data.Form;
import static play.data.Form.form;
import controllers.agents.cashin.BaseAgent;
import controllers.agents.cashin.DokuAgent;
import controllers.agents.cashin.SimulatorAgent;
import controllers.helpers.Definition;
import controllers.helpers.ResponseHelper;
import controllers.helpers.SecurityAgentAuthenticator;
import controllers.tokens.AgentToken;
import controllers.tokens.CustomerToken;
import controllers.tokens.TransactionToken;

/**
 * class wrapper for handling any cash in transactions
 * 
 * <pre>
 * Step should be for API Side: 
 * - Receive request retrieve of fixtures (Country/City, Transaction Types, Currency List, FeeType) 
 * - Receive request current sender by id if member already registered 
 * - Receive request of sender registration 
 * - Receive request current beneficiary if member already registered 
 * - Receive request of beneficiary registration 
 * - Receive request of transaction and its information (sending cust and agent pin) 
 * - Response with transaction detail and information (including transactionId, amount total exchanges, fee) - Receive receipt information
 * 
 * 
 * ==== Scenario to cover 
 * - Warindo cash in via its webinterface 
 * - Small web interface using our information for cashIn 
 * - 3rd party who don't have infrastructure (manual) able to do cashIn 
 * - 3rd party who have established infrastructure for cashIn 
 * - 3rd party with mobile sms/app to cashIn 
 * - Integrate with different mechanism app 
 * - Able to receive the amount before/after forex (using with/withou our forex) 
 * - Receive request from bank (ISO8583) 
 * - Ease of use for current running 3rd party commerce webapp
 * </pre>
 * 
 * @author zi
 * 
 */
@Security.Authenticated(SecurityAgentAuthenticator.class)
@With(LoggerAction.class)
public class CashIn extends Controller {

	/**
	 * Retrieve some informations Send some basic data form - AgentCode - Agent
	 * authorization - Source country ISO - Destination country ISO - Amount
	 * 
	 * @return Response - Status (Open/available/etc) - Total amounts and fee - InfoId
	 */
	public static Result inquiry() {
		try {
			// Create request object as form and bind from request
			Form<InquiryForm> form = form(InquiryForm.class).bindFromRequest();
			Logger.debug("Inquiry request "+form.data());
			if (form.hasErrors()) {
				Logger.debug("Invalid parameters : "+form.errorsAsJson());
				return unauthorized(ResponseHelper.createResponse(Definition.STATUS_INVALIDPARAMS, "Invalid parameters", form.errorsAsJson()));
			}
			
			// Get the routing and initialize agents
			BaseAgent agent = getAgentByCorporate(form.get().corporate);
	
			// Call the Agents inquiry via async and return Promise
			return async(agent.inquiry(request(), form.get()));
		} catch (Exception e) {
			e.printStackTrace();
			return ok(ResponseHelper.createResponse(Definition.STATUS_UNKNOWN, e.getMessage()));
		}
	}

	/**
	 * Process transaction information
	 */
	public static Result remit() {
		// Create request object as form and bind from request
		try {
			try {
				ObjectNode node = Json.newObject();
				node = (ObjectNode) request().body().asJson();
				String channelCode = node.path("channel").path("code").asText();
				Logger.debug("CHANNEL : "+node.path("channel").path("code").asText());
				if (channelCode.equals("10")) {
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date birthDate = null;
					try {
						birthDate = simpleDateFormat.parse("1980-01-01");
					} catch (ParseException e) {
						e.printStackTrace();
					}
					String agentKey = request().headers().get("agentKey")[0];
					AgentToken agentToken = AgentToken.fromString(agentKey);
					User user = null;
					if (agentToken.identifier == 'A') {
						user = User.find.fetch("corporate").where().eq("id",Long.valueOf(agentToken.id)).findUnique();
					}
					Logger.debug("USER : "+Json.toJson(user));
					Customer customerSender = Customer.find.where()
							.eq("firstName", user.corporate.name)
							.eq("lastName", "SENDER")
							.eq("phoneNumber", user.corporate.phoneNumber)
							.eq("personalIdType", "OTHER")
							.eq("personalId", user.corporate.licenseNumber)
							.eq("birthDate", birthDate).setMaxRows(1).findUnique();
					if (customerSender == null) {
						customerSender = new Customer();
						customerSender.firstName = user.corporate.name;
						customerSender.lastName = "SENDER";
						customerSender.phoneNumber = user.corporate.phoneNumber;
						customerSender.personalIdType = "OTHER";
						customerSender.personalId = user.corporate.licenseNumber;
						customerSender.birthDate = java.sql.Date.valueOf("1980-01-01");
						Country country = user.country;
						customerSender.country = country;
					}
					Logger.debug("SENDER : "+Json.toJson(customerSender));
					node.put("sender", Json.toJson(customerSender));
					Customer customerBeneficiary = Customer.find.where()
							.eq("firstName", user.corporate.name)
							.eq("lastName", "BENEFICIARY")
							.eq("phoneNumber", user.corporate.phoneNumber)
							.eq("personalIdType", "OTHER")
							.eq("personalId", user.corporate.licenseNumber)
							.eq("birthDate", birthDate).setMaxRows(1).findUnique();
					if (customerBeneficiary == null) {
						customerBeneficiary = new Customer();
						customerBeneficiary.firstName = user.corporate.name;
						customerBeneficiary.lastName = "BENEFICIARY";
						customerBeneficiary.phoneNumber = user.corporate.phoneNumber;
						customerBeneficiary.personalIdType = "OTHER";
						customerBeneficiary.personalId = user.corporate.licenseNumber;
						customerBeneficiary.birthDate = java.sql.Date.valueOf("1980-01-01");
						Country country = user.country;
						customerBeneficiary.country = country;
					}
					Logger.debug("BENEFICIARY : "+Json.toJson(customerBeneficiary));
					node.put("beneficiary", Json.toJson(customerBeneficiary));
					Logger.debug("REQUEST : "+request().body().asJson());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			Form<RemitForm> form = form(RemitForm.class).bindFromRequest();
			Logger.debug("Remit request "+form.data());
			if (form.hasErrors()) {
				Logger.debug("Invalid parameters : "+form.errorsAsJson());
				return unauthorized(ResponseHelper.createResponse(Definition.STATUS_INVALIDPARAMS, "Invalid parameters", form.errorsAsJson()));
			}
			Corporate corporate = form.get().corporate;
			corporate.refresh();
			
			if (form.get().inquiry.transaction == null) {
				// Automatically create and save customer on new record
				
				if (form.get().sender.personalIdCountry != null && form.get().sender.personalIdCountry.code.equals("")) {
					form.get().sender.personalIdCountry = null;
				}
				
				if((form.get().sender.idToken == null) || form.get().sender.idToken.isEmpty()) {
					form.get().sender.save();
				}
				
				if (form.get().beneficiary.personalIdCountry != null && form.get().beneficiary.personalIdCountry.code.equals("")) {
					form.get().beneficiary.personalIdCountry = null;
				}
				
				if((form.get().beneficiary.idToken == null) || form.get().beneficiary.idToken.isEmpty()) {
					form.get().beneficiary.save();
				}
				
				// Get the routing and initialize agents
				BaseAgent agent = getAgentByCorporate(form.get().corporate);
		
				// Call the Agents inquiry via async and return Promise
				return async(agent.remit(request(), form.get()));
			} else {
				ObjectNode node = ResponseHelper.createResponse(Definition.STATUS_OK, "Pass");
				ObjectNode remit = Json.newObject();
				remit.put("transactionId", TransactionToken.fromTransaction(form.get().inquiry.transaction).toString());
				node.put("remit", remit);
				node.put("warning", "Inquiry Process has been processed");
				Logger.debug("Remit result "+node.toString());
				return unauthorized(node);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ok(ResponseHelper.createResponse(Definition.STATUS_UNKNOWN, e.getMessage()));
		}
	}

	private static BaseAgent getAgentByCorporate(Corporate corporate) {
		// The agent is saved and could be retrieved from
		// SecurityAgentAuthenticator, so we do just need to call
		// request.username()

		BaseAgent agent = null;
		if ((corporate != null) && (corporate.code.equals("AAA"))) {
			agent = new SimulatorAgent();
		} else {
			agent = new DokuAgent();
		}
		return agent;
	}

	/**
	 * The static or embedded InquiryForm that are used as an object request and
	 * any logic for validation of the request
	 * 
	 */
	public static class InquiryForm extends BaseForm {

		@Constraints.Required
		public Country senderCountry;
		
		@Constraints.Required
		public Currency senderCurrency;
		
		@Constraints.Required
		public Country beneficiaryCountry;
		
		@Constraints.Required
		public Currency beneficiaryCurrency;
		
		@Constraints.Required
		public Channel channel;
		
		public BigDecimal senderAmount;
		public BigDecimal beneficiaryAmount;

		public String idToken;

		public List<TransactionFee> fees;
		public ForexReference forexRef;
		
		//Channel Cash To Wallet
		public String beneficiaryWalletId;
		public String beneficiaryWalletName;
		public String trackingId;
		
		//Channel Bill Payment
		public BillPaymentDataHelper billPayment;
		
		
		@Constraints.Required
		public int activity;
		
		public String sendType = "senderAmount";
		
		public CustomerAccount beneficiaryAccount;
		public Customer sender;
		
		public String validate() {
			try {
				super.validateAgentKey(request().username());
				this.senderCountry = Country.find.byId(this.senderCountry.code);
				if (this.senderCountry == null) {
					return "Sender country not valid";
				}
				
				this.senderCurrency = Currency.find.byId(this.senderCurrency.code);
				if (this.senderCurrency == null) {
					return "Sender currency not valid";
				}
	
				this.beneficiaryCountry = Country.find.byId(this.beneficiaryCountry.code);
				if (this.beneficiaryCountry == null) {
					return "Beneficiary country not valid";
				}
	
				this.beneficiaryCurrency = Currency.find.byId(this.beneficiaryCurrency.code);
				if (this.beneficiaryCurrency == null) {
					return "Beneficiary currency not valid";
				}
				
				this.channel = Channel.find.byId(this.channel.code);
				if (this.channel == null) {
					return "Channel code is not valid";
				}
	
				if((senderAmount != null) && (senderAmount.intValue() >= 1)) {
					this.sendType = "senderAmount";
				} else if ( (beneficiaryAmount != null) && (beneficiaryAmount.intValue() >= 1)) {
					this.sendType = "beneficiaryAmount";
				} else {
					if (!this.channel.code.equals("10"))
					return "Sender amount is invalid";
				}
		    	if (this.channel.code.equals("07") && this.sender != null) {
			    	if (this.sender.idToken == null || this.sender.idToken.equals("")) {
						Customer customerSender = Customer.find.where()
								.eq("firstName", this.sender.firstName)
								.eq("lastName", this.sender.lastName)
								.eq("phoneNumber", this.sender.phoneNumber)
								.eq("personalIdType", this.sender.personalIdType)
								.eq("personalId", this.sender.personalId)
								.eq("country", this.sender.country)
								.eq("birthDate", this.sender.birthDate).setMaxRows(1).findUnique();
						if (customerSender == null) {
							this.sender.personalIdCountry = this.sender.country;
							this.sender.save();
						} else {
				    		this.sender = customerSender;
				    	}
			    	}
		    	}
				return null;
			} catch(Exception e) {
				e.printStackTrace();
				return "Invalid parameter "+e.getMessage();
			}
		}

	}

	/**
	 * As the same as InquiryForm with additional fields used for remit action
	 * 
	 */
	public static class RemitForm extends TransactionRemit {

		public TransactionInquiry inquiry;
		public List<TransactionFee> fees;
		
		public String voucherCode;
		
		//Channel Bill Payment
		public BillPaymentDataHelper billPayment;
		
		public String sendType = "senderAmount";
		
		public String validate() {
			try {
				String err = null;
				err = this.validateAgentKey(request().username());
				if(err != null) {
					return err;
				}
				
				this.senderCountry = Country.find.byId(this.senderCountry.code);
				if (this.senderCountry == null) {
					return "Sender country not valid";
				}
				
				this.senderCurrency = Currency.find.byId(this.senderCurrency.code);
				if (this.senderCurrency == null) {
					return "Sender currency not valid";
				}
	
				this.beneficiaryCountry = Country.find.byId(this.beneficiaryCountry.code);
				if (this.beneficiaryCountry == null) {
					return "Beneficiary country not valid";
				}
	
				this.beneficiaryCurrency = Currency.find.byId(this.beneficiaryCurrency.code);
				if (this.beneficiaryCurrency == null) {
					return "Beneficiary currency not valid";
				}
				
				this.channel = Channel.find.byId(this.channel.code);
				if (this.channel == null) {
					return "Channel code is not valid";
				}
				if (this.channel.code.equals("06")) {
					if (this.beneficiaryAccount == null) {
						return "Beneficiary account is required";
					}
					if (this.beneficiaryAccount.bank == null) {
						return "Beneficiary account bank is required";
					}
					models.Bank bank = null;
					if ( (request().body().asFormUrlEncoded() != null) && (request().body().asFormUrlEncoded().get("beneficiaryAccount.bank.code") != null)) {
                        bank = models.Bank.findByCode(request().body().asFormUrlEncoded().get("beneficiaryAccount.bank.code")[0]);
					} else if ( (request().body().asJson() != null) && (request().body().asJson().get("beneficiaryAccount").get("bank").get("code") != null)) {
                        bank = models.Bank.findByCode(request().body().asJson().get("beneficiaryAccount").get("bank").get("code").asText());
					} else {
						return "Beneficiary account bank code is required";
					}
					if (bank == null) {
						return "Beneficiary account bank code is not valid";
					}
					this.beneficiaryAccount.bank = bank;
				}
				if (this.beneficiaryAccount != null) {
		    		if (this.beneficiaryAccount.bank != null) {
		    			if (!ChannelBank.getChannelBankByChannelCodeAndBankId(this.channel.code, this.beneficiaryAccount.bank.id)) {
		    				return "Route Bank not allow";
		    			}
		    		}
		    	}
				if (this.sendType.equals("senderAmount") && (this.senderAmount == null || this.senderAmount.doubleValue()  <= 0)) {
					return "Sender amount is invalid";
				} else if (this.sendType.equals("beneficiaryAmount") && (this.beneficiaryAmount == null || this.beneficiaryAmount.intValue() <= 0)) {
					return "Beneficiary amount is invalid";
				}
				
				this.inquiry = TransactionInquiry.findByCode(this.inquiry.idToken);
				if (this.inquiry == null) {
					return "Invalid inquiryId";
				}
				
				if (!inquiry.user.equals(this.agent)) {
					return "Invalid inquiryId - denied";
				}
				
				if (sender == null) {
					return "Sender parameter required";
				} else if(sender.id != null) {
				} else if ((sender.idToken != null) && (!sender.idToken.isEmpty())) {
					Logger.debug("CashINSenderIdToken "+sender.idToken);
					sender = models.Customer.find.byId(CustomerToken.fromString(sender.idToken).id);
				}
				
				if (beneficiary == null) {
					return "Beneficiary parameter required";
				} else if(beneficiary.id != null) {
				} else if ((beneficiary.idToken != null) && (!beneficiary.idToken.isEmpty())) {
					beneficiary = models.Customer.find.byId(CustomerToken.fromString(beneficiary.idToken).id);
				} else {
					Logger.debug("CUSTOMER COUNTRY : "+this.beneficiaryCountry.code);
					
					Logger.debug("Attempt to create new customer "+Json.toJson(beneficiary));
					// create new customer?
					beneficiary.save();
				}
				
	
				return err;
			} catch(Exception e) {
				e.printStackTrace();
				return "Invalid parameter "+e.getMessage();
			}
		}
		
		protected String validateAgentKey(String agentKey) {
			
	    	if ((agentKey != null) && (agentKey.length()>0)) {
	    		try {
	    			Logger.debug("Attempt agentKey 1 "+agentKey);
	    			AgentToken agentToken = AgentToken.fromString(agentKey);
	    			
	    			if (agentToken.identifier == 'A') {
						this.agent = User.find.byId(Long.valueOf(agentToken.id));
						if (this.agent == null) {
							return "Invalid agent key: unknown user";
						}
						
						this.corporate = this.agent.corporate;
						if (this.corporate == null) {
							return "Invalid agent key: unknown corporate partner code";
						}
						return null;
	    			} else {
	    				return "Invalid agent key: unknown identifier";
	    			}
	    		} catch(Exception e) {
	    			return "Invalid agent key";
	    		}
	    	}
	    	return "Unauthorized agent key access";
	    }
	}
}
