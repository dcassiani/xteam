package com.discountasciiwarehouse.ecommerce.proxy;

import java.util.List;

import com.discountasciiwarehouse.ecommerce.bean.PurchaseDTO;
import com.discountasciiwarehouse.ecommerce.bean.UserDTO;


public interface PurchaseProxy {

	public List<PurchaseDTO> getUserRecentPurchasesList(UserDTO user);
	
	public List<String> getProductPurchaseUsers(Long productId);
}
