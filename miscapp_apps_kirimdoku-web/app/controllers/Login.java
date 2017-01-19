package controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.persistence.ManyToOne;
import models.User;
import org.apache.commons.mail.EmailException;
import org.codehaus.jackson.node.ObjectNode;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import controllers.helpers.ApiHelper;
import controllers.helpers.AuditLogHelper;
import controllers.helpers.EncryptionHelper;
import controllers.helpers.HashWithSHA1;
import controllers.helpers.SessionHelper;
import play.Logger;
import play.api.templates.Html;
import play.cache.Cache;
import play.data.Form;
import play.data.validation.ValidationError;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import static play.data.Form.*;
import views.html.main.*;

public class Login extends Controller {

	public static Result setLang(String langId) {
		try {
			Logger.info("SET LANG : "+langId);
			langId = langId != null && !langId.trim().equals("") ? langId : "EN";
			if (langId.trim().length() == 2) {
			    SessionHelper.putLangId(langId);
			    langId = SessionHelper.getLangId();
			}
		} catch (Exception e) {
			Logger.info("FAILED changeLang : "+e.getMessage());
		}
		return redirect(routes.Login.login());
	}
	
	public static Result setLangAdmin(String langId) {
		try {
			Logger.info("SET LANG : "+langId);
			langId = langId != null && !langId.trim().equals("") ? langId : "EN";
			if (langId.trim().length() == 2) {
			    SessionHelper.putLangId(langId);
			    langId = SessionHelper.getLangId();
			}
		} catch (Exception e) {
			Logger.info("FAILED changeLang : "+e.getMessage());
		}
		return redirect(routes.Login.admin_login());
	}
	
	public static Result captcha(){
	    DefaultKaptcha captchaPro=new DefaultKaptcha();
	    captchaPro.setConfig(new Config(new Properties()));
	    String sessionLogin=captchaPro.createText();
	    SessionHelper.putSessionLogin(sessionLogin);
	    BufferedImage img=captchaPro.createImage(sessionLogin);
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    try{
	        ImageIO.write(img, "jpg", baos);
	        baos.flush();
	    }catch(IOException e){
	        Logger.debug(e.getMessage());
	    }
	    return ok(baos.toByteArray()).as("image/jpg");
	}
	
	/**
     * Login page.
     */
    public static Result admin_login() {
        return ok(
                admin_login.render(form(AdminLoginForm.class))
        );
    }


    /**
     * Handle login form submission.
     */
    public static Result admin_authenticate() {
        Form<AdminLoginForm> loginForm = form(AdminLoginForm.class).bindFromRequest();
        if(loginForm.hasErrors()) {
            return badRequest(admin_login.render(loginForm));
        } else {
            String email = loginForm.get().email;
            models.User user = models.User.findByEmail(email);
			SessionHelper.putUser(user);
            AuditLogHelper.log("admin-login", email+" has logged in at "+(new Date())+" with remote address "+request().remoteAddress(), null, user);
            return redirect(
                    routes.Corporate.admin_index(0, "code", "asc", "")
            );
        }
    }

    /**
     * Logout and clean the session.
     */
    public static Result admin_logout() {
		Cache.set("login-user-"+SessionHelper.getUserId(), null);
		String langId = SessionHelper.getLangId();
		session().clear();
		SessionHelper.putLangId(langId);
        flash("success", "You've been logged out");
        return redirect(routes.Login.admin_login());
    }
    
	/**
	 * Login page.
	 */
	public static Result login() {
		return ok(login.render(form(LoginForm.class)));
	}

	/**
	 * Handle login form submission.
	 */
	public static Result authenticate() {
		Logger.info(" .:: authenticate ::. ");
		try {
			Form<LoginForm> loginForm = form(LoginForm.class).bindFromRequest();
			if (loginForm.hasErrors()) {
				return badRequest(login.render(loginForm));
			} else {
				SessionHelper.putUser(loginForm.get().user);
				Logger.info(".:: Login Redirect ::.");
				return redirect(routes.Main.index());
			}
		} catch (Exception e) {
			flash("error", "");
			Logger.error("Exception occured " + e.getMessage(), e);
			return redirect(routes.Login.login());
		}
	}

	/**
	 * Logout and clean the session.
	 */
	public static Result logout() {
		Cache.set("login-user-"+SessionHelper.getUserId(), null);
	    String langId = SessionHelper.getLangId();
		session().clear();
		SessionHelper.putLangId(langId);
		flash("success", "You've been logged out");
		return redirect(routes.Login.login());
	}

