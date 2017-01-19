package kirimdoku.interfaces;

import static play.data.Form.form;
import java.util.Date;
import kirimdoku.helpers.RefundTransactionHelper;
import kirimdoku.helpers.SecurityAgentMobileAuthenticator;
import models.Transaction;
import models.TransactionInquiry;
import org.codehaus.jackson.node.ObjectNode;
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

@Security.Authenticated(SecurityAgentMobileAuthenticator.class)
@With(LoggerAction.class)
public class RefundTransaction extends Controller {
	
	public static Result process() {
		Logger.info("--------------------------------");
		Logger.info(":: REFUND TRANSACTION PROCESS :: ");
		Logger.info("================================");
		Result result = null;
		try {
			Form<RefundTransactionHelper> helper = form(RefundTransactionHelper.class).bindFromRequest();
			Logger.info("Refund Transaction Process Request " + helper.data());
			if (helper.hasErrors()) {
				result = unauthorized(ResponseHelper.createResponse(
						Definition.STATUS_INVALIDPARAMS, "Invalid parameters",
						helper.errorsAsJson()));
				Logger.info("Invalid parameters : " + helper.errorsAsJson());
			} else {
				ObjectNode response = Json.newObject();
				if (helper.get().transaction != null) {
					if(helper.get().transaction.status == models.Transaction.STATUS_PENDING) {
						if(helper.get().pin.equals(helper.get().transaction.auth1)) {
							helper.get().transaction.status = models.Transaction.STATUS_REFUNDED;
							helper.get().transaction.modifiedTime = new Date();
							helper.get().transaction.update();
							response.put("status", Definition.STATUS_OK);
							response.put("message", "Transaction has been successfully refunded");
						} else {
							TransactionInquiry transactionInquiry = TransactionInquiry.findTransactionInquiryByTransactionIdUser(helper.get().transaction.id, helper.get().user);
							if (transactionInquiry != null) {
								transactionInquiry.invalidAuthCount = transactionInquiry.invalidAuthCount+1;
								transactionInquiry.save();
								if (transactionInquiry.invalidAuthCount > 2) {
									helper.get().transaction.modifiedTime = new Date();
									helper.get().transaction.status = Transaction.STATUS_LOCKED;
									helper.get().transaction.save();
									response.put("status", Definition.STATUS_BLOCKED);
									response.put("message", "Transaction blocked");
								} else {
									response.put("status", Definition.STATUS_ILLEGALSECURITY);
									response.put("message", "PIN are invalid");
								}
							} else {
								response.put("status", Definition.STATUS_UNSUPPORTED);
								response.put("message", "Transaction not found");
							}
						}
					} else if(helper.get().transaction.status == models.Transaction.STATUS_PENDING_REFUND) {
						helper.get().transaction.status = models.Transaction.STATUS_FULLREFUNDED;
						helper.get().transaction.modifiedTime = new Date();
						helper.get().transaction.update();
						response.put("status", Definition.STATUS_OK);
						response.put("message", "Transaction has been successfully full refunded");
					} else {
						response.put("status", Definition.STATUS_UNSUPPORTED);
						response.put("message", "Transaction is not refundable");
					}
				} else {
					response.put("status", Definition.STATUS_UNSUPPORTED);
					response.put("message", "Transaction not found");
				}
				
				Logger.info("Refund Transaction Process Result " + response.toString());
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
