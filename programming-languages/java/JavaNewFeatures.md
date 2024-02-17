# Java New Features

- [Java New Features](#java-new-features)
  - [TODO](#todo)
  - [try-with-resource Statement](#try-with-resource-statement)
  - [switch](#switch)
    - [Switch expressions](#switch-expressions)
    - [New Case label](#new-case-label)
    - [Using guarded patterns](#using-guarded-patterns)
  - [Record classses](#record-classses)
    - [Record classes and pattern matching](#record-classes-and-pattern-matching)
  - [Representing negatives](#representing-negatives)
    - [Mystery of 0xFF](#mystery-of-0xff)
  - [String Interpolation](#string-interpolation)

## TODO

- `Set.of("a","b")` and `Collections.unmodifiable`
- `switch` pattern matching
- `packaging`
- `making http requests`
- `system.`
- `charsequen ce`
- `threading`
- `textblocks`
- `charset`

## try-with-resource Statement

- A resource is an object that must be closed after the program is finished with it. The try-with-resource statement is a try statement that declares one or more resources and ensures that they are closed at the end of statement
- Object that implements `java.lang.AutoClosable` can be use used as a resource
- It can have a `catch` and a `finally` block as well which are only called after the resource is closed

## switch

- till now selector expression must evaluate to a number, string or enum constant and case labels must be constants.
  - Now selector exp. can be of any type and case labels can have patterns

### Switch expressions

- we now have switch expresssion and switch statments
- the main difference b/w case expressions and case statements is that case expressions evalutate to a single value and can be directly assigned to variables.
  - `yeild` can only be in switch expressions
  - `break` can only be in switch statements
- Unlike switch statements, the cases of switch expressions must be exhaustive, which means that for all possible values, there must be a matching switch label

```java
// switch expression
return switch (shape) {
  case Rectangle r -> 2 * r.length() + 2 * r.width();
  case Circle c    -> 2 * c.radius() * Math.PI;
  default          -> throw new IAE("Unrecognized shape");
};

// switch statement
switch (shape) {
  case Rectangle r: return 2 * r.length() + 2 * r.width();
  case Circle c:    return 2 * c.radius() * Math.PI;
  default:          throw new IAE("Unrecognized shape");
}
```

### New Case label

- A new type of case label `case L ->` is introduced for which you don't need break or yield statements
  - A `case L ->` label along with its code to its right is called a switch labeled rule.
  - a `case L:` label along with its code to the right is called a switch labeled statement group:
- You cannot use both types of labels in the same switch statement
- Recommended use of case L ->

```java
// form of case label
case L1, ..., Ln -> expression;|throw-statement;|block

// example of using new case label in switch expression
String mood = switch (day) {
  case MON, TUE, WED -> "good";
  case THU, FRI, SAT -> throw new IllegalArgumentException();
  case SUN -> {
      print("It is very nice to have sunday");
      yield "great";
  }
};

// example of using new case label in switch statement
String mood;
switch (day) {
  case MON, TUE, WED -> mood = "good";
  case THU, FRI, SAT -> throw new IllegalArgumentException();
  case SUN -> {
      print("It is very nice to have sunday");
      mood = "great";
  }
};

// same thing done using traditional case labels
String mood = switch(day) {
  case MON:
  case TUE:
  case WED:
    yield "good";
  case THU:
  case FRI:
  case SAT:
    throw new IllegalArgumentException();
  case SUN:
    print("...");
    yield "great";
};
```

### Using guarded patterns

```java
switch(obj) {
    case null -> print("Invalid argument");
    case String s when s.length() > 10 -> print("String is too long");
    case String s when s.length() < 5  -> print("String is too short");
    case String s when s.tes1() && s.test2() -> print("Tests failed");
    case String s -> print("String is too good");
    default: print("Unknown input");
}
```

## Record classses

- immutable, data carrier, implicitly final (cannot be extended)
- equals: if contain same equal field values
- **header** lists the **components** of the record
  - For each component `private final Type name;` and `public Type name() {}`
  - constructor whose signature is the same as the header
  - equals and hashCode: record classes are equal if they are of the same type and contain equal component values
    - Note: you cannot have instance variables so only components decide the uniqueness of record class
  - toString: string representation of components
- you can declare non-static variables, instance intializers, native methods
- nested record classes are implicity static

```java
record Point(int x, int y) {
  int arg; // invalid
  static int arg; // valid

  public Point { // compact constructor: signature and assignment is implicit
    if(x < 0 || y < 0)
      throw new IllegalArgumentException("x and y must be non-negative");
  }

  public Point(int x, int y) { // you can use this as well
    if(x < 0 || y < 0)
      throw new IllegalArgumentException("x and y must be non-negative");
    this.x = x;
    this.y = y;
  }

  @Override
  public int x() { // you can override accessor methods
    print("Accessing x");
    return x;
  }

}
```

### Record classes and pattern matching

```java
record Point(int x, int y) {
  public double getArgument() {
    return Math.PI/2;
  }
}


// extracting values
if(obj instanceof Point(var x, var y))
  print(STR."x is \{x} and y is \{y}")


// switch expression
Double result = switch (object) {
    case Location(var name, GPSPoint(var a, var b)) -> a;
    default -> 0.0;
};

int coord = switch(point) {
  case Point(var x, var y) when x == 0 -> y;
  case Point(var x, var y) when y == 0 -> x;
  default -> 0;
};
```

## Representing negatives

- Problems with using first bit as a sign is that addition is weird and we have -ve zero and +ve zero. Same problems arrive with **1's complement** in which we get negative of a number by fliping each bit.

  - There are two zeros (+ve zero and -ve zero) i.e 1 byte will represent values from -7 to 7 i.e only 15 distinct numbers rather than 16

- **2's compliment** fixes all these problems. To get negative of a number, flip the bits and add 1
  - Mathematically the last bit becomes -ve as in following case
    - 0 0 0 0
    - -8 4 2 1
  - So `0011` (2 + 1 = 3) has negative as 1100 + 1 = `1101` (-8 + 4 + 1 = -3)

```java
// changes sing of a number according to 2's complement which java uses
// it is equivalent to -n
n = ~n + 1
```

### Mystery of 0xFF

- 0XFF is 1111 1111 in binary and is used to extract lower 8 bits from number as follows
  - 1010 1100 0101 `&` 0XFF `=` 0000 1100 0101
- We use it whenever we want to convert a **byte** to **int** but want byte to be treated as **unsigned byte**

```java
byte bPos = 12;
byte bNeg = -12;

int num = bPos; // num is 12
int num = bNeg; // num is -12

int num = bPos & 0xFF; // num is 12
// num is 244 because -12 is 1   1   1   1      0   1   0   0 in 1 byte but since we are extracting these bits
//                         -128  64 32  16      8   4   2   1
// and putting them in integer it becomes 0000 0000 1111 0100 which is 244

int num = bNeg & 0XFF;
```

## String Interpolation

- Following https://www.baeldung.com/java-21-string-templates
- **String interpolation** is the process of evaluating a string literal containing one or more place holders.
  - We already had string concatenation, StringBuilder, String formatter and message formatter. But now we have **template strings**

```java
MessageFormat.format("This is worlds #{0} language and is widely used for #{1}", 1, "programming");
```
