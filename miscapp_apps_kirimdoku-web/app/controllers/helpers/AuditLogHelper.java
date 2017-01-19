package controllers.helpers;

import java.util.Date;

import models.AuditLog;
import models.User;
import play.Configuration;
import play.libs.Json;
import play.Logger;;

public class AuditLogHelper {

	private static final Configuration CONFIG_API = play.Play.application().configuration().getConfig("api");
	private static final Configuration CONFIG_EMAIL = play.Play.application().configuration().getConfig("email");
	private static final Configuration CONFIG_SMTP = play.Play.application().configuration().getConfig("smtp");


	public static AuditLog log(String tag, String message) {
		return log(tag, message, null);
	}
	
	public static AuditLog log(String tag, String message, Object data) {
		models.User user = null;
		String userId = play.mvc.Http.Context.current().session().get("userId");
		if (userId != null) {
			user = User.find.byId(Long.parseLong(userId));
		}
		return log(tag, message, data, user);
	}
	public static AuditLog log(String tag, String message, Object data, User user) {
		AuditLog log = new AuditLog();
		
		log.source = AuditLog.SOURCE_FRONTEND;
		log.createdTime = new Date();
		
		log.user = user;
		log.tag = tag;
		log.message = message;
		
		if (data != null) {
			try {
				log.data = Json.toJson(data).toString();
			} catch(Exception e) {}
		}
		
		Logger.debug("Log to be saved : "+log.tag+" - "+log.message);
		log.save();
		// Add additional to play logger
		Logger.debug(log.toString());
		return log;
	}
}
