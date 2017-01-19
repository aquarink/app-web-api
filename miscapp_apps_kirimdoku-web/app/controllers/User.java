package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import org.apache.commons.io.IOUtils;
import models.SecurityRole;
import play.Logger;
import play.Play;
import play.data.Form;
import play.mvc.*;
import static play.data.Form.*;
import views.html.user.changePassword;
import views.html.user.create;
import views.html.user.edit;
import views.html.user.list_users;
import views.html.user.show;
import views.html.user.summary;
import views.html.user.modal_demote;
import be.objectify.deadbolt.java.actions.Restrictions;
import be.objectify.deadbolt.java.actions.And;
import be.objectify.deadbolt.java.actions.SubjectPresent;
import controllers.helpers.AuditLogHelper;
import controllers.helpers.EncryptionHelper;
import controllers.helpers.SessionHelper;
import play.data.validation.Constraints;

@Security.Authenticated(SecuredMain.class)
@SubjectPresent
public class User extends Controller {

	@Restrictions({ @And("admin"), @And("mainagent"), @And("supervisor") })
	public static Result index(String corporateCode) {
		
		models.User sessionUser = SessionHelper.getUser();
		models.Corporate corporate = models.Corporate.find.byId(corporateCode);
		models.User user = sessionUser;
		models.SecurityRole role = null;
		
		if (corporate == null)
			return notFound("Corporate " + corporateCode + " not found");
		
		if (user.hasRole("admin")) {
			role = models.SecurityRole.find.byId("mainagent");
		} else {
			if (user.hasRole("mainagent")) {
				role = models.SecurityRole.find.byId("supervisor");
			} else if (user.hasRole("supervisor")) {
				role = models.SecurityRole.find.byId("operator");
			}
		}
		

		if (corporate.isAccessibleByUser(sessionUser) && user.isAccessibleByUser(sessionUser)) {
			String filter = "";
			if (request().queryString().containsKey("filter")) {
				filter = request().queryString().get("filter")[0];
			}
			return ok(views.html.user.index.render(corporate, user, role, filter));
		}
		
		return forbidden();
	}
	
	@Restrictions({ @And("admin"), @And("mainagent"), @And("supervisor") })
	public static Result list(String corporateCode, Long userId) {
		models.User sessionUser = SessionHelper.getUser();
		models.Corporate corporate = models.Corporate.find.byId(corporateCode);
		models.User user = models.User.find.fetch("supervisor").where().idEq(userId).findUnique();
		
		if (corporate.isAccessibleByUser(sessionUser) && user.isAccessibleByUser(sessionUser)) {
			String filter = null;
			if (request().queryString().containsKey("filter")) {
				filter = request().queryString().get("filter")[0];
			}
			return ok(list_users.render(corporate, user, models.User.page(corporate, user, null, 0, 100, "firstName", "asc", filter)));
		}
		
		return forbidden();
	}
	
	@Restrictions({ @And("finance"), @And("admin_operasional") })
	public static Result indexOperasional(String corporateCode) {
		models.User sessionUser = SessionHelper.getUser();
		models.Corporate corporate = models.Corporate.find.byId(corporateCode);
		models.User user = sessionUser;
		models.SecurityRole role = models.SecurityRole.find.byId("mainagent");
		
		if (corporate == null)
			return notFound("Corporate " + corporateCode + " not found");
		if (corporate.isAccessibleByUser(sessionUser) && user.isAccessibleByUser(sessionUser)) {
			String filter = "";
			if (request().queryString().containsKey("filter")) {
				filter = request().queryString().get("filter")[0];
			}
			return ok(views.html.user.indexOperasional.render(corporate, user, role, filter));
		}
		
		return forbidden();
	}
	
	@Restrictions({ @And("finance"), @And("admin_operasional") })
	public static Result listOperasional(String corporateCode) {
		models.User sessionUser = SessionHelper.getUser();
		models.Corporate corporate = models.Corporate.find.byId(corporateCode);
		SecurityRole role = new SecurityRole();
		role.role = "operator";
		String filter = null;
		if (request().queryString().containsKey("filter")) {
			filter = request().queryString().get("filter")[0];
		}
		return ok(list_users.render(corporate, sessionUser, models.User.pageSetLimit(corporate, null, role, 0, 100, "firstName", "asc", filter)));
	}
	
