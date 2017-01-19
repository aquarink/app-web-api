package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import kirimdoku.helpers.CountriesHelper;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.avaje.ebean.Expr;

@Entity
@Table(name = "forex")
public class Forex extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	@JsonIgnore
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

	public static Forex findByCurrency(Corporate corporate, Currency origin, Currency destination) {
		return find.where().eq("corporate.code", corporate.code).eq("origin", origin).eq("destination", destination).findUnique();
	}
	
	public static List<Forex> findByCorporate(Corporate corporate, Currency origin) {
		return find.fetch("destination").where().eq("corporate.code", corporate.code).eq("origin", origin).findList();
	}
	
    public static List<CountriesHelper> getReceiveCountryListByForex(User user, Country senderCountry) {
    	List<CountriesHelper> countryStrs = new ArrayList<CountriesHelper>();
    	List<Forex> forexs = Forex.findByCorporate(user.corporate, senderCountry.currency);
    	List<String> countryCodeList = new ArrayList<String>();
    	for (Forex forex : forexs) {
    		try {
    			countryCodeList.add(forex.destination.code);
    			List<Country> countries = Country.find.fetch("currency").where(Expr.eq("currency.code", forex.destination.code)).findList();
    			for (Iterator<Country> iterator = countries.iterator(); iterator
						.hasNext();) {
					Country country = (Country) iterator.next();
	    			CountriesHelper countriesHelper = new CountriesHelper();
	    			countriesHelper.code = country.code;
	    			countriesHelper.name = country.name;
	    			countriesHelper.currency = country.currency.code;
	    			countryStrs.add(countriesHelper);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        return countryStrs;
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
