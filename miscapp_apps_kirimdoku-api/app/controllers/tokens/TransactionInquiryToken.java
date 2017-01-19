package controllers.tokens;

import java.util.Date;
import java.util.Random;
import models.TransactionInquiry;

/**
 * Transaction Token to compile/decompile token string of transaction id
 * 
 * sample:
 * I0
 * T0AAAMYS02000001
 * @author zi
 *
 */
public class TransactionInquiryToken {
	public Long id;
	public Date requestTime;
	public int r1;
	public int r2;
	
	public static TransactionInquiryToken fromString(String str) {
		try {
			TransactionInquiryToken token = new TransactionInquiryToken();
//			String identifier = str.substring(0, 2);
			token.r1 = Integer.valueOf(str.substring(2, 4)).intValue();
			token.r2 = Integer.valueOf(str.substring(4, 6)).intValue();
			token.id = Long.valueOf(str.substring(6)).longValue();
			token.id = (token.id / token.r2) / (token.r1+81);
			return token;
		} catch (Exception e) {
			return null;
		}
	}

	public static TransactionInquiryToken fromTransactionInquiry(TransactionInquiry transactionInquiry) {
		TransactionInquiryToken token = new TransactionInquiryToken();
		token.id = transactionInquiry.id;
		token.requestTime = transactionInquiry.requestTime;
		return token;
	}
	
	@Override
	public String toString() {
		try {
			Random r = new Random(this.id);
			int r1 = r.nextInt(98)+1;
			int r2 = r.nextInt(98)+1;
			
			long idEnc = (this.id * (r1+81)) * r2;
			return "I0"
					+String.format("%02d", r1)
					+String.format("%02d", r2)
					+String.format("%07d", idEnc);
		} catch (Exception e) {
			return null;
		}
	}

}
