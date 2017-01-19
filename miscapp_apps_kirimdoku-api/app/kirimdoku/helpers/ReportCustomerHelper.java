package kirimdoku.helpers;

import play.data.validation.Constraints.Max;
import play.data.validation.Constraints.Min;
import controllers.BaseForm;
import controllers.tokens.CustomerToken;

public class ReportCustomerHelper extends BaseForm {

	public String agentKey;
	
	public String customerCode;
	public String customerName;
	public String customerPhone;
	public Long customerId;
	
	public String personalIdType;
	public String personalId;
	
	@Min(0)
	@Max(1000000)
	public int page = 0;
	
	@Min(1)
	@Max(1000000)
	public int pageSize = 25;
	public String sortBy = "id";
	public String order = "desc";
	
	public int totalRowCount = 0;
	public int totalPageCount = 0;
	
	public final String validate() {
		String err;
		if ((err = this.validateAgentKey(this.agentKey)) != null) {
			return err;
		}
    	
    	if((this.customerCode != null) && (!this.customerCode.isEmpty())) {
    		try {
    			CustomerToken token = CustomerToken.fromString(this.customerCode);
	    		if (token != null) {
	    			customerId = token.id;
	    		}
    		} catch(Exception e) {
    		} finally {
    			if (this.customerId == null) {
    				this.customerId = 0L;
    			}
    		}
    	} 
    	if (this.page < 0) {
    		this.page = 0;
    	}
    	return null;
    }
}
