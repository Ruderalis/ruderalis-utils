/**
 * 
 */
package at.ruderalis.utils.common.string;

import java.util.ArrayList;
import java.util.List;

import at.ruderalis.utils.common.regex.CommonRegexPattern;

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

	/**
	 * Splits a camel case string to its parts where all parts will be lower
	 * case and all spaces will be removed case
	 * 
	 * @param camel
	 *            the string to be split
	 * @return the split string represented by an String[], an empty array if
	 *         the given camel case string is either null or empty
	 */
	public static String[] splitCamel(final String camel) {
		if (!isEmpty(camel)) {
			final List<String> split = new ArrayList<String>(camel.length());
			final String[] temp = camel.split(CommonRegexPattern.UPPER_CASE);
			for (int i = 0; i < temp.length; i++) {
				if (!isEmpty(temp[i])) {
					split.add(temp[i].trim().toLowerCase());
				}
			}
			return split.toArray(new String[split.size()]);
		} else {
			return new String[0];
		}
	}

	/**
	 * Builds a camel case string out from the given String parameters. All
	 * spaces and null elements will be removed. Before building all upper case
	 * letters will be converted to lower case.
	 * 
	 * @param string
	 *            the string parameters to merged to a camel case string
	 * @return the build camel case string
	 */
	public static String toCamel(final String... string) {
		StringBuilder builder = new StringBuilder(100);
		if ((string != null) && (string.length > 0)) {
			for (int i = 0; i < string.length; i++) {
				String part = string[i];
				if (!isEmpty(part)) {
					part = part.trim().toLowerCase();
					builder.append(part.substring(0, 1).toUpperCase());
					if (part.length() > 1) {
						builder.append(part.substring(1, part.length()));
					}
				}
			}
		}
		return builder.toString();
	}
}
