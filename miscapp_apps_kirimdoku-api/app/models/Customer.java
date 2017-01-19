package models;

import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import kirimdoku.helpers.ReportCustomerHelper;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Page;
import com.avaje.ebean.PagingList;
import com.avaje.ebean.Query;
import com.avaje.ebean.RawSql;
import com.avaje.ebean.RawSqlBuilder;
import com.avaje.ebean.annotation.Sql;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import controllers.tokens.CustomerToken;

@Entity
@Table(name = "customers")
public class Customer extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	@JsonIgnore
	public Long id;

	@Transient
	public String idToken;
	
	@ManyToOne(optional = true)
	@Constraints.Required
	@JsonIgnore
	public User agent;

	@Column(nullable = false, length = 64)
	@Constraints.Required
	public String firstName;

	@Column(nullable = false, length = 64)
	@Constraints.Required
	public String lastName;

	@ManyToOne(optional = false)
	@Constraints.Required
	public Country country;

	@Column(nullable = true, length = 64)
	public String cityName;

	@Column(nullable = true, length = 255)
	public String address;

	public static enum Gender { 
	    MALE,
	    FEMALE
	}

	@Column(nullable = true)
	public Gender gender;
	
	@Column(nullable = true, columnDefinition="DATE")
	@Formats.DateTime(pattern = "yyyy-MM-dd")
	@Constraints.Required
	public Date birthDate;

	@Column(nullable = true, length = 16)
	@Constraints.Required
	public String personalIdType;

	@Column(nullable = true, length = 32)
	@Constraints.Required
	public String personalId;

	@ManyToOne(optional = true)
	@Column(nullable = true)
	public Country personalIdCountry;

	@Column(nullable = true)
	@Formats.DateTime(pattern = "yyyy-MM-dd")
	public Date personalIdIssueDate;

	@Column(nullable = true)
	@Formats.DateTime(pattern = "yyyy-MM-dd")
	public Date personalIdExpireDate;
	
	@Column(nullable = true, length = 20)
	@Constraints.Required
	public String phoneNumber;

	@Column(nullable = true, length = 64)
	public String email;

	@Column(nullable = true, length = 10)
	public String postalCode;

	@Column(nullable = true, length = 32)
	public String taxId;

	@Column(nullable = true, length = 64)
	public String occupation;
    
    @Column(nullable = true, length = 50)
	public String inputMode;
    
    @Column(nullable = true, length = 200)
	public String tcode;
    
    @Column(nullable = true, length = 200)
	public String pinyin;
	
	@JsonProperty
	public String getIdToken() {
		if((this.idToken != null) && (!this.idToken.isEmpty())) return this.idToken;
		if(this.id == null) return null;
    	try {
    		return this.idToken = CustomerToken.fromCustomer(this).toString();
    	} catch(Exception e) {return null;}
	}


	@JsonIgnore
	public String fullName() {
		if ((firstName != null) && (lastName != null)) {
			return firstName + " " + lastName;
		} else if (firstName != null) {
			return firstName;
		} else {
			return "";
		}
	}

	public static final Finder<Long, Customer> find = new Finder<Long, Customer>(Long.class, Customer.class);
	
	public boolean equals(Object o) {
		if((o != null) && (o instanceof Customer)) {
			Customer co = (Customer) o;
			if(co.id == this.id) return true;
		}
		return false;
	}
	
	@Transient
	@JsonIgnore
	public CustomerStatistic statistic;
	
	@JsonIgnore
	public CustomerStatistic getStatistic() {
		if(statistic == null) {
			String sql = "SELECT SUM(CASE WHEN sender_id = "+this.id+" THEN sender_amount ELSE 0 END) AS totalSenderAmount, sum(CASE WHEN beneficiary_id = "+this.id+" THEN beneficiary_amount ELSE 0 END) AS totalBeneficiaryAmount"
					+ " FROM transactions "
					+ " WHERE cash_in_time > (CURRENT_TIMESTAMP - interval '3 day' ) AND (sender_id = "+this.id+" OR beneficiary_id = "+this.id+")";
			
			RawSql rawSql = RawSqlBuilder.unparsed(sql)
					.columnMapping("totalSenderAmount", "totalSenderAmount")
					.columnMapping("totalBeneficiaryAmount", "totalBeneficiaryAmount")
					.create();

			Query<CustomerStatistic> query = Ebean.find(CustomerStatistic.class);
			query.setVanillaMode(true).setRawSql(rawSql);

			statistic = query.findUnique();
			if(statistic == null) {
				statistic = new CustomerStatistic();
			}
		}
		
		return statistic;
	}
	
	@Entity
	@Sql
	public static class CustomerStatistic {
		public BigDecimal totalSenderAmount;
		public BigDecimal totalBeneficiaryAmount;
		
		public CustomerStatistic() {
			totalSenderAmount = BigDecimal.ZERO;
			totalBeneficiaryAmount = BigDecimal.ZERO;
		}
		
		public boolean hasExceedSendLimit(BigDecimal sendAmount, Corporate corporate) {
			if(this.totalSenderAmount == null) this.totalSenderAmount = BigDecimal.ZERO;
			if(this.totalSenderAmount.add(sendAmount).intValue() > corporate.customerSendLimit.intValue())
				return true;
			return false;
		}
		
		public boolean hasExceedBeneficiaryLimit(BigDecimal beneficiaryAmount, Corporate corporate) {
			if(this.totalBeneficiaryAmount == null) this.totalBeneficiaryAmount = BigDecimal.ZERO;
			if(this.totalBeneficiaryAmount.add(beneficiaryAmount).intValue() > corporate.customerSendLimit.intValue())
				return true;
			return false;
		}
	}
	
	@Transient
	public Customer save(Boolean allowCreate, Boolean allowUpdate) {
		if((this.id == null) && allowCreate.booleanValue()) {
			if((this.idToken != null) && (!this.idToken.isEmpty())) {
				this.id = CustomerToken.fromString(this.idToken).id;
				this.update();
			} else {
				this.save();
			}
		} else if((this.id != null) && allowUpdate.booleanValue()) {
			this.update();
		}
		return this;
	}
	
	public static Page<Customer> reportPage(ReportCustomerHelper helper) {
        ExpressionList<Customer> where = find.where();
        
		if((helper.customerId != null)) {
			where = where.eq("id", helper.customerId);
		}

        if((helper.customerName != null)) {
        	where = where.or(Expr.ilike("firstName", helper.customerName + "%"), Expr.ilike("lastName", helper.customerName + "%"));
        }
        if((helper.customerPhone != null)) {
        	where = where.eq("phoneNumber", helper.customerPhone);
        }
        
        if((helper.personalIdType != null)) {
        	where = where.eq("personalIdType", helper.personalIdType);
        }
        
        if((helper.personalId != null)) {
        	where = where.ilike("personalId", helper.personalId + "%");
        }
        
        PagingList<Customer> pagingList = where
                .orderBy(helper.sortBy + " " + helper.order)
                .fetch("agent")
                .fetch("country")
                .fetch("personalIdCountry")
                .findPagingList(helper.pageSize)
                .setFetchAhead(false);
        
        helper.totalRowCount = pagingList.getTotalRowCount();
        helper.totalPageCount = pagingList.getTotalPageCount();
        
        return pagingList.getPage(helper.page);
    }
	
	public static Page<Customer> senderCustomerPage(ReportCustomerHelper helper) {
		String sql ="SELECT DISTINCT(c.*) FROM transactions t LEFT JOIN customers c on t.sender_id = c.id";
		
		RawSql rawSql = RawSqlBuilder.unparsed(sql).create();
		
		Query<Customer> query = Ebean.find(Customer.class);
		query.setVanillaMode(true).setRawSql(rawSql);
		
		if((helper.customerId != null)) {
			query.where().eq("id", helper.customerId);
		}

        if((helper.customerName != null)) {
        	query.where().or(Expr.ilike("firstName", helper.customerName + "%"), Expr.ilike("lastName", helper.customerName + "%"));
        }
        if((helper.customerPhone != null)) {
        	query.where().eq("phoneNumber", helper.customerPhone);
        }
        
        if((helper.personalIdType != null)) {
        	query.where().eq("personalIdType", helper.personalIdType);
        }
        
        if((helper.personalId != null)) {
        	query.where().ilike("personalId", helper.personalId + "%");
        }
        
        return query
                .orderBy(helper.sortBy + " " + helper.order)
                .fetch("agent")
                .fetch("country")
                .fetch("personalIdCountry")
                .findPagingList(helper.pageSize)
                .setFetchAhead(false)
                .getPage(helper.page);
    }
	
}