	@Restrictions({ @And("admin"), @And("mainagent"), @And("supervisor"), @And("finance") })
	public static Result newCreate(String corporateCode, String role, Long supervisorId) {
		models.Corporate corporate = models.Corporate.find.byId(corporateCode);
		SecurityRole roleObj = SecurityRole.find.byId(role);
		models.User user = new models.User();
		user.corporate = corporate;
		user.country = corporate.country;
		user.supervisor = models.User.find.byId(supervisorId);
		user.roles.add(roleObj);
		user.status = models.User.Status.WEAKPASSWORD;
		Form<models.User> userForm = form(models.User.class).fill(user);
		userForm.data().put("primaryRole", roleObj.role);
		return ok(create.render(corporate, roleObj, userForm));
	}

	@Restrictions({ @And("admin"), @And("mainagent"), @And("supervisor") })
	public static Result create(String corporateCode) {
		models.User sessionUser = SessionHelper.getUser();
		Form<models.User> userForm = form(models.User.class).bindFromRequest();

		if (userForm.hasErrors()) {
			return badRequest(userForm.errorsAsJson());
		}

		models.User user = userForm.get();
		Http.MultipartFormData body = request().body().asMultipartFormData();
		try {
			// Upload Process
			Http.MultipartFormData.FilePart picture = body.getFile("picture");

			if (picture != null) {
				String fileName = picture.getFilename();
				String contentType = picture.getContentType();
				File file = picture.getFile();
				String imgPath = Play.application().path().toString() + "/public/images/users/" + fileName;
				file.renameTo(new File(imgPath));
				user.photo = "users/" + fileName;
				Logger.debug(fileName);
				Logger.debug(contentType);
			}

			String roleValue = userForm.apply("primaryRole").value();
			if(sessionUser.hasRole("admin")) {
			} else if(sessionUser.hasRole("mainagent") && (! ((roleValue.equals("supervisor")) || (roleValue.equals("operator"))))) {
				return forbidden("Not allowed for role "+roleValue);
			} else if(sessionUser.hasRole("supervisor") && (!roleValue.equals("operator"))) {
				return forbidden("Supervisor allowed for role "+roleValue);
			}
			
			user.password = EncryptionHelper.encrypt(user.password);
			user.save();
			SecurityRole role = SecurityRole.find.byId(roleValue);
			if (role != null)
				user.addRole(role);
			flash("success", "User " + userForm.get().firstName + " " + userForm.get().lastName + " has been created");
		} catch (Exception e) {
			e.printStackTrace();
			flash("error", "Failed to create user, Please check you have inputted properly");
		}
		return redirect(routes.User.index(corporateCode));
	}

	@Restrictions({ @And("admin"), @And("finance"), @And("mainagent"), @And("supervisor"), @And("operator"), @And("admin_operasional")})
	public static Result edit(String corporateCode, Long id) {
		models.User sessionUser = SessionHelper.getUser();
		models.User user = models.User.find.fetch("supervisor").fetch("roles").where().idEq(id).findUnique();
		if(user.isAccessibleByUser(sessionUser)) {
			Form<models.User> userForm = form(models.User.class).fill(user);
			userForm.data().put("primaryRole", user.getRoles().get(0).getName());
			return ok(edit.render(user.corporate, user.roles.get(0), userForm));
		}
		return forbidden();
	}
	
	@Restrictions({ @And("admin"), @And("finance"), @And("mainagent"), @And("supervisor"), @And("operator"), @And("admin_operasional")})
	public static Result editOperasional(String corporateCode, Long id) {
		models.User sessionUser = SessionHelper.getUser();
		models.User user = models.User.find.fetch("supervisor").fetch("roles").where().idEq(id).findUnique();
		if(user.isAccessibleByUser(sessionUser)) {
			Form<models.User> userForm = form(models.User.class).fill(user);
			userForm.data().put("primaryRole", user.getRoles().get(0).getName());
			return ok(views.html.user.editOperasional.render(user.corporate, user.roles.get(0), userForm));
		}
		return forbidden();
	}
	
