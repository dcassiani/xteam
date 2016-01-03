package com.discountasciiwarehouse.ecommerce.proxy;

import com.discountasciiwarehouse.ecommerce.bean.UserDTO;


public interface UserProxy {

	public UserDTO getUser(String username);
}
