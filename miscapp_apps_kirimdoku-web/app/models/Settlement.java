package models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import play.db.ebean.Model;

@Entity
@Table(name = "settlements")
public class Settlement extends Model {
	@Id
	public Long id;
	
	public Date created;

	// TODO Shall has large summary of settlement here
}