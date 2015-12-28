package com.discountasciiwarehouse.ecommerce.bean;

import java.io.Serializable;

public class UserContainer implements Serializable{
	private static final long serialVersionUID = 9024388139990710853L;
	
	private UserDTO user;

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
}
