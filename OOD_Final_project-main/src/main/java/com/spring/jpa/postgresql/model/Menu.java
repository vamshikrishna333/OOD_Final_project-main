package com.spring.jpa.postgresql.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "LR_menu")
public class Menu {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer foodId;
	@Column(name = "foodItem")
	private String foodItem;
	@Column(name = "price")
	private Float price;

	public Integer getFoodId() {
		return foodId;
	}
	
	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}
	
	public String getFoodItem() {
		return foodItem;
	}
	
	public void setFoodItem(String foodItem) {
		this.foodItem = foodItem;
	}
	
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
	public Menu(String foodItem, Float price) {
		super();
		this.foodItem = foodItem;
		this.price = price;
	}
	public Menu(Integer foodId, String foodItem, Float price) {
		super();
		this.foodId = foodId;
		this.foodItem = foodItem;
		this.price = price;
	}
	public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
