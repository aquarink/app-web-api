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
import com.avaje.ebean.SqlRow;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name="banks")
public class Bank extends Model
{

	private static final long serialVersionUID = 1L;

	@Id
    public String id;
    
    @Column(length=12)
    public String code;

    @Constraints.Required
    public String name;
    
    public String city;
	
	@Column(length=3)
	public String countryCode;

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
    
    public static List<Bank> getList(String countryCode, String groupBankName, String provinceName, String cityName, String swiftCode, String bankName) {
    	try {
    		ExpressionList<Bank> where = find.where();
        	
        	if (countryCode != null && !countryCode.equals("") && groupBankName != null && !groupBankName.equals("")) {
        		where = where.add(Expr.eq("countryCode", countryCode));
	        	where = where.add(Expr.ilike("groupBank", groupBankName));
	        	
	        	if (provinceName != null && !provinceName.equals("")) {
	        		where = where.add(Expr.ilike("province", provinceName));
	        	}
	        	if (cityName != null && !cityName.equals("")) {
	        		where = where.add(Expr.ilike("city", cityName));
	        	}
	        	if (swiftCode != null && !swiftCode.equals("")) {
	        		where = where.add(Expr.eq("code", swiftCode));
	        	}
	        	if (bankName != null && !bankName.equals("")) {
	        		where = where.add(Expr.ilike("name", "%" + bankName + "%"));
	        	}
	        	
	        	return where.orderBy("countryCode, groupBank, province, city, name").findList();
        	} else {
        		return null;
        	}
		} catch (Exception e) {
			return null;
		}
    }
    
    public static List<String> getGroupBankList(String countryCode) {
        List<String> groupBankList = new ArrayList<String>();
        
        String sql = "SELECT DISTINCT(UPPER(group_bank)) as group FROM banks WHERE country_code = :countryCode ORDER BY UPPER(group_bank)";
        List<SqlRow> rows = Ebean.createSqlQuery(sql).setParameter("countryCode", countryCode).findList();
        for (Iterator<SqlRow> iterator = rows.iterator(); iterator.hasNext();) {
			SqlRow sqlRow = (SqlRow) iterator.next();
			groupBankList.add(sqlRow.getString("group").toString());
		}
        return groupBankList;
    }
    
    public static List<String> getProvinceBankList(String countryCode, String groupBank) {
        List<String> provinceBankList = new ArrayList<String>();
        String sql = "SELECT DISTINCT(UPPER(province)) as provinces FROM banks WHERE country_code = :countryCode AND group_bank ILIKE :groupBank ORDER BY UPPER(province)";
        List<SqlRow> rows = Ebean.createSqlQuery(sql).setParameter("countryCode", countryCode).setParameter("groupBank", groupBank).findList();
        for(SqlRow row: rows) {
        	if (row.getString("provinces") != null && !row.getString("provinces").equals(""))
        		provinceBankList.add(row.getString("provinces"));
        }
        return provinceBankList;
    }
    
    public static List<String> getCityBankList(String countryCode, String groupBank, String provinceBank) {
        List<String> cityBankList = new ArrayList<String>();
        String sql = "SELECT DISTINCT(UPPER(city)) as cities FROM banks WHERE country_code = :countryCode AND group_bank ILIKE :groupBank AND province ILIKE :provinceBank ORDER BY UPPER(city)";
        List<SqlRow> rows = Ebean.createSqlQuery(sql).setParameter("countryCode", countryCode).setParameter("groupBank", groupBank).setParameter("provinceBank", provinceBank).findList();
        for(SqlRow row: rows) {
        	if (row.getString("cities") != null && !row.getString("cities").equals(""))
        		cityBankList.add(row.getString("cities"));
        }
        return cityBankList;
    }
    
    public static List<Bank> getDataBankList(String channelCode, String countryCode) {
    	try {
    		countryCode = countryCode != null ? countryCode : "";
            String sql = "find banks WHERE country_code ilike :countryCode AND id IN (SELECT bank_id FROM channel_bank WHERE channel_code = :channelCode AND status = true) ORDER BY name";
            List<Bank> banks = Ebean.createQuery(Bank.class,sql).setParameter("countryCode", countryCode+"%").setParameter("channelCode", channelCode).findList();
            return banks;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }

}
