# Python Data types

- [Python Data types](#python-data-types)
  - [Misc](#misc)
  - [Lists](#lists)
  - [Tuple](#tuple)
  - [Dictionary](#dictionary)
  - [Set](#set)
  - [Frozen Set](#frozen-set)
  - [Strings](#strings)

## Misc

1. `sys.getsizeof(...)` will give the space occupied by the data type in **bytes**
2. `timeit.timeit(stmt="<stmt>", number=<num>)` will repeat/run *stmt* *num* number of times and give the time taken by it to execute

## Lists

- *Ordered*, *Mutable*, allows *duplicates*
- `len(list)` to get size
- `list.insert(index, item)` to insert an item the index
- `list.pop()` & `list.append(item)` to pop and add item at the end of list
- `list.remote(item)` remove the item from list
- `list.clear()` remove all items
- `list.sort()` sorts list in-place and `sorted(list)` will give you a new sorted list
- `list.count(item)` will give the number of times item is present in list
- `list.copy()` or `list(list)` can be used to create copy of list
- `list = [item1, item2] * 5` will give a list of 10 elements with item1 and item2
- `list = list1 + list2` will merge the two lists
- **Slicing `list[start:end:step]`**:
  - *start* index is by default 0
  - *end* index is by default len(list) - 1
  - *step* is by default 1. It can be negative. In fact `list[::-1]` can used to reverse the list
  - `list[:]` can be used to copy the list
- **List comprehension**:
  - [Official doc](https://docs.python.org/3/tutorial/datastructures.html#list-comprehensions)
  - `<exp> for item in list if condition` You can have multiple *if* and *for* clauses
  - `<exp>` can another list comprehension

```python
nums = [0,1,2,3,4,5,6]

# replace
nums[2:5] = ['a', 'b', 'c', 'd']

# delete
nums[2:5] = []
```

```python
############ List Comprehension ############
############################################
# EXAMPLE 1
############################################

num1 = range(10)
num2 = range(10, 20)
num3 = range(20, 30)
coordinates = [(x,y,z) for x in num1 if x % 2 == 0 for y in num2 if y % 3 == 0 for z in num3 if z % 5 == 0]
# equivalent to
coordinates = []
for x in num1:
    if x % 2 == 0:
        for y in num2:
            if y % 3 == 0:
                for z in num3:
                    if z % 5 == 0:
                        coordinates.append((x,y,z))
############################################
# EXAMPLE 2
############################################
# Produces same output as above. However it is way too slow, can you say why?
coordinates = [(x,y,z) for x in num1 for y in num2 for z in num3 if x % 2 == 0 if y % 3 == 0 if z % 5 == 0]
# equivalent to
coordinates = []
for x in num1:
    for y in num2:
        for z in num3:
            if x % 2 == 0 and y % 3 == 0 and z % 5 == 0:
                coordinates.append((x,y,z))
############################################
# EXAMPLE 3
############################################
matrix = [
    [1, 2, 3, 4],
    [5, 6, 7, 8],
    [9, 10, 11, 12],
]
# will transpose matrix that is perform cols -> rows
transposed = [[row[i] for row in matrix] for i in range(len(matrix))]
# is equivalent to
transposed = []
for i in range(len(matrix)):
    # 3 lines implement exp of outer list comprehension
    trans_row = []
    for row in matrix:
        trans_row.append(row[i])
    transposed.append(trans_row)
```

## Tuple

- *Ordered*, *Immutable*, allows *duplicates*
- **Creating tuple:**

```python
nums = 1,
nums = tuple([1])
nums = 1, 2, 3, 4
sqrs = (i*i for i in nums) # inefficient: creates list and then converts that to tuple

nums = (1, 2, 3, 4) # packing
a, b, c, d = nums # unpacking
a, *b, c = nums # unpacking
```

## Dictionary

- *Unordered*, *Mutable*, *Key-value pairs*
- If you are using **integers** as *keys* then `dict[num]` will act as if num is *key* rather than index
- *Keys* must be immutable (i.e to be hashable). Thus *list* can't be used as a key
- **Creating Dict and operating on elements:**

```python
myDict = {"name": "sickboy", "addr": "india", "age": 17}
myDict = dict(name="sickboy", addr="india", age=17) # DON'T NAME IT MYDICT!!

myDict["school"]= "HSS Kabamarg" # add another key-value pair

myDict.popitem() # removes last inserted item, in this case school
del myDict["name"]
myDict.pop("addr")

# Checking if key exists
if "name" in myDict:
    print(myDict["name"])

# accessing keys and values
for key in myDict:
    pass
for key in myDict.keys():
    pass
for value in myDict.values():
    pass

# merging two dictionaries
dict1.update(dict2) # all elements of dict2 + elements which are unique to dict1
```

## Set

- *Unordered*, *Mutable*, does not allow *duplicates*
- `set.add(item)` to add an item
- `set.remove(item)` and `set.discard()` remove an item. Former throws *KeyError* if the item sdoes not exist
- `set.clear()` removes all elements
- `set.pop()` removes and returns a random element
- `set1.union(set2)`, `set1.intersection(set2)`, `set1.difference(set2)` and `set1.symmetric_difference(set2)` will perform operation and return a new set
- `set1.update(set2)`, `set1.intersection_update(set2)` and so on will perform the operation inplace on *set1*
- `set1.issubset(set2)`, `set1.issuperset(set2)` and `set1.isdisjoint(set2)` are some other operations

## Frozen Set

- *Unordered*, *Immutable*, does not allow *duplicates*
- It is a set which is immutable

## Strings

- *substrings* and *characters* are accessed by treating strings as lists i.e *slicing* works the same way
- `str.strip()` removes trailing white spaces
- `str.upper()`, `str.lower()`, `str.startswith(str2)`, `str.endswith(str2)`, `str.count(str2)`, `str.replace(str2, replacement)`, 
- `str.find(str2)` will return the starting index of str2 if str2 is substring of str. Otherwise -1
- `str.split(sep)` will return a list. By default sep is *\s*
- `'_'.join(list)` will create a string from list with *_* separating its elements
- Strings are immutable so use
- **Formatting:**
  - See [official doc examples](https://docs.python.org/3/library/string.html#format-examples)
  - Default *fill* for strings is ' '
  - There are three methods of formatting string:
    1. Using `%`
    2. Using `.format()`
    3. Using `f-strings`
- **Docstrings:**
  - Generally used for documentation
- **Colorful text:**
  - *ANSI* characters are used for colors
  - To start formatting `\033[<num>m` where num can be an integer from 1 to 107 (depends on terminal). To end formatting, use `\033[0m`.

```python
# Standard format specifier
<pre>
format_spec     ::=  [[fill]align][sign]["z"]["#"]["0"][width][grouping_option]["." precision][type]
fill            ::=  <any character>
align           ::=  "<" | ">" | "=" | "^"
sign            ::=  "+" | "-" | " "
width           ::=  digit+
grouping_option ::=  "_" | ","
precision       ::=  digit+
type            ::=  "b" | "c" | "d" | "e" | "E" | "f" | "F" | "g" | "G" | "n" | "o" | "s" | "x" | "X" | "%"
</pre>
```

```python
############
# Docstrings
############
myStr = """Hello! \
My name is sickboy.
And I am learning python.
"""
# equivalent to
myStr = 'Hello, World! My name is sickboy.\nAnd I am learning python.'

############
# Formatting
############
age = 17
name = 'sickboy'
score = [95, 96, 100]

# overview of all three types
details = f'My name is {name} and I am {age}. I have scored {score} in my test'
details = 'My name is {} and I am {}. I have scored {} in my test'.format(age, name, score)
details = 'My name is %s and I am %d. I have scored ... in my test' % (name, age)

# working with dicts
info = {'name': 'sickboy', 'score': [100, 95], 'age': 17}
details = "Hello, my name is {name} and I am {age}".format(**info)
details = "Hello, my name is {0[name]}. I have scored {0[score][1]} in CS".format(info)

# some more formatting
formatted_price = '"${0:*<10,.2f}"'.format(1324.12345) # formatted_price = "$1,324.12**"

# nested formatting
nested_formatting = '{text:{fill}{align}11}'.format(text='sickboy', fill='^', align='^') # "^^sickboy^^"
# above is equivalent to
nested_formatting = '{:^^11}'.format('sickboy')

# base conversion
n = 42
print(f'{n:b} {n:#b} {n:o} {n:d} {n:#x}') # output: 101010 0b101010 52 42 0x2a
```