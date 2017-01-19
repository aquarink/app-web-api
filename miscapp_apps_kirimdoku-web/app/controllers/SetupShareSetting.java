package controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import models.Corporate;
import models.ShareSetting;
import be.objectify.deadbolt.java.actions.And;
import be.objectify.deadbolt.java.actions.Restrictions;
import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import views.html.tools.sharesetting;

@Security.Authenticated(SecuredMain.class)
@Restrictions({ @And("admin"), @And("finance")})
public class SetupShareSetting extends Controller {
	
	public static Result view(String corporate) {
		Corporate corporates = null;
		if (corporate != null && !corporate.equals("")){
			corporates = Corporate.find.where().eq("code", corporate).findUnique();
		}else{
			return redirect(routes.Main.index());
		}
		
		ShareSetting shareSetting = new ShareSetting().findActiveStatus(corporates);
		Integer foType = 1;
		BigDecimal forexShareSettingPercentage = BigDecimal.ZERO.setScale(2,RoundingMode.FLOOR);
		BigDecimal forexShareSettingFix = BigDecimal.ZERO.setScale(2,RoundingMode.FLOOR);
		Integer feType = 1;
		BigDecimal feeShareSettingPercentage = BigDecimal.ZERO.setScale(2,RoundingMode.FLOOR);
		BigDecimal feeShareSettingFix = BigDecimal.ZERO.setScale(2,RoundingMode.FLOOR);
		
		if (shareSetting != null){
			foType = shareSetting.getFoType();
			forexShareSettingPercentage = shareSetting.getForexShareSettingPercentage();
			forexShareSettingFix = shareSetting.getForexShareSettingFix();
			feType = shareSetting.getFeType();
			feeShareSettingPercentage = shareSetting.getFeeShareSettingPercentage();
			feeShareSettingFix = shareSetting.getFeeShareSettingFix();
			
			Logger.debug(":: Get From DB ::");
			Logger.debug("-----------------");
			Logger.debug("Fo Type : "+foType);
			Logger.debug("Forex Share Setting Percentage (%) : "+forexShareSettingPercentage);
			Logger.debug("Forex Share Setting Fix : "+forexShareSettingFix);
			Logger.debug("Fe Type : "+feType);
			Logger.debug("Fee Share Setting Percentage (%) : "+feeShareSettingPercentage);
			Logger.debug("Fee Share Setting Fix : "+feeShareSettingFix);
		}
		
		return ok(sharesetting.render(
				foType,
				forexShareSettingPercentage,
				forexShareSettingFix,
				feType,
				feeShareSettingPercentage,
				feeShareSettingFix,
				corporates
				));
	}	
	
