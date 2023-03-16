# Fundamentals

## Misc

- Blue: Important, learnt something new
- Red: Rewise couldn't understand or solve
- Underline: Mark for something

---

- Loops run until the condition becomes false

## Operators

- Order of operation (Prefer parenthesis):
  1. \*  / ( % ) have priority over + -
  2. **()** > **!** > **&&** > **||** (priority order)
  3. left to right associativity except for `=` and `->`
- The operators **&**, **|**, and **^** are *bitwise logical operations* for integer types that do **and**, **or**, and **exclusive or** (respectively) on each bit position.
  - xor operator is false only when both bits are true
  - **~** is bitwise negation. Flips every bit

## Primitive data types

- Primitive data types:
  1. `long` is 64-bits. Range [-4B, 4B]
  2. `int` is 32-bits. Range [-2B, 2B]
  3. `short` is 16-bits. Range [-32K, 32K]
  4. `char` is 16-bits. Arithmetic operations are valid
  5. `byte` is 8 bits.
  6. `float` is 32-bits.
  7. `double` is 64-bits.
- Default value of numeric types is **0** and **false** for boolean types
- `... = new int[row][column]` by convention. M -> y and N -> x
- `var` keyword introduced in Java 10, allows the compiler to infer the data type of a variable based on its initialization expression.

## Java API

1. String:
   - `indexOf(target, start)`, starts search from start and if not found returns -1
   - If one arg of *+* is string only then is other arg converted to string
2. Math:
   - `log(num)`, base e

## Overflow and Underflow

- Overflow: Absolute value is too big for data type.
- Underflow: Very, very small absolute value.
- Integer-wraparound:
  - `Integer.MAX_VALUE + 1 = Integer.MIN_VALUE`
  - `Integer.MIN_VALUE - 1 = Integer.MAX_VALUE`
- No exception is thrown on under/overflow.
- `1/0` is undefined, but `1.0/0.0` is infinity.
- `%` is the remainder operator, which gives the remainder you will get on paper.
- Modulo is another operation, which performs Euclidean division.
## Java For Loop

```java
// Both are equivalent
for ( <initialization> ; <test> ; <increment> ) {
     <body>
}

<initialization>
while ( <test> ) {
    <body>
    <increment>
}
```

## Type Conversion

1. **Widening or automatic conversion**: BYTE -> SHORT -> INT -> FLOAT -> DOUBLE
2. **Narrowing or explicit conversion**: reverse order of above
3. **Type Promotion in Expressions**: While evaluating expressions, the intermediate value may exceed the range of operands and hence the expression value will be promoted. Some conditions for type promotion are:
   1. Java automatically promotes each byte, short, or char operand to int when evaluating an expression.
   2. If one operand is long, float or double the whole expression is promoted to long, float, or double respectively.

## Formatted string

### Formatting Syntax

Java uses the `Formatter` class to [format strings](https://docs.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax), and the syntax for formatted strings is as follows:

`%[argument_index$][flags][width][.precision]conversion`

- `argument_index`: (optional) The index of the argument to use for this format specifier.
- `flags`: (optional) Specifies various options for formatting, such as left-justifying, adding leading zeros, and more.
- `width`: (optional) The minimum number of characters to use for this format specifier.
- `precision`: (optional) The number of decimal places for floating point numbers or the maximum number of characters for strings.
- `conversion`: (required) The conversion character, which specifies the type of data being formatted.

### Conversion Characters

The conversion character specifies the type of data being formatted:

- `%d`: Integral data types (byte, short, int, long)
- `%f`: Floating point data types (float, double)
- `%s`: General data types (strings, objects)
- `%t`: Date/time data types, followed by a date/time conversion character (see the [Java documentation](https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html#dt) for a list of conversion characters)
  - `%tH`: Hour in 24-hour format (00-23)
  - `%tI`: Hour in 12-hour format (01-12)
  - `%tM`: Minute (00-59)
  - `%tS`: Second (00-59)
  - `%tp`: AM or PM
  - `%tB`: Full month name (e.g., January)
  - `%tb`: Abbreviated month name (e.g., Jan)
  - `%tm`: Month as a two-digit number (01-12)
  - `%tY`: Year with century (e.g., 2022)
  - `%ty`: Year without century (e.g., 22)
  - `%tA`: Full weekday name (e.g., Monday)
  - `%ta`: Abbreviated weekday name (e.g., Mon)
  - `%td`: Day of the month as a two-digit number (01-31)
  - `%te`: Day of the month as a one- or two-digit number (1-31)
- Use the `%n` conversion to add a newline character to the formatted string.
- `<`: Use same arg index as used by prev format specifier

```java  
// Format the current date and time as "2022-03-17 12:45:30 PM"
String.format("%tY-%tm-%td %tI:%tM:%tS %tp", new Date());

// Format the current date as "March 17, 2022"
String.format("%tB %<te, %<tY", new Date());
```

## Methods and Functions

- Functions and Methods:
  1. Args are passed by values
  2. Method overloading: Multiple methods with same name but diff args
  3. void methods are said to produce side effects i.e they change state of system (print, sort array, create file, etc)
     - Void methods do not represent mathematical function

## Recursion

- Recursion:
  - **Base Case:** Conditional statement as the first statement
  - **Converge:** Recursive calls should be sub-problems in some sense
  - **Disjoint sub-problems**: For example in binary search the recursive call must not search the portion which its caller already searched

## Redirection and Piping

### Redirection

- `command > file.txt` redirects the output stream of `command` to `file.txt`
- `command < file.txt` redirects the input stream of `command` from `file.txt`
- `command >> file.txt` appends the output stream of `command` to `file.txt` instead of overwriting it
- `command 2> error.txt` redirects the standard error stream of `command` to `error.txt` instead of the console output

### Piping

- `cmd1 | cmd2` pipes the output stream of `cmd1` as input to `cmd2`
- `cmd1 | tee file.txt | cmd2` pipes the output stream of `cmd1` to both `file.txt` and as input to `cmd2`
- `cmd1 &> output.txt` pipes both standard output and standard error streams of `cmd1` to `output.txt`

## Proof by Mathematical Induction

1. Prove base case (i.e simplest input which is mostly for n = 1)
2. Assume that it is true for n = k
3. Show that it is true for n = k+1
    - Solve RHS using formula
    - Solve LHS using algebra
    - LHS = RHS

> You proved it! How? You proved in `step3` that if you f(k) is true then f(k+1) has to be true. In `step1` you proved that it is true for f(1).
> Therefore it has to be true for f(2) as from `step3`. Similarly it has to be true for f(3) as it is true for f(2) and so on

## DP

Three steps:

   1. **Recursion:** Solve with recursion
   2. **Memoization:** Store values if too much recursion
   3. **Bottom-up:** Remove recursion

DP -> Recursion + Memo + Guessing

### Memoization

1. Create empty arr
2. Is value in arr? return it
3. Calculate value
4. Store in memo
5. return value

> time = sub-problems * (time/subproblem)
> Don't count memoized recursive calls

### Bottom-up approach

Same computation as memoization but no recursion -> no stackoverflow exception

## Comparing doubles

Comparing doubles till 3 decimal places. Thus, 1.123 = 1.1234 = 1.12349 != 1.124

```java
BigDecimal d1 = BigDecimal.valueOf(1.123433333).setScale(3, RoundingMode.FLOOR);
BigDecimal d2 = BigDecimal.valueOf(1.12399999).setScale(3, RoundingMode.FLOOR);
```
