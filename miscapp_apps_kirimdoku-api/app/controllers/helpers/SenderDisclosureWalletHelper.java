package controllers.helpers;

import java.math.BigDecimal;

public class SenderDisclosureWalletHelper {
	
	private String senderAccountId;
	private String senderName;
	private BigDecimal amountToTransfer;
	private BigDecimal fee;
	private BigDecimal totalAmountToTransfer;
	
	public String getSenderAccountId() {
		return senderAccountId;
	}
	public void setSenderAccountId(String senderAccountId) {
		this.senderAccountId = senderAccountId;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public BigDecimal getAmountToTransfer() {
		return amountToTransfer;
	}
	public void setAmountToTransfer(BigDecimal amountToTransfer) {
		this.amountToTransfer = amountToTransfer;
	}
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public BigDecimal getTotalAmountToTransfer() {
		return totalAmountToTransfer;
	}
	public void setTotalAmountToTransfer(BigDecimal totalAmountToTransfer) {
		this.totalAmountToTransfer = totalAmountToTransfer;
	}
}
