package kirimdoku.helpers;

import play.data.validation.Constraints;
import controllers.BaseForm;
import models.Customer;

public class CustomerHelper extends BaseForm {
	
	public String agentKey;
	
	@Constraints.Required
	public Customer customer;
	
	public String validate() {
		try {
			super.validateAgentKey(this.agentKey);
			this.customer.agent = this.user;
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return "Invalid parameter " + e.getMessage();
		}
	}
	
}
