package security;

import java.util.List;
import be.objectify.deadbolt.core.models.Subject;
import models.SecurityRole;
import models.User;
import models.UserPermission;


/* NOT BEING USED NOW */
public class UserSubject extends User implements Subject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getIdentifier() {
		return this.id.toString();
	}

	@Override
	public List<UserPermission> getPermissions() {
		return null;
	}

	@Override
	public List<SecurityRole> getRoles() {
		return this.roles;
	}
}
