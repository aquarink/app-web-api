package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlRow;
import com.avaje.ebean.TxType;
import com.avaje.ebean.annotation.Transactional;
import java.util.Date;

@Entity
@Table(name="batch")
public class Batch extends Model
{

	private static final long serialVersionUID = 1L;

	@Id
	@Constraints.Required
    public int id;
    
	@Constraints.Required
    @Column(length=128)
    public String fileName;

    @Constraints.Required
    public Date createDate;
    public Date inquiryUpdateDate;
    public Date remitUpdateDate;
    
    @Constraints.Required
    public int totalRow = 0;
	
    @Constraints.Required
    public int totalRowInquiry = 0;
    
    @Constraints.Required
    public int totalRowRemit = 0;
    
	public String description;	
	
	@ManyToOne(optional = true, cascade = {})
	public Corporate corporate;
	
	@ManyToOne(optional = true, cascade = {})
	public User user;
	
    @Constraints.Required
    public Character state;
	
    public static final Finder<Integer, Batch> find = new Finder<Integer, Batch>(Integer.class, Batch.class);

	public static enum BatchState {
		UPLOAD('U'),
		PROCESS_INQUIRY('P'),
		DONE_INQUIRY('I'),
		PROCESS_REMIT('R'),
		CLOSE('C');

		private char code;

		BatchState(char code) {
			this.code = code;
		}

		public char getCode() {
			return code;
		}
		
		@Override
		public String toString() {
			return this.name();
		}
	}
    
	@Transactional(type = TxType.NOT_SUPPORTED)
    public static int getSeq() {
    	try {
	        SqlRow row = Ebean.createSqlQuery("SELECT nextval('batch_seq')").findUnique();
	        int nextId = row.getInteger("nextval");
	        return nextId;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return 0;
    }
	
}
