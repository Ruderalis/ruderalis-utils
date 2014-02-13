/**
 * 
 */
package at.ruderalis.utils.test.common.iterable;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import at.ruderalis.utils.common.iterable.IterableUtils;

/**
 * @author Thomas Herzog
 * @date Feb 13, 2014
 */
@RunWith(JUnit4.class)
public class IterableUtilsTest {

	private static class IterableImpl<T> implements Iterable<T> {

		int idx;
		final List<T> elements;

		public IterableImpl(final Collection<T> collection) {
			super();
			idx = -1;
			elements = new ArrayList<T>(collection);
		}

		public Iterator<T> iterator() {
			return new Iterator<T>() {

				public boolean hasNext() {
					return (idx + 1) < elements.size();
				}

				public T next() {
					return elements.get(++idx);
				}

				public void remove() {
					elements.remove(idx);
				}
			};
		}
	}

	@Test
	public void testIsEmpty_iterator_null() {
		assertTrue(IterableUtils.isEmpty((Iterator<String>) null));
	}

	@Test
	public void testIsEmpty_iterator_empty() {
		assertTrue(IterableUtils.isEmpty(new IterableImpl<String>(
				new ArrayList<String>()).iterator()));
	}

	@Test
	public void testIsEmpty_iterator() {
		assertFalse(IterableUtils.isEmpty(new IterableImpl<String>(Arrays
				.asList("Not")).iterator()));
	}

	@Test
	public void testIsEmpty_iterable_null() {
		assertTrue(IterableUtils.isEmpty((Iterable<String>) null));
	}

	@Test
	public void testIsEmpty_iterable_empty() {
		assertTrue(IterableUtils.isEmpty(new IterableImpl<String>(
				new ArrayList<String>()).iterator()));
	}

	@Test
	public void testIsEmpty_iterable() {
		assertFalse(IterableUtils.isEmpty(new IterableImpl<String>(Arrays
				.asList("Not"))));
	}

	@Test
	public void testUnmodifiableIterator_null() {
		assertNull(IterableUtils.unmodifiableIterable((Iterable<?>) null));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testUnmodifiableIterator_unsupported() {
		final List<String> list = Arrays.asList("Not", "meant", "to", "be",
				"deleted");
		final Iterable<String> iterable = new IterableImpl<String>(list);
		IterableUtils.unmodifiableIterator(iterable.iterator()).remove();
	}

	@Test
	public void testUnmodifiableIterator() {
		final List<String> list = Arrays.asList("Not", "meant", "to", "be",
				"deleted");
		final Iterable<String> iterable = new IterableImpl<String>(list);
		final Iterator<String> iterator = IterableUtils
				.unmodifiableIterator(iterable.iterator());
		int idx = 0;
		while (iterator.hasNext()) {
			assertEquals(list.get(idx), iterator.next());
			idx++;
		}
	}

	@Test
	public void testUnmodifiableIterable_null() {
		assertNull(IterableUtils.unmodifiableIterable((Iterable<?>) null));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testUnmodifiableIterableIterable_unsupported() {
		final Iterable<String> iterable = new IterableImpl<String>(
				Arrays.asList("Not", "meant", "to", "be", "deleted"));
		IterableUtils.unmodifiableIterable(iterable).iterator().remove();
	}

	@Test
	public void testUnmodifiableIterable() {
		final List<String> list = Arrays.asList("Not", "meant", "to", "be",
				"deleted");
		final Iterable<String> iterable = new IterableImpl<String>(list);
		final Iterator<String> iterator = IterableUtils.unmodifiableIterable(
				iterable).iterator();
		int idx = 0;
		while (iterator.hasNext()) {
			assertEquals(list.get(idx), iterator.next());
			idx++;
		}
	}

	@Test
	public void testToLinkedList() {
		final List<String> list = new LinkedList<String>(Arrays.asList("Not",
				"meant", "to", "be", "deleted"));
		final List<String> linked = IterableUtils
				.toLinkedList(new IterableImpl<String>(list));
		assertEquals(list.getClass(), linked.getClass());
		assertEquals(list, linked);
	}

	@Test
	public void testToArrayList() {
		final List<String> list = new ArrayList<String>(Arrays.asList("Not",
				"meant", "to", "be", "deleted"));
		final List<String> linked = IterableUtils
				.toArrayList(new IterableImpl<String>(list));
		assertEquals(list.getClass(), linked.getClass());
		assertEquals(list, linked);
	}

	@Test
	public void testToHashSet() {
		final Set<String> list = new HashSet<String>(Arrays.asList("Not",
				"meant", "to", "be", "deleted"));
		final Set<String> linked = IterableUtils
				.toHashSet(new IterableImpl<String>(list));
		assertEquals(list.getClass(), linked.getClass());
		assertEquals(list, linked);
	}

	@Test
	public void testToTreehSet_no_comparator() {
		final Set<String> set = new TreeSet<String>(Arrays.asList("Not",
				"meant", "to", "be", "deleted"));
		final Set<String> treeSet = IterableUtils
				.toTreeSet(new IterableImpl<String>(set));
		assertEquals(set.getClass(), treeSet.getClass());
		assertEquals(set, treeSet);
	}

	@Test
	public void testToTreehSet_comparator() {
		final Set<String> set = new TreeSet<String>(new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o2.compareTo(o1);
			}
		});
		set.addAll(Arrays.asList("Not", "meant", "to", "be", "deleted"));
		final Set<String> treeSet = IterableUtils
				.toTreeSet(new IterableImpl<String>(set));
		assertEquals(set.getClass(), treeSet.getClass());
		assertEquals(set, treeSet);
	}
}
