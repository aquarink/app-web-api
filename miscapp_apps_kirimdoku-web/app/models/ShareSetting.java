package models;


import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
@Table(name="share_setting")
public class ShareSetting extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	@Constraints.Required
	private BigDecimal forexShareSettingPercentage;
	
	@Constraints.Required
	private BigDecimal forexShareSettingFix;
	
	@Constraints.Required
	private BigDecimal feeShareSettingPercentage;
	
	@Constraints.Required
	private BigDecimal feeShareSettingFix;
	
	@Constraints.Required
	private Integer status = 0;
	
	@Constraints.Required
	private Integer foType = 1;
	
	@Constraints.Required
	private Integer feType = 1;
	
	@Constraints.Required
	private Date createdTime;
	
	@Constraints.Required
    @ManyToOne(optional = false, cascade = {}, fetch = FetchType.EAGER)
	private Corporate corporate;
	
	public static final Finder<Long, ShareSetting> find = new Finder<Long, ShareSetting>(
			Long.class, ShareSetting.class);
	
	public ShareSetting findActiveStatus(Corporate corporate) {
		return find.where().eq("status", 1).eq("corporate", corporate).findUnique();
	}
	
	public ShareSetting findById(Long findId) {
		return find.where().eq("id", findId).findUnique();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getForexShareSettingPercentage() {
		return forexShareSettingPercentage;
	}

	public void setForexShareSettingPercentage(
			BigDecimal forexShareSettingPercentage) {
		this.forexShareSettingPercentage = forexShareSettingPercentage;
	}

	public BigDecimal getForexShareSettingFix() {
		return forexShareSettingFix;
	}

	public void setForexShareSettingFix(BigDecimal forexShareSettingFix) {
		this.forexShareSettingFix = forexShareSettingFix;
	}

	public BigDecimal getFeeShareSettingPercentage() {
		return feeShareSettingPercentage;
	}

	public void setFeeShareSettingPercentage(BigDecimal feeShareSettingPercentage) {
		this.feeShareSettingPercentage = feeShareSettingPercentage;
	}

	public BigDecimal getFeeShareSettingFix() {
		return feeShareSettingFix;
	}

	public void setFeeShareSettingFix(BigDecimal feeShareSettingFix) {
		this.feeShareSettingFix = feeShareSettingFix;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getFoType() {
		return foType;
	}

	public void setFoType(Integer foType) {
		this.foType = foType;
	}

	public Integer getFeType() {
		return feType;
	}

	public void setFeType(Integer feType) {
		this.feType = feType;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Corporate getCorporate() {
		return corporate;
	}

	public void setCorporate(Corporate corporate) {
		this.corporate = corporate;
	}
	
}
