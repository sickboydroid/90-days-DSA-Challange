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
