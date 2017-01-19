package controllers.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import models.Transaction;
import models.User;
import models.forms.CashInInquiryForm;
import models.forms.CashInForm;
import controllers.tokens.AgentToken;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

import play.Configuration;
import play.Logger;
import play.api.templates.Html;
import play.libs.F.Promise;
import play.libs.Json;
import play.libs.WS;
import play.libs.WS.Response;
import play.libs.WS.WSRequestHolder;
import tyrex.services.UUID;

import org.apache.commons.mail.*;

public class ApiHelper {

	private static final Configuration CONFIG_API = play.Play.application().configuration().getConfig("api");
	private static final Configuration CONFIG_EMAIL = play.Play.application().configuration().getConfig("email");
	private static final Configuration CONFIG_SMTP = play.Play.application().configuration().getConfig("smtp");

	private static final String URL_CASHIN_INQUIRY = CONFIG_API.getString("base_url") + "/cashin/inquiry";
	private static final String URL_CASHIN_REMIT = CONFIG_API.getString("base_url") + "/cashin/remit";

	private static final String URL_CASHOUT_INQUIRY = CONFIG_API.getString("base_url") + "/cashout/inquiry";
	private static final String URL_CASHOUT_VALIDATE = CONFIG_API.getString("base_url") + "/cashout/validate";
	private static final String URL_CASHOUT_COLLECT = CONFIG_API.getString("base_url") + "/cashout/collect";

	private static final String URL_TRANSACTION_DETAIL = CONFIG_API.getString("base_url") + "/transaction/";
	
	private static final String URL_CUSTOMER_DETAIL = CONFIG_API.getString("base_url") + "/customers/";

	private static final String URL_TRANSLATE = CONFIG_API.getString("base_url") + "/translate";
	
	private static String SMTP_HOST = CONFIG_SMTP.getString("host");
	private static Boolean SMTP_SSL = CONFIG_SMTP.getBoolean("ssl");
	private static Integer SMTP_PORT = CONFIG_SMTP.getInt("port");
	private static String SMTP_USER = CONFIG_SMTP.getString("user");
	private static String SMTP_PASSWORD = CONFIG_SMTP.getString("password");
	private static final int TIMEOUT_API = 80000;
	public static SimpleDateFormat datetimeFormatShow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static class ApiException extends Exception
	{
	
		private static final long serialVersionUID = 2395643784152607596L;
		private int status = -1;
		private String message = "Internal error";
		
		public ApiException(JsonNode node) {
			if((node != null) && (node.has("status"))) {
				this.status = node.path("status").asInt(-1);
				this.message = node.path("message").asText();
			}
		}
	
		public ApiException(Response response) {
			if(response != null) {
				this.status = response.getStatus();
				this.message = response.getStatusText();
			}
		}
		
		public ApiException(String message) {
			this.message = message;
		}
		
		public int getStatus() {
			return status;
		}
		
		public String getMessage() {
			return message;
		}
	}

