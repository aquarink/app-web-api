package job;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import models.Corporate;
import models.CutOffTime;
import models.SettlementReport;
import models.QueryScheduller;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

import play.Logger;

public class SchedullerJob implements Job {
	
	protected static final String JOB_NAME = "BLITZKRIEG_JOB";
	protected static final String TRG_NAME = "BLITZKRIEG_TRG";
	protected static final String JOB_KEY_NAME = "BLITZKRIEG_JOB_KEY";
	protected static final String SCHEDULLER_GROUP = "BLITZKRIEG";
	
	private static Scheduler scheduler = null;
	
	public static void doJob(String cron) throws Exception {
		JobDetail job = JobBuilder.newJob(SchedullerJob.class)
				.withIdentity(JOB_NAME, SCHEDULLER_GROUP).build();
		
		JobKey jobKey = JobKey.jobKey(JOB_KEY_NAME, SCHEDULLER_GROUP);
		
		scheduler = new StdSchedulerFactory().getScheduler();
		if (scheduler.getJobKeys(GroupMatcher.jobGroupEquals(jobKey.getGroup())).size() > 0){
			scheduler.clear();
		}
		
		Trigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity(TRG_NAME, SCHEDULLER_GROUP)
				.withSchedule(
						CronScheduleBuilder.cronSchedule(cron))
				.build();
		
		scheduler.start();
		scheduler.scheduleJob(job, trigger);
	}
	
	public static void doStop() {
		try {
			if (scheduler != null){
				scheduler.clear();
				Logger.debug("Stop scheduller");
			}
		} catch (Exception e) {
			Logger.debug("Failed stop scheduller");
		}
	}
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Logger.debug("Scheduller running at "+sdf.format(new Date()));
		setSettlement();
		getSettlement();
	}
	
	private static void getSettlement() {
		
		List<SettlementReport> settlementReports = SettlementReport.findGenerateSettlement();
		
		if (settlementReports != null && settlementReports.size() > 0) {
			for (SettlementReport settlementReport : settlementReports) {
				Logger.debug("-------------------");
				Logger.debug("ID			: "+settlementReport.getId());
				Logger.debug("Report Date 	: "+settlementReport.getReportDate());
				Logger.debug("Corp Code		: "+settlementReport.getCorporate().code);
				Logger.debug("Corp Name		: "+settlementReport.getCorporate().name);
				Logger.debug("Filename		: "+settlementReport.getFilename());
				Logger.debug("Status		: "+settlementReport.getStatus());
				
				GenerateReport.doGenerate(settlementReport);
			}
		}
		settlementReports = null;
	}
	
	private static void setSettlement() {
		try{
			Date dateReport = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat gdf = new SimpleDateFormat("yyyy-MM-dd");
			
			CutOffTime cutOffTime = CutOffTime.findActiveStatus();
			Date reportDateStart = sdf.parse(gdf.format(dateReport) + " " + cutOffTime.getCutOff());
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(reportDateStart);
			calendar.add(Calendar.HOUR, -24);
			reportDateStart = calendar.getTime();
			Date reportDateEnd = sdf.parse(gdf.format(dateReport) + " " + cutOffTime.getCutOff());
			Logger.debug("Cek Time		: > "+sdf.format(reportDateStart)+" <= "+sdf.format(reportDateEnd));
			List<Corporate> corporateGenerate = QueryScheduller.findCorporateActive();
			if (corporateGenerate != null && corporateGenerate.size() > 0){
				for (Iterator<Corporate> iterator = corporateGenerate.iterator(); iterator.hasNext();) {
					Corporate corporate = (Corporate) iterator.next();
					SettlementReport.createGenerateSettlement(reportDateStart, reportDateEnd, cutOffTime.getCutOff(), corporate);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.debug("Failed set settlement scheduller");
		}
	}
}
