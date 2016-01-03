package com.discountasciiwarehouse.ecommerce.bean;

import java.io.Serializable;
import java.util.List;

public class RecentPurchaseDTO extends ProductDTO implements Serializable {
	private static final long serialVersionUID = -8477872742397648817L;

	private List<String> recent;
	
	public List<String> getRecent() {
		return recent;
	}
	public void setRecent(List<String> recent) {
		this.recent = recent;
	}
	
}
