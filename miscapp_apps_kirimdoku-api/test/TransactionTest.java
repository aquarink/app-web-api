import helpers.UnitHelper;

import java.math.BigDecimal;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.UUID;

import models.Transaction;

import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;
import org.junit.*;
import org.junit.runner.Computer;

import controllers.actors.GlobalActor;
import controllers.helpers.EncryptionHelper;
import controllers.tokens.TransactionToken;

import akka.actor.ActorRef;
import akka.actor.Props;

import play.Logger;
import play.mvc.*;
import play.test.*;
import play.libs.Akka;
import play.libs.Json;
import play.libs.WS;
import play.libs.WS.Response;
import play.libs.WS.WSRequest;
import play.libs.WS.WSRequestHolder;
import play.libs.F.*;

import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

public class TransactionTest {
	public static final String API_BASE_URL = "http://localhost:9000";
	public static final String PARAM_AGENTKEY = "A40006";

	// scenarios: senderCustomerId, beneficiaryCustomerId
	protected String[][] scenariosList = {
			{ "MY", "MYR", "ID", "IDR", "100", "CID0000001", "CID0000002"},
			{ "MY", "MYR", "ID", "IDR", "150", "CID0000003", "CID0000002"},
			{ "MY", "MYR", "ID", "IDR", "1205", "CID0000003", "CID0000002"}, 
			{ "MY", "MYR", "ID", "IDR", "700", "CID0000003", "CID0000002"}, 
			{ "MY", "MYR", "ID", "IDR", "100", "CID0000002", "CID0000001"}, 
			{ "ID", "IDR", "MY", "MYR", "500000", "CID0000002", "CID0000001"},
			{ "ID", "IDR", "MY", "MYR", "944000", "CID0000001", "CID0000002"},
			{ "AU", "AUD", "ID", "IDR", "50", "CMY0000004", "CAU0000005"},
			{ "AU", "AUD", "ID", "IDR", "2580.70", "CMY0000004", "CAU0000005"},
			{ "AU", "AUD", "ID", "IDR", "2580.70", "CID0000003", "CAU0000005"},
			{ "ID", "IDR", "AU", "AUD", "880500", "CID0000003", "CMY0000004"},
			{ "ID", "IDR", "AU", "AUD", "1500000", "CID0000003", "CMY0000004"},
			{ "ID", "IDR", "AU", "AUD", "10800200", "CID0000003", "CMY0000004"},
		};
	
//	@Test
	public void simple() {
		running(fakeApplication(), new Runnable() {
			
			@Override
			public void run() {
				doCashIn(scenariosList[0]);
			}
		});
	}
	
	@Test
	public void scenarios() {
		running(fakeApplication(), new Runnable() {
			
			@Override
			public void run() {
				
				for (int i=1; i < scenariosList.length; i++) {
					doCashIn(scenariosList[i]);
				}
			}
		});	
	}

	@Test
	public void fillUp() {
		running(fakeApplication(), new Runnable() {
			
			@Override
			public void run() {
				
				for (int i=0; i < 10; i++) {
					doCashIn(scenariosList[i % scenariosList.length]);
				}
			}
		});	
	}
	
	@Test
	public void cashingOut() {
		running(fakeApplication(), new Runnable() {
			
			@Override
			public void run() {
				List<Transaction> rows = Transaction.find.all();
				for (ListIterator<Transaction> li = rows.listIterator(); li.hasNext();) {
					Transaction row = li.next();
					TransactionToken token = TransactionToken.fromTransaction(row);
					doCashOut(token.toString(), row);
				}
			}
		});
	}
	
	@Test
	public void fillUpPending() {
		running(fakeApplication(), new Runnable() {
			
			@Override
			public void run() {
				
				for (int i=0; i < 10; i++) {
					doCashIn(scenariosList[i % scenariosList.length]);
				}
			}
		});	
	}
	
