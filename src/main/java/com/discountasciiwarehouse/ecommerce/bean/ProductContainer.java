package com.discountasciiwarehouse.ecommerce.bean;

import java.io.Serializable;

public class ProductContainer implements Serializable {
	private static final long serialVersionUID = -3805619726108735030L;

	private RecentPurchaseDTO product;

	public RecentPurchaseDTO getProduct() {
		return product;
	}

	public void setProduct(RecentPurchaseDTO product) {
		this.product = product;
	}
}
