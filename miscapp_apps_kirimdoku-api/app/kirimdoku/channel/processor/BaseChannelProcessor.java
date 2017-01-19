package kirimdoku.channel.processor;

import kirimdoku.helpers.CashInInquiryHelper;
import kirimdoku.helpers.CashOutCollectHelper;
import kirimdoku.helpers.CashOutInquiryHelper;
import kirimdoku.helpers.CashOutValidateHelper;
import kirimdoku.helpers.CashInRemitHelper;
import play.mvc.Result;

public abstract class BaseChannelProcessor {
	
	public abstract Result Ping();
	
	public abstract Result CashInInquiry(CashInInquiryHelper helper);
	
	public abstract Result CashInRemit(CashInRemitHelper helper);
	
	public abstract Result CashOutInquiry(CashOutInquiryHelper helper);
	
	public abstract Result CashOutValidate(CashOutValidateHelper helper);
	
	public abstract Result CashOutCollect(CashOutCollectHelper helper);
	
}
