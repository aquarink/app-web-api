package kirimdoku.helpers;

import java.util.List;

public class BillPaymentInquiryResponse {
	
	public List<BillDetail> billdetails;
	public String biller_NAME;
	public String inquiry_ID;
	public String payment_TYPE;
	public String subscriber_ID;
	public String subscriber_NAME;
	public List<BillDetailDescription> additional_DATA;
	public String responsecode;
	public String responsemsg;
	
}
