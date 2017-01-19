package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Page;
import com.avaje.ebean.Query;
import com.avaje.ebean.SqlQuery;
import com.avaje.ebean.SqlRow;
import com.avaje.ebean.annotation.Encrypted;

import controllers.helpers.BillPaymentDataHelper;
import controllers.tokens.TransactionToken;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public Long id;
    
    @Transient
    public String idToken;

    @Constraints.Required
    @ManyToOne(optional = false, cascade = {}, fetch = FetchType.EAGER)
    public Corporate corporate;

    @Constraints.Required
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

    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
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

//    @Constraints.Required
    @Column(nullable = false, precision = 16, scale = 2)
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
    @Column(nullable = false, precision = 16, scale = 2)
    public BigDecimal beneficiaryAmount;

    //    @Constraints.Required
    @ManyToOne(optional = false, cascade = {}, fetch = FetchType.EAGER)
    public ForexReference forexReference;

    //    @Constraints.Required
    @Column(nullable = true)
    public String beneficiaryCity;
    
    @OneToMany(cascade = {}, fetch = FetchType.EAGER)
    public List<TransactionFee> fees;

    // @Constraints.Required
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
    
    @Column(nullable=true)
    public String paymentData;
    
    @JsonIgnore
    @Column(nullable=true)
    public String remitResponseCode;
    
    @JsonIgnore
    @Column(nullable=true)
    public String remitResponseMessage;
    
    @JsonIgnore
    @ManyToOne(optional = true, cascade = {}, fetch = FetchType.EAGER)
    public Batch batch;
    
    public static final int STATUS_UNKNOWN = 0;
    public static final int STATUS_CREATED = 10;
    public static final int STATUS_PENDING = 20;
    public static final int STATUS_PENDING_REFUND = 26;
    public static final int STATUS_LOCKED = 30;
	public static final int STATUS_UNPAID = 35;
    public static final int STATUS_REFUNDED = 40;
    public static final int STATUS_FULLREFUNDED = 41;
    public static final int STATUS_PAID = 50;
    public static final int STATUS_FAILED = 66;

    public String getIdToken() {
		if (this.idToken != null) return this.idToken;
		return this.idToken = TransactionToken.fromTransaction(this).toString();
	}

	public void setIdToken(String idToken) {
    	this.idToken = idToken;
    	this.id = TransactionToken.fromString(idToken).transactionId;
    }
    
	public String senderAmountFormat() {
		return formatNumber(this.senderAmount);
	}
    public String senderAmountFormat(boolean withSymbol) {
        return models.Currency.format(this.senderAmount, this.senderCurrency.code, withSymbol);
    }

	public String beneficiaryAmountFormat() {
		return formatNumber(this.beneficiaryAmount);
	}
    public String beneficiaryAmountFormat(boolean withSymbol) {
        return models.Currency.format(this.beneficiaryAmount, this.beneficiaryCurrency.code, withSymbol);
    }

    public static String formatDate(Date d) {
    	return formatDate(d, false);
    }
    
    public static String formatNumber(BigDecimal m) {
    	try {
    		NumberFormat nf = new DecimalFormat("#,##0.000000");
    		return nf.format(m);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
    }
    
    public static String formatDate(Date d, boolean withTime) {
        // TODO Real format with locale
        
        SimpleDateFormat df;
        if (withTime) {
            df  = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.getDefault());
        } else {
            df  = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        }
        return df.format(d);
    }

    public static final Finder<Long, Transaction> find = new Finder<Long, Transaction>(Long.class, Transaction.class);

    public static Page<Transaction> page(int page, int pageSize, String sortBy, String order, String filter) {
        return
                find.where()
                        .orderBy(sortBy + " " + order)
                        .fetch("senderCountry")
                        .fetch("beneficiaryCountry")
                        .findPagingList(pageSize)
                        .setFetchAhead(false)
                        .getPage(page);
    }
    
    public static Page<Transaction> pageWithLabels(List<Label> labels, int page, int pageSize, String sortBy, String order, String filter) {
        return
                find.where()
                		.in("labels", labels)
                        .orderBy(sortBy + " " + order)
                        .fetch("senderCountry")
                        .fetch("beneficiaryCountry")
                        .findPagingList(pageSize)
                        .setFetchAhead(false)
                        .getPage(page);
    }

    public static Transaction findById(Long id)
    {
        return find
                .fetch("senderCountry")
                .fetch("beneficiaryCountry")
                .where()
                .eq("id",
                        id)
                .findUnique();
    }

    public static Transaction findByIdToken(String idToken) {
    	TransactionToken token = TransactionToken.fromString(idToken);
    	return Transaction.findById(token.transactionId);
	}

	public static final Map<Integer, String> statusMap = new HashMap<Integer, String>();
    static {
        statusMap.put(0, "UNKNOWN");
        statusMap.put(STATUS_CREATED, "CREATED");
        statusMap.put(STATUS_PENDING, "UNPAID");
        statusMap.put(STATUS_PENDING_REFUND, "UNPAID FULLREFUND");
        statusMap.put(STATUS_LOCKED, "LOCKED");
		statusMap.put(STATUS_UNPAID, "FAILED");
        statusMap.put(STATUS_REFUNDED, "REFUNDED");
        statusMap.put(STATUS_FULLREFUNDED, "FULLREFUNDED");
        statusMap.put(STATUS_PAID, "PAID");
    }

    public String statusText() {
    	return statusMap.get(this.status);
    }
    
    public static String statusText(int i) {
    	return statusMap.get(i);
    }
    
    public String createdFormat() {
    	if(this.createdTime != null) {
    		return formatDate(this.createdTime, true);
    	}
    	return null;
    }
    
    public String modifiedFormat() {
    	if(this.modifiedTime != null) {
    		return formatDate(this.modifiedTime, true);
    	}
    	return null;
    }
    
    public BigDecimal feesTotal() {
    	BigDecimal total = BigDecimal.ZERO;
    	for(TransactionFee fee: this.fees) {
    		total = total.add(fee.amount);
    	}
    	return total;
    }
    public String feesTotalFormat() {
    	return formatNumber(this.feesTotal());
	}
	public String feesTotalFormat(boolean withSymbol) {
    	return models.Currency.format(this.feesTotal(), this.senderCurrency.code, withSymbol);
    }
    
	
    public BigDecimal additionalFeesTotal() {
    	BigDecimal total = BigDecimal.ZERO;
    	for(TransactionFee fee: this.fees) {
    		total = total.add(fee.additionalFee);
    	}
    	return total;
    }
    public String additionalFeesTotalFormat() {
    	return formatNumber(this.additionalFeesTotal());
		//return additionalFeesTotalFormat(true);
	}
	public String additionalFeesTotalFormat(boolean withSymbol) {
    	return models.Currency.format(this.additionalFeesTotal(), this.beneficiaryCurrency.code, withSymbol);
    }
	
	
    public BigDecimal collectAmount() {
    	return this.senderAmount.add(this.feesTotal());
    }
	public String collectAmountFormat() {
		return collectAmountFormat(true);
	}
    public String collectAmountFormat(boolean withSymbol) {
        return models.Currency.format(this.collectAmount(), this.senderCurrency.code, withSymbol);
    }

    public static List<Transaction> getUserLatestTransaction(User user) {
        //TODO: Filter By User (Agent)
        Query<Transaction> sql = find
                .fetch("senderCountry")
                .fetch("beneficiaryCountry")
                .where()
                .in("agent_id", user.findChildrenIds())
                .orderBy("id desc")
                .setMaxRows(5);
        
        return sql.findList();
    }


    public static List<SqlRow> getUserPerformancesCashIn(User user) {
    	String inUserIdStr = ""; 
    	for(Object id : user.findChildrenIds()) {
    		if(inUserIdStr.length()>0) inUserIdStr += ", ";
    		inUserIdStr += String.valueOf((Long) id);
    	}
        String sqlStr = "SELECT cash_in_time::date AS date, count(*) AS total " +
                "FROM transactions " +
                "WHERE agent_id IN ("+inUserIdStr+") " +
                "AND date(cash_in_time) BETWEEN CURRENT_DATE - interval '6 months' AND CURRENT_DATE " +
                "GROUP BY cash_in_time " +
                "ORDER BY cash_in_time";

//        Logger.debug("Query SQL "+sqlStr);
        SqlQuery query = Ebean.createSqlQuery(sqlStr);
        List<SqlRow> rows = query.findList();

        return rows;
    }

    public static List<SqlRow> getUserPerformancesCashOut(User user) {
    	String inUserIdStr = ""; 
    	for(Object id : user.findChildrenIds()) {
    		if(inUserIdStr.length()>0) inUserIdStr += ", ";
    		inUserIdStr += String.valueOf((Long) id);
    	}
        String sqlStr = "SELECT cash_out_time::date AS date, count(*) AS total " +
                "FROM transactions " +
                "WHERE agent_id IN ("+inUserIdStr+") " +
                "AND date(cash_out_time) BETWEEN CURRENT_DATE - interval '6 months' AND CURRENT_DATE " +
                "GROUP BY cash_out_time " +
                "ORDER BY cash_out_time";

        SqlQuery query = Ebean.createSqlQuery(sqlStr);
        List<SqlRow> rows = query.findList();

        return rows;
    }

    public static List<SqlRow> getUserVolumesCashIn(User user, Currency currency) {
    	String inUserIdStr = ""; 
    	for(Object id : user.findChildrenIds()) {
    		if(inUserIdStr.length()>0) inUserIdStr += ", ";
    		inUserIdStr += String.valueOf((Long) id);
    	}
        String sqlStr = "SELECT cash_in_time::date AS date, sum(sender_amount) AS total " +
                "FROM transactions " +
                "WHERE agent_id IN ("+inUserIdStr+") " +
                "AND sender_currency_code = '"+currency.code+"' "+
                "AND date(cash_in_time) BETWEEN CURRENT_DATE - interval '6 months' AND CURRENT_DATE " +
                "GROUP BY cash_in_time " +
                "ORDER BY cash_in_time";

        SqlQuery query = Ebean.createSqlQuery(sqlStr);
        List<SqlRow> rows = query.findList();

        return rows;
    }

    public static List<SqlRow> getUserVolumesCashOut(User user, Currency currency) {
    	String inUserIdStr = ""; 
    	for(Object id : user.findChildrenIds()) {
    		if(inUserIdStr.length()>0) inUserIdStr += ", ";
    		inUserIdStr += String.valueOf((Long) id);
    	}
        String sqlStr = "SELECT cash_out_time::date AS date, sum(sender_amount) AS total " +
                "FROM transactions " +
                "WHERE agent_id IN ("+inUserIdStr+") " +
                "AND sender_currency_code = '"+currency.code+"' "+
                "AND date(cash_out_time) BETWEEN CURRENT_DATE - interval '6 months' AND CURRENT_DATE " +
                "GROUP BY cash_out_time " +
                "ORDER BY cash_out_time";

        SqlQuery query = Ebean.createSqlQuery(sqlStr);
        List<SqlRow> rows = query.findList();

        return rows;
    }

    public static Page<Transaction> reportPage(User user, int page, int pageSize, String sortBy, String order, controllers.Report.FilterForm form) {
        ExpressionList<Transaction> where = find.where();
        
//        if(form.agent != null) {
//        	List<Object> childrens = models.User.find.byId(form.agent.id).findChildrenIds();
//        	where = where.or(Expr.in("t0.agent_id", childrens), Expr.in("beneficiary_agent_id", childrens));
//        } else {
//        	 if((form.transactionId != null)) {
//        		 where = where.or(Expr.eq("id", form.transactionId), Expr.eq("t0.agent_id", form.transactionId));
//             } else {
//            	 if (form.transactionCode != null && !form.transactionCode.trim().equals("")) {
//            		 where = where.or(Expr.eq("sender.firstName", form.transactionCode), Expr.eq("sender.lastName", form.transactionCode));
//            	 } else {
//					if (user.hasRole("finance") || user.hasRole("admin")) { // For specific reason, finance can see other corporates
//					} else {
//						where = where.or(Expr.eq("corporate", user.corporate), Expr.eq("beneficiaryAgent.corporate", user.corporate));
//					}
//            	 }
//             }
//        }
        
        
        if((form.transactionId != null)) {
        	where = where.or(Expr.eq("id", form.transactionId), Expr.eq("t0.agent_id", form.transactionId));
        }
        if (user.hasRole("operator")) {
        	where = where.or(Expr.eq("agent.id", user.id), Expr.eq("beneficiaryAgent.id", user.id));
        } else if (user.hasRole("finance") || user.hasRole("admin")) {
        	if (form.corporate != null) {
        		where = where.or(Expr.eq("corporate", form.corporate), Expr.eq("beneficiaryAgent.corporate", form.corporate));
        	}
	        if(form.agent != null) {
	        	where = where.or(Expr.eq("t0.agent_id", form.agent.id), Expr.eq("beneficiary_agent_id", form.agent.id));
	        }
        }
        if((form.senderCountry != null)) {
        	where = where.eq("senderCountry", form.senderCountry);
        }
        if((form.beneficiaryCountry != null)) {
        	where = where.eq("beneficiaryCountry", form.beneficiaryCountry);
        }
        
        if ((form.trxStartDate != null) && (form.trxEndDate != null) ) {
        	where = where.between("cash_in_time", new java.sql.Date(form.trxStartDate.getTime()), new java.sql.Date(form.trxEndDate.getTime()+(86400*1000)));
        } else if ((form.trxStartDate != null)) {
        	where = where.gt("cash_in_time", new java.sql.Date(form.trxStartDate.getTime()));
        } else if ((form.trxEndDate != null)) {
        	where = where.gt("cash_in_time", new java.sql.Date(form.trxEndDate.getTime()+(86400*1000)));
        }
        if(form.transactionsStatus != null && form.transactionsStatus != 0) {
        	where = where.eq("status", form.transactionsStatus);
        }
        
        
        return where
                .orderBy(sortBy + " " + order)
                .fetch("channel")
                .fetch("senderCountry")
                .fetch("beneficiaryCountry")
                .fetch("forexReference")
                .fetch("sender")
                .fetch("beneficiary")
                .fetch("senderCurrency")
                .fetch("beneficiaryCurrency")
                .fetch("agent")
                .fetch("beneficiaryAccount")
                .findPagingList(pageSize)
                .setFetchAhead(false)
                .getPage(page);
    }
    
    public BillPaymentDataHelper billPayment() {
    	if (this.channel.code.equals("10")){
    		try {
    			if (this.paymentData != null) {
    				ObjectMapper objectMapper = new ObjectMapper();
    				objectMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
					BillPaymentDataHelper billPaymentDataHelper = (BillPaymentDataHelper) objectMapper.readValue(this.paymentData, BillPaymentDataHelper.class);
					return billPaymentDataHelper;
    			}
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    		
    	return null;
    }
    
    public static Page<Transaction> reportPageBatchTransaction(User user, int page, int pageSize, String sortBy, String order, controllers.Report.FilterForm form, int batchId) {
        ExpressionList<Transaction> where = find.where();
        where = where.eq("batch.id", batchId);
        if(form.agent != null) {
        	List<Object> childrens = models.User.find.byId(form.agent.id).findChildrenIds();
        	where = where.or(Expr.in("t0.agent_id", childrens), Expr.in("beneficiary_agent_id", childrens));
        } else {
        	 if((form.transactionId != null)) {
        		 where = where.or(Expr.eq("id", form.transactionId), Expr.eq("t0.agent_id", form.transactionId));
             } else {
            	 if (form.transactionCode != null && !form.transactionCode.trim().equals("")) {
            		 where = where.or(Expr.eq("sender.firstName", form.transactionCode), Expr.eq("sender.lastName", form.transactionCode));
            	 } else {
					if (user.hasRole("finance") || user.hasRole("admin")) { // For specific reason, finance can see other corporates
					} else {
						where = where.or(Expr.eq("corporate", user.corporate), Expr.eq("beneficiaryAgent.corporate", user.corporate));
					}
            	 }
             }
        }
        
        if((form.senderCountry != null)) {
        	where = where.eq("senderCountry", form.senderCountry);
        }
        if((form.beneficiaryCountry != null)) {
        	where = where.eq("beneficiaryCountry", form.beneficiaryCountry);
        }
        
        if ((form.trxStartDate != null) && (form.trxEndDate != null) ) {
        	where = where.between("cash_in_time", new java.sql.Date(form.trxStartDate.getTime()), new java.sql.Date(form.trxEndDate.getTime()+(86400*1000)));
        } else if ((form.trxStartDate != null)) {
        	where = where.gt("cash_in_time", new java.sql.Date(form.trxStartDate.getTime()));
        } else if ((form.trxEndDate != null)) {
        	where = where.gt("cash_in_time", new java.sql.Date(form.trxEndDate.getTime()+(86400*1000)));
        }
        if(form.transactionsStatus != null && form.transactionsStatus != 0) {
        	where = where.eq("status", form.transactionsStatus);
        }
        
        
        return where
                .orderBy(sortBy + " " + order)
                .fetch("channel")
                .fetch("senderCountry")
                .fetch("beneficiaryCountry")
                .fetch("forexReference")
                .fetch("sender")
                .fetch("beneficiary")
                .fetch("senderCurrency")
                .fetch("beneficiaryCurrency")
                .fetch("agent")
                .fetch("beneficiaryAccount")
                .findPagingList(pageSize)
                .setFetchAhead(false)
                .getPage(page);
    }
    
    public TransactionLog getTransactionLog(){
    	try {
			TransactionLog transactionLog = TransactionLog.getLastTransactionLogByTransaction(this);
			return transactionLog;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
    
}
