package advisor.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class SpotifyObject {


    public static List<String> getAsList(SpotifyObject spotifyObject) throws IllegalAccessException {



        List<String> values = new ArrayList<>();

        for (Field field : spotifyObject.getClass().getDeclaredFields()) {
            field.setAccessible(true); // You might want to set modifier to public first.
            Object value = field.get(spotifyObject);
            if (value != null) {
//                System.out.println(value);
                values.add(value.toString());
            }
        }
        return values;
    }
}
