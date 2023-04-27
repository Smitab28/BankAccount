package com.jbk.model;

import java.time.LocalDateTime;

public class TransactionHistory {

	private int transactionId;
	private long userId;
	private String transactionAccountNumber;
	private String transactionType;
	private double transactionAmount;
	private String transactionSource;
	private String transactionStatus;
	private String transactionReasonCode;
	private LocalDateTime transactionCreatedAt;
	
	
	public TransactionHistory() {	}
	
	
	
	public TransactionHistory(int transactionId, long userId, String transactionAccountNumber, String transactionType,
			double transactionAmount, String transactionSource, String transactionStatus, String transactionReasonCode,
			LocalDateTime transactionCreatedAt) {
		super();
		this.transactionId = transactionId;
		this.userId = userId;
		this.transactionAccountNumber = transactionAccountNumber;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
		this.transactionSource = transactionSource;
		this.transactionStatus = transactionStatus;
		this.transactionReasonCode = transactionReasonCode;
		this.transactionCreatedAt = transactionCreatedAt;
	}



	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	
	
	public String getTransactionAccountNumber() {
		return transactionAccountNumber;
	}

	public void setTransactionAccountNumber(String transactionAccountNumber) {
		this.transactionAccountNumber = transactionAccountNumber;
	}

	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getTransactionSource() {
		return transactionSource;
	}
	public void setTransactionSource(String transactionSource) {
		this.transactionSource = transactionSource;
	}
	public String getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	public String getTransactionReasonCode() {
		return transactionReasonCode;
	}
	public void setTransactionReasonCode(String transactionReasonCode) {
		this.transactionReasonCode = transactionReasonCode;
	}
	public LocalDateTime getTransactionCreatedAt() {
		return transactionCreatedAt;
	}
	public void setTransactionCreatedAt(LocalDateTime transactionCreatedAt) {
		this.transactionCreatedAt = transactionCreatedAt;
	}
	@Override
	public String toString() {
		return "TransactionHistory [transactionId=" + transactionId + ", userId=" + userId
				+ ", transactionAccountNumber=" + transactionAccountNumber + ", transactionType=" + transactionType
				+ ", transactionAmount=" + transactionAmount + ", transactionSource=" + transactionSource
				+ ", transactionStatus=" + transactionStatus + ", transactionReasonCode=" + transactionReasonCode
				+ ", transactionCreatedAt=" + transactionCreatedAt + "]";
	}
	
	
}
