# in-built data structures

## List

- Methods that modify a (mutable) data structure return nothing (design principle)
- `insert(i, x)`: will insert **x** before **i**
- `index(x, start=0, end=sys.maxsize)`: search x in the sub sequence `[start, end)`
- `list = [item1, item2] * 5` will give a list of 10 elements with item1
  and item2 repeated 5 times each. Note that only references of item1 and item2 will be copied, actual objects are still only 2

1. **Lists as Stacks**:

   - list's `append()` is equivalent to stack's `push()`
   - list's `pop()` is equivalent to stack's `pop()`
   - Both operations are fast

2. **Lists as Queues**:
   - Not preferred as pops/inserts from beginning of a list is slow
   - Use **collections.deque** for this purpose

## Tuple

- Just like lists except it is immutable

## Sets

- Support mathematical operations (as shown in example)
- Set comprehensions are also supported
- Very very fast lookup, on big data about 500x faster than lists
- `set1.update(set2)`, `set1.intersection_update(set2)` and so on will perform the operation in-place on _set1_
- `set1.issubset(set2)`, `set1.issuperset(set2)` and `set1.isdisjoint(set2)` are some other important methods

  ```python
  a, b = {1,2,3}, {3,4,5}
  a         # unique nums in a
  a - b     # nums in a but not in b       (difference)
  a | b     # nums in a or b or both       (union)
  a & b     # nums in both a and b         (intersection)
  a ^ b     # nums in a or b but not both  (symmetric difference)
  ```

## Dict

- **Ordered** (since 3.7...). Dict preserves the order in which you insert items
- dict comprehensions are also supported

```python
# creating dicts
myDict = dict(name="sickboy", addr="india", age=17) # DON'T NAME IT MYDICT!!

# dict comprehension
{x: x**2 for x in range(100)}

# dict can be generated from seq as well
dict([('name', 440), ('foo', 1024), ('bar', -1)])

# operations on dict. Priority is given to 'other' for example
# in the first operation the values of other take priority
# when d and other share keys.
d | other # new dict with merged keys and values
d |= other # almost same
```
