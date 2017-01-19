package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlRow;
import com.avaje.ebean.TxType;
import com.avaje.ebean.annotation.Transactional;

import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
@Table(name = "transaction_logs")
public class TransactionLog extends Model {

    @Id
    public Long id;

    @Constraints.Required
    @ManyToOne(optional = false)
    @JsonIgnore
    public Transaction transaction;

    @ManyToOne(optional = true, cascade = {}, fetch = FetchType.EAGER)
    public User user;
     
    @Column(nullable = false)
    public Date createdTime;
    
    @Constraints.Required
    public String tag;
    
    @Constraints.Required
    @Lob
    public String message;


    public TransactionLog() {
    }
    
    @Transactional(type = TxType.NOT_SUPPORTED)
    public static void insert(Transaction transaction, User user, String tag, String message) {
        TransactionLog l = new TransactionLog();
        l.id = getSeq();
        l.transaction = transaction;
        l.createdTime = new Date();
        l.user = user;
        l.tag = tag;
        l.message = message;
        l.save();
    }
    
    public static final Finder<Long, TransactionLog> find = new Finder<Long, TransactionLog>(Long.class, TransactionLog.class);

    public static List<TransactionLog> findByTransaction(Transaction transaction) {
        return find.where()
            .eq("transaction", transaction)
            .findList();
    }
    
    public static long getSeq() {
    	try {
	        SqlRow row = Ebean.createSqlQuery("SELECT nextval('transaction_logs_seq')").findUnique();
	        long nextId = row.getLong("nextval");
	        return nextId;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return 0;
    }
    
    public static String getMessage(Long trxId) {
    	String msg = "";
    	try {
        	SqlRow sqlRow = Ebean.createSqlQuery("SELECT message FROM transaction_logs WHERE transaction_id = :trxId AND tag = 'TPG_RESPONSE' ORDER BY id DESC LIMIT 1")
        	    	.setParameter("trxId", trxId).findUnique();
        	msg = sqlRow.getString("message");
		} catch (Exception e) {
			
		}
    	return msg;
    }
    
    
    
}
