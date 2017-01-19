package controllers;

import java.io.File;
import play.mvc.Controller;
import play.mvc.Result;

public class Logo extends Controller {
	
    public static Result index(String corporateCode) {
    	File defaultFile = new File("/apps/remittance/corporates/doku_small.png");
    	try {
    		if (corporateCode != null) {
	    		corporateCode = corporateCode.trim().toUpperCase();
	    		if (corporateCode.length() == 3) {
		    		models.Corporate corporate = models.Corporate.find.byId(corporateCode);
		    		if((corporate != null) && (corporate.configuration != null) && (corporate.configuration.logoPath != null) && (!corporate.configuration.logoPath.isEmpty())) {
		    			String path = "/apps/remittance/corporates/"+corporate.configuration.logoPath;
		    			File fp = new java.io.File(path);
		    			if((fp != null) && (fp.exists())) {
	    					return ok(fp);
		    			}
		    		}
	    		}
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok(defaultFile);
    }
	
}
