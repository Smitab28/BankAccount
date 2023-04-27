package com.jbk.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Payment {

	@Id
	private int paymentId;
	@OneToOne
	private Account account;
	private String beneficiary;
	private String beneficiaryAccountNumber;
	private double amount;
	private int referenceNumber;
	private String status;
	private String reasonCode;
	
	private LocalDateTime createdAt;
	
	public Payment() {}

	public Payment(int paymentId, Account account, String beneficiary, String beneficiaryAccountNumber, double amount,
			int referenceNumber, String status, String reasonCode, LocalDateTime createdAt) {
		super();
		this.paymentId = paymentId;
		this.account = account;
		this.beneficiary = beneficiary;
		this.beneficiaryAccountNumber = beneficiaryAccountNumber;
		this.amount = amount;
		this.referenceNumber = referenceNumber;
		this.status = status;
		this.reasonCode = reasonCode;
		this.createdAt = createdAt;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(String beneficiary) {
		this.beneficiary = beneficiary;
	}

	public String getBeneficiaryAccountNumber() {
		return beneficiaryAccountNumber;
	}

	public void setBeneficiaryAccountNumber(String beneficiaryAccountNumber) {
		this.beneficiaryAccountNumber = beneficiaryAccountNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(int referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", account=" + account + ", beneficiary=" + beneficiary
				+ ", beneficiaryAccountNumber=" + beneficiaryAccountNumber + ", amount=" + amount + ", referenceNumber="
				+ referenceNumber + ", status=" + status + ", reasonCode=" + reasonCode + ", createdAt=" + createdAt
				+ "]";
	}


	
	
}
