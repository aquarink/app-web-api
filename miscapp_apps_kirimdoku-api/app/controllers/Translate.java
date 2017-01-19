package controllers;

import static play.data.Form.form;
import controllers.helpers.QueryTranslate;
import controllers.helpers.SecurityAgentAuthenticator;
import controllers.netelis.TranslateHelper;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;

@With(LoggerAction.class)
@Security.Authenticated(SecurityAgentAuthenticator.class)
public class Translate extends Controller {
	
	public static Result QueryPinyin() {
		response().setContentType("text/xml; charset=utf-8");
		Form<QueryTranslate> form = form(QueryTranslate.class).bindFromRequest();
		
		Logger.debug("Method   : PINYIN");
		Logger.debug("Code Val : "+form.get().getCodeVal());
		
		TranslateHelper translateHelper = new TranslateHelper();
		String result = translateHelper.doTranslate(form.get().getCodeVal(), "PINYIN");
		Logger.debug("Result Api : "+result);
		return ok(result);
	}
	
	public static Result QueryTC() {
		response().setContentType("text/xml; charset=utf-8");
		Form<QueryTranslate> form = form(QueryTranslate.class).bindFromRequest();
		Logger.debug("Method   : TCODE");
		Logger.debug("Code Val : "+form.get().getCodeVal());
		
		TranslateHelper translateHelper = new TranslateHelper();
		String result = translateHelper.doTranslate(form.get().getCodeVal(), "TCODE");
		Logger.debug("Result Api : "+result);
		return ok(result);
	}
	
}
