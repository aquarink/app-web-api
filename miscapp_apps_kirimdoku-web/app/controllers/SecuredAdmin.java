package controllers;

import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;

public class SecuredAdmin extends Security.Authenticator {

    @Override
    public String getUsername(Context ctx) {
        return ctx.session().get("email");
    }

    @Override
    public Result onUnauthorized(Context ctx) {
		if((ctx.request().headers().get("X-Requested-With") != null) && ctx.request().headers().get("X-Requested-With")[0].equals("XMLHttpRequest")) {
			return unauthorized();
		} else {
	        return redirect(routes.Login.admin_login());
		}
    }

    // Access rights

//    public static boolean isMemberOf(Long project) {
//        return Project.isMember(
//                project,
//                Context.current().request().username()
//        );
//    }
//
//    public static boolean isOwnerOf(Long task) {
//        return Task.isOwner(
//                task,
//                Context.current().request().username()
//        );
//    }

}