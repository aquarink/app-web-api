package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import be.objectify.deadbolt.core.models.Role;
import play.db.ebean.Model;


@Entity
@Table(name = "groups")
public class SecurityRole extends Model implements Role {
	
	@Id
	@Column(nullable = false)
	public String role;

	@Column(nullable = true)
	public String description;

	public static final Finder<String, SecurityRole> find = new Finder<String, SecurityRole>(String.class, SecurityRole.class);

	public String getName() {
		return role;
	}
}
