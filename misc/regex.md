# REGEX

- `global`:
  - If disabled then matches only the first occurrence

- `+`:
  - Matches one or more of the preceding character

- `?`:
  - Matches zero or one of the preceding character (optional)

- `*`:
  - Matches zero or more of the preceding character

- `(group){min, max}`:
  - Matches the preceding group between min and max times

- `.`:
  - Matches any character except newline

- `\w`:
  - Matches any letter or underscore

- `\s`:
  - Matches any whitespace character

- `\d`:
  - Matches any digit

- `^`:
  - Matches the beginning of a line

- `$`:
  - Matches the end of a line

> Capitalizing any of the above negates it (e.g. `\D` matches any character that is not a digit)

## EXAMPLES on GROUPS:

- `(t|T)he`:
  - Matches "the" or "The"

- `t|The`:
  - Matches "t" or "The"

- `(t|T){2,4}`:
  - Matches a combination of "t" and "T" that is 2 to 4 characters long

- `(the|THE){2,4}`:
  - Matches a combination of "the" and "THE" in which there are either two "the" or two "THE" or one "the" and "THE" and at most 4

## Look behind and look ahead

- Look-behind:
  - Selects things that are behind what you are selecting
  - Positive look-behind: `(?<=the )\w*` selects the words after "the "
  - Negative look-behind: `(?<!the )\w*` selects everything except the words followed by "the "

- Look-ahead:
  - Selects things that are ahead of what you are selecting
  - Positive look-ahead: `\w*(?=the)` selects the words before "the "
  - Negative look-ahead: `\w*(?!the)` selects everything except the words before "the "

## Naming groups

- `(?<group_name>the|The)`
- `(?<group1>the|(?<group2>The))`
- You can create non-capturing groups as well
  - `(?:the|The)`: "the" will be matched, but not captured

## Java Regex

```java
Pattern regex = Pattern.compile("\\w*");
Matcher matcher = regex.matcher("The string you want to match with");
while (matcher.find()) {
    String matchedSubstring = matcher.group();
    System.out.println("Matched substring: " + matchedSubstring);
}


> Useful website: [Regex.com](https://regexr.com/)
