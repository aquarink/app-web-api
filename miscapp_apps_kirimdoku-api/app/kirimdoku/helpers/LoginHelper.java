package kirimdoku.helpers;

import java.util.Date;

import com.avaje.ebean.Ebean;

import controllers.helpers.EncryptionHelper;
import models.User;
import play.Logger;
import play.data.validation.Constraints;

public class LoginHelper {
	
	@Constraints.Required
	public String email;
	
	@Constraints.Required
	public String password;
	public User user;
	
	@Constraints.Required
	public String requestId;
	
	@Constraints.Required
	public String signature;
	
	public String locationLong;
	public String locationLat;

	public String validate() {
		if ((user = authenticate(email, password)) == null) {
			Logger.info("Invalid user or password");
			return "Invalid user or password";
		} else {
			if (!this.requestId.trim().equals("") && !this.signature.trim().equals("")) {
				try {
					String correctSignature = EncryptionHelper.encrypt(this.requestId.trim()+this.email.trim(), play.Play.application().configuration().getString("application.secret").substring(0, 16));
					Logger.info("Correct Signature : ["+correctSignature+"]");
					Logger.info("Formula : ["+this.requestId+this.email+"]");
					Logger.info("Key : ["+this.user.corporate.encryptionKey+"]");
					if (!correctSignature.trim().equals(this.signature)) {
						return "Invalid signature";
					}
				} catch (Exception e) {
					e.printStackTrace();
					return "Invalid signature";
				}
			} else {
				return "Invalid signature";
			}
		}
		return null;
	}
	
	protected models.User authenticate(String email, String password) {
		models.User userCheck = models.User.findByEmail(email);
		if (userCheck != null && !userCheck.isAdmin) {
			byte[] bs = userCheck.password.getBytes();
			String passSystem = EncryptionHelper.convertToHex(bs);
			Logger.info("Pass User : ["+password+"]");
			Logger.info("Pass System : ["+passSystem+"]");
			if (passSystem.equalsIgnoreCase(password)) {
				Ebean.createSqlUpdate("UPDATE users SET session_id = :uuid, last_login_time = now(), session_time = now(), last_session_id = :sessionId WHERE id = :id")
				.setParameter("uuid", java.util.UUID.randomUUID().toString())
				.setParameter("sessionId", userCheck.sessionId)
				.setParameter("id", userCheck.id).execute();
				
				userCheck.refresh();
				return userCheck;
			}
		}
		
		return null;
	}
	
}
