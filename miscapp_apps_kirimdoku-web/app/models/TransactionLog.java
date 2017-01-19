package models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.node.ObjectNode;

import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.libs.Json;

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
    
    public static void insert(Transaction transaction, User user, String tag, String message) {
        TransactionLog l = new TransactionLog();
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
    
    public static TransactionLog getLastTransactionLogByTransaction(Transaction transaction) {
    	try {
            return find.where()
                    .eq("transaction", transaction)
                    .orderBy().desc("id")
                    .setMaxRows(1)
                    .findUnique();
		} catch (Exception e) {
			return null;
		}

    }
    
}
