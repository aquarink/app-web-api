package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
@Table(name = "settlements")
public class Settlement extends Model {
	@Id
	public Long id;
	
	public Date created;

	// TODO Shall has large summary of settlement here
}