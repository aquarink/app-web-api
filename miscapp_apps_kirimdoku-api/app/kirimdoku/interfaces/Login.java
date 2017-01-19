package kirimdoku.interfaces;

import static play.data.Form.form;
import kirimdoku.helpers.LoginHelper;
import kirimdoku.helpers.SenderConfig;
import kirimdoku.util.Utility;
import models.Channel;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import com.avaje.ebean.Expr;
import controllers.helpers.Definition;
import controllers.helpers.ResponseHelper;
import play.Logger;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class Login extends Controller {

	public static Result index() {
		Logger.info("-----------");
		Logger.info(":: LOGIN :: ");
		Logger.info("===========");
		response().setContentType("application/json");
		Result result = null;
		try {
			Form<LoginHelper> helper = form(LoginHelper.class).bindFromRequest();
			Logger.info("Login Request " + helper.data());
			if (helper.hasErrors()) {
				result = unauthorized(ResponseHelper.createResponse(
						Definition.STATUS_INVALIDPARAMS, "Invalid parameters",
						helper.errorsAsJson()));
				Logger.info("Invalid parameters : " + helper.errorsAsJson());
			} else {
				ObjectNode response = Json.newObject();
				response.put(Definition.FIELD_RESPONSE_STATUS, Definition.STATUS_OK);
				response.put(Definition.FIELD_RESPONSE_MESSAGE, "STATUS_OK");
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.setDateFormat(Utility.datetimeFormatShow);
				ObjectNode user = objectMapper.valueToTree(helper.get().user);
				
				SenderConfig senderConfig = new SenderConfig();
				String[] allowChannels = helper.get().user.corporate.configuration.channelCode.split(";");
				senderConfig.allowChannels = Channel.find.where(Expr.in("code", allowChannels)).findList();
				senderConfig.allowReceiverCountries = models.Forex.getReceiveCountryListByForex(helper.get().user, helper.get().user.corporate.country);
				user.put("sessionId", helper.get().user.sessionId);
				user.put("locationLong", helper.get().user.locationLong);
				user.put("locationLat", helper.get().user.locationLat);
				ObjectNode senderConfigs = objectMapper.valueToTree(senderConfig);
				user.put("userPermission", senderConfigs);
				user.put("creditLimit", helper.get().user.creditLimit.setScale(6));
				user.put("creditAlertLimit", helper.get().user.creditAlertLimit.setScale(6));
				user.put("creditLastBalance", helper.get().user.creditLastBalance.setScale(6));
				response.put("user", user);
				Logger.info("Login Result " + response.toString());
				result = ok(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = ok(ResponseHelper.createResponse(Definition.STATUS_UNKNOWN, e.getMessage()));
			Logger.info("Exception : " + e.getMessage());
		}
		Logger.info("-----------");
		return result;
	}

}
