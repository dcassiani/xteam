package com.discountasciiwarehouse.ecommerce.proxy;

import javax.annotation.PostConstruct;
import javax.ws.rs.HttpMethod;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.discountasciiwarehouse.ecommerce.bean.ProductContainer;
import com.discountasciiwarehouse.ecommerce.bean.RecentPurchaseDTO;
import com.google.gson.Gson;
import com.sun.jersey.api.client.ClientResponse;

@Service("productProxy")
public class ProductProxyImpl extends JsonProxy implements ProductProxy {

	private final static String PRODUCT_METHOD = "products/";

	private Logger logger = Logger.getLogger(ProductProxyImpl.class);

	@PostConstruct
	public void init() {
		logger.info("ProductProxyImpl started over CDI with " + SOURCE_URL);
	}

	/**
	 * request info about the products
	 */
	public RecentPurchaseDTO getProductInfo(Long productId) {
		String urlDataAcess = SOURCE_URL.concat(PRODUCT_METHOD).concat(
				productId.toString());
		try {
			ClientResponse response = executeRequestJson(urlDataAcess, null,
					HttpMethod.GET);
			String entity = response.getEntity(String.class);
			ProductContainer container = new Gson().fromJson(entity,
					ProductContainer.class);
			return container.getProduct();
		} catch (Exception e) {
			logger.error("ProductProxyImpl.getProductInfo(" + productId + ")", e);
		}
		return null;
	}

}
