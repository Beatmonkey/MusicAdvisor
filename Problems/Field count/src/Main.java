import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 Get number of accessible public fields for a given class.
 */
class FieldGetter {

    ElementType elementType;
    Retention retention;
    RetentionPolicy retentionPolicy;
    Target target;
    Method method;

    public int getNumberOfAccessibleFields(Class<?> clazz) {

      Field[] fields =  clazz.getFields();

      int counter = 0;
      for (Field field : fields) {
          ++counter;
      }


        return counter;
    }

}