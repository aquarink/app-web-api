package models;

import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Page;
import controllers.helpers.SessionHelper;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name="news")
public class News extends Model
{
    @Id
    public Long id;

    @Constraints.Required
    //@Lob
    public String content;

    @Column(nullable = false)
    public boolean published;

    @Constraints.Required
    @ManyToOne(optional = false, cascade = {}, fetch = FetchType.EAGER)
    public Corporate corporate;

    public static final Finder<Long, News> find = new Finder<Long, News>(Long.class, News.class);


    public static List<News> getPublishedNews() {
        models.User sessionUser = SessionHelper.getUser();
        return find.where().eq("published", true).eq("corporate.code", sessionUser.corporate.code).order("id desc").setMaxRows(5).findList();
    }

    public String getTitle(){
    	if (this.content.length()>120) {
    		return this.content.substring(0, 120);
    	}
    	return this.content;
    }
    
    public String getSubContent(){
    	if (this.content.length()>360) {
    		return this.content.substring(0, 360) + "...";
    	} else {
    		return this.content;
    	}
    }

    public String getFullContent(){
        return this.content;
    }

    public String getStatusText(){
        if(this.published){
            return "Published";
        }else{
            return "Not Published";
        }
    }

    public static Page<News> newsPage(Corporate corporate, int page, int pageSize, String sortBy, String order) {
        ExpressionList<News> where = find.where();
        where.eq("corporate", corporate);
        return where
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .setFetchAhead(false)
                .getPage(page);
    }

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        options.put("0", "Not Published");
        options.put("1", "Published");
        return options;
    }
}
