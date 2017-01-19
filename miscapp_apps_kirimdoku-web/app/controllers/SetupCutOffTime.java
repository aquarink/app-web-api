package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import be.objectify.deadbolt.java.actions.And;
import be.objectify.deadbolt.java.actions.Restrictions;
import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import views.html.tools.setupcutofftime;
import models.CutOffTime;
import models.SettlementReport;

@Security.Authenticated(SecuredMain.class)
@Restrictions({ @And("finance")})
public class SetupCutOffTime extends Controller {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	
	@SuppressWarnings("static-access")
	public static Result view() {
		CutOffTime cutOffTime = new CutOffTime().findActiveStatus();
		String cutOff = "";
		if (cutOffTime != null){
			Logger.debug(":: Cut Off Time Active Status");
			Logger.debug("-----------------------------");
			Logger.debug("ID : "+cutOffTime.getId());
			Logger.debug("Cut Off : "+cutOffTime.getCutOff());
			Logger.debug("Status : "+cutOffTime.getStatus());
			cutOff = cutOffTime.getCutOff();
		}
		
		return ok(setupcutofftime.render(cutOff));
	}
	
	@SuppressWarnings("static-access")
	public static Result save() {
		Form<CutOffTimeForm> cutOffTimeForm = form(CutOffTimeForm.class).bindFromRequest();
		
		CutOffTime cutOffTime = new CutOffTime().findActiveStatus();
		if (cutOffTime != null){
			Logger.debug(":: Cut Off Time Active Status");
			Logger.debug("-----------------------------");
			Logger.debug("ID : "+cutOffTime.getId());
			Logger.debug("Cut Off : "+cutOffTime.getCutOff());
			Logger.debug("Status : "+cutOffTime.getStatus());
		}
		
		if (cutOffTimeForm.get().getCutOffTime() != null && !cutOffTimeForm.get().getCutOffTime().equals("")){
			try {
				Date cutOffDate = sdf.parse(cutOffTimeForm.get().getCutOffTime());
				String newCutOff = sdf.format(cutOffDate);				
				CutOffTime newCutOffTime = null;
				if (cutOffTime == null || (cutOffTime != null && !cutOffTime.getCutOff().equals(newCutOff))){
					Logger.debug(":: New Cut Off Time : "+newCutOff);
					newCutOffTime = new CutOffTime();
					newCutOffTime.setCutOff(newCutOff);
					newCutOffTime.setStatus(1);
					newCutOffTime.setCreatedTime(new Date());
					newCutOffTime.save();
					Logger.debug(":: Success save Cut Off Time");
					SettlementReport.changeGenerateSettlement(sdf.format(cutOffDate));
				}
				
				if (cutOffTime != null && newCutOffTime != null){
					cutOffTime.setStatus(0);
					cutOffTime.update();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				Logger.debug(":: Failed to save Cut Off Time");
			}
		}else{
			Logger.debug("Invalid Save Cut Off Time");
		}
		
		return redirect(routes.SetupCutOffTime.view());
	}
	
	public static class CutOffTimeForm {
		private static String cutOffTime = "";

		public static String getCutOffTime() {
			return cutOffTime;
		}

		public static void setCutOffTime(String cutOffTime) {
			CutOffTimeForm.cutOffTime = cutOffTime;
		}
		
	}
	
}
