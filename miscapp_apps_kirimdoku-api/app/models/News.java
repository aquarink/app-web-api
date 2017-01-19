package models;

import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Page;
import play.Logger;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="news")
public class News extends Model
{
    @Id
    public Long id;

    @Constraints.Required
    @Lob
    public String content;

    @Column(nullable = false)
    public boolean published;

    @Constraints.Required
    @ManyToOne(optional = false, cascade = {}, fetch = FetchType.EAGER)
    public Corporate corporate;

    public static final Finder<Long, News> find = new Finder<Long, News>(Long.class, News.class);

    public String getSubContent(){
        return this.content.substring(0, 360) + "...";
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

    public static Page<News> newsPage(int page, int pageSize, String sortBy, String order) {
        ExpressionList<News> where = find.where();
        return where
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .setFetchAhead(false)
                .getPage(page);
    }
}
