package com.application.resume;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import org.json.JSONObject;
import org.json.JSONException;

public class App 
{
    public static void main( String[] args )
    {
    	try {
        	//Email query
        	String email = "muhammadafiqzzuddin11@gmail.com";
        	String name = "Muhammad Afiq Izzuddin bin Rosli";
        	Path pdfPath = Path.of("C:\\Users\\aafq7\\Downloads\\MuhammadAfiqIzzuddin.pdf");
        	
        	//Read the pdf file and encode it in Base64
        	byte[] pdfBytes = Files.readAllBytes(pdfPath);
        	String base64File = Base64.getEncoder().encodeToString(pdfBytes);
        	
        	//Create a JSON object with the required parameters
        	JSONObject jsonObject = new JSONObject();
        	
        	jsonObject.put("name", name);
        	jsonObject.put("email", email);
        	jsonObject.put("file", base64File);
        	
        	//The API endpoint URL
        	String apiURL = "https://career.netizenexperience.com/api/resume";
        	
        	//Create an instance of HttpClient
        	HttpClient client = HttpClient.newHttpClient();
        	
        	//Create an HttpRequest object
        	HttpRequest request = HttpRequest.newBuilder()
        			.uri(URI.create(apiURL))
        			.header("Content-Type", "application/json")
        			.POST(HttpRequest.BodyPublishers.ofString(jsonObject.toString()))
        			.build();
        	
        	//Send the request and handle the response
        	HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        	
        	//Check if the request was successful
        	if(response.statusCode() == 200) {
        		//Print the response to the console
        		System.out.println("Response: ");
        		System.out.println(response.body());
        	} else {
        		//Print the error status and reason
        		System.out.println("Error: " + response.statusCode());
        	}
        } catch (IOException | InterruptedException | JSONException e) {
    		//Handle any errors that occurred during the request
    		System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
