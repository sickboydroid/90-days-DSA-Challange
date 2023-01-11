import java.security.InvalidParameterException;

/**
 * WITHOUT TAIL POINTER
 */
public class SinglyLinkedList2<T> {
	private Node<T> head;

	/* TC: O(1) */
	public void pushFront(T data) {
		head = new Node<>(data, head);
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
		return prevHead.data;
	}

	/* TC: O(n) */
	public void pushBack(T data) {
		if (head == null) { // no elements in list
			head = new Node<>(data);
			return;
		}
		getTail().next = new Node<>(data);
	}

	public T topBack() {
		if (head == null)
			return null;
		return getTail().data;
	}

	/*
	 * To prevent code repetition.
	 * TC: O(n)
	 */
	private Node<T> getTail() {
		Node<T> cur = head;
		while (cur.next != null)
			cur = cur.next;
		return cur;
	}

	/*
	 * TC: O(n)
	 * In Doubly linked list TC will O(1) as we will have pointer to prev node
	 */
	public T popBack() {
		if (head == null)
			throw new RuntimeException(new NullPointerException("No element/node to pop in list"));
		Node<T> prevTail;
		if (head.next == null) { // single element in list
			prevTail = head;
			head = null;
		} else {
			Node<T> newTail = head;
			while (newTail.next.next != null)
				newTail = newTail.next;
			prevTail = newTail.next;
			newTail.next = null;
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
	 *
	 * TIP: You do not need error checking in private methods (theoretically).
	 */

	/* TC: O(1) */
	private void insertAfter(Node<T> node, T data) {
		node.next = new Node<>(data, node.next);
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