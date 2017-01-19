package models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import com.avaje.ebean.SqlRow;
import com.avaje.ebean.TxType;
import com.avaje.ebean.annotation.Transactional;

import controllers.tokens.TransactionInquiryToken;
import play.data.validation.Constraints;

@Entity
@Table(name = "transaction_inquiries")
public class TransactionInquiry extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = true, unique = true)
	@JsonIgnore
	public Long id;

	@Transient
	public String idToken;

	@Constraints.Required
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	@JsonIgnore
	public Type typeId;

	@Constraints.Required
	@ManyToOne(optional = true, cascade = {})
	@Column(nullable = false)
	@JsonIgnore
	public User user;

	@Constraints.Required
	@Column(nullable = false)
	public Date requestTime;

	@Column(nullable = true)
	@JsonIgnore
	public Date updateTime;

	@ManyToOne(optional = true, cascade = {})
    public ForexReference forexReference;
	
	@ManyToOne(optional = true, cascade = {})
	@Column(nullable = true)
	public Transaction transaction;

	@Column(nullable = true)
	public String validationId;

	@Column(nullable = true)
	public String collectId;

	@Column(nullable = false, columnDefinition = "int default 0")
	public int invalidAuthCount = 0;

	@JsonIgnore
	@Column(nullable = true)
	public String accountType;
	
	@JsonIgnore
	@Column(nullable = true)
	public String accountId;
	
	@JsonIgnore
	@Column(nullable = true)
	public String accountName;
	
	@JsonIgnore
	@Column(nullable = true)
	public BigDecimal beneficiaryAmount;
	
	// ========
	@JsonIgnore
	public String senderCid;
	@JsonIgnore
	public String senderFirstName;
	@JsonIgnore
	public String senderLastName;
	@JsonIgnore
	public String senderPhoneNumber;
	@JsonIgnore
	public String senderPersonalIdType;
	@JsonIgnore
	public String senderPersonalId;
	
	@JsonIgnore
	@ManyToOne(optional = false)
	@Column(name = "sender_country_code")
	public Country senderCountry;
	
	@JsonIgnore
	public Date senderDateOfBirth;
	@JsonIgnore
	public String beneficiaryCid;
	@JsonIgnore
	public String beneficiaryFirstName;
	@JsonIgnore
	public String beneficiaryLastName;
	@JsonIgnore
	public String beneficiaryPhoneNumber;
	
	@JsonIgnore
	@ManyToOne(optional = false)
	@Column(name = "beneficiary_country_code")
	public Country beneficiaryCountry;
	
	@JsonIgnore
	@ManyToOne(optional = false)
	@Column(name = "sending_country_code")
	public Country sendingCountry;
	
	@JsonIgnore
	@ManyToOne(optional = false)
	@Column(name = "sending_currency_code")
	public Currency sendingCurrency;
	
	@JsonIgnore
	@ManyToOne(optional = false)
	@Column(name = "receiving_country_code")
	public Country receivingCountry;
	
	@JsonIgnore
	@ManyToOne(optional = false)
	@Column(name = "receiving_currency_code")
	public Currency receivingCurrency;
	
	@JsonIgnore
	public String accountBankId;
	@JsonIgnore
	public String accountBankCode;
	@JsonIgnore
	public String accountBankCity;
	@JsonIgnore
	public String dokuWalletId;
	@JsonIgnore
	public String dokuWalletName;
	@JsonIgnore
	public Character sendType;
	@JsonIgnore
	public BigDecimal sendingAmount;
	@JsonIgnore
	public Character status = 'F';
	@JsonIgnore
	public Character state = 'I';
	@JsonIgnore
	public String inquiryResponseCode;
	@JsonIgnore
	public String inquiryResponseMessage;
	@JsonIgnore
	@ManyToOne(optional = false)
	@Column(name = "channel_code")
	public Channel channel;
	@JsonIgnore
	@ManyToOne(optional = true, cascade = {})
	@Column(nullable = false)
	public Batch batch;
	@JsonIgnore
	@Column(nullable = true)
	public Date remitRequestTime;
	
	// JPA Wouldn't define statuscode, except ordinal but we just put status code for later purpose
	public static enum Type {
		NETWORK_PING(0),
		TRANSACTION_INFO(1),
		CASHIN_INQUIRY(2),
		CASHIN_REMIT(3),
		CASHOUT_INQUIRY(4),
		CASHOUT_VALIDATE(5),
		CASHOUT_COLLECT(6),
		BATCH_UPLOAD(7);

		private int value;

		Type(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
		
		@Override
		public String toString() {
			return this.name();
		}
	}

	public static enum TransactionInquiryStatus {
		SUCCESS('S'),
		FAILED('F');

		private char code;

		TransactionInquiryStatus(char code) {
			this.code = code;
		}

		public char getCode() {
			return code;
		}
		
		@Override
		public String toString() {
			return this.name();
		}
	}
	
	public static enum TransactionInquiryState {
		INITIAL('I'),
		PROCESSING_INQUIRY('P'),
		PROCESSING_REMIT('R'),
		DONE('D');

		private char code;

		TransactionInquiryState(char code) {
			this.code = code;
		}

		public char getCode() {
			return code;
		}
		
		@Override
		public String toString() {
			return this.name();
		}
	}
		
	// JPA Wouldn't define statuscode, except ordinal but we just put status code for later purpose
//	public static enum Type {
//		NETWORK_PING(1),
//		TRANSACTION_INFO(6),
//		CASHIN_INQUIRY(11),
//		CASHIN_REMIT(16),
//		CASHOUT_INQUIRY(21),
//		CASHOUT_VALIDATE(22),
//		CASHOUT_COLLECT(23);
//
//		private int value;
//
//		Type(int value) {
//			this.value = value;
//		}
//
//		public int getValue() {
//			return value;
//		}
//		
//		@Override
//		public String toString() {
//			return this.name();
//		}
//	}

	@JsonProperty
    public String getIdToken() {
		if (this.idToken != null) return this.idToken;
		return this.idToken = TransactionInquiryToken.fromTransactionInquiry(this).toString();
	}
    
	public static final Finder<Long, TransactionInquiry> find = new Finder<Long, TransactionInquiry>(Long.class, TransactionInquiry.class);

	public static TransactionInquiry findByCode(String code) {
		TransactionInquiryToken token = TransactionInquiryToken.fromString(code);
		if(token != null) {
			long DAY_IN_MS = 1000 * 60 * 60 * 24;
			Date weekAgo = new Date(System.currentTimeMillis() - (7 * DAY_IN_MS));
			
			TransactionInquiry inquiry = find.where()
					.idEq(token.id)
					.between("request_time", new java.sql.Date(weekAgo.getTime()), new java.sql.Date(new Date().getTime()+(86400*1000)))
					.findUnique();
			if (inquiry != null) {
				inquiry.getIdToken();
				return inquiry;
			}
		}
		return null;
	}

	public static int findRowCountByInvalidAuth(Transaction transaction) {

		SqlRow row = Ebean.createSqlQuery("select sum(invalid_auth_count) AS totalCount from transaction_inquiries where transaction_id=" + transaction.id).setMaxRows(0)
				.findUnique();
		return row.getInteger("totalCount");
	}

	public static String formatAsCode(TransactionInquiry transactionInquiry) {
		TransactionInquiryToken token = TransactionInquiryToken.fromTransactionInquiry(transactionInquiry);
		return token.toString();
	}

	public void generateValidationId() {
		Random r = new Random(this.id);
		int r1 = r.nextInt(98) + 1;
		String enc = "80" + String.format("%02d", r1) + String.format("%03d", this.id) + String.format("%07d", (this.id * (r1 + 288)));

		this.validationId = enc;
	}

	public void generateCollectId() {
		Random r = new Random(this.id);
		int r1 = r.nextInt(98) + 1;
		String enc = "20" + String.format("%02d", r1) + String.format("%03d", this.id) + String.format("%07d", (this.id * (r1 + 288)));

		this.collectId = enc;
	}
	
	public static void resetInvalidAuthByTransaction(models.Transaction transaction) {
		Ebean.createSqlUpdate("UPDATE transaction_inquiries SET  invalid_auth_count=0 where transaction_id=:transaction_id").setParameter("transaction_id",  transaction.id).execute();
	}
	
	public static TransactionInquiry findTransactionInquiryByTransactionIdUser(Long transactionId, User user) {
		TransactionInquiry transactionInquiry = find.where()
				.add(Expr.eq("transaction.id", transactionId))
				.add(Expr.eq("user", user)).findUnique();
		return transactionInquiry;
	}
	
	@Transactional(type = TxType.NOT_SUPPORTED)
    public static long getSeq() {
    	try {
	        SqlRow row = Ebean.createSqlQuery("SELECT nextval('transaction_inquiries_seq')").findUnique();
	        long nextId = row.getLong("nextval");
	        return nextId;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return 0;
    }
    
	public static TransactionInquiry getTransactionInquiryById(long transactionInquiryId) {
		try {
			TransactionInquiry transactionInquiry = find
					.fetch("channel")
					.where()
					.add(Expr.eq("id", transactionInquiryId))
					.add(Expr.eq("state", TransactionInquiryState.PROCESSING_INQUIRY.code))
					.add(Expr.eq("status", TransactionInquiryStatus.FAILED.code))
					.orderBy()
					.desc("id")
					.findUnique();
			return transactionInquiry;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
