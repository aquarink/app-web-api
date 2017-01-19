package controllers.actors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.QueryIterator;

import models.Corporate;
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
import play.db.DB;
import play.db.ebean.Transactional;
import play.libs.Akka;



public class SettlementActor extends UntypedActor {

	private static ActorRef refInstance;
	
	public static ActorRef getRefInstance() {
		if (refInstance == null) {
			refInstance = Akka.system().actorOf(new Props(SettlementActor.class));
		}
		return refInstance;
	}
	
	
	@Override
	@Transactional
	public void onReceive(Object message) throws Exception {
//		Thread.sleep(5000);
		Logger.info("SettlementActor onReceive "+message);
		
		if("settlement".equals(message)) {
			this.runSettlement();
		}
	}

	@Transactional
	private void runSettlement() {
		Logger.info("Running settlement...");
		List<Corporate> corporates = Corporate.find.fetch("settlement").findList();
		for(Corporate corporate : corporates) {
			if(corporate.status != Corporate.Status.ACTIVE) continue;
			if(corporate.settlement == null) continue;
			Logger.debug("Settling for corporate "+corporate.code+" - "+corporate.name+" - "+corporate.settlement);
			
			Settlement settlement = new Settlement();
			settlement.created = new Date();
			
			ExpressionList<Transaction> where = Transaction.find.where().eq("corporate", corporate);
			Date ltDate = new Date(new Date().getTime()-(86400000*corporate.settlement.settlementPeriodDay));
			where.isNull("settlement");
			where.in("status", Arrays.asList(Transaction.STATUS_PAID));
			where.lt("cashInTime", ltDate);
			
			for(QueryIterator<Transaction> it = where.findIterate(); it.hasNext(); ) {
				Transaction transaction = it.next();
				Logger.debug("Settling transaction "+transaction.id+" with cashInTime "+transaction.cashInTime.getTime());
				transaction.settlement = settlement;
				transaction.update();
			}
			
			settlement.save();
		}
	}
}
