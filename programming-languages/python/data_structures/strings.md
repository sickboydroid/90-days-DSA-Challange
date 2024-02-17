# Strings

- `str.strip()` removes trailing white spaces
- `str.upper()`, `str.lower()`, `str.startswith(str2)`, `str.endswith(str2)`, `str.count(str2)`, `str.replace(str2, replacement)`,
- `str.find(str2)` will return the starting index of str2 if str2 is substring of str. Otherwise -1
- `str.split(sep)` will return a list. By default sep is _\s_
- `'_'.join(list)` will create a string from list with \_\_\_ separating its elements
- Strings are immutable so use
- **Formatting:**
  - See [official doc examples](https://docs.python.org/3/library/string.html#format-examples)
  - Default _fill_ for strings is ' '
  - There are three methods of formatting string:
    1. Using `%`
    2. Using `.format()`
    3. Using `f-strings`
- **Docstrings:**
  - Generally used for documentation
- **Colorful text:**
  - _ANSI_ characters are used for colors
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
# Docstrings
myStr = """Hello! \
My name is sickboy.
And I am learning python.
"""
# same as
myStr = 'Hello, World! My name is sickboy.\nAnd I am learning python.'

# Formatting
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
