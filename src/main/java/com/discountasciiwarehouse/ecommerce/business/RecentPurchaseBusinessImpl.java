package com.discountasciiwarehouse.ecommerce.business;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.discountasciiwarehouse.ecommerce.bean.RecentPurchaseWrapper;
import com.discountasciiwarehouse.ecommerce.bean.UserContainer;
import com.discountasciiwarehouse.ecommerce.bean.UserDTO;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Component
public class RecentPurchaseBusinessImpl implements RecentPurchaseBusiness{

	/**
	//fetch 5 recent purchases for the user 
	purchases/by_user/:username?limit=5

	//for each of those products, get a list of all people who previously purchased that product 
	purchases/by_product/:product_id

	//at the same time, request info about the products
	products/:product_id
		 **/

	
	private final static String SOURCE_URL = "http://localhost:8000/api/";
	
	private final static String USERS_METHOD = "users/";
	private final static String PURCHASE_METHOD = "purchases/";
	private final static String PRODUCT_METHOD = "products/";
	
	private final static String BY_USER_FILTER = "by_user/";
	private final static String BY_PRODUCT_FILTER = "by_product/";
	
	@Override
	public RecentPurchaseWrapper getRecentPurchaseList(String username){
		String urlDataAcess = null;
		try {

//            JsonElement taskRequest = null; //trocar para CRIAR POJO DE PERGUNTA
//			ClientResponse response = executeRequestWithJsonHeaders(urlApiTasks , new GsonBuilder()
//                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create().toJson(taskRequest),
//                    HttpMethod.GET);

			ClientResponse response = executeRequestWithJsonHeaders(urlDataAcess, null, HttpMethod.GET);
            
//            GsonBuilder deserializerBuilder = new GsonBuilder().registerTypeAdapter(Calendar.class,
//                    new CalendarTypeConverter());

//            DomainAutoRenewCheckResponse autoRenewCheckResponse = deserializerBuilder.create().fromJson(entity,
//                    DomainAutoRenewCheckResponse.class);
//            
//            if (responseStatusIsNotAccepted(response)) {

                String responseReturned = response.toString();

                int status = response.getStatus();

                String entity = response.getEntity(String.class);

                System.out.println(responseReturned);
                System.out.println(entity);
                
//            } else {
//                urlRet = response.getEntity(String.class);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;

	}

    private static ClientResponse executeRequestWithJsonHeaders(String url, 
    		String jsonBody, String method) throws Exception {

        Client client = Client.create();
        WebResource webResource = client.resource(url);

        if (jsonBody != null) {
            return webResource.type(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON)
                    .method(method, ClientResponse.class, jsonBody);
        }

        return webResource.type(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON)
                .method(method, ClientResponse.class);
    }
    
    private boolean responseStatusIsNotOk(ClientResponse response) {
        return response == null || (response.getStatus() != ClientResponse.Status.OK.getStatusCode());
    }

    private boolean responseStatusIsNotAccepted(ClientResponse response) {
        return response == null || (response.getStatus() != ClientResponse.Status.ACCEPTED.getStatusCode());
    }

    @Override
	public UserDTO getUser(String username) {
		if (username == null){
			return null;
		}
		
		String urlDataAcess = SOURCE_URL.concat(USERS_METHOD).concat(username);
		
		try{
			ClientResponse response = executeRequestWithJsonHeaders(urlDataAcess, null, HttpMethod.GET);
	        
			if (responseStatusIsNotOk(response)) {
				 return null;	
			}
			
			String entity = response.getEntity(String.class);
			UserContainer userList = new Gson().fromJson(entity, UserContainer.class);
			return userList.getUser();
	          
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
	}

}
