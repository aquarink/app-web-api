package job;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import models.Channel;
import models.ColExcel;
import models.SettlementReport;
import models.ShareSetting;
import models.Transaction;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import models.QueryScheduller;
import play.Logger;

public class GenerateReport {
	
	protected static final String GROSS_TITLE = "Settlement";
	public static final String PATH_REPORT = play.Configuration.root().getString("settlement.report.path.download");
	public static final String[] HEADER_TITLE_SUMMARY= {"Trx Type","Number","Sent Amount","Fee","Forex Share","Fee Share","Total"};
	public static final String[] HEADER_TITLE_SENDING= {"TID","Trx Type","Channel","Date of Sending","Operator ID","Country","Currency","Sent Amount","Exchange Rate","Base Rate","Fee","Fee Share","Forex Share",GROSS_TITLE};
	public static final String[] HEADER_TITLE_RECEIVE= {"TID","Transaction Type","Service Code","Trx Date","Operator ID","Country","Currency","Sent Amount","Exchange Rate","Base Rate","Fee","Fee Share","Forex Share",GROSS_TITLE};
	public static final String[] HEADER_TITLE_REFUND= {"TID","Trx Type","Channel","Trx Date","Operator ID","Country","Currency","Sent Amount","Exchange Rate","Base Rate","Fee","Fee Share","Forex Share",GROSS_TITLE};
	private static final String ADD = "+";
	private static final String SUB = "-";
	private static final String MUL = "*";
	private static final String DIV = "/";
	
	private static Workbook reportWorkbook;
	private static Sheet summarySheet;
	private static Sheet sendingSheet;
	private static Sheet receiveSheet;
	private static Sheet refundSheet;
	private static Map<String, CellStyle> styles;
	protected static SettlementReport settlementReports;
	protected static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	public static SimpleDateFormat sdfBln = new SimpleDateFormat("yyyyMM");
	protected static BigDecimal PERSEN = new BigDecimal(100);
	protected static int PERCENTAGE = 100;
	private static String[] rangeSending = {"", "", "", "", ""};
	private static String[] rangeReceive = {"", "", "", "", ""};
	private static String[] rangeRefund = {"", "", "", "", ""};
	private static String[] rangeSendingRefund = {"", "", "", "", ""};
	
	protected static final String SUMMARY_SHEET = "Summary";
	protected static final String SENDING_SHEET = "Outbound";
	protected static final String RECEIVE_SHEET = "Inbound";
	protected static final String REFUND_SHEET = "Refund";
	
	protected static final String SUMMARY_TITLE = "Money Transfer Summary Report";
	protected static final String SENDING_TITLE = "Detailed Outbound Report";
	protected static final String RECEIVE_TITLE = "Detailed Inbound Report";
	protected static final String REFUND_TITLE = "Detailed Refund Report";
	
	protected static final String SEND_TRXTYPE = "Send Money";
	protected static final String RECEIVE_TRXTYPE = "Receive";
	protected static final String REFUND_TRXTYPE = "Refund";
	
	
	public static void doGenerate(SettlementReport settlementReport){
		try{
			Date time = new Date();
			Logger.debug("Start Generate at "+time);
			
			for (int i = 0; i < rangeSending.length; i++) {
				rangeSending[i] = "";
				rangeReceive[i] = "";
				rangeRefund[i] = "";
				rangeSendingRefund[i] = "";
			}
			
			settlementReports = settlementReport;		
			
			String pathCorporate = PATH_REPORT+settlementReports.getCorporate().code;
			pathXLS(pathCorporate);
			pathCorporate += "/"+sdfBln.format(settlementReports.getReportDate());
			pathXLS(pathCorporate);
			
			String fileName = pathCorporate+"/"+settlementReport.getFilename()+".xls";
			reportWorkbook = new HSSFWorkbook();
			
			styles = createStyles();
			
			summarySheet = reportWorkbook.createSheet(SUMMARY_SHEET);
			
			generateSending();
			generateReceive();
			generateRefund();
			generateSummary();
			
	        FileOutputStream fileOut =  new FileOutputStream(fileName);
	        reportWorkbook.write(fileOut);
	        fileOut.close();
	        time = new Date();
	        
	        settlementReport.setStatus("D");
	        settlementReport.setCreatedTime(time);
	        settlementReport.update();
	        
	        Logger.debug("Finish., Your excel file has been generated! "+time);
		} catch (Exception ex) {
			ex.printStackTrace();
			Logger.debug("failed generated excel");
		}
	}
	
