package controllers.helpers;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

import models.User;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Http.Flash;
import play.mvc.Http.Session;

public class SessionHelper {

	public static void putUser(models.User user) {
		putSessionUser(getSession(), user);
	}
	
	public static void putSessionLogin(String sessionLogin) {
		try {
			getSession().put("sessionLogin", HashWithSHA1.SHA1(play.Play.application().configuration().getString("application.secret").substring(0, 16)+sessionLogin));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	private static void putSessionUser(Session session, models.User user) {
		session.put("userId", user.id.toString());
		session.put("corporateCode", user.corporate.code);
        session.put("email", user.email);
        session.put("userDisplayName", user.firstName);
        
        if(user.isAdmin) {
	        session.put("isAdmin", Boolean.toString(user.isAdmin));
        }
        
//        session.put("corporateLogo", user.corporate.getLogoUrl());
//        session.put("userPhoto", user.getPhotoUrl());
        /*
        String color1 = "#333";
        if ((user.corporate.color1 != null) && (!user.corporate.color1.isEmpty())) {
            color1 = user.corporate.color1;
        }
        session.put("color1", color1);
        String color2 = "#FFF";
        if ((user.corporate.color2 != null) && (!user.corporate.color2.isEmpty())) {
            color2 = user.corporate.color2;
        }
        session.put("color2", color2);
        */
	}
	
	public static Session getSession() {
		return Http.Context.current().session();
	}

	public static Long getUserId() {
		try {
			Session session = getSession();
			if (session.containsKey("userId")) {
				return Long.parseLong(session.get("userId"));
			} else if (session.containsKey("user_id")) {
				return Long.parseLong(session.get("user_id"));
			} else if (session.containsKey("userid")) {
				return Long.parseLong(session.get("userid"));
			}
			return null;
		} catch(NumberFormatException e) {
			return null;
		}
	}
	
	public static User getUser() {
		Long userId = getUserId();
		if (userId != null) {
//			models.User user = User.find.byId(userId);
//			user.corporate.refresh();
//			return user;
			return User.find.byId(userId);
		}
		return null;
	}

	public static String getUserKey() {
		User user = getUser();
		if (user != null) {
			return user.getIdToken();
		}
		return null;
	}

	public static Locale getLocale() {
		return Locale.getDefault();
	}

	public static boolean isLoggedIn() {
		return (getUserId() != null) && (getUserId() > 0);
	}

	public static boolean isAdmin() {
		User user = getUser();
		if (user != null) {
			return user.isAdmin;
		}
		return false;
	}

	public static JsonNode flashToJson(Flash flash) {
		ObjectNode node = Json.newObject();
		
		if(flash.containsKey("success")) {
			node.put("success", flash.get("success"));
		} else if(flash.containsKey("error")) {
			node.put("error", flash.get("error"));
		} else {
		}
		flash.clear();
		return node;
	}
	
	//Stevano Added 18 Jun 2015
	public static String getCountryUser(){
		Long userId = getUserId();
		if (userId != null){
			models.User user = User.find.byId(userId);
			String getCorpCode = controllers.Corporate.getCountryByUserCorporateCode(user.corporate.code);
			return getCorpCode;
		}
		return null;
	}
	//Stevano Added 22 Jun 2015
	public static String getCorporateCode(){
		Long userId = getUserId();
		if (userId != null){
			models.User user = User.find.byId(userId);
			return user.corporate.code;
		}
		return null;
	}
	//End Added
	
	public static void putLangId(String langId) {
		Session session = getSession();
		session.put("langId", langId);
	}
	
	public static void putToken(String token) {
		Session session = getSession();
		session.put("token", token);
	}
	
	public static void removeToken() {
		Session session = getSession();
		session.put("token", "");
	}
	
	public static String getToken() {
		try {
			Session session = getSession();
			if (session.containsKey("token")) {
				return session.get("token");
			}
			return null;
		} catch(NumberFormatException e) {
			return null;
		}
	}
	
	public static String getLangId() {
		try {
			Session session = getSession();
			if (session.containsKey("langId")) {
				return session.get("langId");
			}
			return null;
		} catch(NumberFormatException e) {
			return null;
		}
	}
	
}
