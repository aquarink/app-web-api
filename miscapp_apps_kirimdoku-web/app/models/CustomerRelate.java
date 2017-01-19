package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlRow;
import com.avaje.ebean.TxType;
import com.avaje.ebean.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "customer_relate")
public class CustomerRelate extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	public Long id;

	@ManyToOne(optional = true)
	@Constraints.Required
	public Customer customer;

	@ManyToOne(optional = true)
	@Constraints.Required
	public Customer customerRelate;

	@Column(nullable = false)
	@Constraints.Required
	public Date createDate;

	public Date updateDate;
	
	public Character status;
	
	public static final Finder<Long, CustomerRelate> find = new Finder<Long, CustomerRelate>(Long.class, CustomerRelate.class);
	
	@Transactional(type = TxType.NOT_SUPPORTED)
    public static long getSeq() {
    	try {
	        SqlRow row = Ebean.createSqlQuery("SELECT nextval('customer_relate_seq')").findUnique();
	        long nextId = row.getLong("nextval");
	        return nextId;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return 0;
    }
	
	public static List<Long> getCustomerRelateByCustomerId(Customer customer) {
		try {
			List<CustomerRelate> customerRelates = find.where().eq("customer", customer).eq("status", CustomerRelate.ECustomerRelateStatus.ACTIVE.code()).findList();
			if (customerRelates.size() > 0) {
				List<Long> customers = new ArrayList<Long>();
				for (CustomerRelate relate : customerRelates) {
					customers.add(relate.customerRelate.id);
				}
				return customers;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<Customer> getCustomersRelateByCustomerId(Customer customer) {
		try {
			List<CustomerRelate> customerRelates = find.where().eq("customer", customer).eq("status", CustomerRelate.ECustomerRelateStatus.ACTIVE.code()).findList();
			if (customerRelates.size() > 0) {
				List<Customer> customers = new ArrayList<Customer>();
				for (CustomerRelate relate : customerRelates) {
					customers.add(relate.customerRelate);
				}
				return customers;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean getCustomerRelateByCustomerIdAndCustomerRelateId(Customer customer, Customer customerRelate) {
		try {
			CustomerRelate customerRelates = find.where().eq("customer", customer).eq("customerRelate", customerRelate).eq("status", CustomerRelate.ECustomerRelateStatus.ACTIVE.code()).findUnique();
			if (customerRelates != null) {
				customerRelates.status = ECustomerRelateStatus.INACTIVE.code();
				customerRelates.updateDate = new Date();
				customerRelates.update();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static enum ECustomerRelateStatus {
		
		INACTIVE('I'),
		ACTIVE('A');
		
	    private Character code;
	    private static final Map<Character, ECustomerRelateStatus> lookup = new HashMap<Character, ECustomerRelateStatus>();

	    static {
	        for (ECustomerRelateStatus s : EnumSet.allOf(ECustomerRelateStatus.class)) {
	            getLookup().put(s.code(), s);
	        }
	    }

	    public static Map<Character, ECustomerRelateStatus> getLookup() {
	        return lookup;
	    }

	    ECustomerRelateStatus(Character code) {
	        this.code = code;
	    }

	    public char code() {
	        return code;
	    }
	    
	}
	
	
}
