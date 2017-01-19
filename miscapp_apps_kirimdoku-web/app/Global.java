import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import job.SchedullerJob;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.data.format.Formatters;
import play.data.format.Formatters.SimpleFormatter;
import play.mvc.Action;
import play.mvc.Http.Request;

public class Global extends GlobalSettings
{
	public static final String test = "Uyeah test";
	public static String cronSchedule = "0 0 0/1 * * ?";
	private static SchedullerJob schedullerJob = null;

    @Override
    public void onStart(Application application)
    {
    	registerDataBinders();
    	
		if("enabled".equals(play.Play.application().configuration().getString("evolutionplugin"))) {
			Logger.info("Application Bootstrapping data...");
			GlobalInitialData.insert(application);
			Logger.info("Application Bootstrapping complete");
		}
		
		//Tambah di procedure onStart
        try {
            Logger.debug("Cron schedule : "+cronSchedule);
            Logger.debug("Run Scheduller Job");
            schedullerJob = new SchedullerJob();
            SchedullerJob.doJob(cronSchedule);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.debug("Failed running scheduller job");
        }
		
    }

	private void registerDataBinders() {
		// Hack around for java.sql.Date binder which couldn't pass form validations
		Formatters.register(Date.class, new SimpleFormatter<Date>() {

			@Override
			public Date parse(String input, Locale locale) throws ParseException {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				return new Date(df.parse(input).getTime());
			}

			@Override
			public String print(Date date, Locale locale) {
				return date.toString();
			}
    	  
    	});
		
		Formatters.register(BigDecimal.class, new SimpleFormatter<BigDecimal>() {

			@Override
			public BigDecimal parse(String input, Locale locale) throws ParseException {
				input = input.replace(",", "");
				NumberFormat formatter = NumberFormat.getNumberInstance(locale);
				Number n = formatter.parse(input);
				return BigDecimal.valueOf(n.doubleValue()).setScale(6);
			}

			@Override
			public String print(BigDecimal input, Locale locale) {
				return input.toString();
//				NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
//				DecimalFormatSymbols decimalFormatSymbols = ((DecimalFormat) numberFormat).getDecimalFormatSymbols();
//				decimalFormatSymbols.setCurrencySymbol("");
//				((DecimalFormat) numberFormat).setDecimalFormatSymbols(decimalFormatSymbols);
//				return numberFormat.format(input).trim();
			}
			
		});
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Action onRequest(Request request, Method method) {
		Logger.debug("ON REQUEST "+request.toString());
		return super.onRequest(request, method);
	}
	
    //Penambahan procedure baru
    @SuppressWarnings("static-access")
    @Override
    public void onStop(Application arg0) {
        if (schedullerJob != null){
            Logger.debug("Try stop scheduller.");
            schedullerJob.doStop();
        }
    }
	
}
