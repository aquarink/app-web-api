package kirimdoku.interfaces;

import static play.data.Form.form;
import java.util.ArrayList;
import java.util.Date;
import kirimdoku.helpers.ChangeTransactionHelper;
import kirimdoku.helpers.SecurityAgentMobileAuthenticator;
import models.Label;
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
public class ChangeTransaction extends Controller {
	
	public static Result process() {
		Logger.info("--------------------------------");
		Logger.info(":: CHANGE TRANSACTION PROCESS :: ");
		Logger.info("================================");
		Result result = null;
		try {
			Form<ChangeTransactionHelper> helper = form(ChangeTransactionHelper.class).bindFromRequest();
			Logger.info("Change Transaction Request " + helper.data());
			if (helper.hasErrors()) {
				result = unauthorized(ResponseHelper.createResponse(
						Definition.STATUS_INVALIDPARAMS, "Invalid parameters",
						helper.errorsAsJson()));
				Logger.info("Invalid parameters : " + helper.errorsAsJson());
			} else {
				ObjectNode response = Json.newObject();
				if(helper.get().pin.equals(helper.get().transaction.auth1)) {
					if (helper.get().transaction.status == Transaction.STATUS_PENDING) {
						models.Customer beneficiary = helper.get().beneficiary;
						if (beneficiary.firstName.equalsIgnoreCase(beneficiary.lastName)) {
							beneficiary.lastName = "";
						}
						helper.get().transaction.beneficiary = helper.get().beneficiary.save(true, true);
						helper.get().transaction.modifiedTime = new Date();
						helper.get().transaction.update();
						
						if (helper.get().transaction.labels == null) helper.get().transaction.labels = new ArrayList<Label>();
						if (helper.get().transaction.labels.contains(Label.find.byId(49L)) ) {
							response.put("status", Definition.STATUS_UNSUPPORTED);
							response.put("message", "This transaction is unavailable to change anymore");
						} else {
							helper.get().transaction.labels.add(Label.find.byId(49L));
							helper.get().transaction.saveManyToManyAssociations("labels");
					    	response.put("status", Definition.STATUS_OK);
					    	response.put("message", "Transaction has been successfully changed");
						}
					} else {
						response.put("status", Definition.STATUS_UNSUPPORTED);
						response.put("message", "This transaction is unavailable to change");
					}
				} else {
					TransactionInquiry transactionInquiry = TransactionInquiry.findTransactionInquiryByTransactionIdUser(helper.get().transaction.id, helper.get().user);
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
				}
				Logger.info("Change Transaction Result "+response.toString());
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
