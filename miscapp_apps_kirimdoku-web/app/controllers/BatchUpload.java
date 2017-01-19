package controllers;

import static play.data.Form.form;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import job.BatchUploadJob;
import models.Batch;
import models.TransactionInquiry;
import org.apache.commons.io.FileUtils;
import controllers.helpers.BatchUploadHelper;
import controllers.helpers.SessionHelper;
import doku.kirimdoku.util.CommonUtil;
import be.objectify.deadbolt.java.actions.And;
import be.objectify.deadbolt.java.actions.Restrictions;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.batchupload.layoutUpload;
import views.html.batchupload.resultUpload;

@Security.Authenticated(SecuredMain.class)
@Restrictions({ @And("mainagent"), @And("supervisor"), @And("operator") })
public class BatchUpload extends Controller {
	
	public static Result viewUpload() {
		Logger.debug("VIEW BATCH UPLOAD");
		Form<BatchUploadHelper> helper = form(BatchUploadHelper.class).bindFromRequest();
		try {
			Logger.debug("Data : "+helper.data().toString());
			String token = UUID.randomUUID().toString();
			SessionHelper.putToken(token);
			helper.get().setToken(token);
			if(helper.hasErrors()) {
				return forbidden(helper.errorsAsJson());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok(layoutUpload.render(helper));
	}
	
	public static Result actionUpload() {
		Logger.debug("ACTION BATCH UPLOAD");
	    play.mvc.Http.MultipartFormData body = request().body().asMultipartFormData();
	    play.mvc.Http.MultipartFormData.FilePart filePart = body.getFile("fileUpload");
	    BatchUploadHelper batchUploadHelper = new BatchUploadHelper();
		try {
			String tokenRequest = Form.form().bindFromRequest().get("token");
			Logger.debug("Request param tokenRequest : "+tokenRequest);
			Logger.debug("Request Batch Upload File : "+filePart.getFile());
			String token = SessionHelper.getToken();
			SessionHelper.removeToken();
			if (token.equals(tokenRequest)) {
				batchUploadHelper.setFileUploadFileName(filePart.getFilename());
				if(filePart != null && filePart.getFile() != null) {
					Logger.debug("file size ["+filePart.getFile().length()+"]");
					if (filePart.getFile().length() < 10000000 /* 10 MB */) {
						if (filePart.getFilename().toLowerCase().endsWith(".csv")) {
							Logger.debug("Filename csv : "+filePart.getFilename());
							if (filePart.getFilename().length() == 20) {
								String datePart = filePart.getFilename().substring(0, 8);
								if (CommonUtil.validDateFileName(datePart)){
									Logger.debug("Filename Date part : "+datePart);
									String corporateCodePart = filePart.getFilename().substring(9, 12);
									Logger.debug("Filename Corporate Code part : "+corporateCodePart);
									if (corporateCodePart.equals(SessionHelper.getUser().corporate.code)) {
										String sequencePart = filePart.getFilename().substring(13, 16);
										Logger.debug("Filename Sequence part : "+sequencePart);
										if (CommonUtil.validNumeric(sequencePart, null, 3, 3)){
											String pathUpload = CommonUtil.UPLOAD_PATH+corporateCodePart+"/";
											if (CommonUtil.pathCSV(pathUpload)) {
												File file = new File(pathUpload+filePart.getFilename());
												Logger.debug("File : "+pathUpload+filePart.getFilename());
												if (!file.exists()) {
													FileUtils.copyFile(filePart.getFile(), file);
													Logger.debug("Pindah file : "+file);
													batchUploadHelper = new BatchUploadHelper(file, filePart.getFilename(), filePart.getContentType());
													BatchUploadJob.getBatchUpload(batchUploadHelper);
													if (batchUploadHelper.getBatch() != null) {
														boolean status = file.renameTo(file);
														Logger.debug(status+" Pindah file : "+file);
														return redirect(routes.BatchUpload.viewResultBatchUpload(batchUploadHelper.getBatch().id));
													} else {
														String fileName = filePart.getFilename();
														file.delete();
														return viewResultBatchUploadFailed(fileName, batchUploadHelper.getTotalRow(), batchUploadHelper.getErrorDescription());
													}
												} else {
													Logger.debug("File csv "+filePart.getFilename()+" already exist");
													batchUploadHelper.setErrorDescription("File csv "+filePart.getFilename()+" already exist");
												}
											} else {
												Logger.debug("Failed move file csv");
												batchUploadHelper.setErrorDescription("Failed move file csv");
											}
										} else {
											Logger.debug("Invalid filename format (DDMMYYYY_CODE_SEQ)");
											batchUploadHelper.setErrorDescription("Invalid filename format (DDMMYYYY_CODE_SEQ)");
										}
									} else {
										Logger.debug("Invalid filename format (DDMMYYYY_CODE_SEQ)");
										batchUploadHelper.setErrorDescription("Invalid filename format (DDMMYYYY_CODE_SEQ)");
									}
								} else {
									Logger.debug("Invalid filename format (DDMMYYYY_CODE_SEQ)");
									batchUploadHelper.setErrorDescription("Invalid filename format (DDMMYYYY_CODE_SEQ)");
								}
							} else {
								Logger.debug("Invalid filename format (DDMMYYYY_CODE_SEQ)");
								batchUploadHelper.setErrorDescription("Invalid filename format (DDMMYYYY_CODE_SEQ)");
							}
						} else {
							Logger.debug("Invalid file extension. You must use CSV file extension");
							batchUploadHelper.setErrorDescription("Invalid file extension. You must use CSV file extension");
						}
					} else {
						Logger.debug("File csv too large (max 10MB)");
						batchUploadHelper.setErrorDescription("File csv too large (max 10MB)");
					}
				} else {
					Logger.debug("Failed upload file csv");
					batchUploadHelper.setErrorDescription("Failed upload file csv");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.debug("FAILED ACTION BATCH INQUIRY : "+e.getMessage());
		}
		return viewResultBatchUploadFailed(batchUploadHelper.getFileUploadFileName(), batchUploadHelper.getTotalRow(), batchUploadHelper.getErrorDescription());
	}
	
	public static Result viewResultBatchUpload(int batchId) {
		Logger.debug("VIEW RESULT BATCH UPLOAD");
	    BatchUploadHelper batchUploadHelper = new BatchUploadHelper();
		try {
			Logger.debug("Request param batchId : "+batchId);
			Batch batch = Batch.find.byId(batchId);
			SessionHelper.removeToken();
			if (batch != null) {
				batchUploadHelper.setUser(SessionHelper.getUser());
				batchUploadHelper.setCorporate(SessionHelper.getUser().corporate);
				batchUploadHelper.setBatch(Batch.find.byId(batchId));
				batchUploadHelper.setInquiries(TransactionInquiry.getInquiryDataToRemit(batchId));
				batchUploadHelper.setFileUploadFileName(batch.fileName);
				batchUploadHelper.setTotalRow(batch.totalRow);
				batchUploadHelper.setErrorDescription(batch.description);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.debug("FAILED ACTION BATCH REMIT : "+e.getMessage());
		}
		return ok(resultUpload.render(batchUploadHelper));
	}
	
	public static Result viewResultBatchUploadFailed(String fileName, int totalRow, String description) {
		Logger.debug("VIEW RESULT BATCH UPLOAD");
	    BatchUploadHelper batchUploadHelper = new BatchUploadHelper();
		try {
			SessionHelper.removeToken();
			batchUploadHelper.setUser(SessionHelper.getUser());
			batchUploadHelper.setCorporate(SessionHelper.getUser().corporate);
			batchUploadHelper.setFileUploadFileName(fileName);
			batchUploadHelper.setTotalRow(totalRow);
			batchUploadHelper.setErrorDescription(description);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.debug("FAILED ACTION BATCH REMIT : "+e.getMessage());
		}
		return ok(resultUpload.render(batchUploadHelper));
	}
	
	public static Result actionRemit(String tokenRequest, int batchId) {
		Logger.debug("ACTION BATCH REMIT");
	    BatchUploadHelper batchUploadHelper = new BatchUploadHelper();
		try {
			Logger.debug("Request param tokenRequest : "+tokenRequest);
			Logger.debug("Request param batchId : "+batchId);
			String token = SessionHelper.getToken();
			Logger.debug("Token session : "+token);
			SessionHelper.removeToken();
			if (SessionHelper.getUser().hasRole("supervisor")) {
				if (token.equals(tokenRequest)) {
					batchUploadHelper.setUser(SessionHelper.getUser());
					batchUploadHelper.setCorporate(SessionHelper.getUser().corporate);
					Batch batch = Batch.find.byId(batchId);
					if (batch.state.equals(Batch.BatchState.DONE_INQUIRY.getCode())) {
						List<TransactionInquiry> inquiries = TransactionInquiry.getInquiryDataToRemit(batch.id);
						List<TransactionInquiry> transactionInquiries = new ArrayList<TransactionInquiry>();
						for (TransactionInquiry transactionInquiry : inquiries) {
							String inqVal = Form.form().bindFromRequest().get("inq"+transactionInquiry.id);
							if (inqVal != null && inqVal.equals(transactionInquiry.id+"")) {
								transactionInquiries.add(transactionInquiry);
							}
						}
						batch.totalRowRemit = batch.totalRowRemit+transactionInquiries.size();
						batch.state = Batch.BatchState.PROCESS_REMIT.getCode();
						batch.update();
						batchUploadHelper.setBatch(batch);
						batchUploadHelper.setInquiries(transactionInquiries);
						BatchUploadJob.doRemitBatch(batchUploadHelper);
						return redirect(routes.Transaction.showBatchDetail(batchId, 0, "", "r"));
					}
				}
			} else {
				Logger.debug("FAILED USER ROLE");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.debug("FAILED ACTION BATCH REMIT : "+e.getMessage());
		}
		return redirect(routes.BatchUpload.viewUpload());
	}
	
}
