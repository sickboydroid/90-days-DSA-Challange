# Misc

1. `sys.getsizeof(...)` will give the space occupied by the data type in **bytes**
2. Since -0 = 0, negative indexing starts with -1.
   - In nutshell, if **i** is a negative number then it replaced by `len(seq) + i` when you use it for indexing
3. Slice of **seq** from **i** to **j** never includes **j**. If **j >= len(seq)** then **len(seq)** is used instead of **j**
4. Concatenating immutable sequences is very expensive:
   1. You can **list** as substitution for **StringBuilder** and use **str.join()** at the end to get string from list
   2. Instead of concatenating **tuples**, use **list** instead extend it
5. Accessing and slicing in both strings and lists is of same time complexity.
