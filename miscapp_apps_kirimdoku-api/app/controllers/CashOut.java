package controllers;

import models.TransactionInquiry;
import org.hibernate.validator.constraints.Length;
import controllers.agents.cashout.BaseAgent;
import controllers.agents.cashout.DokuAgent;
import controllers.agents.cashout.SimulatorAgent;
import controllers.helpers.Definition;
import controllers.helpers.ResponseHelper;
import controllers.helpers.SecurityAgentAuthenticator;
import play.Logger;
import play.data.Form;
import static play.data.Form.form;
import play.data.validation.Constraints;
import play.data.validation.Constraints.Min;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;

/**
 * class wrapper for handling any cash out transactions
 * 
 */
@With(LoggerAction.class)
@Security.Authenticated(SecurityAgentAuthenticator.class)
public class CashOut extends Controller {

	/**
	 * Method called when doing inquiry to Agent used mostly for cash out
	 * 
	 * parameters: - agentKey - hashed key of agent for authentication -
	 * requestId - unique identification from 3rd party caller, must be
	 * generated by 3rd party caller - transactionId - primary identification of
	 * transaction ID - beneficiaryId - optional beneficiary identification
	 * (KTP/SIM/Anything)
	 * 
	 * @return status result of inquiry after retrieved from the sourced agent
	 */
	public static Result inquiry() {
		try {
			Form<InquiryForm> form = form(InquiryForm.class).bindFromRequest();
			if (form.hasErrors()) {
				Logger.debug("Invalid parameters : " + form.errorsAsJson());
				return unauthorized(ResponseHelper.createResponse(Definition.STATUS_INVALIDPARAMS, "Invalid parameters", form.errorsAsJson()));
			}
			Logger.debug("Inquiry form "+Json.toJson(form.get()));

			// Get the routing and initialize agents
			BaseAgent agent = getAgentByForm(form.get());

			// Call the Agents inquiry via async and return Promise
			return async(agent.inquiry(request(), form.get()));
		} catch (Exception e) {
			e.printStackTrace();
			return ok(ResponseHelper.createResponse(Definition.STATUS_UNKNOWN, e.getMessage()));
		}
	}

