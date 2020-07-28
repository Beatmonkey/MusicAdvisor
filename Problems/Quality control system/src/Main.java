import java.util.ArrayList;
import java.util.List;

class QualityControl {

    public static void main(String[] args) {
        Cake cherryCake = new Cake();
        Pie applePie = new Pie();
        Tart tart = new Tart();

        Paper paper = new Paper();

        Box box1 = new Box();
        Box box2 = new Box();
        Box box3 = new Box();
        Box box4 = new Box();

        box1.put(cherryCake);
        box2.put(applePie);
        box3.put(tart);
//        box4.put(paper);

        List<Box<? extends Bakery>> newList = new ArrayList<>();
        System.out.println(newList.size());
        newList.add(box1);
        newList.add(box2);
        newList.add(box3);
//        newList.add(box4);


        boolean isAll = check(newList);
        System.out.println(isAll);
        System.out.println(box4.get());


    }

    public static boolean check(List<Box<? extends Bakery>> boxes) {

        try {
            if (boxes == null) {
                return true;
            } else if (boxes != null) {
                int howMuchBakery = 0;
                for (Box box : boxes) {
                    if (box.get() instanceof Bakery && box.get() != null && box.getClass().equals(Box.class)) {
                        howMuchBakery++;
                    } else {
                        return false;
                    }
                }
                if (howMuchBakery == boxes.size()) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (ClassCastException | NullPointerException e) {
            e.printStackTrace();

        }
        return false;
    }

}

// Don't change the code below
class Bakery {
}

class Cake extends Bakery {
}

class Pie extends Bakery {
}

class Tart extends Bakery {
}

class Paper {
}

class Box<T> {

    private T item;

    public void put(T item) {
        this.item = item;
    }

    public T get() {
        return this.item;
    }
}