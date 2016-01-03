package com.discountasciiwarehouse.ecommerce.proxy;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JsonProxy{

	
	protected final static String SOURCE_URL = getDawPurchasesApiUrl();

	protected ClientResponse executeRequestJson(String url, 
    		String jsonBody, String method) throws Exception {

		Client client = Client.create();
        WebResource webResource = client.resource(url);

        if (jsonBody != null) {
            return webResource.type(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON)
                    .method(method, ClientResponse.class, jsonBody);
        }

        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON_TYPE)
        		.accept(MediaType.APPLICATION_JSON)
                .method(method, ClientResponse.class);
        
		if (responseStatusIsNotOk(response)) {
			 throw new Exception ("Failure connecting to datasource. Response code was " + response.getStatus());	
		}
		
		return response;
    }
    
    private boolean responseStatusIsNotOk(ClientResponse response) {
        return response == null || (response.getStatus() != ClientResponse.Status.OK.getStatusCode());
    }

    private static String getDawPurchasesApiUrl(){
    	String url = null;
    	try {
    			url = System.getProperty("com.discountasciiwarehouse.ecommerce.outside.resource"); 
    			if ((url == null) || (url.isEmpty())) {
    				String configError = "Failure executing System.getProperty"
    						+ "(com.discountasciiwarehouse.ecommerce.outside.resource)"
    						+ "  Was the system-properties set at the "
    						+ "WILDFLY_PATH/standalone/configuration/standalone.xml?";
    				throw new Exception (configError);
    			}
    	}catch (Exception e){
    		e.printStackTrace();
    		url = "http://localhost:8000/api/";
    	}
    	return url;
    }
    
}
