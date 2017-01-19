package controllers;

import static play.data.Form.form;
import models.forms.QueryTranslate;
import play.mvc.Controller;
import controllers.helpers.ApiHelper;
import controllers.helpers.ApiHelper.ApiException;
import play.data.Form;
import play.mvc.Result;

public class Translate extends Controller {
	
	public static Result getPinyin() {
		Form<QueryTranslate> form = form(QueryTranslate.class).bindFromRequest();
		
		String result = "";
		try {
			result = ApiHelper.CheckTranslate.doQuery(form.get().getCodeVal(), "PINYIN");
			System.out.println("Result : "+result);
		} catch (ApiException e) {
			e.printStackTrace();
		}
		
		return ok(result);
	}
	
	public static Result getTCode() {
		Form<QueryTranslate> form = form(QueryTranslate.class).bindFromRequest();
		
		String result = "";
		try {
			result = ApiHelper.CheckTranslate.doQuery(form.get().getCodeVal(), "TCODE");
			System.out.println("Result : "+result);
		} catch (ApiException e) {
			e.printStackTrace();
		}
		
		return ok(result);
	}
	
}
