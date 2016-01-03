package com.discountasciiwarehouse.ecommerce.business;

import java.util.List;

import com.discountasciiwarehouse.ecommerce.bean.RecentPurchaseDTO;
import com.discountasciiwarehouse.ecommerce.bean.UserDTO;

public interface RecentPurchaseBusiness {

	public UserDTO getUser(String username);

	public List<RecentPurchaseDTO> getRecentPurchaseList(UserDTO user);
	
}
