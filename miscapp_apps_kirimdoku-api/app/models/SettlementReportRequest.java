package models;

import com.avaje.ebean.Page;

public class SettlementReportRequest {
	
	private int firstRow = 0;
	private int maxRow = 1;
	private int totalRow = 0;
	private int totalPage = 0;
	private String corporate = "";
	private String reportDateStart = "";
	private String reportDateEnd = "";
	private String sortBy = "";
	private String sortByField = "reportDate";
	private String order = "asc";
	private int page = 0;
	private Page<SettlementReport> settlementReports;
	
	public int getFirstRow() {
		return firstRow;
	}
	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}
	public int getMaxRow() {
		return maxRow;
	}
	public void setMaxRow(int maxRow) {
		this.maxRow = maxRow;
	}
	public int getTotalRow() {
		return totalRow;
	}
	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public String getCorporate() {
		return corporate;
	}
	public void setCorporate(String corporate) {
		this.corporate = corporate;
	}
	public Page<SettlementReport> getSettlementReports() {
		return settlementReports;
	}
	public void setSettlementReports(Page<SettlementReport> settlementReports) {
		this.settlementReports = settlementReports;
	}
	public String getReportDateStart() {
		return reportDateStart;
	}
	public void setReportDateStart(String reportDateStart) {
		this.reportDateStart = reportDateStart;
	}
	public String getReportDateEnd() {
		return reportDateEnd;
	}
	public void setReportDateEnd(String reportDateEnd) {
		this.reportDateEnd = reportDateEnd;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getSortByField() {
		return sortByField;
	}
	public void setSortByField(String sortByField) {
		this.sortByField = sortByField;
	}
	
}
