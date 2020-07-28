import java.util.ArrayList;
import java.util.List;

/**
 * Class to modify
 */
class ListMultiplicator {

    /**
     * Multiplies original list provided number of times
     *
     * @param list list to multiply
     * @param n    times to multiply, should be zero or greater
     */
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        ListMultiplicator.multiply(list, 3);
        System.out.println(list);

        System.out.println("Well done!");
    }

    public static void multiply(List<?> list, int n) {
        multiplyHelper(list, n);
//        System.out.println(list);
    }

    public static <T> void multiplyHelper(List<T> list, int n) {
        if (n > 0 && !list.isEmpty()) {
            List<T> inputList = new ArrayList<>(list);
            for (int i = 1; i < n; i++) {
                list.addAll(inputList);
            }
        } else {
            list.clear();
        }
    }
}

/*class Main {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        ListMultiplicator.multiply(list,2);
//        System.out.println(list);

    }
}*/
