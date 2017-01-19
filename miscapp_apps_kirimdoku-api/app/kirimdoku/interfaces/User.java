package kirimdoku.interfaces;

import static play.data.Form.form;
import kirimdoku.helpers.BalanceHelper;
import kirimdoku.helpers.SecurityAgentMobileAuthenticator;
import kirimdoku.helpers.SenderConfig;
import kirimdoku.util.Utility;
import models.Channel;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import com.avaje.ebean.Expr;
import controllers.LoggerAction;
import controllers.helpers.Definition;
import controllers.helpers.ResponseHelper;
import play.Logger;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;

@Security.Authenticated(SecurityAgentMobileAuthenticator.class)
@With(LoggerAction.class)
public class User extends Controller {

	public static Result getInformation() {
		Logger.info("--------------------------");
		Logger.info(":: GET USER INFORMATION :: ");
		Logger.info("==========================");
		response().setContentType("application/json");
		Result result = null;
		try {
			Form<BalanceHelper> helper = form(BalanceHelper.class).bindFromRequest();
			Logger.info("Get User Information Request "+helper.data());
			if (helper.hasErrors()) {
				result = unauthorized(ResponseHelper.createResponse(
						Definition.STATUS_INVALIDPARAMS, "Invalid parameters",
						helper.errorsAsJson()));
				Logger.info("Invalid parameters : " + helper.errorsAsJson());
			} else {
				ObjectNode response = Json.newObject();
				response.put(Definition.FIELD_RESPONSE_STATUS, Definition.STATUS_OK);
				response.put(Definition.FIELD_RESPONSE_MESSAGE, "Get user information success");
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.setDateFormat(Utility.datetimeFormatShow);
				ObjectNode user = objectMapper.valueToTree(helper.get().user);
				
				SenderConfig senderConfig = new SenderConfig();
				models.User userInfo = models.User.find.fetch("corporate").fetch("corporate.configuration").fetch("corporate.country").fetch("corporate.country.currency").where().eq("id", helper.get().user.id).findUnique();
				String[] allowChannels = userInfo.corporate.configuration.channelCode.split(";");
				senderConfig.allowChannels = Channel.find.where(Expr.in("code", allowChannels)).findList();
				senderConfig.allowReceiverCountries = models.Forex.getReceiveCountryListByForex(userInfo, userInfo.corporate.country);
				user.put("sessionId", userInfo.sessionId);
				user.put("locationLong", userInfo.locationLong);
				user.put("locationLat", userInfo.locationLat);
				ObjectNode senderConfigs = objectMapper.valueToTree(senderConfig);
				user.put("userPermission", senderConfigs);
				user.put("creditLimit", userInfo.creditLimit.setScale(6));
				user.put("creditAlertLimit", userInfo.creditAlertLimit.setScale(6));
				user.put("creditLastBalance", userInfo.creditLastBalance.setScale(6));
				response.put("user", user);
				Logger.info("Get User Information Result " + response.toString());
				result = ok(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = ok(ResponseHelper.createResponse(Definition.STATUS_UNKNOWN, e.getMessage()));
			Logger.info("Exception : " + e.getMessage());
		}
		Logger.info("--------------------");
		return result;
	}
	
	
	
	
}
