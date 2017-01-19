package models;

import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;
import com.avaje.ebean.RawSql;
import com.avaje.ebean.RawSqlBuilder;
import com.avaje.ebean.annotation.Sql;
import play.Logger;
import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
@Table(name = "corporates")
public class Corporate extends Model {

	private static final long serialVersionUID = 1L;

	public static final String MAIN_CORPORATE_CODE = "DOK";

	@Id
	@Column(nullable = false, length = 3)
	@Constraints.Required
	public String code;

	@Constraints.Required
	@Column(nullable = false, length = 64)
	@Length(min = 3, max = 64)
	public String name;

	@Column(nullable = true, length = 64)
	@Length(max = 64)
	public String tradeName;

	@Column(nullable = true, length = 64)
	@Length(max = 64)
	public String businessType;

	@Column(nullable = true, length = 64)
	@Length(max = 64)
	public String licenseType;

	@Column(nullable = true, length = 64)
	@Length(max = 64)
	public String licenseNumber;

	@ManyToOne(optional = true)
	public Country country;

	@ManyToOne(optional = true)
	public Currency currency;

	@Column(nullable = true, length = 64)
	@Length(max = 64)
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

	public static enum Status {
		INACTIVE, ACTIVE
	};

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
	public int permission;

	@Column(nullable = true, length = 16)
	@JsonIgnore
	public String encryptionKey;

	@OneToOne(optional = true, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@MapsId
	public CorporateOperation operation;

	@OneToOne(optional = true, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@MapsId
	public CorporateFinance finance;

	@OneToOne(optional = true, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@MapsId
	public CorporateSettlement settlement;

	@OneToOne(optional = true, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@MapsId
	public CorporateConfiguration configuration;

	public static final int PERMISSION_SEND = 2;
	public static final int PERMISSION_RECEIVE = 4;

	public static final Finder<String, Corporate> find = new Finder<String, Corporate>(String.class, Corporate.class);

	public boolean hasPermission(int reqPerm) {
		return (this.permission & reqPerm) != 0;
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
//			System.out.println("Amount : "+this.totalUnsettledSenderAmount);
//			System.out.println("Amount : "+sendAmount);
//			System.out.println("Amount : "+this.corporate.creditLimit);
//			
			if (this.totalUnsettledSenderAmount.add(sendAmount).intValue() >= this.corporate.creditLimit.intValue()) {
				
				return true;
			}
			return false;
		}

		public boolean hasExceedCreditAlertLimit() {
			if (this.remainingLimit.intValue() <= this.corporate.creditAlertLimit.intValue()) {
				Logger.debug("Corporate credit limit has exceeded");
				return true;
			}
			return false;
		}
	}
}
