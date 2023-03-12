# Binary Search

```java
public static int binarySearch(int[] data, int target, int left, int right) {
   if (left > right)
      return -1;

   int mid = (left + right) / 2;
   if (data[mid] > target)
      return binarySearch(data, target, 0, mid - 1);
   else if (data[mid] < target)
      return binarySearch(data, target, mid + 1, right);
   else
      return mid;
}
```
