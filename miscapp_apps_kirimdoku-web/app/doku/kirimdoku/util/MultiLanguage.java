package doku.kirimdoku.util;

import java.io.File;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

import controllers.helpers.SessionHelper;
import doku.kirimdoku.enums.ELanguages;

public class MultiLanguage {
	
	private static final String LanguagePath = play.Play.application().configuration().getString("applications.langs.path");
	static final Object LOCK4 = new Object();
	private static transient PropertiesConfiguration langConfig = null;
	
	private static PropertiesConfiguration getLangConfig(String langId) {
		String languagesId = "EN";
		try {
			try {
				if (langId == null || langId.equals("null")) {
					langId = languagesId;
					SessionHelper.putLangId(languagesId);
				}
				languagesId = ELanguages.getLookup().get(langId.toUpperCase()).code();
			} catch (Exception e) {
				SessionHelper.putLangId(languagesId);
				System.out.println("Failed get lang config : "+e.getMessage());
			}
			String fileConfig = LanguagePath+languagesId.toLowerCase()+".properties";
	    	langConfig = createConfigFile(fileConfig, false);
		} catch (Exception e) {
			System.out.println("Failed get config file : "+e.getMessage());
		}

        return langConfig;
    }
	
	public static PropertiesConfiguration createConfigFile(String file, boolean disableDelimiter) {
        try {
            PropertiesConfiguration config = new PropertiesConfiguration();
            config.setDelimiterParsingDisabled(disableDelimiter);
            config.setEncoding("UTF-8");
            File f = new File(file);
            config.load(file);
            config.setReloadingStrategy(new FileChangedReloadingStrategy());
            return config;
        } catch (Throwable th) {
            System.out.println("Failed create config file : "+th.getMessage());
            return null;
        }
    }
	
	public static String getLanguage(String MSG_ID, String defaultMSG) {
		try {
			String langId = SessionHelper.getLangId() != null ? SessionHelper.getLangId() : "EN";
			String msgIdGet = langId.toLowerCase()+"."+MSG_ID;
			String msgLang = getLangConfig(langId).getString(msgIdGet, defaultMSG);
			if (msgLang.trim().equals("")) {
				msgLang = defaultMSG;
			}
			return msgLang;
		} catch (Exception e) {
			System.out.println("Failed get language : "+e.getMessage());
			return defaultMSG;
		}
	}
	
	public static String getActiveLanguage() {
		try {
			String langId = SessionHelper.getLangId() != null ? SessionHelper.getLangId() : "EN";
			return langId;
		} catch (Exception e) {
			System.out.println("Failed get active language : "+e.getMessage());
			return "EN";
		}
	}
}
