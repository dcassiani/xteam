package com.discountasciiwarehouse.ecommerce.bean;

import java.io.Serializable;

public class ProductDTO implements Serializable {
	private static final long serialVersionUID = 8534813074258224710L;

	private Long id;
	private String face;
	private Double price;
	private Integer size;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFace() {
		return face;
	}
	public void setFace(String face) {
		this.face = face;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}

}
