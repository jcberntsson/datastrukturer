package datastructures;

import testSortCol.CollectionWithGet;

/**
 * Author(s): Joakim Berntsson & Tim Kerschbaumer
 * TDA416
 * Group: 6
 * Laboration: 2.1
 * Purpose: A sorted version of a linked collection.
 */
public class SortedLinkedCollection<E extends Comparable<E>> extends LinkedCollection<E> implements CollectionWithGet<E> {

	/**
	 * Adds the specified element to the collection. The method will use the Comparable compareTo() to place it in order.
	 * @param element the object to add into the list
	 * @return true if the add was a success, false otherwise
	 */
	public boolean add(E element) {
		if (element == null) {
			throw new NullPointerException();
		} else if (head == null || head.element.compareTo(element) > 0) {
			// When the head is bigger than element or head is null, create new head
			head = new Entry(element, head);
		} else {
			Entry tmp = head;
			// Loop until the next element is smaller than element (and the current is bigger or equal)
			while (tmp.next != null && tmp.next.element.compareTo(element) < 0) {
				tmp = tmp.next;
			}
			// Create a new entry and link the current entry to it, and the "old" next as the new entry's next
			tmp.next = new Entry(element, tmp.next);
		}
		return true;
	}

	@Override
	public E get(E element) {
		Entry tmp = head;
		// Set comparison arbitrary integer not equal to zero (because zero implies that the element has been found)
		int comp = 1;
		// Loop until end of the list, a element "bigger" than element was found or the element was found
		while (tmp != null && (comp = tmp.element.compareTo(element)) < 0) {
			tmp = tmp.next;
		}
		// Return null if the element was not found, otherwise the element
		return comp != 0 ? null : tmp.element;
	}
}