# Leetcode Log

- Link to problem
- Problems you faced while solving
- No code (except when it is not on leetcode or is very important)
- New things you learnt
- Time and space complexity

Tips:

1. Whenever creating if-else or for-while, start with `{}` (typing++)

## Contents

- [Leetcode Log](#leetcode-log)
  - [Contents](#contents)
  - [Worthy mentions (not from leetcode)](#worthy-mentions-not-from-leetcode)
    - [Single non-repeating element in array](#single-non-repeating-element-in-array)
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

Given an array with every element repeating exactly three times except one.  Find that non-repeating element.

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
  - row = 3 * (rowCell/3)
  - col = 3 * (colCell/3)
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