# Java

- [Java](#java)
  - [Misc](#misc)
  - [Lambda expression](#lambda-expression)
    - [Method Referencing](#method-referencing)
  - [Generics](#generics)
    - [Type Erasure](#type-erasure)
    - [Array Of Generics](#array-of-generics)
  - [Numbers (subclasses of Number)](#numbers-subclasses-of-number)
    - [AutoBoxing and Unboxing](#autoboxing-and-unboxing)
  - [java.lang.Class](#javalangclass)
  - [Conversions](#conversions)

## Misc

1. Ambiguous if:

```java
if <expr1> if <expr2> <stmntA> else <stmntB>
// it is equivalent to
if <expr1> { if <expr2> <stmntA> else <stmntB> }
```

## Lambda expression

- Interfaces defines behavior
- Types of interface:

  - **Marker Interface**: No abstract methods
  - **Functional Interface**: One method (before 1.8 SAM)
    - Useful when you try to pass functionality as argument
  - **Normal Interface**: > One Method

- General Syntax of Lamda expression: `(<method parameters>) -> { <method-body> }`

  - If single parameter, `()` can be omitted
  - If single expression, `{}` can be omitted
  - If single expression and returns value, `{}` and `return` can be omitted

- `java.util.function` package contains lots functional interfaces:

  - `Predicate<T>` with `boolean test(T t)` for testing (BiPredicate is available as well)
  - `Consumer<T>` with `void accept(T t)` for accepting input and not returning anything (BiConsumer is available as well)
  - `Function<T, R>` with `R apply(T t)` for accepting input and returning value (BiFunction is available as well)
  - `Supplier<T>` with `T get()` is a factory function for creating new instances
  - `IntSupplier` supplies int, `LongConsumer` consumes long, `DoubleFunction` accepts double, etc

- Types of parameters can be skipped
  : When you create implementation of a Functional interface you can use lambda expression

```java
// functional interface annotation prevents you from creating multiple methods in interfaces
@FunctionalInterface
interface Consumer<T> {
   void accept(T t);
}

// in code
Consumer<String> con = (String val) -> {/*method body*/};
con.accept("Do something with value");

// Example 2
interface IntegerMath {
   int operation(int a, int b);
}

IntegerMath addition = (a,b) -> a + b;
IntegerMath subtraction = (a,b) -> a - b;
print("2 + 5 is " + addition.operation(2,5));
print("2 - 5 is " + subtraction.operation(2,5));

// Example of method referencing
Consumer<String> consumer = (str) -> System.out.println(str);
/* OR */
Consumer<String> consumer = System.out::println;
```

### Method Referencing

- If your lambda expression simply calls another method, you can use `Method referencing` (See example)

```java
// static method referencing
Function<Integer, Integer> function = (num1, num2) -> Math.pow(num1, num2);
Function<Integer, Integer> function = Math::pow;

// instance method referencing of an object
SpaceRemove remover = new SpaceRemover();
Consumer<String> consumer = (str) -> remover.removeSpaces(str);
Consumer<String> consumer = remover::removeSpaces;

// instance method referencing of an object another example
StringIntersection intersection = new StringIntersection();
BinaryOperation<String, String> consumer = (str1, str2) -> intersection.intersect(str1,str2);
BinaryOperation<String, String> consumer = intersection::intersect; // passes both string as arguments to intersect method

// instance method referencing of an arbitrary object
BinaryOperation<String, String> consumer = (str1, str2) -> str1.concat(str2);
BinaryOperation<String, String> consumer = String::concat; // takes first string and calls concat with 2nd string as argument

// constructor referencing
Supplier<Cat> supplier = () -> { return new Cat()};
Supplier<Cat> supplier = Cat::new;
```

## Generics

- By convention `TypeParameter` is called `T` and extends `Object` by default
- For generic methods, you have to put `TypeParameter` before `return type`
- See `Mistake Method` below, you cannot pass `List<Integer>` to it because it is not subclass of `List<Object>`
  - Wildcard `?` is used to accept generics of any type
- **T** in `Caller<T>` is a **type parameter** and **String** in `Caller<String>` is a **type argument**.
-

```java

// Generic Class
public class Caller <T extends Class1 & Interface1 & Interface2, S extends Color, U, V> {
   public T call(T animal, S color) {
      print(animal.name + " is " + color.rgb);
      return animal;
   }
}

// Generic Method
public <T,S> String combine(T alpha, S beta) {
   return alpha + " " + beta;
}

// Mistake method
public void print(List<Object> items) {
   ...
}

// Right Method
public void print(List<? extends Number> items) {
   ...
}


// Usage
Caller<Cat> caller = new Caller<>();
caller.call(kitty);
caller.call(pinky);
```

### Type Erasure

- Generics were added to ensure type safety. Compiler applies a process called `type erasure` and removes all type parameters, replacing them with their bounds or Object if unbounded.
- Thus bytecode has no info of type parameters

```java
// Original code
List<String> list;
Integer num2 = calc<Integer>(Integer.valueOf(12));
public <T extends Number> T calc(T number) {
   number.add(12);
   return number;
}

// Bytecode
List<Object> list; /* or */ List list;
Integer num2 = (Integer) calc(Integer.valueOf(12));
public T calc(Number number) {
   number.add(12);
   return number;
}
```

### Array Of Generics

- We cannot create objects or arrays of type parameters as type parameters are only at compile time. At runtime T and T[] are as good as Object and Object[]. Thus leading to ClassCasteException.

- Let's say we want to create array of T and Caller<Cat>

```java
// Method 1
ArrayList<T> array = new ArrayList<>(initialCapacity);
ArrayList<Caller<Cat>> array = new Arraylist<>(initialCapacity);

// Method 2
// Will not run as generic arrays are not possible: T[] arr = new T[cap];
@SuppressWarning("unchecked")
Caller<Cat>[] arr = new Caller[cap]; // You cannot create generic array i.e new Caller<Cat>[cap] is invalid

// Method 2
T[] array = (T[]) new Object[cap];
Caller<Cat>[] array = (Caller<Cat>[]) new Object[cap];

// Method 3
T[] array = Array.newInstance(clazz.getComponentType(), cap);

// Method 4
Object[] array = new Object[cap]; // used by ArrayList internally
```

> Except for primitive type arrays, all object arrays and ArrayList of those objects perform same provided you don't exceed initial capacity and treat ArrayList as an array of fixed size

## Numbers (subclasses of Number)

- `AtomicInteger` and `AtomicLong` are thread-safe
- `BigInteger` and `BigDecimal` are for high-precision calculations
- `Integer.valueOf(12).floatValue()` is equivalent to `(float) 12`
- `Integer.parseInt(String decimal, int radix)` is useful for number conversion.
  - Note `Integer.valueOf(String s)` method returns a boxed int while as `Integer.parseInt(...)` returns primitive.

### AutoBoxing and Unboxing

- Compiler automatically converts `primitive types` to `object wrapper` is called **autoboxing**
  - Ex: int to Integer, float to Float, char to Character
- Compiler automatically converts `object wrapper` to `primitive types` is called **unboxing**
  - Ex: Integer to int, Float to float, Character to char
- This is done in two cases:
  - While assigning primitive to wrapper (vice-versa)
  - While passing primitive to wrapper (vice-versa)

```java
List<Integer> list = new ArrayList<>();

// our code
Integer val1 = 2;
int val2 = val1 / 2;
list.add(val1);
list.add(val2);

// compiler's code
Integer val1 = Integer.valueOf(2);
int val2 = val1.intValue() / 2;
list.add(val1);
list.add(Integer.valueOf(val2));
```

## java.lang.Class

- Except for `int, long, double, etc` everything in java is reference type (including enums, arrays, interfaces, etc) and inherit from Object
- For every object JVM instantiates an object of java.lang.Class which holds runtime and type information about that object
- Getting Class object is starting point for all reflection operations
  - If instance of object is available: `obj.getClass();`
  - If only type is available: `boolean.class; Set.class; void.class;`
  - If full-qualified name is available: `Class.forName("java.io.File");`
    - `Class.forName("[D")` is eq. to double[].class
    - `Class.forName("[[Ljava.lang.String;)` is eq. to `String[][].class`
  - If wrapper exists: `Void.TYPE; Integer.TYPE` (eq. to int.class)

## Conversions

- Conversion from `Object` to `Thread` requires runtime check
- Conversion from `Thread` to `Object` requires no runtime check

- Examples of inter-converting arrays

```java
// Trap
Object[] arr = new Object[10];
Integer[] integers = (Integer[]) arr; // throws exception

// Solution-1: Elements in integers are same as that in arr but integers[0] = 0 does
// not make arr[0] to 0 as they are two different arrays now
Integer[] integers = Arrays.copyOf(arr, arr.length, new Integer[].class);

// Solution-3
Integer[] integers = new Integer[10];
/* copy arr to integers using for-loop */

// Solution-3
Object[] arr = new Integer[10]; // notice the arr is holding Integer[]
Integer[] integers = (Integer[]) arr;
```
