package job;

import java.util.Date;

import org.codehaus.jackson.map.ObjectMapper;

import play.Logger;
import controllers.helpers.BatchRequestHelper;
import controllers.helpers.BatchUploadHelper;
import doku.kirimdoku.util.CommonUtil;
import models.Batch;
import models.TransactionInquiry;
import models.TransactionInquiry.TransactionInquiryState;

public class BatchUploadProcessInquiry {
	
	protected static ObjectMapper objectMapper = new ObjectMapper();
	
	public static boolean doProcessInquiry(BatchUploadHelper batchUploadHelper) {
		Logger.debug(CommonUtil.sdfDateTimes.format(new Date())+" :: START PROCESS INQUIRY BATCH ::.");
		boolean status = false;
		try {
			batchUploadHelper.getBatch().totalRowInquiry = batchUploadHelper.getInquiries().size();
			batchUploadHelper.getBatch().save();
			for (TransactionInquiry transactionInquiry : batchUploadHelper.getInquiries()) {
				Logger.debug(CommonUtil.sdfDateTimes.format(new Date())+" :: START INQUIRY ROW ::.");
				
				int casePrepareProcesssInquiry = preProcessInquiry(transactionInquiry);
				switch (casePrepareProcesssInquiry) {
				case 0:
					Logger.debug("FAILED UPDATE INQUIRY PROCESS");
					break;
				case 1:
					try {
						Logger.debug("Process Inquiry >> "+transactionInquiry.id);
						String response = CommonUtil.postHttp(prepareProcessInquiryData(transactionInquiry), batchUploadHelper.getUser(), CommonUtil.URL_API_INQUIRY_BATCH, CommonUtil.REMIT_TIMEOUT_API);
						Logger.debug("Response Api >> "+response);
						Logger.debug(CommonUtil.sdfDateTimes.format(new Date())+" :: END INQUIRY ROW ::.");
						Thread.sleep(100);
					} catch (NullPointerException e) {
						status = true;
						Logger.debug(CommonUtil.sdfDateTimes.format(new Date())+" :: END INQUIRY ROW [NULL POINTER EXCEPTION] ::.");
						Thread.sleep(60000);
					} catch (Exception e) {
						Logger.debug(CommonUtil.sdfDateTimes.format(new Date())+" :: END INQUIRY ROW [EXCEPTION] ::.");
						Logger.debug("Unexpected error >> "+e.getMessage());
						status = true;
					}
					break;
				}

			}
			batchUploadHelper.getBatch().inquiryUpdateDate = new Date();
			batchUploadHelper.getBatch().state = Batch.BatchState.DONE_INQUIRY.getCode();
			batchUploadHelper.getBatch().update();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Logger.debug(CommonUtil.sdfDateTimes.format(new Date())+" :: END PROCESS INQUIRY BATCH ::.");
		return status;
	}
	
	protected static int preProcessInquiry(TransactionInquiry inquiry) {
		Logger.debug("PRE PROCESS INQUIRY");
		int status = 0;
		try {
			inquiry.state = TransactionInquiryState.PROCESSING_INQUIRY.getCode();
			inquiry.updateTime = new Date();
			inquiry.update();
			status = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	protected static String prepareProcessInquiryData(TransactionInquiry inquiry) {
		Logger.debug("PREPARE PROCESS INQUIRY DATA");
		String dataInquiry = null;
		try {
			BatchRequestHelper batchRequestHelper = new BatchRequestHelper();
			batchRequestHelper.transactionInquiryId = inquiry.id;
			dataInquiry = objectMapper.writeValueAsString(batchRequestHelper);
			Logger.debug("Batch Request >> "+dataInquiry);
			return dataInquiry;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataInquiry;
	}
	
}
