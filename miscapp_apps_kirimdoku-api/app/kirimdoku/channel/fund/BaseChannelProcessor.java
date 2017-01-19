package kirimdoku.channel.fund;

import controllers.helpers.TransferResponse;
import models.TransactionInquiry;

public abstract class BaseChannelProcessor {

	public abstract TransferResponse FundInquiry(TransactionInquiry inquiry);
	
	public abstract void FundRemit(TransactionInquiry inquiry);

}
