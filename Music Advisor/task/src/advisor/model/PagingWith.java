package advisor.model;

public class PagingWith<T> {
    public T list;
    public Paging paging;

    public PagingWith(T list, Paging paging) {
        this.list = list;
        this.paging = paging;
    }

    public Paging getPaging() {
        return this.paging;
    }
}
