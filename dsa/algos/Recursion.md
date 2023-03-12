# Recursion

## Structure of recursive function

Three questions:

1. **What is base case?**
   What will you pass the function so that it returns immediately? That should be your base case

1. **What is smallest amount of work u can do in the problem?**
1. **With every function call, make sure to shrink the input**

## Misc

- Keep track of stack frames in stack
- _Stackoverflow excep_: you exceed preallocated memory of stack

## Divide & Conquer

3 Steps:

1. Divide the problem into smaller subproblems
1. Conquer the subproblems by solving them recursively
   **Solve small enough problems by bruteforce**
1. Combine bruteforce solutions to get solutions of subproblems and then finally solution to orignal problem

> e.g: Merge Sort
