package controllers.helpers;

import java.math.BigDecimal;

public class ReceiverDisclosureWalletHelper {

	private String receiverAccountId;
	private String receiverName;
	private BigDecimal amountReceived;
	private BigDecimal fee;
	private BigDecimal totalAmountReceived;
	public String getReceiverAccountId() {
		return receiverAccountId;
	}
	public void setReceiverAccountId(String receiverAccountId) {
		this.receiverAccountId = receiverAccountId;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public BigDecimal getAmountReceived() {
		return amountReceived;
	}
	public void setAmountReceived(BigDecimal amountReceived) {
		this.amountReceived = amountReceived;
	}
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public BigDecimal getTotalAmountReceived() {
		return totalAmountReceived;
	}
	public void setTotalAmountReceived(BigDecimal totalAmountReceived) {
		this.totalAmountReceived = totalAmountReceived;
	}
}
