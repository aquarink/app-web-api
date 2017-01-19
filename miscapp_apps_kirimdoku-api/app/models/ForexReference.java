package models;

import java.util.Date;

import play.db.ebean.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import play.data.validation.Constraints;

@Entity
@Table(name = "forex_references")
public class ForexReference extends Model {
	@Id
	public Long id;

	@ManyToOne(optional = false)
	public Forex forex;

	// Value from Operator
	@Column(nullable = false)
	@JsonIgnore
	public double initialRate;

	// Value after Spread
	@Column(nullable = false)
	public double rate;

	@Column(nullable = false)
	public Date createdTime;
	
    public static final Finder<Long, ForexReference> find = new Finder<Long, ForexReference>(Long.class, ForexReference.class);

	public static ForexReference findByCurrency(Corporate corporate, Currency origin, Currency destination) {
		Forex forex = Forex.findByCurrency(corporate, origin, destination);

		// TODO find order by and current date
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
