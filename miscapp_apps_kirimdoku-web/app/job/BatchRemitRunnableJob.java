package job;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import play.Logger;
import controllers.helpers.BatchUploadHelper;

public class BatchRemitRunnableJob implements Runnable {
	
	ExecutorService executor = null;
	private BatchUploadHelper batchUploadHelper;
	
	public BatchRemitRunnableJob(BatchUploadHelper batchUploadHelper) {
		this.batchUploadHelper = batchUploadHelper;
	}
	
	public void init() {
		executor = Executors.newSingleThreadExecutor();
	}
	
	public void stat() {
		executor.execute(this);
	}
	
	public void run() {
		Logger.debug("BATCH REMIT THREAD JOB");
		try {
			BatchUploadProcessRemit.doProcessRemit(this.batchUploadHelper);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
