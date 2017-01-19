package kirimdoku.interfaces;

import static play.data.Form.form;

import org.codehaus.jackson.node.ObjectNode;

import kirimdoku.helpers.CashOutCollectHelper;
import kirimdoku.helpers.CashOutInquiryHelper;
import kirimdoku.helpers.CashOutValidateHelper;
import kirimdoku.helpers.SecurityAgentMobileAuthenticator;
import kirimdoku.manage.Manage;
import controllers.LoggerAction;
import controllers.helpers.Definition;
import controllers.helpers.ResponseHelper;
import play.Logger;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;

@With(LoggerAction.class)
@Security.Authenticated(SecurityAgentMobileAuthenticator.class)
public class CashOut extends Controller {
	
	public static Result inquiry() {
		Logger.info("---------------------");
		Logger.info(":: CASHOUT INQUIRY :: ");
		Logger.info("=====================");
		Result result = null;
		try {
			Form<CashOutInquiryHelper> helper = form(CashOutInquiryHelper.class).bindFromRequest();
			Logger.info("CashOut Inquiry Request " + helper.data());
			if (helper.hasErrors()) {
				result = unauthorized(ResponseHelper.createResponse(Definition.STATUS_INVALIDPARAMS, "Invalid parameters", helper.errorsAsJson()));
				Logger.info("Invalid parameters : " + helper.errorsAsJson());
			} else {
				result = Manage.CashOutInquiry(helper.get());
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = ok(ResponseHelper.createResponse(Definition.STATUS_UNKNOWN, e.getMessage()));
			Logger.info("Exception : " + e.getMessage());
		}
		Logger.info("--------------------");
		return result;
	}

	public static Result validate() {
		Logger.info("----------------------");
		Logger.info(":: CASHOUT VALIDATE :: ");
		Logger.info("======================");
		Result result = null;
		try {
			Form<CashOutValidateHelper> helper = form(CashOutValidateHelper.class).bindFromRequest();
			Logger.info("CashOut Validate Request " + helper.data());
			if (helper.hasErrors()) {
				result = unauthorized(ResponseHelper.createResponse(Definition.STATUS_INVALIDPARAMS, "Invalid parameters", helper.errorsAsJson()));
				Logger.info("Invalid parameters : " + helper.errorsAsJson());
			} else {
				models.Transaction transaction = helper.get().inquiry.transaction;
				ObjectNode response = Json.newObject();
				if (transaction == null) {
					response = ResponseHelper.createResponse(Definition.STATUS_ILLEGALPARAMS, "Transaction id not valid");
					result = ok(response);
				} else if (transaction.status == models.Transaction.STATUS_PENDING) {
					result = Manage.CashOutValidate(helper.get());
		        } else if (transaction.status == models.Transaction.STATUS_LOCKED) {
		        	response = ResponseHelper.createResponse(Definition.STATUS_BLOCKED, "Transaction has been blocked");
		        	result = ok(response);
				} else {
					response = ResponseHelper.createResponse(Definition.STATUS_UNAUTHORIZED, "Transaction denied by transaction status");
					result = ok(response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = ok(ResponseHelper.createResponse(Definition.STATUS_UNKNOWN, e.getMessage()));
			Logger.info("Exception : " + e.getMessage());
		}
		Logger.info("--------------------");
		return result;
	}

	public static Result collect() {
		Logger.info("---------------------");
		Logger.info(":: CASHOUT COLLECT :: ");
		Logger.info("=====================");
		Result result = null;
		try {
			Form<CashOutCollectHelper> helper = form(CashOutCollectHelper.class).bindFromRequest();
			Logger.info("CashOut Colect Request " + helper.data());
			if (helper.hasErrors()) {
				result = unauthorized(ResponseHelper.createResponse(Definition.STATUS_INVALIDPARAMS, "Invalid parameters", helper.errorsAsJson()));
				Logger.info("Invalid parameters : " + helper.errorsAsJson());
			} else {
				models.Transaction transaction = helper.get().inquiry.transaction;
				ObjectNode response = Json.newObject();
				if (transaction == null) {
					response = ResponseHelper.createResponse(Definition.STATUS_ILLEGALPARAMS, "Transaction id not valid");
					Logger.info("CashOut Colect Result " + helper.data());
					result = ok(response);
				} else if (!transaction.equals(helper.get().inquiry.transaction)) {
					response = ResponseHelper.createResponse(Definition.STATUS_ILLEGALSECURITY, "Transaction id is not match with inquiryId");
					Logger.info("CashOut Colect Result " + helper.data());
					result = ok(response);
				} else if (transaction.status == models.Transaction.STATUS_PENDING) {
					result = Manage.CashOutCollect(helper.get());
				} else {
					response = ResponseHelper.createResponse(Definition.STATUS_UNAUTHORIZED, "Transaction denied by transaction status");
					Logger.info("CashOut Colect Result " + helper.data());
					result = ok(response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = ok(ResponseHelper.createResponse(Definition.STATUS_UNKNOWN, e.getMessage()));
			Logger.info("Exception : " + e.getMessage());
		}
		Logger.info("--------------------");
		return result;
	}

}
