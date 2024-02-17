# Misc

- [Misc](#misc)
  - [Conversion of primitive and non-primitive arrays](#conversion-of-primitive-and-non-primitive-arrays)
  - [Formatting](#formatting)

## Conversion of primitive and non-primitive arrays

```java
// using a custom comparator
int[] arr = {1,3,2,8,5};
arr = Arrays.stream(arr)
        .boxed() // convert to Integer
        .sorted((a, b) -> Integer.compare(a,b))
        .mapToInt(Integer::intValue) // convert to int
        .toArray(); // convert to int[]
```

## Formatting

![Java Formatting](assets/string-formatting.gif)

- `%[argument_index$][flags][width][.precision]conversion`

- **Precision**. For floating point values, this is the mathematical precision of the formatted value. For s and other general conversions, this is the maximum width of the formatted value; the value is right-truncated if necessary.
- **Width**. The minimum width of the formatted value; the value is padded if necessary. By default the value is left-padded with blanks.
- **Flags** specify additional formatting options. In the Format example, the `+` flag specifies that the number should always be formatted with a sign, and the `0` flag specifies that **0** is the padding character. Other flags include `-` (pad on the right) and , (format number with locale-specific thousands separators).
  - `-` The result will be left-justified.
  - `#` The result should use a conversion-dependent alternate form
  - `+` The result will always include a sign
  - ` ` The result will include a leading space for positive values
  - `0` The result will be zero-padded
  - `,` The result will include locale-specific grouping separators
  - `(` The result will enclose negative numbers in parentheses
- The **Argument Index** allows you to explicitly match a designated argument. You can also specify < to match the same argument as the previous specifier. Thus the example could have said: System.out.format("%f, %<+020.10f %n", Math.PI);
