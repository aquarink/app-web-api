package job;

import play.Logger;
import play.libs.Akka;
import controllers.helpers.BatchUploadHelper;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class BatchRemitThreadJob extends UntypedActor {
	
	private static ActorRef refInstance;
	
	public static ActorRef getRefInstance() {
		if (refInstance == null) {
			refInstance = Akka.system().actorOf(new Props(BatchRemitThreadJob.class));
		}
		return refInstance;
	}
	
	@Override
	public void onReceive(Object arg0) throws Exception {
		Logger.debug("BATCH REMIT THREAD JOB");
		try {
			BatchUploadHelper batchUploadHelper = (BatchUploadHelper) arg0;
			BatchUploadProcessRemit.doProcessRemit(batchUploadHelper);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
