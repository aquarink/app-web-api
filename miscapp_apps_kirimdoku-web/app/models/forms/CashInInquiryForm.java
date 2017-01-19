package models.forms;

import java.math.BigDecimal;

import models.Customer;
import models.CustomerAccount;
import play.Logger;
import play.data.validation.Constraints;
import controllers.helpers.BillPaymentDataHelper;
import controllers.helpers.SessionHelper;

public class CashInInquiryForm {

	@Constraints.Required
	public models.Country senderCountry;

	@Constraints.Required
	public models.Currency senderCurrency;

	@Constraints.Required
	public models.Country beneficiaryCountry;

	@Constraints.Required
	public models.Currency beneficiaryCurrency;

	@Constraints.Required
	public models.Channel channel;

	public String sendType;

	public BigDecimal senderAmount;
	public BigDecimal beneficiaryAmount;
	
	//Channel Cash To Wallet
	public String beneficiaryWalletId;
	public String beneficiaryWalletName;
	
	//Channel Bill Payment
	public BillPaymentDataHelper billPayment;
	
	public String voucherCode;
	public int activity;
	
	public CustomerAccount beneficiaryAccount;
	public Customer sender;
	
	public static enum inquiryActivity {
		INQUIRY(1),
		REMIT(2);

		private int code;

		inquiryActivity(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}
		
		@Override
		public String toString() {
			return this.name();
		}
	}
	
	
	
	public String validate() {
		if (senderCountry.code.isEmpty()) {
			Logger.debug("Validate CasjOnINquiForm sess " + SessionHelper.getUser().corporate.country);
			senderCountry = SessionHelper.getUser().corporate.country;
		}
		if (senderCurrency.code.isEmpty()) {
			senderCurrency = SessionHelper.getUser().corporate.currency;
		}
		Logger.debug("Validate CasjOnINquiForm" + senderCountry.name);

//		if (senderAmount == null)
//			senderAmount = BigDecimal.ZERO;
//
//		if (beneficiaryAmount == null)
//			beneficiaryAmount = BigDecimal.ZERO;

		return null;
	}
}
