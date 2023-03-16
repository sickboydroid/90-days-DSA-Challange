# Binary Search

## Recursive approach

```java
public static int rank(int key, int[] input, int lo, int hi) {
         if(lo > hi)
               return -1;
         int mid = lo + (hi - lo) / 2;
         if(key < input[mid])      return rank(key, input, lo, mid -1);
         else if(key > input[mid]) return rank(key, input, mid + 1, hi);
         else                      return mid;
}
```

## Loop approach

```java
public static int binarySearch(int key, int[] input) {
                int lo = 0;
                int hi = input.length - 1;
                while(lo <= hi) {
                        int mid = (lo + hi) / 2;
                        if(key == input[mid]) return mid;
                        else if(key < input[mid]) hi = mid - 1;
                        else lo = mid + 1;
                }
                return -1;
}
```

- Use lo + (hi-lo)/2 instead of (hi + lo)/2 to prevent integer overflow 
- Slight modification (combine two conditions and return low/high) can allow you find number of values less or greater than the key even if it is not present