package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class CorporateFinance extends Model
{
	@Id
	public int id;
    
	public String name;
	public String phoneNumber;
	public String mobileNumber;
	public String faxNumber;
	public String emailAddress;
	public Country country;
	public String city;
	public String address;
	public String postalCode;
}
