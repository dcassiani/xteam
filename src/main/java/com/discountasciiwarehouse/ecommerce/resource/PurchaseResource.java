package com.discountasciiwarehouse.ecommerce.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.discountasciiwarehouse.ecommerce.bean.RecentPurchaseWrapper;
import com.discountasciiwarehouse.ecommerce.bean.UserDTO;
import com.discountasciiwarehouse.ecommerce.business.RecentPurchaseBusiness;
import com.google.gson.GsonBuilder;

@Path("api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PurchaseResource {

	private static final String USER_NOT_FOUND = "User with username of {username} was not found";
	
	@Autowired
	RecentPurchaseBusiness recentPurchaseBusiness; 
	
    @GET
    @Path("recent_purchases/{username}")
    public String recentPurchases(@PathParam("username") final String username) {
    	
    	UserDTO user = recentPurchaseBusiness.getUser(username);
    	if (user == null) {
    		return USER_NOT_FOUND.replace("{username}", username);	
    	} 
    	
    	RecentPurchaseWrapper ret = recentPurchaseBusiness.getRecentPurchaseList(username);
    	
    	GsonBuilder gsonBuilder = new GsonBuilder();
    	
        return gsonBuilder.create().toJson(ret);
        
    }
}
