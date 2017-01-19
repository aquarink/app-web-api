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
@Table(name="transaction_fees")
public class TransactionFee extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	public Long id;
	
    @Constraints.Required
    @ManyToOne(optional=false)
    @JsonIgnore
    public Transaction transaction;

    @Constraints.Required
    @ManyToOne(optional=true)
    @JsonIgnore
    public Fee fee;

    @Constraints.Required
    @Column(nullable = false, precision = 12, scale = 2)
    public BigDecimal amount;

    @Constraints.Required
    @Column(nullable = false, precision = 12, scale = 6)
    public BigDecimal additionalFee;
    
    @Constraints.Required
    @ManyToOne(optional=false)
    public Currency currency;

    @Constraints.Required
    public String description;

	@ManyToOne(optional=true)
    @JsonIgnore
	public Corporate domainCorporate;
	
    public TransactionFee(Transaction transaction, Fee fee) {
    	this.transaction = transaction;
    	this.fee = fee;
    	this.amount = fee.amount;
    	this.additionalFee = fee.additionalFee;
    	this.currency = fee.currency;
        this.description = fee.description;
        this.domainCorporate = fee.domainCorporate;
    }

    public static final Finder<Long, TransactionFee> find = new Finder<Long, TransactionFee>(Long.class, TransactionFee.class);

    public static List<TransactionFee> findByTransaction(Transaction transaction) {
        return find.where()
                .eq("transaction", transaction)
                .findList();
    }
}
