package controllers.tokens;

import models.Transaction;
import models.User;

/**
 * <pre>
 * A[X][YYYY]
 * 
 * X: 1 - Corporate
 * X: 2 - MainAgent
 * X: 3 - Supervisor
 * X: 4 - Operator
 * 
 * YYYY: Sequential number
 * 
 * Previous Example: 
 * A0AAA0200001
 * 
 * A 0 AAA 02 00001
 * | | |   |  |
 * 
 * </pre>
 * 
 * @author zi
 * 
 */
public class AgentToken {
	public final char identifier;
	public final char reserved;
	public final Long id;

	public static AgentToken fromString(String str) {
		return new AgentToken(str.charAt(0), str.charAt(1), Long.valueOf(str.substring(2, 6)));
	}
	
	public static AgentToken fromUser(models.User user) {
		Character reserved = '0';
		if(user.hasRole("mainagent")) reserved = '2';
		else if(user.hasRole("supervisor")) reserved = '3';
		else if(user.hasRole("operator")) reserved = '4';
		return new AgentToken('A', reserved, user.id);
	}

	public AgentToken(char identifier, char reserved, Long id) {
		this.identifier = identifier;
		this.reserved = reserved;
		this.id = id;
	}

	@Override
	public String toString() {
		return String.valueOf(identifier)+String.valueOf(reserved)+String.format("%04d", id);
	}
}