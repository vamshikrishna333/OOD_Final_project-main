package com.spring.jpa.postgresql.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LR_user")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	@Column(name = "fName")
	private String fName;
	@Column(name = "lName")
	private String lName;
	@Column(name = "mobileNumber")
	private String mobileNumber;
	@Column(name = "email")
	private String email;
	@Column(name = "createdOn")
	private Date createdOn;
	@Column(name = "userType")
	private String userType;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public User(Integer userId, String fName, String lName, String mobileNumber, String email) {
		super();
		this.userId = userId;
		this.fName = fName;
		this.lName = lName;
		this.mobileNumber = mobileNumber;
		this.email = email;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Integer userId, String fName, String lName, String mobileNumber, String email, Date createdOn,
			String userType) {
		super();
		this.userId = userId;
		this.fName = fName;
		this.lName = lName;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.createdOn = createdOn;
		this.userType = userType;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
}
