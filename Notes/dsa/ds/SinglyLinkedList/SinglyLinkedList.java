import java.security.InvalidParameterException;

/**
 * SinglyLinkedList with tail
 * <br>
 * Usually we have a class that manages head and tail pointers. However, you can
 * store these
 * pointers in variable. But commonly we create a class that holds these
 * pointers and some
 * other utility methods.
 * <br>
 * <ul>
 * <li>To enforce invariants, never expose @SNode</li>
 * <li>Insertion is O(1) till you have pointer to prev Node where you want to
 * insert</li>
 * </ul>
 */
public class SinglyLinkedList<T> {
	private Node<T> head;
	private Node<T> tail;

	/* TC: O(1) */
	public void pushFront(T data) {
		head = new Node<>(data, head);
		if (tail == null) // previously list was empty
			tail = head;
	}

	/* TC: O(1) */
	public T topFront() {
		if (head == null)
			return null;
		return head.data;
	}

	/* TC: 0(1) */
	public T popFront() {
		if (head == null)
			throw new RuntimeException(new NullPointerException("No element/node to pop in list"));
		Node<T> prevHead = head;
		head = head.next;
		if (head == null) // in case there is no element left
			tail = null;
		return prevHead.data;
	}

	/* TC: O(1) */
	public void pushBack(T data) {
		Node<T> newTail = new Node<>(data);
		if (tail == null) { // this is first element
			head = newTail;
			tail = newTail;
			return;
		}
		tail.next = newTail;
		tail = newTail;
	}

	public T topBack() {
		if (tail == null)
			return null;
		return tail.data;
	}

	/*
	 * TC: O(n)
	 * In Doubly linked list TC will O(1) as we will have pointer to prev node
	 */
	public T popBack() {
		if (tail == null)
			throw new RuntimeException(new NullPointerException("No element/node to pop in list"));

		Node<T> prevTail = tail;
		if (head == tail) { // single element in list
			head = null;
			tail = null;
		} else {
			// get element previous to tail
			Node<T> cur = head;
			while (cur.next.next != null)
				cur = cur.next;
			tail = cur;
			cur.next = null;
		}
		return prevTail.data;
	}

	/* TC: O(n) */
	public boolean contains(T data) {
		if (data == null)
			throw new RuntimeException(new InvalidParameterException("data cannot be null"));
		Node<T> node = head;
		while (node != null) {
			if (node.data != null && node.data.equals(data))
				return true;
			node = node.next;
		}
		return false;
	}

	public boolean isEmpty() {
		return head == null;
	}

	/*
	 * You can also have insertAfter(SNode) and insertBefore(SNode). But this will
	 * not enforce
	 * the invariants as we are exposing nodes.
	 * TIP: You do not need error checking in private methods (theoretically).
	 */

	/* TC: O(1) */
	private void insertAfter(Node<T> node, T data) {
		Node<T> newNode = new Node<>(data, node.next);
		node.next = newNode;
		if (tail == node) // if provided node was tail
			tail = newNode;
	}

	/*
	 * TC: O(n)
	 * In Doubly linked list the TC will be O(1)
	 */
	private void insertBefore(Node<T> node, T data) {
		if (head == null || node.equals(head)) { // node is head or there is no element
			pushFront(data);
			return;
		}

		Node<T> prevNode = head;
		while (prevNode.next != node)
			prevNode = prevNode.next;
		insertAfter(prevNode, data);
	}

	@Override
	public String toString() {
		return "SinglyLinkedList{" + "head=" + head + '}';
	}
}
