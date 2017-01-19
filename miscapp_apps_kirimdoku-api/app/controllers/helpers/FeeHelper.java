package controllers.helpers;

import java.util.List;
import controllers.CashIn.InquiryForm;
import controllers.CashIn.RemitForm;
import models.Currency;
import models.Fee;
import play.Logger;

/**
 * Provide a small helper to create quick response object in json node
 * 
 * @author fauziassegaff
 * 
 */
public class FeeHelper {
	
	public static List<Fee> getFeeTransaction(InquiryForm form, Currency currency) {
		
		List<Fee> fees = Fee.findFees(form.corporate, form.senderCountry, form.beneficiaryCountry, form.channel, form.senderAmount, form.senderCurrency, form.beneficiaryCurrency);
		
		for (Fee fee:fees) {
			if (!fee.currency.equals(currency)) {
				//TODO Make sure all fee converted to destination fee
				//throw new Exception("Unimplemented multiple currency");
				Logger.error("Fee id "+fee.id+" for multi currency isn't supported yet, please contact sys admin");
				fees.remove(fee);
				throw new UnsupportedOperationException("Unsupported features for multi currency");
			}
		}
		
		return fees;
	}
	
	public static List<Fee> getFeeTransaction(RemitForm form, Currency currency) {

		List<Fee> fees = Fee.findFees(form.corporate, form.senderCountry, form.beneficiaryCountry, form.channel, form.senderAmount, form.senderCurrency, form.beneficiaryCurrency);
		
		for (Fee fee:fees) {
			if (!fee.currency.equals(currency)) {
				//TODO Make sure all fee converted to destination fee
				//throw new Exception("Unimplemented multiple currency");
				Logger.error("Fee id "+fee.id+" for multi currency isn't supported yet, please contact sys admin");
				fees.remove(fee);
				throw new UnsupportedOperationException("Unsupported features for multi currency");
			}
		}
		
		return fees;
	}
}