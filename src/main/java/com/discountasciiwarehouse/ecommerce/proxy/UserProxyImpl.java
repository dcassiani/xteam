package com.discountasciiwarehouse.ecommerce.proxy;

import javax.annotation.PostConstruct;
import javax.ws.rs.HttpMethod;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.discountasciiwarehouse.ecommerce.bean.UserContainer;
import com.discountasciiwarehouse.ecommerce.bean.UserDTO;
import com.google.gson.Gson;
import com.sun.jersey.api.client.ClientResponse;

@Service("userProxy")
public class UserProxyImpl extends JsonProxy implements UserProxy{

	private final static String USERS_METHOD = "users/";
		
	private Logger logger = Logger.getLogger(UserProxyImpl.class);

	
	@PostConstruct
	public void init(){
		logger.info("UserProxyImpl started over CDI with with " + SOURCE_URL);
	}
	
	public UserDTO getUser(String username) {
		if (username == null){
			return null;
		}
		
		String urlDataAcess = SOURCE_URL.concat(USERS_METHOD).concat(username);
		
		try{
			ClientResponse response = executeRequestJson(urlDataAcess, null, HttpMethod.GET);
			String entity = response.getEntity(String.class);
			UserContainer container = new Gson().fromJson(entity, UserContainer.class);
			return container.getUser();
	          
        } catch (Exception e) {
        	logger.error("UserProxyImpl.getUser(" + username +")", e);
        }
		return null;
	}

}
