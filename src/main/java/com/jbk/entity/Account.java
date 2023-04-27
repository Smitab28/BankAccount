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
public class Account {

	
	@Column(unique=true)
	@NotEmpty(message="Account number is manadetory")
	@Id
	private String accountNumber;
	@OneToOne
	private User user;
	@Column(unique=false)
	@NotEmpty(message="Account name is manadatory")
	private String accountName;
	@Column(unique=false)
	@NotEmpty(message="Account type is manadatory")
	private String accountType;
	@Column(unique=false)
	private double accountBalance;
	private LocalDateTime updatedAt;
	private LocalDateTime createdAt;
	
	public Account() {}

	public Account( String accountNumber, User user, String accountName, String accountType,
			double accountBalance, LocalDateTime updatedAt, LocalDateTime createdAt) {
		super();
		
		this.accountNumber = accountNumber;
		this.user = user;
		this.accountName = accountName;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
		this.updatedAt = updatedAt;
		this.createdAt = createdAt;
	}


	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUserId(User user) {
		this.user = user;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
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
		return "Account [ accountNumber=" + accountNumber + ", user=" + user
				+ ", accountName=" + accountName + ", accountType=" + accountType + ", accountBalance=" + accountBalance
				+ ", updatedAt=" + updatedAt + ", createdAt=" + createdAt + "]";
	}

	
	
}
