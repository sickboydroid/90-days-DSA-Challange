package Queue;

/*
 * Implementation of Queue using Singly Linked List with both head and tail pointers
 * Every operation must be of order 1 i.e O(1)
 *
 * A bad implementation using linked list is where you enqueue at head and dequeue at
 * tail would be O(n) because you'd need the next to last element, causing a full traversal
 * each dequeue. Also, it is not inline with the real world queue where new people are added
 * at the end of line.
 * */
public class LinkedListQueue<T> {
	private Node<T> head;
	private Node<T> tail;

	// works just like pushBack(...) of LinkedList
	public void enqueue(T data) {
		Node<T> node = new Node<>(data);
		if (head == null) {
			head = node;
			tail = node;
			return;
		}
		tail.next = node;
		tail = node;
	}

	// works just like popFront(...) of LinkedList
	public T dequeue() {
		if (head == null)
			throw new RuntimeException(new IllegalStateException("Cannot dequeue empty queue"));
		if (head == tail) // only single element was present
			tail = null;
		Node<T> oldHead = head;
		head = head.next;
		return oldHead.data;
	}

	public boolean empty() {
		return head == null;
	}
}

class Node<T> {
	T data;
	Node<T> next;

	public Node() {
	}

	public Node(T data) {
		this.data = data;
	}

	public Node(T data, Node<T> next) {
		this.data = data;
		this.next = next;
	}
}
