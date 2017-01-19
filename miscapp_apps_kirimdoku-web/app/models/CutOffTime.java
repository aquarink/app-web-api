package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
@Table(name="cut_off_time")
public class CutOffTime extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	@Constraints.Required
	private String cutOff;

	@Constraints.Required
	private Integer status;
	
	@Constraints.Required
	private Date createdTime;

	public static final Finder<Long, CutOffTime> find = new Finder<Long, CutOffTime>(
			Long.class, CutOffTime.class);

	public static CutOffTime findActiveStatus() {
		try {
			CutOffTime cutOffTime = find.where().eq("status", 1).findUnique();
			if (cutOffTime != null)
				return cutOffTime;
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCutOff() {
		return cutOff;
	}

	public void setCutOff(String cutOff) {
		this.cutOff = cutOff;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
}
