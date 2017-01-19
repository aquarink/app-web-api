package controllers;

import be.objectify.deadbolt.java.actions.And;
import be.objectify.deadbolt.java.actions.Restrictions;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import play.data.*;
import static play.data.Form.*;
import views.html.corporate.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.Channel;

import org.apache.commons.io.IOUtils;

@Security.Authenticated(SecuredMain.class)
public class Corporate extends Controller {
	
    @Restrictions({@And("admin"), @And("finance"), @And("admin_finance"), @And("admin_operasional")})
    public static Result admin_index(int page, String sortBy, String order, String filter) {
        return ok(
                admin_index.render(
                        models.Corporate.page(page, 10, sortBy, order, filter),
                        sortBy, order, filter
                )
        );
    }

    @Restrictions({@And("admin")})
    public static Result admin_new() {
        Form<models.Corporate> corporateForm = form(models.Corporate.class);
        return ok(
                admin_new.render(corporateForm)
        );
    }

    @Restrictions({@And("admin")})
    public static Result admin_create() {
        Form<models.Corporate> corporateForm = form(models.Corporate.class).bindFromRequest();

        if(corporateForm.hasErrors()) {
            return badRequest(admin_new.render(corporateForm));
        }
        String corporateCode = corporateForm.get().code.toUpperCase();
        models.Corporate existingCorporate = models.Corporate.find.byId(corporateCode);
        if(existingCorporate != null) {
        	flash("error", "Corporate code '"+corporateCode+"' has already exist, please pick another code");
            return badRequest(admin_new.render(corporateForm));
        }

        models.Corporate corporate = corporateForm.get();
        corporate.code = corporateCode;
        corporate.creditLimit = BigDecimal.ZERO;
        corporate.creditAlertLimit = BigDecimal.ZERO;
        corporate.customerSendLimit = BigDecimal.ZERO;
        corporate.status = models.Corporate.Status.ACTIVE;
        corporate.permission = 0;
        corporate.encryptionKey = new BigInteger(80, new SecureRandom()).toString(32);
        corporate.save();
        return redirect(
//                routes.Corporate.admin_index(0, "", "", "")
                routes.Corporate.admin_edit(corporate.code)
        );
    }
    
