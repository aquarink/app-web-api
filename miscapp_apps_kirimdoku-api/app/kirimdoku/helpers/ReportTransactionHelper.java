package kirimdoku.helpers;

import java.util.List;
import kirimdoku.util.Utility;
import play.data.validation.Constraints.Max;
import play.data.validation.Constraints.Min;
import models.User;
import controllers.BaseForm;
import controllers.tokens.AgentToken;
import controllers.tokens.TransactionToken;

public class ReportTransactionHelper extends BaseForm {

	public models.Country senderCountry;
	public models.Country beneficiaryCountry;
	
	// User Agent Login
	public String agentKey;
	
	// Filter By Agent
	public User agentFilter;
	public String agentCode;
	
	public String trxStartDate;
	public String trxEndDate;
	public String transactionCode;
	public Long transactionId;
	public Integer transactionsStatus;
	public List<TransactionsStatus> transactionStatusList;
	public String channelCode;
	
	public String customerName;
	
	@Min(0)
	@Max(1000000)
	public int page = 0;
	public String sortBy = "createdTime";
	public String order = "desc";
	
	@Min(1)
	@Max(1000000)
	public int pageSize = 25;
	
	public int totalRowCount = 0;
	public int totalPageCount = 0;
	
    public final String validate() {
		String err;
		if ((err = this.validateAgentKey(this.agentKey)) != null) {
			return err;
		}
    	
    	if((this.transactionCode != null) && (!this.transactionCode.isEmpty())) {
    		try {
    			TransactionToken token = TransactionToken.fromString(transactionCode);
	    		if (token != null) {
	    			this.transactionId = token.transactionId;
	    		}
    		} catch(Exception e) {
    		}
    	} 
    	
    	if((this.agentCode != null) && (!this.agentCode.isEmpty())) {
    		try {
	    		AgentToken token = AgentToken.fromString(this.agentCode);
	    		if (token != null) {
	    			this.agentFilter = models.User.find.byId(token.id);
	    		}
    		} catch(Exception e) {
    		}
    	}
    	if((this.trxStartDate != null) && (!this.trxStartDate.isEmpty())) {
    		try {
	    		Utility.dateFormatShow.parse(this.trxStartDate);
    		} catch(Exception e) {
    			this.trxStartDate = null;
    		}
    	}
    	if((this.trxEndDate != null) && (!this.trxEndDate.isEmpty())) {
    		try {
	    		Utility.dateFormatShow.parse(this.trxEndDate);
    		} catch(Exception e) {
    			this.trxEndDate = null;
    		}
    	}
    	if (this.page < 0) {
    		this.page = 0;
    	}
    	return null;
    }
    
	public static class TransactionsStatus {
		public int id;
		public String status;
		
		public TransactionsStatus(int id, String status) {
			this.id = id;
			this.status = status;
		}
	}
}
