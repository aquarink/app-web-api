package models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import play.Logger;
import models.Corporate;
import com.avaje.ebean.Expr;

public class QueryScheduller {
	
	public static List<Corporate> findCorporateActive() {
    	return Corporate.find.where()
    			.eq("status", Corporate.Status.ACTIVE)
    			.findList();
    }
	
	public static List<Transaction> getTransactionsSettlementReportSending(Corporate corporate, Date reportDate){
    	try{
	    	SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
	    	SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	Date createdTimeStart = sdfTime.parse(sdfDate.format(reportDate)+" 00:00:00");
	    	Date createdTimeEnd = sdfTime.parse(sdfDate.format(reportDate)+" 23:59:59");
	    	List<Transaction> transactions = Transaction.find
	    			.fetch("agent")
	    			.fetch("channel")
	    			.fetch("senderCountry")
	    			.fetch("senderCurrency")
	    			.fetch("forexReference")
	    			.fetch("forexReference.forex")
	    			.fetch("beneficiaryCurrency")
	    			.fetch("agent.corporate")
	    			.where()
	    			.eq("status", Transaction.STATUS_PAID)
	    			.eq("agent.corporate", corporate)
	    			.ge("cashOutTime", createdTimeStart)
	    			.le("cashOutTime", createdTimeEnd)
	    			.isNotNull("shareSenderId")
	    			.isNotNull("shareReceiverId")
	    			.orderBy("id asc")
	    			.findList();
	    	return transactions;
    	} catch (Exception ex) {
    		Logger.debug("Failed get transactions");
    		return null;
    	}
    }
	
	public static List<Transaction> getTransactionsSettlementReportReceive(Corporate corporate, Date reportDate){
    	try{
	    	SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
	    	SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	Date createdTimeStart = sdfTime.parse(sdfDate.format(reportDate)+" 00:00:00");
	    	Date createdTimeEnd = sdfTime.parse(sdfDate.format(reportDate)+" 23:59:59");
	    	List<Transaction> transactions = Transaction.find
	    			.fetch("beneficiaryAgent")
	    			.fetch("senderCurrency")
	    			.fetch("channel")
	    			.fetch("senderCountry")
	    			.fetch("beneficiaryCurrency")
	    			.fetch("forexReference")
	    			.fetch("forexReference.forex")
	    			.fetch("beneficiaryAgent.corporate")
	    			.where()
	    			.eq("status", Transaction.STATUS_PAID)
	    			.eq("beneficiaryAgent.corporate", corporate)
	    			.ge("cashOutTime", createdTimeStart)
	    			.le("cashOutTime", createdTimeEnd)
	    			.isNotNull("shareSenderId")
	    			.isNotNull("shareReceiverId")
	    			.orderBy("id asc")
	    			.findList();
	    	return transactions;
    	} catch (Exception ex) {
    		Logger.debug("Failed get transactions");
    		return null;
    	}
    }	
	
	
    public static List<Transaction> getTransactionsSettlementReportRefund(Corporate corporate, Date reportDate){
    	try{
	    	SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
	    	SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	Date createdTimeStart = sdfTime.parse(sdfDate.format(reportDate)+" 00:00:00");
	    	Date createdTimeEnd = sdfTime.parse(sdfDate.format(reportDate)+" 23:59:59");
	    	List<Transaction> transactions = Transaction.find
	    			.fetch("agent")
	    			.fetch("channel")
	    			.fetch("senderCountry")
	    			.fetch("senderCurrency")
	    			.fetch("forexReference")
	    			.fetch("forexReference.forex")
	    			.fetch("agent.corporate")
	    			.where()
	    			.or(Expr.eq("status", Transaction.STATUS_REFUNDED), Expr.eq("status", Transaction.STATUS_FULLREFUNDED))
	    			.eq("agent.corporate", corporate)
	    			.ge("modifiedTime", createdTimeStart)
	    			.le("modifiedTime", createdTimeEnd)
	    			.isNotNull("shareSenderId")
	    			.isNotNull("shareReceiverId")
	    			.orderBy("id asc")
	    			.findList();
	    	return transactions;
    	} catch (Exception ex) {
    		Logger.debug("Failed get transactions");
    		return null;
    	}
    }
	
}
