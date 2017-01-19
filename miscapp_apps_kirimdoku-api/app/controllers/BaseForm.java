package controllers;

import models.Corporate;
import models.User;
import controllers.tokens.AgentToken;

public abstract class BaseForm {

    public User user;
    public Corporate corporate;
    
	protected String validateAgentKey(String agentKey) {
		
    	if ((agentKey != null) && (agentKey.length()>0)) {
    		try {
//    			Logger.debug("Attempt agentKey 1 "+agentKey);
    			AgentToken agentToken = AgentToken.fromString(agentKey);
    			
    			if (agentToken.identifier == 'A') {
					this.user = User.find.byId(Long.valueOf(agentToken.id));
					if (this.user == null) {
						return "Invalid agent key: unknown user";
					}
					
					this.corporate = this.user.corporate;
					if (this.corporate == null) {
						return "Invalid agent key: unknown corporate partner code";
					}
					return null;
    			} else {
    				return "Invalid agent key: unknown identifier";
    			}
    		} catch(Exception e) {
    			return "Invalid agent key";
    		}
    	}
    	return "Unauthorized agent key access";
    }
}