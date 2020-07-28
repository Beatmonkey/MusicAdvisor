package advisor.controller;

import advisor.model.ApplicationSettings;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestService {

    ApplicationSettings applicationSettings;

    public RestService(ApplicationSettings applicationSettings) {
        this.applicationSettings = applicationSettings;
    }

    public String getAccessToken() {
        System.out.println("making http request for access_token...");
        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .uri(URI.create(applicationSettings.siteUrl + "/api/token?"))
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=authorization_code&" +
                        "code=" + applicationSettings.code + "&" +
                        "client_id=" + applicationSettings.clientId + "&" +
                        "client_secret=" + applicationSettings.clientSecret + "&" +
                        "redirect_uri=" + applicationSettings.redirectUri))
                .build();

        try {
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.body().contains("access_token")) {
                JsonObject jo = JsonParser.parseString(response.body()).getAsJsonObject();
                System.out.println(jo.get("access_token").getAsString());
                return jo.get("access_token").getAsString();
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            System.out.println("Error");
        }

        return null;
    }

    public JsonObject getJsonObject(String apiPath) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .header("Authorization", "Bearer " + applicationSettings.accessToken)
                .uri(URI.create(apiPath))
                .GET()
                .build();
        try {
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            JsonObject jo = JsonParser.parseString(response.body()).getAsJsonObject();
            return jo;
        } catch (InterruptedException | IOException | IllegalStateException e) {
            throw new RuntimeException(e);
        }

    }
}