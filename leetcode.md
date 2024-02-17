# Leetcode Log

- Link to problem
- Problems you faced while solving
- No code (except when it is not on leetcode or is very important)
- New things you learnt
- Time and space complexity

## Contents

- [Leetcode Log](#leetcode-log)
  - [Contents](#contents)
  - [Worthy mentions (not from leetcode)](#worthy-mentions-not-from-leetcode)
    - [Single non-repeating element in array](#single-non-repeating-element-in-array)
  - [Blind75](#blind75)
    - [Problems List](#problems-list)
      - [Array](#array)
      - [Binary](#binary)
      - [Dynamic Programming](#dynamic-programming)
      - [Graph](#graph)
      - [Interval](#interval)
      - [Linked List](#linked-list)
      - [Matrix](#matrix)
      - [String](#string)
      - [Tree](#tree)
      - [Heap](#heap)
  - [207. Course Schedule](#207-course-schedule)
  - [417. Pacific Atlantic Water Flow](#417-pacific-atlantic-water-flow)
  - [128. Longest Consecutive Sequence](#128-longest-consecutive-sequence)
  - [322. Coin Change](#322-coin-change)
  - [8. String to Integer (atoi)](#8-string-to-integer-atoi)
  - [19. Remove Nth Node From End of List](#19-remove-nth-node-from-end-of-list)
  - [3. Longest Substring Without Repeating Characters](#3-longest-substring-without-repeating-characters)
  - [7. Reverse Integer](#7-reverse-integer)
  - [54. Spiral Matrix](#54-spiral-matrix)
  - [1706. Where Will the Ball Fall](#1706-where-will-the-ball-fall)
  - [33. Search in Rotated Sorted Array](#33-search-in-rotated-sorted-array)
  - [34. Find First and Last Position of Element in Sorted Array](#34-find-first-and-last-position-of-element-in-sorted-array)
  - [43. Multiply Strings](#43-multiply-strings)
  - [39. Combination Sum](#39-combination-sum)
  - [37. Sudoku Solver](#37-sudoku-solver)
  - [2220. Minimum Bit Flips to Convert Number](#2220-minimum-bit-flips-to-convert-number)
  - [40. Combination Sum II](#40-combination-sum-ii)
  - [746. Min Cost Climbing Stairs](#746-min-cost-climbing-stairs)
  - [1823. Find the Winner of the Circular Game](#1823-find-the-winner-of-the-circular-game)
  - [198. House Robber](#198-house-robber)
  - [213. House Robber II](#213-house-robber-ii)
  - [50. Pow(x, n)](#50-powx-n)
  - [876. Middle of the Linked List](#876-middle-of-the-linked-list)
  - [143. Reorder List](#143-reorder-list)
  - [796. Rotate String](#796-rotate-string)
  - [55. Jump Game](#55-jump-game)
  - [45. Jump Game II](#45-jump-game-ii)
  - [53. Maximum Subarray](#53-maximum-subarray)
  - [46. Permutations](#46-permutations)
  - [78. Subsets](#78-subsets)
  - [79. Word Search](#79-word-search)
  - [1161. Maximum Level Sum of a Binary Tree](#1161-maximum-level-sum-of-a-binary-tree)
  - [102. Binary Tree Level Order Traversal](#102-binary-tree-level-order-traversal)
  - [797. All Paths From Source to Target](#797-all-paths-from-source-to-target)
  - [994. Rotting Oranges](#994-rotting-oranges)
  - [200. Number of Islands](#200-number-of-islands)
  - [72. Edit Distance](#72-edit-distance)

## Worthy mentions (not from leetcode)

### Single non-repeating element in array

Given an array with every element repeating exactly twice except one. Find that non-repeating element

0 < arr[i] < 10^4

Return the element

```java
public int find(int[] arr) {
    int target = 0;
    for(int element : arr)
        target ^= element;
}
```

Logic: a^b^c^a^b is equal to c because XOR of same number gives zero and XOR of any number with zero gives back that number

Single non-repeating element in array

Given an array with every element repeating exactly three times except one. Find that non-repeating element.

This element occurs exactly once

0 < arr[i] < 10^4

Return the element

```java
/*
* Generate bits arr of length 32
* For each number increment the bits[i] for every ith set bit of number
* At end see where the bits are 3n + 1 i.e not multiple of 3
* Generate your number from them
*/
```

## Blind75

### Problems List

#### Array

- [x] [Two Sum](https://leetcode.com/problems/two-sum/)
- [x] [Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/)
- [x] [Contains Duplicate](https://leetcode.com/problems/contains-duplicate/)
- [x] [Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/)
- [x] [Maximum Subarray](https://leetcode.com/problems/maximum-subarray/)
- [x] [Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray/)
- [x] [Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/)
- [x] [Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/)
- [x] [3 Sum](https://leetcode.com/problems/3sum/)
- [x] [Container With Most Water](https://leetcode.com/problems/container-with-most-water/)

#### Binary

- [x] [Sum of Two Integers](https://leetcode.com/problems/sum-of-two-integers/)
- [x] [Number of 1 Bits](https://leetcode.com/problems/number-of-1-bits/)
- [x] [Counting Bits](https://leetcode.com/problems/counting-bits/)
- [x] [Missing Number](https://leetcode.com/problems/missing-number/)
- [x] [Reverse Bits](https://leetcode.com/problems/reverse-bits/)

#### Dynamic Programming

- [x] [Climbing Stairs](https://leetcode.com/problems/climbing-stairs/)
- [x] [Coin Change](https://leetcode.com/problems/coin-change/)
- [ ] [Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/)
- [x] [Longest Common Subsequence](https://leetcode.com/problems/longest-common-subsequence/)
- [x] [Word Break Problem](https://leetcode.com/problems/word-break/)
- [ ] [Combination Sum](https://leetcode.com/problems/combination-sum-iv/)
- [x] [House Robber](https://leetcode.com/problems/house-robber/)
- [x] [House Robber II](https://leetcode.com/problems/house-robber-ii/)
- [ ] [Decode Ways](https://leetcode.com/problems/decode-ways/)
- [x] [Unique Paths](https://leetcode.com/problems/unique-paths/)
- [x] [Jump Game](https://leetcode.com/problems/jump-game/)

#### Graph

- [x] [Clone Graph](https://leetcode.com/problems/clone-graph/)
- [x] [Course Schedule](https://leetcode.com/problems/course-schedule/)
- [x] [Pacific Atlantic Water Flow](https://leetcode.com/problems/pacific-atlantic-water-flow/)
- [x] [Number of Islands](https://leetcode.com/problems/number-of-islands/)
- [x] [Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/)
- [ ] [Alien Dictionary (Leetcode Premium)](https://leetcode.com/problems/alien-dictionary/)
- [ ] [Graph Valid Tree (Leetcode Premium)](https://leetcode.com/problems/graph-valid-tree/)
- [ ] [Number of Connected Components in an Undirected Graph (Leetcode Premium)](https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/)

#### Interval

- [ ] [Insert Interval](https://leetcode.com/problems/insert-interval/)
- [ ] [Merge Intervals](https://leetcode.com/problems/merge-intervals/)
- [ ] [Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/)
- [ ] [Meeting Rooms (Leetcode Premium)](https://leetcode.com/problems/meeting-rooms/)
- [ ] [Meeting Rooms II (Leetcode Premium)](https://leetcode.com/problems/meeting-rooms-ii/)

#### Linked List

- [ ] [Reverse a Linked List](https://leetcode.com/problems/reverse-linked-list/)
- [ ] [Detect Cycle in a Linked List](https://leetcode.com/problems/linked-list-cycle/)
- [ ] [Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/)
- [ ] [Merge K Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/)
- [ ] [Remove Nth Node From End Of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/)
- [ ] [Reorder List](https://leetcode.com/problems/reorder-list/)

#### Matrix

- [ ] [Set Matrix Zeroes](https://leetcode.com/problems/set-matrix-zeroes/)
- [ ] [Spiral Matrix](https://leetcode.com/problems/spiral-matrix/)
- [ ] [Rotate Image](https://leetcode.com/problems/rotate-image/)
- [ ] [Word Search](https://leetcode.com/problems/word-search/)

#### String

- [ ] [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/)
- [ ] [Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/)
- [ ] [Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/)
- [ ] [Valid Anagram](https://leetcode.com/problems/valid-anagram/)
- [ ] [Group Anagrams](https://leetcode.com/problems/group-anagrams/)
- [ ] [Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)
- [ ] [Valid Palindrome](https://leetcode.com/problems/valid-palindrome/)
- [ ] [Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/)
- [ ] [Palindromic Substrings](https://leetcode.com/problems/palindromic-substrings/)
- [ ] [Encode and Decode Strings (Leetcode Premium)](https://leetcode.com/problems/encode-and-decode-strings/)

#### Tree

- [ ] [Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/)
- [ ] [Same Tree](https://leetcode.com/problems/same-tree/)
- [ ] [Invert/Flip Binary Tree](https://leetcode.com/problems/invert-binary-tree/)
- [ ] [Binary Tree Maximum Path Sum](https://leetcode.com/problems/binary-tree-maximum-path-sum/)
- [ ] [Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/)
- [ ] [Serialize and Deserialize Binary Tree](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/)
- [ ] [Subtree of Another Tree](https://leetcode.com/problems/subtree-of-another-tree/)
- [ ] [Construct Binary Tree from Preorder and Inorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)
- [ ] [Validate Binary Search Tree](https://leetcode.com/problems/validate-binary-search-tree/)
- [ ] [Kth Smallest Element in a BST](https://leetcode.com/problems/kth-smallest-element-in-a-bst/)
- [ ] [Lowest Common Ancestor of BST](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/)
- [ ] [Implement Trie (Prefix Tree)](https://leetcode.com/problems/implement-trie-prefix-tree/)
- [ ] [Add and Search Word](https://leetcode.com/problems/add-and-search-word-data-structure-design/)
- [ ] [Word Search II](https://leetcode.com/problems/word-search-ii/)

#### Heap

- [ ] [Merge K Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/)
- [x] [Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/)
- [ ] [Find Median from Data Stream](https://leetcode.com/problems/find-median-from-data-stream/)

## [207. Course Schedule](https://leetcode.com/problems/course-schedule/)

- <b style='color: black; background-color: #FDFF32; padding: 3px; border: 4px solid tomato;'>REWISE: 0</b>
- Cycle in graph indicates course can't be completed
- Use three states for each course
  - 0: Not sure: I haven't started testing whether this course can be completed or not
  - 1: Testing: Currently i am walking through the portion of graph of which this is part.
    - If you encounter course with 1, there is a cycle
  - -1: Completed: Course that has been completed so any course whose prerequisite is this can also be completed

## [417. Pacific Atlantic Water Flow](https://leetcode.com/problems/pacific-atlantic-water-flow/)

- <b style='color: black; background-color: #FDFF32; padding: 3px; border: 4px solid tomato;'>REWISE: 0</b>
- **Question Things:**

  - Think in reverse, find all cells from pacific ocean from where rain water can enter in it. Since you are doing in reverse, you will move from a cell with LOWER height to a cell with HEIGHT height
  - Do same for other ocean
  - Take intersection of cells of both ocean to get your answer

- **Python things:**

  - `0 <= x < K` is correct python syntax ($#!t)
  - For moving to adjacent cells use following:

    ```python
    directions = ((0, 1), (1, 0), (0, -1), (-1, 0))
    for dr, dc in directions:
      new_r, new_c = r + dr, c + dc
      if 0 <= new_r < ROWS and 0 <= new_c < COLS:
        ...
    ```

## [128. Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/)

- <b style='color: black; background-color: #FDFF32; padding: 3px; border: 4px solid tomato;'>REWISE: 0</b>
- **Question Things:**
  - Use set
  - Iterate through the set and check if the current number is a start of sequence (i.e there is no other number 1 smaller than it). If it is find the length of sequence (i.e biggest number in this seq)

## [322. Coin Change](https://leetcode.com/problems/coin-change/)

- <b style='color: black; background-color: #FDFF32; padding: 3px; border: 4px solid tomato;'>REWISE: 0</b>
- **Question Things:**
  - Solution exists in three methods:
    1. Brute-force (don't submit)
    2. Use memo (you can submit)
    3. True/pure dp solution (no recursion)
  - Brute-force solution was not that hard
  - I still need to work with "what goes in memo as a key?"
  - DP solution i literally copied and pasted. Do review dp notes again

## [8. String to Integer (atoi)](https://leetcode.com/problems/string-to-integer-atoi/description/)

- **digitChar - '0' = digitInt**
- Even if you don't remember ascii code, use above trick
  - is char an int? char >= '0' && char <= '9'

## [19. Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list)

- Slow and fast pointer
- dummy head keeps pointer n + _1_ steps ahead of slow pointer

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

- NIGHTMARE. ==Revise== it without any excuse
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

## [39. Combination Sum](https://leetcode.com/problems/combination-sum/description/)

- ==REVISE==
- It is so confusing

## [37. Sudoku Solver](https://leetcode.com/problems/sudoku-solver/)

- Phew! I will take credit for solving at first try however i took some help from IDE and chatGPT
- Finding grid (3x3) of cell.
  - row = 3 \* (rowCell/3)
  - col = 3 \* (colCell/3)
  - It works because dividing rowCell by 3 will give us 0, 1 and 2 i.e either first 3 or 2nd 3 or 3rd 3 are the possible grids
    - Multiplying it by 3 will give the row of the first cells of these grids
  - Same for second

## [2220. Minimum Bit Flips to Convert Number](https://leetcode.com/problems/minimum-bit-flips-to-convert-number/description/)

- Use bit wise manipulation
- Approach 1: Uses constant time. Basically we run a loop 32 times and check if the two bits are different
- Approach 2: Uses constant time. But we run loop on exactly that number of time as the number of set bits in `a^b`

```java
public int minBitFlips(int start, int goal) {
    int diff = (start ^ goal); // diff will have those bits set which are different
    // n & (n-1) sets the last least sig "set" bit to 0
    // 001101 & 001100 => 001100
    // 100000 & 011111 => 000000
    // e.g 1101 -> 1100 -> 1000 -> 0000
    int count = 0;
    while(diff != 0) {
        diff = diff & (diff - 1);
        count++;
    }
    return count;
}
```

## [40. Combination Sum II](https://leetcode.com/problems/combination-sum-ii/description/)

- Duplicate lists is the main problem
- Think of it this way,
  1. Sort the given candidates array, e.g [1 1 1 2 3 3]
  2. In your list, Isn't picking 1 at 0th index either from 0 1 or 2 equivalent?
     - All will give you give sublist `[1] [1] [1]`. So pick only one and skip the rest.
     - In next method call, we will be picking 1th index. Then again we will include only one 1 to get `[11]` and not `[11][11]`

> In nutshell, when we are excluding an element, we are excluding all of its copies. However, when we are including it, we do so normally.

```java
class Solution {
    List<List<Integer>> res;
    int[] candidates;
    int target;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates); // sort to group duplicates
        res = new ArrayList<>();
        this.candidates = candidates;
        this.target = target;
        solve(0, new ArrayList<>(), 0);
        return res;
    }

    public void solve(int index, ArrayList<Integer> cur, int curSum) {
        if(curSum == target) {
            res.add(new ArrayList<Integer>(cur));
            return;
        }

        if(curSum > target || index == candidates.length)
            return;

        cur.add(candidates[index]);
        solve(index + 1, cur, curSum + candidates[index]); // include current element
        cur.remove(cur.size() - 1); // backtrack

        // skip duplicates. From the example, if you did not skip the duplicates,
        // we will call solve with solve(1, [], 0) and solve will do the same exact thing
        // as we did few lines above. Which will ultimately give duplicate solutions
        while(index + 1 < candidates.length && candidates[index] == candidates[index + 1])
            index++;
        solve(index + 1, cur, curSum); // exclude
    }
}
```

## [746. Min Cost Climbing Stairs](https://leetcode.com/problems/min-cost-climbing-stairs/description/?envType=study-plan&id=dynamic-programming-i)

- ==REVISE==
- You can do `return memo[x] = val;`
- You can also use `Arrays.fill(arr, -1)` to fill array with default value
- Memo formula only works (correctly?) when your method is actually returning the answer of final problem
- `System.gc()`: Beats `30% to 99.99%` in memory but Beats `100% to 9%` in runtime

## [1823. Find the Winner of the Circular Game](https://leetcode.com/problems/find-the-winner-of-the-circular-game/description/)

- ==Revise==
- If you have to create circular array or any DS, simply mod the index with the size.
  - TIPs:
    - Solve problem as if the index value automatically moved to the beginning
    - Once solved, write the solution using same concept. Don't care about IndexOutOfBound
    - Then replace every index with index % size. Size must be the size of array/list at that point
    - Don't confuse logic with modding. Simple mod the index **after** you find the solution. Otherwise it will get complicated
  - Also recall Division Lemma, n % k is always **[0, k) or [0, k-1]**
    - For 0 based indexing mod with size
    - For 1 based indexing mod with size and add 1
    - For 2 based indexing mod with size and add 2
- DOUBT: Think again if you really understand the recursive solution without using space
  - The key is that you are assume that you know ans for f(n-1,k) but then you need to map the index of that circle to current circles index.
  - In current circle k+1th person was holding gun and the circle you got answer has 1th person holding gun

## [198. House Robber](https://leetcode.com/problems/house-robber/)

- Great problem to learn bottom-up approach

## [213. House Robber II](https://leetcode.com/problems/house-robber-ii/)

- ==Revise==
- Again, great problem for revising bottom-up approach
- I still have hard time understanding its logic

## [50. Pow(x, n)](https://leetcode.com/problems/powx-n/description/)

- If `n is Integer.MIN_VALUE` then `-n` will also be `Integer.MIN_VALUE`. Handle this case.
- _O(n)_ is easy but _O(log2 n)_ is a little bit tricky.
  - HINT: Square **x** and half **n**

## [876. Middle of the Linked List](https://leetcode.com/problems/middle-of-the-linked-list/description/)

- Following code returns middle node
- `1 -> 2 -> 3 -> 4 -> 5` **3** is returned
- `1 -> 2 -> 3 -> 4` **3** is returned

```java
    ListNode slow = head;
    ListNode fast = head;
    while(fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    return slow;
```

## [143. Reorder List](https://leetcode.com/problems/reorder-list/description/)

- Find middle but be sure to create first list bigger
- Break link and reverse the second half
- Merge the first and 2nd half to get answer

## [796. Rotate String](https://leetcode.com/problems/rotate-string/description/)

- There is a one-liner solution.
- Use indexOf, concat and contains only

## [55. Jump Game](https://leetcode.com/problems/jump-game/description/)

- Be greedy ;)
- T: O(n)
- S: O(1)

## [45. Jump Game II](https://leetcode.com/problems/jump-game-ii/description/)

- `REVISE`
- You can make sort of trays
- e.g: [2, 3, 1, 1, 4]
- Tray one is 0. Tray two is 1 to 2. Tray three is 3 to 4.
- From each tray you decide the next tray by seeing what is the longest jump you can make

## [53. Maximum Subarray](https://leetcode.com/problems/maximum-subarray/description/)

- You need include left elements only up until the sum is > 0

## [46. Permutations](https://leetcode.com/problems/permutations/description/)

- `Arrays.stream(ints).boxed().toList()` is too slow. I created a simple method that coverts integer array list. Guess what without my method, beats 6% and with my method, beats 98.5%
- Moral of the story **ALWAYS CONVERT INTEGER ARRAY TO ARRAYLIST BY CUSTOM METHOD**

- e.g: abcd
- There are two ways of understanding the optimized algorithm. In both understandings, we are doing every operation on original array
  1. `a` has four options. So we first swap it with `a` then `b` then `c` and then `d`. [abcd, bacd, cbad, dbca]
     - Again we start with abcd and say that `b` has 3 options (because every element has been swapped with `a` so that option is eleminated). We first swap it with `b` then `c` and then `d`. [abcd, acbd, adcb]
     - Now we go to the 3rd element i.e `c` and repeat the process
  2. At first place we can place either `a` or `b` or `c` or `d`. So we do that by swapping every element with `a`.

## [78. Subsets](https://leetcode.com/problems/subsets/description/)

- `LinkedList` is actually `DoublyLinkedList` in java
- `new List(listToCopy)` is linear time (as we are copying all elements)

## [79. Word Search](https://leetcode.com/problems/word-search/description)

- `anyCollection.clear()` method will remove all elements from that collection
- Ordered pair `(x,y)` is equivalent to `x + " " + y`
- HINT: You can mark visited cells by `*`
- 5% to 90% by just moving from cell to next cell from recursion to for loop (see first accepted submission and 2nd accepted submission)

## [1161. Maximum Level Sum of a Binary Tree](https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/description/)

- `bfs` used
- Now i know two ways of know current level _(next question will give you another method)_. `Null in queue` and `nested while loop`. The 2nd one is faster and less error prone

```java
// BFS via null
public void bfs(TreeNode tree) {
  Queue<TreeNode> nodes = new LinkedList<>();
  nodes.add(tree);
  nodes.add(null);
  int level = 0;
  while(!nodes.isEmpty()) {
    level++;
    TreeNode node = nodes.remove();
    if(node == null) {
      if(!nodes.isEmpty()) node.add(null);
      continue;
    }
    if(node.left != null) nodes.add(node.left);
    if(node.right != null) nodes.add(node.right);
    System.out.println("LEVEL: " + level + " NODE: " + node.val);
  }
}

// BFS via nested while loop
public void bfs(TreeNode tree) {
  Queue<TreeNode> nodes = new LinkedList<>();
  nodes.add(tree);
  int level = 0;
  while(!nodes.isEmpty()) {
    level++;
    int size = nodes.size();
    while(size > 0) {
      size--;
      TreeNode node = nodes.remove();
      System.out.println("LEVEL: " + level + " NODE: " + node.val);
      if(node.left != null) nodes.add(node.left);
      if(node.right != null) nodes.add(node.right);
    }
  }
}
```

## [102. Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/description)

- New way of traversing binary tree in level order (bfs). No queue used, only recursion.

```java
List<List<Integer>> ans;
public void solve(TreeNode root, int level) {
    if(root == null) return;
    if(ans.size() <= level) ans.add(new ArrayList<Integer>());
    solve(root.left, level + 1);
    ans.get(level).add(root.val);
    solve(root.right, level + 1);
}
```

## [797. All Paths From Source to Target](https://leetcode.com/problems/all-paths-from-source-to-target)

- Use dfs
- Beats 26% when using **LinkedList** for path and beats 96% when using **ArrayList**. It is f\*\*kin' ridiculous
- (tested in ide) Even if you add 10^7 elements and remove them using `ArrayList.remove(size()-1)` and `LinkedList.removeLast()`, still ArrayList is about 4 times faster.

## [994. Rotting Oranges](https://leetcode.com/problems/rotting-oranges/)

- Actual common uses **multi-source bfs** and recall that bfs requires _Queue_ data structure
- My first solution simulated everything without using extra space. The interesting part that according to leetcode both beat 87% which is strange because I guess my solution was going reiterating over same element a lot of times

## [200. Number of Islands](https://leetcode.com/problems/number-of-islands)

- Easy but neat thing is that you can solve it using both bfs and dfs

## [72. Edit Distance](https://leetcode.com/problems/edit-distance)

- Solution 1: It is self explanatory. We check every possible combination and pick out the one that takes min operations. Notice if two letters are same then we are skipping that.

```java
class Solution {
    public int minDistance(String word1, String word2) {
        return solve(word1, word2, 0, 0);
    }

    public int solve(String word1, String word2, int i1, int i2) {
        if(i1 == word1.length())
            return word2.length() - i2;
        if(i2 == word2.length())
            return word1.length() - i1;
        if(word1.charAt(i1) == word2.charAt(i2))
            return solve(word1, word2, i1 + 1, i2 + 1);
        return 1 + min(
            solve(word1, word2, i1, i2 + 1), // insert
            solve(word1, word2, i1 + 1, i2), // delete
            solve(word1, word2, i1 + 1, i2 + 1) // replace
        );
    }

    public int min(int... nums) {
        return Math.min(nums[0], Math.min(nums[1], nums[2]));
    }
}
```

- Solution 2: Improvement can be done by using DP i.e saving the solution of subproblems for future use. Notice "key" of map should be that value which makes that method call unique. Here word1 and word2 are shared by all method calls but i1 and i2 make each call unique

```java
class Solution {
    public int minDistance(String word1, String word2) {
        return solve(word1, word2, 0, 0);
    }
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    public int solve(String word1, String word2, int i1, int i2) {
        String key = STR."\{i1}, \{i2}";
        Integer res = null;
        if((res = map.get(key)) != null)
            return res;
        else if(i1 == word1.length())
            res = word2.length() - i2;
        else if(i2 == word2.length())
            res = word1.length() - i1;
        else if(word1.charAt(i1) == word2.charAt(i2))
            res = solve(word1, word2, i1 + 1, i2 + 1);
        else
            res =  1 + min(
                solve(word1, word2, i1, i2 + 1), // insert
                solve(word1, word2, i1 + 1, i2), // delete
                solve(word1, word2, i1 + 1, i2 + 1) // replace
            );
        map.put(key, res);
        return res;
    }

    public int min(int... nums) {
        return Math.min(nums[0], Math.min(nums[1], nums[2]));
    }
}
```

- Solution 3: Observe that i1 in HashMap is from [0, word1.length()] and i2 is [0, word2.length()] thus we can use a simple 2D array to hold the values

```java
class Solution {
    public int minDistance(String word1, String word2) {
        this.map = new int[word1.length()+1][word2.length()+1];
        for(int i = 0; i < map.length; i++)
            for(int j = 0; j < map[i].length; j++)
                map[i][j] = -1;
        return solve(word1, word2, 0, 0);
    }
    int[][] map;
    public int solve(String word1, String word2, int i1, int i2) {
        int res = map[i1][i2];
        if(res != -1)
            return res;
        else if(i1 == word1.length())
            res = word2.length() - i2;
        else if(i2 == word2.length())
            res = word1.length() - i1;
        else if(word1.charAt(i1) == word2.charAt(i2))
            res = solve(word1, word2, i1 + 1, i2 + 1);
        else
            res =  1 + min(
                solve(word1, word2, i1, i2 + 1), // insert
                solve(word1, word2, i1 + 1, i2), // delete
                solve(word1, word2, i1 + 1, i2 + 1) // replace
            );
        map[i1][i2] = res;
        return res;
    }

    public int min(int... nums) {
        return Math.min(nums[0], Math.min(nums[1], nums[2]));
    }
}
```

- Solution 4: So far we have use top-down recursive approach. Now we will be using bottom-up iterative approach. Time Complexity and space complexity (assuming you ignore the recursive calls where we have already memoized solutions) of both will be same but we will be saving overhead of method calls.

```java
class Solution {
  public int minDistance(String word1, String word2) {
      int m = word1.length(), n = word2.length();
      int[][] dp = new int[m + 1][n + 1];
      for(int r = 0; r < m + 1; r++)
          dp[r][0] = r;
      for(int c = 0; c < n + 1; c++)
          dp[0][c] = c;
      for(int r = 1; r < m + 1; r++) {
          for(int c = 1; c < n + 1; c++) {
              if(word1.charAt(r-1) == word2.charAt(c-1))
                  dp[r][c] = dp[r-1][c-1];
              else
                  dp[r][c] = 1 + min(
                                      dp[r-1][c-1],
                                      dp[r-1][c],
                                      dp[r][c-1]
                                  );
          }
      }
      return dp[m][n];
  }

  public int min(int... nums) {
      return Math.min(nums[0], Math.min(nums[1], nums[2]));
  }
}
```
