package com.discountasciiwarehouse.ecommerce.bean;

import java.io.Serializable;
import java.util.List;

public class RecentPurchaseDTO implements Serializable {
	private static final long serialVersionUID = -8477872742397648817L;

	private ProductDTO product;
	private List<String> recent;
	
	public ProductDTO getProduct() {
		return product;
	}
	public void setProduct(ProductDTO product) {
		this.product = product;
	}
	public List<String> getRecent() {
		return recent;
	}
	public void setRecent(List<String> recent) {
		this.recent = recent;
	}
	
}
