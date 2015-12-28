package com.discountasciiwarehouse.ecommerce.business;

import com.discountasciiwarehouse.ecommerce.bean.RecentPurchaseWrapper;
import com.discountasciiwarehouse.ecommerce.bean.UserDTO;

public interface RecentPurchaseBusiness {

	public RecentPurchaseWrapper getRecentPurchaseList(String username);
	public UserDTO getUser(String username);
	
}
