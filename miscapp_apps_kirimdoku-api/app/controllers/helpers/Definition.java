package controllers.helpers;


public class Definition {
	public static final int STATUS_OK = 0;
	public static final int STATUS_UNKNOWN = -1;
	public static final int STATUS_UNAUTHORIZED = 9;
	public static final int STATUS_SESSIONEXPIRED = 10;
	public static final int STATUS_INVALIDPARAMS = 11;
	public static final int STATUS_ILLEGALPARAMS = 12;
	public static final int STATUS_ILLEGALSECURITY = 13;
	public static final int STATUS_UNSUPPORTED = 16;
	public static final int STATUS_NOTFOUND = 21;
	public static final int STATUS_BLOCKED = 91;
	
	public final static String FIELD_AGENTKEY = "agentKey";
	public final static String FIELD_REQUESTID = "requestId";
	public final static String FIELD_SIGNATURE = "signature";
	public final static String FIELD_SESSIONID = "sessionId";
	
	public final static String FIELD_TRANSACTIONID = "transactionId";
	public static final String FIELD_INQUIRYID = "inquiryId";
	public static final String FIELD_VALIDATIONID = "validationId";
	public static final String FIELD_ERRORS = "errors";
	
	public static final String FIELD_AUTH1 = "auth1";
	public static final String FIELD_AUTH2 = "auth2";
	
	public final static String FIELD_RESPONSE_STATUS = "status";
	public final static String FIELD_RESPONSE_MESSAGE = "message";
	
	// DEFINITION ACTIVITY
	public final static int DO_SIGNON = 0;
	public final static int DO_INQUIRY = 1;
	public final static int DO_PAYMENT = 2;
	public final static int DO_ADVICE = 3;
	
}
