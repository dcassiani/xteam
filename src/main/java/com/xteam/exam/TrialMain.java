package com.xteam.exam;

import java.util.Calendar;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class TrialMain {

	public static void main(String[] args) {
		String urlApiTasks = "http://localhost:8000/api/users/";
		String urlRet = null;
		try {

            JsonElement taskRequest = null; //trocar para CRIAR POJO DE PERGUNTA
			ClientResponse response = executeRequestWithJsonHeaders(urlApiTasks , new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create().toJson(taskRequest),
                    HttpMethod.POST);

            
            GsonBuilder deserializerBuilder = new GsonBuilder().registerTypeAdapter(Calendar.class,
                    new CalendarTypeConverter());

//            DomainAutoRenewCheckResponse autoRenewCheckResponse = deserializerBuilder.create().fromJson(entity,
//                    DomainAutoRenewCheckResponse.class);
//            
//            if (responseStatusIsNotAccepted(response)) {

                String responseReturned = response.toString();

                int status = response.getStatus();

                String entity = response.getEntity(String.class);

//            } else {
//                urlRet = response.getEntity(String.class);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

	}

    private static ClientResponse executeRequestWithJsonHeaders(String url, String jsonBody, String method) throws Exception {

        Client client = Client.create();
        WebResource webResource = client.resource(url);

        if (jsonBody != null) {
            return webResource.type(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON)
                    .method(method, ClientResponse.class, jsonBody);
        }

        return webResource.type(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON)
                .method(method, ClientResponse.class);

    }
    
    /**
     * Metodo que verificar se chamada foi 200 OK
     * 
     * @param response
     * @return
     */
    private boolean responseStatusIsNotOk(ClientResponse response) {
        return response == null || (response.getStatus() != ClientResponse.Status.OK.getStatusCode());
    }

    /**
     * Metodo que verificar se chamada foi ACCEPTED OK
     * 
     * @param response
     * @return
     */
    private boolean responseStatusIsNotAccepted(ClientResponse response) {
        return response == null || (response.getStatus() != ClientResponse.Status.ACCEPTED.getStatusCode());
    }
}
