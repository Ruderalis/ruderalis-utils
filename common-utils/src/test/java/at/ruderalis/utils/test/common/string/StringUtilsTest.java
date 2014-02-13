/**
 * 
 */
package at.ruderalis.utils.test.common.string;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import at.ruderalis.utils.common.string.StringUtils;

/**
 * @author Thomas Herzog
 * @date Feb 13, 2014
 */
@RunWith(JUnit4.class)
public class StringUtilsTest {

	@Test
	public void testIsEmpty_null() {
		assertTrue(StringUtils.isEmpty((String) null));
	}

	@Test
	public void testIsEmpty_empty() {
		assertTrue(StringUtils.isEmpty(""));
	}

	@Test
	public void testIsEmpty_spaces() {
		assertTrue(StringUtils.isEmpty("   "));
	}

	@Test
	public void testIsEmpty() {
		assertFalse(StringUtils.isEmpty("I am not empty"));
	}

	@Test
	public void testSplitCamel_null() {
		assertArrayEquals(new String[0], StringUtils.splitCamel((String) null));
	}

	@Test
	public void testSplitCamel_empty() {
		assertArrayEquals(new String[0], StringUtils.splitCamel(""));
	}

	@Test
	public void testSplitCamel_spaces() {
		assertArrayEquals(new String[0], StringUtils.splitCamel("  "));
	}

	@Test
	public void testSplitCamel_single_characters() {
		assertArrayEquals(new String[] { "a", "b", "c", "d" },
				StringUtils.splitCamel("ABCD"));
	}

	@Test
	public void testSplitCamel() {
		assertArrayEquals(new String[] { "i", "am", "a", "camel", "case",
				"string" }, StringUtils.splitCamel("IAmACamelCaseString"));
	}

	@Test
	public void testToCamel_null() {
		assertEquals("", StringUtils.toCamel((String) null));
	}

	@Test
	public void testToCamel_empty() {
		assertEquals("", StringUtils.toCamel(new String[0]));
	}

	@Test
	public void testToCamel_spaces() {
		assertEquals("", StringUtils.toCamel(new String[] { "", "", "", "" }));
	}

	@Test
	public void testToCamel_spaces_null() {
		assertEquals("", StringUtils.toCamel(new String[] { "", null, "", "" }));
	}

	@Test
	public void testToCamel() {
		assertEquals(
				"IWannaBeACamel",
				StringUtils.toCamel(new String[] { "i", null, "wanna", "be",
						"", null, "a", "camel" }));
	}
}
