package kirimdoku.helpers;

import play.data.validation.Constraints;
import controllers.BaseForm;

public class InquiryBillPaymentHelper extends BaseForm {
	
	@Constraints.Required
	public String agentKey;
	
	@Constraints.Required
	public String billPaymentService;
	
	@Constraints.Required
	public String selectBill;
	
	@Constraints.Required
	public String selectOperator;
	
	public String systrace;
	public String accountNumber;
	public String amount;
	
	public String validate() {
		try {
			String err;
			if ((err = this.validateAgentKey(this.agentKey)) != null) {
				return err;
			}
			if (!this.billPaymentService.trim().equals("PREPAID RELOAD")) {
				return "Invalid bill payment service";
			}
			if (this.selectOperator.trim().equals("")) {
				return "Invalid operator id";
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return "Invalid parameter " + e.getMessage();
		}
	}
	
}
