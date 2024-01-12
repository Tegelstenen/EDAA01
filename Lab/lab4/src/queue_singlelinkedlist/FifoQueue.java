package queue_singlelinkedlist;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E e) {
	    QueueNode<E> n = new QueueNode<E>(e);
	    if (size == 0) {
	        last = n;
	        last.next = last; 
	    } else {
	        n.next = last.next; 
	        last.next = n; 
	        last = n; 
	    }
	    size++;
	    return true;
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if (size == 0) {
			return null;
		}
		return last.next.element;
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
	    if (last == null || last.next == null) { 
	        return null;
	    }

	    E element = last.next.element; 
	    last.next = last.next.next; 

	    if (last.next == null) { 
	        last = null;
	    }

	    size--;
	    return element;
	}
	
	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}
	
	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;
		
		private QueueIterator() {
			pos = (last != null) ? last.next : null;
		}
		
		@Override
	    public boolean hasNext() {
	        // hasNext should return true if the current position is not null.
	        return pos != null;
	    }

	    @Override
	    public E next() {
	        if (!hasNext()) {
	            throw new NoSuchElementException();
	        }
	        E item = pos.element;
	        pos = pos != last ? pos.next : null;
	        // This ensures that once we've returned the last element, the next call to hasNext will return false.
	        return item;
	    }
		
	}
	
	/**
	* Appends the specified queue to this queue
	* post: all elements from the specified queue are appended
	* to this queue. The specified queue (q) is empty after the call.
	* @param q the queue to append
	* @throws IllegalArgumentException if this queue and q are identical
	*/
	public void append(FifoQueue<E> q) {
		if (this == q) {
			
		}
	}
	
	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

}
