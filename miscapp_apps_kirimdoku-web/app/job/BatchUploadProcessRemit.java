package job;

import java.util.Date;
import org.codehaus.jackson.map.ObjectMapper;
import play.Logger;
import controllers.helpers.BatchRequestHelper;
import controllers.helpers.BatchUploadHelper;
import doku.kirimdoku.util.CommonUtil;
import models.Batch;
import models.TransactionInquiry;

public class BatchUploadProcessRemit {
	
	protected static ObjectMapper objectMapper = new ObjectMapper();
	
	public static boolean doProcessRemit(BatchUploadHelper batchUploadHelper) {
		Logger.debug(CommonUtil.sdfDateTimes.format(new Date())+" :: START PROCESS REMIT BATCH ::.");
		boolean status = false;
		try {
			for (TransactionInquiry transactionInquiry : batchUploadHelper.getInquiries()) {
				Logger.debug(CommonUtil.sdfDateTimes.format(new Date())+" :: START REMIT ROW ::.");

				try {
					Logger.debug("Process Remit >> "+transactionInquiry.id);
					String response = CommonUtil.postHttp(prepareProcessRemitData(transactionInquiry), batchUploadHelper.getUser(), CommonUtil.URL_API_REMIT_BATCH, CommonUtil.REMIT_TIMEOUT_API);
					Logger.debug("Response Api >> "+response);
					Logger.debug(CommonUtil.sdfDateTimes.format(new Date())+" :: END REMIT ROW ::.");
					Thread.sleep(100);
				} catch (NullPointerException e) {
					status = true;
					Logger.debug(CommonUtil.sdfDateTimes.format(new Date())+" :: END REMIT ROW [NULL POINTER EXCEPTION] ::.");
					Thread.sleep(60000);
				} catch (Exception e) {
					Logger.debug(CommonUtil.sdfDateTimes.format(new Date())+" :: END REMIT ROW [EXCEPTION] ::.");
					Logger.debug("Unexpected error >> "+e.getMessage());
					status = true;
				}

			}
			batchUploadHelper.getBatch().remitUpdateDate = new Date();
			if (batchUploadHelper.getBatch().totalRowRemit == batchUploadHelper.getBatch().totalRowInquiry) {
				batchUploadHelper.getBatch().state = Batch.BatchState.CLOSE.getCode();
			} else {
				batchUploadHelper.getBatch().state = Batch.BatchState.DONE_INQUIRY.getCode();
			}
			batchUploadHelper.getBatch().update();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Logger.debug(CommonUtil.sdfDateTimes.format(new Date())+" :: END PROCESS REMIT BATCH ::.");
		return status;
	}
	
	protected static String prepareProcessRemitData(TransactionInquiry inquiry) {
		Logger.debug("PREPARE PROCESS REMIT DATA");
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
