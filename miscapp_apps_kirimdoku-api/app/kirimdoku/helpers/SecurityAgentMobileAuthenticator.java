package kirimdoku.helpers;

import java.util.Calendar;
import java.util.Date;

import models.User;

import org.codehaus.jackson.node.ObjectNode;

import com.avaje.ebean.TxType;
import com.avaje.ebean.annotation.Transactional;

import controllers.helpers.Definition;
import controllers.helpers.EncryptionHelper;
import controllers.tokens.AgentToken;
import play.Logger;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.Http.Context;

public class SecurityAgentMobileAuthenticator extends Security.Authenticator {
	
	private ObjectNode response = Json.newObject();
	
	@Override
	@Transactional(type=TxType.NOT_SUPPORTED)
	public String getUsername(Context ctx) {
		Logger.info("--------------------------------");
		Logger.info(":: AGENT MOBILE AUTHENTICATOR :: ");
		Logger.info("================================");
		ctx.request().setUsername(null);
		String agentKey = null;
		String requestId = null;
		String signature = null;
		String sessionId = null;
		try {
			if ((ctx.request().getHeader("Content-Type") != null) && (ctx.request().getHeader("Content-Type").startsWith("application/json"))) {
				agentKey = ctx.request().body().asJson().get(Definition.FIELD_AGENTKEY).asText().trim();
				requestId = ctx.request().body().asJson().get(Definition.FIELD_REQUESTID).asText().trim();
				signature = ctx.request().body().asJson().get(Definition.FIELD_SIGNATURE).asText().trim();
				sessionId = ctx.request().body().asJson().get(Definition.FIELD_SESSIONID).asText().trim();
			}
			if (agentKey.length() != 6) {
				Logger.error("Invalid agentKey length");
				response.put("status", Definition.STATUS_UNAUTHORIZED);
				response.put("message", "Invalid agentKey length");
				return null;
			}
		} catch(NullPointerException e) {
			Logger.info("Access authorization failed, unable to retrieve any authorization post data");
			response.put("status", Definition.STATUS_UNAUTHORIZED);
			response.put("message", "Access authorization failed, unable to retrieve any authorization post data");
		}
		
		Logger.info("agentKey ["+agentKey+"]");
		Logger.info("requestId ["+requestId+"]");
		Logger.info("signature ["+signature+"]");
		if((agentKey != null) && (requestId != null) && (signature != null)) {
			AgentToken agentToken = AgentToken.fromString(agentKey);
			if(agentToken != null) {
				models.User user = models.User.find.byId(agentToken.id);
				if(user != null) {
					if (sessionId != null && sessionId.equals(user.sessionId) && user.sessionTime != null) {
						Logger.info("Corporate ["+user.corporate.code+"]");
						try {
							Logger.info("Formula signature ["+agentKey+requestId+sessionId+"]");
							String correctSignature = EncryptionHelper.encrypt(agentKey+requestId+sessionId, play.Play.application().configuration().getString("application.secret").substring(0, 16));
							if(signature.equals(correctSignature)) {
								Date lastSession = user.sessionTime;
								Logger.info("Last Session Time "+lastSession);
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(new Date());
								calendar.add(Calendar.MINUTE, -30);
								if (calendar.getTime().compareTo(lastSession) < 0 ) {
									User.updateSessionTime(user.id);
									return agentKey.toString();
								} else {
									Logger.info("Session ["+sessionId+"] expired");
									response.put("status", Definition.STATUS_SESSIONEXPIRED);
									response.put("message", "Session expired");
								}
							} else {
								Logger.info("Invalid signature, correct signature : "+correctSignature);
								ctx.args.put("user", user);
								ctx.request().setUsername(user.idToken());
								response.put("status", Definition.STATUS_UNAUTHORIZED);
								response.put("message", "Invalid signature");
							}
						} catch (Exception e) {
							e.printStackTrace();
							Logger.info("Signature decode failed "+e.getMessage());
							response.put("status", Definition.STATUS_UNAUTHORIZED);
							response.put("message", "Invalid signature");
						}
					} else if (sessionId != null && sessionId.equals(user.lastSessionId)) {
						Logger.info("Session expired : "+sessionId);
						response.put("status", Definition.STATUS_SESSIONEXPIRED);
						response.put("message", "Session expired");
					} else {
						Logger.info("Invalid Session : "+sessionId);
						response.put("status", Definition.STATUS_UNAUTHORIZED);
						response.put("message", "Invalid session");
					}
				}
			}
		}
		
		return null;
	}

	@Override
	public Result onUnauthorized(Context ctx) {
		Logger.info("Unauthorized result "+response.toString());
		return unauthorized(response);
	}
	
}
