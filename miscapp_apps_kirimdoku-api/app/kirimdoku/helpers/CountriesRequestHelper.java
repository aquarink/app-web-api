package kirimdoku.helpers;

import controllers.BaseForm;

public class CountriesRequestHelper extends BaseForm {
	
	public String agentKey;
	
	public String validate() {
		super.validateAgentKey(this.agentKey);
		
		return null;
	}
	
}
