package kirimdoku.helpers;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import models.Channel;
import models.Corporate;
import models.Country;
import models.Currency;
import models.Customer;
import models.CustomerAccount;
import models.ForexReference;
import models.TransactionFee;
import play.data.validation.Constraints;
import play.mvc.Http.Request;
import controllers.BaseForm;

public class CashInInquiryHelper extends BaseForm {

	@Constraints.Required
	@Length(min = 2, max = 2)
	public String senderCountryCode;
	
	@Constraints.Required
	@Length(min = 3, max = 3)
	public String senderCurrencyCode;
	
	@Constraints.Required
	@Length(min = 2, max = 2)
	public String beneficiaryCountryCode;
	
	@Constraints.Required
	@Length(min = 3, max = 3)
	public String beneficiaryCurrencyCode;
	
	@Constraints.Required
	@Length(min = 2, max = 2)
	public String channelCode;
	
	public String sendType = "senderAmount";
	public BigDecimal senderAmount;
	
	public Country senderCountry;
	public Currency senderCurrency;
	public Country beneficiaryCountry;
	public Currency beneficiaryCurrency;
	public Channel channel;
	public BigDecimal beneficiaryAmount;
	public String idToken;
	public List<TransactionFee> fees;
	public ForexReference forexRef;
	public Request request;
	public String agentKey;
	
	public String beneficiaryWalletId;
	public String beneficiaryWalletName;
	public String trackingId;
	public String billerId;
	public String accountNumber;
	
	//Channel Bill Payment
	public BillPaymentDataHelper billPayment;
	
	public CustomerAccount beneficiaryAccount;
	public Customer sender;
	
	@Constraints.Required
	public int activity = 1;
	
	public String validate() {
		try {
			super.validateAgentKey(this.agentKey);
			this.senderCountry = Country.find.byId(this.senderCountryCode);
			if (this.senderCountry == null) {
				return "Sender country not valid";
			}

			this.senderCurrency = Currency.find.byId(this.senderCurrencyCode);
			if (this.senderCurrency == null) {
				return "Sender currency not valid";
			}

			this.beneficiaryCountry = Country.find.byId(this.beneficiaryCountryCode);
			if (this.beneficiaryCountry == null) {
				return "Beneficiary country not valid";
			}

			this.beneficiaryCurrency = Currency.find.byId(this.beneficiaryCurrencyCode);
			if (this.beneficiaryCurrency == null) {
				return "Beneficiary currency not valid";
			}

			this.channel = Channel.find.byId(this.channelCode);
			if (this.channel == null) {
				return "Channel code is not valid";
			}
			String allowChannel = this.user.corporate.configuration.channelCode != null ? this.user.corporate.configuration.channelCode : "";
			if (!allowChannel.contains(this.channel.code)) {
				return "Unauthorized channel permission";
			}
			if (this.user.corporate.permission != Corporate.PERMISSION_SEND) {
				return "Unauthorized sending permission";
			}
			if (!this.channel.code.equals("10")) {
				if (this.sendType.equals("senderAmount") && (this.senderAmount == null || this.senderAmount.doubleValue()  <= 0)) {
					return "Sender amount is invalid";
				} else if (this.sendType.equals("beneficiaryAmount") && (this.beneficiaryAmount == null || this.beneficiaryAmount.intValue() <= 0)) {
					return "Beneficiary amount is invalid";
				}
			}
	    	if (this.channel.code.equals("07")) {
	    		if (this.sender != null) {
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
	    	}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return "Invalid parameter " + e.getMessage();
		}
	}

}
