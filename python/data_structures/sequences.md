# Sequence Types

- lists tuples and range objects are all sequences
- Operations implemented by both **mutable and immutable sequences:**
  - `in`, `not in`, `+`, `*`,`indexing`, `slicing`, `len(seq)`, `min(seq)`, `max(seq)` `index(x[, i, [, j]])` and `count(x)`
    - **seq \* n** equivalent to adding seq to itself n times
      - Items are not copied but referenced multiple times (see first example)
    - **seq.index(x, i=0, j=len(s)):** index of first occurrence of **x** in sequence seq after **i** and before **j**
- Operations supported by only **immutable sequences:**
  - `hash()`
    - Thus you can use them as dict keys
- Operations supported by only **mutable sequences:** (t is any iterable object)
  - `seq[i] = x` replaces **ith** item by x
  - `seq[i:j:k] = t` items are replaced
    - if **t**'s length does not match the slice length, other items will be inserted or deleted accordingly
    - In more general, above operation
  - `del seq[i:j:k]` same as `seq[i:j:k] = []`
  - `seq.append(x)` append element x to the end of seq
  - `seq.extend(t)` or `seq += t` extends items of t
  - `seq.insert(i,x)` or `seq[i:i] = x`, `seq.clear()`, `seq.copy()` or `seq[:]`, `seq.pop([i])`, `s.remove(x)`, `s.reverse()` (in-place) are some other operations
- Sequence of same types support comparisons. Tuples and lists are compared lexicographically.

```python
# Example 1
lists = [[1,2]]
lists = lists * 3 # gives [[1,2], [1,2], [1,2]]
lists[0].append(3) # gives [[1,2,3], [1,2,3], [1,2,3]]
```
