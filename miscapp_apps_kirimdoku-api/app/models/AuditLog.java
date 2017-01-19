package models;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Page;

import play.db.ebean.Model;

@Entity
@Table(name="audit_logs")
public class AuditLog extends Model
{
	public static final int SOURCE_BACKEND = 1;
	public static final int SOURCE_FRONTEND = 2;
	
	@Id
    public Long id;

	@Column(nullable = false)
	public Date createdTime;
	
	@Column(nullable = false)
	public int source;
	
    @ManyToOne(optional = true)
    public User user;
    
    @Column(nullable = false, length=24)
    public String tag;
	
    @Column(nullable = false, length=255)
	public String message;
	
    @Column(nullable = true)
    @Lob
	public String data;

    @Column(nullable = true)
    @Lob
	public String data2;
    
    public static final Finder<Long, AuditLog> find = new Finder<Long, AuditLog>(Long.class, AuditLog.class);
    
    public static Page<AuditLog> findPaging(User user, int page, int pageSize, String sortBy, String order, Map<String, String> filters) {
    	
        ExpressionList<AuditLog> where = find.where();
        
        if(filters != null) {
        	for(Entry<String, String> entry : filters.entrySet()) {
        		where.eq(entry.getKey(), entry.getValue());
        	}
        }
        
        return where
                .orderBy(sortBy + " " + order)
                .fetch("user")
                .findPagingList(pageSize)
                .setFetchAhead(false)
                .getPage(page);
    }
    
    @Override
    public String toString() {
    	String userStr = "SYSTEM";
    	if (this.user != null)
    		userStr = this.user.email;
    	return "["+this.createdTime+"] ["+userStr+"] ["+this.tag+"] - "+this.message;
    }
}
