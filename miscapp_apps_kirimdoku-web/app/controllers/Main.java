package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import controllers.helpers.SessionHelper;

public class Main extends Controller {

	public static Result admin_index() {
		if (SessionHelper.isLoggedIn() && SessionHelper.isAdmin()) {
			return redirect(routes.Dashboard.admin_index());
		} else {
			return redirect(routes.Login.admin_login());
		}
	}
	
	public static Result index() {
		if (SessionHelper.isLoggedIn()) {
			return redirect(routes.Dashboard.index());
		} else {
			return redirect(routes.Login.login());
		}
	}
	
}
