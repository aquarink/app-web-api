package kirimdoku.channel.batchprocessor;

import models.TransactionInquiry;
import play.mvc.Result;

public abstract class BaseChannelProcessor {
	
	public abstract Result Ping();
	
	public abstract Result CashInInquiry(TransactionInquiry inquiry);
	
	public abstract Result CashInRemit(TransactionInquiry inquiry);

	
}
