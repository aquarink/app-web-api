package helpers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;


public class UnitHelper {
	public static Long generateRequestId() {
		Date d = new Date();
		return d.getTime();
	}

	public static Object generateWordsToken() {
		return "ABCDEF123";
	}
	
	public static BigDecimal generateRandomAmount(double minLength, double maxLength) {
		Random r = new Random();
		Double d = minLength + (r.nextDouble() * (maxLength-minLength));
		return new BigDecimal(d);
	}
}