	@SuppressWarnings("static-access")
	public static Result save() {
		Form<ShareSettingForm> shareSettingForm = form(ShareSettingForm.class).bindFromRequest();
		Integer foType = 1;
		BigDecimal forexShareSettingPercentage = BigDecimal.ZERO.setScale(2,RoundingMode.FLOOR);
		BigDecimal forexShareSettingFix = BigDecimal.ZERO.setScale(2,RoundingMode.FLOOR);
		Integer feType = 1;
		BigDecimal feeShareSettingPercentage = BigDecimal.ZERO.setScale(2,RoundingMode.FLOOR);
		BigDecimal feeShareSettingFix = BigDecimal.ZERO.setScale(2,RoundingMode.FLOOR);
		
		Logger.debug(":: Get From Post ::");
		Logger.debug("-------------------");
		Logger.debug("Fo Type : "+shareSettingForm.get().getFo());
		Logger.debug("Forex Share Setting Percentage (%) : "+shareSettingForm.get().getForexShareSettingPercentage());
		Logger.debug("Forex Share Setting Fix : "+shareSettingForm.get().getForexShareSettingFix());
		Logger.debug("Fe Type : "+shareSettingForm.get().getFe());
		Logger.debug("Fee Share Setting Percentage (%) : "+shareSettingForm.get().getFeeShareSettingPercentage());
		Logger.debug("Fee Share Setting Fix : "+shareSettingForm.get().getFeeShareSettingFix());
		
		Corporate corporates = null;
		if (shareSettingForm.get().getCorporate() != null && !shareSettingForm.get().getCorporate().equals("")){
			corporates = Corporate.find.where().eq("code", shareSettingForm.get().getCorporate()).findUnique();
		}else{
			return redirect(routes.Main.index());
		}
		
		ShareSetting shareSetting = new ShareSetting().findActiveStatus(corporates);
		
		if (shareSettingForm.get().getFo() != null && shareSettingForm.get().getFo() > 0 && shareSettingForm.get().getFe() != null && shareSettingForm.get().getFe() > 0){
			try {
				try {
					if (shareSettingForm.get().getFo() != null){
						foType = shareSettingForm.get().getFo();
					}
					if (shareSettingForm.get().getForexShareSettingPercentage() != null && shareSettingForm.get().getFo() == 1){
						forexShareSettingPercentage = shareSettingForm.get().getForexShareSettingPercentage().setScale(2,RoundingMode.FLOOR);
					}
					if (shareSettingForm.get().getForexShareSettingFix() != null && shareSettingForm.get().getFo() == 2){
						forexShareSettingFix = shareSettingForm.get().getForexShareSettingFix().setScale(2,RoundingMode.FLOOR);
					}
					if (shareSettingForm.get().getFe() != null){
						feType = shareSettingForm.get().getFe();
					}
					if (shareSettingForm.get().getFeeShareSettingPercentage() != null && shareSettingForm.get().getFe() == 1){
						feeShareSettingPercentage = shareSettingForm.get().getFeeShareSettingPercentage().setScale(2,RoundingMode.FLOOR);
					}
					if (shareSettingForm.get().getFeeShareSettingFix() != null && shareSettingForm.get().getFe() == 2){
						feeShareSettingFix = shareSettingForm.get().getFeeShareSettingFix().setScale(2,RoundingMode.FLOOR);
					}
				} catch (Exception e) {
					e.printStackTrace();
					Logger.debug("Failed get paramter share setting");
				}
				
				String shareSettings = "";
				if (shareSetting != null){
					shareSettings = shareSetting.getForexShareSettingPercentage().toString()+"_"+
							shareSetting.getForexShareSettingFix().toString()+"_"+
							shareSetting.getFeeShareSettingPercentage().toString()+"_"+
							shareSetting.getFeeShareSettingFix().toString();
				}
				String shareSettingp = shareSettingForm.get().getForexShareSettingPercentage().toString()+"_"+
						shareSettingForm.get().getForexShareSettingFix().toString()+"_"+
						shareSettingForm.get().getFeeShareSettingPercentage().toString()+"_"+
						shareSettingForm.get().getFeeShareSettingFix().toString();
				
				ShareSetting newShareSetting = null;
				if (shareSetting == null || (shareSetting != null && (!shareSettings.equals(shareSettingp)))){
					Logger.debug(":: Save To DB ::");
					Logger.debug("----------------");
					Logger.debug("Forex Share Setting Percentage (%) : "+forexShareSettingPercentage);
					Logger.debug("Forex Share Setting Fix : "+forexShareSettingFix);
					Logger.debug("Fee Share Setting Percentage (%) : "+feeShareSettingPercentage);
					Logger.debug("Fee Share Setting Fix : "+feeShareSettingFix);
					
					//Create new share setting
					newShareSetting = new ShareSetting();
					newShareSetting.setFoType(foType);
					newShareSetting.setForexShareSettingPercentage(forexShareSettingPercentage);
					newShareSetting.setForexShareSettingFix(forexShareSettingFix);
					newShareSetting.setFeType(feType);
					newShareSetting.setFeeShareSettingPercentage(feeShareSettingPercentage);
					newShareSetting.setFeeShareSettingFix(feeShareSettingFix);
					newShareSetting.setStatus(1);
					newShareSetting.setCreatedTime(new Date());
					newShareSetting.setCorporate(corporates);
					newShareSetting.save();
					Logger.debug("Success save share setting");
				}
				
				if (shareSetting != null && newShareSetting != null){
					//Disable old share setting, save to database
					shareSetting.setStatus(0);
					shareSetting.update();
					Logger.debug("Update old share setting");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				Logger.debug("Failed to save Cut Off Time");
			}
		}else{
			Logger.debug("Invalid Parameter Save Share Setting");
		}
		
		return redirect(routes.SetupShareSetting.view(shareSettingForm.get().getCorporate()));
	}
	
	public static class ShareSettingForm {
		private static String corporate;
		private static Integer fo;
		private static Integer fe;
		private static BigDecimal forexShareSettingPercentage = BigDecimal.ZERO.setScale(2,RoundingMode.FLOOR);
		private static BigDecimal forexShareSettingFix = BigDecimal.ZERO.setScale(2,RoundingMode.FLOOR);
		private static BigDecimal feeShareSettingPercentage = BigDecimal.ZERO.setScale(2,RoundingMode.FLOOR);
		private static BigDecimal feeShareSettingFix = BigDecimal.ZERO.setScale(2,RoundingMode.FLOOR);
		public static BigDecimal getForexShareSettingPercentage() {
			return forexShareSettingPercentage;
		}
		public static void setForexShareSettingPercentage(
				BigDecimal forexShareSettingPercentage) {
			ShareSettingForm.forexShareSettingPercentage = forexShareSettingPercentage.setScale(2,RoundingMode.FLOOR);
		}
		public static BigDecimal getForexShareSettingFix() {
			return forexShareSettingFix;
		}
		public static void setForexShareSettingFix(BigDecimal forexShareSettingFix) {
			ShareSettingForm.forexShareSettingFix = forexShareSettingFix.setScale(2,RoundingMode.FLOOR);
		}
		public static BigDecimal getFeeShareSettingPercentage() {
			return feeShareSettingPercentage;
		}
		public static void setFeeShareSettingPercentage(
				BigDecimal feeShareSettingPercentage) {
			ShareSettingForm.feeShareSettingPercentage = feeShareSettingPercentage.setScale(2,RoundingMode.FLOOR);
		}
		public static BigDecimal getFeeShareSettingFix() {
			return feeShareSettingFix;
		}
		public static void setFeeShareSettingFix(BigDecimal feeShareSettingFix) {
			ShareSettingForm.feeShareSettingFix = feeShareSettingFix.setScale(2,RoundingMode.FLOOR);
		}
		public static Integer getFo() {
			return fo;
		}
		public static void setFo(Integer fo) {
			ShareSettingForm.fo = fo;
		}
		public static Integer getFe() {
			return fe;
		}
		public static void setFe(Integer fe) {
			ShareSettingForm.fe = fe;
		}
		public static String getCorporate() {
			return corporate;
		}
		public static void setCorporate(String corporate) {
			ShareSettingForm.corporate = corporate;
		}
	}
}
