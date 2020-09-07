package advisor.model;

public class Paging {

    public String next;
    public String previous;
    public int total;
    public int limit;



    public Paging(String next, String previous, int total, int limit) {
        this.next = next;
        this.previous = previous;
        this.total = total;
        this.limit = limit;

    }

    public Paging(int total) {
        this.total = total;
    }
}
