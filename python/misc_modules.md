# Misc Modules

- [Misc Modules](#misc-modules)
  - [re (for regex)](#re-for-regex)
    - [Regex recap](#regex-recap)
      - [Grouping](#grouping)
      - [Look behind and look ahead](#look-behind-and-look-ahead)
    - [Regex and Python](#regex-and-python)
  - [JSON](#json)
    - [Conversion table](#conversion-table)
  - [functools](#functools)

## re (for regex)

### Regex recap

- `+`: one or more of the preceding character
- `?`: zero or one of the preceding character (optional)
- `*`: zero or more of the preceding character
- `(group){min, max}`: Matches the preceding group between min and max times
- `.`: any character except newline
- `\w` is same as `[A-z0-9_]+`
- `\s`: any whitespace character
- `\d`: any decimal digit
- `\W`, `\D` and `\S` are opposites/complements of above
- `^`: Matches the beginning of a line
- `$`: Matches the end of a line
- `[...]` set of characters
- `[^...]` complement of set of characters

#### Grouping

- `(...)` capturing group
- `(?:...)` non-capturing group

#### Look behind and look ahead

- Look-behind:

  - Selects things that are behind what you are selecting
  - Positive look-behind: `(?<=the )\w*` selects the words after "the "
  - Negative look-behind: `(?<!the )\w*` selects everything except the words followed by "the "

- Look-ahead:
  - Selects things that are ahead of what you are selecting
  - Positive look-ahead: `\w*(?=the)` selects the words before "the "
  - Negative look-ahead: `\w*(?!the)` selects everything except the words before "the "

### Regex and Python

- Visit [Regex.com](https://regexr.com/) for practice
- Use `re` library for regex
- **raw strings** in python treat `\` as normal character meaning for example `\n` is simply '\n' and not new line
  - It is helpful in regex as it will pass `\` as it is to the function and does not give some special meaning
- **Alphanumericals** or **Alphanumeric characters** are _[A-z0-9]_
- Different Functions:
  - `re.search(pattern, str, flags=0)`:
    - flags something like: re.IGNORECASE, re.MULTILINE, re.DOTALL (i.e `.` matches newline as well)
  - `re.match(...)` similar to search except that it starts match from begginning of string
  - `re.fullmatch(...)` starts matching from begginning to end of string
  - `re.sub(pattern, replacement, string, count=0, flags=0)` will help you with find and replace
  - `re.split(pattern, string, maxsplit=0, flags=0)` will split the string using regex
  - `re.findall(pattern, string, flags=0)` helps you to find a pattern multiple times in a string
- Return Value:
  - Some functions of _re_ return an instance of `Match` if there is at least one match in string otherwise `None`
  - `match.groups()` or `match.group(num)` will give you the captured groups. Group indexing starts with 1 because group 0 represents the original string

## JSON

- json module contains only 4 methods. Two for serialization, two for deserialization and one for detecting encoding
  1. **Serializing** or **Encoding** (converting _dict_ to _json_):
     - `json.dumps(dict)`: dict => json string
     - `json.dump(dict, file)`: dict => json file
  2. **Deserializing** or **Decoding** (converting _json_ to _dict_):
     - `json.loads(str)`: json string => dict (see [conversion table](#conversion-table))
     - `json.load(file)`: json file => dict
- Even though I used `dict` above, it is not necessary. If you json something like `[..something...]` it will be converted to python `list`

### Conversion table

<pre>
________________________
<b>Python</b>           <b>JSON</b>
________________________
dict             object
list, tuple      array
str              string
int, float       number
True             true
False            false
None             null
</pre>

## functools

- `functools.partial(func, arg1, arg2, ..., karg1=value1, karg2=value2)`: Used to create a variant func that has some parameters filled by default. This is called **partial function application**

```python

def log(message, subsystem):
    # Write the contents of 'message' to the specified subsystem.
    print('%s: %s' % (subsystem, message))

server_log = functools.partial(log, subsystem='server')
server_log('Unable to open socket') # equivalent to log('...', 'server')
```
