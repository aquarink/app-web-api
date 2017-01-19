package controllers.helpers;

import java.io.File;
import java.util.List;
import models.Batch;
import models.Corporate;
import models.User;
import models.TransactionInquiry;
import play.Logger;

public class BatchUploadHelper {
	
    private File fileUpload;
    private String fileUploadContentType = "";
    private String fileUploadFileName = "";
    private String fileCSV = "";
	private List<BatchUploadDetailHelper> batchUploadDetailHelpers;
	private Corporate corporate;
	private int totalRow = 0;
	private int totalRowInquiry = 0;
	private String errorDescription;
	private User user;
	private Batch batch;
	private List<TransactionInquiry> inquiries;
	private String token;
	
	public BatchUploadHelper() {
		
	}
	
	public BatchUploadHelper(File fileUpload, String fileName, String contentType) {
		try {
			setFileUpload(fileUpload);
			setFileCSV(fileName);
			setFileUploadFileName(fileName);
			setFileUploadContentType(fileName.substring(fileName.length()-4));
			Logger.debug("BATCH UPLOAD HELPER :");
			Logger.debug("Csv ["+getFileCSV()+"]");
			Logger.debug("ContentType ["+getFileUploadContentType()+"]");
			Logger.debug("Filename ["+getFileUploadFileName()+"]");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public File getFileUpload() {
		return fileUpload;
	}
	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}
	public String getFileUploadContentType() {
		return fileUploadContentType;
	}
	public void setFileUploadContentType(String fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}
	public String getFileUploadFileName() {
		return fileUploadFileName;
	}
	public void setFileUploadFileName(String fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}
	public String getFileCSV() {
		return fileCSV;
	}
	public void setFileCSV(String fileCSV) {
		this.fileCSV = fileCSV;
	}
	public List<BatchUploadDetailHelper> getBatchUploadDetailHelpers() {
		return batchUploadDetailHelpers;
	}
	public void setBatchUploadDetailHelpers(
			List<BatchUploadDetailHelper> batchUploadDetailHelpers) {
		this.batchUploadDetailHelpers = batchUploadDetailHelpers;
	}

	public Corporate getCorporate() {
		return corporate;
	}

	public void setCorporate(Corporate corporate) {
		this.corporate = corporate;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public int getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}

	public int getTotalRowInquiry() {
		return totalRowInquiry;
	}

	public void setTotalRowInquiry(int totalRowInquiry) {
		this.totalRowInquiry = totalRowInquiry;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public List<TransactionInquiry> getInquiries() {
		return inquiries;
	}

	public void setInquiries(List<TransactionInquiry> inquiries) {
		this.inquiries = inquiries;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
