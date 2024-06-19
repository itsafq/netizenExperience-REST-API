import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        //email to query
        String email = "muhammadafiqzzuddin11@gmail.com";

        //API endopoint URL
        String apiURL = "https://career.netizenexperience.com/api/resume";

        //Create an instance of HttpClient
        HttpClient client = HttpClient.newHttpClient();

        //Construct teh full request URL
        String requestURL = apiURL + "?email=" + email;

        //Create an HttpRequest object
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(requestURL)).build();

        //Send the request and handle the response
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
        .thenApply(HttpResponse::body)
        .thenAccept(responseBody -> {
            //Print the response to the console
            System.out.println("Response:");
            System.out.println(responseBody);
        })
        .exceptionally(ex -> {
            //Handle any errors that occured during the request 
            System.err.println("An error occurred: " + ex.getMessage());
            return null;
        }).join();
    }
}