	@Restrictions({ @And("admin"), @And("finance"), @And("mainagent"), @And("supervisor"), @And("operator"), @And("admin_operasional") })
	public static Result update(String corporateCode, Long id) {
		models.User sessionUser = SessionHelper.getUser();
		models.User user = null;
		models.User realUser = models.User.find.fetch("corporate").where().eq("id", id).findUnique();
		Form<models.User> userForm = form(models.User.class).fill(realUser);
		userForm = userForm.bindFromRequest();
		if (userForm.hasErrors()) {
			//Skip validations, since it can comeout with partial only
			return badRequest(userForm.errors().values().toString());
		} else if(userForm.get().isAccessibleByUser(sessionUser)) {
			Http.MultipartFormData body = request().body().asMultipartFormData();
			user = userForm.get();
			try {
				// Upload Process
				Http.MultipartFormData.FilePart picture = body.getFile("picture");
	
				if ((picture != null) && (picture.getFilename() != null) && (!picture.getFilename().isEmpty())) {
					String fileName = picture.getFilename();
					String contentType = picture.getContentType();
					File file = picture.getFile();
					String imgPath = Play.application().path().toString() + "/public/images/users/" + fileName;
					file.renameTo(new File(imgPath));
					user.photo = "users/" + fileName;
					Logger.debug(fileName);
					Logger.debug(contentType);
				}
				user.corporate = realUser.corporate;
				user.isAdmin = realUser.isAdmin;
				user.roles = realUser.roles;
				user.update(id);
				user = models.User.find.fetch("supervisor").fetch("roles").where().eq("id", user.id).findUnique();
				flash("success", "User " + userForm.get().firstName + " " + userForm.get().lastName + " has been updated");
			} catch (Exception e) {
				e.printStackTrace();
				flash("error", e.getMessage());
			}
		} else {
			flash("error", "You are not allowed to edit the user");
		}
		
		
		if((user != null) && (user.equals(sessionUser))) {
			SessionHelper.putUser(user);
			return redirect(routes.User.show(user.corporate.code, user.id));
		} else if((user != null) && (user.supervisor != null)) {
			return redirect(routes.User.index(corporateCode));
		} else {
			return redirect(routes.User.index(corporateCode));
		}
	}
	
	@Restrictions({ @And("admin_operasional"), @And("finance") })
	public static Result updateOperasional(String corporateCode, Long id) {
		models.User sessionUser = SessionHelper.getUser();
		models.User user = null;
		models.User realUser = models.User.find.fetch("corporate").where().eq("id", id).findUnique();
		Form<models.User> userForm = form(models.User.class).fill(realUser);
		userForm = userForm.bindFromRequest();
		if (userForm.hasErrors()) {
			//Skip validations, since it can comeout with partial only
			return badRequest(userForm.errors().values().toString());
		} else if(userForm.get().isAccessibleByUser(sessionUser)) {
			Http.MultipartFormData body = request().body().asMultipartFormData();
			user = userForm.get();
			try {
				// Upload Process
				Http.MultipartFormData.FilePart picture = body.getFile("picture");
	
				if ((picture != null) && (picture.getFilename() != null) && (!picture.getFilename().isEmpty())) {
					String fileName = picture.getFilename();
					String contentType = picture.getContentType();
					File file = picture.getFile();
					String imgPath = Play.application().path().toString() + "/public/images/users/" + fileName;
					file.renameTo(new File(imgPath));
					user.photo = "users/" + fileName;
					Logger.debug(fileName);
					Logger.debug(contentType);
				}
				user.corporate = realUser.corporate;
				user.isAdmin = realUser.isAdmin;
				user.roles = realUser.roles;
				user.update(id);
				user = models.User.find.fetch("supervisor").fetch("roles").where().eq("id", user.id).findUnique();
				flash("success", "User " + userForm.get().firstName + " " + userForm.get().lastName + " has been updated");
			} catch (Exception e) {
				e.printStackTrace();
				flash("error", e.getMessage());
			}
		} else {
			flash("error", "You are not allowed to edit the user");
		}

		if((user != null) && (user.equals(sessionUser))) {
			SessionHelper.putUser(user);
			return redirect(routes.User.show(user.corporate.code, user.id));
		} else {
			return redirect(routes.User.indexOperasional(corporateCode));
		}
	}
	
