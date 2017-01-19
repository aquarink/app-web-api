package models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.avaje.ebean.Page;
import com.avaje.ebean.Query;
import play.Logger;
import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
@Table(name="settlement_report")
public class SettlementReport extends Model {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	@Constraints.Required
	private Date reportDate;
	
	@Constraints.Required
    @ManyToOne(optional = false, cascade = {}, fetch = FetchType.EAGER)
	private Corporate corporate;
	
	@Constraints.Required
	private String filename;
	
	@Constraints.Required
	private String status;
	
	@Constraints.Required
	private Date createdTime;
	
	public static Finder<Long,SettlementReport> find = new Finder<Long,SettlementReport>(Long.class, SettlementReport.class);
	
	public static List<SettlementReport> findGenerateSettlement() {
		try{
			SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date todayStart = new Date();
			Date todayEnd = new Date();
			todayStart = sdfTime.parse(sdfDate.format(todayStart)+" 00:00:00");
			
			return find
					.fetch("corporate")
					.where()
					.eq("status", "G")
					.ge("reportDate", todayStart)
					.le("reportDate", todayEnd)
					.findList();
		} catch (Exception e){
			e.printStackTrace();
			Logger.debug("Failed query find generate settlement");
			return null;
		}

	}
	
	public static void changeGenerateSettlement(String cutOffTime) {
		try{
			SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date todayStart = new Date();
			Date todayEnd = new Date();
			todayStart = sdfTime.parse(sdfDate.format(todayStart)+" 00:00:00");
			todayEnd = sdfTime.parse(sdfDate.format(todayStart)+" 23:59:59");
			List<SettlementReport> settlementReports = find
					.fetch("corporate")
					.where()
					//.eq("status", "G") // for testing
					.ge("reportDate", todayStart)
					.le("reportDate", todayEnd)
					.findList();
			
			for (Iterator<SettlementReport> iterator = settlementReports.iterator(); iterator.hasNext();) {
				SettlementReport settlementReport = (SettlementReport) iterator.next();
				try {
					Date cutOffTimeDate = sdfTime.parse(sdfDate.format(settlementReport.getReportDate())+" "+cutOffTime);
					settlementReport.setReportDate(cutOffTimeDate);
					settlementReport.setStatus("G"); // for testing
					settlementReport.save();
					
					Logger.debug("Change generated settlement ("+settlementReport.getId()+") to setup cutOffTime "+cutOffTime);
					
				} catch (Exception e) {
					e.printStackTrace();
					Logger.debug("Failed save change generate settlement to database");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.debug("Failed change generate settlement");
		}
	}
	
	public static void createGenerateSettlement(Date reportDateStart, Date reportDateEnd, String cutOffTime, Corporate corporate) {
		try{
			List<SettlementReport> settlementReports = find
					.fetch("corporate")
					.where()
					.eq("corporate", corporate)
					.gt("reportDate", reportDateStart)
					.le("reportDate", reportDateEnd)
					.findList();
			
			if (corporate != null && settlementReports == null || settlementReports.size() == 0){
				SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
				SimpleDateFormat gdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat ddf = new SimpleDateFormat("yyyy-MM-dd");
				Date reportDateGenerate = gdf.parse(ddf.format(reportDateEnd)+" "+cutOffTime);
				SettlementReport settlementReport = new SettlementReport();
				settlementReport.setStatus("G");
				settlementReport.setReportDate(reportDateGenerate);
				settlementReport.setCorporate(corporate);
				settlementReport.setFilename(corporate.code+sdf.format(reportDateEnd));
				settlementReport.save();
				Logger.debug("Saving For Corporate "+corporate.code+", Date "+ddf.format(reportDateStart)+" "+cutOffTime+" - "+ddf.format(reportDateEnd)+" "+cutOffTime);
			}
		}catch (Exception e){
			e.printStackTrace();
			Logger.debug("Failed Create Settlement");
		}
	}
	
	public static SettlementReport findByName(String name) {
		try {
			SettlementReport settlementReport = find.fetch("corporate").where().eq("status", "D").eq("filename", name).le("reportDate", new Date()).findUnique();
			if (settlementReport != null){
				return settlementReport;
			}else{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static SettlementReport findById(Long findId) {		
		return find.where().eq("id", findId).findUnique();
	}
	
	public static SettlementReportRequest findDownloadSettlement(SettlementReportRequest settlementReportRequest) {
		try {
			Query<SettlementReport> qSettlementReport = find.fetch("corporate").where()
					.query();
			Logger.debug("Total Row Query : "+qSettlementReport.getMaxRows());
			if (settlementReportRequest.getCorporate() != null && !settlementReportRequest.getCorporate().equals("")){
				qSettlementReport = qSettlementReport.where()
					.ilike("corporate_code", settlementReportRequest.getCorporate())
					.query();
			}
			Logger.debug("Total Row Query : "+qSettlementReport.getMaxRows());
			if (settlementReportRequest.getReportDateStart() != null 
					&& !settlementReportRequest.getReportDateStart().equals("")
					&& settlementReportRequest.getReportDateEnd() != null
					&& !settlementReportRequest.getReportDateEnd().equals("")){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date dateStart = sdf.parse(settlementReportRequest.getReportDateStart()+" 00:00:00");
				Date dateEnd = sdf.parse(settlementReportRequest.getReportDateEnd()+" 23:59:59");
				qSettlementReport = qSettlementReport
						.where()
						.between("reportDate", dateStart, dateEnd)
						.query();
			}
			if (settlementReportRequest.getSortByField() != null && !settlementReportRequest.getSortByField().trim().equals("")){
				qSettlementReport = qSettlementReport.orderBy(settlementReportRequest.getSortByField());
			}else{
				qSettlementReport = qSettlementReport.orderBy("reportDate desc");
			}
			Page<SettlementReport> settlementReports = qSettlementReport
					.where()
					.findPagingList(settlementReportRequest.getMaxRow())
					.setFetchAhead(false)
					.getPage(settlementReportRequest.getPage());
			Logger.debug("Total Page Query : "+settlementReports.getTotalPageCount());
			Logger.debug("Total Row Query : "+settlementReports.getTotalRowCount());
			settlementReportRequest.setTotalPage(settlementReports.getTotalPageCount());
			settlementReportRequest.setTotalRow(settlementReports.getTotalRowCount());
			settlementReportRequest.setSettlementReports(settlementReports);
			return settlementReportRequest;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.debug("Error on findDownloadSettlement");
			return null;
		}
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public Corporate getCorporate() {
		return corporate;
	}

	public void setCorporate(Corporate corporate) {
		this.corporate = corporate;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
}
