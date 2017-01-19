/*
 * Copyright 2012 Steve Chaloner
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Page;
import com.avaje.ebean.SqlRow;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name="banks")
public class Bank extends Model
{

	private static final long serialVersionUID = 1L;

	@Id
	@Constraints.Required
    public String id;
    
	@Constraints.Required
    @Column(length=12)
    public String code;

    @Constraints.Required
    public String name;

    public String city;
    
    @Constraints.Required
	@Column(length=3)
	public String countryCode;
	
    @Constraints.Required
	@Column(length=255)
	public String groupBank;	
	
	@Column(length=255)
	public String province;	
	
    public static final Finder<String, Bank> find = new Finder<String, Bank>(String.class, Bank.class);

    public static Bank findByCode(String code)
    {
        return find.where()
                .eq("code", code)
                .findUnique();
    }

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Bank b: Bank.find.orderBy("name").findList()) {
            options.put(b.code, b.name);
        }
        return options;
    }
    
    public static Map<String,String> optionsBankId() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Bank b: Bank.find.where().eq("countryCode", "ID").orderBy("id").findList()) {
            options.put(b.id, b.name);
        }
        return options;
    }
    
    public static Map<String,String> optionsByCountry(String country) {
    	System.out.println("Country Bank : "+country);
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Bank b: Bank.find.where().eq("countryCode", country).findList()) {
            options.put(b.code, b.name);
        }
        return options;
    }
    
    public static Map<String,String> optionsGroupBank() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        String sql = "SELECT DISTINCT(UPPER(group_bank)) as group FROM banks ORDER BY UPPER(group_bank)";
        List<SqlRow> rows = Ebean.createSqlQuery(sql).findList();
        for (Iterator<SqlRow> iterator = rows.iterator(); iterator.hasNext();) {
			SqlRow sqlRow = (SqlRow) iterator.next();
			options.put(sqlRow.getString("group").toString(), sqlRow.getString("group").toString());
		}
        return options;
    }
    
    public static Map<String,String> optionsGroupCountryBank(String countryCode) {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        String sql = "SELECT DISTINCT(UPPER(group_bank)) as group FROM banks WHERE country_code = :countryCode ORDER BY UPPER(group_bank)";
        List<SqlRow> rows = Ebean.createSqlQuery(sql).setParameter("countryCode", countryCode).findList();
        for (Iterator<SqlRow> iterator = rows.iterator(); iterator.hasNext();) {
			SqlRow sqlRow = (SqlRow) iterator.next();
			options.put(sqlRow.getString("group").toString(), sqlRow.getString("group").toString());
		}
        return options;
    }
    
    public static Map<String,String> optionsGroupBank(String channelCode, String countryCode) {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        countryCode = countryCode != null ? countryCode : "";
        String sql = "SELECT DISTINCT(UPPER(group_bank)) as group FROM banks WHERE country_code ilike :countryCode AND id IN (SELECT bank_id FROM channel_bank WHERE channel_code = :channelCode AND status = true) ORDER BY UPPER(group_bank)";
        List<SqlRow> rows = Ebean.createSqlQuery(sql)
        		.setParameter("countryCode", countryCode+"%")
        		.setParameter("channelCode", channelCode).findList();
        for (Iterator<SqlRow> iterator = rows.iterator(); iterator.hasNext();) {
			SqlRow sqlRow = (SqlRow) iterator.next();
			options.put(sqlRow.getString("group").toString(), sqlRow.getString("group").toString());
		}
        return options;
    }
    
    public static Map<String,String> optionsProvinceBank(String countryBank, String groupBank) {
    	System.out.println("Country Bank  : "+countryBank);
    	System.out.println("Group Bank    : "+groupBank);
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Bank b: Bank.find.select("province").setDistinct(true).where().eq("countryCode", countryBank).ilike("groupBank", groupBank).orderBy("province").findList()) {
        	if (b.province != null && !b.province.equals(""))
        	options.put(b.province, b.province);
        }
        if (options.size() == 0) {
        	options.put("default", "default");
        }
        return options;
    }
    
    public static Map<String,String> optionsCityBank(String countryBank, String groupBank, String provinceBank) {
    	System.out.println("Country Bank  : "+countryBank);
    	System.out.println("Group Bank    : "+groupBank);
    	System.out.println("Province Bank : "+provinceBank);
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Bank b: Bank.find.select("city").setDistinct(true)
        		.where().eq("countryCode", countryBank)
        		.where().ilike("groupBank", groupBank)
        		.where().ilike("province", provinceBank)
        		.orderBy("city").findList()) {
        	if (b.city != null && !b.city.equals(""))
        	options.put(b.city, b.city);
        }
        if (options.size() == 0) {
        	options.put("default", "default");
        }
        return options;
    }
    
    public static Map<String,String> optionsBank(String countryBank, String groupBank, String provinceBank, String cityBank) {
    	System.out.println("Country Bank  : "+countryBank);
    	System.out.println("Group Bank    : "+groupBank);
    	System.out.println("Province Bank : "+provinceBank);
    	System.out.println("City Bank     : "+cityBank);
    	
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        
        ExpressionList<Bank> expressionList = Bank.find.where().eq("countryCode", countryBank).ilike("groupBank", groupBank);
        
        if (provinceBank.equals("default")) {
        	expressionList.where().or(Expr.isNull("province"),Expr.eq("province", ""));
        } else {
        	expressionList.where().ilike("province", provinceBank);
        }
        
        if (cityBank.equals("default")) {
        	expressionList.where().or(Expr.isNull("city"),Expr.eq("city",""));
        } else {
        	expressionList.where().ilike("city", cityBank);
        }
        
        for(Bank b: expressionList.findList()) {
        	if (b.code != null && !b.code.equals(""))
        	options.put(b.code, b.name);
        }
        return options;
    }
    
	public static Page<Bank> page(int page, int pageSize, String sortBy, String order, String filter) {
		ExpressionList<Bank> where = find.where().eq("countryCode", "ID");
		if (filter != null) {
			where.or(Expr.or(Expr.ilike("id", "%" + filter + "%"), Expr.ilike("code", "%" + filter + "%")), Expr.ilike("name", "%" + filter + "%"));
		}
		return where.orderBy(sortBy + " " + order).findPagingList(pageSize).setFetchAhead(false).getPage(page);
	}
    
	public static List<Bank> csv(String sortBy, String order, String filter) {
		ExpressionList<Bank> where = find.where();
		return where.orderBy(sortBy + " " + order).findList();
	}
	
}
