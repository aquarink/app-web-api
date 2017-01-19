package controllers.actors;

import java.util.Date;

import models.AuditLog;
import models.UserRequest;
import play.Logger;
import play.libs.Akka;
import play.mvc.Http.Request;
import play.mvc.Result;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import controllers.helpers.Definition;



public class LoggerActor extends UntypedActor {

	private static ActorRef refInstance;
	
	public static ActorRef getRefInstance() {
		if (refInstance == null) {
			refInstance = Akka.system().actorOf(new Props(LoggerActor.class));
		}
		return refInstance;
	}
	
	
	@Override
	public void onReceive(Object message) throws Exception {
		//Thread.sleep(500);
		Logger.debug("LoggerActor onReceive with simulating delay "+message);
		
		if (message instanceof ErrorMessage) {
			ErrorMessage errMessage = (ErrorMessage) message;
			AuditLog log = new AuditLog();
			
			log.source = AuditLog.SOURCE_FRONTEND;
			log.createdTime = new Date();
			log.tag = "error";
			log.message = errMessage.toString();
			log.save();
		} else if (message instanceof UserRequestMessage) {
			UserRequestMessage msg = (UserRequestMessage) message;

			UserRequest userRequest = new UserRequest();
			userRequest.id = UserRequest.getSeq();
			userRequest.requestTime = msg.requestTime;
			userRequest.username = msg.username;
			userRequest.requestId = msg.requestId;
			userRequest.requestData = msg.request.body().toString();
			userRequest.resultData = msg.result.toString();
			
			userRequest.save();
		}
	}
	
	
	public static class ErrorMessage {
		// TODO error kind?
		private String value;
		
		public ErrorMessage(String v) {
			this.value = v;
		}
		
		@Override
		public String toString() {
			return value;
		}
	}
	
	public static class UserRequestMessage {
		protected final Request request;
		protected final Result result;
		protected final Date requestTime;
		protected final String username;
		protected String requestId;
		
		public UserRequestMessage(Request request, Result result) {
			this.request = request;
			this.result = result;
			this.requestTime = new Date();
			this.username = request.username();
			
			try {
				if (request.queryString().containsKey(Definition.FIELD_REQUESTID)) {
					requestId = request.queryString().get(Definition.FIELD_REQUESTID)[0];
				} else if ((request.body().asFormUrlEncoded() != null) && request.body().asFormUrlEncoded().containsKey(Definition.FIELD_REQUESTID)) {
					requestId = request.body().asFormUrlEncoded().get(Definition.FIELD_REQUESTID)[0];
				} else if ((request.getHeader("Content-Type") != null) && (request.getHeader("Content-Type").startsWith("application/json"))) {
					requestId = request.body().asJson().get(Definition.FIELD_REQUESTID).asText();
				}
			} catch(NullPointerException e) {
				
			}
		}
	}
}