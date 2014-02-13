/**
 * 
 */
package at.ruderalis.utils.common.reflection;

import java.lang.reflect.Method;

import at.ruderalis.utils.common.StringUtils;

/**
 * @author Thomas Herzog
 * @date Feb 12, 2014
 */
public class ReflectionUtils {

	public static <T> Method getGetter(final Class<T> target, final String name) {
		return null;
	}

	public static <T> Method getMethod(final T target, final String name,
			final Class<?>... parameterTypes) {

		return ReflectionUtils.getMethod(target != null ? target.getClass()
				: null, name);
	}

	public static Method getMethod(final Class<?> target, final String name,
			final Class<?>... parameterTypes) {
		if ((target == null) || (StringUtils.isEmpty(name))) {
			throw new IllegalArgumentException(
					"Target and name must not be null");
		}
		Method method = null;
		try {
			target.getDeclaredMethod(name, parameterTypes);
		} catch (NoSuchMethodException e) {
			// TODO: handle exception
		}

		return method;
	}

	/**
	 * Searches the traverse class for a method with the given name. All
	 * interfaces and super classes are searched for the method.
	 * 
	 * @param traverseClass
	 *            the class to be traversed
	 * @param name
	 *            the name of the method
	 * @return true if the method could be found on a interface or a super
	 *         class, false otherwise
	 */
	public static boolean hasMethodTraverse(final Class<?> traverseClass,
			final String name) {
		return (getInterfaceDeclaresMethod(traverseClass, name) != null)
				|| (getClassImplementsMethod(traverseClass, name) != null);
	}

	/**
	 * Answers the question if the given class either interface or class
	 * contains a method with the given name. All declared methods of the class
	 * will be searched. Super classes or interfaces of this class are ignored.
	 * 
	 * @param clazz
	 *            the class to search for method with the given name
	 * @param name
	 *            the name of the method to be found
	 * @return true if the method could be found.
	 */
	public static boolean hasMethod(final Class<?> clazz, final String name) {
		boolean found = Boolean.FALSE;
		if ((clazz != null) && (!StringUtils.isEmpty(name))) {
			int i = 0;
			final Method[] methods = clazz.getDeclaredMethods();
			while ((i < methods.length) && (!found)) {
				found = methods[i].getName().equals(name);
				i++;
			}
		}
		return found;
	}

	/**
	 * Traverses the class and returns the (super) class which implements the
	 * searched method.
	 * 
	 * @param traverseClass
	 *            the class to traversed
	 * @param name
	 *            the name of the method to be found
	 * @return the found class, null if traverseClass, name is null or if the
	 *         given traverseClass is no class
	 */
	public static Class<?> getClassImplementsMethod(
			final Class<?> traverseClass, final String name) {
		Class<?> clazz = traverseClass;
		Class<?> foundClazz = null;
		if ((clazz != null) && (!StringUtils.isEmpty(name))
				&& (!clazz.isInterface())) {
			while ((foundClazz == null) && (clazz.getSuperclass() != null)) {
				if (hasMethod(clazz, name)) {
					foundClazz = clazz;
				} else {
					clazz = clazz.getSuperclass();
				}
			}
		}
		return foundClazz;
	}

	/**
	 * Traverses the class and returns the interface which defines the searched
	 * method.
	 * 
	 * @param traverseInterface
	 *            the class to traversed
	 * @param name
	 *            the name of the method to be found
	 * @return the found interface, null if interface, name is null or the given
	 *         class is no interface
	 */
	public static Class<?> getInterfaceDeclaresMethod(
			final Class<?> traverseInterface, final String name) {
		Class<?> interfaze = traverseInterface;
		Class<?> foundInterfaze = null;
		if ((interfaze != null) && (!StringUtils.isEmpty(name))
				&& (interfaze.isInterface())) {
			int i = 0;
			final Class<?>[] interfaces = traverseInterface.getInterfaces();
			while ((foundInterfaze == null) && (i < interfaces.length)) {
				if (hasMethod(interfaze, name)) {
					foundInterfaze = interfaze;
				} else {
					interfaze = interfaces[i];
					i++;
				}
			}
		}
		return foundInterfaze;
	}
}
