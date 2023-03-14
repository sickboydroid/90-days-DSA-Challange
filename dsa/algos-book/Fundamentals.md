# Fundamentals

- Order of operation (Prefer parenthesis):
  1. \*  / ( % ) have priority over + -
  2. **()** > **!** > **&&** > **||** (priority order)
  3. left to right except for `=` and `->`
- Primitive data types:
  - `long` is 64-bits. Range [-4B, 4B]
  - `int` is 32-bits. Range [-2B, 2B]
  - `short` is 16-bits. Range [-32K, 32K]
  - `char` is 16-bits. Arithmetic operations are valid
  - `byte` is 8 bits.
- Loops run until the condition becomes false
- You need to **declare**, **create** and **initialize array**. `int[] nums = new int[size]` is declaration and creation
- Default value of numeric types is **0** and **false** for boolean types
- `... = new int[M][N]` is 2d array with M rows and N columns by convention. M -> y and N -> x

---

- Functions and Methods:
  1. Args are passed by values
  2. Method overloading: Multiple methods with same name but diff args
  3. void methods are said to produce side effects i.e they change state of system (print, sort array, create file, etc)
     - Void methods do not represent mathematical function

---

- Recursion:
  - **Base Case:** Conditional statement as the first statement
  - **Converge:** Recursive calls should be sub-problems in some sense
  - **Disjoint sub-problems**: For example in binary search the recursive call must not search the portion which its caller already searched