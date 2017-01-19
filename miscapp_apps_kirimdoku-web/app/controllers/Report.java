package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import models.Transaction;

import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import util.pdf.PDF;
import views.html.report.list;
import views.html.report.pdf;
import views.html.report.transaction;
import be.objectify.deadbolt.java.actions.Restrictions;
import be.objectify.deadbolt.java.actions.And;

import com.avaje.ebean.Page;

import controllers.helpers.SessionHelper;
import controllers.tokens.AgentToken;
import controllers.tokens.TransactionToken;

@Security.Authenticated(SecuredMain.class)
@Restrictions({ @And("admin"), @And("finance"), @And("mainagent"), @And("supervisor"), @And("operator"), @And("admin_operasional") })
public class Report extends Controller {
	
	public static CellStyle style;
	
	public static class FilterForm {
		public models.Corporate corporate;
		public models.Country senderCountry;
		public models.Country beneficiaryCountry;
		public models.User agent;
		public String agentCode;
		public Date trxStartDate;
		public Date trxEndDate;
		public String transactionCode;
		public Long transactionId;
		public Integer transactionsStatus;
		public List<TransactionsStatus> transactionStatusList;
		
	    public final String validate() {
	    	if((this.agentCode != null) && (!this.agentCode.isEmpty())) {
	    		try {
		    		AgentToken token = AgentToken.fromString(agentCode);
		    		if (token != null) {
		    			agent = models.User.find.byId(token.id);
		    		}
	    		} catch(Exception e) {
	    		}
	    	}
	    	
	    	if((this.transactionCode != null) && (!this.transactionCode.isEmpty()) && this.transactionCode.startsWith("DK")) {
	    		try {
	    			TransactionToken token = TransactionToken.fromString(transactionCode);
		    		if (token != null) {
		    			this.transactionId = token.transactionId;
		    		}
	    		} catch(Exception e) {
	    		} finally {
	    			if (this.transactionId == null) {
	    				this.transactionId = 0L;
	    			}
	    		}
	    	} else if((this.transactionCode != null) && (!this.transactionCode.isEmpty()) && this.transactionCode.startsWith("A")) {
	    		try {
		    		AgentToken token = AgentToken.fromString(transactionCode);
		    		if (token != null) {
		    			agent = models.User.find.byId(token.id);
		    			this.transactionId = agent.id;
		    		}
	    		} catch(Exception e) {
	    		} finally {
	    			if (this.transactionId == null) {
	    				this.transactionId = 0L;
	    			}
	    		}
	    	} else if(this.transactionCode != null && !this.transactionCode.isEmpty()) {
	    		Logger.debug("Find Transaction Sender Name "+this.transactionCode);
	    	}
	    	return null;
	    }
	}
	
//	public static String getCountryCodeBasedOnCorporateCode(){
//		models.Corporate corporate = SessionHelper.getUser().corporate;
//		System.out.println(corporate);
//	}
	
	public static class TransactionsStatus {
		public int id;
		public String status;
		
		public TransactionsStatus(int id, String status) {
			this.id = id;
			this.status = status;
		}
	}
	
	public static List<TransactionsStatus> getTransactionStatusList() {
		List<TransactionsStatus> list = new ArrayList<Report.TransactionsStatus>();
		list.add(new TransactionsStatus(models.Transaction.STATUS_PENDING, models.Transaction.statusMap.get(models.Transaction.STATUS_PENDING)));
		list.add(new TransactionsStatus(models.Transaction.STATUS_LOCKED, models.Transaction.statusMap.get(models.Transaction.STATUS_LOCKED)));
		list.add(new TransactionsStatus(models.Transaction.STATUS_UNPAID, models.Transaction.statusMap.get(models.Transaction.STATUS_UNPAID)));
		list.add(new TransactionsStatus(models.Transaction.STATUS_REFUNDED, models.Transaction.statusMap.get(models.Transaction.STATUS_REFUNDED)));
		list.add(new TransactionsStatus(models.Transaction.STATUS_FULLREFUNDED, models.Transaction.statusMap.get(models.Transaction.STATUS_FULLREFUNDED)));
		list.add(new TransactionsStatus(models.Transaction.STATUS_PAID, models.Transaction.statusMap.get(models.Transaction.STATUS_PAID)));
		//list.add(new TransactionsStatus(models.Transaction.STATUS_FAILED, models.Transaction.statusMap.get(models.Transaction.STATUS_FAILED)));
		return list;
	}
	
