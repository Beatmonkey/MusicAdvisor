package advisor;


import advisor.controller.MusicLibraryService;
import advisor.controller.RestService;
import advisor.model.ApplicationSettings;
import advisor.view.UserInteraction;

import java.io.IOException;


public class Main {


    public static void main(String[] args) throws IOException, InterruptedException {

        ApplicationSettings applicationSettings = new ApplicationSettings();

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-access")) {
                applicationSettings.siteUrl = args[i + 1];
            }
            if (args[i].equals("-resource")) {
                applicationSettings.apiServerPath = args[i + 1];
            }
            if (args[i].equals("-page")) {
                if (args[i + 1].isEmpty()) {
                    applicationSettings.limit = "5";
                } else {
                    applicationSettings.limit = args[i + 1];
                }
            }
        }


        RestService restService = new RestService(applicationSettings);
        MusicLibraryService musicLibraryService = new MusicLibraryService(restService, applicationSettings);
        UserInteraction userInteraction = new UserInteraction(applicationSettings, musicLibraryService);
        userInteraction.menu();


    }
}
























