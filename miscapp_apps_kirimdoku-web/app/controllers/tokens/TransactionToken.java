package controllers.tokens;

import models.Transaction;

/**
 * DK[X][YYYYYY]
 * 
 * D[KX][YYYYYY]
 * 
 * KX: Reserved
 * YYYYYY: Sequential
 *
 */
public class TransactionToken {
	public final char identifier;
	public final String reserved;
	public final Long transactionId;

	public static TransactionToken fromString(String str) {
		try {
			return new TransactionToken(str.charAt(0), str.substring(1, 3), Long.valueOf(str.substring(3,9)));
		} catch (Exception e) {
			return null;
		}
	}

	public static TransactionToken fromTransaction(Transaction trx) {
		return new TransactionToken('D', "K0", trx.id);
	}

	public TransactionToken(char identifier, String reserved, Long transactionId) {
		this.identifier = identifier;
		this.reserved = reserved;
		this.transactionId = transactionId;
	}
	
	

	@Override
	public String toString() {
		//return "[ID:" + identifier + "] [Reserved:" + reserved + "] [AgentCode:" + corporate + "] [Channel:" + channel + "] [User:" + user + "]";
		return String.valueOf(identifier)+String.valueOf(reserved)+String.format("%06d", transactionId);
	}
}
