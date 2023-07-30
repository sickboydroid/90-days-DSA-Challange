# Python Misc

- [Python Misc](#python-misc)
  - [Misc](#misc)
  - [Lambda function](#lambda-function)
  - [True v/s False](#true-vs-false)
  - [Python interpreter](#python-interpreter)
  - [in-built functions (useful ones)](#in-built-functions-useful-ones)
  - [Glossary](#glossary)
  - [Structural Pattern Matching (match-case statement)](#structural-pattern-matching-match-case-statement)
  - [Function Parameters](#function-parameters)

## Misc

## Lambda function

- `lambda arg: exp` will create a function with no name and which takes an argument _arg_ and evalutes and returns value of _exp_

## True v/s False

- **None**, **False**, empty **collections**, empty **sequences** and **0** of any numeric type -> FALSE
- **object** is considered true unless `__bool__()` returns _False_ or `__len__()` returns _0_

## Python interpreter

- `python -c command`
- `python -m module`
- `python -i script` will first run script and then enter interactive mode

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

## Structural Pattern Matching (match-case statement)

- [Visit the official doc](https://peps.python.org/pep-0636/)
- Pattern does two things:
  - Verify subject has certain structure, called matching
  - bind some names in patternt to componnent of elements
  - e.g `case [1, bar, 'foo']` will match the subject with structure of 3 elements (tuple or list maybe) and with first entry 1, 2nd entry anything and 3rd entry 'foo'. It will also bind bar to the 2nd entry of subject
- **Pattern** can be of two types:
  - **Capture Patterns:** stand-alone names like bar in above example
  - **Literal Patterns:** string litertals, number literals, true, false and none
  - **Wildcard Pattern:** `_`
- While writing **or patterns** it is mandetory that all alternatives should bind the same vars. Thus `[1, x] | [2, x]` is valid but `[1, x] | [2, y]` is invalid
- There is also **as pattern** which matches whatever is to its left but also binds the name (see example)
- Cases can have conditions called **guards**. Guards are part of case and not pattern
-

```python

match sub:
  case pattern:
    pass
  case ('drop', *objects):
    # will match sequences with first element 'drop'. Rest of things will
    # be stored in objects
    pass
  case ('kiss', (male, female), _, *viewers):
    # sequence of at least three elements with sub[0] as 'kiss', sub[1]
    # as another sequence of 2 elements and sub[2] can be anything.
    # Also male = sub[1][0] and female = sub[1][1] and viewers = sub[3:]
    pass
  case ('go', ('north' | 'south' | 'east' | 'west') as direction):
    # as pattern
    print(f'Going towards {direction}')
  case ('go', direction) if direction in 'news':
    # case with a guard. Notice direction will be already bound if pattern
    # matches and condition fails. If any next case matches, you will still
    # have direction variable
    pass
  case {"text": message, **rest}:
    # matches a dictionary with text key and will bind message to its value.
    # Rest of dictionary will be in rest name
  case {"text": message}:
    # Same as above except other keys will be ignored
  case {"text": str() as message}:
    # Same as above except message must be subclass of str with any attributes
  case Click(position=(x,y)):
    # If sub is a subclass of Click and has attribute position with two elements
    # x = sub.position[0] and y = sub.position[1]
  case Click((x,y)):
    # It will work only if your class defines specific position for
    # attributes by setting __match_args__ special attribute in class
    # See doc for more info
  case _:
    # always matches but does not bind any vars.
    # It will match any object and not just sequences
    pass
```

## Function Parameters

- Iterables can deliver positional arguments via `*iter` and dicts can deliver keyword arguments via `**name`
- Non `**kwargs` should always be after positional arguments while defining functions as well as while calling them
- Default value is evaluated only once while reading the definition of function (can cause unwanted behaviour with mutable objects)
- **Order**:
  - `*args`: will receive all positional arguments except the ones that are defined before it
  - `**kwargs`: will contain all keyword arguments except the ones that are defined before it
  - `**kwargs` must be before `*args`
  - Only keyword-only arguments can be after `**args`
- By default arguments are of type 'positional or keyword' and can be passed either by position or by explicitly by keyword
- You can change this behaviour using as follows:
  <pre>
  def f(pos1, pos2, /, pos_or_kwd, *, kwd1, kwd2):
        -----------    ----------     ----------
          |             |                  |
          |        Positional or keyword   |
          |             (default)          - Keyword only
          -- Positional only
  </pre>
- For an API, it is suggested to use positional-only arguments
- Arguments that are of type 'positional or keyword' cannot be passed to `**kwargs` i.e only the names of positional-only parameters can be used in `**kwargs` without ambiguity (see example below)

```python
# EXAMPLE: Only positional-only args can be in **kwds
def foo(name, **kwds):
  return 'name' in kwds # always false


def bar(name, /, **kwds):
  return 'name' in kwds # may or may not be true


foo(name=12, **{'name':21}) # error: name was assigned multiple times
foo(12, **{'name':21}) # error: //
bar(name=13, **{'name':31}) # error: name is poitional-only argument
bar(13, **{'name': 31}) # True
```
