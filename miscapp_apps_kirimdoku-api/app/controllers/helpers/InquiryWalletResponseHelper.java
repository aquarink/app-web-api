package controllers.helpers;

public class InquiryWalletResponseHelper {
	
	private String clientId;
	private String responseCode;
	private ResponseWalletHelper responseMessage;
	private Integer dokuId;
	private String transactionId;
	private String trackingId;
	private SenderDisclosureWalletHelper senderDisclosure;
	private ReceiverDisclosureWalletHelper receiverDisclosure;
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public ResponseWalletHelper getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(ResponseWalletHelper responseMessage) {
		this.responseMessage = responseMessage;
	}
	public Integer getDokuId() {
		return dokuId;
	}
	public void setDokuId(Integer dokuId) {
		this.dokuId = dokuId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getTrackingId() {
		return trackingId;
	}
	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}
	public SenderDisclosureWalletHelper getSenderDisclosure() {
		return senderDisclosure;
	}
	public void setSenderDisclosure(SenderDisclosureWalletHelper senderDisclosure) {
		this.senderDisclosure = senderDisclosure;
	}
	public ReceiverDisclosureWalletHelper getReceiverDisclosure() {
		return receiverDisclosure;
	}
	public void setReceiverDisclosure(
			ReceiverDisclosureWalletHelper receiverDisclosure) {
		this.receiverDisclosure = receiverDisclosure;
	}
}
