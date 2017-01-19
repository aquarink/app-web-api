package controllers.helpers;

import java.math.BigDecimal;

import models.Corporate;
import models.Currency;
import models.ForexReference;
//import java.util.Date;

/**
 * Provide a small helper to create quick response object in json node
 * 
 * @author fauziassegaff
 * 
 */
public class ForexHelper {
	
	public static ForexReference getLastForexReference(String corporateCode, String originCode, String destinationCode) {
		Corporate corporate = Corporate.find.byId(corporateCode);
		Currency srcCurrency = Currency.find.byId(originCode);
		Currency dstCurrency = Currency.find.byId(destinationCode);
		if ((srcCurrency != null) && (dstCurrency != null)) {
			return getLastForexReference(corporate, srcCurrency, dstCurrency);
		} else {
			throw new IllegalArgumentException("Source/Destination code is not valid");
		}
	}
	public static ForexReference getLastForexReference(Corporate corporate, Currency origin, Currency destination) {
		System.out.println("Corporate   :: "+corporate.code);
		System.out.println("Origin      :: "+origin.code);
		System.out.println("Destination :: "+destination.code);
		ForexReference ref = ForexReference.findByCurrency(corporate, origin, destination);
		
		return ref;
	}
	
	public static BigDecimal convertCurrency(BigDecimal amount, ForexReference reference) {
		if (reference != null) {
			amount = amount.multiply(BigDecimal.valueOf(reference.rate));
			amount = amount.setScale(6, BigDecimal.ROUND_FLOOR);
		}
		return amount;
	}
	
	public static BigDecimal convertOriginCurrency(BigDecimal amount, ForexReference reference) {
		if (reference != null) {
			amount = amount.divide(BigDecimal.valueOf(reference.rate), 6, BigDecimal.ROUND_FLOOR);
			amount = amount.setScale(6, BigDecimal.ROUND_FLOOR);
		}
		return amount;
	}
	
}