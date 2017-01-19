package models.forms;

import java.math.BigDecimal;

import com.avaje.ebean.Expr;

import controllers.helpers.BillPaymentDataHelper;
import controllers.helpers.SessionHelper;
import models.Country;
import models.Customer;
import models.Transaction;
import models.TransactionInquiry;

/*
 */
public class CashInForm extends Transaction {

	private static final long serialVersionUID = 1L;

	public TransactionInquiry inquiry;
	public BigDecimal feesTotal;
	public BigDecimal additionalFee;
	public BigDecimal rate;
    public String auth1;
    public String auth2;
    public String sendType;
    public String voucherCode;
    public models.User supervisor;
    
    //Channel Cash To Wallet
	public String beneficiaryWalletId;
	public String beneficiaryWalletName;
	public String trackingId;
	
	//Channel Bill Payment
	public BillPaymentDataHelper billPayment;
	
	public String validate() {
		try {
	    	if(senderCountry.code.isEmpty()) {
	    		senderCountry = SessionHelper.getUser().corporate.country;
	    	}
	    	if(senderCurrency.code.isEmpty()) {
	    		senderCurrency = SessionHelper.getUser().corporate.currency;
	    	}
	    	this.beneficiaryCountry = models.Country.find.byId(this.beneficiaryCountry.code);
	    	this.channel = models.Channel.find.byId(this.channel.code);
	    	
	    	if((this.beneficiaryCity != null) && (!this.beneficiaryCity.isEmpty())) {
	    		models.City city = models.City.findByName(this.beneficiaryCity, senderCountry.code);
	    		if(city != null) {
	    			this.beneficiaryCity = city.name;
	    		}
	    	}
	    	
			if(this.feesTotal != null) {
				this.feesTotal = this.feesTotal.setScale(6);
			}
			if(this.additionalFee != null) {
				this.additionalFee = this.additionalFee.setScale(6);
			}
	    	if((this.beneficiaryAccount != null) && (this.beneficiaryAccount.bank != null) && (beneficiaryAccount.bank.code != null) && (!this.beneficiaryAccount.bank.code.isEmpty())) {
	    		models.Bank bank = models.Bank.findByCode(this.beneficiaryAccount.bank.code);
	    		if (bank != null) {
	    			this.beneficiaryAccount.bank = bank;
	    		}
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
			return "Invalid parameter, Debug: "+e.getMessage();
		}
    }

	public static Transaction findById(String id) {
		return find.where().eq("id", id).findUnique();
	}
}
