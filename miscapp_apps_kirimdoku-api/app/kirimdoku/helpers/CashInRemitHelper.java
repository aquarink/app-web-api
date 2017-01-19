package kirimdoku.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.node.ObjectNode;

import play.Logger;
import play.libs.Json;
import play.mvc.Http.Request;
import controllers.tokens.AgentToken;
import controllers.tokens.CustomerToken;
import models.Channel;
import models.ChannelBank;
import models.Corporate;
import models.Country;
import models.Currency;
import models.Customer;
import models.TransactionFee;
import models.TransactionInquiry;
import models.User;

public class CashInRemitHelper extends TransactionRemit {

	public TransactionInquiry inquiry;
	public List<TransactionFee> fees;
	public String voucherCode;
	public Request request;
	public String agentKey;
	
	//Channel Bill Payment
	public BillPaymentDataHelper billPayment;
	
	public String sendType = "senderAmount";

	public String validate() {
		try {
			String err = null;
			err = this.validateAgentKey(this.agentKey);
			if (err != null) {
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

			this.beneficiaryCountry = Country.find
					.byId(this.beneficiaryCountry.code);
			if (this.beneficiaryCountry == null) {
				return "Beneficiary country not valid";
			}

			this.beneficiaryCurrency = Currency.find
					.byId(this.beneficiaryCurrency.code);
			if (this.beneficiaryCurrency == null) {
				return "Beneficiary currency not valid";
			}

			this.channel = Channel.find.byId(this.channel.code);
			if (this.channel == null) {
				return "Channel code is not valid";
			}
			String allowChannel = this.agent.corporate.configuration.channelCode != null ? this.agent.corporate.configuration.channelCode : "";
			if (!allowChannel.contains(this.channel.code)) {
				return "Unauthorized channel permission";
			}
			if (this.agent.corporate.permission != Corporate.PERMISSION_SEND) {
				return "Unauthorized sending permission";
			}
			if (this.channel.code.equals("06") || this.channel.code.equals("07")) {
				if (this.beneficiaryAccount == null) {
					return "Beneficiary account is required";
				}
				if (this.beneficiaryAccount.bank == null) {
					return "Beneficiary account bank is required";
				}
				models.Bank bank = null;
				if (this.beneficiaryAccount.bank.code != null
						&& !this.beneficiaryAccount.bank.code.equals("")) {
					bank = models.Bank
							.findByCode(this.beneficiaryAccount.bank.code);
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
				if (channel.code.equals("10")) {
					try {
							SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
							Date birthDate = null;
							try {
								birthDate = simpleDateFormat.parse("1980-01-01");
							} catch (ParseException e) {
								e.printStackTrace();
							}
							Customer customerSender = Customer.find.where()
									.eq("firstName", this.agent.corporate.name)
									.eq("lastName", "SENDER")
									.eq("phoneNumber", this.agent.corporate.phoneNumber)
									.eq("personalIdType", "OTHER")
									.eq("personalId", this.agent.corporate.licenseNumber)
									.eq("birthDate", birthDate).setMaxRows(1).findUnique();
							if (customerSender == null) {
								customerSender = new Customer();
								customerSender.firstName = this.agent.corporate.name;
								customerSender.lastName = "SENDER";
								customerSender.phoneNumber = this.agent.corporate.phoneNumber;
								customerSender.personalIdType = "OTHER";
								customerSender.personalId = this.agent.corporate.licenseNumber;
								customerSender.birthDate = java.sql.Date.valueOf("1980-01-01");
								Country country = this.agent.country;
								customerSender.country = country;
							}
							Logger.debug("SENDER : "+Json.toJson(customerSender));
							this.sender = customerSender;
							
							Customer customerBeneficiary = Customer.find.where()
									.eq("firstName", this.agent.corporate.name)
									.eq("lastName", "BENEFICIARY")
									.eq("phoneNumber", this.agent.corporate.phoneNumber)
									.eq("personalIdType", "OTHER")
									.eq("personalId", this.agent.corporate.licenseNumber)
									.eq("birthDate", birthDate).setMaxRows(1).findUnique();
							if (customerBeneficiary == null) {
								customerBeneficiary = new Customer();
								customerBeneficiary.firstName = this.agent.corporate.name;
								customerBeneficiary.lastName = "BENEFICIARY";
								customerBeneficiary.phoneNumber = this.agent.corporate.phoneNumber;
								customerBeneficiary.personalIdType = "OTHER";
								customerBeneficiary.personalId = this.agent.corporate.licenseNumber;
								customerBeneficiary.birthDate = java.sql.Date.valueOf("1980-01-01");
								Country country = this.agent.country;
								customerBeneficiary.country = country;
							}
							Logger.debug("BENEFICIARY : "+Json.toJson(customerBeneficiary));
							this.beneficiary = customerBeneficiary;
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					return "Sender parameter required";
				}
			} else if (sender.id != null) {
			} else if ((sender.idToken != null) && (!sender.idToken.isEmpty())) {
				Logger.debug("CashINSenderIdToken " + sender.idToken);
				sender = models.Customer.find.byId(CustomerToken
						.fromString(sender.idToken).id);
			} else {
				List<Customer> customers = models.Customer.find.where()
						.eq("firstName", sender.firstName)
						.eq("lastName", sender.lastName)
						.eq("phoneNumber", sender.phoneNumber)
						.eq("country.code", sender.country.code)
						.eq("personalIdType", sender.personalIdType)
						.eq("personalId", sender.personalId)
						.eq("birthDate", sender.birthDate)
						.findList();
				if (customers != null && customers.size() > 0) {
					sender = customers.iterator().next();
				}
			}

			if (beneficiary == null) {
				return "Beneficiary parameter required";
			} else if (beneficiary.id != null) {
			} else if ((beneficiary.idToken != null)
					&& (!beneficiary.idToken.isEmpty())) {
				beneficiary = models.Customer.find.byId(CustomerToken
						.fromString(beneficiary.idToken).id);
			} else {
				List<Customer> customers = models.Customer.find.where()
						.eq("firstName", beneficiary.firstName)
						.eq("lastName", beneficiary.lastName)
						.eq("phoneNumber", beneficiary.phoneNumber)
						.eq("country.code", beneficiary.country.code).findList();
				if (customers != null && customers.size() > 0) {
					beneficiary = customers.iterator().next();
				} else {
					Logger.debug("Attempt to create new customer "
							+ Json.toJson(beneficiary));
					// create new customer?
					beneficiary.cityName = beneficiaryCity;
					beneficiary.save();
				}
			}

			return err;
		} catch (Exception e) {
			e.printStackTrace();
			return "Invalid parameter " + e.getMessage();
		}
	}

	protected String validateAgentKey(String agentKey) {

		if ((agentKey != null) && (agentKey.length() > 0)) {
			try {
				Logger.debug("Attempt agentKey 1 " + agentKey);
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
			} catch (Exception e) {
				return "Invalid agent key";
			}
		}
		return "Unauthorized agent key access";
	}
	
}
