package controllers;

import java.util.List;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import be.objectify.deadbolt.java.actions.Restrictions;
import be.objectify.deadbolt.java.actions.And;
import com.avaje.ebean.ExpressionList;
import models.City;
import models.Country;
import models.Currency;
import models.Bank;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

@Security.Authenticated(SecuredMain.class)
@Restrictions({@And("admin"), @And("mainagent"), @And("supervisor"), @And("operator")})
public class Helpers extends Controller {

	
	public static Result currencies() {
		//TODO Shall had been mapping to countries <-> currencies
		ExpressionList<Currency> where = Currency.find.where();
		if (request().queryString().containsKey("_value")) {
			Country country = Country.find.byId(request().queryString().get("_value")[0]);
			where.eq("code", country.currency.code);
		}
		List<Currency> rows = where.findList();
		
		ArrayNode root = Json.newObject().arrayNode();
		for (Currency row : rows) {
			ObjectNode node = Json.newObject();
			node.put(row.code, row.code);
			root.add(node);
		}
		return ok(root);
	}
	
	public static Result cities() {
		ExpressionList<City> where = City.find.where();
		if (request().queryString().containsKey("_value")) {
			Country country = Country.find.byId(request().queryString().get("_value")[0]);
			where.eq("country", country);
		}
		if (request().queryString().containsKey("country.code")) {
			Country country = Country.find.byId(request().queryString().get("country.code")[0]);
			where.eq("country", country);
		}
		if (request().queryString().containsKey("q")) {
			where.istartsWith("name", request().queryString().get("q")[0]);
		}
		List<City> rows = where.findList();
		
		ArrayNode root = Json.newObject().arrayNode();
		for (City row : rows) {
			root.add(row.name);
//			ObjectNode node = Json.newObject();
//			node.put(String.valueOf(row.id), row.name);
//			root.add(node);
		}
		
		return ok(root);
	}

	public static Result banks() {
		ExpressionList<Bank> where = Bank.find.where();
		if (request().queryString().containsKey("_value")) { // && ("beneficiaryBank_code".equals(request().queryString().get("_value")))) {
			where.eq("code", request().queryString().get("_value")[0]);
		}
		List<Bank> rows = where.findList();
		
		ArrayNode root = Json.newObject().arrayNode();
		for (Bank row : rows) {
			ObjectNode node = Json.newObject();
			node.put(row.code, row.code);
			root.add(node);
		}
		return ok(root);
	}
	
	public static Result banks_detail(String code) {
		Bank bank = Bank.findByCode(code);
		if(bank != null) {
			return ok(Json.toJson(bank));
		} else {
			return notFound();
		}
	}
}
