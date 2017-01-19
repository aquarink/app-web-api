package controllers;

import java.util.Map;

import models.Bank;
import models.Channel;
import models.Country;
import be.objectify.deadbolt.java.actions.And;
import be.objectify.deadbolt.java.actions.Restrictions;
import play.Logger;
import play.libs.Json;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

@Security.Authenticated(SecuredMain.class)
@Restrictions({ @And("mainagent"), @And("supervisor"), @And("operator") })
public class GetBank extends Controller {
	
//	public static Result getCountriesByCode() {
//		
//		Form<Country> countryData = form(Country.class).bindFromRequest(request());
//		
//		Logger.debug("Request Countries : "+countryData.data());
//		Map<String,String> map = null;
//		
//		if (countryData.get().code != null) {
//			try {
//				map = Country.findByCode(countryData.get().code, countryData.get().currency);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		} else {
//			map = Country.findByCode(countryData.get().code);
//		}
//		
//		return ok(Json.toJson(map));
//	}

	public static Result getGroupBank() {
		Form<SearchBank> formSearch = form(SearchBank.class).bindFromRequest(request());
		Logger.debug("Request getGroupBank : "+formSearch.data());
		Map<String,String> map = null;
		if (formSearch.get().channel != null) {
			try {
				map = Bank.optionsGroupBank(formSearch.get().channel.code, formSearch.get().countryBank);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			map = Bank.optionsGroupCountryBank(formSearch.get().countryBank);
		}
		return ok(Json.toJson(map));
	}
	
	public static Result getProvinceBank() {
		Form<SearchBank> formSearch = form(SearchBank.class).bindFromRequest(request());
		Map<String,String> map = Bank.optionsProvinceBank(formSearch.get().countryBank, formSearch.get().groupBank);
		
		return ok(Json.toJson(map));
	}
	
	public static Result getCityBank() {
		Form<SearchBank> formSearch = form(SearchBank.class).bindFromRequest(request());
		Map<String,String> map = Bank.optionsCityBank(formSearch.get().countryBank, formSearch.get().groupBank, formSearch.get().provinceBank);
		
		return ok(Json.toJson(map));
	}
	
	public static Result getBank() {
		Form<SearchBank> formSearch = form(SearchBank.class).bindFromRequest(request());
		Map<String,String> map = Bank.optionsBank(formSearch.get().countryBank, formSearch.get().groupBank, formSearch.get().provinceBank, formSearch.get().cityBank);
		
		return ok(Json.toJson(map));
	}
	
	public static class SearchBank {
		public String countryBank = null;
		public String groupBank = null;
		public String provinceBank = null;
		public String cityBank = null;
		public Channel channel;
	}

}
