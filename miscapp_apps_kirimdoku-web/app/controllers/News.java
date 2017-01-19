package controllers;

import com.avaje.ebean.Page;

import be.objectify.deadbolt.java.actions.Restrictions;
import be.objectify.deadbolt.java.actions.And;
import controllers.helpers.SessionHelper;
import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import views.html.news.list;
import views.html.news.create;
import views.html.news.edit;

@Security.Authenticated(SecuredMain.class)
public class News extends Controller {
	
	@Restrictions({@And("mainagent")})
    public static Result list(int page, String sortBy, String order) {
		models.User user = SessionHelper.getUser();
        Page<models.News> results = models.News.newsPage(user.corporate, page, 25, sortBy, order);
        return ok( list.render( results, sortBy, order ) );
    }

	@Restrictions({@And("mainagent")})
    public static Result create() {
        Form<models.News> form = form(models.News.class);
        return ok(create.render(form));
    }

	@Restrictions({@And("mainagent")})
    public static Result save() {
        Form<models.News> form = form(models.News.class).bindFromRequest();

        if (form.hasErrors()) {
            Logger.debug("News errors " + form.errorsAsJson());
            return badRequest(create.render(form));
        }
        form.get().save();
        flash("success", "News has been created");
        return redirect(routes.News.list(0, "id", "desc"));
    }

	@Restrictions({@And("mainagent")})
    public static Result update(Long id) {
        models.News news = models.News.find.byId(id);
        Form<models.News> form = form(models.News.class).bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(edit.render(form));
        }
        form.get().update(id);
        flash("success", "News has been updated");
        return redirect(routes.News.list(0, "id", "desc"));
    }

	@Restrictions({@And("mainagent")})
    public static Result edit(Long id) {
        models.News news = models.News.find.byId(id);
        Form<models.News> form = form(models.News.class).fill(news);
        return ok(edit.render(form));
    }

    @Restrictions({@And("mainagent")})
    public static Result status(Long id, String status){
        models.User sessionUser = SessionHelper.getUser();
        models.News news = models.News.find.byId(id);

        if(sessionUser.corporate.equals(news.corporate)) {
            if(status.equals("publish")){
                news.published = true;
            }else if(status.equals("unpublish")){
                news.published = false;
            }

            news.save();

            flash("success", "News has successfully "+status+"ed");
        }else{
            flash("error", "You are not allowed");
        }
        return redirect(routes.News.list(0, "id", "desc"));
    }
}

