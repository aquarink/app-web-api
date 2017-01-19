package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Constraint;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
@Table(name="forex_setting")
public class ForexSetting extends Model
{
    @Id
    public Long id;

    @Constraints.Required
    public double threshold;

    @Constraints.Required
    public double spread;

    @Constraints.Required
    public Date createdTime;
    
    public static final Finder<Long, ForexSetting> find = new Finder<Long, ForexSetting>(Long.class,
    		ForexSetting.class);
}
