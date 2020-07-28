package advisor.model;

public class ApplicationSettings {

    public final String clientId = "fc2c20a7573e4717a3e51b174cd46d5b";
    public final String clientSecret = "274b0292252947989e885e66f7647274";
    public final String redirectUri = "http://localhost:8080";

    public String apiServerPath = "https://api.spotify.com";
    public String siteUrl = "https://accounts.spotify.com";
    public String code = "";

    public String limit = "1";



    public boolean isAuthorized = false;
    public String accessToken;


    public String createAuthLink() {
        StringBuilder authLink = new StringBuilder(siteUrl + "/authorize?client_id=" + clientId + "&redirect_uri=" + redirectUri + "&response_type=code");

        return authLink.toString();
    }


}