package models;

import java.util.Date;

import play.db.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.avaje.ebean.Ebean;

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
    @Version
    public Date updateTime;
    
    public static final Finder<String, Channel> find = new Finder<String, Channel>(String.class, Channel.class);


    public static Channel findByName(String name)
    {
        return find.where()
                .eq("name",
                        name)
                .findUnique();
    }
    
    public static Channel findByCode(String code)
    {
        return find.where()
                .eq("code",
                        code)
                .findUnique();
    }
    
    public static void updateAdditionalParam(String code, String additionalParam) {
    	Ebean.createSqlUpdate("UPDATE channels SET additional_param = :additionalParam, update_time = now() where code = :code").setParameter("code",  code).setParameter("additionalParam", additionalParam).execute();
    }
}
