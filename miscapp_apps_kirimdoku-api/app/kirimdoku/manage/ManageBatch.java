package kirimdoku.manage;

import kirimdoku.channel.batchprocessor.BaseChannelProcessor;
import models.TransactionInquiry;
import org.codehaus.jackson.node.ObjectNode;
import play.Logger;
import play.mvc.Result;
import play.mvc.Results;
import controllers.helpers.Definition;
import controllers.helpers.ResponseHelper;

public class ManageBatch {

	public static Result CashInInquiry(long transactionInquiryId) {
		Logger.debug("---------------------------------");
		Logger.debug(":: MANAGE BATCH CASHIN INQUIRY :: ");
		Logger.debug("=================================");
		Class<BaseChannelProcessor> classProcessor;
		Result result = null;
		try {
			TransactionInquiry inquiry = TransactionInquiry.getTransactionInquiryById(transactionInquiryId);
			if (inquiry != null) {
				String cfName = inquiry.channel != null ? inquiry.channel.cfnames : "";
				Logger.debug("Channel Code           : "+inquiry.channel.code);
				Logger.debug("Channel Name           : "+inquiry.channel.name);
				Logger.debug("Channel cfName         : "+inquiry.channel.cfnames);
				cfName = cfName.replace("kirimdoku.channel.processor.", "kirimdoku.channel.batchprocessor.");
				classProcessor = (Class<BaseChannelProcessor>) Class.forName(cfName, true, ManageBatch.class.getClassLoader());
				BaseChannelProcessor processor = classProcessor.newInstance();
				Logger.debug("Router loaded "+processor.getClass());
				result = processor.CashInInquiry(inquiry);
			} else {
				ObjectNode response = ResponseHelper.createResponse(Definition.STATUS_NOTFOUND, "Transaction inquiry id not found");
				result = Results.ok(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = Results.ok(ResponseHelper.createResponse(Definition.STATUS_UNKNOWN, e.getMessage()));
			Logger.debug("Exception              : " + e.getMessage());
		}
		return result;
	}
	
	public static Result CashInRemit(long transactionInquiryId) {
		Logger.debug("-------------------------------");
		Logger.debug(":: MANAGE BATCH CASHIN REMIT :: ");
		Logger.debug("===============================");
		Class<BaseChannelProcessor> classProcessor;
		Result result = null;
		try {
			TransactionInquiry inquiry = TransactionInquiry.find.byId(transactionInquiryId);
			if (inquiry != null && inquiry.channel.status) {
				String cfName = inquiry.channel != null ? inquiry.channel.cfnames : "";
				Logger.debug("Channel Code           : "+inquiry.channel.code);
				Logger.debug("Channel Name           : "+inquiry.channel.name);
				Logger.debug("Channel cfName         : "+inquiry.channel.cfnames);
				cfName = cfName.replace("kirimdoku.channel.processor.", "kirimdoku.channel.batchprocessor.");
				classProcessor = (Class<BaseChannelProcessor>) Class.forName(cfName, true, Manage.class.getClassLoader());
				BaseChannelProcessor processor = classProcessor.newInstance();
				Logger.debug("Router loaded "+processor.getClass());
				result = processor.CashInRemit(inquiry);
			} else {
				ObjectNode response = ResponseHelper.createResponse(Definition.STATUS_UNSUPPORTED, "Inquiry Id not found");
				result = Results.ok(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = Results.ok(ResponseHelper.createResponse(Definition.STATUS_UNKNOWN, e.getMessage()));
			Logger.debug("Exception              : " + e.getMessage());
		}
		return result;
	}
	
}
