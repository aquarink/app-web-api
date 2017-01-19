package models;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import com.avaje.ebean.SqlRow;

import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
@Table(name="channel_bank")
public class ChannelBank extends Model {

	private static final long serialVersionUID = 1L;
	
	@Id
	public Long id;
	
	@Constraints.Required
	@ManyToOne(optional = true)
	public Channel channel;
	
	@Constraints.Required
	@ManyToOne(optional = true)
	public Bank bank;
	
	@Constraints.Required
	public boolean status;
	
	@Constraints.Required
	public Date createdTime;
	
	public static final Finder<String, ChannelBank> find = new Finder<String, ChannelBank>(String.class, ChannelBank.class);

	public static Map<String, String> getChannelBankByChannelCode(String channelCode) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			List<ChannelBank> channelBanks = ChannelBank.find.where().add(Expr.eq("channel.code", channelCode)).add(Expr.eq("status", true)).findList();
			for (ChannelBank channelBank : channelBanks) {
				map.put(channelBank.bank.id, channelBank.bank.id);
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Map<String, String> bankSwiftCodeByChannelCode(String channelCode) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			List<ChannelBank> channelBanks = ChannelBank.find.where().add(Expr.eq("channel.code", channelCode)).add(Expr.eq("status", true)).findList();
			for (ChannelBank channelBank : channelBanks) {
				map.put(channelBank.bank.code, channelBank.bank.code);
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Map<String, String> bankIdSwiftCodeByChannelCode(String channelCode) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			List<ChannelBank> channelBanks = ChannelBank.find.where().add(Expr.eq("channel.code", channelCode)).add(Expr.eq("status", true)).findList();
			for (ChannelBank channelBank : channelBanks) {
				map.put(channelBank.bank.id, channelBank.bank.code);
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
    public static long getSeq() {
    	try {
	        SqlRow row = Ebean.createSqlQuery("SELECT nextval('channel_bank_id_seq')").findUnique();
	        long nextId = row.getLong("nextval");
	        return nextId;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return 0;
    }
	
    public static void disableLastChannel(String channelCode) {
    	try {
    		Ebean.createSqlUpdate("UPDATE channel_bank SET status = false WHERE channel_code = :channelCode").setParameter("channelCode", channelCode).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    
}
