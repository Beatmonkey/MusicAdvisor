package advisor;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        boolean isAccessed = false;

        String access = "";

        if (args.length != 0 && args[0].contains("-access")) {
            access = args[1];
        } else {
            access = "https://accounts.spotify.com";
        }


        NewReleases newReleases = new NewReleases();
        newReleases.addNewReleases("Mountains [Sia, Diplo, Labrinth]");
        newReleases.addNewReleases("Runaway [Lil Peep]");
        newReleases.addNewReleases("The Greatest Show [Panic! At The Disco]");
        newReleases.addNewReleases("All Out Life [Slipknot]");

        Featured featured = new Featured();
        featured.addFeatured("Mellow Morning");
        featured.addFeatured("Wake Up and Smell the Coffee");
        featured.addFeatured("Monday Motivation");
        featured.addFeatured("Songs to Sing in the Shower");

        Categories categories = new Categories();
        categories.addCategory("Top List");
        categories.addCategory("Pop");
        categories.addCategory("Mood");
        categories.addCategory("Latin");

        Playlists playlists = new Playlists();
        playlists.addPlaylist("Walk Like A Badass");
        playlists.addPlaylist("Rage Beats");
        playlists.addPlaylist("Arab Mood Booster");
        playlists.addPlaylist("Sunday Stroll");


        ApplicationSettings applicationSettings = new ApplicationSettings();
        MusicLibrary musicLibrary = new MusicLibrary(newReleases, featured, categories, playlists);
        UserInteraction userInteraction = new UserInteraction(applicationSettings, musicLibrary);
        userInteraction.menu();


    }


}


class UserInteraction {

    ApplicationSettings applicationSettings;
    MusicLibrary musicLibrary;

    UserInteraction(ApplicationSettings applicationSettings, MusicLibrary musicLibrary) {
        this.musicLibrary = musicLibrary;
        this.applicationSettings = applicationSettings;

    }

    void menu() throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        boolean isActionZero = false;


        while (!isActionZero) {
            String[] action = scanner.nextLine().split(" ");

            switch (action[0]) {
                case "new": {
                    if (!applicationSettings.isAuthorized) {
                        System.out.println("Please, provide access for application.");
                        break;
                    } else {
                        showNewReleases(action[0]);
                        break;
                    }
                }
                case "featured": {
                    if (!applicationSettings.isAuthorized) {
                        System.out.println("Please, provide access for application.");
                        break;
                    } else {
                        showFeatured(action[0]);
                        break;
                    }
                }
                case "categories": {
                    if (!applicationSettings.isAuthorized) {
                        System.out.println("Please, provide access for application.");
                        break;
                    } else {
                        showCategories(action[0]);
                        break;
                    }
                }
                case "playlists": {
                    if (!applicationSettings.isAuthorized) {
                        System.out.println("Please, provide access for application.");
                        break;
                    } else {
                        String input = (action[0] + " " + action[1]);
                        showPlaylists(input);
                        break;
                    }
                }
                case "auth": {
                    System.out.println("use this link to request the access code:");
                    System.out.println(applicationSettings.createAuthLink());
                    System.out.println("waiting for code...");
                    SimpleHttpServer server = new SimpleHttpServer(applicationSettings);
                    System.out.println("---SUCCESS---");

                    break;
                }
                case "exit": {
                    System.out.println("---GOODBYE!---");
                    isActionZero = true;
                    break;
                }
                default: {
                    System.out.println("Incorrect command!");
                }
            }
        }

    }

    void showNewReleases(String input) {
        System.out.println("---NEW RELEASES---");
        for (String release : musicLibrary.getEntity(input)) {
            System.out.println(release);
        }


    }

    void showFeatured(String input) {
        System.out.println("---FEATURED---");
        for (String feature : musicLibrary.getEntity(input)) {
            System.out.println(feature);
        }
    }

    void showCategories(String input) {
        System.out.println("---CATEGORIES---");
        for (String category : musicLibrary.getEntity(input)) {
            System.out.println(category);
        }

    }

    void showPlaylists(String input) {
        String[] config = input.split(" ");
        String formattedString = String.format("---%s PLAYLISTS---", config[1].toUpperCase());
        System.out.println(formattedString);

        for (String playlist : musicLibrary.getEntity(config[0])) {
            System.out.println(playlist);
        }

    }


}