	/**
	 * Validate fund, the second step for authentication inquiry
	 */
	public static Result validate() {
		try {
			Form<ValidateForm> form = form(ValidateForm.class).bindFromRequest();
			if (form.hasErrors()) {
				return unauthorized(ResponseHelper.createResponse(Definition.STATUS_INVALIDPARAMS, "Invalid parameters", form.errorsAsJson()));
			}
			Logger.debug("Validate form " + form);

			models.Transaction transaction = form.get().inquiry.transaction;
			Logger.debug("Validate trx " + transaction);
			
			if (transaction == null) {
				return ok(ResponseHelper.createResponse(Definition.STATUS_ILLEGALPARAMS, "Transaction id not valid"));
			} else if (transaction.status == models.Transaction.STATUS_PENDING) {
				// Get the routing and initialize agents
				BaseAgent agent = getAgentByForm(form.get());
		
				// Call the Agents inquiry via async and return Promise
				return async(agent.validate(request(), form.get(), transaction));
	        } else if (transaction.status == models.Transaction.STATUS_LOCKED) {
				return ok(ResponseHelper.createResponse(Definition.STATUS_BLOCKED, "Transaction has been blocked"));
			} else {
				return ok(ResponseHelper.createResponse(Definition.STATUS_UNAUTHORIZED, "Transaction denied by transaction status"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ok(ResponseHelper.createResponse(Definition.STATUS_UNKNOWN, e.getMessage()));
		}
	}

	/**
	 * The final and the 3rd step to collect fund by authenticated beneficiary,
	 * and mark the fund as collected
	 * 
	 * Parameters: - agentKey - hashed key of agent for authentication -
	 * requestId - unique identification from 3rd party caller, must be
	 * generated by 3rd party caller - transactionId - primary identification of
	 * transaction ID - beneficiaryId - optional beneficiary identification
	 * (KTP/SIM/Anything)
	 * 
	 */
	public static Result collect() {
		try {
			Form<CollectForm> form = form(CollectForm.class).bindFromRequest();
			
			if (form.hasErrors()) {
				return unauthorized(ResponseHelper.createResponse(Definition.STATUS_INVALIDPARAMS, "Invalid parameters", form.errorsAsJson()));
			}
			Logger.debug("Collect form " + form);

			// Check Cash in gateway database
			models.Transaction transaction = form.get().inquiry.transaction;
			
			if (transaction == null) {
				return ok(ResponseHelper.createResponse(Definition.STATUS_ILLEGALPARAMS, "Transaction id not valid"));
			} else if (!transaction.equals(form.get().inquiry.transaction)) {
				return ok(ResponseHelper.createResponse(Definition.STATUS_ILLEGALSECURITY, "Transaction id is not match with inquiryId"));
			} else if (transaction.status == models.Transaction.STATUS_PENDING) {

				// Get routing agents
				BaseAgent agent = getAgentByForm(form.get());
		
				// Call the agents collection
				return async(agent.collect(request(), form.get(), transaction));
			} else {
				return ok(ResponseHelper.createResponse(Definition.STATUS_UNAUTHORIZED, "Transaction denied by transaction status"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ok(ResponseHelper.createResponse(Definition.STATUS_UNKNOWN, e.getMessage()));
		}
	}

	private static BaseAgent getAgentByForm(BaseForm form) {
		// The agent is saved and could be retrieved from
		// SecurityAgentAuthenticator, so we do just need to call
		// request.username()

		BaseAgent agent = null;
		if ((form.corporate != null) && (form.corporate.code.equals("AAA"))) {
			agent = new SimulatorAgent();
		} else {
			agent = new DokuAgent();
		}
		return agent;
	}

	public static class InquiryForm extends BaseForm {

		@Constraints.Required
		public models.Transaction transaction;

		public String validate() {
			String err;
			if ((err = this.validateAgentKey(request().username())) != null) {
				return err;
			}

			return null;
		}
	}

	public static class ValidateForm extends BaseForm {

		@Constraints.Required
		public models.TransactionInquiry inquiry;

		@Constraints.Required
		@Length(min = 4, max = 16)
		public String auth1;

		public String validate() {
			String err;
			if ((err = this.validateAgentKey(request().username())) != null) {
				return err;
			}

			if ((err = this.validateInquiryId()) != null) {
				return err;
			}

			if (this.auth1 == null) {
				return "Auth1 are required";
			}

			return null;
		}

		private String validateInquiryId() {
			TransactionInquiry trxInquiry = TransactionInquiry.findByCode(inquiry.idToken);

			//TODO Add if transaction inquiry is this transaction
			if ((trxInquiry != null) && (trxInquiry.typeId.equals(TransactionInquiry.Type.CASHOUT_INQUIRY)) && (trxInquiry.transaction != null)
					&& (trxInquiry.user.equals(user))) {
				this.inquiry = trxInquiry;
				return null;
			} else if (trxInquiry == null) {
				return "invalid inquiryId";
			} else if (!trxInquiry.typeId.equals(TransactionInquiry.Type.CASHOUT_INQUIRY)) {
				return "invalid inquiryId - denial type";
			} else if (trxInquiry.transaction == null) {
				return "invalid inquiryId - denial association";
			} else if (!trxInquiry.user.equals(user)) {
				return "invalid inquiryId - denial owner";
			}
			return "inquiryId - unable to restraint logic";
		}

	}

	public static class CollectForm extends BaseForm {
		
		@Constraints.Required
		public models.TransactionInquiry inquiry;

		@Constraints.Required
		@Min(1000)
		public Long validationId;
		
		@Constraints.Required
		@Length(min = 4, max = 16)
		public String auth2;
		
		
		public String receiveTrxId;
		
		//TODO Optional cashIntome param, might need to be removed?
		public java.util.Date cashOutTime;

		public String validate() {
			String err;
			if ((err = this.validateAgentKey(request().username())) != null) {
				return err;
			}
			
			if ((err = this.validateInquiryId()) != null) {
				return err;
			}
			
			if ((err = this.validateValidationId()) != null) {
				return err;
			}
			
			
			return null;
		}

		private String validateInquiryId() {
			TransactionInquiry trxInquiry = TransactionInquiry.findByCode(inquiry.idToken);
		
			if ((trxInquiry != null) && (trxInquiry.typeId.equals(TransactionInquiry.Type.CASHOUT_INQUIRY)) && (trxInquiry.transaction != null)
					&& (trxInquiry.user.equals(user))) {
				this.inquiry = trxInquiry;
				return null;
			} else if (trxInquiry == null) {
				return "invalid inquiryId";
			} else if (!trxInquiry.typeId.equals(TransactionInquiry.Type.CASHOUT_INQUIRY)) {
				return "invalid inquiryId - denial type";
			} else if (trxInquiry.transaction == null) {
				return "invalid inquiryId - denial association";
			} else if (!trxInquiry.user.equals(user)) {
				return "invalid inquiryId - denial owner";
			}
			return "inquiryId - unable to restraint logic";
		}

		private String validateValidationId() {
			if(inquiry == null) {
			} else if(inquiry.validationId == null) {
			} else if (inquiry.validationId.equals(String.valueOf(validationId))) {
				return null;
			}
			
			return "invalid inquiryId - denial validationId "+validationId+" - "+inquiry.validationId;
		}
	}
}