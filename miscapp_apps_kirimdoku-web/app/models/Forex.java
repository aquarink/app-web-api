package models;

import play.db.ebean.Model;

import javax.persistence.*;

import org.apache.commons.lang.Validate;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="forex")
public class Forex extends Model
{
	@Id
    public Long id;

	@ManyToOne(optional = false)
	@JsonIgnore
	public Corporate corporate;
	
    @ManyToOne(optional = false)
    public Currency origin;

    @ManyToOne(optional = false)
    public Currency destination;

	@Column(nullable = false)
	@JsonIgnore
    public double rate;

	@Column(nullable = false)
	@JsonIgnore
    public double spread;
	
	@Column(nullable = false)
	@JsonIgnore
    public Date createdTime;

    public static final Finder<Long, Forex> find = new Finder<Long, Forex>(Long.class, Forex.class);

    public String getRateFormatted(){
    	return format(this.rate);
    }
    
    public static String format(double rate) {
        DecimalFormat formatter = new DecimalFormat("0.000000####");
		formatter.setMinimumFractionDigits(6);
        return formatter.format(rate);
    }
    
    public String rateFormat() {
        DecimalFormat formatter = new DecimalFormat("0.000000####");
		formatter.setMinimumFractionDigits(6);
        return formatter.format(this.rate);
    }
    
    public String validate() {
    	if(! ((spread >= 0) && (spread <= 100))) {
    		return "invalid spread value";
    	}
    	return null;
    }

	public double getSpreadRate() {
		return rate - (rate / (100 / spread));
	}
	
	public static List<Forex> findByCorporate(Corporate corporate) {
		return find.where().eq("corporate.code", corporate.code).findList();
	}
	public static List<Forex> findByCorporate(Corporate corporate, Currency origin) {
		return find.where().eq("corporate.code", corporate.code).eq("origin", origin).findList();
	}
	public static Forex findByCorporate(Corporate corporate, Currency origin, Currency destination) {
		return find.where().eq("corporate.code", corporate.code).eq("origin", origin).eq("destination", destination).findUnique();
	}
	
	public static ForexReference findLastForexReference(Corporate corporate, Currency origin, Currency destination) {
		Forex forex = Forex.findByCorporate(corporate, origin, destination);
		return ForexReference.findLastByForex(forex);
	}
	
	public static Forex findByCurrency(String corporateCode, String originCurrencyCode, String destinationCurrencyCode) {
		try {
			return find.where()
					.eq("corporate.code", corporateCode)
					.eq("origin.code", originCurrencyCode)
					.eq("destination.code", destinationCurrencyCode)
					.setMaxRows(1)
					.findUnique();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
