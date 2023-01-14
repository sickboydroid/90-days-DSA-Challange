package Queue;

/*
 * Read pointer points to the value to be read
 * Write pointer points to the value last wrote
 * 
 * Queue is empty when read is -1
 * Queue is full when after increamenting write it becomes equal to read. But as our queue is
 * circular we use modulus to handle cases like below:
 * 		[a, b, c, d, f, g]
 * 	    ^              ^
 *       read          write
 */
public class ArrayQueue<T> {
	private int capacity;
	private int read = -1; // equivalent to front
	private int write = -1; // equivalent to rear
	private T[] arr;

	@SuppressWarnings("unchecked")
	public ArrayQueue(int capacity) {
		this.capacity = capacity;
		arr = (T[]) new Object[capacity];
	}

	public void enqueue(T data) {
		if (read == -1) // empty queue
			read = 0;
		else if (full()) // full queue
			throw new RuntimeException(new IllegalStateException("Queue is full"));
		write = ++write % capacity;
		arr[write] = data;
	}

	public T dequeue() {
		if (read == -1) // empty queue
			throw new RuntimeException(new IllegalStateException("Cannot dequeue an empty queue"));
		T data = arr[read];
		if (read == write) // queue will now be empty
			read = write = -1;
		else
			read = ++read % capacity;
		return data;
	}

	public boolean empty() {
		return read == -1;
	}

	public boolean full() {
		return read == (write + 1) % capacity;
	}
}
