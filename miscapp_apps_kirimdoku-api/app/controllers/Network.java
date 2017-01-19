package controllers;

import org.codehaus.jackson.node.ObjectNode;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;
import controllers.helpers.Definition;
import controllers.helpers.ResponseHelper;
import controllers.helpers.SecurityAgentAuthenticator;

/**
 * Statistics class wrapper for some statistical echo/ping and some most network
 * utilities negotiations
 * 
 * @author zi
 */
@With(LoggerAction.class)
@Security.Authenticated(SecurityAgentAuthenticator.class)
public class Network extends Controller {

	/**
	 * ACK/PING/ECHO point
	 * 
	 * @return status result of ping from some check internal
	 */
	public static Result ping() {
		Logger.debug("Calling PING");

		final ObjectNode result = ResponseHelper.createResponse(Definition.STATUS_OK, "Ok");

//		logger.tell(new LoggerActor.UserRequestMessage(UserRequest.TYPE_NETWORK_PING, request(), result, form.get().user));

		return ok(result);
	}
	
	public static Result pings() {
		Logger.debug("Calling PING");

		final ObjectNode result = ResponseHelper.createResponse(Definition.STATUS_OK, "Ok");

//		logger.tell(new LoggerActor.UserRequestMessage(UserRequest.TYPE_NETWORK_PING, request(), result, form.get().user));

		return ok(result);
	}
	
	/**
	 * Ping Form which is subclass from BaseForm is used as the form request
	 * object creation from PING request, including the validation methods
	 * 
	 * @author zi
	 */
	public static class PingForm extends BaseForm {

		protected String validate() {
			return null;
		}
	}
}