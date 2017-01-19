package models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import play.Logger;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import be.objectify.deadbolt.core.models.Permission;
import be.objectify.deadbolt.core.models.Role;
import be.objectify.deadbolt.core.models.Subject;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Page;
import com.avaje.ebean.Query;
import com.avaje.ebean.RawSql;
import com.avaje.ebean.RawSqlBuilder;
import com.avaje.ebean.SqlRow;
import com.avaje.ebean.annotation.Sql;

import controllers.tokens.AgentToken;

@Entity
@Table(name = "users")
public class User extends Model implements Subject {

	private static final long serialVersionUID = 1L;

	@Id
	@JsonIgnore
	public Long id;

	@Transient
	public String idToken;
	
	@ManyToOne(optional = false)
	public Corporate corporate;

	@Column(nullable = false, length = 64)
	@Constraints.Required
	public String firstName;

	@Constraints.Required
	@Column(nullable = false, length = 64)
	public String lastName;

	@Constraints.Required
	@Column(nullable = true, unique = true, length = 64)
	public String email;

	@Column(nullable = true, length = 128)
	@JsonIgnore
	public String password;

	@ManyToOne(optional = true)
	@Column(nullable=true)
	public Country country;

	@Column(nullable=true)
	public String cityName;
	
	@Column(nullable = true, length = 255)
	public String address;
	
	@Column(nullable = true, length = 255)
	@JsonIgnore
	public String sessionId;
	
	@Column(nullable = true, length = 50)
	@JsonIgnore
	public String locationLong;
	
	@Column(nullable = true, length = 50)
	@JsonIgnore
	public String locationLat;
	
	public static enum Gender { 
	    MALE,
	    FEMALE
	}
	@Column(nullable = false)
	@Constraints.Required
	public Gender gender;

	@Column(nullable = true, columnDefinition="DATE")
	@Formats.DateTime(pattern = "yyyy-mm-dd")
	public Date birthDate;

	@Column(nullable = true, length = 16)
	public String personalIdType;

	@Column(nullable = true, length = 32)
	public String personalId;

	@Column(nullable = true, length = 24)
	public String phoneNumber;

	@Column(nullable = true, length = 24)
	public String mobileNumber;
	
	@Column(nullable = true, length = 10)
	public String postalCode;

	@ManyToOne(optional = true)
	@JsonIgnore
	public User supervisor;

	@Column(nullable = false)
	@JsonIgnore
	public Boolean isAdmin = Boolean.FALSE;

	@Column(nullable = true, length = 64)
	public String photo;
	
	public enum Status {
		INACTIVE, ACTIVE, WEAKPASSWORD
	};
	@Column(nullable = false)
	public Status status = Status.INACTIVE;
	
	@Column(nullable = true)
	public Date lastLoginTime;

	@Column(nullable = false)
	@JsonIgnore
	public BigDecimal creditLimit = BigDecimal.ZERO;
	
	@Column(nullable = false)
	@JsonIgnore
	public BigDecimal creditAlertLimit = BigDecimal.ZERO;
	
	@ManyToMany
	@JsonIgnore
	public List<SecurityRole> roles = new ArrayList<SecurityRole>();

	@Column(nullable = true, length = 255)
	@JsonIgnore
	public String lastSessionId;
	
	@Column(nullable = true, length = 255)
	@JsonIgnore
	public Date sessionTime;
	
	@Column(nullable = false)
	@JsonIgnore
	public BigDecimal creditLastBalance = BigDecimal.ZERO;
	
//	@ManyToMany
//	@JsonIgnore
//	public List<UserPermission> permissions = new ArrayList<UserPermission>();
	
	public static final Finder<Long, User> find = new Finder<Long, User>(Long.class, User.class);

	@Override
	@JsonIgnore
	public String getIdentifier() {
		return id.toString();
	}
	
	@Override
	@JsonIgnore
	public List<? extends Role> getRoles() {
		return roles;
	}

	@Override
	@JsonIgnore
	public List<? extends Permission> getPermissions() {
//		return permissions;
		return null;
	}

	public void addRole(SecurityRole role) {
		if (!roles.contains(role)) {
			roles.add(role);
		}
		this.saveManyToManyAssociations("roles");
	}

