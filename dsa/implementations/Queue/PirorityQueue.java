/*************Using BINARY Heap******************/
public static class MinPQ<Key extends Comparable<Key>> {

  private Key[] pq;
  private int N = 0; // index and size of pq

  public MinPQ(int numElements) {
    pq = (Key[]) new Comparable[numElements + 1];
  }

  /***Heap Sort***/
  /**
   * Heap Sort: TC: O(nlogn) and SC: O(1)
   * We are building the heap-bottom up and then getting elements from top and placing them at end
   */
  public static <Key extends Comparable<Key>> Key[] sort(Key[] keys) {
    MinPQ<Key> pq = new MinPQ<>(keys);
    /* Get parent of last element and sink it */
    for (int i = keys.length / 2; i >= 1; i--) pq.sink(i);
    for (int i = keys.length - 1; i >= 1; i--) {
      pq.exch(1, pq.N);
      pq.N--;
      pq.sink(1);
    }

    return keys;
  }

  private MinPQ(Key[] pq) {
    this.pq = pq;
    this.N = pq.length - 1;
  }

  /**
   * Heap Sort: TC: O(nlogn) and SC: O(1)
   * We are building the heap-bottom up and then getting elements from top and placing them at end
   * */
  public static <Key extends Comparable<Key>> Key[] sort(Key[] keys) {
    MinPQ<Key> pq = new MinPQ<>(keys);
    /* Get parent of last element and sink it */
    for (int i = keys.length / 2; i >= 1; i--) pq.sink(i);
    for (int i = keys.length - 1; i >= 1; i--) {
      pq.exch(1, pq.N);
      pq.N--;
      pq.sink(1);
    }

    return keys;
  }

  public void add(Key key) {
    if (key == null) return;
    pq[++N] = key;
    swim(N);
  }

  public Key delMax() {
    Key max = pq[1];
    pq[1] = pq[N];
    pq[N--] = null; // prevent loitering
    sink(1);
    return max;
  }

  public Key max() {
    return pq[1];
  }

  public int size() {
    return N;
  }

  private void swim(int k) {
    while (k > 1 && less(k, k / 2)) {
      exch(k, k / 2);
      k = k / 2;
    }
  }

  private void sink(int k) {
    while (2 * k <= N) {
      int l = 2 * k;
      if (l < N && less(l + 1, l)) l++;
      if (less(k, l)) break;
      exch(k, l);
      k = l;
    }
  }

  private void exch(int a, int b) {
    Key temp = pq[a - 1];
    pq[a - 1] = pq[b - 1];
    pq[b - 1] = temp;
  }

  public boolean less(int a, int b) {
    return pq[a - 1].compareTo(pq[b - 1]) < 0;
  }
}

/*************ELEMENTARY IMPLEMENTATIONS*****************/
/**
 * Implementation of priority queue using an unordered array
 */
static class UnorderedMaxPQ<Key extends Comparable<Key>> {

  Key[] pq;
  int N; // number of elements in pq

  @SuppressWarnings("unchecked")
  public UnorderedMaxPQ(int capacity) {
    pq = (Key[]) new Comparable[capacity];
  }

  public void insert(Key key) {
    pq[N++] = key;
  }

  public Key delMax() {
    max();
    return pq[--N];
  }

  public Key max() {
    for (int i = 0; i < N - 1; i++) { // bring the largest element to end of array
      if (pq[N - 1].compareTo(pq[i]) < 0) swap(pq, i, N - 1);
    }
    return pq[N - 1];
  }

  public boolean isEmpty() {
    return N == 0;
  }

  public int size() {
    return N;
  }

  public int getCapacity() {
    return pq.length;
  }
}

/**
 * Implementation of priority queue using an ordered array
 */
static class OrderedMaxPQ<Key extends Comparable<Key>> {

  Key[] pq;
  int N = 0; // number of elements in pq

  @SuppressWarnings("unchecked")
  public OrderedMaxPQ(int capacity) {
    pq = (Key[]) new Comparable[capacity];
  }

  public void insert(Key key) {
    pq[N] = key;
    for (int i = N; i > 0; i--) {
      if (pq[i].compareTo(pq[i - 1]) < 0) swap(pq, i, i - 1); else break; // i-1 element is bigger => voilation
    }
    N++;
  }

  public Key delMax() {
    return pq[--N];
  }

  public Key max() {
    return pq[N - 1];
  }

  public boolean isEmpty() {
    return N == 0;
  }

  public int size() {
    return N;
  }

  public int getCapacity() {
    return pq.length;
  }
}
