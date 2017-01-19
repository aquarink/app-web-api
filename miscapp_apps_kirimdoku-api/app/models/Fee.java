package models;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonIgnore;
import play.data.validation.Constraints;
import play.db.ebean.Model;


@Entity
@Table(name="fees")
public class Fee extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	public int id;

	@Constraints.Required
	@ManyToOne(optional=false)
	public Corporate corporate;
	
	@Constraints.Required
	@ManyToOne(optional=false)
	public Country senderCountry;
	
	@Constraints.Required
	@ManyToOne(optional=false)
	public Country beneficiaryCountry;

    @Constraints.Required
	@ManyToOne(optional=false)
    public Channel channel;
    
    @Constraints.Required
    @Column(nullable = false, precision = 16, scale = 6)
    public BigDecimal fromAmount;
    
    @Constraints.Required
    @Column(nullable = false, precision = 16, scale = 6)
    public BigDecimal toAmount;
    
    @Constraints.Required
    @Column(nullable = false, precision = 16, scale = 6)
    public BigDecimal amount;

    @Constraints.Required
    @ManyToOne(optional=false)
    public Currency currency;
    
    @Column(nullable = true)
    public String description; // TODO is it this simple?
    
	@ManyToOne(optional=true)
	public Corporate domainCorporate;
	
    @Constraints.Required
    @ManyToOne(optional=false)
    public Currency beneficiaryCurrency;
	
    @Column(nullable = false, precision = 16, scale = 6)
    @JsonIgnore
    public BigDecimal additionalFee;
    
    public static final Finder<Long, Fee> find = new Finder<Long, Fee>(Long.class, Fee.class);

    public static List<Fee> findFees(Corporate corporate, Country senderCountry, Country beneficiaryCountry, Channel channel, BigDecimal amount, Currency senderCurrency, Currency beneficiaryCurrency) {
        double amountRange = amount != null ? amount.doubleValue() : 0;
    	return find.where()
                .eq("corporate", corporate)
                .eq("senderCountry", senderCountry)
                .eq("beneficiaryCountry", beneficiaryCountry)
                .eq("currency", senderCurrency)
                .eq("beneficiaryCurrency", beneficiaryCurrency)
                .eq("channel", channel)
                .betweenProperties("fromAmount", "toAmount", amountRange)
                .findList();
    }
}