	@Restrictions({ @And("admin_operasional") })
	public static Result updateLimit(String corporateCode, Long id) {
		models.User sessionUser = SessionHelper.getUser();
		models.User user = null;
		models.User realUser = models.User.find.fetch("corporate").where().eq("id", id).findUnique();
		Form<models.User> userForm = form(models.User.class).fill(realUser);
		userForm = userForm.bindFromRequest();
		if (userForm.hasErrors()) {
			//Skip validations, since it can comeout with partial only
			return badRequest(userForm.errors().values().toString());
		} else if(userForm.get().isAccessibleByUser(sessionUser)) {
			Http.MultipartFormData body = request().body().asMultipartFormData();
			user = userForm.get();
			try {
				// Upload Process
				Http.MultipartFormData.FilePart picture = body.getFile("picture");
	
				if ((picture != null) && (picture.getFilename() != null) && (!picture.getFilename().isEmpty())) {
					String fileName = picture.getFilename();
					String contentType = picture.getContentType();
					File file = picture.getFile();
					String imgPath = Play.application().path().toString() + "/public/images/users/" + fileName;
					file.renameTo(new File(imgPath));
					user.photo = "users/" + fileName;
					Logger.debug(fileName);
					Logger.debug(contentType);
				}
				user.corporate = realUser.corporate;
				user.isAdmin = realUser.isAdmin;
				user.roles = realUser.roles;
				user.update(id);
				user = models.User.find.fetch("supervisor").fetch("roles").where().eq("id", user.id).findUnique();
				flash("success", "User " + userForm.get().firstName + " " + userForm.get().lastName + " has been updated");
			} catch (Exception e) {
				e.printStackTrace();
				flash("error", e.getMessage());
			}
		} else {
			flash("error", "You are not allowed to edit the user");
		}
		
		
		if((user != null) && (user.equals(sessionUser))) {
			SessionHelper.putUser(user);
			return redirect(routes.User.show(user.corporate.code, user.id));
		} else if((user != null) && (user.supervisor != null)) {
			return redirect(routes.User.index(corporateCode));
		} else {
			return redirect(routes.User.index(corporateCode));
		}
	}
	
	@Restrictions({ @And("admin"), @And("mainagent"), @And("supervisor"), @And("finance") })
	public static Result updateStatus(String corporateCode, Long id, boolean toActive) {
		models.User sessionUser = SessionHelper.getUser();
		models.User user = models.User.find.byId(id);
		if(user.isAccessibleByUser(sessionUser)) {
			if(toActive) {
				user.status = models.User.Status.ACTIVE;
				user.update();
				flash("success", "User has successfully updated to active");
			} else {
				user.status = models.User.Status.INACTIVE;
				flash("success", "User has successfully updated to inactive");
				user.update();
			}
			String reason = "";
			if(request().queryString().containsKey("reason") && !request().queryString().get("reason")[0].isEmpty()) {
				reason = " with reason "+request().queryString().get("reason")[0];
			}
			AuditLogHelper.log("user", "Updating user status "+user.email+" to "+user.status+reason);
		}
		return ok(SessionHelper.flashToJson(flash()));
	}
	
	@Restrictions({ @And("admin"), @And("mainagent"), @And("supervisor")})
	public static Result updateRolePromote(String corporateCode, Long id) {
		models.User sessionUser = SessionHelper.getUser();
		models.User user = models.User.find.fetch("roles").fetch("supervisor").where().idEq(id).findUnique();
		SecurityRole newRole = SecurityRole.find.byId("supervisor");
		if((newRole != null) && user.isAccessibleByUser(sessionUser)) {
			if(newRole.role.equals("supervisor")) {
				user.roles.clear();
				user.addRole(newRole);
				user.supervisor = user.supervisor.supervisor;
				user.save();
				// hack around on when editor is supervisor redirect back to self
			}
			flash("success", "User has successfully update its role to "+newRole.description);
			String reason = "";
			if(request().body().asFormUrlEncoded().containsKey("reason") && !request().body().asFormUrlEncoded().get("reason")[0].isEmpty()) {
				reason = " with reason "+request().body().asFormUrlEncoded().get("reason")[0];
			}
			AuditLogHelper.log("user", "Updating user role "+user.email+" to "+newRole.role+reason);
		}
		return redirect(routes.User.index(corporateCode));
	}
	
