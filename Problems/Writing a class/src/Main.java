/*class Book {
}

class Brochure extends Book {

    private String name;

    Brochure(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }
}*/

class Shelf<T extends Book> {

    T book;

    void setElement(T book) {
        this.book = book;
    }

    T getElement() {
       return this.book;
    }







}

/*
public class Main {

    public static void main(String[] args) {
        Brochure brochure = new Brochure("NewBrochure");
        Shelf<Brochure> shelf = new Shelf<>();
        shelf.setElement(brochure);
        System.out.println(shelf.getElement().getName());
    }
}*/
