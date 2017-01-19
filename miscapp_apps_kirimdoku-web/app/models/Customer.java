package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Page;
import com.avaje.ebean.Query;
import com.avaje.ebean.RawSql;
import com.avaje.ebean.RawSqlBuilder;
import com.avaje.ebean.annotation.Sql;

import controllers.helpers.SessionHelper;
import controllers.tokens.CustomerToken;
import play.Logger;
import play.data.Form;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "customers")
public class Customer extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	public Long id;

	@Transient
	public String idToken;
	
	@ManyToOne(optional = true)
	@Constraints.Required
	public User agent;

	@Column(nullable = false, length = 64)
	@Constraints.Required
	public String firstName;

	@Column(nullable = true, length = 64)
	public String lastName;

	@ManyToOne(optional = false)
	@Constraints.Required
	public Country country;

	@Column(nullable = true, length = 64)
	public String cityName;

	@Column(nullable = false, length = 255)
	public String address;

	public static enum Gender { 
	    MALE,
	    FEMALE
	}

	@Column(nullable = true)
	public Gender gender;
	
	@Column(nullable = true)
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
	public Country personalIdCountry;

	@Column(nullable = true)
	@Formats.DateTime(pattern = "yyyy-MM-dd")
	public Date personalIdIssueDate;

	@Column(nullable = true)
	@Formats.DateTime(pattern = "yyyy-MM-dd")
	public Date personalIdExpireDate;
	
	@Column(nullable = true, length = 20)
	@Constraints.Required
	public String phoneNumber; // It shall not be string

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

	public String fullName() {
		if ((firstName != null) && (lastName != null) && (!lastName.equals(""))) {
			if (firstName.equals(lastName)) {
				return firstName;
			} else {
				return firstName + " " + lastName;
			}
		} else if (firstName != null) {
			return firstName;
		} else {
			return "";
		}
	}

	@JsonIgnore
	public String getBirthDateFormatted() {
		if (this.birthDate != null) {
			SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy", SessionHelper.getLocale());
			return df.format(this.birthDate);
		} else return "";
	}
	public String birthDateFormat() {
		return getBirthDateFormatted();
	}

	public static final Finder<Long, Customer> find = new Finder<Long, Customer>(Long.class, Customer.class);

	public static Customer findByToken(String tokenStr) {
		return findByToken(CustomerToken.fromString(tokenStr));
	}
	public static Customer findByToken(CustomerToken token) {
		return find.fetch("country").where().eq("id", token.id).findUnique();
	}
	
	public static Customer findByName(String name) {
		//TODO More robust search?
		return find.fetch("country").where()
				.ieq("firstName", name)
//				.ieq("firstName", name)
//				.ilike("firstName", '%' + name + '%')
				.findUnique();
	}

	public static Customer findByEmail(String email) {
		return find.fetch("country").fetch("city").where().eq("email", email).findUnique();
	}

	public static Page<Customer> findByQuickSearch(String query) {

		ExpressionList<Customer> where = find.fetch("country").where();

		Long id = null;
		try {
			CustomerToken token = CustomerToken.fromString(query);
			id = token.id;
		} catch (Exception e) {
		}

		if (id != null) {
			where = where.eq("id", id);
		} else {
			where = where.ilike("firstName", '%' + query + '%');
		}

		return where.findPagingList(10).setFetchAhead(false).getPage(0);
	}
	
	
	public static Customer findByForm(Form<Customer> form) {
		ExpressionList<Customer> where = find.where();
		if(form.get() != null) {
			where = where.eq("country", form.get().country);
			where = where.eq("firstName", form.get().firstName);
			where = where.eq("lastName", form.get().lastName);
			if(form.get().birthDate != null) where = where.eq("birthDate", form.get().birthDate);
			return where.findUnique();
		}
		return null;
	}
	
	public static Customer findByMatch(String countryCode, String firstName, String lastName, Date birthDate) {
		ExpressionList<Customer> where = find.where();
		where = where.eq("country.code", countryCode);
		where = where.eq("firstName", firstName);
		where = where.eq("lastName", lastName);
		if(birthDate != null) where = where.eq("birthDate", birthDate);
		return where.order().desc("id").setMaxRows(1).findUnique();
	}

	public static Page<Customer> page(int page, int pageSize, String sortBy, String order, String filter) {
		try {
			ExpressionList<Customer> where = find.where();
			where.or(Expr.ilike("personalId", "%" + filter + "%"), Expr.or(Expr.ilike("first_name", "%" + filter + "%"), Expr.ilike("last_name", "%" + filter + "%")));
			return where.orderBy(sortBy + " " + order).findPagingList(pageSize).setFetchAhead(false).getPage(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Page<Customer> pageRelate(Customer customer, int page, int pageSize, String sortBy, String order, String filter) {
		try {
			List<Long> customers = CustomerRelate.getCustomerRelateByCustomerId(customer);
			ExpressionList<Customer> where = find.where();
			where.or(Expr.ilike("personalId", "%" + filter + "%"), Expr.or(Expr.ilike("first_name", "%" + filter + "%"), Expr.ilike("last_name", "%" + filter + "%")));
			if (customers != null) {
				where.in("id", customers);
			} else {
				where.isNull("id");
			}
			return where.orderBy(sortBy + " " + order).findPagingList(pageSize).setFetchAhead(false).getPage(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Page<Customer> pageAddRelate(Customer customer, int page, int pageSize, String sortBy, String order, String filter) {
		try {
			List<Long> customers = CustomerRelate.getCustomerRelateByCustomerId(customer);
			if (customers == null) {
				customers = new ArrayList<Long>();
			}
			customers.add(customer.id);
			ExpressionList<Customer> where = find.where();
			where.or(Expr.ilike("personalId", "%" + filter + "%"), Expr.or(Expr.ilike("first_name", "%" + filter + "%"), Expr.ilike("last_name", "%" + filter + "%")));
			Logger.debug("customer : "+customers);
			if (customers != null) {
				where.not(Expr.in("id", customers));
			}
			return where.orderBy(sortBy + " " + order).findPagingList(pageSize).setFetchAhead(false).getPage(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<Customer> csv(String sortBy, String order, String filter) {
		Long id = null;
		try {
			CustomerToken token = CustomerToken.fromString(filter);
			id = token.id;
		} catch (Exception e) {
		}

		ExpressionList<Customer> where = find.where();
		if (id != null) {
			where.eq("id", id);
		} else {
			where.ilike("first_name||' '||last_name", "%" + filter + "%");
		}
		return where.orderBy(sortBy + " " + order).fetch("country").findList();
	}

	public static Map<String, String> optionsPersonalIdType() {
		LinkedHashMap<String, String> options = new LinkedHashMap<String, String>();
		options.put("CITIZENID", "Citizen ID");
		options.put("PASSPORTID", "Passport ID");
		options.put("DRIVERLICENSE", "Driver License");
		options.put("OTHER", "Other ID");
		return options;
	}
	
	public static Map<String, String> optionsGender() {
		LinkedHashMap<String, String> options = new LinkedHashMap<String, String>();
		options.put("MALE", "Male");
		options.put("FEMALE", "Female");
		return options;
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
	

	@Transient
	@JsonIgnore
	public CustomerStatistic statistic;
	
	@JsonIgnore
	public CustomerStatistic getStatistic(int days) {
		
		if(statistic == null) {
			String sql = "SELECT SUM(CASE WHEN sender_id = "+this.id+" THEN sender_amount ELSE 0 END) AS totalSenderAmount, sum(CASE WHEN beneficiary_id = "+this.id+" THEN beneficiary_amount ELSE 0 END) AS totalBeneficiaryAmount"
					+ " FROM transactions "
					+ " WHERE cash_in_time > (CURRENT_TIMESTAMP - interval '"+days+" day' ) AND (sender_id = "+this.id+" OR beneficiary_id = "+this.id+")";
			System.out.println("SQL : "+sql);
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
		
		if (statistic.totalSenderAmount == null) {
			statistic.totalSenderAmount = BigDecimal.ZERO;
		}
		
		if (statistic.totalBeneficiaryAmount == null) {
			statistic.totalBeneficiaryAmount = BigDecimal.ZERO;
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
		
		public boolean hasExceedSendLimit() {
			return hasExceedSendLimit(BigDecimal.ZERO);
		}
		public boolean hasExceedSendLimit(BigDecimal sendAmount) {
			System.out.println("sender amount                 : "+sendAmount.doubleValue());
			System.out.println("total sender amount           : "+this.totalSenderAmount.add(sendAmount).doubleValue());
			System.out.println("corporate customer send limit : "+SessionHelper.getUser().corporate.customerSendLimit.doubleValue());
			
			if(this.totalSenderAmount == null) this.totalSenderAmount = BigDecimal.ZERO;
			if(this.totalSenderAmount.add(sendAmount).doubleValue() > SessionHelper.getUser().corporate.customerSendLimit.doubleValue())
				return true;
			return false;
		}
		
		public boolean hasExceedBeneficiaryLimit() {
			return hasExceedBeneficiaryLimit(BigDecimal.ZERO);
		}
		public boolean hasExceedBeneficiaryLimit(BigDecimal beneficiaryAmount) {
			System.out.println("beneficiary amount            : "+beneficiaryAmount.doubleValue());
			System.out.println("beneficiary amount db         : "+this.totalBeneficiaryAmount.doubleValue());
			System.out.println("total beneficiary amount      : "+this.totalBeneficiaryAmount.add(beneficiaryAmount).doubleValue());
			System.out.println("corporate customer send limit : "+SessionHelper.getUser().corporate.customerSendLimit.doubleValue());
			
			if(this.totalBeneficiaryAmount == null) this.totalBeneficiaryAmount = BigDecimal.ZERO;
			if(this.totalBeneficiaryAmount.add(beneficiaryAmount).doubleValue() >= SessionHelper.getUser().corporate.customerSendLimit.doubleValue())
				return true;
			return false;
		}
	}
}
