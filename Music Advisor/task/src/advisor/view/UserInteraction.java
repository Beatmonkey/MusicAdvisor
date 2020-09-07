package advisor.view;


import advisor.common.AuthCallback;
import advisor.controller.MusicLibraryService;
import advisor.controller.PrintService;
import advisor.model.*;

import java.util.*;

public class UserInteraction {

    private final Map<String, Boolean> STATE_TRANSITION_MAP;


    ApplicationSettings applicationSettings;
    MusicLibraryService musicLibraryService;
    String previousCommand = "0";
    String currentCommand = "0";
    Scanner scanner = new Scanner(System.in);

    PrintService printer;


    public UserInteraction(ApplicationSettings applicationSettings, MusicLibraryService musicLibraryService) {
        this.applicationSettings = applicationSettings;
        this.musicLibraryService = musicLibraryService;

        this.printer = new PrintService(this.applicationSettings);
        STATE_TRANSITION_MAP = new HashMap<>();
        STATE_TRANSITION_MAP.put("0-auth", true);
        STATE_TRANSITION_MAP.put("0-categories", true);
        STATE_TRANSITION_MAP.put("0-playlists", true);
        STATE_TRANSITION_MAP.put("0-featured", true);
        STATE_TRANSITION_MAP.put("0-new", true);
        STATE_TRANSITION_MAP.put("0-next", false);
        STATE_TRANSITION_MAP.put("0-prev", false);

        STATE_TRANSITION_MAP.put("auth-auth", true);
        STATE_TRANSITION_MAP.put("auth-categories", true);
        STATE_TRANSITION_MAP.put("auth-playlists", true);
        STATE_TRANSITION_MAP.put("auth-featured", true);
        STATE_TRANSITION_MAP.put("auth-new", true);
        STATE_TRANSITION_MAP.put("auth-next", false);
        STATE_TRANSITION_MAP.put("auth-prev", false);

        STATE_TRANSITION_MAP.put("categories-auth", true);
        STATE_TRANSITION_MAP.put("categories-categories", true);
        STATE_TRANSITION_MAP.put("categories-playlists", true);
        STATE_TRANSITION_MAP.put("categories-featured", true);
        STATE_TRANSITION_MAP.put("categories-new", true);
        STATE_TRANSITION_MAP.put("categories-next", true);
        STATE_TRANSITION_MAP.put("categories-prev", true);

        STATE_TRANSITION_MAP.put("featured-auth", true);
        STATE_TRANSITION_MAP.put("featured-categories", true);
        STATE_TRANSITION_MAP.put("featured-playlists", true);
        STATE_TRANSITION_MAP.put("featured-featured", true);
        STATE_TRANSITION_MAP.put("featured-new", true);
        STATE_TRANSITION_MAP.put("featured-next", true);
        STATE_TRANSITION_MAP.put("featured-prev", true);

        STATE_TRANSITION_MAP.put("new-auth", true);
        STATE_TRANSITION_MAP.put("new-categories", true);
        STATE_TRANSITION_MAP.put("new-playlists", true);
        STATE_TRANSITION_MAP.put("new-featured", true);
        STATE_TRANSITION_MAP.put("new-new", true);
        STATE_TRANSITION_MAP.put("new-next", true);
        STATE_TRANSITION_MAP.put("new-prev", true);

        STATE_TRANSITION_MAP.put("playlists-auth", true);
        STATE_TRANSITION_MAP.put("playlists-categories", true);
        STATE_TRANSITION_MAP.put("playlists-playlists", true);
        STATE_TRANSITION_MAP.put("playlists-featured", true);
        STATE_TRANSITION_MAP.put("playlists-new", true);
        STATE_TRANSITION_MAP.put("playlists-next", true);
        STATE_TRANSITION_MAP.put("playlists-prev", true);

        STATE_TRANSITION_MAP.put("next-auth", true);
        STATE_TRANSITION_MAP.put("next-categories", true);
        STATE_TRANSITION_MAP.put("next-playlists", true);
        STATE_TRANSITION_MAP.put("next-featured", true);
        STATE_TRANSITION_MAP.put("next-new", true);
        STATE_TRANSITION_MAP.put("next-next", true);
        STATE_TRANSITION_MAP.put("next-prev", true);

        STATE_TRANSITION_MAP.put("prev-auth", true);
        STATE_TRANSITION_MAP.put("prev-categories", true);
        STATE_TRANSITION_MAP.put("prev-playlists", true);
        STATE_TRANSITION_MAP.put("prev-featured", true);
        STATE_TRANSITION_MAP.put("prev-new", true);
        STATE_TRANSITION_MAP.put("prev-next", true);
        STATE_TRANSITION_MAP.put("prev-prev", true);

    }

    public void menu() throws IllegalAccessException {

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
                            printer.printFirst(musicLibraryService.getNewReleases());
                            previousCommand = currentCommand;
                            break;
                        }
                    }
                    case "featured": {
                        if (!applicationSettings.isAuthorized) {
                            System.out.println("Please, provide access for application.");
                            break;
                        } else {
                            printer.printFirst(musicLibraryService.getFeatured());
                            previousCommand = currentCommand;
                            break;
                        }
                    }
                    case "categories": {
                        if (!applicationSettings.isAuthorized) {
                            System.out.println("Please, provide access for application.");
                            break;
                        } else {
                            printer.printFirst(musicLibraryService.getCategories());
                            previousCommand = currentCommand;
                            break;
                        }
                    }
                    case "playlists": {
                        if (!applicationSettings.isAuthorized) {
                            System.out.println("Please, provide access for application.");
                            break;
                        } else {

                            String playlistName = tryGetPlaylistName(input);
                            if (musicLibraryService.getPlaylistsByName(playlistName) == null) {
                                System.out.println("Unknown category name.");
                            } else {
                                printer.printFirst(musicLibraryService.getPlaylistsByName(playlistName));
                                previousCommand = currentCommand;
                            }
                            break;
                        }
                    }
                    case "next": {
                        //Get paging data from previous request
                        //Use data to get playlists by URI
                        if (previousCommand.equalsIgnoreCase("playlists")) {
                            printer.printNext();
                        } else if (previousCommand.equalsIgnoreCase("categories")) {
                            printer.printNext();
                        } else if (previousCommand.equalsIgnoreCase("featured")) {
                            printer.printNext();
                        } else if (previousCommand.equalsIgnoreCase("new")) {
                            printer.printNext();
                        }
                        break;
                    }
                    case "prev": {
                        if (previousCommand.equalsIgnoreCase("playlists")) {
                            printer.printPrevious();
                        } else if (previousCommand.equalsIgnoreCase("categories")) {
                            printer.printPrevious();
                        } else if (previousCommand.equalsIgnoreCase("featured")) {
                            printer.printPrevious();
                        } else if (previousCommand.equalsIgnoreCase("new")) {
                            printer.printPrevious();
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
