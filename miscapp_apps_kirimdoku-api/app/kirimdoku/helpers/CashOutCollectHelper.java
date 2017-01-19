package kirimdoku.helpers;

import models.Corporate;
import models.Customer;
import models.TransactionInquiry;
import org.hibernate.validator.constraints.Length;
import controllers.BaseForm;
import play.data.validation.Constraints;
import play.data.validation.Constraints.Min;
import play.mvc.Http.Request;

public class CashOutCollectHelper extends BaseForm {
	
	@Constraints.Required
	public Customer beneficiary;
	
	@Constraints.Required
	@Length(min = 10, max = 30)
	public String inquiryIdToken;
	
	@Constraints.Required
	@Min(1000)
	public Long validationId;
	
	@Constraints.Required
	@Length(min = 4, max = 16)
	public String auth2;
	
	public models.TransactionInquiry inquiry;
	public String receiveTrxId;
	public java.util.Date cashOutTime;
	public Request request;
	public String agentKey;
	
	public String validate() {
		try {
			String err;
			if ((err = this.validateAgentKey(this.agentKey)) != null) {
				return err;
			}
			if ((err = this.validateInquiryId()) != null) {
				return err;
			}
			if ((err = this.validateValidationId()) != null) {
				return err;
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
	
		if ((trxInquiry != null) && (trxInquiry.typeId.equals(TransactionInquiry.Type.CASHOUT_INQUIRY)) && (trxInquiry.transaction != null)
				&& (trxInquiry.user.equals(user))) {
			this.inquiry = trxInquiry;
			return null;
		} else if (trxInquiry == null) {
			return "invalid inquiryId";
		} else if (!trxInquiry.typeId.equals(TransactionInquiry.Type.CASHOUT_INQUIRY)) {
			return "invalid inquiryId - denial type";
		} else if (trxInquiry.transaction == null) {
			return "invalid inquiryId - denial association";
		} else if (!trxInquiry.user.equals(user)) {
			return "invalid inquiryId - denial owner";
		}
		return "inquiryId - unable to restraint logic";
	}

	private String validateValidationId() {
		if(inquiry == null) {
		} else if(inquiry.validationId == null) {
		} else if (inquiry.validationId.equals(String.valueOf(validationId))) {
			return null;
		}
		
		return "invalid inquiryId - denial validationId "+validationId+" - "+inquiry.validationId;
	}
}
