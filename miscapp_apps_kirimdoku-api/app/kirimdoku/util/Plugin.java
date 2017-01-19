package kirimdoku.util;

import java.math.BigDecimal;

import models.Corporate;
import models.User;

import org.codehaus.jackson.node.ObjectNode;

import play.Logger;
import controllers.helpers.Definition;
import controllers.helpers.ResponseHelper;

public class Plugin {
	
	public static ObjectNode operatorHasExceedSendLimit(User agent, BigDecimal senderAmount) {
		try {
			Logger.debug(">> PLUGIN OPERATOR CHECK SEND LIMIT ");
			if(agent.agentHasExceedSendLimit(senderAmount)) {
				Logger.debug("Agent credit limit has exceeded");
				return ResponseHelper.createResponse(Definition.STATUS_BLOCKED, "Agent credit limit has exceeded");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static ObjectNode corporateHasExceedSendLimit(Corporate corporate, BigDecimal senderAmount) {
		try {
			Logger.debug(">> PLUGIN CORPORATE CHECK SEND LIMIT");
			if(corporate.getStatistic().hasExceedCreditLimit(senderAmount)) {
				Logger.debug("Corporate credit limit has exceeded");
				return ResponseHelper.createResponse(Definition.STATUS_BLOCKED, "Corporate credit limit has exceeded");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}	
}
