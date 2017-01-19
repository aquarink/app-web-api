package kirimdoku.helpers;

import java.math.RoundingMode;
import models.TransactionInquiry;
import org.hibernate.validator.constraints.Length;
import com.avaje.ebean.Expr;
import play.Configuration;
import controllers.helpers.HashWithSHA1;
import play.Logger;
import play.data.validation.Constraints;

public class CallBackHelper {
	
	@Constraints.Required
	@Length(min = 1, max = 30)
	private String SYSTRACE;
	
	@Constraints.Required
	private String RESPONSECODE;
	
	@Constraints.Required
	private String RESPONSEMESSAGE;
	
	@Constraints.Required
	private String WORDS;
	
	public TransactionInquiry transactionInquiry = null;
	
	private static final Configuration CONFIG_CALLBACK = play.Play.application().configuration().getConfig("callback");
	
	public String validate() {
		try {
			this.transactionInquiry = TransactionInquiry.find.where()
					.add(Expr.eq("validationId", this.SYSTRACE))
					.add(Expr.isNotNull("transaction"))
					.findUnique();
			if (this.transactionInquiry != null) {
				Logger.debug("Channel Transaction >> "+this.transactionInquiry.transaction.channel.code);
				if (!this.transactionInquiry.transaction.channel.code.equals("10"))  {
					Logger.debug("Channel unsupported callback");
					return "Channel unsupported callback";
				}
			} else {
				Logger.debug("Invalid SYSTRACE >> "+this.SYSTRACE);
				return "Invalid SYSTRACE >> "+this.SYSTRACE;
			}
			
			if (!this.WORDS.equals("")) {
				String sharedKey = "";
				try {
					sharedKey = CONFIG_CALLBACK.getString("sharedKey");
				} catch (Exception e) {
					Logger.debug("Failed get shared key callBack >> "+e.getMessage());
				}
				
				if (!sharedKey.equals("")) {
					String SYSTRACE_KIRIMDOKU = this.transactionInquiry.validationId;
					String AMOUNT_KIRIMDOKU = this.transactionInquiry.beneficiaryAmount.setScale(2, RoundingMode.FLOOR)+"";
					//Logger.debug("Komponen WORDS >> "+SYSTRACE_KIRIMDOKU+AMOUNT_KIRIMDOKU+sharedKey);
					String WORDS_KIRIMDOKU = HashWithSHA1.SHA1(SYSTRACE_KIRIMDOKU+AMOUNT_KIRIMDOKU+sharedKey);
					Logger.debug("Client WORDS >> "+this.WORDS);
					Logger.debug("Local WORDS >> "+WORDS_KIRIMDOKU);
					if (!this.WORDS.equals(WORDS_KIRIMDOKU)) {
						Logger.debug("Invalid WORDS >> "+this.WORDS);
						return "Invalid WORDS >> "+this.WORDS;
					}
				}
			} else {
				Logger.debug("Invalid WORDS >> "+this.WORDS);
				return "Invalid WORDS >> "+this.WORDS;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.debug("Failed validate parameter callback >> "+e.getMessage());
			return "Failed validate parameter callback >> "+e.getMessage();
		}
	}

	public String getSYSTRACE() {
		return SYSTRACE;
	}

	public void setSYSTRACE(String sYSTRACE) {
		SYSTRACE = sYSTRACE;
	}

	public String getRESPONSECODE() {
		return RESPONSECODE;
	}

	public void setRESPONSECODE(String rESPONSECODE) {
		RESPONSECODE = rESPONSECODE;
	}

	public String getRESPONSEMESSAGE() {
		return RESPONSEMESSAGE;
	}

	public void setRESPONSEMESSAGE(String rESPONSEMESSAGE) {
		RESPONSEMESSAGE = rESPONSEMESSAGE;
	}

	public String getWORDS() {
		return WORDS;
	}

	public void setWORDS(String wORDS) {
		WORDS = wORDS;
	}
}
