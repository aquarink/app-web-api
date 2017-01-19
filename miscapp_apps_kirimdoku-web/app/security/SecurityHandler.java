/*
 * Copyright 2012 Steve Chaloner
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package security;

import java.util.Date;
import java.util.Map;
import org.codehaus.jackson.JsonNode;
import controllers.routes;
import be.objectify.deadbolt.java.AbstractDeadboltHandler;
import be.objectify.deadbolt.java.DeadboltHandler;
import be.objectify.deadbolt.java.DynamicResourceHandler;
import be.objectify.deadbolt.core.models.Subject;
import models.Transaction;
import models.User;
import play.cache.Cache;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.Logger;

public class SecurityHandler extends AbstractDeadboltHandler {
	
	@Override
	public Result beforeAuthCheck(Http.Context context) {
		Logger.debug("beforeAuthCheck ");
		Date d = new Date();
		context.session().put("t", String.valueOf(d.getTime()));
		String userId = context.session().get("userId");
		if (userId != null) {
			int expireSecond = 60 * 5;
			long expireTime = d.getTime() + (expireSecond * 1000);
			String cacheKey = "login-user-" + userId;
			Logger.debug("Setting cache " + cacheKey + " = " + expireTime);
			Cache.set(cacheKey, expireTime, expireSecond);
		}

		// returning null means that everything is OK. Return a real result if
		// you want a redirect to a login page or
		// somewhere else
		return null;
	}

	public Subject getSubject(Http.Context context) {
		String userId = context.session().get("userId");
		try {
			return User.find.byId(Long.parseLong(userId));
		} catch (Exception e) {
			Logger.error("Exception ", e);
		}
		return null;
	}

	public DynamicResourceHandler getDynamicResourceHandler(Http.Context context) {
//		return new MyDynamicResourceHandler();
		return new AgentDynamicResourceHandler();
	}
	
	@Override
	public Result onAccessFailure(Http.Context context, String content) {
		Logger.warn("onAccessFailure of "+context.request().uri());
		Map<String, String[]> headers = context.request().headers();
		if((headers.get("X-Requested-With") != null) && headers.get("X-Requested-With")[0].startsWith("XML")) {
			return unauthorized();
		}
		context.flash().put("error", "Access restricted, Please login with the valid credentials");
		return redirect(routes.Login.login());
	}
	
	
	class AgentDynamicResourceHandler implements DynamicResourceHandler {

		@Override
		public boolean checkPermission(String name, DeadboltHandler handler, Context context) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isAllowed(String name, String meta, DeadboltHandler handler, Context context) {
			models.User subject = (User) handler.getSubject(context);
			if("userPredecessor".equals(name)) {
			} else if("userDescendant".equals(name)) {
			} else if("allowCreateUser".equals(name)) {
				if(subject.hasRole("admin")) {
					return true;
				}
				return false;
			} else if("sendTransaction".equals(name)) {
				if(subject.corporate.hasPermission(models.Corporate.PERMISSION_SEND)) {
					if((meta != null) && (!meta.isEmpty())) {
						JsonNode json = Json.parse(meta);
						if (json.has("senderCountry") && json.has("beneficiaryCountry")) {
							if (json.path("senderCountry").path("code").getTextValue().equals(subject.corporate.country.code)) {
								return true;
							}
						}
						return false;
					} else {
						Logger.debug("meta is empty or null");
						return true;
					}
				}
				return false;
			} else if("receiveTransaction".equals(name)) {
				if(subject.corporate.hasPermission(models.Corporate.PERMISSION_RECEIVE)) {
					if((meta != null) && (!meta.isEmpty())) {
						Transaction transaction = Transaction.findById(Long.parseLong(meta));
						if(transaction != null) {
							if((transaction.status == Transaction.STATUS_PENDING) && transaction.beneficiaryCountry.equals(subject.corporate.country)) {
								return true;
							}
						}
					} else {
						return true;
					}
				}
				return false;
			} else if("editTransaction".equals(name)) {
				Transaction transaction = Transaction.findById(Long.parseLong(meta));
				if(transaction != null) {
					if ( (transaction.status == Transaction.STATUS_PENDING) || (transaction.status == Transaction.STATUS_PENDING_REFUND) || (transaction.status == Transaction.STATUS_LOCKED)) {
						if(String.valueOf(transaction.agent.id).equals(subject.getIdentifier())) { // Owner
								return true;
						} else if(subject.hasRole("admin")) {
							return true;
						} else if(subject.hasRole("supervisor")) {
							// check other predecessor?
							if( (transaction.agent.supervisor != null) && (subject.id == transaction.agent.supervisor.id)) {
								return true;
							}
						}
					}
				}
				return false;
			} else if("changePassword".equals(name)) {
				if(subject.hasRole("admin")) {
					return true;
				} else if (meta != null) {
					User target = models.User.find.byId(Long.valueOf(meta));
					Logger.debug("Dua change password "+target.id+" - "+subject.id);
					if((target.id != null) && (target.id.equals(subject.id))) return true;
				}
				return false;
			} else if("transactionAdmin".equals(name)) {
			} else if("transactionCorporateOwner".equals(name)) {
			} else if("editUser".equals(name)) {
				if(subject.hasRole("admin") || subject.hasRole("admin_operasional")) {
					return true;
				} else if(meta != null) {
					User target = models.User.find.byId(Long.valueOf(meta));
					if((target.id != null) && (target.id.equals(subject.id))) return true;
					if((target.supervisor != null) && (target.supervisor.id.equals(subject.id))) return true;
					if((target.supervisor != null) && (target.supervisor.supervisor != null) && (target.supervisor.supervisor.id.equals(subject.id))) return true;
				}
				return false;
			}
			return false;
		}
		
	}
}
