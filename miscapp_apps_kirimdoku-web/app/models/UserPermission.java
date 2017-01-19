package models;

import be.objectify.deadbolt.core.models.Permission;
import play.db.ebean.Model;
import javax.persistence.Id;

//@Entity
//@Table(name="permissions")
public class UserPermission extends Model implements Permission
{
    @Id
    public Long id;

    public String value;

    public static final Model.Finder<Long, UserPermission> find = new Model.Finder<Long, UserPermission>(Long.class, UserPermission.class);

    public String getValue()
    {
        return value;
    }

    public static UserPermission findByValue(String value)
    {
        return find.where()
                   .eq("value",
                       value)
                   .findUnique();
    }
}
