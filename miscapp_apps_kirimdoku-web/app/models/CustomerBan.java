package models;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import play.data.format.Formats;
import play.db.ebean.Model;

import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Page;

import controllers.helpers.SessionHelper;
import controllers.tokens.CustomerToken;

@Entity
@Table(name = "customer_bans")
public class CustomerBan extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	@Column(nullable = false)
	@ManyToOne(optional = false)
	public Country country;
	
	@Column(nullable = false)
	public String firstName;
	
	@Column(nullable = false)
	public String lastName;
	
	@Column(nullable = true, columnDefinition="DATE")
	@Formats.DateTime(pattern = "yyyy-MM-dd")
	public Date birthDate;

	@Column(nullable = true)
	public String reason;
	
	@OneToOne(optional = true)
	@Column(unique = true, nullable=true)
	public Customer customer;
	
	@Column(nullable = false)
	public Date created;
	
	@Column(nullable = true)
	public Date modified;
	
	public String birthDateFormat() {
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy", SessionHelper.getLocale());
		return df.format(this.birthDate);
	}
	
	public static final Finder<Long, CustomerBan> find = new Finder<Long, CustomerBan>(Long.class, CustomerBan.class);
	
	public static CustomerBan findByToken(String tokenStr) {
		return findByToken(CustomerToken.fromString(tokenStr));
	}
	public static CustomerBan findByToken(CustomerToken token) {
		return find.fetch("country").where().eq("customer.id", token.id).findUnique();
	}
	public static CustomerBan findByCustomer(Customer customer) {
		return find.fetch("country").where().eq("customer.id", customer.id).findUnique();
	}
	
	public static CustomerBan findPositiveMatch(Customer c) {
		return find.where().eq("country", c.country).ieq("firstName", c.firstName).ieq("lastName", c.lastName).eq("birthDate", c.birthDate).setMaxRows(1).findUnique();
	}
	
	public static Page<CustomerBan> pageCustomerBans(int page, int pageSize, String sortBy, String order, String filter) {
		Long id = null;
		try {
			CustomerToken token = CustomerToken.fromString(filter);
			id = token.id;
		} catch (Exception e) {}

		ExpressionList<CustomerBan> where = find.where().isNotNull("customer");
		if (id != null) {
			where.eq("customer.id", id);
		} else if(filter != null && !filter.isEmpty()){
			where.ilike("firstName||' '||lastName", "%" + filter + "%");
		}
		return where.orderBy(sortBy + " " + order).findPagingList(pageSize).setFetchAhead(false).getPage(page);
	}
	
	public static Page<CustomerBan> pageInterdictions(int page, int pageSize, String sortBy, String order, String filter) {
		ExpressionList<CustomerBan> where = find.where();
		where.isNull("customer.id");
		if((filter != null) && (!filter.isEmpty())) {
			where.ilike("firstName||' '||lastName", "%" + filter + "%");
		}
		return where.orderBy(sortBy + " " + order).findPagingList(pageSize).setFetchAhead(false).getPage(page);
	}
}
