package org.example;

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
	private int size;

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

	/* TC: O(n) */
	public T valueAt(int index) {
		if (head == null)
			throw new RuntimeException(new NullPointerException("No element/node in list"));
		if (index < 0)
			throw new RuntimeException(new IllegalArgumentException("Index must be positive"));
		return getNodeAt(index).data;
	}

	/* TC: O(n) */
	public void insertAt(int index, T data) {
		if (head == null && index != 0)
			throw new RuntimeException(new NullPointerException("Cannot insert in empty list at " + index));
		if (index < 0)
			throw new RuntimeException(new IllegalArgumentException("Index must be positive"));
		insertBefore(getNodeAt(index), data);
	}

	/* TC: O(n) */
	public void remove(int index) {
		if (head == null)
			throw new RuntimeException(new IllegalStateException("Cannot remove node at " + index + " as list is empty"));
		if (index < 0)
			throw new RuntimeException(new IllegalArgumentException("Index must be positive"));
		if (index == 0) { // need to remove head
			if (head == tail) {
				popFront();
			}
		}

		Node<T> pre = getNodeAt(index - 1);
		if (pre.next == null) // tail cannot be previous element
			throw new RuntimeException(new IndexOutOfBoundsException());
		else if (pre.next.next == null)
			popBack();
		else // removing normal element
			pre.next = pre.next.next;
	}

	/*
	 * TC: O(n)
	 * Does not remove null values
	 */
	public boolean removeValue(T data) {
		if (head == null || data == null)
			return false;
		if (head.data != null && head.data.equals(data)) { // head to be removed
			popFront();
			return true;
		}
		Node<T> preNode = head;
		while (preNode.next != null) {
			if (preNode.next.data != null && preNode.next.data.equals(data))
				break;
			preNode = preNode.next;
		}
		if (preNode.next == null) // tail cannot be prev node
			return false;
		if (preNode.next == tail) { // tail to be removed
			popBack();
			return true;
		}
		preNode.next = preNode.next.next;
		return true;
	}

	private Node<T> getNodeAt(int index) {
		Node<T> cur = head;
		while (index > 0) {
			cur = cur.next;
			if (cur == null)
				throw new RuntimeException(new IndexOutOfBoundsException());
			index--;
		}
		return cur;
	}

	/* TC: O(n) */
	public void reverse() {
		if (head == tail || head == null)
			return;
		Node<T> pre = null;
		Node<T> cur = head;
		while (cur != null) {
			Node<T> next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}

		// manage head and tail pointers
		Node<T> tmp = head;
		head = tail;
		head.next = tail.next;
		tail = tmp;
		tail.next = null;
	}

	public void reverseRecursively() {
		Node<T> prevTail = tail;
		tail = reverseRecursively(head);
		head = prevTail;
	}

	private Node<T> reverseRecursively(Node<T> head) {
		if (head == null || head.next == null)
			return head;
		reverseRecursively(head.next).next = head;
		head.next = null; // for the last element
		return head;
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

	/* TC: O(n) */
	public int size() {
		int size = 0;
		Node<T> cur = head;
		while (cur != null) {
			cur = cur.next;
			size++;
		}
		return size;
	}

	@Override
	public String toString() {
		return "SinglyLinkedList{" + "head=" + head + '}';
	}
}
