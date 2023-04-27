package com.jbk.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	@Column(unique=true)
	@NotEmpty(message="User Name is Mandatory")
	private String userName;
	@Column(unique=false)
	@NotEmpty(message="First Name is Mandatory")
	private String firstName;
	@Column(unique=false)
	@NotEmpty(message="Last Name is Mandatory")
	private String lastName;
	@Column(unique=true)
	@Email
	private String emailId;
	@Column(unique=true)
	@NotEmpty(message="Mobile number is manadatory")
	private String mobileNo;
	@Column(unique=true)
	@NotNull
	private String password;
	private String token;
	private String code;
	private int verified;
	private LocalDateTime createdAt;
	private LocalDateTime verifiedAt;
	private LocalDateTime updatedAt;
	

	public User() {}


	public User(long userId, @NotEmpty(message = "First Name is Mandatory") String firstName,
			@NotEmpty(message = "Last Name is Mandatory") String lastName, @Email String emailId,
			@NotEmpty(message = "Mobile number is manadatory") String mobileNo, @NotNull String password, String token,
			String code, int verified, LocalDateTime createdAt, LocalDateTime verifiedAt, LocalDateTime updatedAt) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.mobileNo = mobileNo;
		this.password = password;
		this.token = token;
		this.code = code;
		this.verified = verified;
		this.createdAt = createdAt;
		this.verifiedAt = verifiedAt;
		this.updatedAt = updatedAt;
	}

	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getUserId() {
		return userId;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getMobileNo() {
		return mobileNo;
	}


	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public int getVerified() {
		return verified;
	}


	public void setVerified(int verified) {
		this.verified = verified;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	public LocalDateTime getVerifiedAt() {
		return verifiedAt;
	}


	public void setVerifiedAt(LocalDateTime verifiedAt) {
		this.verifiedAt = verifiedAt;
	}


	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ ", mobileNo=" + mobileNo + ", password=" + password + ", token=" + token + ", code=" + code
				+ ", verified=" + verified + ", createdAt=" + createdAt + ", verifiedAt=" + verifiedAt + ", updatedAt="
				+ updatedAt + "]";
	}

	

	
	
}
