# DSA Concepts

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

## Structure of recursive function

Three questions:

1. **What is base case?**
    - What will you pass the function so that it returns immediately? That should be your base case
    - It should be such that you don't even do a single piece of work.
      - e.g empty string, 0 length array
2. **What is smallest amount of work u can do in the problem?**
3. **With every function call, make sure to shrink the input**

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

Memoization recipe:
    1. Make it work
       1. Visualize problem as a tree (break large problem into smaller ones)
         - Tree nodes represent input and line may be output
         -   

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