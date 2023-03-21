# Leetcode Log

- Link to problem
- Problems you faced while solving
- No code (except when it is not on leetcode or is very important)
- New things you learnt
- Time and space complexity

## Contents

- [Leetcode Log](#leetcode-log)
  - [Contents](#contents)
  - [171. Linked List Cycle](#171-linked-list-cycle)
  - [101. Symmetric Tree (Easy)](#101-symmetric-tree-easy)
  - [8. String to Integer (atoi)](#8-string-to-integer-atoi)
  - [19. Remove Nth Node From End of List](#19-remove-nth-node-from-end-of-list)
  - [3. Longest Substring Without Repeating Characters](#3-longest-substring-without-repeating-characters)
  - [7. Reverse Integer](#7-reverse-integer)
  - [54. Spiral Matrix](#54-spiral-matrix)
  - [1706. Where Will the Ball Fall](#1706-where-will-the-ball-fall)
  - [33. Search in Rotated Sorted Array](#33-search-in-rotated-sorted-array)
  - [34. Find First and Last Position of Element in Sorted Array](#34-find-first-and-last-position-of-element-in-sorted-array)
  - [43. Multiply Strings](#43-multiply-strings)

## 171. Linked List Cycle

**Approach 1:** Use HashMap
  
    - TC: O(n)
    - SC: O(n)

**Approach 2:** [Floyd's Cycle Detection Algorithm](https://youtu.be/jcZtMh_jov0)
    - Uses two pointers, slow and fast pointer
    - Slow steps one node at a time and Fast steps two nodes at a time
    - Case 1:
      - If there is a loop then the fast pointer gets trapped in it
      - Slowly the slow pointer also reaches it and both stay in loop.
      - At some point both pointers point to the same node and thus we found the loop
    - Case 2:
      - If there is no loop then fast pointer reaches the end and we return
    - Finding node where loop starts
      - TC: O()
      - SC: O(1)

## [101. Symmetric Tree (Easy)](https://leetcode.com/problems/symmetric-tree/)

**Approach 1:** Recursive

**Apporach 2;** Iterative (TODO: I haven't implemented this one yet so implement it after learning about stacks and tree)

## [8. String to Integer (atoi)](https://leetcode.com/problems/string-to-integer-atoi/description/)

- **digitChar - '0' = digitInt**
- Even if you don't remember ascii code, use above trick
  - is char an int? char >= '0' && char <= '9'

## [19. Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list)

- Slow and fast pointer
- dummy head keeps pointer n + *1* steps ahead of slow pointer

## [3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/description/)

- Sliding window technique

## [7. Reverse Integer](https://leetcode.com/problems/reverse-integer/description/)

- Prevent Overflow and Underflow
- Learned a lot about checking overflow and underflow

```java

// Lets say you want to do y = y * 10 + digit. Below code will check if integer over/underflow will happen
// underflow
if(y > Integer.MAX_VALUE / 10 || (y == Integer.MAX_VALUE && digit > Integer.MAX_VALUE % 10))
    return 0;

// overflow
if(y < Integer.MIN_VALUE / 10 || (y == Integer.MIN_VALUE && digit < Integer.MIN_VALUE % 10))
    return 0;

```

## [54. Spiral Matrix](https://leetcode.com/problems/spiral-matrix/description/)

- Simulation is the key
- Left, right, top and bottom boundaries
- keep right and bottom exclusive for clean code

## [1706. Where Will the Ball Fall](https://leetcode.com/problems/where-will-the-ball-fall/description)

- Use dfs. Fancy name but i implemented it without even knowing about it
- Use row, col instead of x,y when dealing with 2d-arrays

## [33. Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/description/)

- NIGHTMARE. Revise it without any excuse
- Don't try to find the pivot. It is possible but lots of edge cases
- Use modified binary search (kind of)

## [34. Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/)

- ALWAYS REMEMBER, in binary search:
  - nums[lo] <= target <= nums[hi]
  - And all values outside this range ARE NOT TARGET

- Another method: Biased binary search was great

```java
// if left bias is true then it will return left most index of target
public int binarySearch(int[] nums, int target, boolean leftBias) {
    int lo = 0;
    int hi = nums.length - 1;
    int targetBiasedIndex = -1;
    while(lo <= hi) {
        int mid = lo + (hi - lo)/2;
        if(target < nums[mid])
            hi = mid - 1;
        else if(target > nums[mid])
            lo = mid + 1;
        else {
            targetBiasedIndex = mid;
            if(leftBias)
                hi = mid - 1;
            else
                lo = mid + 1;
        }
    }
    return targetBiasedIndex;
}
```

## [43. Multiply Strings](https://leetcode.com/problems/multiply-strings/submissions)

- Importance of using `StringBuilder` instead of concatenation in loops. **beats 30% -> beats 90%**
- Use `new BigInteger("123221312312412").toString(2)` to convert large numbers to any base
