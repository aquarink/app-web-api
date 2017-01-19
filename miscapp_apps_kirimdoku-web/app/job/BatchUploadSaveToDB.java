package job;

import java.util.List;
import play.Logger;
import models.ForexReference;
import models.TransactionInquiry;
import controllers.helpers.BatchUploadDetailHelper;
import controllers.helpers.BatchUploadHelper;


public class BatchUploadSaveToDB {
	
	public static BatchUploadHelper saveToDB(BatchUploadHelper batchUploadHelper) {
		Logger.debug("BATCH UPLOAD SAVE TO DB");
		BatchUploadHelper result = batchUploadHelper;
		try {
			List<BatchUploadDetailHelper> batchUploadDetailHelper = batchUploadHelper.getBatchUploadDetailHelpers();
			if (batchUploadDetailHelper != null && batchUploadDetailHelper.size() > 0) {
				if (batchUploadHelper.getBatch() != null) {
					for (BatchUploadDetailHelper detailHelper : batchUploadDetailHelper) {
						int statusParse = detailHelper.isStatus() ? 1 : 0;
						switch (statusParse) {
						case 1:
							ForexReference forexReference = ForexReference.find.byId(detailHelper.getForexReferenceId());
							int statusSave = TransactionInquiry.saveInquiryBatch(detailHelper, batchUploadHelper.getUser(), forexReference, batchUploadHelper.getBatch());
							switch (statusSave){
								case 0:
									Logger.debug("Failed save to db, amount "+detailHelper.getSendingAmount()+", personal id "+detailHelper.getSenderPersonalId());
									break;
							}
							break;
						default:
							Logger.debug(">>");
							break;
						}
					}
					
					result.setInquiries(TransactionInquiry.getInquiryDataToSend(batchUploadHelper.getBatch().id, batchUploadHelper.getUser().id));
				} else {
					System.out.println("Failed create batch >> "+batchUploadHelper.getFileUploadFileName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