	public static class CashIn {
		/**
		 * Private methods to doing inquiry to DRS
		 * 
		 * @param form
		 * @return json node
		 **/
		public static JsonNode inquiry(Transaction form) {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setDateFormat(datetimeFormatShow);
			String json = "";
			try {
				json = objectMapper.writeValueAsString(form);
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return inquiry(json);
		}

		/**
		 * Private methods to doing inquiry to DRS
		 * 
		 * @param form
		 *            CashInInquiryForm
		 * @return json node
		 **/
		public static JsonNode inquiry(CashInInquiryForm form) {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setDateFormat(datetimeFormatShow);
			String json = "";
			try {
				json = objectMapper.writeValueAsString(form);
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return inquiry(json);
		}

		private static JsonNode inquiry(String post) {
			// Send to WS and map return as JSON
			try {
				Logger.debug("Request inquiry ["+URL_CASHIN_INQUIRY+"] "+post);
				JsonNode result = fetchUrls(URL_CASHIN_INQUIRY, post.toString());
				return result;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		/**
		 * Private method for doing remit to DRS
		 * 
		 * @param form
		 * @return json node
		 */
		public static JsonNode remit(CashInForm form) {
			// build json node for post
			JsonNode post = Json.toJson(form);
			ObjectMapper objectMapper = new ObjectMapper();
			SimpleDateFormat datetimeFormatShow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			objectMapper.setDateFormat(datetimeFormatShow);
			Logger.debug("Sending remit form json "+post);
			String json = "";
			try {
				json = objectMapper.writeValueAsString(form);
			} catch (JsonGenerationException e1) {
				e1.printStackTrace();
			} catch (JsonMappingException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Logger.debug("Sending remit form json "+post);
			
			// Send to WS and map return as JSON
			try {
				JsonNode result = fetchUrls(URL_CASHIN_REMIT,json);
				Logger.debug("Remit Response : "+result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	public static class CashOut {

		/**
		 * Private methods to doing inquiry to DRS
		 * @throws ApiException 
		 **/
		public static JsonNode inquiry(String idToken) throws ApiException {

			// build json node for post
			ObjectNode post = Json.newObject();
			post.put("transaction.idToken", idToken);
			Logger.debug("Sending cashout inquiry "+post);
			JsonNode result = fetchUrls(URL_CASHOUT_INQUIRY, post.toString());
			Logger.debug("Inquiry Response : "+result);
			
			return result;
		}

		/**
		 * Method for invalidate transaction
		 * @throws ApiException 
		 */
		public static JsonNode validate(String idToken, String inquiryIdToken, String auth1) throws ApiException {
			// build json node for post
			ObjectNode post = Json.newObject();
			post.put("inquiry.idToken", inquiryIdToken);
			post.put("inquiry.transaction.idToken", idToken);
			post.put("auth1", auth1);

			Logger.debug("Sending cashout validate "+post);
			JsonNode result = fetchUrls(URL_CASHOUT_VALIDATE, post.toString());
			return result;
		}

		public static JsonNode collect(String transactionId, String inquiryId, String validationId, String authKey2, String receiveTrxId) throws ApiException {
			// build json node for post
			ObjectNode post = Json.newObject();
			post.put("inquiry.idToken", inquiryId);
			post.put("validationId", validationId);
			post.put("auth2", authKey2);
			if (receiveTrxId != null && !receiveTrxId.trim().equals(""))
			post.put("receiveTrxId", receiveTrxId);
			
			Logger.debug("Sending cashout collect "+post);
			JsonNode result = fetchUrls(URL_CASHOUT_COLLECT, post.toString());
			return result;
		}
	}

	public static JsonNode transactionDetail(String transactionId) {
		// build json node for post
		ObjectNode post = Json.newObject();
		post.put("agentKey", SessionHelper.getUserKey());
		post.put("transactionId", transactionId);
		post.put("auth1", "1234");
		
		try {
			JsonNode result = fetchUrls(URL_TRANSACTION_DETAIL + transactionId, post.toString());
			return result;
		} catch (Exception e) {
			Logger.error("ApiException : "+e.getMessage());
		}
		return Json.newObject();
	}
	
	public static Transaction getTransaction(String transactionCode) throws ApiException {
		Response response = fetchTransaction(transactionCode);
		if((response != null) && (response.getStatus() == 200)) {
			JsonNode responseJson = response.asJson();
			if(responseJson != null) {
				if(responseJson.path("status").asInt(-1) == 0) {
					return Json.fromJson(responseJson.path("transaction"), models.Transaction.class);
				}
				throw new ApiException(responseJson);
			}
		}
		throw new ApiException(response);
	}
	
	public static TransactionShowHelper getTransactionShow(String transactionCode) throws ApiException {
		Response response = fetchTransaction(transactionCode);
		if((response != null) && (response.getStatus() == 200)) {
			JsonNode responseJson = response.asJson();
			if(responseJson != null) {
				if(responseJson.path("status").asInt(-1) == 0) {
					TransactionShowHelper helper = new TransactionShowHelper();
					helper.setTransaction(Json.fromJson(responseJson.path("transaction"), Transaction.class));
					try {
						ObjectNode object = null;
				        try {
				        	object = (ObjectNode) responseJson.path("transactionlog");
				        	object.remove("trackingId");
				        } catch (ClassCastException e) {
				        	
				        }
						helper.setTransactionLog(Json.fromJson(responseJson.path("transactionlog"), TransactionShowHelper.TransactionLog.class));
					} catch (Exception e) {
//						e.printStackTrace();
					}
					return helper;
				}
				throw new ApiException(responseJson);
			}
		}
		throw new ApiException(response);
	}
	
	public static Response fetchTransaction(String transactionCode) throws ApiException {
		Promise<Response> promise = fetchUrl(URL_TRANSACTION_DETAIL + transactionCode).get();
		return promise.get();
	}
	
	public static class Customer {
		public static Response fetchCustomer(String customerCode) throws ApiException {
			Promise<Response> promise = fetchUrl(URL_CUSTOMER_DETAIL + customerCode).get();
			return promise.get();
		}
		
		public static models.Customer getCustomer(String customerCode) throws ApiException {
			Response response = fetchCustomer(customerCode);
			if((response != null) && (response.getStatus() == 200)) {
				JsonNode responseJson = response.asJson();
				if(responseJson != null) {
					if(responseJson.path("status").asInt(-1) == 0) {
						return Json.fromJson(responseJson.path("customer"), models.Customer.class);
					}
					throw new ApiException(responseJson);
				}
			}
			throw new ApiException(response);
		}
		
	}
	
	private static WSRequestHolder fetchUrl(String url) throws ApiException {
		User user = SessionHelper.getUser();
		AgentToken agentToken = AgentToken.fromUser(user);
		String requestId = generateRequestId();
		String signature;
		try {
			signature = EncryptionHelper.encrypt(agentToken.toString()+requestId, user.corporate.encryptionKey);
			return WS.url(url)
					.setHeader("agentKey", agentToken.toString())
					.setHeader("requestId", requestId)
					.setHeader("signature", signature);
		} catch (Exception e) {
			throw new ApiException("Invalid Request Authorization");
		}
	}
	
	private static JsonNode fetchUrls(String urls, String post) {
		try {
			Logger.debug("Request inquiry ["+URL_CASHIN_INQUIRY+"] "+post);

			//HTTP URL CONNECTION
            Logger.debug("--------------------------------------------------");
            Logger.debug("URL                    : "+URL_CASHIN_INQUIRY);
            Logger.debug("Connection Timeout     : "+TIMEOUT_API);
            Logger.debug("==================================================");
			URL url = new URL(urls);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(TIMEOUT_API);
			connection.setReadTimeout(TIMEOUT_API);
			connection.addRequestProperty("Content-Type", "application/json");
			
			User user = SessionHelper.getUser();
			AgentToken agentToken = AgentToken.fromUser(user);
			String requestId = generateRequestId();
			String signature;
			signature = EncryptionHelper.encrypt(agentToken.toString()+requestId, user.corporate.encryptionKey);
			connection.addRequestProperty("agentKey", agentToken.toString());
			connection.addRequestProperty("requestId", requestId);
			connection.addRequestProperty("signature", signature);
			
			OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(post.toString());
            wr.flush();
			String hasil = "";
			Logger.debug("Http Response Code     : "+connection.getResponseCode());
            if (connection.getResponseCode() == 200) {

                BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    hasil = hasil + line;
                }
                
                Logger.debug("Result                 : "+hasil);
    			JsonNode resultJson = Json.parse(hasil);
    			Logger.debug("--------------------------------------------------");
    			Logger.debug("Fund Transfer transaction Post result (json) "+resultJson);
    			connection.disconnect();
				return resultJson;
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	private static String fetchUrl(String urls, String post) {
		try {
			//HTTP URL CONNECTION
            Logger.debug("--------------------------------------------------");
            Logger.debug("URL                    : "+urls);
            Logger.debug("Connection Timeout     : "+TIMEOUT_API);
            Logger.debug("==================================================");
			URL url = new URL(urls);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(TIMEOUT_API);
			connection.setReadTimeout(TIMEOUT_API);
			connection.addRequestProperty("Content-Type", "application/json");
			
			User user = SessionHelper.getUser();
			AgentToken agentToken = AgentToken.fromUser(user);
			String requestId = generateRequestId();
			String signature;
			signature = EncryptionHelper.encrypt(agentToken.toString()+requestId, user.corporate.encryptionKey);
			connection.addRequestProperty("agentKey", agentToken.toString());
			connection.addRequestProperty("requestId", requestId);
			connection.addRequestProperty("signature", signature);
			
			OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(post.toString());
            wr.flush();
			String hasil = "";
			Logger.debug("Http Response Code     : "+connection.getResponseCode());
            if (connection.getResponseCode() == 200) {

                BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    hasil = hasil + line;
                }
                
                Logger.debug("Result                 : "+hasil);
    			Logger.debug("--------------------------------------------------");
    			Logger.debug("Fund Transfer transaction Post result "+hasil);
    			connection.disconnect();
				return hasil;
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void sendPlainEmail(InternetAddress from, InternetAddress to, String subject, String body) throws EmailException, UnsupportedEncodingException, AddressException {
		Email mail = new SimpleEmail();
		
		mail.setDebug(true);
		mail.setHostName(SMTP_HOST);
		mail.setSSL(SMTP_SSL);
		
		if ( SMTP_PORT != null && SMTP_PORT > 1 && SMTP_PORT < 65536 ) {
            mail.setSmtpPort( SMTP_PORT );
        }
        if (SMTP_USER.length() > 0) {
        	mail.setAuthenticator(new DefaultAuthenticator(SMTP_USER, SMTP_PASSWORD));
        }

        if (from == null) {
        	from = InternetAddress.parse(CONFIG_EMAIL.getString("from"))[0];
        }
        mail.setFrom(from.getAddress(), from.getPersonal());
        if(CONFIG_EMAIL.getBoolean("simulateSelf")) {
			to.setAddress(CONFIG_EMAIL.getString("from"));
        }
        mail.addTo(to.getAddress(), to.getPersonal());
        mail.setSubject(subject);
        mail.setMsg(body);
        
        mail.send();
	}

	// Reference: http://commons.apache.org/email/userguide.html
	public static void sendHtmlEmail(InternetAddress from, InternetAddress to, String subject, Html body) throws EmailException, UnsupportedEncodingException, AddressException {
		HtmlEmail mail = new HtmlEmail();
		
		mail.setDebug(true);
		mail.setHostName(SMTP_HOST);
		mail.setSSL(SMTP_SSL);
		
		if ( SMTP_PORT != null && SMTP_PORT > 1 && SMTP_PORT < 65536 ) {
            mail.setSmtpPort( SMTP_PORT );
        }
        if (SMTP_USER.length() > 0) {
        	mail.setAuthenticator(new DefaultAuthenticator(SMTP_USER, SMTP_PASSWORD));
        }

        if (from == null) {
        	from = InternetAddress.parse(CONFIG_EMAIL.getString("from"))[0];
        }
        
        mail.setFrom(from.getAddress(), from.getPersonal());
        if(CONFIG_EMAIL.getBoolean("simulateSelf")) {
			to.setAddress(CONFIG_EMAIL.getString("from"));
        }
        mail.addTo(to.getAddress(), to.getPersonal());
        mail.setSubject(subject);
        
        mail.setHtmlMsg(body.body());
        
        mail.send();
	}
	
	private static String generateRequestId() {
		return UUID.create();
	}
	
	public static class CheckTranslate {
		
		public static String doQuery(String codeVal, String method) throws ApiException {

			// build json node for post
			ObjectNode post = Json.newObject();
			post.put("codeVal", codeVal);

			Logger.debug("Sending query translate "+post);
			String result = fetchUrl(URL_TRANSLATE+"/"+method.toLowerCase(), post.toString());
			try {
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
	}
	
}
