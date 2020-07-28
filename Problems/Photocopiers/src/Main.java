/**
 * Class to work with
 */
class Multiplicator {

    public static <T extends Copy<T>> Folder<T>[] multiply(Folder<T> folder, int arraySize) {
        Folder<T>[] folders = null;
        if (arraySize > 0) {
            folders = new Folder[arraySize];
            T item = folder.get();
//            for (Folder fl : folders) {
//                fl = new Folder();
//                fl.put(item.copy());
//            }

            for (int i = 0; i < arraySize; i++) {
                folders[i] = new Folder<>();
                folders[i].put(item.copy());
            }
            return folders;
        } else {
            folders = new Folder[arraySize];
        }

        return folders;
    }

/*    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        Paper paper = new Paper();
        Folder<Paper> folder = new Folder<>();
        String s = "Java";
        folder.put(paper);


        multiply(folder, 3);

    }*/


}

/*class Paper implements Copy {
    Paper paper;

    @Override
    public Object copy() {
        return this.paper;
    }
}*/

// Don't change the code below
interface Copy<T> {
    T copy();
}

class Folder<T> {

    private T item;

    public void put(T item) {
        this.item = item;
    }

    public T get() {
        return this.item;
    }
}