    @Restrictions({@And("admin")})
    public static Result admin_edit(String code) {
        models.Corporate corporate = models.Corporate.find.byId(code);
        Form<models.Corporate> corporateForm = form(models.Corporate.class).fill(corporate);
		List<Channel> channels = Channel.find.all();
		Map<String, String> mapChannel = new HashMap<String, String>();
		try{
			String channelCode = corporate.configuration.channelCode;
			if (channelCode != null && !channelCode.trim().equals(""))
				for(String itemCode : channelCode.split(";")) {
					mapChannel.put(itemCode, itemCode);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return ok(
                admin_edit.render(corporateForm, channels, mapChannel)
        );
    }
    
    @Restrictions({@And("admin")})
    public static Result admin_update(String code) {
        Form<models.Corporate> corporateForm = form(models.Corporate.class).bindFromRequest();
        
        String corpConfigChannelCode = "";
        
        int channelCount = Channel.find.all().size();
        System.out.println("map : "+corporateForm.data().toString());
        for (int i = 0; i < channelCount; i++) {
        	if (corporateForm.data().get("channel["+i+"]") != null) {
        		corpConfigChannelCode += corporateForm.data().get("channel["+i+"]")+";";
        		System.out.println("Channel Checked : "+corporateForm.data().get("channel["+i+"]"));
        	}
		}
        
        int permission = Integer.parseInt(corporateForm.field("permission_send").valueOr("0")) | Integer.parseInt(corporateForm.field("permission_receive").valueOr("0"));
        corporateForm.data().put("permission", String.valueOf(permission));
        
        if(corporateForm.hasErrors()) {
            return badRequest(admin_edit.render(corporateForm, null, null));
        }
        
//        models.Corporate corporate = models.Corporate.find.byId(code);
        models.Corporate corporate = corporateForm.get();
        
        //Stevano Added 19 Jun 2015
        if (corpConfigChannelCode != ""){
        	corporate.configuration.channelCode = corpConfigChannelCode;
        }
        //End Added
        
        try {
            //Upload Process
            Http.MultipartFormData body = request().body().asMultipartFormData();
            Http.MultipartFormData.FilePart logo = body.getFile("configuration.logoPath");

            if(logo != null) {
            	System.out.println("Logo Not Null");
                String fileName = corporate.code+"_"+logo.getFilename();
                String contentType = logo.getContentType();
                File file = logo.getFile();
                //String imgPath = Play.application().path().toString() + "/public/images/corporates/" + fileName;
                String imgPath = "/apps/remittance/corporates/"+fileName;
                if(file.renameTo(new File(imgPath))) {
	                corporate.configuration.logoPath = fileName;
	                Logger.debug(fileName);
	                Logger.debug(contentType);
                }
            } else {
            	System.out.println("Logo Null");
            }

//            corporate.name = corporateForm.get().name;
//            corporate.tradeName = corporateForm.get().tradeName;
            corporate.permission = permission;
            if(corporate.operation.id > 0) {
            	corporate.operation.update();
            } else {
            	corporate.operation.save();
            }
            if(corporate.finance.id > 0) {
            	corporate.finance.update();
            } else {
            	corporate.finance.save();
            }
            if(corporate.settlement.id > 0) {
            	corporate.settlement.update();
            } else {
            	corporate.settlement.save();
            }            
            corporate.update();
            flash("success", "Corporate " + corporateForm.get().name + " has been updated");
        } catch(Exception e) {
            e.printStackTrace();
            flash("error", e.getMessage());
        }

        return redirect(
                routes.Corporate.admin_index(0, "code", "asc", "")
        );
    }
    
    @Restrictions({@And("admin"), @And("finance"), @And("mainagent"), @And("supervisor"), @And("operator"), @And("admin_operasional")})
    public static Result show(String code) {
        models.Corporate corporate = models.Corporate.find.fetch("country").where().eq("code",code).findUnique();
        return ok(show.render(corporate));
    }
    
    public static Result logo(String corporateCode, String size) {
		models.Corporate corporate = models.Corporate.find.byId(corporateCode);
//		String path = "http://"+request().host()+"/assets/images/doku_small.png";
//		if((corporate != null) && (corporate.logoPath != null) && (!corporate.logoPath.isEmpty())) {
//			path = "http://"+request().host()+"/assets/images/"+corporate.logoPath;
//		}
//		return redirect(path);
		
		if((corporate != null) && (corporate.configuration != null) && (corporate.configuration.logoPath != null) && (!corporate.configuration.logoPath.isEmpty())) {
			String path = "/apps/remittance/corporates/"+corporate.configuration.logoPath;
			System.out.println("Path : "+path);
			
			File fp = new java.io.File(path);
			if((fp != null) && (fp.exists())) {
				response().setHeader("Cache-Control", "public");
//				response().setHeader("Content-Description", "File Transfer");
//				response().setHeader("Content-Disposition", "attachment; filename=download.xml");
				response().setHeader("Content-Type",  "image/jpeg");
				response().setHeader("Content-Transfer-Encoding", "binary");
				try {
					InputStream is = new FileInputStream(fp);
					byte[] buffs = IOUtils.toByteArray(is);
					return ok(buffs);
				} catch (Exception e) {
				}
			}
		}
		String path = "http://"+request().host()+"/assets/images/doku_small.png";
		return redirect(path);
	}
    
    //Stevano Added 18 Jun 2015
    @Restrictions({@And("mainagent"), @And("supervisor"), @And("operator")})
    public static String getCountryByUserCorporateCode(String code){
    	models.Corporate corporate = models.Corporate.find.byId(code);
    	if (corporate != null){
    		return corporate.country.code;
    	}
    	return null;
    }
    //End Added
}
