/**
 * 
 */
package at.ruderalis.utils.common.regex;

/**
 * @author Thomas Herzog
 * @date Feb 13, 2014
 */
public final class CommonRegexPattern {

	/**
	 * Determines if the current character is upper case.
	 */
	public static final String UPPER_CASE = "(?=\\p{Lu})";

	private CommonRegexPattern() {
		super();
	}
}
