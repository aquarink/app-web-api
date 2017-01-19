package controllers;

import models.AuditLog;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.audit.admin_index;
import java.util.HashMap;
import java.util.Map;
import be.objectify.deadbolt.java.actions.And;
import be.objectify.deadbolt.java.actions.Restrictions;
import com.avaje.ebean.Page;
import controllers.helpers.SessionHelper;

@Security.Authenticated(SecuredMain.class)
@Restrictions({@And("admin")})
public class Audit extends Controller {
	
    public static Result admin_index(int page, String sortBy, String order, String filterUserId, String filterTag) {
    	Map map = new HashMap<String, String>();
    	if(filterUserId != null) map.put("user.id", filterUserId);
    	if(filterTag != null) map.put("tag", filterTag);
        Page<AuditLog> rows = AuditLog.findPaging(SessionHelper.getUser(), page, 50, sortBy, order, map);
        
        return ok(admin_index.render(rows, sortBy, order, filterUserId, filterTag));
    }
}
