package models;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.avaje.ebean.ExpressionList;

import play.db.ebean.Model;

@Entity
@Table(name = "currencies")
public class Currency extends Model {
	@Id
	@Column(length = 3)
	public String code;

	public static final Finder<String, Currency> find = new Finder<String, Currency>(String.class, Currency.class);

	public static Currency findByCode(String code) {
		return find.where().eq("code", code).findUnique();
	}

	public String findCountryName() {
		Country country = Country.findByCurrency(this.code);
		if (country != null) {
			return country.name;
		}
		return this.code;
	}

		
	public static Map<String, String> options() {
		return options(null);
	}
	
	public static Map<String, String> optionsByCountryCode(String countryCode) {
		return options(models.Country.find.byId(countryCode));
	}
	public static Map<String, String> options(Country country) {
		LinkedHashMap<String, String> options = new LinkedHashMap<String, String>();
		ExpressionList<Currency> where = Currency.find.where();
		if(country != null) {
			where.eq("code", country.currency.code);
		}
		for (Currency c : where.orderBy("code").findList()) {
			options.put(c.code, c.code);
		}
		return options;
	}

	private static SortedMap<java.util.Currency, Locale> currencyLocaleMap;

	static {
		currencyLocaleMap = new TreeMap<java.util.Currency, Locale>(new Comparator<java.util.Currency>() {
			@Override
			public int compare(java.util.Currency c1, java.util.Currency c2) {
				return c1.getCurrencyCode().compareTo(c2.getCurrencyCode());
			}
		});

		for (Locale locale : Locale.getAvailableLocales()) {
			try {
				java.util.Currency currency = java.util.Currency.getInstance(locale);
				currencyLocaleMap.put(currency, locale);
			} catch (Exception e) {
			}
		}
	}

	private static String getCurrencySymbol(String currencyCode) {
		java.util.Currency currency = java.util.Currency.getInstance(currencyCode);
		return currency.getSymbol(currencyLocaleMap.get(currency));
	}

	// Helper for formatting currencies according to locale
	public static String format(Number amount, String currencyCode) { return format(amount, currencyCode, true); }
	public static String format(Number amount, String currencyCode, boolean withSymbol) {
		return format(amount.doubleValue(), 0.0, currencyCode, withSymbol);
	}

	public static String format(BigDecimal amount, String currencyCode) { return format(amount, currencyCode, true); }
	public static String format(BigDecimal amount, String currencyCode, boolean withSymbol) {
		return format(amount.doubleValue(), 0.0, currencyCode, withSymbol);
	}

	public static String format(Double amount, String currencyCode) { return format(amount, currencyCode, true); }
	public static String format(Double amount, String currencyCode, boolean withSymbol) {
		return format(amount, 0.0, currencyCode, withSymbol);
	}
	

	private static String format(Double amount, Double decimals, String currencyCode, boolean withSymbol) {
		double doubleBalance = 0.00;
		if (amount != null) {
			doubleBalance = ((Double) amount) / (Math.pow(10.0, decimals));
		}
		if (currencyCode.equals("RMB")) {
			//Pake RMB failed di java.util.Currency.getInstance("RMB") jd di ganti dulu ke CNY
			currencyCode = "CNY";
		}
		java.util.Currency currency = java.util.Currency.getInstance(currencyCode);
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(currencyLocaleMap.get(currency));
		
		DecimalFormatSymbols decimalFormatSymbols = ((DecimalFormat) numberFormat).getDecimalFormatSymbols();
		decimalFormatSymbols.setCurrencySymbol("");
		((DecimalFormat) numberFormat).setDecimalFormatSymbols(decimalFormatSymbols);
		((DecimalFormat) numberFormat).setMinimumFractionDigits(6);
		String finalFormat = numberFormat.format(doubleBalance).trim();
		if(withSymbol){
			if (currencyCode.equals("CNY")) {
				//Pake RMB failed di java.util.Currency.getInstance("RMB")
				finalFormat+= " RMB";
			} else {
				finalFormat+= " "+currencyCode;
			}
		}
		return finalFormat;
	}
}
