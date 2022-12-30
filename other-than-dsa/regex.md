global
: If disabled then matches only first occurence

- + one or more
- ? 0 or one (optional)
- * 0 or more
- (group){min, max}
- . matches anything except newline
- \w letter _
- \s whitespaces
- \d digits
- ^ begginning of line
- $ end of line
- 
> Capital letters of above negates. e.g \D matches everything except digits

EXAMPLES:
- GROUP: 
	- **(t|T)he** searches for the or The
	- **t|The** searches for t or The
	- **(t|T){2, 4}** a combinationof t and T whose length is 2 to 4
	- **(the|THE){2, 4}** a combination of the and THE in which there are either two 'the' or two 'THE' or one 'the' and 'THE' and at most 4

## Look behind and look ahead
Look behind helps you select things which are behind what are selecting
Look ahead helps you to select things which ahead of what you are selecting

(?<=the )\w\* selects the words after 'the '. This is called positive look behind
(?<\!the )\w* selects everything except the words followd by 'the '. This is called negative lookbehind

\w*(?=the) selects the words before 'the '.  It is called positive lookahead
\w*(?!the) selects everything except the words before 'the '. It is called negative lookahead

## Naming groups
(?<group_name>the|The)
(?<group1>the|?<group2>The)

You can create non capturing group as well
(?:the|The) here the will be captured

# Java Regex
`java
Patter regex = Pattern.compile("\\w*");
Matcher matcher = regex.mathcer("The string you want to match with");

// Everything thing about the output of regex is in matcher object
`

> Useful website: (Regex.com)[https://regexr.com/]
