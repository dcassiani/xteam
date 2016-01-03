package com.discountasciiwarehouse.ecommerce.proxy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.HttpMethod;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.discountasciiwarehouse.ecommerce.bean.PurchaseContainer;
import com.discountasciiwarehouse.ecommerce.bean.PurchaseDTO;
import com.discountasciiwarehouse.ecommerce.bean.UserDTO;
import com.discountasciiwarehouse.ecommerce.proxy.adapter.CalendarTypeConverter;
import com.google.gson.GsonBuilder;
import com.sun.jersey.api.client.ClientResponse;

@Service("purchaseProxy")
public class PurchaseProxyImpl extends JsonProxy implements PurchaseProxy{

	private final static String PURCHASE_METHOD = "purchases/";
	private final static String BY_USER_FILTER = "by_user/";
	private final static String BY_PRODUCT_FILTER = "by_product/";
	private final static String PARAM_LIMIT_5 = "limit=5";

	private Logger logger = Logger.getLogger(PurchaseProxyImpl.class);

	
	@PostConstruct
	public void init(){
		logger.info("PurchaseProxyImpl started over CDI with " + SOURCE_URL);
	}
	
	/**
	 * fetch 5 recent purchases for the user
	 */
	public List<PurchaseDTO> getUserRecentPurchasesList(UserDTO user) {
		if ((user == null) || (user.getUsername() == null)){
			return new ArrayList<PurchaseDTO>();
		}
		
		String urlDataAcess = SOURCE_URL
				.concat(PURCHASE_METHOD)
				.concat(BY_USER_FILTER)
				.concat(user.getUsername())
				.concat("?")
				.concat(PARAM_LIMIT_5);
		
		try{
			ClientResponse response = executeRequestJson(urlDataAcess, null, HttpMethod.GET);
			String entity = response.getEntity(String.class);
			GsonBuilder deserializerBuilder = new GsonBuilder().registerTypeAdapter(Calendar.class,
                new CalendarTypeConverter());
			PurchaseContainer container = deserializerBuilder.create().fromJson(entity, PurchaseContainer.class);
			return container.getPurchases();
	          
        } catch (Exception e) {
        	logger.error("PurchaseProxyImpl.getUserRecentPurchasesList(" + user.getUsername() +")", e);
        }
		return new ArrayList<PurchaseDTO>();
	}



	/**
	 * for each of the products, get a list of all people 
	 * who previously purchased that product 
	 */
	public List<String> getProductPurchaseUsers(Long productId) {
		String urlDataAcess = SOURCE_URL
				.concat(PURCHASE_METHOD)
				.concat(BY_PRODUCT_FILTER)
				.concat(productId.toString());
		
		List<String> ret = new ArrayList<String>();
		try{
			ClientResponse response = executeRequestJson(urlDataAcess, null, HttpMethod.GET);
			String entity = response.getEntity(String.class);
			GsonBuilder deserializerBuilder = new GsonBuilder().registerTypeAdapter(Calendar.class,
	                new CalendarTypeConverter());

			PurchaseContainer container = deserializerBuilder.create().fromJson(entity, PurchaseContainer.class);
			for (PurchaseDTO purchase: container.getPurchases()){
				ret.add(purchase.getUsername());
			}
	          
        } catch (Exception e) {        	
        	logger.error("PurchaseProxyImpl.getProductPurchaseUsers(" + productId +")", e);
        }
		return ret;

	}


	
}
