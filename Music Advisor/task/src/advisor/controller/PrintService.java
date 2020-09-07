package advisor.controller;

import advisor.model.*;

import java.util.List;

public class PrintService<T> {
    ApplicationSettings applicationSettings;
    Paging paging;
    int maxPage;
    int currentPage = 1;
    public int from = 0;
    public int to;

    Result<PagingWith<List<? extends SpotifyObject>>> resultData;


    public PrintService(ApplicationSettings applicationSettings) {
//        this.paging = paging;
        this.applicationSettings = applicationSettings;
    }

    private void printData(int from, int to, Result<PagingWith<List<? extends SpotifyObject>>> result) throws IllegalAccessException {
        if (result.isSuccess()) {
            List<? extends SpotifyObject> list = result.data.list;
            for (int i = from; i < to; i++) {
                SpotifyObject.getAsList(list.get(i))
                        .stream()
                        .forEach(System.out::println);
            }
            maxPage = Math.round(paging.total / applicationSettings.limit);
            System.out.printf("---PAGE %d OF %d---\n", currentPage, maxPage);
        } else {
            System.out.println(result.error.message);
        }
    }
    

    public void printFirst(Result<PagingWith<List<? extends SpotifyObject>>> result) throws IllegalAccessException {
        this.paging = result.data.getPaging();
        this.resultData = result;
        reset().printData(from, to, resultData);
    }

    public void printNext() throws IllegalAccessException {
        if (to >= paging.total) {
            System.out.println("No more pages.");
        } else {
            currentPage++;
            from += applicationSettings.limit;
            to += applicationSettings.limit;
            if (to > resultData.data.list.size() - 1) {
                printData(from, resultData.data.list.size(), resultData);
            } else {
                printData(from, to, resultData);
            }
        }
    }

    public void printPrevious() throws IllegalAccessException {
        if (from <= 0) {
            System.out.println("No more pages.");
        } else {
            currentPage--;
            from -= applicationSettings.limit;
            to -= applicationSettings.limit;
            if (from < 0) {
                printData(0, to, resultData);
            } else {
                printData(from, to, resultData);
            }
        }
    }

    public PrintService<T> reset() {
        this.currentPage = 1;
        this.from = 0;
        this.to = applicationSettings.limit;

        return this;
    }

}
