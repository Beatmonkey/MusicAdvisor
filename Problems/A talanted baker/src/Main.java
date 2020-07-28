import java.lang.reflect.InvocationTargetException;

/**
 * Class to implement
 */
class ReflexiveBaker {

	/**
	 * Create bakery of the provided class.
	 * 
	 * @param order class of bakery to create
	 * @return bakery object
	 */
	public <T extends Bakery> T bake(Class<T> order) {
		try {
			return order.getDeclaredConstructor().newInstance();
		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException
				| InvocationTargetException e) {
			throw new AssertionError("Class could not be instantiated.", e);
		}
	}



}

// Don't change classes below
class Paper { }

class Bakery { }

class Cake extends Bakery { }

class Pie extends Bakery { }

class Tart extends Bakery { }

class ApplePie extends Pie { }