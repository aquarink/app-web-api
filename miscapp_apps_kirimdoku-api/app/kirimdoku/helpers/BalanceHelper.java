package kirimdoku.helpers;

import java.math.BigDecimal;
import controllers.BaseForm;

public class BalanceHelper extends BaseForm {
	
	public String agentKey;
	public BigDecimal creditScore;
	public BigDecimal creditAlertLimit;
	public BigDecimal creditLeft;
	
	public String validate() {
		try {
			super.validateAgentKey(this.agentKey);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return "Invalid parameter " + e.getMessage();
		}
	}
	
}
