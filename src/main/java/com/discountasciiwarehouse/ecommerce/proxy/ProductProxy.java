package com.discountasciiwarehouse.ecommerce.proxy;

import com.discountasciiwarehouse.ecommerce.bean.RecentPurchaseDTO;


public interface ProductProxy {
	
	public RecentPurchaseDTO getProductInfo(Long productId);
	
}