	public static void generateSummary() {
		try {
			//summarySheet = reportWorkbook.createSheet("Summary");
			int row = 0;
			createCellOnRowOfSheet(summarySheet, row, ColExcel.A.value(), (short)0, "TITLE", SUMMARY_TITLE, Cell.CELL_TYPE_STRING);
			summarySheet.addMergedRegion(new CellRangeAddress(row, row+1, 0, HEADER_TITLE_SUMMARY.length-1));
			
			List<Channel> channels = Channel.find.findList();
			for (Iterator<Channel> iterator = channels.iterator(); iterator.hasNext();) {
				Channel channel = (Channel) iterator.next();
				
				row += 3;
				createCellOnRowOfSheet(summarySheet, row, ColExcel.A.value(), (short)0, "HEADER", "Corporate:", Cell.CELL_TYPE_STRING);
				createCellOnRowOfSheet(summarySheet, row, ColExcel.B.value(), (short)0, "", settlementReports.getCorporate().code, Cell.CELL_TYPE_STRING);
				createCellOnRowOfSheet(summarySheet, row, ColExcel.E.value(), (short)0, "HEADER", "Corporate Name:", Cell.CELL_TYPE_STRING);
				createCellOnRowOfSheet(summarySheet, row, ColExcel.F.value(), (short)0, "", settlementReports.getCorporate().name, Cell.CELL_TYPE_STRING);
				row++;
				createCellOnRowOfSheet(summarySheet, row, ColExcel.A.value(), (short)0, "HEADER", "Date:", Cell.CELL_TYPE_STRING);
				createCellOnRowOfSheet(summarySheet, row, ColExcel.B.value(), (short)0, "", sdf.format(settlementReports.getReportDate()), Cell.CELL_TYPE_STRING);
				createCellOnRowOfSheet(summarySheet, row, ColExcel.E.value(), (short)0, "HEADER", "Currency:", Cell.CELL_TYPE_STRING);
				createCellOnRowOfSheet(summarySheet, row, ColExcel.F.value(), (short)0, "", settlementReports.getCorporate().currency.code, Cell.CELL_TYPE_STRING);
				row +=2;
				createCellOnRowOfSheet(summarySheet, row, ColExcel.A.value(), (short)0, "HEADER", "Channel:", Cell.CELL_TYPE_STRING);
				createCellOnRowOfSheet(summarySheet, row, ColExcel.B.value(), (short)0, "", channel.name, Cell.CELL_TYPE_STRING);
				createCellOnRowOfSheet(summarySheet, row, ColExcel.E.value(), (short)0, "HEADER", "Channel Code:", Cell.CELL_TYPE_STRING);
				createCellOnRowOfSheet(summarySheet, row, ColExcel.F.value(), (short)0, "", channel.code, Cell.CELL_TYPE_STRING);
				row++;
				for (int i = 0; i < HEADER_TITLE_SUMMARY.length; i++) {
					createCellOnRowOfSheet(summarySheet, row, i, (short)500, "HEADER_TABLE", HEADER_TITLE_SUMMARY[i], Cell.CELL_TYPE_STRING);
					summarySheet.setColumnWidth(i, 4000);
				}
				
				String formulaGrossTotal = "";
				
				row++;
				//Summary Sending
				createCellOnRowOfSheet(summarySheet, row, ColExcel.A.value(), (short)0, "TABLE", SEND_TRXTYPE, Cell.CELL_TYPE_STRING);
				
				//Sending Refund
				String formulaCountTrxSendRefund = "0";
				if (!rangeSendingRefund[0].trim().equals("")){
					formulaCountTrxSendRefund = "COUNTIF("+refundSheet.getSheetName()+"!"+rangeSendingRefund[0]+",\"="+channel.name+",\")";
				}				
				
				String formulaCountTrxSend = "0";
				if (!rangeSending[0].trim().equals("")){
					formulaCountTrxSend = "COUNTIF("+sendingSheet.getSheetName()+"!"+rangeSending[0]+",\"="+channel.name+"\")";
					createCellOnRowOfSheet(summarySheet, row, ColExcel.B.value(), (short)0, "TABLE", formulaCountTrxSend+ADD+formulaCountTrxSendRefund, Cell.CELL_TYPE_FORMULA);
				}else{
					createCellOnRowOfSheet(summarySheet, row, ColExcel.B.value(), (short)0, "TABLE", formulaCountTrxSend+ADD+formulaCountTrxSendRefund, Cell.CELL_TYPE_FORMULA);
				}
				
				//Sending Refund
				String formulaSumTrxSendAmountRefund = "0";
				if (!rangeSendingRefund[0].trim().equals("") && !rangeSendingRefund[1].trim().equals("")){
					formulaSumTrxSendAmountRefund = "SUMIF("+refundSheet.getSheetName()+"!"+rangeSendingRefund[0]+",\"="+channel.name+",\","+refundSheet.getSheetName()+"!"+rangeSendingRefund[1]+")";
				}
				
				String formulaSumTrxSendAmount = "0";
				if (!rangeSending[0].trim().equals("") && !rangeSending[1].trim().equals("")){
					formulaSumTrxSendAmount = "SUMIF("+sendingSheet.getSheetName()+"!"+rangeSending[0]+",\"="+channel.name+"\","+sendingSheet.getSheetName()+"!"+rangeSending[1]+")";
					createCellOnRowOfSheet(summarySheet, row, ColExcel.C.value(), (short)0, "TABLE", formulaSumTrxSendAmount+ADD+formulaSumTrxSendAmountRefund, Cell.CELL_TYPE_FORMULA);
				}else{
					createCellOnRowOfSheet(summarySheet, row, ColExcel.C.value(), (short)0, "TABLE", formulaSumTrxSendAmount+ADD+formulaSumTrxSendAmountRefund, Cell.CELL_TYPE_FORMULA);
				}
				
				//Sending Refund
				String formulaSumTrxSendFeeRefund = "0";
				if (!rangeSendingRefund[0].trim().equals("") && !rangeSendingRefund[2].trim().equals("")){
					formulaSumTrxSendFeeRefund = "SUMIF("+refundSheet.getSheetName()+"!"+rangeSendingRefund[0]+",\"="+channel.name+",\","+refundSheet.getSheetName()+"!"+rangeSendingRefund[2]+")";
				}
				
				String formulaSumTrxSendFee = "0";
				if (!rangeSending[0].trim().equals("") && !rangeSending[2].trim().equals("")){
					formulaSumTrxSendFee = "SUMIF("+sendingSheet.getSheetName()+"!"+rangeSending[0]+",\"="+channel.name+"\","+sendingSheet.getSheetName()+"!"+rangeSending[2]+")";
					createCellOnRowOfSheet(summarySheet, row, ColExcel.D.value(), (short)0, "TABLE", formulaSumTrxSendFee+ADD+formulaSumTrxSendFeeRefund, Cell.CELL_TYPE_FORMULA);
				}else{
					createCellOnRowOfSheet(summarySheet, row, ColExcel.D.value(), (short)0, "TABLE", formulaSumTrxSendFee+ADD+formulaSumTrxSendFeeRefund, Cell.CELL_TYPE_FORMULA);
				}
				
				//Sending Refund
				String formulaSumTrxSendForexShareRefund = "0";
				if (!rangeSendingRefund[0].trim().equals("") && !rangeSendingRefund[3].trim().equals("")){
					formulaSumTrxSendForexShareRefund = "SUMIF("+refundSheet.getSheetName()+"!"+rangeSendingRefund[0]+",\"="+channel.name+",\","+refundSheet.getSheetName()+"!"+rangeSendingRefund[3]+")";
				}
				
				String formulaSumTrxSendForexShare = "0";
				if (!rangeSending[0].trim().equals("") && !rangeSending[3].trim().equals("")){
					formulaSumTrxSendForexShare = "SUMIF("+sendingSheet.getSheetName()+"!"+rangeSending[0]+",\"="+channel.name+"\","+sendingSheet.getSheetName()+"!"+rangeSending[3]+")";
					createCellOnRowOfSheet(summarySheet, row, ColExcel.F.value(), (short)0, "TABLE", formulaSumTrxSendForexShare+ADD+formulaSumTrxSendForexShareRefund, Cell.CELL_TYPE_FORMULA);
				}else{
					createCellOnRowOfSheet(summarySheet, row, ColExcel.F.value(), (short)0, "TABLE", formulaSumTrxSendForexShare+ADD+formulaSumTrxSendForexShareRefund, Cell.CELL_TYPE_FORMULA);
				}
				
				//Sending Refund
				String formulaSumTrxSendFeeShareRefund = "0";
				if (!rangeSendingRefund[0].trim().equals("") && !rangeSendingRefund[4].trim().equals("")){
					formulaSumTrxSendFeeShareRefund = "SUMIF("+refundSheet.getSheetName()+"!"+rangeSendingRefund[0]+",\"="+channel.name+",\","+refundSheet.getSheetName()+"!"+rangeSendingRefund[4]+")";
				}
				
				String formulaSumTrxSendFeeShare = "0";
				if (!rangeSending[0].trim().equals("") && !rangeSending[4].trim().equals("")){
					formulaSumTrxSendFeeShare = "SUMIF("+sendingSheet.getSheetName()+"!"+rangeSending[0]+",\"="+channel.name+"\","+sendingSheet.getSheetName()+"!"+rangeSending[4]+")";
					createCellOnRowOfSheet(summarySheet, row, ColExcel.E.value(), (short)0, "TABLE", formulaSumTrxSendFeeShare+ADD+formulaSumTrxSendFeeShareRefund, Cell.CELL_TYPE_FORMULA);
				}else{
					createCellOnRowOfSheet(summarySheet, row, ColExcel.E.value(), (short)0, "TABLE", formulaSumTrxSendFeeShare+ADD+formulaSumTrxSendFeeShareRefund, Cell.CELL_TYPE_FORMULA);
				}
				
				String formulaTotal = ColExcel.C.name()+(row+1)+ADD+"("+ColExcel.D.name()+(row+1)+SUB+ColExcel.E.name()+(row+1)+")"+SUB+ColExcel.F.name()+(row+1);
				createCellOnRowOfSheet(summarySheet, row, ColExcel.G.value(), (short)0, "TABLE", formulaTotal, Cell.CELL_TYPE_FORMULA);
				
				formulaGrossTotal = ColExcel.G.name()+(row+1)+":";
				
				row++;
				//Summary Receive
				createCellOnRowOfSheet(summarySheet, row, ColExcel.A.value(), (short)0, "TABLE", RECEIVE_TRXTYPE, Cell.CELL_TYPE_STRING);
				
				String formulaCountTrxReceive = "0";
				if (!rangeReceive[0].trim().equals("")){
					formulaCountTrxReceive = "COUNTIF("+receiveSheet.getSheetName()+"!"+rangeReceive[0]+",\"="+channel.name+"\")";
					createCellOnRowOfSheet(summarySheet, row, ColExcel.B.value(), (short)0, "TABLE", formulaCountTrxReceive, Cell.CELL_TYPE_FORMULA);
				}else{
					createCellOnRowOfSheet(summarySheet, row, ColExcel.B.value(), (short)0, "TABLE", formulaCountTrxReceive, Cell.CELL_TYPE_NUMERIC);
				}
				
				String formulaSumTrxReceiveAmount = "0";
				if (!rangeReceive[0].trim().equals("") && !rangeReceive[1].trim().equals("")){
					formulaSumTrxReceiveAmount = "SUMIF("+receiveSheet.getSheetName()+"!"+rangeReceive[0]+",\"="+channel.name+"\","+receiveSheet.getSheetName()+"!"+rangeReceive[1]+")";
					createCellOnRowOfSheet(summarySheet, row, ColExcel.C.value(), (short)0, "TABLE", formulaSumTrxReceiveAmount, Cell.CELL_TYPE_FORMULA);
				}else{
					createCellOnRowOfSheet(summarySheet, row, ColExcel.C.value(), (short)0, "TABLE", formulaSumTrxReceiveAmount, Cell.CELL_TYPE_NUMERIC);
				}
				
				String formulaSumTrxReceiveFee = "0";
				if (!rangeReceive[0].trim().equals("") && !rangeReceive[2].trim().equals("")){
					formulaSumTrxReceiveFee = "SUMIF("+receiveSheet.getSheetName()+"!"+rangeReceive[0]+",\"="+channel.name+"\","+receiveSheet.getSheetName()+"!"+rangeReceive[2]+")";
					createCellOnRowOfSheet(summarySheet, row, ColExcel.D.value(), (short)0, "TABLE", formulaSumTrxReceiveFee, Cell.CELL_TYPE_FORMULA);
				}else{
					createCellOnRowOfSheet(summarySheet, row, ColExcel.D.value(), (short)0, "TABLE", formulaSumTrxReceiveFee, Cell.CELL_TYPE_NUMERIC);
				}
				
				String formulaSumTrxReceiveForexShare = "0";
				if (!rangeReceive[0].trim().equals("") && !rangeReceive[3].trim().equals("")){
					formulaSumTrxReceiveForexShare = "SUMIF("+receiveSheet.getSheetName()+"!"+rangeReceive[0]+",\"="+channel.name+"\","+receiveSheet.getSheetName()+"!"+rangeReceive[3]+")";
					createCellOnRowOfSheet(summarySheet, row, ColExcel.F.value(), (short)0, "TABLE", formulaSumTrxReceiveForexShare, Cell.CELL_TYPE_FORMULA);
				}else{
					createCellOnRowOfSheet(summarySheet, row, ColExcel.F.value(), (short)0, "TABLE", formulaSumTrxReceiveForexShare, Cell.CELL_TYPE_NUMERIC);
				}
				
				String formulaSumTrxReceiveFeeShare = "0";
				if (!rangeReceive[0].trim().equals("") && !rangeReceive[4].trim().equals("")){
					formulaSumTrxReceiveFeeShare = "SUMIF("+receiveSheet.getSheetName()+"!"+rangeReceive[0]+",\"="+channel.name+"\","+receiveSheet.getSheetName()+"!"+rangeReceive[4]+")";
					createCellOnRowOfSheet(summarySheet, row, ColExcel.E.value(), (short)0, "TABLE", formulaSumTrxReceiveFeeShare, Cell.CELL_TYPE_FORMULA);
				}else{
					createCellOnRowOfSheet(summarySheet, row, ColExcel.E.value(), (short)0, "TABLE", formulaSumTrxReceiveFeeShare, Cell.CELL_TYPE_NUMERIC);
				}
				
				formulaTotal = ColExcel.C.name()+(row+1)+ADD+ColExcel.E.name()+(row+1)+ADD+ColExcel.F.name()+(row+1);
				createCellOnRowOfSheet(summarySheet, row, ColExcel.G.value(), (short)0, "TABLE", formulaTotal, Cell.CELL_TYPE_FORMULA);
				
				row++;
				//Summary Refund
				createCellOnRowOfSheet(summarySheet, row, ColExcel.A.value(), (short)0, "TABLE", REFUND_TRXTYPE, Cell.CELL_TYPE_STRING);
				
				String formulaCountTrxRefund = "0";
				if (!rangeRefund[0].trim().equals("")){
					formulaCountTrxRefund = "COUNTIF("+refundSheet.getSheetName()+"!"+rangeRefund[0]+",\"="+channel.name+"\")";
					createCellOnRowOfSheet(summarySheet, row, ColExcel.B.value(), (short)0, "TABLE", formulaCountTrxRefund, Cell.CELL_TYPE_FORMULA);
				}else{
					createCellOnRowOfSheet(summarySheet, row, ColExcel.B.value(), (short)0, "TABLE", formulaCountTrxRefund, Cell.CELL_TYPE_NUMERIC);
				}
				
				String formulaSumTrxRefundAmount = "0";
				if (!rangeRefund[0].trim().equals("") && !rangeRefund[1].trim().equals("")){
					formulaSumTrxRefundAmount = "SUMIF("+refundSheet.getSheetName()+"!"+rangeRefund[0]+",\"="+channel.name+"\","+refundSheet.getSheetName()+"!"+rangeRefund[1]+")";
					createCellOnRowOfSheet(summarySheet, row, ColExcel.C.value(), (short)0, "TABLE", formulaSumTrxRefundAmount, Cell.CELL_TYPE_FORMULA);
				}else{
					createCellOnRowOfSheet(summarySheet, row, ColExcel.C.value(), (short)0, "TABLE", formulaSumTrxRefundAmount, Cell.CELL_TYPE_NUMERIC);
				}
				
				String formulaSumTrxRefundFee = "0";
				if (!rangeRefund[0].trim().equals("") && !rangeRefund[2].trim().equals("")){
					formulaSumTrxRefundFee = "SUMIF("+refundSheet.getSheetName()+"!"+rangeRefund[0]+",\"="+channel.name+"\","+refundSheet.getSheetName()+"!"+rangeRefund[2]+")";
					createCellOnRowOfSheet(summarySheet, row, ColExcel.D.value(), (short)0, "TABLE", formulaSumTrxRefundFee, Cell.CELL_TYPE_FORMULA);
				}else{
					createCellOnRowOfSheet(summarySheet, row, ColExcel.D.value(), (short)0, "TABLE", formulaSumTrxRefundFee, Cell.CELL_TYPE_NUMERIC);
				}
				
				String formulaSumTrxRefundForexShare = "0";
				if (!rangeRefund[0].trim().equals("") && !rangeRefund[3].trim().equals("")){
					formulaSumTrxRefundForexShare = "SUMIF("+refundSheet.getSheetName()+"!"+rangeRefund[0]+",\"="+channel.name+"\","+refundSheet.getSheetName()+"!"+rangeRefund[3]+")";
					createCellOnRowOfSheet(summarySheet, row, ColExcel.F.value(), (short)0, "TABLE", formulaSumTrxRefundForexShare, Cell.CELL_TYPE_FORMULA);
				}else{
					createCellOnRowOfSheet(summarySheet, row, ColExcel.F.value(), (short)0, "TABLE", formulaSumTrxRefundForexShare, Cell.CELL_TYPE_NUMERIC);
				}
				
				String formulaSumTrxRefundFeeShare = "0";
				if (!rangeRefund[0].trim().equals("") && !rangeRefund[4].trim().equals("")){
					formulaSumTrxRefundFeeShare = "SUMIF("+refundSheet.getSheetName()+"!"+rangeRefund[0]+",\"="+channel.name+"\","+refundSheet.getSheetName()+"!"+rangeRefund[4]+")";
					createCellOnRowOfSheet(summarySheet, row, ColExcel.E.value(), (short)0, "TABLE", formulaSumTrxRefundFeeShare, Cell.CELL_TYPE_FORMULA);
				}else{
					createCellOnRowOfSheet(summarySheet, row, ColExcel.E.value(), (short)0, "TABLE", formulaSumTrxRefundFeeShare, Cell.CELL_TYPE_NUMERIC);
				}
				
				formulaTotal = ColExcel.C.name()+(row+1)+ADD+ColExcel.E.name()+(row+1)+ADD+ColExcel.F.name()+(row+1);
				createCellOnRowOfSheet(summarySheet, row, ColExcel.G.value(), (short)0, "TABLE", formulaTotal, Cell.CELL_TYPE_FORMULA);
				
				formulaGrossTotal += ColExcel.G.name()+(row+1);
				
				row++;
				//Summary Gross
				createCellOnRowOfSheet(summarySheet, row, ColExcel.A.value(), (short)0, "TABLE_BOLD", GROSS_TITLE, Cell.CELL_TYPE_STRING);
				createCellOnRowOfSheet(summarySheet, row, ColExcel.B.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
				createCellOnRowOfSheet(summarySheet, row, ColExcel.C.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
				createCellOnRowOfSheet(summarySheet, row, ColExcel.D.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);				
				createCellOnRowOfSheet(summarySheet, row, ColExcel.F.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);					
				createCellOnRowOfSheet(summarySheet, row, ColExcel.E.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
				
				formulaGrossTotal = "SUM("+formulaGrossTotal+")";
				createCellOnRowOfSheet(summarySheet, row, ColExcel.G.value(), (short)0, "TABLE_BOLD", formulaGrossTotal, Cell.CELL_TYPE_FORMULA);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.debug("Failed generate summary");
		}
	}
	
	public static void generateSending() {
		try {
			int[] colWidth = {4000,4000,6000,4000,4000,3000,3000,4000,3500,3000,3000,3000,3000,3000};
			int row = 0;
			String formulaSubTotal = "0";
			sendingSheet = reportWorkbook.createSheet(SENDING_SHEET);
			createCellOnRowOfSheet(sendingSheet, row, ColExcel.A.value(), (short)0, "TITLE", SENDING_TITLE, Cell.CELL_TYPE_STRING);
			sendingSheet.addMergedRegion(new CellRangeAddress(row, row+1, ColExcel.A.value(), HEADER_TITLE_SENDING.length-1));
			row++;
			row++;
			createCellOnRowOfSheet(sendingSheet, row, ColExcel.A.value(), (short)0, "HEADER", "Corporate Code:", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(sendingSheet, row, ColExcel.B.value(), (short)0, "", settlementReports.getCorporate().code, Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(sendingSheet, row, ColExcel.E.value(), (short)0, "HEADER", "Corporate Name:", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(sendingSheet, row, ColExcel.F.value(), (short)0, "", settlementReports.getCorporate().name, Cell.CELL_TYPE_STRING);
			row++;
			createCellOnRowOfSheet(sendingSheet, row, ColExcel.A.value(), (short)0, "HEADER", "Report Date:", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(sendingSheet, row, ColExcel.B.value(), (short)0, "", sdf.format(settlementReports.getReportDate()), Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(sendingSheet, row, ColExcel.E.value(), (short)0, "HEADER", "Currency:", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(sendingSheet, row, ColExcel.F.value(), (short)0, "", settlementReports.getCorporate().currency.code, Cell.CELL_TYPE_STRING);
			row++;
			for (int i = 0; i < HEADER_TITLE_SENDING.length; i++) {
				createCellOnRowOfSheet(sendingSheet, row, i, (short)500, "HEADER_TABLE", HEADER_TITLE_SENDING[i], Cell.CELL_TYPE_STRING);
				sendingSheet.setColumnWidth(i, colWidth[i]);
			}
			
			List<Transaction> transactions = QueryScheduller.getTransactionsSettlementReportSending(settlementReports.getCorporate(), settlementReports.getReportDate());
			
			row++;
			if (transactions != null && transactions.size() > 0){
				formulaSubTotal = ColExcel.N.name()+(row+1)+":";
				rangeSending[0] = ColExcel.C.name()+(row+1)+":";
				rangeSending[1] = ColExcel.H.name()+(row+1)+":";
				rangeSending[2] = ColExcel.K.name()+(row+1)+":";
				rangeSending[3] = ColExcel.L.name()+(row+1)+":";
				rangeSending[4] = ColExcel.M.name()+(row+1)+":";
				for (Iterator<Transaction> iterator = transactions.iterator(); iterator.hasNext();) {
					Transaction transaction = (Transaction) iterator.next();
					ShareSetting sendingShareSetting = new ShareSetting().findById(transaction.shareSenderId);
					
					if (sendingShareSetting != null){
						
						//Send
						createCellOnRowOfSheet(sendingSheet, row, ColExcel.A.value(), (short)0, "TABLE", transaction.getIdToken(), Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(sendingSheet, row, ColExcel.B.value(), (short)0, "TABLE", SEND_TRXTYPE, Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(sendingSheet, row, ColExcel.C.value(), (short)0, "TABLE", transaction.channel.name, Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(sendingSheet, row, ColExcel.D.value(), (short)0, "TABLE", sdf.format(transaction.cashInTime.getTime()), Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(sendingSheet, row, ColExcel.E.value(), (short)0, "TABLE", transaction.agent.getIdToken(), Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(sendingSheet, row, ColExcel.F.value(), (short)0, "TABLE", transaction.senderCountry.code, Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(sendingSheet, row, ColExcel.G.value(), (short)0, "TABLE", transaction.senderCurrency.code, Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(sendingSheet, row, ColExcel.H.value(), (short)0, "TABLE", transaction.senderAmount.toString(), Cell.CELL_TYPE_NUMERIC);
						createCellOnRowOfSheet(sendingSheet, row, ColExcel.I.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(sendingSheet, row, ColExcel.J.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(sendingSheet, row, ColExcel.K.value(), (short)0, "TABLE", transaction.feesTotal().toString(), Cell.CELL_TYPE_NUMERIC);
						
						String formulaFeeShare = "";
						if (sendingShareSetting.getFeType() == 1){
							BigDecimal feePercentage = sendingShareSetting.getFeeShareSettingPercentage();
							formulaFeeShare = ColExcel.K.name()+(row+1)+MUL+feePercentage+DIV+PERCENTAGE;
						}else{
							formulaFeeShare = sendingShareSetting.getFeeShareSettingFix().toString();
						}
						
						createCellOnRowOfSheet(sendingSheet, row, ColExcel.L.value(), (short)0, "TABLE", formulaFeeShare, Cell.CELL_TYPE_FORMULA);
						String formulaForexShare = "";
						if (sendingShareSetting.getFoType() == 1){
							BigDecimal spread = BigDecimal.valueOf(transaction.forexReference.forex.spread);
							formulaForexShare = "("+ColExcel.H.name()+(row+1)+MUL+spread+DIV+PERCENTAGE+")"+MUL+sendingShareSetting.getForexShareSettingPercentage()+DIV+PERCENTAGE;
						}else{
							formulaForexShare = sendingShareSetting.getForexShareSettingFix().toString();
						}
						
						createCellOnRowOfSheet(sendingSheet, row, ColExcel.M.value(), (short)0, "TABLE", formulaForexShare, Cell.CELL_TYPE_FORMULA);
						String formulaGross = ColExcel.H.name()+(row+1)+ADD+"("+ColExcel.K.name()+(row+1)+SUB+ColExcel.L.name()+(row+1)+")"+SUB+ColExcel.M+(row+1);				
						createCellOnRowOfSheet(sendingSheet, row, ColExcel.N.value(), (short)0, "TABLE", formulaGross, Cell.CELL_TYPE_FORMULA);
						
						row++;
						//Receive
						createCellOnRowOfSheet(sendingSheet, row, ColExcel.A.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(sendingSheet, row, ColExcel.B.value(), (short)0, "TABLE", RECEIVE_TRXTYPE, Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(sendingSheet, row, ColExcel.C.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(sendingSheet, row, ColExcel.D.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(sendingSheet, row, ColExcel.E.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(sendingSheet, row, ColExcel.F.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(sendingSheet, row, ColExcel.G.value(), (short)0, "TABLE", transaction.beneficiaryCurrency.code, Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(sendingSheet, row, ColExcel.H.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(sendingSheet, row, ColExcel.I.value(), (short)0, "TABLE", transaction.forexReference.rate+"", Cell.CELL_TYPE_NUMERIC);
						createCellOnRowOfSheet(sendingSheet, row, ColExcel.J.value(), (short)0, "TABLE", transaction.forexReference.initialRate+"", Cell.CELL_TYPE_NUMERIC);
						createCellOnRowOfSheet(sendingSheet, row, ColExcel.K.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(sendingSheet, row, ColExcel.L.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(sendingSheet, row, ColExcel.M.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(sendingSheet, row, ColExcel.N.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
						row++;
					}
				}
				rangeSending[0] += ColExcel.C.name()+row;
				rangeSending[1] += ColExcel.H.name()+row;
				rangeSending[2] += ColExcel.K.name()+row;
				rangeSending[3] += ColExcel.L.name()+row;
				rangeSending[4] += ColExcel.M.name()+row;
				formulaSubTotal = "SUM("+formulaSubTotal+ColExcel.N.name()+row+")";
			}
			//Subtotal
			createCellOnRowOfSheet(sendingSheet, row, ColExcel.A.value(), (short)0, "TABLE_BOLD", "Subtotal", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(sendingSheet, row, ColExcel.B.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(sendingSheet, row, ColExcel.C.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(sendingSheet, row, ColExcel.D.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(sendingSheet, row, ColExcel.E.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(sendingSheet, row, ColExcel.F.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(sendingSheet, row, ColExcel.G.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(sendingSheet, row, ColExcel.H.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(sendingSheet, row, ColExcel.I.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(sendingSheet, row, ColExcel.J.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(sendingSheet, row, ColExcel.K.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(sendingSheet, row, ColExcel.L.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(sendingSheet, row, ColExcel.M.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(sendingSheet, row, ColExcel.N.value(), (short)0, "TABLE_BOLD", formulaSubTotal, Cell.CELL_TYPE_FORMULA);
			
		} catch (Exception e) {
			e.printStackTrace();
			Logger.debug("Failed generate sending");
		}
	}
	
	public static void generateReceive() {
		try {
			int[] colWidth = {4000,4000,6000,4000,4000,3000,3000,4000,3500,3000,3000,3000,3000,3000};
			int row = 0;
			String formulaSubTotal = "0";
			receiveSheet = reportWorkbook.createSheet(RECEIVE_SHEET);
			createCellOnRowOfSheet(receiveSheet, row, row, (short)0, "TITLE", RECEIVE_TITLE, Cell.CELL_TYPE_STRING);
			receiveSheet.addMergedRegion(new CellRangeAddress(row, row+1, ColExcel.A.value(), HEADER_TITLE_RECEIVE.length-1));
			row++;
			row++;
			createCellOnRowOfSheet(receiveSheet, row, ColExcel.A.value(), (short)0, "HEADER", "Corporate Code:", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(receiveSheet, row, ColExcel.B.value(), (short)0, "", settlementReports.getCorporate().code, Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(receiveSheet, row, ColExcel.E.value(), (short)0, "HEADER", "Corporate Name:", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(receiveSheet, row, ColExcel.F.value(), (short)0, "", settlementReports.getCorporate().name, Cell.CELL_TYPE_STRING);
			row++;
			createCellOnRowOfSheet(receiveSheet, row, ColExcel.A.value(), (short)0, "HEADER", "Report Date:", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(receiveSheet, row, ColExcel.B.value(), (short)0, "", sdf.format(settlementReports.getReportDate()), Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(receiveSheet, row, ColExcel.E.value(), (short)0, "HEADER", "Currency:", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(receiveSheet, row, ColExcel.F.value(), (short)0, "", settlementReports.getCorporate().currency.code, Cell.CELL_TYPE_STRING);
			row++;
			for (int i = 0; i < HEADER_TITLE_RECEIVE.length; i++) {
				createCellOnRowOfSheet(receiveSheet, row, i, (short)500, "HEADER_TABLE", HEADER_TITLE_RECEIVE[i], Cell.CELL_TYPE_STRING);
				receiveSheet.setColumnWidth(i, colWidth[i]);
			}
			
			List<Transaction> transactions = QueryScheduller.getTransactionsSettlementReportReceive(settlementReports.getCorporate(), settlementReports.getReportDate());
			System.out.println("SIZE RECEIVE : "+transactions.size());
			row++;
			if (transactions != null && transactions.size() > 0){
				formulaSubTotal = ColExcel.N.name()+(row+1)+":";
				rangeReceive[0] = ColExcel.C.name()+(row+1)+":";
				rangeReceive[1] = ColExcel.H.name()+(row+1)+":";
				rangeReceive[2] = ColExcel.K.name()+(row+1)+":";
				rangeReceive[3] = ColExcel.L.name()+(row+1)+":";
				rangeReceive[4] = ColExcel.M.name()+(row+1)+":";
				for (Iterator<Transaction> iterator = transactions.iterator(); iterator.hasNext();) {
					Transaction transaction = (Transaction) iterator.next();
					ShareSetting receivingShareSetting = new ShareSetting().findById(transaction.shareReceiverId);
					
					if (receivingShareSetting != null){
						//Send
						createCellOnRowOfSheet(receiveSheet, row, ColExcel.A.value(), (short)0, "TABLE", transaction.getIdToken(), Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(receiveSheet, row, ColExcel.B.value(), (short)0, "TABLE", SEND_TRXTYPE, Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(receiveSheet, row, ColExcel.C.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(receiveSheet, row, ColExcel.D.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(receiveSheet, row, ColExcel.E.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(receiveSheet, row, ColExcel.F.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(receiveSheet, row, ColExcel.G.value(), (short)0, "TABLE", transaction.senderCurrency.code, Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(receiveSheet, row, ColExcel.H.value(), (short)0, "TABLE", transaction.senderAmount.toString(), Cell.CELL_TYPE_NUMERIC);
						createCellOnRowOfSheet(receiveSheet, row, ColExcel.I.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(receiveSheet, row, ColExcel.J.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(receiveSheet, row, ColExcel.K.value(), (short)0, "TABLE", transaction.feesTotal().toString(), Cell.CELL_TYPE_NUMERIC);
						createCellOnRowOfSheet(receiveSheet, row, ColExcel.L.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(receiveSheet, row, ColExcel.M.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(receiveSheet, row, ColExcel.N.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
						row++;
						//Receive
						createCellOnRowOfSheet(receiveSheet, row, ColExcel.A.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(receiveSheet, row, ColExcel.B.value(), (short)0, "TABLE", RECEIVE_TRXTYPE, Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(receiveSheet, row, ColExcel.C.value(), (short)0, "TABLE", transaction.channel.name, Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(receiveSheet, row, ColExcel.D.value(), (short)0, "TABLE", sdf.format(transaction.cashInTime.getTime()), Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(receiveSheet, row, ColExcel.E.value(), (short)0, "TABLE", transaction.beneficiaryAgent.getIdToken(), Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(receiveSheet, row, ColExcel.F.value(), (short)0, "TABLE", transaction.senderCountry.code, Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(receiveSheet, row, ColExcel.G.value(), (short)0, "TABLE", transaction.beneficiaryCurrency.code, Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(receiveSheet, row, ColExcel.H.value(), (short)0, "TABLE", transaction.beneficiaryAmount.toString(), Cell.CELL_TYPE_NUMERIC);
						createCellOnRowOfSheet(receiveSheet, row, ColExcel.I.value(), (short)0, "TABLE", transaction.forexReference.rate+"", Cell.CELL_TYPE_NUMERIC);
						createCellOnRowOfSheet(receiveSheet, row, ColExcel.J.value(), (short)0, "TABLE", transaction.forexReference.initialRate+"", Cell.CELL_TYPE_NUMERIC);
						
						String formulaFee = ColExcel.K.name()+row+MUL+ColExcel.J.name()+(row+1);
						createCellOnRowOfSheet(receiveSheet, row, ColExcel.K.value(), (short)0, "TABLE", formulaFee, Cell.CELL_TYPE_FORMULA);
						
						String formulaFeeShare = "";
						if (receivingShareSetting.getFeType() == 1){
							BigDecimal feePercentage = receivingShareSetting.getFeeShareSettingPercentage();
							formulaFeeShare = ColExcel.K.name()+row+MUL+ColExcel.J.name()+(row+1)+MUL+feePercentage+DIV+PERCENTAGE;
						}else{
							formulaFeeShare = receivingShareSetting.getFeeShareSettingFix().toString();
						}
						
						createCellOnRowOfSheet(receiveSheet, row, ColExcel.L.value(), (short)0, "TABLE", formulaFeeShare, Cell.CELL_TYPE_FORMULA);
						
						String formulaForexShare = "";
						if (receivingShareSetting.getFoType() == 1){
							BigDecimal spread = BigDecimal.valueOf(transaction.forexReference.forex.spread);
							formulaForexShare = "("+ColExcel.H.name()+row+MUL+spread+DIV+PERCENTAGE+")"+MUL+receivingShareSetting.getForexShareSettingPercentage()+MUL+ColExcel.J.name()+(row+1)+DIV+PERCENTAGE;
						}else{
							formulaForexShare = receivingShareSetting.getForexShareSettingFix().toString();
						}
						
						createCellOnRowOfSheet(receiveSheet, row, ColExcel.M.value(), (short)0, "TABLE", formulaForexShare, Cell.CELL_TYPE_FORMULA);
						String formulaGross = ColExcel.H.name()+(row+1)+ADD+ColExcel.L.name()+(row+1)+ADD+ColExcel.M+(row+1);
						createCellOnRowOfSheet(receiveSheet, row, ColExcel.N.value(), (short)0, "TABLE", formulaGross, Cell.CELL_TYPE_FORMULA);
						row++;
					}
				}
				rangeReceive[0] += ColExcel.C.name()+row;
				rangeReceive[1] += ColExcel.H.name()+row;
				rangeReceive[2] += ColExcel.K.name()+row;
				rangeReceive[3] += ColExcel.L.name()+row;
				rangeReceive[4] += ColExcel.M.name()+row;
				formulaSubTotal = "SUM("+formulaSubTotal+ColExcel.N.name()+row+")";
			}
			
			//Subtotal
			createCellOnRowOfSheet(receiveSheet, row, ColExcel.A.value(), (short)0, "TABLE_BOLD", "Subtotal", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(receiveSheet, row, ColExcel.B.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(receiveSheet, row, ColExcel.C.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(receiveSheet, row, ColExcel.D.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(receiveSheet, row, ColExcel.E.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(receiveSheet, row, ColExcel.F.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(receiveSheet, row, ColExcel.G.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(receiveSheet, row, ColExcel.H.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(receiveSheet, row, ColExcel.I.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(receiveSheet, row, ColExcel.J.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(receiveSheet, row, ColExcel.K.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(receiveSheet, row, ColExcel.L.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(receiveSheet, row, ColExcel.M.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(receiveSheet, row, ColExcel.N.value(), (short)0, "TABLE_BOLD", formulaSubTotal, Cell.CELL_TYPE_FORMULA);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.debug("Failed generate receive");
		}
	}
	
	@SuppressWarnings("static-access")
	public static void generateRefund() {
		try {
			int row = 0;
			String formulaSubTotal = "0";
			refundSheet = reportWorkbook.createSheet(REFUND_SHEET);
			createCellOnRowOfSheet(refundSheet, row, ColExcel.A.value(), (short)0, "TITLE", REFUND_TITLE, Cell.CELL_TYPE_STRING);
			refundSheet.addMergedRegion(new CellRangeAddress(row, row+1, ColExcel.A.value(), HEADER_TITLE_REFUND.length-1));
			row++;
			row++;
			createCellOnRowOfSheet(refundSheet, row, ColExcel.A.value(), (short)0, "HEADER", "Corporate Code:", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(refundSheet, row, ColExcel.B.value(), (short)0, "", settlementReports.getCorporate().code, Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(refundSheet, row, ColExcel.E.value(), (short)0, "HEADER", "Corporate Name:", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(refundSheet, row, ColExcel.F.value(), (short)0, "", settlementReports.getCorporate().name, Cell.CELL_TYPE_STRING);
			row++;
			createCellOnRowOfSheet(refundSheet, row, ColExcel.A.value(), (short)0, "HEADER", "Report Date:", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(refundSheet, row, ColExcel.B.value(), (short)0, "", sdf.format(settlementReports.getReportDate()), Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(refundSheet, row, ColExcel.E.value(), (short)0, "HEADER", "Currency:", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(refundSheet, row, ColExcel.F.value(), (short)0, "", settlementReports.getCorporate().currency.code, Cell.CELL_TYPE_STRING);
			row++;
			for (int i = 0; i < HEADER_TITLE_REFUND.length; i++) {
				createCellOnRowOfSheet(refundSheet, row, i, (short)500, "HEADER_TABLE", HEADER_TITLE_REFUND[i], Cell.CELL_TYPE_STRING);
				refundSheet.setColumnWidth(i, 4000);
			}
			List<Transaction> transactions = QueryScheduller.getTransactionsSettlementReportRefund(settlementReports.getCorporate(), settlementReports.getReportDate());
			row++;
			if (transactions != null && transactions.size() > 0){
				formulaSubTotal = ColExcel.N.name()+(row+1)+":";
				rangeRefund[0] = ColExcel.C.name()+(row+1)+":";
				rangeRefund[1] = ColExcel.H.name()+(row+1)+":";
				rangeRefund[2] = ColExcel.K.name()+(row+1)+":";
				rangeRefund[3] = ColExcel.L.name()+(row+1)+":";
				rangeRefund[4] = ColExcel.M.name()+(row+1)+":";
				rangeSendingRefund[0] = ColExcel.C.name()+(row+1)+":";
				rangeSendingRefund[1] = ColExcel.H.name()+(row+1)+":";
				rangeSendingRefund[2] = ColExcel.K.name()+(row+1)+":";
				rangeSendingRefund[3] = ColExcel.L.name()+(row+1)+":";
				rangeSendingRefund[4] = ColExcel.M.name()+(row+1)+":";				
				for (Iterator<Transaction> iterator = transactions.iterator(); iterator.hasNext();) {
					Transaction transaction = (Transaction) iterator.next();
					ShareSetting sendingShareSetting = new ShareSetting().findById(transaction.shareSenderId);
					
					if (sendingShareSetting != null){
						//Send
						createCellOnRowOfSheet(refundSheet, row, ColExcel.A.value(), (short)0, "TABLE", transaction.getIdToken(), Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(refundSheet, row, ColExcel.B.value(), (short)0, "TABLE", SEND_TRXTYPE, Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(refundSheet, row, ColExcel.C.value(), (short)0, "TABLE", transaction.channel.name+",", Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(refundSheet, row, ColExcel.D.value(), (short)0, "TABLE", sdf.format(transaction.cashInTime.getTime()), Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(refundSheet, row, ColExcel.E.value(), (short)0, "TABLE", transaction.agent.getIdToken(), Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(refundSheet, row, ColExcel.F.value(), (short)0, "TABLE", transaction.senderCountry.code, Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(refundSheet, row, ColExcel.G.value(), (short)0, "TABLE", transaction.senderCurrency.code, Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(refundSheet, row, ColExcel.H.value(), (short)0, "TABLE", transaction.senderAmount.toString(), Cell.CELL_TYPE_NUMERIC);
						createCellOnRowOfSheet(refundSheet, row, ColExcel.I.value(), (short)0, "TABLE", transaction.forexReference.rate+"", Cell.CELL_TYPE_NUMERIC);
						createCellOnRowOfSheet(refundSheet, row, ColExcel.J.value(), (short)0, "TABLE", transaction.forexReference.initialRate+"", Cell.CELL_TYPE_NUMERIC);
						createCellOnRowOfSheet(refundSheet, row, ColExcel.K.value(), (short)0, "TABLE", transaction.feesTotal().toString(), Cell.CELL_TYPE_NUMERIC);
						
						String formulaFeeShare = "";
						if (sendingShareSetting.getFeType() == 1){
							BigDecimal feePercentage = sendingShareSetting.getFeeShareSettingPercentage();
							formulaFeeShare = ColExcel.K.name()+(row+1)+MUL+feePercentage+DIV+PERCENTAGE;
						}else{
							formulaFeeShare = sendingShareSetting.getFeeShareSettingFix().toString();
						}
						createCellOnRowOfSheet(refundSheet, row, ColExcel.L.value(), (short)0, "TABLE", formulaFeeShare, Cell.CELL_TYPE_FORMULA);
						
						String formulaForexShare = "";
						if (sendingShareSetting.getFoType() == 1){
							BigDecimal spread = BigDecimal.valueOf(transaction.forexReference.forex.spread);
							formulaForexShare = "("+ColExcel.H.name()+(row+1)+MUL+spread+DIV+PERCENTAGE+")"+MUL+sendingShareSetting.getForexShareSettingPercentage()+DIV+PERCENTAGE;
						}else{
							formulaForexShare = sendingShareSetting.getForexShareSettingFix().toString();
						}
						
						createCellOnRowOfSheet(refundSheet, row, ColExcel.M.value(), (short)0, "TABLE", formulaForexShare, Cell.CELL_TYPE_FORMULA);
						String formulaGross = ColExcel.H.name()+(row+1)+ADD+"("+ColExcel.K.name()+(row+1)+SUB+ColExcel.L.name()+(row+1)+")"+SUB+ColExcel.M+(row+1);
						createCellOnRowOfSheet(refundSheet, row, ColExcel.N.value(), (short)0, "TABLE", formulaGross, Cell.CELL_TYPE_FORMULA);
						row++;
						
						//Receive
						createCellOnRowOfSheet(refundSheet, row, ColExcel.A.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(refundSheet, row, ColExcel.B.value(), (short)0, "TABLE", REFUND_TRXTYPE, Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(refundSheet, row, ColExcel.C.value(), (short)0, "TABLE", transaction.channel.name, Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(refundSheet, row, ColExcel.D.value(), (short)0, "TABLE", sdf.format(transaction.cashInTime.getTime()), Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(refundSheet, row, ColExcel.E.value(), (short)0, "TABLE", transaction.agent.getIdToken(), Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(refundSheet, row, ColExcel.F.value(), (short)0, "TABLE", transaction.senderCountry.code, Cell.CELL_TYPE_STRING);
						createCellOnRowOfSheet(refundSheet, row, ColExcel.G.value(), (short)0, "TABLE", transaction.senderCurrency.code, Cell.CELL_TYPE_STRING);
						String formulaSenderAmount = "("+transaction.senderAmount.toString()+MUL+"(-1))";
						createCellOnRowOfSheet(refundSheet, row, ColExcel.H.value(), (short)0, "TABLE", formulaSenderAmount, Cell.CELL_TYPE_FORMULA);
						createCellOnRowOfSheet(refundSheet, row, ColExcel.I.value(), (short)0, "TABLE", transaction.forexReference.rate+"", Cell.CELL_TYPE_NUMERIC);
						createCellOnRowOfSheet(refundSheet, row, ColExcel.J.value(), (short)0, "TABLE", transaction.forexReference.initialRate+"", Cell.CELL_TYPE_NUMERIC);
						createCellOnRowOfSheet(refundSheet, row, ColExcel.K.value(), (short)0, "TABLE", "0", Cell.CELL_TYPE_NUMERIC);
						createCellOnRowOfSheet(refundSheet, row, ColExcel.L.value(), (short)0, "TABLE", "0", Cell.CELL_TYPE_NUMERIC);
						
						String formulaForexShareRefund = "";
						if (sendingShareSetting.getFoType() == 1){
							BigDecimal spread = BigDecimal.valueOf(transaction.forexReference.forex.spread);
							formulaForexShareRefund = "("+ColExcel.H.name()+(row+1)+MUL+spread+DIV+PERCENTAGE+")"+MUL+sendingShareSetting.getForexShareSettingPercentage()+DIV+PERCENTAGE;
						}else{
							formulaForexShareRefund = sendingShareSetting.getForexShareSettingFix().toString()+MUL+"(-1)";
						}
						
						createCellOnRowOfSheet(refundSheet, row, ColExcel.M.value(), (short)0, "TABLE", formulaForexShareRefund, Cell.CELL_TYPE_FORMULA);
						String formulaGrossRefund = "0";
						if (transaction.status == transaction.STATUS_REFUNDED){
							formulaGrossRefund = ColExcel.H.name()+(row+1)+ADD+ColExcel.M+(row+1);
						}else if (transaction.status == transaction.STATUS_FULLREFUNDED){
							formulaGrossRefund = ColExcel.H.name()+(row+1)+ADD+ColExcel.M+(row+1);
						}
						createCellOnRowOfSheet(refundSheet, row, ColExcel.N.value(), (short)0, "TABLE", formulaGrossRefund, Cell.CELL_TYPE_FORMULA);
						row++;
					}
				}
				rangeRefund[0] += ColExcel.C.name()+row;
				rangeRefund[1] += ColExcel.H.name()+row;
				rangeRefund[2] += ColExcel.K.name()+row;
				rangeRefund[3] += ColExcel.L.name()+row;
				rangeRefund[4] += ColExcel.M.name()+row;
				
				rangeSendingRefund[0] += ColExcel.C.name()+row;
				rangeSendingRefund[1] += ColExcel.H.name()+row;
				rangeSendingRefund[2] += ColExcel.K.name()+row;
				rangeSendingRefund[3] += ColExcel.L.name()+row;
				rangeSendingRefund[4] += ColExcel.M.name()+row;
				
				formulaSubTotal = "SUM("+formulaSubTotal+ColExcel.N.name()+row+")";
			}
			//Subtotal
			createCellOnRowOfSheet(refundSheet, row, ColExcel.A.value(), (short)0, "TABLE_BOLD", "Subtotal", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(refundSheet, row, ColExcel.B.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(refundSheet, row, ColExcel.C.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(refundSheet, row, ColExcel.D.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(refundSheet, row, ColExcel.E.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(refundSheet, row, ColExcel.F.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(refundSheet, row, ColExcel.G.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(refundSheet, row, ColExcel.H.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(refundSheet, row, ColExcel.I.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(refundSheet, row, ColExcel.J.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(refundSheet, row, ColExcel.K.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(refundSheet, row, ColExcel.L.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(refundSheet, row, ColExcel.M.value(), (short)0, "TABLE", "", Cell.CELL_TYPE_STRING);
			createCellOnRowOfSheet(refundSheet, row, ColExcel.N.value(), (short)0, "TABLE_BOLD", formulaSubTotal, Cell.CELL_TYPE_FORMULA);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.debug("Failed generate refund");
		}
	}
	
	private static Map<String, CellStyle> createStyles(){
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
        CellStyle style;
        Font titleFont = reportWorkbook.createFont();
        titleFont.setFontHeightInPoints((short)18);
        titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style = reportWorkbook.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFont(titleFont);
        styles.put("TITLE", style);

        Font monthFont = reportWorkbook.createFont();
        monthFont.setFontHeightInPoints((short)11);
        monthFont.setColor(IndexedColors.BLACK.getIndex());
        monthFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        
        Font normalFont = reportWorkbook.createFont();
        normalFont.setFontHeightInPoints((short)11);
        normalFont.setColor(IndexedColors.BLACK.getIndex());
        normalFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
        
        style = reportWorkbook.createCellStyle();
        //style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        //style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        //style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setFont(monthFont);
        //style.setWrapText(true);
        styles.put("HEADER", style);

        style = reportWorkbook.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setWrapText(true);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setFont(monthFont);
        styles.put("HEADER_TABLE", style);

        style = reportWorkbook.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_GENERAL);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setWrapText(true);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setFont(normalFont);
        styles.put("TABLE", style);

        style = reportWorkbook.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_GENERAL);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setWrapText(true);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setFont(monthFont);
        styles.put("TABLE_BOLD", style);        
        
        style = reportWorkbook.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setDataFormat(reportWorkbook.createDataFormat().getFormat("0.00"));
        styles.put("formula_2", style);

        return styles;
    }
	
	public static void createCellOnRowOfSheet(Sheet sheet, int rowNo, int colNo, short heightRow, String style, String value, int cellType) {
		try {
			Row row = null;
			if (sheet.getRow(rowNo) != null){
				row = sheet.getRow(rowNo);
			}else{
				row = sheet.createRow(rowNo);
			}
			if (heightRow != 0)
				row.setHeight(heightRow);
			Cell cell = row.createCell(colNo);
			if (cellType == Cell.CELL_TYPE_NUMERIC){
				Double valDouble = Double.valueOf(value);
				cell.setCellValue(valDouble);
			}else if (cellType == Cell.CELL_TYPE_FORMULA){
				cell.setCellFormula(value);
			}else{
				cell.setCellValue(value);
			}
			cell.setCellType(cellType);
			if (!style.equals("") && styles.get(style) != null)
			cell.setCellStyle(styles.get(style));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    public static void pathXLS(String PathFile) {
        try {
            File cekDir = new File(PathFile);
            if (!cekDir.exists() || !cekDir.isDirectory()) {
                cekDir.mkdir();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
	
}
