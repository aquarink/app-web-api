package kirimdoku.interfaces;

import static play.data.Form.form;
import org.codehaus.jackson.node.ObjectNode;
import kirimdoku.helpers.InquiryBillPaymentHelper;
import kirimdoku.helpers.SecurityAgentMobileAuthenticator;
import kirimdoku.util.Utility;
import controllers.LoggerAction;
import controllers.helpers.Definition;
import controllers.helpers.ResponseHelper;
import controllers.helpers.TransferResponse;
import play.Logger;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;

@Security.Authenticated(SecurityAgentMobileAuthenticator.class)
@With(LoggerAction.class)
public class InquiryBillPayment extends Controller {
	
	public static Result doInquiry() {
		Logger.info("-------------------------------");
		Logger.info("::  DO INQUIRY BILL PAYMENT  ::");
		Logger.info("===============================");
		response().setContentType("application/json; charset=utf-8");
		
		ObjectNode response = Json.newObject();
		Result result = null;
		try {
			Form<InquiryBillPaymentHelper> helper = form(InquiryBillPaymentHelper.class).bindFromRequest();
			Logger.info("Do Inquiry Bill Payment Request " + helper.data());
			if (helper.hasErrors()) {
				result = unauthorized(ResponseHelper.createResponse(
						Definition.STATUS_INVALIDPARAMS, "Invalid parameters",
						helper.errorsAsJson()));
				Logger.info("Invalid parameters : " + helper.errorsAsJson());
			} else {
				InquiryBillPaymentHelper inquiryBillPaymentHelper = helper.get();
				TransferResponse transferResponse = Utility.inquiryBillPayment(inquiryBillPaymentHelper);
				if (transferResponse.referenceStatus.equals("0000")) {
					response.put("status", Definition.STATUS_OK);
					response.put("message", "Inquiry Bill Payment success");
					response.put("inquiryBillPayment", Json.parse(transferResponse.referenceMessage));
					response.put("systrace", inquiryBillPaymentHelper.systrace);
				} else {
					response = ResponseHelper.createResponse(
							Definition.STATUS_UNKNOWN, "Invalid method");
				}
				Logger.debug("Do Inquiry Bill Payment Result : "+response.toString());
				result = ok(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = ok(ResponseHelper.createResponse(
					Definition.STATUS_UNKNOWN, e.getMessage()));
			Logger.info("Exception : " + e.getMessage());
		}

		return result;
	}
	
	
}
