package com.discountasciiwarehouse.ecommerce.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.discountasciiwarehouse.ecommerce.bean.RecentPurchaseDTO;
import com.discountasciiwarehouse.ecommerce.bean.UserDTO;
import com.discountasciiwarehouse.ecommerce.business.RecentPurchaseBusiness;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.api.client.ClientResponse.Status;

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
	public Response recentPurchases(@PathParam("username") final String username, @Context Request request) {

		logger.info(" GET /recent_purchases/"+ username +" RECEIVED");
		Gson gsonBuilder = new GsonBuilder().create();
		try {

			UserDTO user = recentPurchaseBusiness.getUser(username);
			if (user == null) {
				logger.info(" GET /recent_purchases/"+ username +" ENDED - NOT SUCH USER");
				return setResponseWithCacheHeaders(Status.OK, gsonBuilder
						.toJson(USER_NOT_FOUND.replace("{username}", username)), request);
			}

			List<RecentPurchaseDTO> ret = recentPurchaseBusiness
					.getRecentPurchaseList(user);

			return setResponseWithCacheHeaders(Status.OK, gsonBuilder.toJson(ret), request);
		} catch (Exception e) {
			logger.info(" GET /recent_purchases/"+ username +" ENDED WITHOUT RESULTS");
			logger.error("PurchaseResource.recentPurchases(" + username +")", e);			
			return setResponseWithCacheHeaders(Status.INTERNAL_SERVER_ERROR, gsonBuilder.toJson(e.getMessage()), request);
		}
	}
	
	private Response setResponseWithCacheHeaders(Status status, String ret, Request request){

	    CacheControl cc = new CacheControl();
	    cc.setMaxAge(-1); 		// forever, so browser will use his version unless ETag hash changes 
	    cc.setPrivate(true);	// this browser only
	    cc.setNoStore(true);	// this browser RAM only
	    EntityTag etag = new EntityTag(Integer.toString(ret.hashCode()));
	    ResponseBuilder builder = request.evaluatePreconditions(etag);

	    if (builder == null){
		    if (Status.OK.equals(status)){
		    	builder = Response.ok(ret);
		    } else {
		    	builder = Response.status(status);
		    }
		    builder.tag(etag);
	    } else {
	    	builder = Response.notModified(etag);
	    }
	    
	    builder.cacheControl(cc);
	    return builder.build();
	}
}
