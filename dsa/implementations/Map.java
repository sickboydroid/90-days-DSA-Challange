/**
 * Load factor represents the avg number of key-value pairs that should be in each bucket (linked list in this case)
 * lambda = size / buckets.length is the avg number of key-value pairs in each linked list at current time.
 * For constant time complexity, maintain lambda <= k where k is a constant and is called load factor.
 * Thus, avg time complexity is O(lambda) i.e O(1) as lambda <= k (constant)
 * Some stats:
 * ----- JAVA's HASHMAP: 4 s
 * ----- MINE MAP: 8 s
 */
public class Map<K, V> {
    private final int DEFAULT_CAPACITY = 16; // HashMap of Java uses by default 16 and then almost doubles on rehashing
    private List<Pair>[] buckets;
    private final float loadFactor = 0.75f; // HashMap of java uses by default 0.75f
    private int size = 0; // number of pairs in map

    public Map() {
        buckets = generateBuckets(DEFAULT_CAPACITY);
    }

    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>(buckets.length);
        for (List<Pair> bucket : buckets) {
            for (Pair pair : bucket)
                keySet.add(pair.key);
        }
        return keySet;
    }

    public Collection<V> values() {
        Collection<V> values = new LinkedList<>();
        for (List<Pair> bucket : buckets) {
            for (Pair pair : bucket)
                values.add(pair.value);
        }
        return values;
    }

    public V put(K key, V value) {
        if ((float) size / buckets.length >= loadFactor)
            rehash();
        int bucketIndex = getHash(key);
        for (Pair pair : buckets[bucketIndex]) {
            if (pair.key.equals(key)) {
                V oldValue = pair.value;
                pair.value = value;
                return oldValue;
            }
        }
        buckets[bucketIndex].add(new Pair(key, value));
        size++;
        return null;
    }

    public V get(K key) {
        int bucketIndex = getHash(key);
        for (Pair pair : buckets[bucketIndex]) {
            if (pair.key.equals(key)) return pair.value;
        }
        return null;
    }

    public boolean containsKey(K key) {
        int bucketIndex = getHash(key);
        for (Pair pair : buckets[bucketIndex]) {
            if (pair.key.equals(key)) return true;
        }
        return false;
    }

    public V remove(K key) {
        int bucketIndex = getHash(key);
        List<Pair> bucket = buckets[bucketIndex];
        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i).equals(key)) {
                size--;
                return bucket.remove(i).value;
            }
        }
        return null;
    }

    private int getHash(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    private void rehash() {
        List<Pair>[] newBuckets = generateBuckets(buckets.length * 2);
        for (List<Pair> bucket : buckets) {
            for (Pair pair : bucket) {
                int bucketIndex = getHash(pair.key);
                newBuckets[bucketIndex].add(pair);
            }
        }
        buckets = newBuckets;
    }

    @SuppressWarnings("unchecked")
    private List<Pair>[] generateBuckets(int size) {
        List<Pair>[] buckets = new LinkedList[size];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();

        }
        return buckets;
    }

    private class Pair {
        public K key;
        public V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}