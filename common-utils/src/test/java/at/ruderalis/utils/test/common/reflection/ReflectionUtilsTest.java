/**
 * 
 */
package at.ruderalis.utils.test.common.reflection;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import at.ruderalis.utils.common.reflection.ReflectionUtils;

/**
 * @author Thomas Herzog
 * @date Feb 12, 2014
 */
@RunWith(JUnit4.class)
public class ReflectionUtilsTest {

	public abstract class AbstractSearchClass {

		private void privateAbstractMethod() {
		}

		public void publicAbstractMethod() {
		}

		public void overwriteAbstractMethod() {
		}

		public abstract void abstractMethod();
	}

	public class BaseSearchClass extends AbstractSearchClass {

		private void privateBaseMethod() {
		}

		public void publicBaseMethod() {
		}

		@Override
		public void abstractMethod() {
		}
	}

	public class SearchInheritClass extends BaseSearchClass {
		@Override
		public void abstractMethod() {
		}

		private void privateMethod() {
		}

		public void publicMethod() {
		}

		@Override
		public void overwriteAbstractMethod() {
		}
	}

	public class SearchSingleClass {

		private void privateMethod() {
		}

		public void publicMethod() {
		}
	}

	public interface SearchInterface1 {
		public void interfaceMethod1();
	}

	public interface SearchInterface2 extends SearchInterface1 {
		public void interfaceMethod2();
	}

	public interface SearchInterface3 extends SearchInterface1,
			SearchInterface2 {
		public void interfaceMethod3();
	}

	@Test
	public void testHasMethod_not_found() {
		assertFalse(ReflectionUtils.hasMethod(SearchSingleClass.class,
				"notFoundMethod"));
	}

	@Test
	public void testHasMethod_null_class() {
		assertFalse(ReflectionUtils.hasMethod(null, "publicMethod"));
	}

	@Test
	public void testHasMethod_null_name() {
		assertFalse(ReflectionUtils.hasMethod(SearchSingleClass.class, null));
	}

	@Test
	public void testHasMethod_not_found_inherit_class() {
		assertFalse(ReflectionUtils.hasMethod(SearchInheritClass.class,
				"privateBaseMethod"));
	}

	@Test
	public void testHasMethod_single_class() {
		assertTrue(ReflectionUtils.hasMethod(SearchSingleClass.class,
				"privateMethod"));
	}

	@Test
	public void testGetClassImplementsMethod_not_found() {
		assertNull(ReflectionUtils.getClassImplementsMethod(
				SearchInheritClass.class, "doesNotExists"));
	}

	@Test
	public void testGetClassImplementsMethod_null_name() {
		assertNull(ReflectionUtils.getClassImplementsMethod(
				SearchInheritClass.class, null));
	}

	@Test
	public void testGetClassImplementsMethod_null_class() {
		assertNull(ReflectionUtils.getClassImplementsMethod(null,
				"publicMethod"));
	}

	@Test
	public void testGetClassImplementsMethod_interface() {
		assertNull(ReflectionUtils.getClassImplementsMethod(
				SearchInterface1.class, "publicMethod"));
	}

	@Test
	public void testGetClassImplementsMethod_on_base_public() {
		assertEquals(BaseSearchClass.class,
				ReflectionUtils.getClassImplementsMethod(
						SearchInheritClass.class, "publicBaseMethod"));
	}

	@Test
	public void testGetClassImplementsMethod_on_base_private() {
		assertEquals(BaseSearchClass.class,
				ReflectionUtils.getClassImplementsMethod(
						SearchInheritClass.class, "privateBaseMethod"));
	}

	@Test
	public void testGetClassImplementsMethod_on_inherit_class_public() {
		assertEquals(SearchInheritClass.class,
				ReflectionUtils.getClassImplementsMethod(
						SearchInheritClass.class, "publicMethod"));
	}

	@Test
	public void testGetClassImplementsMethod_on_inherit_class_private() {
		assertEquals(SearchInheritClass.class,
				ReflectionUtils.getClassImplementsMethod(
						SearchInheritClass.class, "privateMethod"));
	}

	@Test
	public void testGetClassImplementsMethod_on_inherit_class_abstract() {
		assertEquals(SearchInheritClass.class,
				ReflectionUtils.getClassImplementsMethod(
						SearchInheritClass.class, "abstractMethod"));
	}

	@Test
	public void testGetClassImplementsMethod_on_single_class_public() {
		assertEquals(SearchSingleClass.class,
				ReflectionUtils.getClassImplementsMethod(
						SearchSingleClass.class, "publicMethod"));
	}

	@Test
	public void testGetClassImplementsMethod_on_single_class_private() {
		assertEquals(SearchSingleClass.class,
				ReflectionUtils.getClassImplementsMethod(
						SearchSingleClass.class, "privateMethod"));
	}

	@Test
	public void testGetInterfaceDeclaresMethod_not_found() {
		assertNull(ReflectionUtils.getInterfaceDeclaresMethod(
				SearchInterface1.class, "doesNotExists"));
	}

	@Test
	public void testGetInterfaceDeclaresMethod_null_name() {
		assertNull(ReflectionUtils.getInterfaceDeclaresMethod(
				SearchInterface1.class, null));
	}

	@Test
	public void testGetInterfaceDeclaresMethod_null_interface() {
		assertNull(ReflectionUtils.getInterfaceDeclaresMethod(null,
				"interfaceMethod"));
	}

	@Test
	public void testGetInterfaceDeclaresMethod_class() {
		assertNull(ReflectionUtils.getInterfaceDeclaresMethod(
				SearchInheritClass.class, "publicMethod"));
	}

	@Test
	public void testGetInterfaceDeclaresMethod_single_interface() {
		assertNull(ReflectionUtils.getInterfaceDeclaresMethod(
				SearchInterface1.class, "interfaceMethod1"));
	}

	@Test
	public void testGetInterfaceDeclaresMethod_interface() {
		assertEquals(SearchInterface1.class,
				ReflectionUtils.getInterfaceDeclaresMethod(
						SearchInterface3.class, "interfaceMethod1"));
	}
}
