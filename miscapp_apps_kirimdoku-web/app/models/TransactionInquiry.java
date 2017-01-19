package models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

import play.Logger;
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
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Page;
import com.avaje.ebean.SqlRow;
import com.avaje.ebean.TxType;
import com.avaje.ebean.annotation.Transactional;

import controllers.helpers.BatchUploadDetailHelper;
import controllers.helpers.BatchUploadHelper;
import controllers.helpers.SessionHelper;
import controllers.tokens.TransactionInquiryToken;
import doku.kirimdoku.util.CommonUtil;

@Entity
@Table(name = "transaction_inquiries")
public class TransactionInquiry extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = true, unique = true)
	public Long id;

	@Transient
	public String idToken;

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	public Type typeId;

	@ManyToOne(optional = true, cascade = {})
	@Column(nullable = false)
	public User user;

	@Column(nullable = false)
	public Date requestTime;

	@Column(nullable = true)
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
	public String senderCountryCode;
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
	public String beneficiaryCountryCode;
	@JsonIgnore
	public String sendingCountryCode;
	@JsonIgnore
	public String sendingCurrencyCode;
	@JsonIgnore
	public String receivingCountryCode;
	@JsonIgnore
	public String receivingCurrencyCode;
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
	
	
	@JsonProperty
    public String getIdToken() {
		try {
			Logger.debug("ID Token : "+this.idToken);
			if (this.idToken != null) return this.idToken;
			return this.idToken = TransactionInquiryToken.fromTransactionInquiry(this).toString();			
		} catch (Exception e) {
			Logger.debug("Exception : "+e.getMessage());
		}
		return null;
	}
    
	
	public static final Finder<Long, TransactionInquiry> find = new Finder<Long, TransactionInquiry>(Long.class, TransactionInquiry.class);

	public static TransactionInquiry findByCode(String code) {
		TransactionInquiryToken token = TransactionInquiryToken.fromString(code);
		if(token != null) {
			return find.byId(token.id);
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
	
	public static int saveInquiryBatch(BatchUploadDetailHelper batchUploadDetailHelper, User user, ForexReference forexReference, Batch batch) {
		int status = 0;
		try {
			TransactionInquiry transactionInquiry = new TransactionInquiry();
			transactionInquiry.id = getSeq();
			transactionInquiry.typeId = Type.BATCH_UPLOAD;
			transactionInquiry.requestTime = new Date();
			transactionInquiry.user = user;
			transactionInquiry.forexReference = forexReference;
			transactionInquiry.channel = Channel.find.byId(batchUploadDetailHelper.getChannelCode());
			transactionInquiry.accountId = batchUploadDetailHelper.getBankAccountNumber();
			transactionInquiry.accountName = batchUploadDetailHelper.getBeneficiaryFirstName()+" "+batchUploadDetailHelper.getBeneficiaryLastName();
			transactionInquiry.senderCid = batchUploadDetailHelper.getSenderCID();
			transactionInquiry.senderFirstName = batchUploadDetailHelper.getSenderFirstName();
			transactionInquiry.senderLastName = batchUploadDetailHelper.getSenderLastName();
			transactionInquiry.senderPhoneNumber = batchUploadDetailHelper.getSenderPhoneNumber();
			transactionInquiry.senderPersonalIdType = batchUploadDetailHelper.getSenderPersonalIdType();
			transactionInquiry.senderPersonalId = batchUploadDetailHelper.getSenderPersonalId();
			transactionInquiry.senderCountryCode = batchUploadDetailHelper.getSenderCountryCode();
			transactionInquiry.senderDateOfBirth = CommonUtil.sdfDate.parse(batchUploadDetailHelper.getSenderDateOfBirth());
			transactionInquiry.beneficiaryCid = batchUploadDetailHelper.getBeneficiaryCID();
			transactionInquiry.beneficiaryFirstName = batchUploadDetailHelper.getBeneficiaryFirstName();
			transactionInquiry.beneficiaryLastName = batchUploadDetailHelper.getBeneficiaryLastName();
			transactionInquiry.beneficiaryPhoneNumber = batchUploadDetailHelper.getBeneficiaryPhoneNumber();
			transactionInquiry.beneficiaryCountryCode = batchUploadDetailHelper.getBeneficiaryCountryCode();
			
			transactionInquiry.sendingCountryCode = batchUploadDetailHelper.getSendingCountryCode();
			transactionInquiry.sendingCurrencyCode = batchUploadDetailHelper.getSendingCurrencyCode();
			transactionInquiry.receivingCountryCode = batchUploadDetailHelper.getReceivingCountryCode();
			transactionInquiry.receivingCurrencyCode = batchUploadDetailHelper.getReceivingCurrencyCode();
			transactionInquiry.accountBankId = batchUploadDetailHelper.getBankId();
			transactionInquiry.accountBankCode = batchUploadDetailHelper.getBankSwiftCode();
			transactionInquiry.accountBankCity = batchUploadDetailHelper.getBankCity();
			transactionInquiry.dokuWalletId = batchUploadDetailHelper.getDokuWalletId();
			transactionInquiry.sendType = batchUploadDetailHelper.getSendType().charAt(0);
			transactionInquiry.sendingAmount = batchUploadDetailHelper.getSendingAmount() != null ? new BigDecimal(batchUploadDetailHelper.getSendingAmount()) : null;
			
			transactionInquiry.status = TransactionInquiryStatus.FAILED.code;
			transactionInquiry.state = TransactionInquiryState.INITIAL.code;
			transactionInquiry.batch = batch;
			transactionInquiry.save();
			transactionInquiry.refresh();
			status = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
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
	
	@Transactional(type = TxType.NOT_SUPPORTED)
	public static List<TransactionInquiry> getInquiryDataToSend(int batchId, Long userId) {
		try {
			List<TransactionInquiry> inquirys = TransactionInquiry.find.where()
					.add(Expr.eq("user.id", userId))
					.add(Expr.eq("state", TransactionInquiryState.INITIAL.code))
					.add(Expr.eq("status", TransactionInquiryStatus.FAILED.code))
					.add(Expr.eq("batch.id", batchId))
					.setMaxRows(50000)
					.findList();
			return inquirys;
		} catch (javax.persistence.OptimisticLockException e) {
			Logger.debug("OptimisticLockException :: "+e.getMessage());
		} catch (NullPointerException e) {
			Logger.debug("NullPointerException :: "+e.getMessage());
		} catch (Exception e) {
			Logger.debug("Exception :: "+e.getMessage());
		}
		return null;
	}
	
	public static List<TransactionInquiry> getInquiryDataToRemit(int batchId) {
		try {
			List<TransactionInquiry> inquirys = TransactionInquiry.find.where()
					.add(Expr.eq("user.corporate", SessionHelper.getUser().corporate))
					.add(Expr.eq("batch.id", batchId))
					.add(Expr.eq("state", TransactionInquiryState.DONE.code))
					.add(Expr.eq("status", TransactionInquiryStatus.SUCCESS.code))
					.setMaxRows(10000)
					.findList();
			return inquirys;
		} catch (javax.persistence.OptimisticLockException e) {
			Logger.debug("OptimisticLockException :: "+e.getMessage());
		} catch (NullPointerException e) {
			Logger.debug("NullPointerException :: "+e.getMessage());
		} catch (Exception e) {
			Logger.debug("Exception :: "+e.getMessage());
		}
		return null;
	}
	
	
	public static Page<TransactionInquiry> reportPageBatch(User user, int batchId, int page, int pageSize, String filter) {
        try {
        	Page<TransactionInquiry> inquiries = find.where()
        			.eq("batch.id", batchId)
        			.or(Expr.ilike("senderPersonalId", "%"+filter+"%"), Expr.or(Expr.ilike("senderFirstName", "%"+filter+"%"), Expr.ilike("senderLastName", "%"+filter+"%")))
        			.order().asc("requestTime")
        			.findPagingList(pageSize)
	                .setFetchAhead(false)
	                .getPage(page);
        	return inquiries;
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return null;
    }
}
