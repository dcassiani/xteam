package com.discountasciiwarehouse.ecommerce.bean;

import java.io.Serializable;
import java.util.List;

public class RecentPurchaseWrapper implements Serializable{
	private static final long serialVersionUID = -3425742843996802617L;

	
	private List<RecentPurchaseDTO> recentPurchaseList;
	
	
	public List<RecentPurchaseDTO> getRecentPurchaseList() {
		return recentPurchaseList;
	}
	public void setRecentPurchaseList(List<RecentPurchaseDTO> recentPurchaseList) {
		this.recentPurchaseList = recentPurchaseList;
	}

}
