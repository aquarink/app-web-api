package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.ebean.Model;

@Entity
@Table(name="voucher")
public class Voucher extends Model {
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long id;

    @Column(nullable=false)
    public String code;
    
    @Column(nullable=false)
    public boolean voucher_status;
    
    public static final Model.Finder<Long, Voucher> find = new Model.Finder<Long, Voucher>(Long.class, Voucher.class);
    
}
