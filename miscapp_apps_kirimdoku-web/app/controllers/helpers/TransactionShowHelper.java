package controllers.helpers;

import org.codehaus.jackson.annotate.JsonIgnore;

import models.Transaction;


public class TransactionShowHelper {
	
	private Transaction transaction;
	private TransactionLog transactionLog;
	
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	public TransactionLog getTransactionLog() {
		return transactionLog;
	}
	public void setTransactionLog(TransactionLog transactionLog) {
		this.transactionLog = transactionLog;
	}
	
	public static class TransactionLog {
		@JsonIgnore
		private int status;
		@JsonIgnore
		private String statusMessage;
		private String referenceStatus;
		private String referenceMessage;
		@JsonIgnore
		private String channelCode;

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public String getStatusMessage() {
			return statusMessage;
		}

		public void setStatusMessage(String statusMessage) {
			this.statusMessage = statusMessage;
		}

		public String getReferenceStatus() {
			return referenceStatus;
		}

		public void setReferenceStatus(String referenceStatus) {
			this.referenceStatus = referenceStatus;
		}

		public String getReferenceMessage() {
			return referenceMessage;
		}

		public void setReferenceMessage(String referenceMessage) {
			this.referenceMessage = referenceMessage;
		}

		public String getChannelCode() {
			return channelCode;
		}

		public void setChannelCode(String channelCode) {
			this.channelCode = channelCode;
		}
		
	}
	
}
