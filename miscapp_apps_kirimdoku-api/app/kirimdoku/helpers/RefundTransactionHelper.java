package kirimdoku.helpers;

import org.hibernate.validator.constraints.Length;

import play.data.validation.Constraints;
import controllers.BaseForm;
import controllers.tokens.TransactionToken;

public class RefundTransactionHelper extends BaseForm {
	
	@Constraints.Required
	public String agentKey;
	
	@Constraints.Required
	@Length(min = 9, max = 9)
	public String transactionId;
	
	public models.Transaction transaction;
	
	@Constraints.Required
	@Length(min = 4, max = 4)
	public String pin;

	public String validate() {
		try {
			String err;
			if ((err = this.validateAgentKey(this.agentKey)) != null) {
				return err;
			}
			TransactionToken token = TransactionToken.fromString(this.transactionId);
			if(token != null) {
				this.transaction = models.Transaction.findByToken(token);
				
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