	private JsonNode doCashIn(String[] params) {
		Random r = new Random();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		boolean feeIncluded = false;
		if (r.nextInt(2)==1) feeIncluded = true;
		
		Calendar c = Calendar.getInstance();
		c.set(2012, 1, 1);
		long startTime = c.getTimeInMillis()/1000;
		long endTime = Calendar.getInstance().getTimeInMillis()/1000;
		long randomTime = startTime + (r.nextInt((int) ((endTime-startTime))));
		
		Response response = fetchUrl(API_BASE_URL+"/cashin/inquiry")
		.setQueryParameter("channel.code", "02")
		.setQueryParameter("senderCountry.code", params[0])
		.setQueryParameter("senderCurrency.code", params[1])
		.setQueryParameter("beneficiaryCountry.code", params[2])
		.setQueryParameter("beneficiaryCurrency.code", params[3])
		.setQueryParameter("senderAmount", params[4])
		.post("").get();
		
		System.out.println("Response CashIn Inquiry Body = "+response.getBody());
		assertThat(response.getStatus()).isEqualTo(OK);
		
		JsonNode root;
		assertThat(root = response.asJson()).isNotNull();
		
		Response response2 = fetchUrl(API_BASE_URL+"/cashin/remit")
		.setQueryParameter("channel.code", "02")
		.setQueryParameter("senderCountry.code", params[0])
		.setQueryParameter("senderCurrency.code", params[1])
		.setQueryParameter("beneficiaryCountry.code", params[2])
		.setQueryParameter("beneficiaryCurrency.code", params[3])
		.setQueryParameter("senderAmount", params[4])
		.setQueryParameter("sender.idToken", params[5])
		.setQueryParameter("beneficiary.idToken", params[6])
//		.setQueryParameter("beneficiaryCityId", "1")
//		.setQueryParameter("inquiryId", ""+root.findPath("uu)
		.setQueryParameter("inquiry.idToken", String.valueOf(root.path("inquiry").path("idToken").getTextValue()))
		.setQueryParameter("cashInTime", df.format(new Date( randomTime * 1000 )))
		.setQueryParameter("auth1", "4321")
		.post("").get();

		System.out.println("cashInTime = "+ df.format(new Date( randomTime * 1000 )));
		
		System.out.println("Response CashIn Remit Body = "+response2.getBody());
		assertThat(response2.getStatus()).isEqualTo(OK);
		
		JsonNode root2;
		assertThat(root2 = response.asJson()).isNotNull();
		return root2;
	}
	
	private JsonNode doCashOut(String transactionId, Transaction transaction) {
		Random r = new Random();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		// build json node for post
		ObjectNode post = Json.newObject();
		post.put("agentKey", PARAM_AGENTKEY);
		post.put("transaction.idToken", transactionId);

		Response response = fetchUrl(API_BASE_URL+"/cashout/inquiry")
				.setHeader("Content-Type", "application/json")
				.post(Json.stringify(post))
				.map(new Function<WS.Response, Response>() {
		
					@Override
					public Response apply(WS.Response response) throws Throwable {
						System.out.println("CashOut Inquiry Response are = " + response.getBody());
		
						return response;
					}
				}).get();
		
		assertThat(response.getStatus()).isEqualTo(OK);
		
		JsonNode root;
		assertThat(root = response.asJson()).isNotNull();
		
		String inquiryId = root.path("inquiry").path("idToken").getTextValue();
		
		// Validate
		post.put("inquiry.idToken", inquiryId);
		post.put("auth1", "4321");
		Response response2 = fetchUrl(API_BASE_URL+"/cashout/validate")
				.setHeader("Content-Type", "application/json")
				.post(Json.stringify(post))
				.map(new Function<WS.Response, Response>() {
		
					@Override
					public Response apply(WS.Response response) throws Throwable {
						System.out.println("CashOut Validate Response are = " + response.getBody());
		
						return response;
					}
				}).get();
		
//		assertThat(response2.getStatus()).isEqualTo(OK);
		
		JsonNode root2;
		assertThat(root2 = response2.asJson()).isNotNull();
		if (root2.path("status").getIntValue() != 0) return null;
		
		String validationId = root2.path("validation").path("validationId").getTextValue();
		
		
		// Collect
		post.put("validationId", validationId);
		post.put("auth2", "4321");
		long randomTime = (transaction.cashInTime.getTimeInMillis()/1000) + (r.nextInt(86400*7));
		post.put("cashOutTime", df.format(new Date(randomTime * 1000))); // Random cashOutTime
		
		Response response3 = fetchUrl(API_BASE_URL+"/cashout/collect")
				.setHeader("Content-Type", "application/json")
				.post(Json.stringify(post))
				.map(new Function<WS.Response, Response>() {
		
					@Override
					public Response apply(WS.Response response) throws Throwable {
						System.out.println("CashOut Collect Response are = " + response.getBody());
		
						return response;
					}
				}).get();
		
		assertThat(response3.getStatus()).isEqualTo(OK);
		
		JsonNode root3;
		assertThat(root3 = response3.asJson()).isNotNull();
		
		return root3;
	}
	
	private static WSRequestHolder fetchUrl(String url) {
		String requestId = UnitHelper.generateRequestId().toString();
		String signature = null;
		try {
			signature = EncryptionHelper.encrypt(PARAM_AGENTKEY+requestId, "SIMULATORDEMOKEY");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return WS.url(url)
				.setHeader("agentKey", PARAM_AGENTKEY)
				.setHeader("requestId", requestId)
				.setHeader("signature", signature);
	}
}
