package datastructures;

/**
 * Created by: Tim Kerschbaumer & Joakim Berntsson
 * Project: lab2
 * Date: 15-02-10
 * Time: 17:14
 */
public class SortedLinkedCollection<E extends Comparable<E>> extends LinkedCollection<E> {

	@Override
	public boolean add(E element) {
		if (element == null) {
			throw new NullPointerException();
		} else if (this.isEmpty() || element.compareTo(head.element) < 0) {
			head = new Entry(element, head);
		} else {
			Entry myHead = this.head;
			while (myHead.next != null && element.compareTo(myHead.next.element) > 0) {
				myHead = myHead.next;
			}
			myHead.next = new Entry(element, myHead.next);
		}
		return true;
	}

	public E get(E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		Entry myHead = this.head;
		while (myHead != null && myHead.element.compareTo(element) != 0) {
			myHead = myHead.next;
		}
		return (myHead != null ? myHead.element : null);
	}
}