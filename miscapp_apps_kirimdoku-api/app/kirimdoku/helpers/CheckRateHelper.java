package kirimdoku.helpers;

import java.math.BigDecimal;
import org.hibernate.validator.constraints.Length;
import models.Channel;
import models.Country;
import models.Currency;
import play.data.validation.Constraints;
import controllers.BaseForm;

public class CheckRateHelper extends BaseForm {

	@Constraints.Required
	public String agentKey;
	
	@Constraints.Required
	@Length(min = 2, max = 2)
	public String senderCountryCode;
	
	@Constraints.Required
	@Length(min = 3, max = 3)
	public String senderCurrencyCode;
	
	@Constraints.Required
	public BigDecimal senderAmount;
	
	@Constraints.Required
	@Length(min = 2, max = 2)
	public String channelCode;
	
	@Constraints.Required
	@Length(min = 2, max = 2)
	public String beneficiaryCountryCode;
	
	@Constraints.Required
	@Length(min = 3, max = 3)
	public String beneficiaryCurrencyCode;
	
	public Country senderCountry;
	public Currency senderCurrency;
	public Channel channel;
	public Country beneficiaryCountry;
	public Currency beneficiaryCurrency;

	public String validate() {
		try {
			String err;
			if ((err = this.validateAgentKey(this.agentKey)) != null) {
				return err;
			}
			if (this.senderCountryCode.trim().equals("")) {
				return "Invalid sender country code";
			} else {
				this.senderCountry = Country.findByCode(this.senderCountryCode);
				if (this.senderCountry == null) {
					return "Invalid sender country code";
				}
			}
			if (this.senderCurrencyCode.trim().equals("")) {
				return "Invalid sender currency code";
			} else {
				this.senderCurrency = Currency.findByCode(this.senderCurrencyCode);
				if (this.senderCurrency == null) {
					return "Invalid sender currency code";
				}
			}
			
			if (this.beneficiaryCountryCode.trim().equals("")) {
				return "Invalid beneficiary country code";
			} else {
				this.beneficiaryCountry = Country.findByCode(this.beneficiaryCountryCode);
				if (this.beneficiaryCountry == null) {
					return "Invalid beneficiary country code";
				}
			}
			if (this.beneficiaryCurrencyCode.trim().equals("")) {
				return "Invalid beneficiary currency code";
			} else {
				this.beneficiaryCurrency = Currency.findByCode(this.beneficiaryCurrencyCode);
				if (this.beneficiaryCurrency == null) {
					return "Invalid beneficiary currency code";
				}
			}
			if (this.channelCode.trim().equals("")) {
				return "Invalid channel code";
			} else {
				this.channel = Channel.findByCode(this.channelCode);
				if (this.channel == null) {
					return "Invalid channel code";
				}
			}
			if (this.senderAmount.compareTo(BigDecimal.ZERO) < 1) {
				return "Invalid amount";
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return "Invalid parameter " + e.getMessage();
		}
	}
	
}
