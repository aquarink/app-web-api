package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Page;

import controllers.tokens.CustomerToken;

import play.data.format.Formats;
import play.db.ebean.Model;

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
	
	public static final Finder<Long, CustomerBan> find = new Finder<Long, CustomerBan>(Long.class, CustomerBan.class);
	
}
