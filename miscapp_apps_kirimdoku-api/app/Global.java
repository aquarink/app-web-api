import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.data.format.Formatters;
import play.data.format.Formatters.SimpleFormatter;
import play.mvc.Action;
import play.mvc.Http.Request;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;
import play.mvc.Results;
import controllers.helpers.ResponseHelper;

public class Global extends GlobalSettings {

	@Override
	public void onStart(Application app) {
		Logger.info("Application Starting...");

		registerDataBinders();

		if ("enabled".equals(play.Play.application().configuration().getString("evolutionplugin"))) {
			Logger.info("Application Bootstrapping data...");
			GlobalInitialData.insert(app);
			Logger.info("Application Bootstrapping complete");
		}
		
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.add(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		//long msTillMidnight = (c.getTimeInMillis()-now.getTime());
//		msTillMidnight = 60000;
		
//		Akka.system().scheduler().schedule(Duration.create(msTillMidnight+(3600000*7), TimeUnit.MILLISECONDS), Duration.create(1, TimeUnit.DAYS), GlobalActor.getRefInstance(), "reminder", Akka.system().dispatcher());
//		Akka.system().scheduler().schedule(Duration.Zero(), Duration.create(10, TimeUnit.SECONDS), SuspiciousActor.getRefInstance(), "check", Akka.system().dispatcher());
//      Akka.system().scheduler().schedule(Duration.create(msTillMidnight, TimeUnit.MILLISECONDS), Duration.create(1, TimeUnit.MINUTES), SettlementActor.getRefInstance(), "settlement", Akka.system().dispatcher());
	}

	private void registerDataBinders() {
		// Hack around for java.sql.Date binder which couldn't pass form
		// validations
		Formatters.register(java.sql.Date.class, new SimpleFormatter<java.sql.Date>() {

			@Override
			public java.sql.Date parse(String input, Locale locale) throws ParseException {
				Logger.info("Parsing date input "+input+" with locale "+locale.getCountry());
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd", locale);
				return new java.sql.Date(df.parse(input).getTime());
			}

			@Override
			public String print(java.sql.Date date, Locale locale) {
				return date.toString();
			}
		});
		Formatters.register(Calendar.class, new SimpleFormatter<Calendar>() {

			@Override
			public Calendar parse(String input, Locale locale) throws ParseException {
				Logger.info("Parsing calendar input "+input+" with locale "+locale.getCountry());
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
				Calendar c = Calendar.getInstance(locale);
				c.setTimeInMillis(df.parse(input).getTime());
				return c;
			}

			@Override
			public String print(Calendar calendar, Locale locale) {
				return calendar.toString();
			}
		});
	}

	@Override
	public void onStop(Application app) {
		Logger.info("Application shutdown...");
	}

	@Override
	public Result onError(RequestHeader header, Throwable e) {
		return Results.badRequest(ResponseHelper.createErrorResponse(99, e.getMessage(), null));
	}

	@Override
	public Result onBadRequest(RequestHeader header, String message) {
		return Results.badRequest(ResponseHelper.createErrorResponse(99, message, null));
	}

	@Override
	public Result onHandlerNotFound(RequestHeader header) {
		return Results.badRequest(ResponseHelper.createErrorResponse(98, "Not found", null));
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Action onRequest(Request request, Method actionMethod) {
		// Check to ensure all the mandatory key is called
		Logger.debug("ON REQUEST " + request.toString());
		return super.onRequest(request, actionMethod);
	}
}
