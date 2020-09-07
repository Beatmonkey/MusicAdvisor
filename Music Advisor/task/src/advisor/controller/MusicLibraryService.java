package advisor.controller;


import advisor.common.AuthCallback;
import advisor.model.*;
import advisor.model.Error;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicLibraryService {
    RestService restService;
    ApplicationSettings applicationSettings;

    public MusicLibraryService(RestService restService, ApplicationSettings applicationSettings) {
        this.restService = restService;
        this.applicationSettings = applicationSettings;
    }

    public Result<PagingWith<List<NewRelease>>> getNewReleases() {
        String uri = applicationSettings.apiServerPath + "/v1/browse/new-releases?";
        return getNewReleasesByURI(uri);
    }

    public Result<PagingWith<List<NewRelease>>> getNewReleasesByURI(String uri) {
        JsonObject jo = restService.getJsonObject(uri).getAsJsonObject("albums");

//        JsonObject jo = NewReleasesTest.joTest.getAsJsonObject("albums");

        JsonArray items = jo.getAsJsonArray("items");
        List<NewRelease> newReleases = new ArrayList<>();

        int total = 0;

        for (JsonElement release : items) {
            NewRelease newRelease = new NewRelease();
            newRelease.name = release.getAsJsonObject().get("name").getAsString();

            List<String> artistsNames = new ArrayList<>();
            JsonArray names = release.getAsJsonObject().get("artists").getAsJsonArray();
            for (JsonElement name : names) {
                artistsNames.add(name.getAsJsonObject().get("name").getAsString());

            }
            newRelease.artist = artistsNames.toString();
            newRelease.href = release.getAsJsonObject().get("external_urls").getAsJsonObject().get("spotify").getAsString();

            newReleases.add(newRelease);
            total++;
        }

        Paging pagingData = new Paging(total);


        return Result.successful(new PagingWith<>(newReleases, pagingData));
    }


    public Result<PagingWith<List<Featured>>> getFeatured() {
        String uri = applicationSettings.apiServerPath + "/v1/browse/featured-playlists?";
        return getFeaturedByURI(uri);

    }

    public Result<PagingWith<List<Featured>>> getFeaturedByURI(String uri) {
        JsonObject jo = restService.getJsonObject(uri).getAsJsonObject("playlists");

//        JsonObject jo = NewReleasesTest.joTestNewfeat.getAsJsonObject("playlists");


        JsonArray items = jo.getAsJsonArray("items");
        List<Featured> featuredList = new ArrayList<>();


        int total = 0;
        for (JsonElement featured : items) {
            Featured fe = new Featured();
            fe.name = featured.getAsJsonObject().get("name").getAsString();
            fe.href = featured.getAsJsonObject().get("external_urls").getAsJsonObject().get("spotify").getAsString();
            featuredList.add(fe);
            total++;
        }
        Paging pagingData = new Paging(total);

        return Result.successful(new PagingWith<>(featuredList, pagingData));
    }

    public Result<PagingWith<List<Category>>> getCategories() {
        String uri = applicationSettings.apiServerPath + "/v1/browse/categories?";
        return getCategoriesByURI(uri);

    }

    public Result<PagingWith<List<Category>>> getCategoriesByURI(String uri) {
        JsonObject jo = restService.getJsonObject(uri).getAsJsonObject("categories");

        JsonArray items = jo.getAsJsonArray("items");
        List<Category> categories = new ArrayList<>();


        int total = 0;
        for (JsonElement category : items) {
            Category cat = new Category();
            cat.name = category.getAsJsonObject().get("name").getAsString();
            categories.add(cat);
            total++;
        }
        Paging pagingData = new Paging(total);

        return Result.successful(new PagingWith<>(categories, pagingData));
    }


    private String getCategoryId(String categoryName) {
        JsonArray items = restService.getJsonObject(applicationSettings.apiServerPath + "/v1/browse/categories").getAsJsonObject("categories").getAsJsonArray("items");

        for (JsonElement item : items) {
            var name = item.getAsJsonObject().get("name").getAsString();
            if (categoryName.equals(name)) {
                return item.getAsJsonObject().get("id").getAsString();
            }
        }
        return null;
    }


    public Result<PagingWith<List<? extends SpotifyObject>>> getPlaylistsByName(String playlistName) {

        String categoryId = getCategoryId(playlistName);
        if (categoryId == null) {
            return null;
        } else {
            String uri = applicationSettings.apiServerPath + "/v1/browse/categories/" + categoryId + "/playlists?";
            return getPlaylistsByURI(uri);
        }
    }

    public Result<PagingWith<List<? extends SpotifyObject>>> getPlaylistsByURI(String uri) {

        JsonObject jo = restService.getJsonObject(uri);


        if (jo.has("playlists")) {
            jo = jo.getAsJsonObject("playlists");
            List<Playlist> playlists = new ArrayList<>();
            var items = jo.getAsJsonArray("items");


            int total = 0;
            for (JsonElement playlist : items) {
                Playlist p = new Playlist();

                JsonObject playlistJson = playlist.getAsJsonObject();
//                p.id = playlistJson.get("id").getAsString();
//                p.description = playlistJson.get("description").getAsString();
                p.name = playlistJson.get("name").getAsString();
                p.href = playlistJson.get("external_urls").getAsJsonObject().get("spotify").getAsString();
                playlists.add(p);
                total++;
            }

            Paging pagingData = new Paging(total);

            return Result.successful(new PagingWith<>(playlists, pagingData));
        } else {
            Error error = new Error();
            JsonObject errorJson = jo.getAsJsonObject("error");
            error.status = errorJson.get("status").getAsInt();
            error.message = errorJson.get("status").getAsString();
            return Result.failed(error);
        }

    }


    private Paging getPagingData(JsonObject jo) {
        String next;
        if (jo.get("next").isJsonNull()) {
            next = null;
        } else {
            next = jo.get("next").getAsString();
        }

        String previous;
        if (jo.get("previous").isJsonNull()) {
            previous = null;
        } else {
            previous = jo.get("previous").getAsString();
        }
        int total = jo.get("total").getAsInt();
        int limit = jo.get("limit").getAsInt();

        return new Paging(next, previous, total, limit);
    }


    public void auth(AuthCallback authCallback) {
        try {
            SimpleHttpServer server = new SimpleHttpServer(applicationSettings);
            server.requestCode(status -> {
                if (status == 1) {
                    System.out.println("Code received");
                    String accessToken = restService.getAccessToken();
                    if (accessToken != null) {
                        applicationSettings.accessToken = accessToken;
                        applicationSettings.isAuthorized = true;
                        authCallback.call(status);
                    } else {
                        applicationSettings.isAuthorized = false;
//                            authCallback.accept(false);
                    }
                } else if (status == 0) {
                    System.out.println("Empty response");
                } else {
                    System.out.println("Code isn't received");
                }
            });

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}