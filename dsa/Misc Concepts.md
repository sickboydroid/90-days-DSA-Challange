# Misc Concepts

## Taking intersection of two ranges

```java
int[] range1 = {10, 30};
int[] range2 = {15, 20};
int[] intersection = new int[2];
intersection = Math.max(range1[0], range2[0]);
intersection = Math.min(range1[1], range2[1]);
```

## UNICODE

- Java uses **unicode** character set
- *[65-90]* -> **A-Z**
- *[97-122]* -> **a-z**
- To convert lowercase char to uppercase, sub 32
- To convert uppercase chat to lowercase, add 32

## Co-Primes

- Two numbers `a` and `b` (both are not zero simultaneously) are said to be co-primes if `gcd(a, b) = 1`
- Exceptionally, `0` is co-prime only with `1` and `-1`
- `1` and `1` are co-prime as well