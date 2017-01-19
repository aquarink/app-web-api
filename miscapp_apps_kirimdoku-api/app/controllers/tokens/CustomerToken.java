package controllers.tokens;

import models.Customer;

/*
 * <pre>
 * C[XX][YYYYYYY]
 * 
 * XX: Describe country code, ex: ID / MY
 * YYYYYYY: Sequential number
 * 
 * </pre>
 */
public class CustomerToken {
	public final char identifier;
	public final String countryCode;
	public final Long id;

	public static CustomerToken fromString(String str) {
		try {
			return new CustomerToken(str.charAt(0), str.substring(1,3), Long.valueOf(str.substring(3,10)));
		} catch (Exception e) {
			return null;
		}
	}

	public static CustomerToken fromCustomer(Customer customer) {
		return new CustomerToken('C', customer.country.code, customer.id);
	}

	public CustomerToken(char identifier, String countryCode, Long id) {
		this.identifier = identifier;
		this.countryCode = countryCode;
		this.id = id;
	}

	@Override
	public String toString() {
//		return "[ID:" + identifier + "] [Reserved:" + reserved + "] [AgentCode:" + corporate + "] [Channel:" + channel + "] [User:" + user + "]";
		return String.valueOf(identifier)+String.valueOf(countryCode)+String.format("%07d", id);
	}
}