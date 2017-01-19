package controllers;

import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Result;
import akka.actor.ActorRef;
import controllers.actors.LoggerActor;

/**
 * An action context caller, which will be called on dispatch any controller request
 * 
 * @author fauziassegaff
 *
 */
public class LoggerAction extends Action.Simple {

	private static ActorRef logger = LoggerActor.getRefInstance();

	@Override
	public Result call(Context ctx) throws Throwable {
		
		Result result = delegate.call(ctx);
		
		logger.tell(new LoggerActor.UserRequestMessage(ctx.request(), result));
		return result;
	}
}
