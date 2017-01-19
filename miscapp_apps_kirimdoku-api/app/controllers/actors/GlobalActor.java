package controllers.actors;
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
import play.libs.Akka;



public class GlobalActor extends UntypedActor {

	private static ActorRef refInstance;
	
	public static ActorRef getRefInstance() {
		if (refInstance == null) {
			refInstance = Akka.system().actorOf(new Props(GlobalActor.class));
		}
		return refInstance;
	}
	
	
	@Override
	public void onReceive(Object message) throws Exception {
//		Thread.sleep(5000);
		Logger.info("GlobalActor onReceive "+message);
		if("reminder".equals(message)) {
			this.runReminderJob();
		}
	}


	private void runReminderJob() {
//		models.Corporate c = new models.Corporate();
//		c.
	}

}