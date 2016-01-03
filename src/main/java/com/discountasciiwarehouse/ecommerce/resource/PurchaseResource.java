package com.discountasciiwarehouse.ecommerce.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.discountasciiwarehouse.ecommerce.bean.RecentPurchaseDTO;
import com.discountasciiwarehouse.ecommerce.bean.UserDTO;
import com.discountasciiwarehouse.ecommerce.business.RecentPurchaseBusiness;
import com.google.gson.GsonBuilder;

@Controller
@Path("recent_purchases")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PurchaseResource {

	private Logger logger = Logger.getLogger(PurchaseResource.class);
	private static final String USER_NOT_FOUND = "User with username of {username} was not found";

	@Inject
	RecentPurchaseBusiness recentPurchaseBusiness;

	@GET
	@Path("/{username}")
	public String recentPurchases(@PathParam("username") final String username) {

		logger.info(" GET /recent_purchases/"+ username +" RECEIVED");
		try {

			UserDTO user = recentPurchaseBusiness.getUser(username);
			if (user == null) {
				logger.info(" GET /recent_purchases/"+ username +" ENDED - NOT SUCH USER");
				return USER_NOT_FOUND.replace("{username}", username);
			}

			List<RecentPurchaseDTO> ret = recentPurchaseBusiness
					.getRecentPurchaseList(user);

			return new GsonBuilder().create().toJson(ret);
		} catch (Exception e) {
			logger.info(" GET /recent_purchases/"+ username +" ENDED WITHOUT RESULTS");
			logger.error("PurchaseResource.recentPurchases(" + username +")", e);			
			return null;
			// silent response for less noise for a client.
			// on requisite demand: change method for ClientResponse returns and
			// for a ClientResponse.Status.INTERNAL_SERVER_ERROR here
		}
	}
}
