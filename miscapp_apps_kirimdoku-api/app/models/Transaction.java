package models;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import kirimdoku.helpers.ReportTransactionHelper;
import kirimdoku.util.Utility;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.node.ObjectNode;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Page;
import com.avaje.ebean.PagingList;
import com.avaje.ebean.Query;
import com.avaje.ebean.RawSqlBuilder;
import com.avaje.ebean.SqlRow;
import com.avaje.ebean.annotation.Encrypted;
import com.avaje.ebean.annotation.Sql;

import play.Logger;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.mvc.Result;
import play.mvc.Results;
import controllers.helpers.Definition;
import controllers.helpers.ForexHelper;
import controllers.helpers.ResponseHelper;
import controllers.tokens.TransactionToken;

@Entity
@Table(name = "transactions", 
uniqueConstraints={
		@UniqueConstraint(columnNames={"corporate_code", "send_trx_id"})
		, @UniqueConstraint(columnNames={"corporate_code", "receive_trx_id"})
	}
)
public class Transaction extends Model {

	private static final long serialVersionUID = 1L;

	@Id
    @JsonIgnore
    public Long id;
    
    @Transient
    public String idToken;

//    @Constraints.Required
    @ManyToOne(optional = false, cascade = {}, fetch = FetchType.EAGER)
    public Corporate corporate;

//    @Constraints.Required
    @ManyToOne(optional = true, cascade = {}, fetch = FetchType.EAGER)
    public User agent;

    @Constraints.Required
    @ManyToOne(optional = false, cascade = {}, fetch = FetchType.EAGER)
    public Channel channel;

//    @Constraints.Required
    @Column(nullable = false)
    public Date createdTime;
    
    @Column(nullable = true)
    public Date modifiedTime;

    @Constraints.Required
    @Column(nullable = false)
    public int status;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @OrderColumn
    public Calendar cashInTime;

    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    public Calendar cashOutTime;

    @Constraints.Required
    @ManyToOne(optional = false, cascade = {}, fetch = FetchType.EAGER)
    public Customer sender;

    @Constraints.Required
    @ManyToOne(optional = false, cascade = {}, fetch = FetchType.EAGER)
    public Country senderCountry;

    @Constraints.Required
    @ManyToOne(optional = false, cascade = {}, fetch = FetchType.EAGER)
    public Currency senderCurrency;

    @Constraints.Required
    @Column(nullable = false, precision = 16, scale = 6)
    public BigDecimal senderAmount;

    @Constraints.Required
    @ManyToOne(optional = false, cascade = {}, fetch = FetchType.EAGER)
    public Customer beneficiary;

    @Constraints.Required
    @ManyToOne(optional = false, cascade = {}, fetch = FetchType.EAGER)
    public Country beneficiaryCountry;

    @Constraints.Required
    @ManyToOne(optional = false, cascade = {}, fetch = FetchType.EAGER)
    public Currency beneficiaryCurrency;

//    @Constraints.Required
    @Column(nullable = false, precision = 16, scale = 6)
    public BigDecimal beneficiaryAmount;

//    @Constraints.Required
    @ManyToOne(optional = false, cascade = {}, fetch = FetchType.EAGER)
    public ForexReference forexReference;

//    @Constraints.Required
    @Column(nullable = true)
    public String beneficiaryCity;

    @OneToMany(cascade = {}, fetch = FetchType.EAGER)
    public List<TransactionFee> fees;
    
    @Constraints.Required
    @Column(nullable = true)
    @JsonIgnore
    @Encrypted(dbEncryption=false)
    public String auth1;

    // @Constraints.Required
    @Column(nullable = true)
    @JsonIgnore
    @Encrypted(dbEncryption=false)
    public String auth2;

    @Column(nullable = true)
    public String senderNote;
    
