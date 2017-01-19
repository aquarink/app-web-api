package models;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.avaje.ebean.Expr;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Page;

import controllers.helpers.SessionHelper;


@Entity
@Table(name="channels")
public class Channel extends Model
{

	private static final long serialVersionUID = 1L;

	@Id
    @Column(length=2)
    public String code;

    @Constraints.Required
    public String name;

    @JsonIgnore
    public String cfnames;
    
    @JsonIgnore
    public Boolean status;
    
    @JsonIgnore
    public String cid;
    
    @JsonIgnore
    public String accountId;
    
    @JsonIgnore
    public String sharedKey;
    
    @JsonIgnore
    public String additionalParam;
    
    @JsonIgnore
    public Date createdTime;
    
    @JsonIgnore
    public Date updateTime;
    
    public static final Finder<String, Channel> find = new Finder<String, Channel>(String.class, Channel.class);

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Channel c: Channel.find.orderBy("code").findList()) {
            options.put(c.code, c.name);
        }
        return options;
    }
    
    public static Map<String,String> optionsUser() {
		String channelCode = "";
		
		TreeMap<String, String> mapChannel = new TreeMap<String, String>();
		try{
			channelCode = SessionHelper.getUser().corporate.configuration.channelCode;
			
			if (channelCode != null && !channelCode.trim().equals("")) {
				Object[] channel = channelCode.split(";");
				List<Channel> channels = null;
				channels = Channel.find.where().in("code", channel).orderBy("code").findList();
				for (Iterator<Channel> iterator = channels.iterator(); iterator.hasNext();) {
					Channel channelItem = (Channel) iterator.next();
					if (!channelItem.code.equals("10"))
					mapChannel.put(channelItem.code, channelItem.name);
				}
			}
		} catch (Exception e) {
			channelCode = "empty";
		}
		return mapChannel;
    }
    
	public static Page<Channel> page(int page, int pageSize, String sortBy, String order, String filter) {
		ExpressionList<Channel> where = find.where();
		if (filter != null) {
			where.or(Expr.ilike("code", "%" + filter + "%"), Expr.ilike("name", "%" + filter + "%"));
		}
		return where.orderBy(sortBy + " " + order).findPagingList(pageSize).setFetchAhead(false).getPage(page);
	}
	
	public static Map<String, String> getChannelRouteToTpg() {
		Map<String, String> mapChannel = new HashMap<String, String>();
		mapChannel.put("04", "Cash To Wallet");
		mapChannel.put("06", "Cash to Account");
		mapChannel.put("07", "Bank Deposit");
		mapChannel.put("10", "Bill Payment");
		mapChannel.put("03", "Cash to China");
		return mapChannel;
	}
	
}
