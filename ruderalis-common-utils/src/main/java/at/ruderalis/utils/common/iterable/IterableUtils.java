/**
 * 
 */
package at.ruderalis.utils.common.iterable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class provides utility methods for wrapping {@link Iterable} isntance to
 * unmodifiable instance where no elements can be removed. Also the iterable
 * instance can be converted to all common {@link Collection} implementations.
 * 
 * @author Thomas Herzog
 * @date Feb 13, 2014
 */
public class IterableUtils {

	/**
	 * Returns a unmodifiable {@link Iterable} instance
	 * 
	 * @param iterable
	 *            the iterable to be wrapped
	 * @return the wrapped iterable
	 * @see UnmodifiableIterable
	 */
	public static <T> UnmodifiableIterable<T> unmodifiableIterable(
			final Iterable<T> iterable) {
		return (iterable != null) ? new UnmodifiableIterable<T>(iterable)
				: null;
	}

	/**
	 * Returns a unmodifiable {@link Iterator} instance
	 * 
	 * @param iterator
	 *            the iterator to be wrapped
	 * @return the wrapped iterator
	 * @see UnmodifiableIterator
	 */
	public static <T> UnmodifiableIterator<T> unmodifiableIterator(
			final Iterator<T> iterator) {
		return (iterator != null) ? new UnmodifiableIterator<T>(iterator)
				: null;
	}

	/**
	 * Answers the question if this {@link Iterable} instance is empty or not.
	 * 
	 * @param iterable
	 *            the iterable to check
	 * @return true if the iterable is either null, or the iterator has no next
	 *         element
	 */
	public static <T> boolean isEmpty(final Iterable<T> iterable) {
		return (iterable == null) || (!iterable.iterator().hasNext());
	}

	/**
	 * Answers the question if this {@link Iterable} instance is empty or not.
	 * 
	 * @param iterable
	 *            the iterable to check
	 * @return true if the iterable is either null, or the iterator has no next
	 *         element
	 */
	public static <T> boolean isEmpty(final Iterator<T> iterator) {
		return (iterator == null) || (!iterator.hasNext());
	}

	/**
	 * Converts the {@link Iterator} instance to a {@link LinkedList}.
	 * 
	 * @param iterable
	 *            the iterable instance to be converted
	 * @return the linked list containing all elements of the iterable
	 */
	public static <T> List<T> toLinkedList(final Iterable<T> iterable) {
		return (List<T>) toCollection(new LinkedList<T>(), iterable);
	}

	/**
	 * Converts the {@link Iterator} instance to a {@link ArrayList}.
	 * 
	 * @param iterable
	 *            the iterable instance to be converted
	 * @return the array list containing all elements of the iterable
	 */
	public static <T> List<T> toArrayList(final Iterable<T> iterable) {
		return (List<T>) toCollection(new ArrayList<T>(), iterable);
	}

	public static <T> Set<T> toHashSet(final Iterable<T> iterable) {
		return (Set<T>) toCollection(new HashSet<T>(), iterable);
	}

	public static <T> Set<T> toTreeSet(final Iterable<T> iterable) {
		return toTreeSet(iterable, (Comparator<T>) null);
	}

	public static <T> Set<T> toTreeSet(final Iterable<T> iterable,
			final Comparator<T> comparator) {
		return (Set<T>) toCollection(new TreeSet<T>(comparator), iterable);
	}

	private static <T> Collection<T> toCollection(
			final Collection<T> collection, final Iterable<T> iterable) {
		if (!isEmpty(iterable)) {
			final Iterator<T> it = IterableUtils.unmodifiableIterable(iterable)
					.iterator();
			while (it.hasNext()) {
				collection.add(it.next());
			}
		}
		return collection;
	}

	/**
	 * This {@link Iterable} implementation returns a
	 * {@link UnmodifiableIterator} which disallows the removing of this
	 * iterator.
	 * 
	 * @author Thomas Herzog
	 * @date Feb 13, 2014
	 * @param <T>
	 * @see UnmodifiableIterator
	 */
	public static class UnmodifiableIterable<T> implements Iterable<T> {

		private final Iterable<T> iterable;

		/**
		 * @param iterable
		 */
		public UnmodifiableIterable(final Iterable<T> iterable) {
			super();
			this.iterable = iterable;
		}

		public Iterator<T> iterator() {
			return new UnmodifiableIterator<T>(iterable.iterator());
		}
	}

	/**
	 * This iterator disallows removing of elements of this iterator.
	 * 
	 * @author Thomas Herzog
	 * @date Feb 13, 2014
	 * @param <T>
	 */
	public static class UnmodifiableIterator<T> implements Iterator<T> {

		private final Iterator<T> iterator;

		/**
		 * @param iterator
		 */
		public UnmodifiableIterator(final Iterator<T> iterator) {
			super();
			this.iterator = iterator;
		}

		/**
		 * @return
		 * @see java.util.Iterator#hasNext()
		 */
		public boolean hasNext() {
			return iterator.hasNext();
		}

		/**
		 * @return
		 * @see java.util.Iterator#next()
		 */
		public T next() {
			return iterator.next();
		}

		/**
		 * @see java.util.Iterator#remove()
		 * @throws UnsupportedOperationException
		 *             if someone tries to remove elements of this
		 *             {@link Iterator}
		 */
		public void remove() {
			throw new UnsupportedOperationException(
					"Umodifyable iterator disallowes removing of elements");
		}

	}
}
