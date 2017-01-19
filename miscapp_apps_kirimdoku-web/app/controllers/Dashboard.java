package controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.News;
import models.Transaction;
import models.User;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import play.Logger;
import play.cache.Cache;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.dashboard.admin_index;
import views.html.dashboard.index;
import views.html.dashboard.statistics;
import be.objectify.deadbolt.java.actions.Restrictions;
import be.objectify.deadbolt.java.actions.And;
import com.avaje.ebean.SqlRow;
import controllers.helpers.SessionHelper;

@Security.Authenticated(SecuredMain.class)
@Restrictions({ @And("admin"),  @And("finance"), @And("mainagent"), @And("supervisor"), @And("operator") })
public class Dashboard extends Controller {


	@Security.Authenticated(SecuredAdmin.class)
	@Restrictions({@And("admin"), @And("finance")})
    public static Result admin_index() {

		if(Cache.get("test") == null) {
			Logger.debug("Creating cache...");
			Cache.set("test", "Testing cache "+new Date().getTime(), 10);
		} else {
			Logger.debug("Has cache "+Cache.get("test"));
		}
		
		
        return ok(admin_index.render(SessionHelper.getUser()));
    }
	
	@Restrictions({@And("operator"), @And("supervisor"), @And("mainagent")})
	public static Result index() {
		User user = SessionHelper.getUser();
		if(user.status == User.Status.WEAKPASSWORD) {
			flash("warning", "You are required to change your password, please <a href=\""+routes.User.changePassword(user.corporate.code, user.id)+"\">click here</a> to change your password");
		}
		return ok(index.render(user, News.getPublishedNews()));
	}

	public static Result statistics(String userId) {
		User user = User.find.byId(Long.parseLong(userId));
		String err = null;
		if(user.corporate.getStatistic().hasExceedCreditLimit()) {
			err = "Your credit left has reach alert limit, please contact your finance for settlement.";
		} else if(user.corporate.getStatistic().hasExceedCreditAlertLimit()) {
			err = "Your credit left has reach alert limit, please contact your finance for settlement.";
		}
		return ok(statistics.render(err, user, models.Transaction.getUserLatestTransaction(user)));
	}

    public static Result news(Long newsId) {
        models.News news = models.News.find.byId(newsId);
        return ok(news.getFullContent());
    }

	public static Result userPerformances(String userId) {
		User user = User.find.byId(Long.parseLong(userId));
		
		ObjectNode result = Json.newObject();

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		ArrayNode cashIn = Json.newObject().arrayNode();
		List<SqlRow> rows = Transaction.getUserPerformancesCashIn(user);
		for (SqlRow row : rows) {
			ObjectNode node = Json.newObject();
			try {
				node.put("x", df.parse(row.getString("date")).getTime());
				node.put("y", row.getLong("total"));
				cashIn.add(node);
			} catch (ParseException e) {
			}
		}

		ArrayNode cashOut = Json.newObject().arrayNode();
		rows = Transaction.getUserPerformancesCashOut(user);
		for (SqlRow row : rows) {
			ObjectNode node = Json.newObject();
			try {
				node.put("x", df.parse(row.getString("date")).getTime());
				node.put("y", row.getLong("total"));
				cashOut.add(node);
			} catch (ParseException e) {
			}
		}

		result.put("cashIn", cashIn);
		result.put("cashOut", cashOut);

		return ok(result);
	}

	public static Result userVolumes(String userId) {
		User user = User.find.byId(Long.parseLong(userId));
		
		ObjectNode result = Json.newObject();

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		ArrayNode cashIn = Json.newObject().arrayNode();
		List<SqlRow> rows = Transaction.getUserVolumesCashIn(user, user.country.currency);
		for (SqlRow row : rows) {
			ObjectNode node = Json.newObject();
			try {
				node.put("x", df.parse(row.getString("date")).getTime());
				node.put("y", row.getLong("total"));
				cashIn.add(node);
			} catch (ParseException e) {
			}
		}

		ArrayNode cashOut = Json.newObject().arrayNode();
		rows = Transaction.getUserVolumesCashOut(user, user.country.currency);
		for (SqlRow row : rows) {
			ObjectNode node = Json.newObject();
			try {
				node.put("x", df.parse(row.getString("date")).getTime());
				node.put("y", row.getLong("total"));
				cashOut.add(node);
			} catch (ParseException e) {
			}
		}

		result.put("cashIn", cashIn);
		result.put("cashOut", cashOut);

		return ok(result);
	}
	
	private static List<List<models.User.TopAgents>> zipForCarousel(List<models.User.TopAgents> datas, int perPageSize) {
		
		List<List<models.User.TopAgents>> pages = new ArrayList<List<models.User.TopAgents>>();
		
		List<models.User.TopAgents> curPage = new ArrayList<User.TopAgents>();
//		for (Iterator<models.User.TopAgents> i = datas.iterator(); i.hasNext(); ) {
//			 models.User.TopAgents curData = i.next();
		int counter = 0;
		if(datas != null) {
			for(models.User.TopAgents curData : datas) {
				 curPage.add(curData);
				 
				 if ((counter % perPageSize) == perPageSize-1) {
					 pages.add(curPage);
					 curPage = new ArrayList<User.TopAgents>();
				 }
				counter++;
			}
		}
		return pages;
	}
}
