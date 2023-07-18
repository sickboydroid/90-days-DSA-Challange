# Python Regex

- [Python Regex](#python-regex)
  - [Misc](#misc)
  - [Re library](#re-library)
  - [Regex Quick Recap](#regex-quick-recap)
    - [Grouping](#grouping)

## Misc

- Visit [Regex.com](https://regexr.com/)
- library in python: `re`
- **raw strings** in python treat `\` as normal character meaning `\n` is simply '\n' and new line
  - Useful in regex as it will pass `\` as it is to the function and does not modify it
- **Alphanumericals** or **Alphanumeric characters** are *[A-z0-9]*

## Re library

- Different Functions:
  - `re.search(pattern, str, flags=0)`:
    - flags can take values: re.IGNORECASE, re.MULTILINE, re.DOTALL (`.` matches newline as well)
  - `re.match(...)` similar to search except that it starts match from begginning of string
  - `re.fullmatch(...)` starts matching from begginning to end of string
  - `re.sub(pattern, replacement, string, count=0, flags=0)` will help you with find and replace
  - `re.split(pattern, string, maxsplit=0, flags=0)` will split the string using regex
  - `re.findall(pattern, string, flags=0)` helps you to find a pattern multiple times in a string
- Return Value:
  - Some functions of *re* return an instance of `Match` if there is at least one match in string otherwise `None`
  - `match.groups()` or `match.group(num)` will give you the captured groups. Group indexing starts with 1 because group 0 represents the original string

## Regex Quick Recap

- `.` matches any character except newline
- `+` 1 or more
- `?` 0 or 1
- `[...]` set of characters
- `[^...]` complement of set of characters
- `\w` is same as `[A-z0-9_]+`
- `\d` decimal digit
- `\s` whitespace
- `\W`, `\D` and `\S` are opposites of above

### Grouping

- `(...)` capturing group
- `(?:...)` non-capturing group