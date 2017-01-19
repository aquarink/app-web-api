package models;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Page;
import com.avaje.ebean.Query;
import com.avaje.ebean.RawSql;
import com.avaje.ebean.RawSqlBuilder;
import com.avaje.ebean.annotation.Sql;
import controllers.helpers.SessionHelper;
import controllers.routes;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="corporates")
@NamedQuery(name = "getCreditUsage2", query = "SELECT 66 AS totalSendLimit")
@NamedNativeQuery(name = "getCreditUsage3", query = "SELECT 777 AS totalSendLimit")
public class Corporate extends Model
{

	private static final long serialVersionUID = 1L;

	public static final String MAIN_CORPORATE_CODE = "DOK";

	@Id
    @Column(nullable=false,length=3)
    @Constraints.Required
    public String code;

    @Constraints.Required
    @Column(nullable=false, length = 64)
    @Length(min=3, max=64)
    public String name;
    
    @Column(nullable=true, length = 64)
    @Length(max=64)
    public String tradeName;
    
    @Column(nullable=true, length = 64)
    @Length(max=64)
    public String businessType;
    
    @Column(nullable=true, length = 64)
    @Length(max=64)
    public String licenseType;
    
    @Column(nullable=true, length = 64)
    @Length(max=64)
    public String licenseNumber;
    
	@ManyToOne(optional = true)
    public Country country;
	
	@ManyToOne(optional = true)
	public Currency currency;
	
    @Column(nullable=true, length = 64)
    @Length(max=64)
    public String cityName;
	
	@Column(nullable = true, length = 255)
	public String address;
	
	@Column(nullable = true, length = 10)
	public String postalCode;

	@Column(nullable = true, length = 20)
	public String phoneNumber;

	@Column(nullable = true, length = 20)
	public String faxNumber;

	@Column(nullable = true, length = 64)
	public String email;
    
	public static enum Status {INACTIVE, ACTIVE};
	
	@Column(nullable = false)
	public Status status = Status.INACTIVE;
	
	@Column(nullable = false)
	@JsonIgnore
	public BigDecimal creditLimit;
	
	@Column(nullable = false)
	@JsonIgnore
	public BigDecimal creditAlertLimit;
	
	@Column(nullable = false)
	@JsonIgnore
	public BigDecimal customerSendLimit;
	
    @Column(nullable = false)
    @JsonIgnore
    public int permission;
    
    @Column(nullable = true, length=16)
	@JsonIgnore
    public String encryptionKey;
    
    @OneToOne(optional = true, cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @MapsId
    public CorporateOperation operation;
    
    @OneToOne(optional = true, cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @MapsId
    public CorporateFinance finance;

    @OneToOne(optional = true, cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @MapsId
    public CorporateSettlement settlement;
    
    @OneToOne(optional = true, cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @MapsId
    public CorporateConfiguration configuration;

    public static final int PERMISSION_SEND = 2;
    public static final int PERMISSION_RECEIVE = 4;
    
    public static final Finder<String, Corporate> find = new Finder<String, Corporate>(String.class, Corporate.class);
    
    public boolean hasPermission(int reqPerm) {
    	return (this.permission & reqPerm) != 0;
    }
    
    public static Map<String,String> options() {
    	return optionsByUser(SessionHelper.getUser());
    }
    public static Map<String,String> optionsByUser(User user) {
        ExpressionList<Corporate> where = Corporate.find.where();
        if(user.hasRole("admin") || user.hasRole("finance")) {
        } else {
        	where.idEq(user.corporate.code);
        }
        List<Corporate> corporates = where.orderBy("name").findList();
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Corporate c: corporates) {
            options.put(c.code, c.name);
        }
        return options;
    }

    public static Page<Corporate> page(int page, int pageSize, String sortBy, String order, String filter) {
        return
                find.fetch("country").where()
                        .ilike("name", "%" + filter + "%")
                        .orderBy(sortBy + " " + order)
                        .findPagingList(pageSize)
                        .setFetchAhead(false)
                        .getPage(page);
    }
    
    public String getLogoUrl() {
        String url = routes.Assets.at("images/doku_small.png").toString();
        if((this.configuration != null) && (this.configuration.logoPath != null)) {
            url = routes.Assets.at("images/" + this.configuration.logoPath).toString();
        }
        return url;
    }
	public boolean isAccessibleByUser(models.User user) {
		if(user.hasRole("admin") || user.hasRole("admin_operasional") || user.hasRole("finance")) {
			return true;
		} else {
			if(this.equals(user.corporate)) {
				return true;
			}
		}
		return false;
	}
	
	@Transient
	@JsonIgnore
	CorporateStatistic statistic;

	@JsonIgnore
	public CorporateStatistic getStatistic() {
		if (statistic == null) {
			String sql = "SELECT SUM(sender_amount) AS totalUnsettledSenderAmount"
					+ " FROM corporates INNER JOIN transactions ON(transactions.corporate_code=corporates.code)" + " WHERE corporate_code='" + this.code
					+ "' AND transactions.settlement_id IS NULL";

			RawSql rawSql = RawSqlBuilder.parse(sql).create();

			Query<CorporateStatistic> query = Ebean.find(CorporateStatistic.class);
			query.setRawSql(rawSql);

			statistic = query.findUnique();
			if (statistic == null)
				statistic = new CorporateStatistic();
			statistic.corporate = this;
			if (statistic.totalUnsettledSenderAmount == null)
				statistic.totalUnsettledSenderAmount = BigDecimal.ZERO;
			statistic.remainingLimit = this.creditLimit.subtract(statistic.totalUnsettledSenderAmount);
			
			
		}
		return statistic;
	}
	
	public CorporateOperatorStatistic getOperatorStatistic() {

		RawSql rawSql = RawSqlBuilder.parse("SELECT COUNT(*) AS totalOperator, SUM(credit_limit) AS totalCreditLimit FROM users "+ " WHERE corporate_code='" + this.code+"' GROUP BY corporate_code").create();
		Query<CorporateOperatorStatistic> query = Ebean.find(CorporateOperatorStatistic.class);
		query.setRawSql(rawSql);
		CorporateOperatorStatistic cos = query.findUnique();
		return cos;
	}

	@Entity
	@Sql
	public static class CorporateOperatorStatistic {
		@OneToOne
		public Corporate corporate;

		public Long totalOperator;
		public Long totalCreditLimit;
	}
	
	@Entity
	@Sql
	public static class CorporateStatistic {
		@OneToOne
		public Corporate corporate;

		public int totalOperator;
		public BigDecimal totalUnsettledSenderAmount;
		public BigDecimal remainingLimit;

		public boolean hasExceedCreditLimit() {
			return hasExceedCreditLimit(BigDecimal.ZERO);
		}
		public boolean hasExceedCreditLimit(BigDecimal sendAmount) {
			if (this.totalUnsettledSenderAmount.add(sendAmount).intValue() >= this.corporate.creditLimit.intValue()) {
				return true;
			}
			return false;
		}

		public boolean hasExceedCreditAlertLimit() {
			if (this.remainingLimit.intValue() <= this.corporate.creditAlertLimit.intValue()) {
				return true;
			}
			return false;
		}
	}
}
