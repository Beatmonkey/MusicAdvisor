import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Class to work with
 */

/*class MyInt implements Comparable<Integer> {


    @Override
    public int compareTo(@NotNull Integer o) {
        return 0;
    }
}*/

class ComparatorInspector {

    /**
     * Return Type variable that corresponds to the type parameterizing Comparator.
     *
     * @param clazz {@link Class} object, should be non null
     * @return {@link Type} object or null if Comparable does not have type parameter
     */
    public static <T extends Comparable<?>> Type getComparatorType(Class<? extends Comparable<?>> clazz) {
        // for Comparable class
        if (clazz.equals(Comparable.class)) {
            return clazz.getTypeParameters()[0];
        }
        Type[] genericInterfaces = clazz.getGenericInterfaces();
        for (Type genericInterface : genericInterfaces) {
            // for Comparable<Integer>
            if (genericInterface instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) genericInterface;
                if (parameterizedType.getRawType().equals(Comparable.class)) {
                    Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                    if (actualTypeArguments != null && actualTypeArguments.length == 1) {
                        return actualTypeArguments[0];
                    }
                }
            }
        }

        // for Comparable
        return null;
    }



}

/*
class Main {
    public static void main(String[] args) {
        Type type = ComparatorInspector.getComparatorType(MyInt.class);

        System.out.println(type.getTypeName());
    }
}
*/
