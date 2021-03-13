package com.spring.jpa.postgresql.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "LR_bill")
public class Bill {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer billId;
	@Column(name = "userId")
	private Integer userId;
	@Column(name = "totalAmount")
	private String totalAmount;
	@Column(name = "orderId")
	private String orderId;
	@Column(name = "paymentMode")
	private String paymentMode;
	@Column(name = "paymentStatus")
	private String paymentStatus;
	public Integer getBillId() {
		return billId;
	}
	public void setBillId(Integer billId) {
		this.billId = billId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public Bill() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Bill(Integer billId, Integer userId, String totalAmount, String orderId, String paymentMode,
			String paymentStatus) {
		super();
		this.billId = billId;
		this.userId = userId;
		this.totalAmount = totalAmount;
		this.orderId = orderId;
		this.paymentMode = paymentMode;
		this.paymentStatus = paymentStatus;
	}
	public Bill(Integer userId, String totalAmount, String orderId, String paymentMode,
			String paymentStatus) {
		super();
		this.userId = userId;
		this.totalAmount = totalAmount;
		this.orderId = orderId;
		this.paymentMode = paymentMode;
		this.paymentStatus = paymentStatus;
	}

}
