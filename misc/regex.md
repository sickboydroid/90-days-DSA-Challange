# REGEX

- [REGEX](#regex)
  - [EXAMPLES on GROUPS:](#examples-on-groups)
  - [Look behind and look ahead](#look-behind-and-look-ahead)
  - [Naming groups](#naming-groups)
  - [Java Regex](#java-regex)

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
