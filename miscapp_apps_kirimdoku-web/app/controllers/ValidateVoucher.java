package controllers;

import static play.data.Form.form;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import controllers.helpers.HashWithSHA1;
import models.Voucher;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import be.objectify.deadbolt.java.actions.And;
import be.objectify.deadbolt.java.actions.Restrictions;

@Security.Authenticated(SecuredMain.class)
@Restrictions({ @And("mainagent"), @And("supervisor"), @And("operator") })
public class ValidateVoucher extends Controller {

	public static Result getVoucher() {
		Form<Object> voucherForm = form(Object.class).bindFromRequest(request());
		String voucherValid = "STOP";
		if (voucherForm != null && voucherForm.data() != null && voucherForm.data().get("vouchercode") != null && voucherForm.data().get("channelcode") != null) {
			String voucherCode = voucherForm.data().get("vouchercode").trim().toUpperCase();
			String channelCode = voucherForm.data().get("channelcode").trim();
			
			Logger.debug(":: [ValidateVoucher] voucherCode : "+voucherCode);
			Logger.debug(":: [ValidateVoucher] channelCode : "+channelCode);
			
			if (validateRequest(voucherCode, channelCode)) {
				String hashVoucher;
				try {
					hashVoucher = HashWithSHA1.SHA1(voucherCode);
					Voucher availableVoucher = Voucher.find.where().eq("voucher_status", true).eq("code", hashVoucher).findUnique();
					if (availableVoucher != null) {
						voucherValid = "CONTINUE";
						Logger.debug(":: [ValidateVoucher] Available CONTINUE");
					}
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
					Logger.debug(":: [ValidateVoucher] NoSuchAlgorithmException");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					Logger.debug(":: [ValidateVoucher] UnsupportedEncodingException");
				}
			} else {
				Logger.debug(":: [ValidateVoucher] Invalid voucher code");
			}
		} else {
			Logger.debug(":: [ValidateVoucher] Invalid parameter request");
		}
		
		return ok(voucherValid);
	}
	
	protected static Boolean validateRequest(String voucherCode, String channelCode) {
		try {
			if (voucherCode == null || voucherCode.equals("") || voucherCode.length() != 6) 
				return false;
			if (channelCode == null || channelCode.equals("") || channelCode.length() != 2) 
				return false;
			if (!(channelCode.equals("21") || channelCode.equals("22"))) 
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
}