	@JsonProperty
	public String idToken() {
		try {
			if(this.idToken != null) return this.idToken;
			return this.idToken = AgentToken.fromUser(this).toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
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
	
	@JsonIgnore
	public boolean isActive() {
		return status == Status.ACTIVE; 
	}
	
	public List<Object> findChildrenIds() {
		return queryUserChildrens(this).findIds();
	}

	public List<User> findChildrens() {
		return queryUserChildrens(this).findList();
	}

	public static User findByUserName(String userName) {
		return find.fetch("country").where().eq("name", userName).findUnique();
	}

	public static User authenticate(String email, String password) {
		return find.where().eq("email", email).eq("password", password).eq("isAdmin", false).findUnique();
	}

	public static User authenticateAdmin(String email, String password) {
		return find.where().eq("email", email).eq("password", password).eq("isAdmin", true).findUnique();
	}

	public boolean hasRole(String roleName) {
		for (Role r : getRoles()) {
			if (r.getName().equals(roleName)) {
				return true;
			}
		}
		return false;
	}

	public static User findByEmail(String email) {
		return find.fetch("corporate").where().eq("email", email).findUnique();
	}

	public static Map<String,String> supervisorOptionsForUser(User user) {
        ExpressionList<User> where = User.find.where().eq("corporate", user.corporate).eq("roles.role", "supervisor");
        
        List<User> users = where.orderBy("firstName").findList();
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(User u: users) {
            options.put(String.valueOf(u.id), u.fullName());
        }
        return options;
    }
	
	public static Page<User> page(Corporate corporate, User user, SecurityRole role, int page, int pageSize, String sortBy, String order, String filter) {
		ExpressionList<User> where = find.where();
		
		if (corporate != null) {
			where = where.eq("corporate", corporate);
		}
		
		if (user != null) {
			where = where.eq("supervisor", user);
		}
		
		if(role != null) {
			where = where.eq("roles.role", role.role);
		}

		try {
			AgentToken token = AgentToken.fromString(filter);
			where = where.eq("id", token.id);
			filter = null;
		} catch(Exception e) {}
		
		if ((filter != null) && (filter.length() > 0)) {
			where = where.ilike("firstName", "%" + filter + "%");
		}
		return where.orderBy(sortBy + " " + order).findPagingList(pageSize).setFetchAhead(false).getPage(page);
	}

	public static Query<User> queryUserChildrens(User user) {
		ExpressionList<User> where = User.find.where();

		if (user.hasRole("doku")) {
		} else if (user.hasRole("mainagent")) {
			where.eq("corporate", user.corporate);
		} else if (user.hasRole("supervisor")) {
			where.eq("corporate", user.corporate);
			where.or(Expr.eq("id", user.id), Expr.eq("supervisor_id", user.id));
		} else {
			where.eq("corporate", user.corporate);
			where.eq("id", user.id);
		}

		return where.orderBy("id");
	}

	public static List<TopAgents> findTopAgents(User user) {
		String sql = "SELECT users.id, users.first_name, users.last_name, countries.code, countries.name, users.photo, count(transactions.id) AS totalTransactions " +
				"FROM ((users JOIN countries ON (((countries.code)::text = (users.country_code)::text))) " +
				"LEFT JOIN transactions ON (((transactions.user_id = users.id) AND ((date(transactions.created_time) >= (('now'::text)::date - '1 mon'::interval)) AND (date(transactions.created_time) <= ('now'::text)::date))))) " +
				"WHERE users.corporate_code='"+user.corporate.code+"' "+
				"GROUP BY users.id, users.first_name, users.last_name, countries.code, countries.name, users.photo " +
				"ORDER BY count(transactions.id) DESC LIMIT 9";
		
		RawSql rawSql = RawSqlBuilder.parse(sql)
				// map results
				.columnMapping("users.id", "user.id")
				.columnMapping("users.first_name", "user.firstName")
				.columnMapping("users.last_name", "user.lastName")
				.columnMapping("users.photo", "user.photo")
				.columnMapping("countries.code", "country.code")
				.columnMapping("countries.name", "country.name")
				.create();
		
		return Ebean.find(TopAgents.class)
				.setRawSql(rawSql)
				.fetch("user")
				.fetch("country", "name")
				.findList();
	}

	@Entity
	@Sql
	public static class TopAgents {
		@OneToOne
		public User user;
		
		@OneToOne
		public Country country;
		
		public long totalTransactions;
	}

	public boolean isAccessibleByUser(models.User user) {
		if(user.hasRole("doku")) {
			return true;
		} else if(user.hasRole("mainagent")) {
			if(this.corporate.equals(user.corporate)) {
				return true;
			}
		} else if(user.hasRole("supervisor")) {
			if(this.equals(user)) {
				return true;
			} else if((this.supervisor != null) && (this.supervisor.equals(user))) {
				return true;
			}
		} else if(this.equals(user)) {
			return true;
		}
		return false;
	}
	
	public static void updateCreditLastBalance(User agent, BigDecimal sendMoney) {
		if (agent.creditLimit.compareTo(BigDecimal.ZERO) > 0) {
			Ebean.createSqlUpdate("UPDATE users SET credit_last_balance = (credit_last_balance-(:sendMoney)) where id = :agent").setParameter("sendMoney",  sendMoney.setScale(0, RoundingMode.UP)).setParameter("agent", agent.id).execute();
		}
	}
	
	public boolean agentHasExceedSendLimit(BigDecimal sendAmount) {
		if (this.creditLimit.compareTo(BigDecimal.ZERO) > 0) {
			Logger.debug("credit limit      : "+this.creditLimit);
			Logger.debug("last balance      : "+this.creditLastBalance);
			Logger.debug("sender amount     : "+sendAmount.doubleValue());
			BigDecimal totalSendAmount = BigDecimal.ZERO;
			totalSendAmount = totalSendAmount.add(this.creditLastBalance.subtract(sendAmount));
			Logger.debug("total send amount : "+totalSendAmount);
			Logger.debug("agent send limit  : "+this.creditAlertLimit);
			if(totalSendAmount.compareTo(this.creditAlertLimit) < 0)
				return true;
			return false;
		}
		return false;
	}
	
	public BigDecimal creditLeft() {
		BigDecimal creditLeftTotal = BigDecimal.ZERO;
		if (this.creditLimit.compareTo(BigDecimal.ZERO) > 0)
		try {
			String sql = "SELECT SUM(sender_amount) AS total_sender_amount "
					+ " FROM corporates INNER JOIN transactions ON(transactions.corporate_code=corporates.code)" 
					+ " WHERE corporate_code='" + this.corporate.code + "' AND agent_id = " + this.id
					+ " AND transactions.settlement_id IS NULL";
			
			creditLeftTotal = creditLeftTotal.add(this.creditLimit);
			
			SqlRow row = Ebean.createSqlQuery(sql).setMaxRows(0)
					.findUnique();
			creditLeftTotal = creditLeftTotal.subtract(row.getBigDecimal("total_sender_amount"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return creditLeftTotal;
	}
	
	public static void updateSessionTime(Long userId) {
		try {
			Ebean.createSqlUpdate("UPDATE users SET session_time = now() WHERE id = :userId").setParameter("userId", userId).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
