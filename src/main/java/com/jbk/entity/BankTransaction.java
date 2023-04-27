package com.jbk.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import javax.validation.constraints.NotEmpty;

@Entity
public class BankTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int transactionId;
	@OneToOne
	private Account account;
	@Column(unique=false)
	@NotEmpty(message="Transaction type is manadetory")
	private String transactionType;
	@Column(unique=false)
	private double amount;
	@Column(unique=false)
	//@NotEmpty(message="Status is manadatory")
	private String status;
	@Column(unique=false)
	private String source;
	@Column(unique=false)
	private String reasonCode;
	private LocalDateTime updatedAt;
	private LocalDateTime createdAt;
	
	public BankTransaction() {}

	public BankTransaction(int transactionId, Account account, String transactionType, double amount, String status,
			String source, String reasonCode, LocalDateTime updatedAt, LocalDateTime createdAt) {
		super();
		this.transactionId = transactionId;
		this.account = account;
		this.transactionType = transactionType;
		this.amount = amount;
		this.status = status;
		this.source = source;
		this.reasonCode = reasonCode;
		this.updatedAt = updatedAt;
		this.createdAt = createdAt;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccountId(Account account) {
		this.account = account;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", account=" + account + ", transactionType="
				+ transactionType + ", amount=" + amount + ", status=" + status + ", source=" + source + ", reasonCode="
				+ reasonCode + ", updatedAt=" + updatedAt + ", createdAt=" + createdAt + "]";
	}
	
	
}
