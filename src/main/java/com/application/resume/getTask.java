package com.application.resume;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class getTask {
	private static final String BaseURL = "https://career.netizenexperience.com/api/resume";
	
	public static void main(String[] args) {
		String email = "muhammadafiqzzuddin@gmail.com";
		try {
			sendGetRequest(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void sendGetRequest(String email) throws Exception {
		String urlWithParams = BaseURL + "?email=" + email;
		
		try (CloseableHttpClient httpClient = HttpClients.createDefault()){
			HttpGet request = new HttpGet(urlWithParams);
			HttpResponse response = httpClient.execute(request);
			
			//Get the response
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				//return it as a string
				String result = EntityUtils.toString(entity);
				System.out.println(result);
			}
		}
	}
	
}