	@Restrictions({ @And("admin"), @And("mainagent"), @And("supervisor")})
	public static Result updateRoleDemote(String corporateCode, Long id) {
		Form<DemoteForm> form = form(DemoteForm.class).bindFromRequest();
		if(form.hasErrors()) {
			Logger.error("Invalid DemoteForm "+form.errorsAsJson());
			flash("error", "Unable to proceed your request");
			return redirect(routes.User.index(corporateCode));
		}
		
		models.User sessionUser = SessionHelper.getUser();
		models.User user = models.User.find.fetch("roles").fetch("supervisor").where().idEq(id).findUnique();
		List<models.User> childrens = user.findChildrens();
		SecurityRole newRole = SecurityRole.find.byId("agent");
		if((newRole != null) && user.isAccessibleByUser(sessionUser)) {
			// if(true) return ok("Oke deh"+form.get().parentSupervisor.firstName);
			user.roles.clear();
			user.addRole(newRole);
			
			
			user.supervisor = form.get().parentSupervisor;
			user.save();

			for(models.User children : childrens) {
				if(children.id == user.id) continue;
				children.supervisor = form.get().agentSupervisor;
				children.save();
				Logger.debug("Set children sup "+children.firstName+" to "+form.get().agentSupervisor.firstName);
			}
			
			flash("success", "User has successfully update its role to "+newRole.description);
			String reason = "";
			if(request().body().asFormUrlEncoded().containsKey("reason") && !request().body().asFormUrlEncoded().get("reason")[0].isEmpty()) {
				reason = " with reason "+request().body().asFormUrlEncoded().get("reason")[0];
			}
			AuditLogHelper.log("user", "Updating user role "+user.email+" to "+newRole.role+reason);
		}
		return redirect(routes.User.index(corporateCode));
	}
	
	@Restrictions({ @And("admin"), @And("mainagent"), @And("supervisor")})
	public static Result updateRoleDemoteForm(String corporateCode, Long id) {
		models.User user = models.User.find.fetch("roles").fetch("supervisor").where().idEq(id).findUnique();
		Form<DemoteForm> form = form(DemoteForm.class);
		return ok(modal_demote.render(form, user));
	}

	@Restrictions({ @And("admin"), @And("mainagent"), @And("supervisor") })
	public static Result destroy(String corporateCode, Long id) {
		models.User sessionUser = SessionHelper.getUser();
		models.User user = models.User.find.byId(id);
		if(user.isAccessibleByUser(sessionUser)) {
			user.delete();
			AuditLogHelper.log("user", "Deleting user "+user.email, user);
			return ok();
		}
		return forbidden();
	}

	@Restrictions({ @And("admin"), @And("finance"), @And("mainagent"), @And("supervisor"), @And("operator"), @And("admin_operasional")})
	public static Result show(String corporateCode, Long id) {
		models.User sessionUser = SessionHelper.getUser();
		models.User user = models.User.find.fetch("country").where().idEq(id).findUnique();
		if(user.isAccessibleByUser(sessionUser)) {
			return ok(show.render(user));
		}
		return forbidden();
	}

	// User summayr, also used as My Profile
	public static Result summary(String userId) {
		models.User user = models.User.find.byId(Long.parseLong(userId));
		return ok(summary.render(user));
	}

	@Restrictions({ @And("admin"), @And("finance"), @And("mainagent"), @And("supervisor"),  @And("operator"), @And("admin_operasional") })
	public static Result changePassword(String corporateCode, Long id) {
		models.User sessionUser = SessionHelper.getUser();
		models.User user = models.User.find.where().idEq(id).findUnique();
		
		if(user.isAccessibleByUser(sessionUser)) {
			Form<ChangePassword> changePasswordForm = form(ChangePassword.class).fill(new ChangePassword(corporateCode, id));
			return ok(changePassword.render(changePasswordForm));
		}
		return forbidden();
	}

