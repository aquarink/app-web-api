package kirimdoku.helpers;

import org.hibernate.validator.constraints.Length;

import models.Customer;
import play.data.validation.Constraints;
import controllers.BaseForm;
import controllers.tokens.TransactionToken;

public class ChangeTransactionHelper extends BaseForm {
	
	@Constraints.Required
	public String agentKey;
	
	@Constraints.Required
	@Length(min = 9, max = 9)
	public String transactionId;
	
	public TransactionToken token;
	public models.Transaction transaction;
	
	@Constraints.Required
	@Length(min = 4, max = 4)
	public String pin;

	@Constraints.Required
	public Customer beneficiary;

	public String validate() {
		try {
			super.validateAgentKey(this.agentKey);
			token = TransactionToken.fromString(this.transactionId);
			if(token != null) {
				transaction = models.Transaction.findByToken(token);
				if (transaction == null) {
					return "Transaction could not be found";
				}
			} else {
				return "Transaction id is invalid";
			}
			if (!this.transaction.agent.corporate.code.equals(this.corporate.code)) {
				return "Transaction could not be found";
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return "Invalid parameter " + e.getMessage();
		}
	}
}