    public static Result list(int page, String sortBy, String order) {//
    	Form<FilterForm> filterForm = form(FilterForm.class).bindFromRequest();
    	try{
    	filterForm.get().transactionStatusList = getTransactionStatusList();
    	} catch (Exception e) {
    		
    	}
    	Page<models.Transaction> rows = null;
    	if(!filterForm.data().isEmpty()) {
    		rows = models.Transaction.reportPage(SessionHelper.getUser(), page, 20, sortBy, order, filterForm.get());
    	}
    	
		//System.out.println("Ini isi nya-> "+SessionHelper.getCountryUser());
		
    	
    	
        return ok(
            list.render(filterForm,
                    rows,
                    sortBy, 
                    order,
                    SessionHelper.getCountryUser()
            )
        );
    }

    public static Result pdf() {
    	Form<FilterForm> filterForm = form(FilterForm.class).bindFromRequest();
        return PDF.ok(
                pdf.render(
                        models.Transaction.reportPage(SessionHelper.getUser(), 0, 1000000, "id", "desc", filterForm.get())
                )
        );
    }

    public static Result excel() {
    	Form<FilterForm> filterForm = form(FilterForm.class).bindFromRequest();
    	
        try{
            String path = "/tmp/blitzkrieg-export.xlsx";
            File file = new File(path);
            FileOutputStream fileOut = new FileOutputStream(file);

            List<models.Transaction> transactions = models.Transaction.reportPage(SessionHelper.getUser(), 0, 1000000, "id", "desc", filterForm.get()).getList();

            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Report");
            int rNum = 0;
            int cNum = 0;
            
            Font titleFont = wb.createFont();
            titleFont.setFontHeightInPoints((short)11);
            titleFont.setColor(IndexedColors.BLACK.getIndex());
            titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
            
            Font rowFont = wb.createFont();
            rowFont.setFontHeightInPoints((short)11);
            rowFont.setColor(IndexedColors.BLACK.getIndex());         
            
            CellStyle styles;
            styles = wb.createCellStyle();
            styles.setAlignment(CellStyle.ALIGN_CENTER);
            styles.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            styles.setWrapText(true);
            styles.setBorderRight(CellStyle.BORDER_THIN);
            styles.setRightBorderColor(IndexedColors.BLACK.getIndex());
            styles.setBorderLeft(CellStyle.BORDER_THIN);
            styles.setLeftBorderColor(IndexedColors.BLACK.getIndex());
            styles.setBorderTop(CellStyle.BORDER_THIN);
            styles.setTopBorderColor(IndexedColors.BLACK.getIndex());
            styles.setBorderBottom(CellStyle.BORDER_THIN);
            styles.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            styles.setFont(titleFont);
            
            style = wb.createCellStyle();
            style.setAlignment(CellStyle.ALIGN_LEFT);
            style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            style.setBorderRight(CellStyle.BORDER_THIN);
            style.setRightBorderColor(IndexedColors.BLACK.getIndex());
            style.setBorderLeft(CellStyle.BORDER_THIN);
            style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
            style.setBorderTop(CellStyle.BORDER_THIN);
            style.setTopBorderColor(IndexedColors.BLACK.getIndex());
            style.setBorderBottom(CellStyle.BORDER_THIN);
            style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            style.setFont(rowFont);            
            
            // Create Header
            String head[] = new String[] { 
//				"Date", "Agent ID", "TrxID", "Channel",
//				"Sender ID",  "Sender Country", "Sender amount", "Currency",
//				"Receiver ID", "Receiver Country", "Receiving Amount", "Currency",
//				"BaseRate", "Rate", "Fee", "Fee Currency", "Status", "Account Number", "Bank Name"
            	
            	"Agent ID",
            	"Transaction Date",
            	"Transaction ID",
            	"Channel",
            	"Sending Country",
            	"Receiving Country",
            	"Sender CID",
            	"Sender Name",
            	"Sender ID",
            	"Sender Phone Number",
            	"Receiver CID",
            	"Receiver Name",
            	"Receiver ID",
            	"Receiver Phone Number",
            	"Bank Account",
            	"Bank Account Number",
            	"Sending Currency",
            	"Sending Amount",
            	"Base Rate",
            	"Sell Rate",
            	"Fee",
            	"Receiving Currency",
            	"Receiving Amount",
            	"Additional Fee",
            	"Status",
            	"Reference"
            };
            List<String> header = Arrays.asList(head);
            
            XSSFRow rowH = sheet.createRow(rNum);
            rNum++;
            for(String h: header){
                XSSFCell cell = rowH.createCell(cNum);
                cell.setCellValue(h);
                cell.setCellStyle(styles);
                sheet.setColumnWidth(cNum, 3000);
                cNum++;
            }
            
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            cNum = 0;
            BigDecimal totalReceivingAmount = BigDecimal.ZERO;
            BigDecimal totalSendingAmount = BigDecimal.ZERO;
            BigDecimal totalTransactionFee = BigDecimal.ZERO;
            for(models.Transaction trx:transactions) {
                XSSFRow row = sheet.createRow(rNum);

                String accountNumber = "";
                String accountBank = "";
                if (trx.channel.code.equals("04")) {
                	accountNumber = trx.beneficiaryWalletId != null ? trx.beneficiaryWalletId : "";
                	accountBank = trx.beneficiaryWalletName != null ? trx.beneficiaryWalletName : "";
                } else if (trx.channel.code.equals("10")) {
                	accountNumber = trx.billPayment() != null ? trx.billPayment().accountNumber : "";
                	accountBank = trx.billPayment() != null ? trx.billPayment().selectDenomName+" "+trx.billPayment().selectOperatorName : "";
                } else {
                    accountNumber = trx.beneficiaryAccount != null ? trx.beneficiaryAccount.number : "";
                    accountBank = trx.beneficiaryAccount != null ? trx.beneficiaryAccount.bank.name : "";
                }
                
                createCell(row, 0, trx.agent.getIdToken());
                if(trx.cashInTime != null) {
                	createCell(row, 1, df.format(trx.cashInTime.getTime()));
                }
                createCell(row, 2, trx.getIdToken());
                createCell(row, 3, trx.channel.name);
                createCell(row, 4, trx.senderCountry.name);
                createCell(row, 5, trx.beneficiaryCountry.name);
                createCell(row, 6, trx.sender.getIdToken());
                createCell(row, 7, trx.sender.fullName());
                createCell(row, 8, trx.sender.personalId);
                createCell(row, 9, trx.sender.phoneNumber);
                createCell(row,10, trx.beneficiary.getIdToken());
                createCell(row,11, trx.beneficiary.fullName());
                createCell(row,12, trx.beneficiary.personalId);
                createCell(row,13, trx.beneficiary.phoneNumber);
                createCell(row,14, accountBank);
                createCell(row,15, accountNumber);
                createCell(row,16, trx.senderCurrency.code);
                createCell(row,17, trx.senderAmountFormat());
                createCell(row,18, trx.forexReference.initialRateFormat());
                createCell(row,19, trx.forexReference.rateFormat());
                createCell(row,20, trx.feesTotalFormat());
                createCell(row,21, trx.beneficiaryCurrency.code);
                createCell(row,22, trx.beneficiaryAmountFormat());
                createCell(row,23, trx.additionalFeesTotalFormat());
                createCell(row,24, trx.statusText());
                createCell(row,25, trx.senderNote);
                totalReceivingAmount = totalReceivingAmount.add(trx.beneficiaryAmount);
                totalSendingAmount = totalSendingAmount.add(trx.senderAmount);
                totalTransactionFee = totalTransactionFee.add(trx.additionalFeesTotal());
                rNum++;
            }
            
            rNum++;

            
            //FOOTER
        	XSSFRow rowFooter0 = sheet.createRow(rNum);
            XSSFCell cell01 = rowFooter0.createCell(0);
            cell01.setCellValue("");
            cell01.setCellStyle(style);
            sheet.setColumnWidth(0, 6000);
            XSSFCell cell02 = rowFooter0.createCell(1);
            cell02.setCellValue("");
            cell02.setCellStyle(style);
            sheet.setColumnWidth(1, 4000);
            rNum++;
            XSSFRow rowFooter1 = sheet.createRow(rNum);
            XSSFCell cell11 = rowFooter1.createCell(0);
            cell11.setCellValue("Transaction Volume");
            cell11.setCellStyle(style);
            sheet.setColumnWidth(0, 6000);
            XSSFCell cell12 = rowFooter1.createCell(1);
            cell12.setCellValue(transactions.size());
            cell12.setCellStyle(style);
            sheet.setColumnWidth(1, 4000);
            rNum++;
            XSSFRow rowFooter2 = sheet.createRow(rNum);
            XSSFCell cell21 = rowFooter2.createCell(0);
            cell21.setCellValue("Transacted Receiving Amount");
            cell21.setCellStyle(style);
            sheet.setColumnWidth(0, 6000);
            XSSFCell cell22 = rowFooter2.createCell(1);
            cell22.setCellValue(Transaction.formatNumber(totalReceivingAmount.setScale(6, RoundingMode.FLOOR)));
            cell22.setCellStyle(style);
            sheet.setColumnWidth(1, 4000);
            rNum++;
            XSSFRow rowFooter3 = sheet.createRow(rNum);
            XSSFCell cell31 = rowFooter3.createCell(0);
            cell31.setCellValue("Transacted Sending Amount");
            cell31.setCellStyle(style);
            sheet.setColumnWidth(0, 6000);
            XSSFCell cell32 = rowFooter3.createCell(1);
            cell32.setCellValue(Transaction.formatNumber(totalSendingAmount.setScale(6, RoundingMode.FLOOR)));
            cell32.setCellStyle(style);
            sheet.setColumnWidth(1, 4000);
            rNum++;
            XSSFRow rowFooter4 = sheet.createRow(rNum);
            XSSFCell cell41 = rowFooter4.createCell(0);
            cell41.setCellValue("Transfer Fee");
            cell41.setCellStyle(style);
            sheet.setColumnWidth(0, 6000);
            XSSFCell cell42 = rowFooter4.createCell(1);
            cell42.setCellValue(Transaction.formatNumber(totalTransactionFee.setScale(6, RoundingMode.FLOOR)));
            cell42.setCellStyle(style);
            sheet.setColumnWidth(1, 4000);
            rNum++;
            
            BigDecimal grandTotal = BigDecimal.ZERO;
            grandTotal = grandTotal.add(totalReceivingAmount);
            grandTotal = grandTotal.add(totalTransactionFee);
            XSSFRow rowFooter5 = sheet.createRow(rNum);
            XSSFCell cell51 = rowFooter5.createCell(0);
            cell51.setCellValue("Grand Total");
            cell51.setCellStyle(style);
            sheet.setColumnWidth(0, 6000);
            XSSFCell cell52 = rowFooter5.createCell(1);
            cell52.setCellValue(Transaction.formatNumber(grandTotal.setScale(6, RoundingMode.FLOOR)));
            cell52.setCellStyle(style);
            sheet.setColumnWidth(1, 4000);
            rNum++;
        	XSSFRow rowFooter6 = sheet.createRow(rNum);
            XSSFCell cell61 = rowFooter6.createCell(0);
            cell61.setCellValue("");
            cell61.setCellStyle(style);
            sheet.setColumnWidth(0, 6000);
            XSSFCell cell62 = rowFooter6.createCell(1);
            cell62.setCellValue("");
            cell62.setCellStyle(style);
            sheet.setColumnWidth(1, 4000);
            rNum++;
        	XSSFRow rowFooter7 = sheet.createRow(rNum);
            XSSFCell cell71 = rowFooter7.createCell(0);
            cell71.setCellValue("");
            cell71.setCellStyle(style);
            sheet.setColumnWidth(0, 6000);
            XSSFCell cell72 = rowFooter7.createCell(1);
            cell72.setCellValue("");
            cell72.setCellStyle(style);
            sheet.setColumnWidth(1, 4000);
            rNum++;
        	XSSFRow rowFooter8 = sheet.createRow(rNum);
            XSSFCell cell81 = rowFooter8.createCell(0);
            cell81.setCellValue("");
            cell81.setCellStyle(style);
            sheet.setColumnWidth(0, 6000);
            XSSFCell cell82 = rowFooter8.createCell(1);
            cell82.setCellValue("");
            cell82.setCellStyle(style);
            sheet.setColumnWidth(1, 4000);
            rNum++;
            
            wb.write(fileOut);
            fileOut.close();
            response().setHeader("Cache-Control", "public");
            response().setHeader("Content-Description", "File Transfer");
            response().setHeader("Content-Disposition", "attachment; filename=Blitzkrieg-Transaction-Report-"+filterForm.apply("corporate.code").valueOr("")+".xlsx");
            response().setHeader("Content-Transfer-Encoding", "binary");
            return ok(IOUtils.toByteArray(new FileInputStream(new java.io.File(path)))).as("application/excel");
        } catch(Exception e) {
        	e.printStackTrace();
            Logger.debug(e.getMessage());
			return ok("Error "+e);
        }
        //return ok("Error");
    }

    private static XSSFCell createCell(XSSFRow row, int column, String value){
	    XSSFCell cell = row.createCell(column);
        if(value != null) {
            cell.setCellValue(value);
        }
        cell.setCellStyle(style);
	    return cell;
	}
//    private static XSSFCell createCell(XSSFRow row, int column, BigDecimal value){
//	    XSSFCell cell = row.createCell(column);
//	    cell.setCellValue(value.doubleValue());
//	    cell.setCellStyle(style);
//	    return cell;
//    }
//    private static XSSFCell createCell(XSSFRow row, int column, Calendar value){
//	    XSSFCell cell = row.createCell(column);
//        if(value != null) {
//            cell.setCellValue(value);
//        }
//        cell.setCellStyle(style);
//	    return cell;
//    }

	public static Result transaction() {
        return ok(transaction.render());
    }

}
