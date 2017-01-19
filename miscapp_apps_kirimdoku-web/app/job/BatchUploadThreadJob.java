package job;

import play.Logger;
import play.libs.Akka;
import controllers.helpers.BatchUploadHelper;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class BatchUploadThreadJob extends UntypedActor {
	
	private static ActorRef refInstance;
	
	public static ActorRef getRefInstance() {
		if (refInstance == null) {
			refInstance = Akka.system().actorOf(new Props(BatchUploadThreadJob.class));
		}
		return refInstance;
	}
	
	@Override
	public void onReceive(Object arg0) throws Exception {
		Logger.debug("BATCH UPLOAD THREAD JOB");
		try {
			BatchUploadHelper batchUploadHelper = (BatchUploadHelper) arg0;
			BatchUploadSaveToDB.saveToDB(batchUploadHelper);
			BatchUploadProcessInquiry.doProcessInquiry(batchUploadHelper);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
