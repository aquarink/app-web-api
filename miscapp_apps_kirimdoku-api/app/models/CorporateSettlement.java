package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class CorporateSettlement extends Model
{
	@Id
	public int id;
    
	public String bankName;
	public String accountNumber;
	public String accountName;
	public String swiftCode;
	public String name;
	public String phoneNumber;
	public String faxNumber;
	public String emailAddress;
	public Currency currency;
	public String bankAddress;
	public String postalCode;
	public Country country;
	public String province;
	public String city;
	
	public int settlementPeriodDay;
	public Date agreementDate;
	public int agreementYear;
	public Date agreementExpiredDate;
	public Date agreementReminderDate;
}
