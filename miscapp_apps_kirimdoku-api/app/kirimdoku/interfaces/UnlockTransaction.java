package kirimdoku.interfaces;

import static play.data.Form.form;

import java.util.Date;
import kirimdoku.helpers.SecurityAgentMobileAuthenticator;
import kirimdoku.helpers.UnlockTransactionHelper;
import models.TransactionInquiry;
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
public class UnlockTransaction extends Controller {
	
	public static Result process() {
		Logger.info("--------------------------------");
		Logger.info(":: UNLOCK TRANSACTION PROCESS :: ");
		Logger.info("================================");
		Result result = null;
		try {
			Form<UnlockTransactionHelper> helper = form(UnlockTransactionHelper.class).bindFromRequest();
			Logger.info("Refund Transaction Request " + helper.data());
			if (helper.hasErrors()) {
				result = unauthorized(ResponseHelper.createResponse(
						Definition.STATUS_INVALIDPARAMS, "Invalid parameters",
						helper.errorsAsJson()));
				Logger.info("Invalid parameters : " + helper.errorsAsJson());
			} else {
				models.Transaction transaction = models.Transaction.find.byId(TransactionToken.fromString(helper.get().transactionId).transactionId);
				ObjectNode response = Json.newObject();

				response = Json.newObject();
				if(transaction.status == models.Transaction.STATUS_LOCKED) {
					Logger.info("PIN : "+transaction.auth1);
					if(helper.get().pin != null && helper.get().pin.equals(transaction.auth1)) {
						if (helper.get().newPin != null) {
							transaction.modifiedTime = new Date();
							transaction.status = models.Transaction.STATUS_PENDING;
							transaction.auth1 = helper.get().newPin;
							transaction.auth2 = helper.get().pin;
							TransactionInquiry.resetInvalidAuthByTransaction(transaction);
							transaction.save();
							response.put("status", 0);
							response.put("message", "Transaction has been unlocked");
						} else {
							response = ResponseHelper.createResponse(Definition.STATUS_UNAUTHORIZED, "New PIN are invalid");
						}
					} else {
						response = ResponseHelper.createResponse(Definition.STATUS_UNAUTHORIZED, "PIN is not match. Try Again!");
					}
				} else {
					response = ResponseHelper.createResponse(Definition.STATUS_UNSUPPORTED, "Transaction is not unlockable");
				}
				Logger.info("Unlock Transaction Result "+response.toString());
				result = ok(response);
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
