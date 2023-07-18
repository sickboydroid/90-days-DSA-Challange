# Python Regex

- [Python Regex](#python-regex)
  - [Misc](#misc)
  - [Regex Python](#regex-python)
  - [Java Regex](#java-regex)
  - [Regex Quick Recap](#regex-quick-recap)
    - [Grouping](#grouping)
    - [Look behind and look ahead](#look-behind-and-look-ahead)

## Misc

- Visit [Regex.com](https://regexr.com/) for practice

## Regex Python

- Use `re` library for regex
- **raw strings** in python treat `\` as normal character meaning for example `\n` is simply '\n' and not new line
  - It is helpful in regex as it will pass `\` as it is to the function and does not give some special meaning
- **Alphanumericals** or **Alphanumeric characters** are *[A-z0-9]*
- Different Functions:
  - `re.search(pattern, str, flags=0)`:
    - flags something like: re.IGNORECASE, re.MULTILINE, re.DOTALL (i.e `.` matches newline as well)
  - `re.match(...)` similar to search except that it starts match from begginning of string
  - `re.fullmatch(...)` starts matching from begginning to end of string
  - `re.sub(pattern, replacement, string, count=0, flags=0)` will help you with find and replace
  - `re.split(pattern, string, maxsplit=0, flags=0)` will split the string using regex
  - `re.findall(pattern, string, flags=0)` helps you to find a pattern multiple times in a string
- Return Value:
  - Some functions of *re* return an instance of `Match` if there is at least one match in string otherwise `None`
  - `match.groups()` or `match.group(num)` will give you the captured groups. Group indexing starts with 1 because group 0 represents the original string

## Java Regex

```java
Pattern regex = Pattern.compile("\\w*");
Matcher matcher = regex.matcher("The string you want to match with");
while (matcher.find()) {
    String matchedSubstring = matcher.group();
    System.out.println("Matched substring: " + matchedSubstring);
}
```

## Regex Quick Recap

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

### Grouping

- `(...)` capturing group
- `(?:...)` non-capturing group

### Look behind and look ahead

- Look-behind:
  - Selects things that are behind what you are selecting
  - Positive look-behind: `(?<=the )\w*` selects the words after "the "
  - Negative look-behind: `(?<!the )\w*` selects everything except the words followed by "the "

- Look-ahead:
  - Selects things that are ahead of what you are selecting
  - Positive look-ahead: `\w*(?=the)` selects the words before "the "
  - Negative look-ahead: `\w*(?!the)` selects everything except the words before "the "

