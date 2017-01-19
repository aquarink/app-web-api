package kirimdoku.interfaces;

import static play.data.Form.form;
import kirimdoku.helpers.BatchRequestHelper;
import kirimdoku.manage.ManageBatch;
import controllers.LoggerAction;
import controllers.helpers.Definition;
import controllers.helpers.ResponseHelper;
import controllers.helpers.SecurityAgentAuthenticator;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;

@Security.Authenticated(SecurityAgentAuthenticator.class)
@With(LoggerAction.class)
public class CashInBatch extends Controller {

	public static Result inquiry() {
		Logger.info("--------------------------");
		Logger.info(":: CASHIN INQUIRY BATCH :: ");
		Logger.info("==========================");
		Result result = null;
		try {
			Form<BatchRequestHelper> helper = form(BatchRequestHelper.class)
					.bindFromRequest();
			Logger.info("CashIn Inquiry Request " + helper.data());
			if (helper.hasErrors()) {
				result = unauthorized(ResponseHelper.createResponse(
						Definition.STATUS_INVALIDPARAMS, "Invalid parameters",
						helper.errorsAsJson()));
				Logger.info("Invalid parameters : " + helper.errorsAsJson());
			} else {
				result = ManageBatch.CashInInquiry(helper.get().transactionInquiryId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = ok(ResponseHelper.createResponse(Definition.STATUS_UNKNOWN, e.getMessage()));
			Logger.info("Exception : " + e.getMessage());
		}
		Logger.info("--------------------");
		return result;
	}

	public static Result remit() {
		Logger.info("------------------------");
		Logger.info(":: CASHIN REMIT BATCH :: ");
		Logger.info("========================");
		Result result = null;
		try {
			Form<BatchRequestHelper> helper = form(BatchRequestHelper.class)
					.bindFromRequest();			
			Logger.info("CashIn Remit Request " + helper.data());
			if (helper.hasErrors()) {
				result = unauthorized(ResponseHelper.createResponse(
						Definition.STATUS_INVALIDPARAMS, "Invalid parameters",
						helper.errorsAsJson()));
				Logger.info("Invalid parameters : " + helper.errorsAsJson());
			} else {
				result = ManageBatch.CashInRemit(helper.get().transactionInquiryId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = ok(ResponseHelper.createResponse(Definition.STATUS_UNKNOWN, e.getMessage()));
			Logger.info("Exception : " + e.getMessage());
		}
		Logger.info("-----------");
		return result;
	}

}
