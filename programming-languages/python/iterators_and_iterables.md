# Iterators and Iterables

- [Iterators and Iterables](#iterators-and-iterables)
  - [Iterators and Iterables (def and properties)](#iterators-and-iterables-def-and-properties)
  - [Generator expressions and List comprehensions](#generator-expressions-and-list-comprehensions)
  - [Generators](#generators)
  - [Built-in functions for iterators](#built-in-functions-for-iterators)
  - [itertools module](#itertools-module)

## Iterators and Iterables (def and properties)

- see [iterators](https://docs.python.org/3/howto/functional.html#iterators)
- **Iterables v/s Iterables:**
  - **Iterables** are objects which implements magic method `__iter__()` method which returns an _iterator_
  - **Iterators** are objects with a `__next__()` method that keeps track of its position and retrieves the next value when called. If there are no more values, it raises a **StopIteration** exception
  - Every iterator is an iterable but not vise versa. It is because all iterators (have to) implement `__iter__()` method.
    - Although you can create an iterator without implementing `__iter__()`, it is not recommended. Not having `__iter__()` makes the iterator incompatible with for loops and many functions that expect iterables.
- **StopIteration** exception is what is used for determining if an iterator is exhausted
- For `for x in y` y must be an iterable. If y is an iterable and not iterator then `for x in y` and `for x in iter(y)` are same
- `in` and `not in` also take iterable as an input
- Sequence unpacking is also supported by iterators. If you know iterator will return N objects you can store it in an N sized tuple
- Iterators can be converted to `list` and `tuples`
- `iter(dict)` returns an iterator of keys
  - `dict.values()` and `dict.keys()` will give you iterator for values and keys respective
  - `dict(iterator)` dict constructor accepts an iterator that returns a tuple with first entry as key and 2nd as value
- Iterators are unidirectional and cannot be reset. Custom iterators may not support generating new iterators, while built-in types like list can create new iterators on each call to `iter(list)`.

```python
# EXAMPLE 1: Counter class is an iterator (and thus iterable)
class Counter:
    def __init__(self, end):
      self.end = end
      self.count = 0

    def __iter__(self):
        return self

    def __next__(self):
        self.count += 1
        if self.count >= end: # Can you guess why == is wrong here??
            raise StopIteration()
        return self.count

counter = Counter(6) # will count 1 to 5 (excluding 6)
print(next(counter)) # prints '1'
print(next(counter)) # prints '2'
counter = iter(counter) # no effect
print(next(counter)) # prints '3'

for i in counter: # or for i in iter(counter):
  print(i) # prints '4', '5'

```

## Generator expressions and List comprehensions

- Or **genexps** and **listcomps**
- Most common operations for iterators
  1. Perform some operation for every element: **genexps** is shorthand for that
  2. Create subset of elements that meet some condition: **listcomps** is shorthand for that
- _listcomps_ give back list. Fail with infinite iterators
- _genexps_ give back another iterator with lazy evaluation (i.e new values are calculated only when required). Can work with infinite iterators

```python
# Notice that function parenthesis act as parenthesis of genexps.
# Here list_all_objects() returns an iterator
sum(obj.count for obj in list_all_objects())
```

## Generators

- A function with a `yield` keyword is a generator function
- They can be thought of as resumable functions
- `return value` (or when end of generator is reached) causes `StopIteration(value)` exception
- **Sending Value to Generator:**
  - `(yield value)` (parenthesis optional but recommended) returns a value of type None if generator's `__next__()` was called. It will return **value** when generator's `iterator.send(value)` is called. Note that `send` and `next` both cause generator to move forward
- **How generator works:**
  - When you call generator function, no code is run
  - When you call `next` for the first time the function runs until it encounters a **yield statement**. It then pauses and saves the state of function returning the value. When you call the `next` again it evaluates the **yield statement** to `None`. In other words execution of generator function starts by evaluating yield statement and moving forward
  - `send` works exactly like `next` however the **yield statement** evaluates to the passed value instead of `None`
  - `throw(exception)` causes the yield statement to raise an exception
  - `close()` raises an **GeneratorExit** exception.
    - DO NOT CATCH EXCEPTION. Python will otherwise throw runtime error. You can use `try...finally` clause to free any resources you want

```python
# you can the max value even after creation of iterator
def var_counter(maximum):
  count = 1
  while count < maximum:
    new_max = (yield count)
    if new_max:
      maximum = new_max
      print("updated maximum to be {new_max}")

counter = var_counter(5)
print(next(counter)) # It will print '1', maximum = 5
print(counter.send(10)) # It will print 'updating maximum to be 10' and then '2', maximum = 10
print(next(counter)) # It will print '3'

```

## Built-in functions for iterators

- `map` and `filter` replicate the `genexps` and `listcomps` respectively
  - `map(f, iterA, iterB, ...)` returns an _iterator_ with elements as $f(iterA[0], iterB[0]), f(iterA[1], iterB[1]), ...$
  - `filter(predicate, iter)` returns an iterator with elements of `iter` for which `predicate` returns true value
- `enumerate(iter, start=0)` return an iterator which return tuple elements with first element as _count_ (from _start_) and 2nd element as _element_ from iter
- `any(iter)` and `all(iter)`: Former returns true when at least one element is a true value and the latter returns true only when all elements are true values
- `zip(iter1, iter2, ...)`: takes one element from each and returns a tuple. Number of returned tuples will be equal to the size of smallest passed iterable. Uses lazy evaluation.
  - Iterables are suggested to be of same length because of following reason

```python
# After picking 1 and 2, zip will pick 3 but there is no 3rd element in 2nd iterable thus it will drop it.
# Imagine if you had a iterator and you thought after zipping the possible elements you would store the remained elements somewhere
# But since 3 has been dropped, your iterator will only return 4. Thus you will lose an element
zip([1,2,3,4], ('a','b'))
```

## itertools module

- `count(start=0, step=1)`: start to infinity
- `cycle(iter)`: saves a copy of contents provided by iterable and then returns a new iterable that loops over those elements infinitely
- `chain(iterA, iterB, ...)`: returns an iterator which returns first all the elements of iterA then iterB and so on
- `islice(iter, stat=0, stop, step=1)`: Just like ordinary slicing but for iterables. You CANNOT provide negative start, stop or step
- `tee(iterable, n=2)` returns n independent iterators from a single iterable.
  - Once you create `tee`, the original iterable should not be used
  - It returns ITERATORS and not just their saved contents. Meaning that all computations are done from scratch for each element.
  - On the other hand, `cycle(...)` just saved the contents.
- `product(iter1, iter2)` will give you a cartesian product of iter1 and iter2
- `permutations(iter)` will give you all possible permutations
