package models;

import play.db.ebean.Model;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "forex_references")
public class ForexReference extends Model {
	@Id
	public Long id;

	@ManyToOne(optional = false)
	public Forex forex;

	// Value from Operator
	@Column(nullable = false)
	public double initialRate;

	// Value after Spread
	@Column(nullable = false)
	public double rate;

	@Column(nullable = false)
	public Date createdTime;

	public String initialRateFormat() {
		return Transaction.formatNumber(new BigDecimal(initialRate));
	}
	
	public String rateFormat() {
		return Transaction.formatNumber(new BigDecimal(rate));
		//return Forex.format(rate);
	}

	public static final Finder<Long, ForexReference> find = new Finder<Long, ForexReference>(Long.class, ForexReference.class);
	

	public static ForexReference findLastByForex(Forex forex) {
		return find.where().eq("forex", forex).setMaxRows(1).order("id DESC").findUnique();
	}
	
	public static ForexReference findByCorporateCurrencyOriginDestination(String corporateCode, String originCurrencyCode, String destinationCurrencyCode) {
		try {
			Forex forex = Forex.findByCurrency(corporateCode, originCurrencyCode, destinationCurrencyCode);
			System.out.println("forex >>> "+forex+" >> "+corporateCode+"::"+originCurrencyCode+"::"+destinationCurrencyCode);
			if (forex != null)
			return find.where().eq("forex", forex).setMaxRows(1).order("id DESC").findUnique();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}