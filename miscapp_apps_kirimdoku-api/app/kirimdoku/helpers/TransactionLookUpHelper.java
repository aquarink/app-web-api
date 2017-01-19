package kirimdoku.helpers;

import org.hibernate.validator.constraints.Length;

import models.Customer;
import controllers.BaseForm;
import controllers.tokens.TransactionToken;
import play.data.validation.Constraints;

public class TransactionLookUpHelper extends BaseForm {
	
	public String agentKey;
	
	@Constraints.Required
	@Length(min = 9, max = 9)
	public String transactionId;
	
	public TransactionToken token;
	public models.Transaction transaction;
	public String pin1;
	public String pin2;
	public Customer beneficiary;

	public String validate() {
		super.validateAgentKey(this.agentKey);
		
		token = TransactionToken.fromString(transactionId);
		if(token != null) {
			transaction = models.Transaction.findByToken(token);
			if (transaction == null) {
				return "Transaction could not be found";
			}
		} else {
			return "Transaction id is invalid";
		}
		return null;
	}
	
}
