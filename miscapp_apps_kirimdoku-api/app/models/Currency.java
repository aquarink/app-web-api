package models;

import play.db.ebean.Model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.avaje.ebean.Expr;

@Entity
@Table(name="currencies")
public class Currency extends Model
{

	private static final long serialVersionUID = 1L;

	@Id
    @Column(length=3)
    public String code;

    public static final Finder<String, Currency> find = new Finder<String, Currency>(String.class, Currency.class);
    
    
    public static Currency findByCode(String code) {
    	return find.where().add(Expr.eq("code", code)).findUnique();
    }
    
}