class MusicLibrary {

    NewReleases newReleases;
    Featured featured;
    Categories categories;
    Playlists playlists;

    MusicLibrary(NewReleases newReleases, Featured featured, Categories categories, Playlists playlists) {
        this.newReleases = newReleases;
        this.featured = featured;
        this.categories = categories;
        this.playlists = playlists;

    }


    List<String> getEntity(String input) {
        if (input.equalsIgnoreCase("new")) {
            return newReleases.getNewReleases();
        } else if (input.equalsIgnoreCase("featured")) {
            return featured.getFeatured();
        } else if (input.equalsIgnoreCase("categories")) {
            return categories.getCategories();
        } else if (input.equalsIgnoreCase("playlists")) {
            return playlists.getPlaylists();
        } else {
            return null;
        }
    }

}

class NewReleases {

    List<String> newReleases;

    NewReleases() {
        this.newReleases = new ArrayList<>();
    }

    List<String> addNewReleases(String release) {
        newReleases.add(release);

        return newReleases;
    }

    public List<String> getNewReleases() {
        return newReleases;
    }
}

class Featured {

    List<String> featured;

    Featured() {
        this.featured = new ArrayList<>();
    }

    List<String> addFeatured(String feature) {
        featured.add(feature);

        return featured;
    }

    public List<String> getFeatured() {
        return featured;
    }
}

class Categories {

    List<String> categories;

    Categories() {
        this.categories = new ArrayList<>();
    }

    List<String> addCategory(String category) {
        categories.add(category);

        return categories;
    }

    public List<String> getCategories() {
        return categories;
    }
}

class Playlists {

    List<String> playlists;

    Playlists() {
        this.playlists = new ArrayList<>();
    }

    List<String> addPlaylist(String playlist) {
        playlists.add(playlist);

        return playlists;
    }

    public List<String> getPlaylists() {
        return playlists;
    }
}

class ApplicationSettings {


    String siteUrl = "https://accounts.spotify.com/";
    String clientId = "fc2c20a7573e4717a3e51b174cd46d5b";
    String clientSecret = "274b0292252947989e885e66f7647274";
    String redirectUri = "http://localhost:8080";
    String code = "";
    boolean isAuthorized = false;

    public ApplicationSettings() {
        this.siteUrl = siteUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.code = code;
    }

    protected String createAuthLink() {
        StringBuilder authLink = new StringBuilder(siteUrl + "authorize?client_id=" + clientId + "&redirect_uri=" + redirectUri + "&response_type=code");

        return authLink.toString();
    }

}

class SimpleHttpServer {
    HttpServer server;
    ApplicationSettings applicationSettings;

    SimpleHttpServer(ApplicationSettings applicationSettings) throws IOException, InterruptedException {
        server = HttpServer.create();
        server.bind(new InetSocketAddress(8080), 0);
        server.start();

        server.createContext("/",
                new HttpHandler() {
                    public void handle(HttpExchange exchange) throws IOException {
                        String query = exchange.getRequestURI().getQuery();
                        String result = "";
                        String answer = "";
                        if (query == null) {
                            result = "Not found authorization code. Try again.";
                            answer = "Empty response";
                        } else if (query.contains("code")) {
                            result = "Got the code. Return back to your program.";
                            answer = "Code received";
                            applicationSettings.code = query.replaceAll("code=", "");
                        } else {
                            result = "Not found authorization code. Try again.";
                            answer = "Code isn't received";
                        }
                        exchange.sendResponseHeaders(200, result.length());
                        //output result string to the browser
                        exchange.getResponseBody().write(result.getBytes());
                        exchange.getResponseBody().close();
                        System.out.println(answer);


                        if (query != null & !applicationSettings.code.equals("")) {
                            getAccessToken(applicationSettings);
                            applicationSettings.isAuthorized = true;
                        }

                    }
                }

        );
        while (applicationSettings.code.equals("")) {
            Thread.sleep(1);
        }

        server.stop(10);


    }

    public void getAccessToken(ApplicationSettings applicationSettings) {

        System.out.println("making http request for access_token...");

        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .uri(URI.create("https://accounts.spotify.com/api/token?"))
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
                System.out.println(response.body());
            }
        } catch (InterruptedException | IOException e) {
            System.out.println("Error");
        }


    }

}









