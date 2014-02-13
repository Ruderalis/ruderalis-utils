/**
 * 
 */
package at.ruderalis.utils.common;

/**
 * This class provides common utility methods for the handling with Strings.
 * 
 * @author Thomas Herzog
 * @date Feb 12, 2014
 */
public class StringUtils {

	/**
	 * Answers the question if the given String instance is either null or
	 * contains no characters. Spaces will be ignored.
	 * 
	 * @param value
	 *            the String instance to check if it is empty
	 * @return true if the String instance is either null or contains no
	 *         characters, false otherwise
	 */
	public static boolean isEmpty(final String value) {
		return (value == null) || (value.trim().length() == 0);
	}
}
