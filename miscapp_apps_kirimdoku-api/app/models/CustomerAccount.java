package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlRow;
import com.avaje.ebean.TxType;
import com.avaje.ebean.annotation.Transactional;
import play.data.validation.Constraints;
import play.db.ebean.Model.Finder;


@Entity
@Table(name = "customer_accounts")
public class CustomerAccount {
    @Id
    public Long id;
    
    @Constraints.Required
    @ManyToOne(optional = false)
    public Bank bank;
    
    @Constraints.Required
    @Column(nullable = false)
	public String number;
    
    @Constraints.Required
    @Column(nullable = false)
	public String name;
    
    @Column(nullable = true)
	public String city;
    
    @Column(nullable = true)
	public String address;
	
    @Column(nullable = true, length = 50)
	public String inputMode;
    
    public static final Finder<Long, CustomerAccount> find = new Finder<Long, CustomerAccount>(Long.class, CustomerAccount.class);

	@Transactional(type = TxType.NOT_SUPPORTED)
    public static long getSeq() {
    	try {
	        SqlRow row = Ebean.createSqlQuery("SELECT nextval('customer_accounts_seq')").findUnique();
	        long nextId = row.getLong("nextval");
	        return nextId;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return 0;
    }
    
    
}

