package models;

import java.util.List;

import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="labels")
public class Label extends Model
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer id;

    @Column(nullable=false)
    public String name;
    
    @Column(nullable=false)
    public String description;
    
    @Column(nullable=false)
    public TYPE type = TYPE.DEFAULT;
    
    public enum TYPE {DEFAULT, USER, CUSTOMER, TRANSACTION};

    public static final Model.Finder<Long, Label> find = new Model.Finder<Long, Label>(Long.class, Label.class);
    
    public static List<Label> getLabelSuspicious() {
    	try {
    		return Label.find.setUseQueryCache(true).where().eq("type", Label.TYPE.TRANSACTION).between("id", 30, 39).findList();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
    
}
