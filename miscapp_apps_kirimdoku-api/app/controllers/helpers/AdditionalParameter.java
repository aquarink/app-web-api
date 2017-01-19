package controllers.helpers;

import java.util.List;


public class AdditionalParameter {
	
	public String systrace;
	public String accessToken;
	public String chainMallId;
	public String accountName;
	public List<String> banks;
	public List<BillPaymentService> billPaymentService;
	
	public static class BillPaymentService {
		
		public String billPaymentServiceName;
		public List<Biller> biller;
		
	}	
	
	public static class Biller {
		
		public String billName;
		public List<Operator> operator;
		
	}
	
	public static class Operator {
		
		public String operatorName;
		public String billerCode;
		
	}
	
}
