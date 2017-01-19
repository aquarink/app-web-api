package controllers;

import static play.data.Form.form;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;

import models.Label;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.suspicious.admin_list;
import au.com.bytecode.opencsv.CSVWriter;

import com.avaje.ebean.Page;
import com.avaje.ebean.annotation.Transactional;

@Security.Authenticated(SecuredAdmin.class)
public class Suspicious extends Controller {

	@Transactional(readOnly=true)
	public static Result admin_index(int page, String sortBy, String order, String filter) {
		Form<models.Transaction> filterForm = form(models.Transaction.class).bindFromRequest();
		
		List<Label> labels = Label.find.where().eq("type", Label.TYPE.TRANSACTION).findList();
		Page<models.Transaction> rows = models.Transaction.pageWithLabels(labels, page, 10, sortBy, order, filter);
		return ok(admin_list.render(filterForm, rows, sortBy, order, filter));
	}
	
	public static Result csv(String sortBy, String order, String filter) {
		String csvExportPath = "/tmp/kirimdoku-export-str.csv";
		
		Form<models.Transaction> filterForm = form(models.Transaction.class).bindFromRequest();
		
		List<Label> labels = Label.find.where().eq("type", Label.TYPE.TRANSACTION).findList();
		List<models.Transaction> rows = models.Transaction.pageWithLabels(labels, 0, 10000, sortBy, order, filter).getList();
		
		if(rows.isEmpty()) return notFound();
		
		CSVWriter csvWriter;
		try {
			csvWriter = new CSVWriter(new FileWriter(csvExportPath));
			for (models.Transaction t : rows) {
				String labelsStr = "";
				for(models.Label l : t.labels) {
					if (!labelsStr.isEmpty()) labelsStr += ", "; 
					labelsStr += l.description;
				}
				String[] cs = {t.getIdToken(), t.senderCountry.name, t.sender.idToken, t.sender.fullName(), t.beneficiaryCountry.name, t.beneficiaryCity, t.beneficiary.idToken, t.beneficiary.fullName(), t.senderAmountFormat(), t.senderNote, t.statusText(), labelsStr};
				csvWriter.writeNext(cs);
			}
			csvWriter.close();
			return ok(IOUtils.toByteArray(new FileInputStream(new java.io.File(csvExportPath)))).as("text/csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return internalServerError();
	}
}
