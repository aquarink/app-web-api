package job;

import models.Batch;
import play.Logger;
//import akka.actor.ActorRef;
import controllers.helpers.BatchUploadHelper;
import controllers.helpers.SessionHelper;

public class BatchUploadJob {
	
	//private static ActorRef batchInquiryJob = BatchUploadThreadJob.getRefInstance();
	//private static ActorRef batchRemitJob = BatchRemitThreadJob.getRefInstance();
	
	public static boolean getBatchUpload(BatchUploadHelper batchUploadHelper) {
		Logger.debug("GET BATCH UPLOAD [JOB]");
		boolean status = false;
		try {
			batchUploadHelper.setUser(SessionHelper.getUser());
			ParserBatchUpload.prepareParserData(batchUploadHelper);
			status = ParserBatchUpload.doParsingUploadBatch(batchUploadHelper);
			//BatchUploadSaveToDB.saveToDB(batchUploadHelper);
			if (status) {
				batchUploadHelper.setBatch(Batch.createBatch(batchUploadHelper));
				//batchInquiryJob.tell(batchUploadHelper);
				
				BatchUploadRunnableJob runnableJob = new BatchUploadRunnableJob(batchUploadHelper);
				runnableJob.init();
				runnableJob.stat();
				status = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public static boolean doRemitBatch(BatchUploadHelper batchUploadHelper) {
		Logger.debug("DO REMIT BATCH [JOB]");
		boolean status = false;
		try {
			batchUploadHelper.setInquiries(ParserBatchUpload.doPrepareBatchRemit(batchUploadHelper.getInquiries()));
			if (batchUploadHelper.getInquiries().size() > 0) {
				//batchRemitJob.tell(batchUploadHelper);
				BatchRemitRunnableJob runnableJob = new BatchRemitRunnableJob(batchUploadHelper);
				runnableJob.init();
				runnableJob.stat();
				status = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
}
