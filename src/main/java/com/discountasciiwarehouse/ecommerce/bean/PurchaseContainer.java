package com.discountasciiwarehouse.ecommerce.bean;

import java.io.Serializable;
import java.util.List;

public class PurchaseContainer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2449081848940743676L;
	private List<PurchaseDTO> purchases;

	public List<PurchaseDTO> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<PurchaseDTO> purchases) {
		this.purchases = purchases;
	}
	
}
