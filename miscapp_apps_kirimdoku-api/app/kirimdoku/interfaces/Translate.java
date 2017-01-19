package kirimdoku.interfaces;

import static play.data.Form.form;
import org.codehaus.jackson.node.ObjectNode;
import kirimdoku.helpers.QueryTranslateHelper;
import kirimdoku.helpers.SecurityAgentMobileAuthenticator;
import controllers.LoggerAction;
import controllers.helpers.Definition;
import controllers.helpers.ResponseHelper;
import controllers.netelis.TranslateHelper;
import play.Logger;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;

@Security.Authenticated(SecurityAgentMobileAuthenticator.class)
@With(LoggerAction.class)
public class Translate extends Controller {
	
	public static Result doTranslate() {
		Logger.info("--------------------");
		Logger.info("::  DO TRANSLATE  ::");
		Logger.info("====================");
		response().setContentType("application/json; charset=utf-8");
		
		ObjectNode response = Json.newObject();
		Result result = null;
		try {
			Form<QueryTranslateHelper> helper = form(QueryTranslateHelper.class).bindFromRequest();
			Logger.info("Do Translate Request " + helper.data());
			if (helper.hasErrors()) {
				result = unauthorized(ResponseHelper.createResponse(
						Definition.STATUS_INVALIDPARAMS, "Invalid parameters",
						helper.errorsAsJson()));
				Logger.info("Invalid parameters : " + helper.errorsAsJson());
			} else {
				if (helper.get().method.equals("PINYIN")) {
					Logger.debug("Method   : "+helper.get().method);
					Logger.debug("Code Val : "+helper.get().codeVal);
					
					TranslateHelper translateHelper = new TranslateHelper();
					String translate = translateHelper.doTranslate(helper.get().codeVal, helper.get().method);
					
					response.put("status", Definition.STATUS_OK);
					response.put("message", "Translate info success");
					helper.get().getResult(translate);
					response.put("translate", Json.toJson(helper.get().resultTranslate));
				} else if (helper.get().method.equals("TCODE")) {
					Logger.debug("Method   : TCODE");
					Logger.debug("Code Val : "+helper.get().codeVal);
					
					TranslateHelper translateHelper = new TranslateHelper();
					String translate = translateHelper.doTranslate(helper.get().codeVal, "TCODE");
					
					response.put("status", Definition.STATUS_OK);
					response.put("message", "Translate info success");
					helper.get().getResult(translate);
					response.put("translate", Json.toJson(helper.get().resultTranslate.result));
				} else {
					response = ResponseHelper.createResponse(
							Definition.STATUS_UNKNOWN, "Invalid method");
				}
				Logger.debug("Do Translate Result : "+response.toString());
				result = ok(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = ok(ResponseHelper.createResponse(
					Definition.STATUS_UNKNOWN, e.getMessage()));
			Logger.info("Exception : " + e.getMessage());
		}

		return result;
	}

}
