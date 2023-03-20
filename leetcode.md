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
