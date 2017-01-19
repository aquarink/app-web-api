package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlRow;

import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
@Table(name = "user_requests")
public class UserRequest extends Model {
	@Id
	public Long id;
	
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
	
	public static long getSeq() {
		try {
	        SqlRow row = Ebean.createSqlQuery("SELECT nextval('user_requests_seq')").findUnique();
	        long nextId = row.getLong("nextval");
	        return nextId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
}