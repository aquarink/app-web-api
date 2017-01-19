package models;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.avaje.ebean.annotation.Sql;
import controllers.helpers.EncryptionHelper;
import controllers.helpers.SessionHelper;
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
	public Country country;

	@Column(nullable=true)
	public String cityName;

	@Column(nullable = true, length = 255)
	public String address;

	@Column(nullable = true, length = 255)
	public String sessionId;
	
	@Column(nullable = true, length = 50)
	public String locationLong;
	
	@Column(nullable = true, length = 50)
	public String locationLat;
	
	public static enum Gender { 
	    MALE,
	    FEMALE
	}
	@Column(nullable = false)
	@Constraints.Required
	public Gender gender;

	@Column(nullable = true)
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
	@JsonIgnore
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
		return null;
	}

	public void addRole(SecurityRole role) {
		if (!roles.contains(role)) {
			roles.add(role);
		}
		this.saveManyToManyAssociations("roles");
	}

	@JsonProperty
	public String getIdToken() {
		try {
			if(this.idToken != null) return this.idToken;
			return this.idToken = AgentToken.fromUser(this).toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public String idToken() {return this.getIdToken();}

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
	 public String getBirthDateFormatted() {
    	SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", SessionHelper.getLocale());
    	return df.format(this.birthDate);
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
		try {
			return find.fetch("corporate").where().eq("email", email).eq("password", EncryptionHelper.encrypt(password)).eq("isAdmin", false).ne("status", Status.INACTIVE).findUnique();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static User authenticateAdmin(String email, String password) {
		try {
			return find.fetch("corporate").where().eq("email", email).eq("password", EncryptionHelper.encrypt(password)).eq("isAdmin", true).findUnique();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
		return find.where().eq("email", email).findUnique();
	}

	public static Map<String,String> optionsRoles() {
		return optionsRoles(SessionHelper.getUser());
	}
	public static Map<String,String> optionsRoles(User user) {
		ExpressionList<SecurityRole> where = SecurityRole.find.where();
		if(user.hasRole("admin")) {
		} else if(user.hasRole("mainagent")) {
		} else if(user.hasRole("supervisor")) {
		}
        
		List<SecurityRole> roles = where.findList();
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(SecurityRole r: roles) {
            options.put(String.valueOf(r.role), r.description);
        }
        return options;
    }
	
	public static Map<String,String> optionsMainAgentForUser(User user) {
        ExpressionList<User> where = User.find.where().eq("corporate", user.corporate).eq("roles.role", "mainagent");
        
        if(SessionHelper.getUser().hasRole("admin")) {
        } else if(SessionHelper.getUser().hasRole("mainagent")) {
        	where.eq("id", SessionHelper.getUser().id);
        }
        List<User> users = where.orderBy("firstName").findList();
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(User u: users) {
            options.put(String.valueOf(u.id), u.fullName());
        }
        return options;
	}
	
	public static Map<String,String> optionsSupervisorForUser(User user) {
        ExpressionList<User> where = User.find.where().eq("corporate", user.corporate).eq("roles.role", "supervisor");
        
        if(SessionHelper.getUser().hasRole("admin")) {
        } else if(SessionHelper.getUser().hasRole("mainagent")) {
        	where.eq("supervisor.id", SessionHelper.getUser().id);
        } else if(SessionHelper.getUser().hasRole("supervisor")) {
        	where.eq("id", SessionHelper.getUser().id);
        }
        List<User> users = where.orderBy("firstName").findList();
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(User u: users) {
            options.put(String.valueOf(u.id), u.fullName());
        }
        return options;
    }
	
	public static Map<String,String> optionsSupervisorAlternativeForUser(User user) {
        ExpressionList<User> where = User.find.where().eq("corporate", user.corporate).eq("roles.role", "supervisor").ne("id", user.id);
        
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
			where.ilike("first_name||' '||last_name", "%" + filter + "%");
		}
		return where.orderBy(sortBy + " " + order).findPagingList(pageSize).setFetchAhead(false).getPage(page);
	}

	public static Page<User> pageSetLimit(Corporate corporate, User user, SecurityRole role, int page, int pageSize, String sortBy, String order, String filter) {
		ExpressionList<User> where = find.where();
		System.out.println("pageSetLimit : ###");
		if (corporate != null) {
			System.out.println("Corporate : "+corporate.code);
			where = where.eq("corporate", corporate);
		}
		
		if (user != null) {
			where = where.eq("supervisor", user);
		}
		
		if(role != null) {
			System.out.println("Role : "+role.role);
			where = where.eq("roles.role", role.role);
		}

		try {
			System.out.println("Filter : "+role.role);
			AgentToken token = AgentToken.fromString(filter);
			where = where.eq("id", token.id);
			filter = null;
		} catch(Exception e) {}
		
		if ((filter != null) && (filter.length() > 0)) {
			where.ilike("first_name||' '||last_name", "%" + filter + "%");
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
				"LEFT JOIN transactions ON (((transactions.agent_id = users.id) AND ((date(transactions.created_time) >= (('now'::text)::date - '1 mon'::interval)) AND (date(transactions.created_time) <= ('now'::text)::date))))) " +
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
		
		try {
			return Ebean.find(TopAgents.class)
					.setRawSql(rawSql)
					.fetch("user")
					.fetch("country", "name")
					.findList();
		} catch(Exception e) {
			Logger.error("Ebean raw sql execution error "+e.getMessage());
			return null;
		}
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

    public String getPhotoUrl() {
    	//routes.
    	//TODO
    	
//        String url = routes.Assets.at("images/user_profile.png").toString();
//        if(this.photo != null) {
//            url = routes.Assets.at("images/" + this.photo).toString();
//        }
//        return url;
    	return "/images/user_profile.png";
    }

	public boolean isAccessibleByUser(models.User user) {
		if(user.hasRole("admin") || user.hasRole("admin_operasional") || user.hasRole("finance")) {
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
	
	
}
