package com.discountasciiwarehouse.ecommerce.bean;

import java.io.Serializable;
import java.util.Calendar;

public class PurchaseDTO implements Serializable{
	private static final long serialVersionUID = -6723230180831129692L;

	
	private Long id;
	private String username;
	private Long productId;
	private Calendar date; //2015-12-17T04:34:31.278Z
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
}
