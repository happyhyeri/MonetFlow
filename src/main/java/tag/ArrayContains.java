package tag;

public class ArrayContains {

	public static boolean contains(String[] array, String value) {
		
		if (array != null) {
			boolean found = false;
			for (String item : array) {
				if (item.equals(value)) {
					found = true;
					break;
				}
			}
			return found;
		}
		return false;
	}
}


