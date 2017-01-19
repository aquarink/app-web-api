package controllers.tokens;

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
		Character identifier = '0';
		if(user.hasRole("mainagent")) identifier = '2';
		else if(user.hasRole("supervisor")) identifier = '3';
		else if(user.hasRole("operator")) identifier = '4';
		return new AgentToken('A', identifier, user.id);
	}

	public AgentToken(char identifier, char reserved, Long id) {
		this.identifier = identifier;
		this.reserved = reserved;
		this.id = id;
	}

	@Override
	public String toString() {
//		return "[ID:" + identifier + "] [Reserved:" + reserved + "] [AgentCode:" + corporate + "] [Channel:" + channel + "] [User:" + user + "]";
		return String.valueOf(identifier)+String.valueOf(reserved)+String.format("%04d", id);
	}
}