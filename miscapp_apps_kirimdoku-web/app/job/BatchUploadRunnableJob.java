package job;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import play.Logger;
import controllers.helpers.BatchUploadHelper;

public class BatchUploadRunnableJob implements Runnable {
	
	private BatchUploadHelper batchUploadHelper;
	ExecutorService simulator = null;
	
	public BatchUploadRunnableJob(BatchUploadHelper batchUploadHelper) {
		this.batchUploadHelper = batchUploadHelper;
	}
	
	public void init() {
		simulator = Executors.newSingleThreadExecutor();
	}
	
	public void stat() {
		simulator.execute(this);
	}
	
	public void run() {
		Logger.debug("BATCH UPLOAD THRED JOB");
		try {
			BatchUploadSaveToDB.saveToDB(this.batchUploadHelper);
			BatchUploadProcessInquiry.doProcessInquiry(this.batchUploadHelper);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
