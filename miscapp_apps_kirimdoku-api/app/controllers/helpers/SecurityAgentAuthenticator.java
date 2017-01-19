package controllers.helpers;

import org.codehaus.jackson.node.ObjectNode;

import com.avaje.ebean.Expr;

import controllers.tokens.AgentToken;
import play.Logger;
import play.libs.Json;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;

public class SecurityAgentAuthenticator extends Security.Authenticator {

	@Override
	public String getUsername(Context ctx) {
//		Logger.debug("Request from "+ctx.request().method()+ctx.request().getHeader("Content-Type")+ctx.request().body().asFormUrlEncoded());
		ctx.request().setUsername(null);
		String agentKey = null;
		String requestId = null;
		String signature = null;
		try {
			// Get parameters from somewhere between GET query or Request Body
			if(ctx.request().headers().containsKey(Definition.FIELD_AGENTKEY)) {
				agentKey = ctx.request().headers().get(Definition.FIELD_AGENTKEY)[0];
				requestId = ctx.request().headers().get(Definition.FIELD_REQUESTID)[0];
				signature = ctx.request().headers().get(Definition.FIELD_SIGNATURE)[0];
			} else if (ctx.request().queryString().containsKey(Definition.FIELD_AGENTKEY)) {
				agentKey = ctx.request().queryString().get(Definition.FIELD_AGENTKEY)[0];
				requestId = ctx.request().queryString().get(Definition.FIELD_REQUESTID)[0];
				signature = ctx.request().queryString().get(Definition.FIELD_SIGNATURE)[0];
			} else if ((ctx.request().body().asFormUrlEncoded() != null) && ctx.request().body().asFormUrlEncoded().containsKey(Definition.FIELD_AGENTKEY)) {
				agentKey = ctx.request().body().asFormUrlEncoded().get(Definition.FIELD_AGENTKEY)[0];
				requestId = ctx.request().body().asFormUrlEncoded().get(Definition.FIELD_REQUESTID)[0];
				signature = ctx.request().body().asFormUrlEncoded().get(Definition.FIELD_SIGNATURE)[0];
			} else if ((ctx.request().getHeader("Content-Type") != null) && (ctx.request().getHeader("Content-Type").startsWith("application/json"))) {
				agentKey = ctx.request().body().asJson().get(Definition.FIELD_AGENTKEY).asText();
				requestId = ctx.request().body().asJson().get(Definition.FIELD_REQUESTID).asText();
				signature = ctx.request().body().asJson().get(Definition.FIELD_SIGNATURE).asText();
			}
		} catch(NullPointerException e) {
			Logger.error("Access authorization failed, unable to retrieve any authorization key from headers, post, query or data");
		}
		
		Logger.debug("Request from Agent "+agentKey+" - "+requestId+" - "+signature);
		if((agentKey != null) && (requestId != null) && (signature != null)) {
			AgentToken agentToken = AgentToken.fromString(agentKey);
			if(agentToken != null) {
				Logger.debug("AgentToken "+agentToken.id);
				models.User user = models.User.find.fetch("corporate").where().add(Expr.eq("id", agentToken.id)).findUnique();
				if(user != null) {
					Logger.debug("Corporate "+user.corporate.code);
					Logger.debug("Corporate "+user.corporate.encryptionKey);
					try {
						String correctSignature = EncryptionHelper.encrypt(agentKey+requestId, user.corporate.encryptionKey);
						if(signature.equals(correctSignature)) {
							return agentKey.toString();
						}
						Logger.debug("Receive Signature : "+signature);
						Logger.error("Invalid signature, correct signature : "+correctSignature);
						ctx.args.put("user", user);
						ctx.request().setUsername(user.idToken());
					} catch (Exception e) {
						e.printStackTrace();
						Logger.error("Signature decode failed "+e.getMessage());
					}
				}
			}
		}
		
		return null;
	}

	@Override
	public Result onUnauthorized(Context ctx) {
		ObjectNode node = Json.newObject();
		node.put("status", Definition.STATUS_UNAUTHORIZED);
		node.put("message", "Unathorized access");
		return unauthorized(node);
	}

}
