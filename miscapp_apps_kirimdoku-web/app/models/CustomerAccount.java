package models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.data.validation.Constraints;


@Entity
@Table(name = "customer_accounts")
public class CustomerAccount {
    @Id
    public Long id;
    
    @Constraints.Required
    @ManyToOne(optional = false)
    public Bank bank;
    
    @Constraints.Required
    @Column(nullable = false)
	public String number;
    
    @Constraints.Required
    @Column(nullable = false)
	public String name;
    
    @Column(nullable = true)
	public String city;
    
    @Column(nullable = true)
	public String address;
	
    @Column(nullable = true, length = 50)
	public String inputMode;
}

