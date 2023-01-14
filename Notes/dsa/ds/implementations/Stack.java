/*
 * Implementation os stack with array is useless
 * Every operation is order 1 i.e O(1)
 * */
public class Stack<T> {
   private Node<T> head;

   public void push(T data) {
      Node<T> newNode = new Node<T>(data);
      if (head != null)
         newNode.next = head;
      head = newNode;
   }

   public T top() {
      if (head == null)
         throw new RuntimeException(new IllegalStateException("No element to top"));
      return head.data;
   }

   public T pop() {
      if (head == null)
         throw new RuntimeException(new IllegalStateException("No element to pop"));
      Node<T> prevHead = head;
      head = head.next;
      return prevHead.data;
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