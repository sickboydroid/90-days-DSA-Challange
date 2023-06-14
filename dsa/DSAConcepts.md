# DSA Concepts

- [DSA Concepts](#dsa-concepts)
  - [Time complexity](#time-complexity)
  - [Taking intersection of two ranges](#taking-intersection-of-two-ranges)
  - [DFS and BFS](#dfs-and-bfs)
    - [BFS](#bfs)
    - [DFS](#dfs)
  - [Backtracking](#backtracking)
  - [Recursion](#recursion)
    - [Structure of recursive function](#structure-of-recursive-function)
    - [Divide \& Conquer](#divide--conquer)
  - [DP](#dp)
    - [Memoization](#memoization)
    - [Bottom-up approach](#bottom-up-approach)
    - [Tabulation](#tabulation)
  - [Greedy Algorithms](#greedy-algorithms)

## Time complexity

- Usually denoted by O(n) where the n = input size
- Before calculating time complexity be sure to ask the case
  - Worst case (mostly)
  - Best case
  - Average case (real world case)
- [Big O Cheatsheet](https://www.bigocheatsheet.com/)
- For each line ask, how will the runtime increase as input approaches infinity.
- If by doubling size of input the runtime increases by 1 then the time complextiy is `O(log2 n)` or simply `O(log n)`.

> e.g: O(n) means that some number of computations are performed on each element of input

- In list of python:
  - len => O(1)
  - append => O(1)
  - get slice => O(k)
  - set slice => O(k+n)

> Because n is used to represent number of elements in input array, k is used to represent the
> number of elements to be sliced here.

## Taking intersection of two ranges

```java
int[] range1 = {10, 30};
int[] range2 = {15, 20};
int[] intersection = new int[2];
intersection = Math.max(range1[0], range2[0]);
intersection = Math.min(range1[1], range2[1]);
```

## DFS and BFS

### BFS

1. You can start from any vertex
2. While exploring vertex, you can visit adjacent vertex in any order
3. `RULE1`: While exploring vertex, visit all its adjacent vertices then only you can move to next vertex for exploration
4. `RULE2`: Next vertex for exploration should be chosen from **Queue**

### DFS

1. You can start from any vertex
2. **Stack** is used

> Add current vertex to stack only when you are about to start the exploration of another vertex i.e when you are about to suspend current vertex for later

## Backtracking

1. Def: Find all possible solutions and use the one you want
2. Tree visualization helps in solving backtracking problems
   1. Continue one branch of tree
   2. Once you reach end i.e you find your answer , backtrack to solve previous branch
      1. You backtrack whenever your method returns. So whenever you define a base condition in recursion, you are actually backtracking

## Recursion

1. Tradeoff: Calling method is slow than looping
2. **Call Stack:** IT IS A *STACK*. Don't forget that

### Structure of recursive function

- Three Questions:
  1. **What is base case?**
      - What will you pass the function so that it returns immediately? That should be your base case
      - It should be such that you don't even do a single piece of work.
        - e.g empty string, 0 length array
  2. **What is smallest amount of work u can do in the problem?**
  3. **With every function call, make sure to shrink the input**
- Five Questions (Based on finding sum of first *n* natural numbers):
  1. Find base case
  2. Play around around examples and visualize
  3. Relate hard cases with simpler cases
     - If you have to sum first 5 natural numbers. Ask if i was given sum of 4 numbers can i find sum of 5 numbers?
  4. Generalize the pattern.
     - If sum of first n-1 is given, can i find sum of n numbers?
  5. Code the solution

> **Recursive leap of faith**, assume that the answer of easier/smaller problem's answer will be correct. For example assume that sum(n-1) is correct and then work your way out

### Divide & Conquer

3 Steps:

1. Divide the problem into smaller subproblems.
1. Conquer the subproblems by solving them recursively
   **Solve small enough problems by bruteforce**
1. Combine bruteforce solutions to get solutions of subproblems and then finally solution to orignal problem

> e.g: Merge Sort

## DP

Dynamic programming is a technique for solving problems.

In any dynamic programming problem, what's important is that our problem must be **breakable** into smaller subproblems and also, these subproblems show some sort of **overlap** which we can save upon by **caching** or **memoization**.
Three steps:

   1. **Recursion:** Solve with recursion
   2. **Memoization:** Store values if too much recursion
   3. **Bottom-up:** Remove recursion

DP -> Recursion + Memo + Guessing

> TIP: First make a working solution using **pure recursion** then make it efficient using **memoization**

### Memoization

- Conversion of any **recursive function** into **memoized recursive function**
  - Check if args in memo. Return if present
  - Save return value
- MISTAKE: Don't check cache for the recursive calls you are gonna make in case your functions args were not cached.
  - They will handle themselves as well

```java
// recursive function
function(a, b, c) {
    ...
    return ans
}
```

```java
// memoized recursive function
memo = {}
function(a, b, c) {
    if((a,b,c) in map)
        return memo[a,b,c]
    ...
    memo[a,b,c] = ans
    return ans
}
```

> time = sub-problems * (time/subproblem)
> Don't count memoized recursive calls

- Memoization recipe:
    1. Make it work
       1. Visualize problem as a tree (break large problem into smaller ones)
          - Tree nodes (circles) represent input or the input that is changing over time  and line may be output
       2. Implement tree into recursive code
          - Leaf nodes are base cases
       3. Test it (it can be slow but should be working)
    2. Make it efficient
       1. Add a memo object
          - **Keys** represent arguments of function
          - **Values** represent return values of function
       2. Add a base case for memo object
       3. Implement memo storing logic i.e store return value

- Calculating time complexity and space complexity of recursive solution (without memo)
  1. For **Binary Tree**:
     - Time complexity: **O(2^h^</sup>)** where **h** is its height or number of levels
     - Space Complexity: **O(h)**. It is the space for stack of method calls
  2. For other, here are some tips:
        - Height: What is the maximum number of 
        - steps/levels before reaching to base case
          - Pick on branch and follow it to the end

### Bottom-up approach

Same computation as memoization but no recursion -> no stackoverflow exception

```java
// recursive function
public static int fib(int n) {
    if (n <= 1) return n;
    return fib(n-1) + fib(n-2);
}

```

```java
// bottom-up approach
public static int fib(int n) {
    if (n <= 1) return n;
    int[] dp = new int[n+1];
    dp[0] = 0;
    dp[1] = 1;
    for (int i = 2; i <= n; i++) {
        dp[i] = dp[i-1] + dp[i-2];
    }
    return dp[n];
}
```

### Tabulation

- Can you visualize the tabulation as a binary tree for fib?
- Initialize the base case in the table.
- Sure, here are some dynamic programming problems that are of moderate difficulty and can be solved using a tabulated approach:

1. Climbing Stairs: https://leetcode.com/problems/climbing-stairs/
1. Unique Paths: https://leetcode.com/problems/unique-paths/
1. Minimum Path Sum: https://leetcode.com/problems/minimum-path-sum/
1. Longest Increasing Subsequence: https://leetcode.com/problems/longest-increasing-subsequence/
1. Decode Ways: https://leetcode.com/problems/decode-ways/
1. Coin Change: https://leetcode.com/problems/coin-change/
1. Maximum Subarray: https://leetcode.com/problems/maximum-subarray/
1. Palindromic Substrings: https://leetcode.com/problems/palindromic-substrings/

## Greedy Algorithms

- A greedy algorithm makes the best available choice at each step, without considering the future implications of that choice. It aims to achieve a globally optimal solution by locally optimizing at each step.
- 