	public static Result forgetPassword() {
		// TODO: SEND EMAIL PASSWORD HERE
		String email = request().body().asFormUrlEncoded().get("forget-email")[0];

		// Query username by this email
		models.User user = models.User.findByEmail(email);
		if (user != null) {
			// is it valid/allowed?
			try {
				String encKey = generateResetPasswordKey(user);
				String link = "https://"+request().host();
				link += "/reset-password";
				link += "/" + URLEncoder.encode(encKey, "utf-8");
				Html html = forget_password_email.render(user, link);
				ApiHelper.sendHtmlEmail(null, new InternetAddress(user.email, user.fullName()), "Change Password Confirmation", html);
				return ok("Confirmation for changing password has been sent to your email address ");
			} catch (EmailException e) {
				e.printStackTrace();
				return internalServerError(e.getMessage());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return internalServerError(e.getMessage());
			} catch (AddressException e) {
				e.printStackTrace();
				return internalServerError(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				return internalServerError("Unable to send email, please check with your administrator");
			}
		}
		return notFound();
	}

	public static Result resetPassword(String key) {
		try {
			ObjectNode node = Json.newObject();
			node.put("key", key);
			Form<ResetPasswordForm> form = form(ResetPasswordForm.class).bind(node);
			if (form.hasErrors()) { 
				flash("error", "Invalid authorization password reset key request");
				return redirect(routes.Login.login());
			}
			return ok(resetPassword.render(key, form.get().user, form));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return notFound();
		}
	}

	public static Result resetPasswordSubmit() {
		Form<ResetPasswordForm> form = form(ResetPasswordForm.class).bindFromRequest();
		if (form.hasErrors()) {
			for(Entry<String, List<ValidationError>> entry : form.errors().entrySet()) {
				for(ValidationError err : entry.getValue()) {
					flash("error", err.message());
				}
			}
			try {
				String key = URLEncoder.encode(request().body().asFormUrlEncoded().get("key")[0], "utf-8");
				return redirect(routes.Login.resetPassword(key));
			} catch (UnsupportedEncodingException e) {e.printStackTrace();}
		}
		models.User user = models.User.find.byId(form.get().user.id);
		Logger.debug("User = "+user.firstName);
		try {
			user.password = EncryptionHelper.encrypt(form.get().password);
			user.save();
		} catch (Exception e) {
			e.printStackTrace();
		}
		AuditLogHelper.log("resetpassword", "Just resetting his/her password", null, user);
		flash("success", "Password has been resetted, you may now login with your new password");
		if (user.isAdmin) {
			return redirect(routes.Login.admin_login());
		} else {
			return redirect(routes.Login.login());
		}
	}

	private static String generateResetPasswordKey(User user) throws Exception {
		Date d = new Date();
		String token = d.getTime() + "-" + user.id + "-" + user.email;
		return EncryptionHelper.encrypt(token);
	}

	private static StringTokenizer decodeResetPasswordKey(String string) throws Exception {
		return new StringTokenizer(EncryptionHelper.decrypt(string), "-");
	}

	// -- Authentication
	public static class LoginForm {
		public String email;
		public String password;
		public String recaptcha_challenge_field;
		public String recaptcha_response_field;
		public User user;
	
		public String validate() {
			Logger.info(".:: Login Validate ::.");
			if ((this.user = authenticate(email, password)) == null) {
				return "Invalid user or password";
			}
			
			Long expireTime;
			// Check on cache wether the user has already logged in before
			if ((expireTime = (Long) Cache.get("login-user-"+this.user.id)) != null) {
				Date d = new Date();
				long expire = expireTime - d.getTime();
				double expireMinute = Math.ceil((expire / 1000) / 60);
				if(expireMinute > 0) {
					return String.format("Your user are currently logged in, please logout the current session, or wait another %d minutes", (int) expireMinute);
				} else {
					return String.format("Your user are currently logged in, please wait in a minutes to continue", (int) expireMinute);
				}
			}
			return null;
		}
		
		protected models.User authenticate(String email, String password) {
			Logger.info(".:: Login Authenticate ::.");
			return models.User.authenticate(email, password);
		}
	
		
	}

	public static class AdminLoginForm extends LoginForm {
		@Override
		protected models.User authenticate(String email, String password) {
			return models.User.authenticateAdmin(email, password);
		}
		
		public String validate() {
			String err;
			err = super.validate();
			if(err != null) {
				return err;
			}
			
			try {
				if (!validateCaptcha()) {
					return "Your captcha input is invalid";
				}
			} catch (Exception e) {
				Logger.error("Captcha failed : " + e.getMessage());
				return e.getMessage();
			}
			return err;
		}
		
		protected boolean validateCaptcha() {
			try {
				Logger.debug("CAPTCHA >> "+recaptcha_challenge_field);
				String postEnc = HashWithSHA1.SHA1(play.Play.application().configuration().getString("application.secret").substring(0, 16)+recaptcha_challenge_field);
				Logger.debug("CAPTCHA >> "+postEnc);
				String getsEnc = SessionHelper.getSession().get("sessionLogin");
				Logger.debug("CAPTCHA << "+getsEnc);
				if(postEnc.equalsIgnoreCase(getsEnc) && getsEnc != null && !getsEnc.equals("")) {
					SessionHelper.getSession().remove("sessionLogin");
					return true;
				}
				Logger.warn("Captcha invalid");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			return false;
		}
	}

	public static class ResetPasswordForm {
		public String key;
		public String password;
		public String password2;
		
		@ManyToOne(targetEntity=User.class)
		public User user;

		public String validate() {
			String err;
			if ((err = validateKey()) != null) {
				return err;
			}
			
			if (password != null) {
				if (!password.equals(password2)) {
					return "Your confirmation for new password did not match";
				}
			}
			return null;
		}

		public String validateKey() {
			StringTokenizer token;
			try {
				
				Logger.debug("Key to be decoded "+key);
				token = decodeResetPasswordKey(key);
				Date d = new Date(Long.parseLong(token.nextToken()));
				Date currentD = new Date();
				long interval = d.getTime() - currentD.getTime();
				Logger.debug("interval " + interval);
				if (interval < (-86400 * 1000)) {
					return "Request key has expired";
				}
				long userId = Long.parseLong(token.nextToken());
				if ((userId > 0) && ((user = User.find.byId(userId)) != null)) {
				} else {
					return "Request key was invalid";
				}
//				String verificationCode = token.nextToken();
				//TODO reverify code and make sure its not allowed to be reusable
			} catch (Exception e) {
				e.printStackTrace();
				return e.getMessage();
			}
			return null;
		}
	}

}