	@Restrictions({ @And("admin"), @And("finance"), @And("mainagent"), @And("supervisor"),  @And("operator"), @And("admin_operasional") })
	public static Result saveChangePassword(String corporateCode, Long id) {
		Form<ChangePassword> changePasswordForm = form(ChangePassword.class).bindFromRequest();

		models.User sessionUser = SessionHelper.getUser();
		models.User user = models.User.find.where().idEq(id).findUnique();
		if(user.isAccessibleByUser(sessionUser)) {
			if (changePasswordForm.hasErrors()) {
				Logger.debug("CustomerForm errors " + changePasswordForm.errorsAsJson());
				return badRequest(changePassword.render(changePasswordForm));
			}
			try {
				user.password = EncryptionHelper.encrypt(changePasswordForm.get().newPassword);
				user.status = models.User.Status.ACTIVE;
				user.save();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(user.equals(sessionUser)) {
				flash("success", "Your Password has been changed");
				return redirect(routes.User.show(corporateCode, id));
			} else {
				flash("success", "Password for user "+user.email+" has been changed");
				return redirect(routes.User.index(corporateCode));
			}
		}
		return forbidden();
	}
	
	
	public static Result photo(Long userId, String size) {
		models.User user = models.User.find.byId(userId);
//		String path = "http://"+request().host()+"/assets/images/user_profile.png";
//		
//		if( (user != null) && (user.photo != null) && (!user.photo.isEmpty())) {
//			path = "http://"+request().host()+"/assets/images/"+user.photo;
//		}
//		return redirect(path);
		
		if(user != null) {
			File fp = new java.io.File(Play.application().path().getAbsolutePath()+"/public/images/"+user.photo);
			if((fp != null) && (fp.canRead())) {
				response().setHeader("Cache-Control", "public");
//				response().setHeader("Content-Description", "File Transfer");
//				response().setHeader("Content-Disposition", "attachment; filename=download.xml");
//				response().setHeader("Content-Type",  "image/jpeg");
				response().setHeader("Content-Transfer-Encoding", "binary");
				try {
					InputStream is = new FileInputStream(fp);
					byte[] buffs = IOUtils.toByteArray(is);
					return ok(buffs);
				} catch (Exception e) {
				}
			}
		}
		String path = "http://"+request().host()+"/assets/images/user_profile.png";
		return redirect(path);
	}
	

	public static Result css(Long userId) {
		models.User user = models.User.find.fetch("corporate.configuration").where().eq("id", userId).findUnique();
		return ok(views.html.user.css.render(user)).as("text/css");
	}

	public static class ChangePassword {
		public String corporateCode;
		public Long id;
		public String currentPassword;
		public String newPassword;
		public String newPassword2;

		public ChangePassword() {
			
		}
		
		public ChangePassword(String corporateCode, Long id) {
			this.corporateCode = corporateCode;
			this.id = id;
		}
		
		public String validate() {
			models.User user = models.User.find.byId(id);
			models.User sessionUser = SessionHelper.getUser();
			if( (!user.id.equals(sessionUser.id)) && user.isAccessibleByUser(sessionUser)) {
				
			} else
				try {
					if (!user.password.equals(EncryptionHelper.encrypt(currentPassword))) {
						return "Invalid current Password";
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			if (!newPassword.equals(newPassword2)) {
				return "Your confirmation for new password did not match";
			}
			return null;
		}
	}
	
	public static class DemoteForm {
		public Long userId;
		
		public String reason;
		
		@Constraints.Required
		public models.User parentSupervisor;
		
		@Constraints.Required
		public models.User agentSupervisor;
		
		public String validate() {
			parentSupervisor = models.User.find.byId(parentSupervisor.id);
			if(parentSupervisor == null) {
				return "Parent supervisor is not valid";
			}
			agentSupervisor = models.User.find.byId(agentSupervisor.id);
			if(agentSupervisor == null) {
				return "Agent supervisor is not valid";
			}
			return null;
		}
	}
}
