import java.util.ArrayList;
import java.util.List;

/**
 * Class to work with
 */


class Bakery {}

class Cake extends Bakery {}

class Paper {}

class Box<T> {
    T item;
    void put(T item) {
        this.item = item;
    }
    T get() {
        return this.item;
    }
}

class NaiveQualityControl {

    public static boolean check(List<Box<? extends Bakery>> boxes) {

        return true;
    }

}

class Violator {


    public static List<Box<? extends Bakery>> defraud() {
        List<Box<? extends Bakery>> boxes = new ArrayList<>();
        Box box = new Box();
        Paper paper = new Paper();
        box.put(paper);
        boxes.add(box);


        return boxes;
    }

}

class Main {
    public static void main(String[] args) {
        Cake cake = new Cake();
        Bakery bakery = new Bakery();
        Paper paper = new Paper();

        List list = new ArrayList<>();

        list.add(cake);
        list.add(bakery);
        list.add(paper);

        NaiveQualityControl naiveQualityControl = new NaiveQualityControl();
        naiveQualityControl.check(Violator.defraud());
    }
}
