package kirimdoku.helpers;

import models.Corporate;
import models.TransactionInquiry;
import org.hibernate.validator.constraints.Length;
import play.Logger;
import play.data.validation.Constraints;
import play.mvc.Http.Request;
import controllers.BaseForm;

public class CashOutValidateHelper extends BaseForm {

	@Constraints.Required
	@Length(min = 10, max = 30)
	public String inquiryIdToken;
	
	@Constraints.Required
	@Length(min = 4, max = 16)
	public String auth1;
	
	public models.TransactionInquiry inquiry;
	public Request request;
	public String agentKey;
	
	public String validate() {
		try {
			String err;
			if ((err = this.validateAgentKey(this.agentKey)) != null) {
				Logger.info(err);
				return err;
			}

			if ((err = this.validateInquiryId()) != null) {
				Logger.info(err);
				return err;
			}

			if (this.auth1 == null) {
				return "Auth1 are required";
			}
			String allowChannel = this.user.corporate.configuration.channelCode != null ? this.user.corporate.configuration.channelCode : "";
			if (!allowChannel.contains(this.inquiry.transaction.channel.code)) {
				return "Unauthorized channel permission";
			}
			if (this.user.corporate.permission != Corporate.PERMISSION_RECEIVE) {
				return "Unauthorized receive permission";
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return "Invalid parameter " + e.getMessage();
		}
	}

	private String validateInquiryId() {
		TransactionInquiry trxInquiry = TransactionInquiry.findByCode(this.inquiryIdToken);
		String result = null;
		if (trxInquiry != null) {
			if ((trxInquiry.typeId.equals(TransactionInquiry.Type.CASHOUT_INQUIRY)) && (trxInquiry.transaction != null)
					&& (trxInquiry.user.equals(user))) {
				this.inquiry = trxInquiry;
			} else if (!trxInquiry.typeId.equals(TransactionInquiry.Type.CASHOUT_INQUIRY)) {
				result = "invalid inquiryId - denial type";
			} else if (trxInquiry.transaction == null) {
				result = "invalid inquiryId - denial association";
			} else if (!trxInquiry.user.equals(user)) {
				result = "invalid inquiryId - denial owner";
			}
		} else {
			result = "invalid inquiryId";
		}
		return result;
	}

}
