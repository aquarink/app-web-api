package controllers;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import job.GenerateReport;
import models.Corporate;
import models.SettlementReportRequest;
import be.objectify.deadbolt.java.actions.And;
import be.objectify.deadbolt.java.actions.Restrictions;
import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import views.html.tools.settlementreport;

@Security.Authenticated(SecuredMain.class)
@Restrictions({ @And("finance")})
public class SettlementReport extends Controller {
	
	private static Form<SettlementReportRequest> settlementReportRequest;
	private static final String[] fieldData = {"reportDate","corporate_code","corporate.name","filename"};
	public static final String PATH_REPORT = play.Configuration.root().getString("settlement.report.path.download");
	public static final Integer MAX_ROW_PAGE = 20;
	
	public static Result list() {
		settlementReportRequest = form(SettlementReportRequest.class).bindFromRequest();
		
		Logger.debug("Corporate : "+settlementReportRequest.get().getCorporate());
		Logger.debug("Report Date Start : "+settlementReportRequest.get().getReportDateStart());
		Logger.debug("Report Date End :"+settlementReportRequest.get().getReportDateEnd());
		Logger.debug("Sort By : "+settlementReportRequest.get().getSortBy());
		Logger.debug("Order : "+settlementReportRequest.get().getOrder());
		if (settlementReportRequest.get().getPage() < 0){
			settlementReportRequest.get().setPage(0);
		}
		if (settlementReportRequest.get().getSortBy() != null && !settlementReportRequest.get().getSortBy().trim().equals("")){
			try {
				int idxField = new Integer(settlementReportRequest.get().getSortBy());
				settlementReportRequest.get().setSortByField(fieldData[idxField]+" "+settlementReportRequest.get().getOrder());
				Logger.debug("Sort By Field : "+settlementReportRequest.get().getSortByField());
			} catch (Exception e) {
				e.printStackTrace();
				Logger.debug("Failed get sort field");
			}
		}else{
			settlementReportRequest.get().setSortByField("reportDate desc");
		}
		
		settlementReportRequest.get().setFirstRow(0);
		settlementReportRequest.get().setMaxRow(MAX_ROW_PAGE);
		if (!settlementReportRequest.get().getReportDateEnd().equals("") && !settlementReportRequest.get().getReportDateEnd().equals("")){
			SettlementReportRequest request = models.SettlementReport.findDownloadSettlement(settlementReportRequest.get());

			if (request != null){
				Logger.debug("Total Page : "+settlementReportRequest.get().getTotalPage());
				settlementReportRequest.fill(request);
			}
		}
		List<Corporate> corporates = Corporate.find.findList();
		
		if (settlementReportRequest.get().getSettlementReports() != null){
			Logger.debug("Prev Page : "+settlementReportRequest.get().getSettlementReports().hasPrev());
			Logger.debug("Next Page : "+settlementReportRequest.get().getSettlementReports().hasNext());
		}
		
		return ok(settlementreport.render(settlementReportRequest,corporates));
	}
	
	public static Result listView() {
		settlementReportRequest = form(SettlementReportRequest.class).bindFromRequest();
		
		Logger.debug("Corporate : "+settlementReportRequest.get().getCorporate());
		Logger.debug("Report Date Start : "+settlementReportRequest.get().getReportDateStart());
		Logger.debug("Report Date End :"+settlementReportRequest.get().getReportDateEnd());
		Logger.debug("Sort By : "+settlementReportRequest.get().getSortBy());
		Logger.debug("Order : "+settlementReportRequest.get().getOrder());
		if (settlementReportRequest.get().getPage() < 0){
			settlementReportRequest.get().setPage(0);
		}
		if (settlementReportRequest.get().getSortBy() != null && !settlementReportRequest.get().getSortBy().trim().equals("")){
			try {
				int idxField = new Integer(settlementReportRequest.get().getSortBy());
				settlementReportRequest.get().setSortByField(fieldData[idxField]+" "+settlementReportRequest.get().getOrder());
				Logger.debug("Sort By Field : "+settlementReportRequest.get().getSortByField());
			} catch (Exception e) {
				e.printStackTrace();
				Logger.debug("Failed get sort field");
			}
		}else{
			settlementReportRequest.get().setSortByField("reportDate desc");
		}
		
		settlementReportRequest.get().setFirstRow(0);
		settlementReportRequest.get().setMaxRow(MAX_ROW_PAGE);
		if (!settlementReportRequest.get().getReportDateEnd().equals("") && !settlementReportRequest.get().getReportDateEnd().equals("")){
			SettlementReportRequest request = models.SettlementReport.findDownloadSettlement(settlementReportRequest.get());

			if (request != null){
				Logger.debug("Total Page : "+settlementReportRequest.get().getTotalPage());
				settlementReportRequest.fill(request);
			}
		}
		List<Corporate> corporates = Corporate.find.findList();
		
		if (settlementReportRequest.get().getSettlementReports() != null){
			Logger.debug("Prev Page : "+settlementReportRequest.get().getSettlementReports().hasPrev());
			Logger.debug("Next Page : "+settlementReportRequest.get().getSettlementReports().hasNext());
		}
		
		return ok(settlementreport.render(settlementReportRequest,corporates));
	}
	
	public static Result generateManual(String id){
		if (id != null && !id.trim().equals("")){
			Long findId = new Long(id);
			models.SettlementReport report = models.SettlementReport.findById(findId);
			GenerateReport.doGenerate(report);
		}		
		
		return redirect(routes.SettlementReport.list());
	}	
	
	public static Result download(String name) {
		DecimalFormat sdf = new DecimalFormat("###,###,###,### bytes");
		Logger.debug("Get Name ["+name+"]");
		name = name.replaceAll(".xls", "").trim();
		Logger.debug("Name ["+name+"]");
		if (name != null && !name.trim().equals("")){
			Logger.debug("Found Name ["+name+"]");
			models.SettlementReport report = models.SettlementReport.findByName(name);
			if (report != null){
				Logger.debug("Found on database");
				File fileDownload = new java.io.File(PATH_REPORT+report.getCorporate().code+"/"+GenerateReport.sdfBln.format(report.getReportDate())+"/"+name+".xls");
				if (fileDownload.exists()){
					Logger.debug("Found on storage");
					Logger.debug("File Size : "+sdf.format(fileDownload.length()));
					return ok(fileDownload);
				}
			}
		}
		return ok("Broken link download");
	}
	
	public static Result generateById(Long id) {
		try {
			models.SettlementReport settlementReport = models.SettlementReport.findById(id);
			GenerateReport.doGenerate(settlementReport);
			settlementReport.setReportDate(new Date());
			settlementReport.update();
			Logger.debug("Succes generate by id : "+id);
		} catch (Exception e) {
			Logger.debug("Failed generate by id");
		}
		
		return list();
	}
	
}
