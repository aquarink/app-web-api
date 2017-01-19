package controllers.actors;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.QueryIterator;
import com.avaje.ebean.RawSql;
import com.avaje.ebean.RawSqlBuilder;

import models.Corporate;
import models.Label;
import models.Settlement;
import models.Transaction;
import scala.Option;
import scala.PartialFunction;
import scala.collection.immutable.Stack;
import scala.runtime.BoxedUnit;
import akka.actor.Actor;
import akka.actor.ActorContext;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.actor.UntypedActor;
import play.Logger;
import play.db.ebean.Transactional;
import play.libs.Akka;



public class SuspiciousActor extends UntypedActor {

	private static ActorRef refInstance;
	
	private long lastTransactionId = 0;
	
	public static ActorRef getRefInstance() {
		if (refInstance == null) {
			refInstance = Akka.system().actorOf(new Props(SuspiciousActor.class));
		}
		return refInstance;
	}
	
	
	@Override
	//@Transactional
	public void onReceive(Object message) throws Exception {
//		Thread.sleep(5000);
		Logger.info("SettlementActor onReceive "+message);
		
		if("check".equals(message)) {
			this.runCheckTransactions();
		} else if(message instanceof models.Transaction) {
			this.runCheckTransaction((Transaction) message);
		}
	}

	//@Transactional
	private void runCheckTransactions() {
		Logger.info("Running suspicious actor jobs...");
		ExpressionList<Transaction> where = models.Transaction.find.where();
		where.gt("id", lastTransactionId);
		where.setMaxRows(5);
		where.setOrderBy("id");
		
		for(QueryIterator<Transaction> trxIterator = where.findIterate(); trxIterator.hasNext();) {
			models.Transaction transaction = trxIterator.next();
			this.lastTransactionId = transaction.id;
			this.runCheckTransaction(transaction);
		}
	}
	
	//@Transactional
	private void runCheckTransaction(Transaction transaction) {
//		transaction.refresh();
		Logger.debug("Checking transaction "+transaction.idToken()+" with labels "+transaction.labels.size());
//		for(Iterator<Label> it = transaction.labels.iterator(); it.hasNext(); ) {
//			Logger.debug(it.next().name);
//		}
		// Rules:
		// 31. sender send trx more then once perday
		// 32. receiver receive trx more then once perday
		// 36. transaction that accumulately over the usage (corporate.customerSendLimit per 3 day)
		// 37. receiver receive trx more than one sender permonth
		
		List<Label> labels = Label.getLabelSuspicious();
		for (Label label : labels) {
			boolean hasLabel = transaction.labels.contains(label);
			switch(label.id) {
			case 31:
				int countSend = Transaction.find.where().eq("sender", transaction.sender)
				.between("cash_in_time", new Date(transaction.cashInTime.getTimeInMillis()-(8640000*1000)), transaction.cashInTime.getTime())
//				.between("created_time", new Date(transaction.createdTime.getTime()-(8640000*1000)), transaction.createdTime)
				.findRowCount();
				if(countSend>1) hasLabel = true;
				else hasLabel = false;
				break;
			
			case 32:
				int countReceive = Transaction.find.where().eq("beneficiary", transaction.beneficiary)
				.between("cash_in_time", new Date(transaction.cashInTime.getTimeInMillis()-(8640000*1000)), transaction.cashInTime.getTime())
//				.between("created_time", new Date(transaction.createdTime.getTime()-(8640000*1000)), transaction.createdTime)
				.findRowCount();
				if(countReceive>1) hasLabel = true;
				else hasLabel = false;
				break;
				
			case 33:
				hasLabel = transaction.sender.getStatistic().hasExceedSendLimit(BigDecimal.ZERO, transaction.corporate);
				break;
				
			case 34:
				if(transaction.beneficiaryAgent != null) {
					hasLabel = transaction.beneficiary.getStatistic().hasExceedBeneficiaryLimit(BigDecimal.ZERO, transaction.beneficiaryAgent.corporate);
				}
				break;
				
			case 36:
				int countSender = Transaction.find.where().eq("beneficiary", transaction.beneficiary).ne("sender", transaction.sender)
//				.between("cash_in_time", new Date(transaction.cashInTime.getTimeInMillis()-(8640000*1000)), transaction.cashInTime.getTime())
				.between("created_time", new Date(transaction.createdTime.getTime()-(8640000*1000)), transaction.createdTime)
				.findRowCount();
				if(countSender>=1) hasLabel = true;
				else hasLabel = false;
				break;
				
			default:
				String sql = "SELECT SUM(sender_amount) AS totalUnsettledSenderAmount"
						+ " FROM corporates INNER JOIN transactions ON(transactions.corporate_code=corporates.code)" + " WHERE corporate_code=1"
						+ " AND transactions.settlement_id IS NULL";
				
//				RawSql rawSql = RawSqlBuilder.unparsed(sql).create();
				break;
			}
			
			if(hasLabel) {
				if(!transaction.labels.contains(label)) transaction.labels.add(label);
			} else {
				transaction.labels.remove(label);
			}
			transaction.update();
		}
		
//		Date ltDate = new Date(new Date().getTime()-(86400000*corporate.settlement.settlementPeriodDay));
//		Transaction.find.where().
		//transaction.sender.;
		
	}

	public enum RULES {
		UNKNOWN,
		SENDER_DAY_SEVERAL,
		BENEFICIARY_DAY_SEVERAL,
		SENDER_MONTH_SEVERAL,
		BENEFICIARY_MONTH_SEVERAL,
	}

}
