package kirimdoku.interfaces;

import static play.data.Form.form;
import kirimdoku.helpers.CashInInquiryHelper;
import kirimdoku.helpers.CashInRemitHelper;
import kirimdoku.helpers.SecurityAgentMobileAuthenticator;
import kirimdoku.manage.Manage;
import org.codehaus.jackson.node.ObjectNode;
import controllers.LoggerAction;
import controllers.helpers.Definition;
import controllers.helpers.ResponseHelper;
import controllers.tokens.TransactionToken;
import play.Logger;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;

@Security.Authenticated(SecurityAgentMobileAuthenticator.class)
@With(LoggerAction.class)
public class CashIn extends Controller {

	public static Result inquiry() {
		Logger.info("--------------------");
		Logger.info(":: CASHIN INQUIRY :: ");
		Logger.info("====================");
		Result result = null;
		try {
			Form<CashInInquiryHelper> helper = form(CashInInquiryHelper.class)
					.bindFromRequest();
			Logger.info("CashIn Inquiry Request " + helper.data());
			if (helper.hasErrors()) {
				result = unauthorized(ResponseHelper.createResponse(
						Definition.STATUS_INVALIDPARAMS, "Invalid parameters",
						helper.errorsAsJson()));
				Logger.info("Invalid parameters : " + helper.errorsAsJson());
			} else {
				result = Manage.CashInInquiry(helper.get());
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
		Logger.info("------------------");
		Logger.info(":: CASHIN REMIT :: ");
		Logger.info("==================");
		Result result = null;
		try {
			Form<CashInRemitHelper> helper = form(CashInRemitHelper.class)
					.bindFromRequest();			
			Logger.info("CashIn Remit Request " + helper.data());
			if (helper.hasErrors()) {
				result = unauthorized(ResponseHelper.createResponse(
						Definition.STATUS_INVALIDPARAMS, "Invalid parameters",
						helper.errorsAsJson()));
				Logger.info("Invalid parameters : " + helper.errorsAsJson());
			} else {
				if (helper.get().inquiry.transaction == null) {
					if (helper.get().sender.personalIdCountry != null && helper.get().sender.personalIdCountry.code.equals("")) {
						helper.get().sender.personalIdCountry = null;
					}
					if ((helper.get().sender.idToken == null)
							|| helper.get().sender.idToken.isEmpty()) {
						helper.get().sender.save();
					}
					if (helper.get().beneficiary.personalIdCountry != null && helper.get().beneficiary.personalIdCountry.code.equals("")) {
						helper.get().beneficiary.personalIdCountry = null;
					}
					if ((helper.get().beneficiary.idToken == null)
							|| helper.get().beneficiary.idToken.isEmpty()) {
						helper.get().beneficiary.save();
					}
					result = Manage.CashInRemit(helper.get());
				} else {
					ObjectNode response = ResponseHelper.createResponse(
							Definition.STATUS_OK, "Pass");
					ObjectNode remit = Json.newObject();
					remit.put(
							"transactionId",
							TransactionToken.fromTransaction(
									helper.get().inquiry.transaction)
									.toString());
					response.put("remit", remit);
					response.put("warning",
							"Inquiry Process has been processed");
					Logger.info("CashIn Remit Result " + response.toString());
					result = unauthorized(response);
				}
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