    @ManyToOne(optional = true, cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    public User beneficiaryAgent;

    @OneToOne(optional = true, cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    public CustomerAccount beneficiaryAccount;
    
    @Column(nullable=true)
    public String sendTrxId;
    
    @Column(nullable=true)
    public String receiveTrxId;
    
    @ManyToOne(optional = true, cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JsonIgnore
    public Settlement settlement;
    
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JsonIgnore
    public List<Label> labels;
    
    @JsonIgnore
    public Long shareSenderId;

    @JsonIgnore
    public Long shareReceiverId;
    
    @Column(nullable=true)
    public String voucherCode;
    
    @Column(nullable=true)
    public String beneficiaryWalletId;
    
    @Column(nullable=true)
    public String beneficiaryWalletName;
    
    @JsonIgnore
    public String paymentData;
    
    @JsonIgnore
    public String remitResponseCode;
    
    @JsonIgnore
    public String remitResponseMessage;
    
    @JsonIgnore
    @ManyToOne(optional = true, cascade = {}, fetch = FetchType.EAGER)
    public Batch batch;
    
    public static final int STATUS_UNKNOWN = 0;
    public static final int STATUS_CREATED = 10;
    public static final int STATUS_PENDING = 20;
    public static final int STATUS_LOCKED = 30;
	public static final int STATUS_UNPAID = 35;
    public static final int STATUS_REFUNDED = 40;
    public static final int STATUS_FULLREFUNDED = 41;
    public static final int STATUS_PAID = 50;
    public static final int STATUS_PENDING_REFUND = 26;

    @JsonProperty
    public String idToken() {
		if (this.idToken != null) return this.idToken;
    	return this.idToken = TransactionToken.fromTransaction(this).toString();
    }
    
    public static final Finder<Long, Transaction> find = new Finder<Long, Transaction>(Long.class, Transaction.class);

	public static models.Transaction findByToken(TransactionToken token) {
		if(token != null) {
			return find.where().idEq(token.transactionId).setMaxRows(0).findUnique();
		}
		return null;
	}
	
	
	@Override
	public void save() {
		if((this.id == null) || (this.id == 0)) {
	        SqlRow row = Ebean.createSqlQuery("SELECT nextval('transactions_seq')").findUnique();
	        Integer nextId = row.getInteger("nextval");
	        this.id = (long) ((16807 * nextId + 1) % 814748);
		}
		
        if (this.agent != null && this.agent.corporate != null){
            ShareSetting shareSenderSetting = new ShareSetting().findActiveStatus(this.agent.corporate);
            this.shareSenderId = shareSenderSetting.getId();
        }
       
        if (this.beneficiaryAgent != null && this.beneficiaryAgent.corporate != null){
            ShareSetting shareReceiverSetting = new ShareSetting().findActiveStatus(this.beneficiaryAgent.corporate);
            this.shareReceiverId = shareReceiverSetting.getId();
        }
		
		super.save();
	}
	
	
	

	protected class ConfigRules {
//		public final static int LIMIT_SEND_DAY_COUNT = 1; // 1 default
//		public final static int LIMIT_SEND_DAY_SUM = 8499; // in USD
//		public final static int LIMIT_SEND_DAY_SUM_STR = 8499; // In USD
//		public final static int LIMIT_SEND_WEEK_SUM = 8499; // In USD
//		public final static int LIMIT_SEND_MONTH_RECEIVER_COUNT = 50;
//		public static final int LIMIT_RECEIVE_DAY_COUNT = 1;
//		public static final int LIMIT_RECEIVE_DAY_COUNT_STR = 1;
//		public static final int LIMIT_RECEIVE_DAY_SUM_STR = 8499;
//		public static final int LIMIT_RECEIVE_WEEK_SUM_STR = 8499;
		
		public final static int LIMIT_SEND_DAY_COUNT = 10; // 10 default
		public final static int LIMIT_SEND_DAY_SUM = 100000000; // in IDR
		public final static int LIMIT_SEND_DAY_SUM_STR = 100000000; // In IDR
		public final static int LIMIT_SEND_WEEK_SUM = 100000000; // In IDR
		public final static int LIMIT_SEND_MONTH_RECEIVER_COUNT = 50;
		public static final int LIMIT_RECEIVE_DAY_COUNT = 50;
		public static final int LIMIT_RECEIVE_DAY_COUNT_STR = 50;
		public static final int LIMIT_RECEIVE_DAY_SUM_STR = 100000000; // In IDR
		public static final int LIMIT_RECEIVE_WEEK_SUM_STR = 100000000; // In IDR
		
	}
	
	
	@Entity @Sql
	public static class SendRules {
		public BigDecimal totalTodaySenderAmount = BigDecimal.ZERO;
		public BigDecimal totalTodaySenderCount = BigDecimal.ZERO;
		public BigDecimal totalWeekSenderAmount = BigDecimal.ZERO;
		public BigDecimal totalMonthSenderReceiverCount = BigDecimal.ZERO;
		
		@Override
		public String toString() {
			return "totalTodaySenderAmount:"+totalTodaySenderAmount+" totalTodaySenderCount:"+totalTodaySenderCount+" totalWeekSenderAmount:"+totalWeekSenderAmount+" totalMonthSenderReceiverCount:"+totalMonthSenderReceiverCount;
		}
	};

	@Entity @Sql
	public static class ReceiveRules {
		public BigDecimal totalTodayReceiveCount = BigDecimal.ZERO;
		public BigDecimal totalTodayReceiveAmount = BigDecimal.ZERO;
		public BigDecimal totalWeekReceiveAmount = BigDecimal.ZERO;
		public BigDecimal totalMonthReceiveCount = BigDecimal.ZERO;
		
		@Override
		public String toString() {
			return "totalTodayReceiveCount:"+totalTodayReceiveCount+" totalTodayReceiveAmount:"+totalTodayReceiveAmount+" totalWeekReceiveAmount:"+totalWeekReceiveAmount+" totalMonthReceiveCount:"+totalMonthReceiveCount;
		}
	}
	
	public static Result checkSendRules(Transaction t, User user) {
		try {
			boolean rules41 = false;
			String sql = "SELECT COALESCE(COUNT(CASE WHEN cash_in_time > (CURRENT_TIMESTAMP - interval '24 hours') THEN id ELSE null END), 0) AS totalTodaySenderCount, COALESCE(SUM(CASE WHEN cash_in_time > (CURRENT_TIMESTAMP - interval '24 hours') THEN sender_amount ELSE null END), 0) AS totalTodaySenderAmount, COALESCE(SUM(CASE WHEN cash_in_time > (CURRENT_TIMESTAMP - interval '1 WEEKS') THEN sender_amount ELSE null END), 0) AS totalWeekSenderAmount"
					+ " FROM transactions "
					+ " WHERE cash_in_time > (CURRENT_TIMESTAMP - interval '1 months' ) AND (sender_id = "+t.sender.id+")";
			Query<SendRules> qRule1 = Ebean.find(SendRules.class);
			qRule1.setVanillaMode(true).setRawSql(RawSqlBuilder.unparsed(sql)
					.columnMapping("totalTodaySenderCount", "totalTodaySenderCount")
					.columnMapping("totalTodaySenderAmount", "totalTodaySenderAmount")
					.columnMapping("totalWeekSenderAmount", "totalWeekSenderAmount")
					.create());
			
			SendRules sRules = qRule1.findUnique();
			if(sRules != null) {
				Logger.info("sendRules: "+sRules);
				if (t.channel.code.equals("10")) {
					//Except Channel Bill Payment
					return null;
				}
//				if ((sRules.totalTodaySenderCount != null) && sRules.totalTodaySenderCount.longValue() >= ConfigRules.LIMIT_SEND_DAY_COUNT) {
//					return Results.forbidden(ResponseHelper.createErrorResponse(31, "Sender has exceed sending limit", null));
//				}
				
				if (sRules != null && sRules.totalTodaySenderAmount != null && (t.corporate.configuration != null && !t.corporate.configuration.allowHighValueTransfer)) {					
					ForexReference usdRef = ForexHelper.getLastForexReference(t.corporate, t.senderCurrency, models.Currency.find.byId("USD"));
					if(usdRef != null) {
						BigDecimal todayUSDAmount = ForexHelper.convertCurrency(sRules.totalTodaySenderAmount.add(t.senderAmount), usdRef);
						BigDecimal weekUSDAmount = ForexHelper.convertCurrency(sRules.totalWeekSenderAmount.add(t.senderAmount), usdRef);
						if (todayUSDAmount.longValue() > ConfigRules.LIMIT_SEND_DAY_SUM) {
							return Results.forbidden(ResponseHelper.createErrorResponse(31, "Sender has exceed sending amount limit", null));
						} else if (todayUSDAmount.longValue() > ConfigRules.LIMIT_SEND_DAY_SUM_STR) {
							rules41 = true;
							t.labels.add(Label.find.byId(41L));
						} else if (weekUSDAmount.longValue() > ConfigRules.LIMIT_SEND_WEEK_SUM) {
							rules41 = true;
							t.labels.add(Label.find.byId(41L));
						}
					}
				}
			}
		
			String rsql = "SELECT COALESCE(COUNT(CASE WHEN cash_in_time > (CURRENT_TIMESTAMP - interval '24 hours') THEN id ELSE null END), 0) AS totalTodayReceiveCount"
					+ " FROM transactions "
					+ " WHERE cash_in_time > (CURRENT_TIMESTAMP - interval '24 hours' ) AND (beneficiary_id = "+t.beneficiary.id+")";
			Query<ReceiveRules> rrule = Ebean.find(ReceiveRules.class);
			rrule.setVanillaMode(true).setRawSql(RawSqlBuilder.unparsed(rsql)
					.columnMapping("totalTodayReceiveCount", "totalTodayReceiveCount")
					.create());
			ReceiveRules rRules = rrule.findUnique();
			
			Logger.info("SenderRules ReceiveRules: "+rRules);
			if((rRules != null) && (rRules.totalTodayReceiveCount != null)) {
				if(rRules.totalTodayReceiveCount.longValue() > ConfigRules.LIMIT_RECEIVE_DAY_COUNT) {
					if (!rules41)
					t.labels.add(Label.find.byId(41L));
					rules41 = true;
				}
			}
			
			String rsql2 = "SELECT COUNT(DISTINCT beneficiary_id) totalMonthReceiveCount"
					+ " FROM transactions "
					+ " WHERE cash_in_time > (CURRENT_TIMESTAMP - interval '1 months' ) AND (sender_id = "+t.sender.id+")";
			Query<ReceiveRules> rrule2 = Ebean.find(ReceiveRules.class);
			rrule2.setVanillaMode(true).setRawSql(RawSqlBuilder.unparsed(rsql2)
					.columnMapping("totalMonthReceiveCount", "totalMonthReceiveCount")
					.create());
			ReceiveRules rRules2 = rrule2.findUnique();
			
			Logger.info("SenderRules ReceiveRules2: "+rRules2);
			if((rRules2 != null)) {
				if(rRules2.totalMonthReceiveCount.longValue() > ConfigRules.LIMIT_SEND_MONTH_RECEIVER_COUNT) {
					if (!rules41)
					t.labels.add(Label.find.byId(41L));
					rules41 = true;
				}
			}
			
			if (!t.channel.code.equals("10")) {
				Date today = new Date();
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date createTransaction = sdf1.parse(today.getYear()+"-"+today.getMonth()+"-"+today.getDate()+" 00:00:00");
				List<Transaction> transactions = find.where()
					.eq("sender.id", t.sender.id)
					.eq("beneficiary.id", t.beneficiary.id)
					.gt("createdTime", createTransaction)
					.eq("channel.code", t.channel.code)
					.eq("senderAmount", t.senderAmount)
					.eq("status", STATUS_PAID).findList();
				
				if (transactions != null && transactions.size() > 0) {
					return Results.forbidden(ResponseHelper.createErrorResponse(50, "Sender sending same transaction more than once a day", null));
				}
			}
			
			if (t.corporate.customerSendLimit.doubleValue() < t.senderAmount.doubleValue()) {
				if (!rules41)
				t.labels.add(Label.find.byId(41L));
				return Results.forbidden(ResponseHelper.createErrorResponse(41, "Sender has sending transaction with exceeding accumulation limit", null));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			Logger.error("Exception "+e);
		}

		return null;
	}
	
	public static ObjectNode checkSendRules(long senderId, long beneficiaryId, String channelCode, User user, Currency senderCurrency, BigDecimal sendingAmount, List<Label> labels) {
		Logger.debug("CHECK SEND RULES");
		try {
			boolean rules41 = false;
			String sql = "SELECT COALESCE(COUNT(CASE WHEN cash_in_time > (CURRENT_TIMESTAMP - interval '24 hours') THEN id ELSE null END), 0) AS totalTodaySenderCount, COALESCE(SUM(CASE WHEN cash_in_time > (CURRENT_TIMESTAMP - interval '24 hours') THEN sender_amount ELSE null END), 0) AS totalTodaySenderAmount, COALESCE(SUM(CASE WHEN cash_in_time > (CURRENT_TIMESTAMP - interval '1 WEEKS') THEN sender_amount ELSE null END), 0) AS totalWeekSenderAmount"
					+ " FROM transactions "
					+ " WHERE cash_in_time > (CURRENT_TIMESTAMP - interval '1 months' ) AND (sender_id = "+senderId+")";
			Query<SendRules> qRule1 = Ebean.find(SendRules.class);
			qRule1.setVanillaMode(true).setRawSql(RawSqlBuilder.unparsed(sql)
					.columnMapping("totalTodaySenderCount", "totalTodaySenderCount")
					.columnMapping("totalTodaySenderAmount", "totalTodaySenderAmount")
					.columnMapping("totalWeekSenderAmount", "totalWeekSenderAmount")
					.create());
			
			SendRules sRules = qRule1.findUnique();
			if(sRules != null) {
				Logger.info("sendRules: "+sRules);
				if (channelCode.equals("10")) {
					//Except Channel Bill Payment
					return null;
				}
				if (sRules != null && sRules.totalTodaySenderAmount != null && (user.corporate.configuration != null && !user.corporate.configuration.allowHighValueTransfer)) {					
					ForexReference usdRef = ForexHelper.getLastForexReference(user.corporate, senderCurrency, models.Currency.find.byId("IDR"));
					if(usdRef != null) {
						BigDecimal todayIDRAmount = ForexHelper.convertCurrency(sRules.totalTodaySenderAmount.add(sendingAmount), usdRef);
						BigDecimal weekUSDAmount = ForexHelper.convertCurrency(sRules.totalWeekSenderAmount.add(sendingAmount), usdRef);
						if (todayIDRAmount.longValue() > ConfigRules.LIMIT_SEND_DAY_SUM) {
							return ResponseHelper.createErrorResponse(31, "Sender has exceed sending amount limit", null);
						} else if (todayIDRAmount.longValue() > ConfigRules.LIMIT_SEND_DAY_SUM_STR) {
							rules41 = true;
							labels.add(Label.find.byId(41L));
						} else if (weekUSDAmount.longValue() > ConfigRules.LIMIT_SEND_WEEK_SUM) {
							rules41 = true;
							labels.add(Label.find.byId(41L));
						}
					}
				}
			}
		
			String rsql = "SELECT COALESCE(COUNT(CASE WHEN cash_in_time > (CURRENT_TIMESTAMP - interval '24 hours') THEN id ELSE null END), 0) AS totalTodayReceiveCount"
					+ " FROM transactions "
					+ " WHERE cash_in_time > (CURRENT_TIMESTAMP - interval '24 hours' ) AND (beneficiary_id = "+beneficiaryId+")";
			Query<ReceiveRules> rrule = Ebean.find(ReceiveRules.class);
			rrule.setVanillaMode(true).setRawSql(RawSqlBuilder.unparsed(rsql)
					.columnMapping("totalTodayReceiveCount", "totalTodayReceiveCount")
					.create());
			ReceiveRules rRules = rrule.findUnique();
			
			Logger.info("SenderRules ReceiveRules: "+rRules);
			if((rRules != null) && (rRules.totalTodayReceiveCount != null)) {
				if(rRules.totalTodayReceiveCount.longValue() > ConfigRules.LIMIT_RECEIVE_DAY_COUNT) {
					if (!rules41)
					labels.add(Label.find.byId(41L));
					rules41 = true;
				}
			}
			
			String rsql2 = "SELECT COUNT(DISTINCT beneficiary_id) totalMonthReceiveCount"
					+ " FROM transactions "
					+ " WHERE cash_in_time > (CURRENT_TIMESTAMP - interval '1 months' ) AND (sender_id = "+senderId+")";
			Query<ReceiveRules> rrule2 = Ebean.find(ReceiveRules.class);
			rrule2.setVanillaMode(true).setRawSql(RawSqlBuilder.unparsed(rsql2)
					.columnMapping("totalMonthReceiveCount", "totalMonthReceiveCount")
					.create());
			ReceiveRules rRules2 = rrule2.findUnique();
			
			Logger.info("SenderRules ReceiveRules2: "+rRules2);
			if((rRules2 != null)) {
				if(rRules2.totalMonthReceiveCount.longValue() > ConfigRules.LIMIT_SEND_MONTH_RECEIVER_COUNT) {
					if (!rules41)
					labels.add(Label.find.byId(41L));
					rules41 = true;
				}
			}
			
			if (!channelCode.equals("10")) {
				Date today = new Date();
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date createTransaction = sdf1.parse(today.getYear()+"-"+today.getMonth()+"-"+today.getDate()+" 00:00:00");
				List<Transaction> transactions = find.where()
					.eq("sender.id", senderId)
					.eq("beneficiary.id", beneficiaryId)
					.gt("createdTime", createTransaction)
					.eq("channel.code", channelCode)
					.eq("senderAmount", sendingAmount)
					.eq("status", STATUS_PAID).findList();
				
				if (transactions != null && transactions.size() > 0) {
					return ResponseHelper.createErrorResponse(50, "Sender sending same transaction more than once a day", null);
				}
			}
			
			if (user.corporate.customerSendLimit.doubleValue() < sendingAmount.doubleValue()) {
				if (!rules41)
				labels.add(Label.find.byId(41L));
				return ResponseHelper.createErrorResponse(41, "Sender has sending transaction with exceeding accumulation limit", null);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			Logger.error("Exception "+e);
			return ResponseHelper.createResponse(Definition.STATUS_ILLEGALPARAMS,e.getMessage());
		}

		return null;
	}
	
	public static Result checkReceiveRules(Transaction t, User user) {
		try {
			String sql = "SELECT COALESCE(COUNT(CASE WHEN cash_out_time > (CURRENT_TIMESTAMP - interval '24 hours') THEN id ELSE null END), 0) AS totalTodayReceiveCount, COALESCE(SUM(CASE WHEN cash_out_time > (CURRENT_TIMESTAMP - interval '24 hours') THEN beneficiary_amount ELSE null END),0) AS totalTodayReceiveAmount, COALESCE(SUM(CASE WHEN cash_out_time > (CURRENT_TIMESTAMP - interval '1 WEEKS') THEN beneficiary_amount ELSE null END), 0) AS totalWeekReceiveAmount"
					+ " FROM transactions "
					+ " WHERE cash_in_time > (CURRENT_TIMESTAMP - interval '1 months' ) AND (beneficiary_id = "+t.beneficiary.id+")";
			Query<ReceiveRules> qRule1 = Ebean.find(ReceiveRules.class);
			qRule1.setVanillaMode(true).setRawSql(RawSqlBuilder.unparsed(sql)
					.columnMapping("totalTodayReceiveCount", "totalTodayReceiveCount")
					.columnMapping("totalTodayReceiveAmount", "totalTodayReceiveAmount")
					.columnMapping("totalWeekReceiveAmount", "totalWeekReceiveAmount")
					.create());
			
			ReceiveRules rRules = qRule1.findUnique();
			
			if((rRules != null) && (rRules.totalTodayReceiveCount != null)) {
				if(rRules.totalTodayReceiveCount.longValue() > ConfigRules.LIMIT_RECEIVE_DAY_COUNT) {
					return Results.forbidden(ResponseHelper.createErrorResponse(31, "Receiver has exceeded its limit for the last 24 hours", null));
				} else if(rRules.totalTodayReceiveCount.longValue() > ConfigRules.LIMIT_RECEIVE_DAY_COUNT_STR) {
					t.labels.add(Label.find.byId(41L));
					t.save();
				}
				
				ForexReference usdRef = ForexHelper.getLastForexReference(t.corporate, t.senderCurrency, models.Currency.find.byId("USD"));
				if(usdRef != null && (user.corporate.configuration != null && !user.corporate.configuration.allowHighValueTransfer)) {
					BigDecimal todayUSDAmount = ForexHelper.convertCurrency(rRules.totalTodayReceiveAmount.add(t.beneficiaryAmount), usdRef);
					BigDecimal weekUSDAmount = ForexHelper.convertCurrency(rRules.totalWeekReceiveAmount.add(t.beneficiaryAmount), usdRef);
					
					if(todayUSDAmount.longValue() > ConfigRules.LIMIT_RECEIVE_DAY_SUM_STR) {
						t.labels.add(Label.find.byId(41L));
						t.save();
					} else if(weekUSDAmount.longValue() > ConfigRules.LIMIT_RECEIVE_WEEK_SUM_STR) {
						t.labels.add(Label.find.byId(41L));
						t.save();
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			Logger.error("Exception "+e);
		}

		return null;
	}
	
	public static Page<Transaction> reportPage(ReportTransactionHelper helper) {
		ExpressionList<Transaction> where = Ebean.createQuery(Transaction.class, "find Transaction").setAutofetch(false).where();
        where = where.or(Expr.eq("corporate", helper.user.corporate), Expr.eq("beneficiaryAgent.corporate", helper.user.corporate));
        
        if(helper.agentFilter != null) {
        	List<Object> childrens = models.User.find.byId(helper.agentFilter.id).findChildrenIds();
        	where = where.or(Expr.in("agent_id", childrens), Expr.in("beneficiary_agent_id", childrens));
        }
		if(helper.transactionId != null) {
			where = where.eq("id", helper.transactionId);
		} else if (helper.transactionCode != null && !helper.transactionCode.equals("")) {
			return null;
		}
        
        if((helper.senderCountry != null)) {
        	where = where.eq("senderCountry", helper.senderCountry);
        }
        if((helper.channelCode != null)) {
        	where = where.eq("channel.code", helper.channelCode);
        }
        if((helper.beneficiaryCountry != null)) {
        	where = where.eq("beneficiaryCountry", helper.beneficiaryCountry);
        }
        if ((helper.trxStartDate != null) && (helper.trxEndDate != null) ) {
        	try {
				Date startDate = Utility.datetimeFormatShow.parse(helper.trxStartDate+" 00:00:00");
				Date endDate = Utility.datetimeFormatShow.parse(helper.trxEndDate+" 23:59:59");
				where = where.between("cash_in_time", startDate, endDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
        } else if ((helper.trxStartDate != null)) {
        	try {
				Date startDate = Utility.datetimeFormatShow.parse(helper.trxStartDate+" 00:00:00");
				where = where.gt("cash_in_time", startDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
        } else if ((helper.trxEndDate != null)) {
        	try {
        		Date endDate = Utility.datetimeFormatShow.parse(helper.trxEndDate+" 23:59:59");
        		where = where.lt("cash_in_time", endDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
        }
        if(helper.transactionsStatus != null && helper.transactionsStatus != 0) {
        	where = where.eq("status", helper.transactionsStatus);
        }
        if(helper.customerName != null && !helper.customerName.trim().equals("")) {
        	where = where.or(Expr.ilike("sender.firstName", helper.customerName + "%"),Expr.ilike("sender.lastName", helper.customerName + "%"));
        }
        PagingList<Transaction> pagingList =  where
	        .orderBy(helper.sortBy + " " + helper.order)
	        .fetch("channel")
	        .fetch("corporate")
	        .fetch("corporate.country")
	        .fetch("corporate.operation")
	        .fetch("corporate.finance")
	        .fetch("corporate.settlement")
	        .fetch("corporate.configuration")
	        .fetch("senderCountry")
	        .fetch("beneficiaryCountry")
	        .fetch("forexReference")
	        .fetch("forexReference.forex")
	        .fetch("sender")
	        .fetch("beneficiary")
	        .fetch("beneficiaryAccount")
	        .fetch("beneficiaryAccount.bank")
	        .fetch("agent")
	        .fetch("agent.country")
	        .findPagingList(helper.pageSize)
	        .setFetchAhead(false);
        helper.totalRowCount = pagingList.getTotalRowCount();
        helper.totalPageCount = pagingList.getTotalPageCount();
        
        return pagingList.getPage(helper.page);
    }
}
