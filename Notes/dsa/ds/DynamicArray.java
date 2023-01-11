import java.util.Arrays;

public class DynamicArray<E> {
	private static final int DEFAULT_INITIAL_CAPACITY = 10;
	private Object[] arr;
	private int size = 0;
	private int capacity = DEFAULT_INITIAL_CAPACITY;

	public DynamicArray() {
		arr = new Object[DEFAULT_INITIAL_CAPACITY];
	}

	@SuppressWarnings("unchecked")
	public E get(int index) {
		throwExceptionIfOutOfRange(index);
		return (E) arr[index];
	}

	public void set(int index, E value) {
		throwExceptionIfOutOfRange(index);
		arr[index] = value;
	}

	public void insert(int index, E value) {
		if (size == capacity)
			resize(capacity * 2);
		if (index == size - 1)
			push(value);
		for (int i = size - 1; i >= index; i--)
			arr[i + 1] = arr[i];
		arr[index] = value;
	}

	public void push(E value) {
		if (size == capacity)
			resize(capacity * 2);
		arr[size] = value;
		size++;
	}

	public E pop() {
		E element = get(size - 1);
		remove(size - 1);
		return element;
	}

	public void remove(int index) {
		throwExceptionIfOutOfRange(index);
		for (int i = index; i <= size - 1; i++)
			arr[i] = arr[i + 1];
		size--;
		if (size == 0.25 * capacity)
			resize(capacity / 2);
	}

	public int find(E value) {
		if (value == null)
			return -1;
		for (int i = 0; i < size; i++) {
			if (value.equals(arr[i]))
				return i;
		}
		return -1;
	}

	public int size() {
		return size;
	}

	public int getCapacity() {
		return capacity;
	}

	private void resize(int capacity) {
		this.capacity = capacity;
		arr = Arrays.copyOf(arr, capacity);
	}

	private void throwExceptionIfOutOfRange(int index) {
		if (index < 0 || index >= size)
			throw new RuntimeException(new IndexOutOfBoundsException());
	}

	@Override
	public String toString() {
		return Arrays.deepToString(arr);
	}
}