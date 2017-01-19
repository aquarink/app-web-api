package models;

import play.Logger;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.avaje.ebean.ExpressionList;

import controllers.helpers.SessionHelper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name="countries")
public class Country extends Model
{
	private static final long serialVersionUID = 1L;

	@Id
    @Column(length=3)
    public String code;

    @Constraints.Required
    public String name;

    @Constraints.Required
    @ManyToOne(optional=false)
    @JsonIgnore
    public Currency currency;

    public static final Finder<String, Country> find = new Finder<String, Country>(String.class,
            Country.class);


    public static Country findByName(String name)
    {
        return find.where()
                .ieq("name", name)
                .findUnique();
    }

    public static Country findByCurrency(String currency)
    {
        return find.where()
                .eq("currency", currency)
                .findUnique();
    }

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Country c: Country.find.orderBy("name").findList()) {
            options.put(c.code, c.name);
        }
        return options;
    }
    
    public static Map<String,String> optionsBeneficiaryCountry(String senderCountryCode) {return optionsBeneficiaryCountry(SessionHelper.getUser(), senderCountryCode);}
    public static Map<String,String> optionsBeneficiaryCountry(User user, String senderCountryCode) {
    	return optionsBeneficiaryCountry(user, models.Country.find.byId(senderCountryCode));
    }
    public static Map<String,String> optionsBeneficiaryCountry(User user, Country senderCountry) {
    	List<String> countryStrs = new ArrayList<String>();
    	for(Forex f : Forex.findByCorporate(user.corporate, senderCountry.currency)) {
    		countryStrs.add(f.destination.code);
    	}
    	
    	Logger.debug("==== Countries "+countryStrs);
        ExpressionList<Country> where = Country.find.where();
        where.in("currency.code", countryStrs);
    	
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Country c: where.orderBy("name").findList()) {
            options.put(c.code, c.name);
        }
        return options;
    }
    
    public static Map<String,String> optionsBeneficiaryCountryBatchUpload(String senderCountryCode) {
    	List<String> countryStrs = new ArrayList<String>();
    	Country senderCountry = models.Country.find.byId(senderCountryCode);
    	for(Forex f : Forex.findByCorporate(SessionHelper.getUser().corporate, senderCountry.currency)) {
    		countryStrs.add(f.destination.code);
    	}
    	
    	Logger.debug("==== Countries "+countryStrs);
        ExpressionList<Country> where = Country.find.where();
        where.in("currency.code", countryStrs);
    	
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Country c: where.orderBy("name").findList()) {
            options.put(c.code, c.currency.code);
        }
        return options;
    }
    
    //Bill Payment Destination Country
    public static Map<String,String> optionsBeneficiaryCountryBillPayment(String senderCountryCode) {
    	return optionsBeneficiaryCountryBillPayment(SessionHelper.getUser(), senderCountryCode);
    }
    
    public static Map<String,String> optionsBeneficiaryCountryBillPayment(User user, String senderCountryCode) {
    	List<String> countryStrs = new ArrayList<String>();
    	Country senderCountry = models.Country.find.byId(senderCountryCode);
    	Currency currencyDestination = Currency.findByCode("IDR");
    	Forex f = Forex.findByCorporate(user.corporate, senderCountry.currency, currencyDestination);
    	countryStrs.add(f.destination.code);
    	
    	Logger.debug("==== Countries "+countryStrs);
        ExpressionList<Country> where = Country.find.where();
        where.in("currency.code", countryStrs);
    	
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Country c: where.orderBy("name").findList()) {
            options.put(c.code, c.name);
        }
        return options;
    }
    
    public static Map<String,String> optionsCurrency() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Country c: Country.find.orderBy("code").findList()) {
            options.put(c.code, c.code);
        }
        return options;
    }

}
