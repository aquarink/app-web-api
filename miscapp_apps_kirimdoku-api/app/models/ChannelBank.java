package models;

import java.util.Date;
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

	public static boolean getChannelBankByChannelCodeAndBankId(String channelCode, String bankId) {
		try {
			ChannelBank channelBanks = ChannelBank.find.where().add(Expr.eq("channel.code", channelCode)).add(Expr.eq("bank.id", bankId)).add(Expr.eq("status", true)).findUnique();
			if (channelBanks != null) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
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
