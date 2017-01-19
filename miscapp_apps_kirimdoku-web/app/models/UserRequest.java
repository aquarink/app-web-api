package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
@Table(name = "user_requests")
public class UserRequest extends Model {
	@Constraints.Required
	@ManyToOne(optional = false, cascade = {})
	public Date requestTime;

	@ManyToOne(optional = true, cascade = {})
	public String username;

	@Column(nullable = true)
	public String requestId;

	@Column(nullable = true)
	public String requestData;

	@Column(nullable = true)
	public String resultData;

	@Override
	public void save() {
		if ((requestData != null) && (requestData.length() > 255)) {
			requestData = requestData.substring(0, 255);
		}
		if ((resultData != null) && (resultData.length() > 255)) {
			resultData = resultData.substring(0, 255);
		}
		super.save();
	}
}