package controllers;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

import controllers.actors.SettlementActor;
import controllers.actors.SuspiciousActor;
import controllers.helpers.Definition;
import controllers.helpers.ResponseHelper;
import controllers.tokens.TransactionToken;


import play.*;
import play.libs.Json;
import play.libs.WS;
import play.libs.F.Function;
import play.mvc.*;


/**
 * Base application context wrapper and function for some general function and abstraction method 
 * 
 * @author fauziassegaff
 *
 */
public class Lab extends Controller {

	public static Result index() {
		return unauthorized();
	}

	public static Result hello() {
		return ok("Hello");
	}

	/**
	 * Class to be called / redirected when user access is restricted
	 */
	public static Result check() {
		ObjectNode result = Json.newObject();
		result.put("status", "0");
		result.put("message", "Check OK");
		return ok(result);
	}
	
	

	public static Result test1() {
		unauthorized();
		
		ObjectNode result = Json.newObject();
		result.put("status", "1");
		result.put("message", "OKe2 b");
		return ok(result);
	}

	public static Result test2() {
		return null;
//		SimulatorAgent agent = new SimulatorAgent();
//
//		return ok(agent.ping());
	}

	public static Result test3() {

		return async(WS
				.url("http://localhost:9000/lab/2")
				.get().map(new Function<WS.Response, Result>() {

					@Override
					public Result apply(WS.Response response) throws Throwable {
						JsonNode node = response.asJson();
						Logger.debug("Message are = "+node.get("message"));
						return ok("Feed ok "+response.getBody());
					}
				}));
	}
	
	
	public static Result test4() {
		ObjectNode post = Json.newObject();
		post.put("agentKey", "A0AAA0200001");
		post.put("requestId", "123456");
		post.put("wordsToken", "1234567");
		post.put("transactionId", "T0AAAMY020000001");
		post.put("auth1", "1234");
		return async(WS
				.url("http://localhost:9000/transaction/T0AAAMY020000001")
				.setHeader("Content-Type", "application/json")
				.post(Json.stringify(post))
				.map(new Function<WS.Response, Result>() {

					@Override
					public Result apply(WS.Response response) throws Throwable {
						JsonNode node = response.asJson();
						models.Transaction transaction = Json.fromJson(node.path("transaction"), models.Transaction.class);
						Logger.debug("Message are = "+node.get("message"));
						return ok(String.format("Test transaction %s %d %s %s %s", transaction, transaction.id, transaction.agent, transaction.agent.firstName, transaction.beneficiaryCity));
					}
				}));
	}
	
	

	// For testing purpose only
	public static Result settlement() {
		final ObjectNode result = ResponseHelper.createResponse(Definition.STATUS_OK, "Ok");
		
		SettlementActor.getRefInstance().tell("settlement");
		return ok(result);
	}
	
	public static Result suspiciousCheck(String idToken) {
		final ObjectNode result = ResponseHelper.createResponse(Definition.STATUS_OK, "Ok");
		
		SuspiciousActor.getRefInstance().tell(models.Transaction.findByToken(TransactionToken.fromString(idToken)));
		return ok(result);
	}
}
