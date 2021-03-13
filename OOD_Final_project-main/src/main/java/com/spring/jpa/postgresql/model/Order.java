package com.spring.jpa.postgresql.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LR_order")
public class Order {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	@Column(name = "userId")
	private String userId;
	@Column(name = "quantity")
	private String quantity;
	@Column(name = "foodId")
	private Integer foodId;
	@Column(name = "createdOn")
	private Timestamp createdOn;
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getFoodId() {
		return foodId;
	}
	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}
	public Timestamp getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}
	public Order(Integer orderId, String userId, String quantity, Integer foodId, Timestamp createdOn) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.quantity = quantity;
		this.foodId = foodId;
		this.createdOn = createdOn;
	}
	
}
