package advisor.view;


import advisor.common.AuthCallback;
import advisor.controller.MusicLibraryService;
import advisor.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserInteraction {

    private final Map<String, Boolean> STATE_TRANSITION_MAP;


    ApplicationSettings applicationSettings;
    MusicLibraryService musicLibraryService;
    String previousCommand = "0";
    String currentCommand = "0";
    Scanner scanner = new Scanner(System.in);
    int currentPage = 1;
    int maxPage;

    Paging lastPaging;

    public UserInteraction(ApplicationSettings applicationSettings, MusicLibraryService musicLibraryService) {
        this.applicationSettings = applicationSettings;
        this.musicLibraryService = musicLibraryService;
        STATE_TRANSITION_MAP = new HashMap<>();
        STATE_TRANSITION_MAP.put("0-auth", true);
        STATE_TRANSITION_MAP.put("0-categories", true);
        STATE_TRANSITION_MAP.put("0-playlists", true);
        STATE_TRANSITION_MAP.put("0-featured", true);
        STATE_TRANSITION_MAP.put("0-new", true);
        STATE_TRANSITION_MAP.put("0-next", false);
        STATE_TRANSITION_MAP.put("0-previous", false);

        STATE_TRANSITION_MAP.put("auth-auth", true);
        STATE_TRANSITION_MAP.put("auth-categories", true);
        STATE_TRANSITION_MAP.put("auth-playlists", true);
        STATE_TRANSITION_MAP.put("auth-featured", true);
        STATE_TRANSITION_MAP.put("auth-new", true);
        STATE_TRANSITION_MAP.put("auth-next", false);
        STATE_TRANSITION_MAP.put("auth-previous", false);

        STATE_TRANSITION_MAP.put("categories-auth", true);
        STATE_TRANSITION_MAP.put("categories-categories", true);
        STATE_TRANSITION_MAP.put("categories-playlists", true);
        STATE_TRANSITION_MAP.put("categories-featured", true);
        STATE_TRANSITION_MAP.put("categories-new", true);
        STATE_TRANSITION_MAP.put("categories-next", true);
        STATE_TRANSITION_MAP.put("categories-previous", true);

        STATE_TRANSITION_MAP.put("featured-auth", true);
        STATE_TRANSITION_MAP.put("featured-categories", true);
        STATE_TRANSITION_MAP.put("featured-playlists", true);
        STATE_TRANSITION_MAP.put("featured-featured", true);
        STATE_TRANSITION_MAP.put("featured-new", true);
        STATE_TRANSITION_MAP.put("featured-next", true);
        STATE_TRANSITION_MAP.put("featured-previous", true);

        STATE_TRANSITION_MAP.put("new-auth", true);
        STATE_TRANSITION_MAP.put("new-categories", true);
        STATE_TRANSITION_MAP.put("new-playlists", true);
        STATE_TRANSITION_MAP.put("new-featured", true);
        STATE_TRANSITION_MAP.put("new-new", true);
        STATE_TRANSITION_MAP.put("new-next", true);
        STATE_TRANSITION_MAP.put("new-previous", true);

        STATE_TRANSITION_MAP.put("playlists-auth", true);
        STATE_TRANSITION_MAP.put("playlists-categories", true);
        STATE_TRANSITION_MAP.put("playlists-playlists", true);
        STATE_TRANSITION_MAP.put("playlists-featured", true);
        STATE_TRANSITION_MAP.put("playlists-new", true);
        STATE_TRANSITION_MAP.put("playlists-next", true);
        STATE_TRANSITION_MAP.put("playlists-previous", true);

        STATE_TRANSITION_MAP.put("next-auth", true);
        STATE_TRANSITION_MAP.put("next-categories", true);
        STATE_TRANSITION_MAP.put("next-playlists", true);
        STATE_TRANSITION_MAP.put("next-featured", true);
        STATE_TRANSITION_MAP.put("next-new", true);
        STATE_TRANSITION_MAP.put("next-next", true);
        STATE_TRANSITION_MAP.put("next-previous", true);

        STATE_TRANSITION_MAP.put("previous-auth", true);
        STATE_TRANSITION_MAP.put("previous-categories", true);
        STATE_TRANSITION_MAP.put("previous-playlists", true);
        STATE_TRANSITION_MAP.put("previous-featured", true);
        STATE_TRANSITION_MAP.put("previous-new", true);
        STATE_TRANSITION_MAP.put("previous-next", true);
        STATE_TRANSITION_MAP.put("previous-previous", true);

    }

    public void menu() {

        boolean isActionZero = false;


        while (!isActionZero) {
            String input[] = scanner.nextLine().split(" ");

            currentCommand = input[0];

            Boolean isTransitionValid = STATE_TRANSITION_MAP.get(previousCommand + "-" + currentCommand);


            if (Boolean.TRUE.equals(isTransitionValid)) {
                switch (currentCommand) {
                    case "new": {
                        if (!applicationSettings.isAuthorized) {
                            System.out.println("Please, provide access for application.");
                            break;
                        } else {
                            currentPage = 1;
                            Result<PagingWith<List<NewRelease>>> newReleases = musicLibraryService.getNewReleases();
                            showNewReleases(newReleases);
                            previousCommand = currentCommand;
                            break;
                        }
                    }
                    case "featured": {
                        if (!applicationSettings.isAuthorized) {
                            System.out.println("Please, provide access for application.");
                            break;
                        } else {
                            currentPage = 1;
                            Result<PagingWith<List<Featured>>> featured = musicLibraryService.getFeatured();
                            showFeatured(featured);
                            previousCommand = currentCommand;
                            break;
                        }
                    }
                    case "categories": {
                        currentPage = 1;
                        if (!applicationSettings.isAuthorized) {
                            System.out.println("Please, provide access for application.");
                            break;
                        } else {
                            currentPage = 1;
                            Result<PagingWith<List<Category>>> categories = musicLibraryService.getCategories();
                            showCategories(categories);
                            previousCommand = currentCommand;
                            break;
                        }
                    }
                    case "playlists": {
                        if (!applicationSettings.isAuthorized) {
                            System.out.println("Please, provide access for application.");
                            break;
                        } else {
                            currentPage = 1;
                            String playlistName = tryGetPlaylistName(input);
                            Result<PagingWith<List<Playlist>>> playlistsByNameResult = musicLibraryService.getPlaylistsByName(playlistName);
                            showPlaylists(playlistsByNameResult);
                            previousCommand = currentCommand;
                            break;
                        }
                    }
                    case "next": {
                        //Get paging data from previous request
                        //Use data to get playlists by URI
                        if (previousCommand.equalsIgnoreCase("playlists")) {
                            String next = lastPaging.next;
                            if (next == null) {
                                System.out.println("No more pages.");
                            } else {
                                Result<PagingWith<List<Playlist>>> playlistsResultNext = musicLibraryService.getPlaylistsByURI(lastPaging.next);
                                currentPage++;
                                //Print list of playlists
                                showPlaylists(playlistsResultNext);
                                //save new paging data to previous
                                lastPaging = playlistsResultNext.data.paging;
                            }

                        } else if (previousCommand.equalsIgnoreCase("categories")) {
                            String next = lastPaging.next;
                            if (next == null) {
                                System.out.println("No more pages.");
                            } else {
                                Result<PagingWith<List<Category>>> categoryResultNext = musicLibraryService.getCategoriesByURI(lastPaging.next);
                                currentPage++;
                                showCategories(categoryResultNext);
                                //save new paging data to previous
                                lastPaging = categoryResultNext.data.paging;
                            }
                        } else if (previousCommand.equalsIgnoreCase("featured")) {
                            String next = lastPaging.next;
                            if (next == null) {
                                System.out.println("No more pages.");
                            } else {
                                Result<PagingWith<List<Featured>>> featuredResultNext = musicLibraryService.getFeaturedByURI(lastPaging.next);
                                currentPage++;
                                showFeatured(featuredResultNext);
                                //save new paging data to previous
                                lastPaging = featuredResultNext.data.paging;
                            }
                        } else if (previousCommand.equalsIgnoreCase("new")) {
                            String next = lastPaging.next;
                            if (next == null) {
                                System.out.println("No more pages.");
                            } else {
                                Result<PagingWith<List<NewRelease>>> newReleasesResultNext = musicLibraryService.getNewReleasesByURI(lastPaging.next);
                                currentPage++;
                                showNewReleases(newReleasesResultNext);
                                //save new paging data to previous
                                lastPaging = newReleasesResultNext.data.paging;
                            }
                        }
                        break;
                    }
                    case "previous": {
                        if (previousCommand.equalsIgnoreCase("playlists")) {
                            String previous = lastPaging.previous;
                            if (previous == null) {
                                System.out.println("No more pages.");
                            } else {
                                Result<PagingWith<List<Playlist>>> playlistsResultPrev = musicLibraryService.getPlaylistsByURI(lastPaging.previous);
                                currentPage--;
                                //Print list of playlists
                                showPlaylists(playlistsResultPrev);
                                //save new paging data to previous
                                lastPaging = playlistsResultPrev.data.paging;
                            }

                        } else if (previousCommand.equalsIgnoreCase("categories")) {
                            String previous = lastPaging.previous;
                            if (previous == null) {
                                System.out.println("No more pages.");
                            } else {
                                Result<PagingWith<List<Category>>> categoryResultPrev = musicLibraryService.getCategoriesByURI(lastPaging.previous);
                                currentPage--;
                                showCategories(categoryResultPrev);
                                //save new paging data to previous
                                lastPaging = categoryResultPrev.data.paging;
                            }
                        } else if (previousCommand.equalsIgnoreCase("featured")) {
                            String previous = lastPaging.previous;
                            if (previous == null) {
                                System.out.println("No more pages.");
                            } else {
                                Result<PagingWith<List<Featured>>> featuredResultPrev = musicLibraryService.getFeaturedByURI(lastPaging.previous);
                                currentPage--;
                                showFeatured(featuredResultPrev);
                                //save new paging data to previous
                                lastPaging = featuredResultPrev.data.paging;
                            }
                        } else if (previousCommand.equalsIgnoreCase("new")) {
                            String previous = lastPaging.previous;
                            if (previous == null) {
                                System.out.println("No more pages.");
                            } else {
                                Result<PagingWith<List<NewRelease>>> newReleasesResultPrev = musicLibraryService.getNewReleasesByURI(lastPaging.previous);
                                currentPage--;
                                showNewReleases(newReleasesResultPrev);
                                //save new paging data to previous
                                lastPaging = newReleasesResultPrev.data.paging;
                            }
                        }
                        break;
                    }
                    case "auth": {
                        System.out.println("use this link to request the access code:");
                        System.out.println(applicationSettings.createAuthLink());
                        System.out.println("waiting for code...");
                        musicLibraryService.auth(new AuthCallback() {
                            @Override
                            public void call(int status) {
                                if (status == 1) {
                                    System.out.println("---SUCCESS---");
                                } else {
                                    System.out.println("---Failed---");
                                }
                            }
                        });
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
            } else {
                System.out.println("Incorrect Transition!");
            }
/*            if (previousCommand != currentCommand) {
                previousCommand = currentCommand;
            }*/


        }

    }

    void showNewReleases(Result<PagingWith<List<NewRelease>>> result) {
        if (result.isSuccess()) {
            List<NewRelease> newReleases = result.data.list;
            lastPaging = result.data.paging;
            for (NewRelease newRelease: newReleases) {
                System.out.println(newRelease.name);
                System.out.println(newRelease.artist);
                System.out.println(newRelease.href + "\n");
            }
            maxPage = Math.round(lastPaging.total / lastPaging.limit);
            System.out.printf("---PAGE %d OF %d---\n", currentPage, maxPage);

        }
    }

    void showFeatured(Result<PagingWith<List<Featured>>> result) {
        if (result.isSuccess()) {
            List<Featured> featuredList = result.data.list;
            lastPaging = result.data.paging;
            for (Featured featured: featuredList) {
                System.out.println(featured.name);
                System.out.println(featured.href + "\n");
            }
            maxPage = Math.round(lastPaging.total / lastPaging.limit);
            System.out.printf("---PAGE %d OF %d---\n", currentPage, maxPage);
        }
    }

    void showCategories(Result<PagingWith<List<Category>>> result) {

        if (result.isSuccess()) {
            List<Category> categories = result.data.list;
            lastPaging = result.data.paging;
            for (Category category : categories) {
                System.out.println(category.name);
            }
            maxPage = Math.round(lastPaging.total / lastPaging.limit);
            System.out.printf("---PAGE %d OF %d---\n", currentPage, maxPage);
        } else {
            System.out.println(result.error.message);
        }

    }

    void showPlaylists(Result<PagingWith<List<Playlist>>> result) {
        if (result.isSuccess()) {
            List<Playlist> playlists = result.data.list;
            lastPaging = result.data.paging;
            for (Playlist playlist : playlists) {
                System.out.println(playlist.name);
                System.out.println(playlist.href);
            }
            maxPage = Math.round(lastPaging.total / lastPaging.limit);
            System.out.printf("---PAGE %d OF %d---\n", currentPage, maxPage);
        } else {
            System.out.println(result.error.message);
        }
    }


    private String tryGetPlaylistName(String[] input) {
        String playlistName = "";
        if (input.length == 2) {
            playlistName = input[1];
        } else {
            for (int i = 1; i < input.length; i++) {
                if (i == input.length - 1) {
                    playlistName += input[i];
                } else {
                    playlistName += input[i] + " ";
                }
            }
        }
        return playlistName;
    }


}
