package datastructures;

/**
 * An interface for the abstract datatype queue.
 *
 * @author Bror Bjerner
 * @version (2007)
 */

public interface Queues<E> {

	/**
	 * @return the number of elements in the queue
	 */
	public int size();

	/**
	 * Store a new object in the queue
	 *
	 * @param elem the element to store in the queue
	 */
	public void enqueue(E elem);

	/**
	 * Gives the next element in FIFO order and
	 * takes it away from the queue
	 *
	 * @throws NoSuchElementException if the que is empty
	 * @return the next element in the queue
	 */
	public E dequeue();

	/**
	 * Gives the next element in FIFO order
	 *
	 * @throws NoSuchElementException if the queue is empty
	 * @return the next element in the queue
	 */
	public E front();

	/**
	 * Checks if the queue has any elements stored
	 *
	 * @return true if the empty queue, false otherwise
	 */
	public boolean isEmpty();

}  // interfac Queues
