package kirimdoku.manage;

import org.codehaus.jackson.node.ObjectNode;
import models.Channel;
import controllers.helpers.Definition;
import controllers.helpers.ResponseHelper;
import kirimdoku.channel.processor.BaseChannelProcessor;
import kirimdoku.helpers.CashInInquiryHelper;
import kirimdoku.helpers.CashOutCollectHelper;
import kirimdoku.helpers.CashOutInquiryHelper;
import kirimdoku.helpers.CashOutValidateHelper;
import kirimdoku.helpers.CashInRemitHelper;
import play.Logger;
import play.mvc.Result;
import play.mvc.Results;

public class Manage {
	
	public static Result CashInInquiry(CashInInquiryHelper helper) {
		Logger.debug("---------------------------");
		Logger.debug(":: MANAGE CASHIN INQUIRY :: ");
		Logger.debug("===========================");
		Class<BaseChannelProcessor> classProcessor;
		Result result = null;
		try {
			Channel channel = Channel.findByCode(helper.channel.code);
			if (channel != null && channel.status) {
				String cfName = channel != null ? channel.cfnames : "";
				Logger.debug("Channel Code           : "+channel.code);
				Logger.debug("Channel Name           : "+channel.name);
				Logger.debug("Channel cfName         : "+channel.cfnames);
				
				classProcessor = (Class<BaseChannelProcessor>) Class.forName(cfName, true, Manage.class.getClassLoader());
				BaseChannelProcessor processor = classProcessor.newInstance();
				Logger.debug("Router loaded "+processor.getClass());
				result = processor.CashInInquiry(helper);
			} else {
				ObjectNode response = ResponseHelper.createResponse(Definition.STATUS_UNSUPPORTED, "Channel not active");
				result = Results.ok(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = Results.ok(ResponseHelper.createResponse(Definition.STATUS_UNKNOWN, e.getMessage()));
			Logger.debug("Exception              : " + e.getMessage());
		}
		return result;
	}
	
	public static Result CashInRemit(CashInRemitHelper helper) {
		Logger.debug("-------------------------");
		Logger.debug(":: MANAGE CASHIN REMIT :: ");
		Logger.debug("=========================");
		Class<BaseChannelProcessor> classProcessor;
		Result result = null;
		try {
			Channel channel = Channel.findByCode(helper.channel.code);
			if (channel != null && channel.status) {
				String cfName = channel != null ? channel.cfnames : "";
				Logger.debug("Channel Code           : "+channel.code);
				Logger.debug("Channel Name           : "+channel.name);
				Logger.debug("Channel cfName         : "+channel.cfnames);
				
				classProcessor = (Class<BaseChannelProcessor>) Class.forName(cfName, true, Manage.class.getClassLoader());
				BaseChannelProcessor processor = classProcessor.newInstance();
				Logger.debug("Router loaded "+processor.getClass());
				result = processor.CashInRemit(helper);
			} else {
				ObjectNode response = ResponseHelper.createResponse(Definition.STATUS_UNSUPPORTED, "Channel not active");
				result = Results.ok(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = Results.ok(ResponseHelper.createResponse(Definition.STATUS_UNKNOWN, e.getMessage()));
			Logger.debug("Exception              : " + e.getMessage());
		}
		return result;
	}
	
	public static Result CashOutInquiry(CashOutInquiryHelper helper) {
		Logger.debug("----------------------------");
		Logger.debug(":: MANAGE CASHOUT INQUIRY :: ");
		Logger.debug("============================");
		Class<BaseChannelProcessor> classProcessor;
		Result result = null;
		try {
			Channel channel = Channel.findByCode(helper.transaction.channel.code);
			if (channel != null && channel.status) {
				String cfName = channel != null ? channel.cfnames : "";
				Logger.debug("Channel Code           : "+channel.code);
				Logger.debug("Channel Name           : "+channel.name);
				Logger.debug("Channel cfName         : "+channel.cfnames);
				
				classProcessor = (Class<BaseChannelProcessor>) Class.forName(cfName, true, Manage.class.getClassLoader());
				BaseChannelProcessor processor = classProcessor.newInstance();
				Logger.debug("Router loaded "+processor.getClass());
				result = processor.CashOutInquiry(helper);
			} else {
				ObjectNode response = ResponseHelper.createResponse(Definition.STATUS_UNSUPPORTED, "Channel not active");
				result = Results.ok(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = Results.ok(ResponseHelper.createResponse(Definition.STATUS_UNKNOWN, e.getMessage()));
			Logger.debug("Exception              : " + e.getMessage());
		}
		return result;
	}
	
	public static Result CashOutValidate(CashOutValidateHelper helper) {
		Logger.debug("-----------------------------");
		Logger.debug(":: MANAGE CASHOUT VALIDATE :: ");
		Logger.debug("=============================");
		Class<BaseChannelProcessor> classProcessor;
		Result result = null;
		try {
			Channel channel = Channel.findByCode(helper.inquiry.transaction.channel.code);
			if (channel != null && channel.status) {
				String cfName = channel.cfnames != null ? channel.cfnames : "";
				Logger.debug("Channel Code           : " + channel.code);
				Logger.debug("Channel Name           : " + channel.name);
				Logger.debug("Channel cfName         : " + channel.cfnames);
				
				classProcessor = (Class<BaseChannelProcessor>) Class.forName(cfName, true, Manage.class.getClassLoader());
				BaseChannelProcessor processor = classProcessor.newInstance();
				Logger.debug("Router loaded "+processor.getClass());
				result = processor.CashOutValidate(helper);
			} else {
				result = Results.ok(ResponseHelper.createResponse(Definition.STATUS_UNSUPPORTED, "Channel not active"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = Results.ok(ResponseHelper.createResponse(Definition.STATUS_UNKNOWN, e.getMessage()));
			Logger.debug("Exception              : " + e.getMessage());
		}
		return result;
	}
	
	public static Result CashOutCollect(CashOutCollectHelper helper) {
		Logger.debug("----------------------------");
		Logger.debug(":: MANAGE CASHOUT COLLECT :: ");
		Logger.debug("============================");
		Class<BaseChannelProcessor> classProcessor;
		Result result = null;
		try {
			Channel channel = Channel.findByCode(helper.inquiry.transaction.channel.code);
			if (channel != null && channel.status) {
				String cfName = channel != null ? channel.cfnames : "";
				Logger.debug("Channel Code           : "+channel.code);
				Logger.debug("Channel Name           : "+channel.name);
				Logger.debug("Channel cfName         : "+channel.cfnames);
				
				classProcessor = (Class<BaseChannelProcessor>) Class.forName(cfName, true, Manage.class.getClassLoader());
				BaseChannelProcessor processor = classProcessor.newInstance();
				Logger.debug("Router loaded "+processor.getClass());
				result = processor.CashOutCollect(helper);
			} else {
				result = Results.ok(ResponseHelper.createResponse(Definition.STATUS_UNSUPPORTED, "Channel not active"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = Results.ok(ResponseHelper.createResponse(Definition.STATUS_UNKNOWN, e.getMessage()));
			Logger.debug("Exception              : " + e.getMessage());
		}
		return result;
	}
}
