package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class CorporateConfiguration extends Model
{
	private static final long serialVersionUID = 1L;

	@Id
	public int id;
    
	public String theme;
	public String logoPath;
	public String primaryColor;
	public String secondaryColor;
	public String headerColor;

	public boolean allowHighValueTransfer;
	public String channelCode;
	public String notifyUrl;
	public String sharedKey;
	
}
