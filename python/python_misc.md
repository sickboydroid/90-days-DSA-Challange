# Python Misc

- [Python Misc](#python-misc)
  - [Misc](#misc)
    - [Lambda function](#lambda-function)
    - [True v/s False](#true-vs-false)
  - [in-built functions (useful ones)](#in-built-functions-useful-ones)

## Misc

### Lambda function

- `lambda arg: exp` will create a function with no name and which takes an argument _arg_ and evalutes and returns value of _exp_

### True v/s False

- **None**, **False**, empty **collections**, empty **sequences** and **0** of any numeric type -> FALSE
- **object** is considered true unless `__bool__()` returns _False_ or `__len__()` returns _0_

## in-built functions (useful ones)

- `all(iterable)` will return True if all elements are True
- `any(iterable)` will return True if at least one element is True
- `repr(object)` calls `object.__repr__()` which is similar to `object.toString()` in java
- `bin(x)` If x is not an interger then it should implement `__index(self)__` that returns an integer
- `bytearray(source, encoding)`. If _source_ is string _encoding_ is necessary
- `bytes(source, encoding)` same as above excep byte sequence is immutable
- `callable(object)` checks whether object can be called or not.
  - classes are callable (calling a class creates a new instance)
  - instances are callable if their class implements `__call__()` method
- `chr(i)` return string representation of integer i
- `ord(c)` return integer representing string c. c should be a single character
- `sorted(iterable, key=callable, reverse=false )` will give you sorted list of iterable
  - `list.sort(key=callabel, reverse=false)` method sorts list inplace
  - Callable is called on every element of iterable exactly once before making comparsion. Then the output of this callable is used to compare the elements of original iterable
- `filter(func, iteratable)` will construct an iterator with elements for which func is true
