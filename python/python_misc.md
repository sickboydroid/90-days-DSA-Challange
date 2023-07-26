# Python Misc

- [Python Misc](#python-misc)
  - [Misc](#misc)
    - [Lambda function](#lambda-function)
    - [True v/s False](#true-vs-false)
  - [in-built functions (useful ones)](#in-built-functions-useful-ones)
  - [Glossary](#glossary)

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
- `dir([object])`: Return list of names in current scope. With an argument, **attempts** to return a list of valid attributes for that object
- **Attributes**:
  - `hasattr(object, name)`: calls getattr(object, name) and sees if it raises an exception
  - `getattr(object, name, default)`: name must be a string. e.g `getattr(x, 'foo')` will return `x.foo`
  - `setattr(object, name, value)`: name must be a string. e.g `setattr(x, 'foo', 'bar')` is equivalent to `x.foo = 'bar'`
  - NOTE: Name need not to be a python identifier and if it is not then you can excess it only via setattr(...) and getattr(...) and not using dot notation. e.g Name which is not a python identifier is **foo+\nbarr** (there is literally a line break in name)

## Glossary

1. **file-like object** or **file object**: Object exposing file-oriented API (with methods such as `read()` and `write()`)
2. **path-like object**: `str/bytes` representing a path or an object implementing `os.PathLike` protocol
3. **namespace**: In nutshell, it is a dictionary in which keys are names of object and values are the actual objects. Types: Local, global, built-in and nested namespaces
   object
   Any data with state (attributes or value) and defined behavior (methods). Also the ultimate base class of any new-style class.
