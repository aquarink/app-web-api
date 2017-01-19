package kirimdoku.helpers;

import org.hibernate.validator.constraints.Length;

import models.Corporate;
import models.Transaction;
import play.data.validation.Constraints;
import play.mvc.Http.Request;
import controllers.BaseForm;
import controllers.tokens.TransactionToken;

public class CashOutInquiryHelper extends BaseForm {
	
	@Constraints.Required
	@Length(min = 9, max = 9)
	public String transactionId;
	
	public models.Transaction transaction;
	public Request request;
	public String agentKey;
	
	public String validate() {
		String err;
		if ((err = this.validateAgentKey(this.agentKey)) != null) {
			return err;
		}
		
		TransactionToken transactionToken = TransactionToken.fromString(this.transactionId);
		this.transaction = Transaction.findByToken(transactionToken);
		if (this.transaction == null) {
			return "Invalid transaction";
		}
		
		String allowChannel = this.user.corporate.configuration.channelCode != null ? this.user.corporate.configuration.channelCode : "";
		if (!allowChannel.contains(this.transaction.channel.code)) {
			return "Unauthorized channel permission";
		}
		if (this.user.corporate.permission != Corporate.PERMISSION_RECEIVE) {
			return "Unauthorized receive permission";
		}
		
		return null;
	}
	
}
