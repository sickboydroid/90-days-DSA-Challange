# Java

- [Java](#java)
  - [Misc](#misc)
  - [Lambda expression](#lambda-expression)
  - [For each method](#for-each-method)
  - [Stream API](#stream-api)

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
  - **Normal Interface**: > One Method

Lambda Expression
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
```

## For each method

- In Collections
- for loop uses index and enhanced for loop uses iterator are both external loop
- For each method is an internal loop
- Receives an object of Consumer functional interface
  - Consumer has a generic method accept(T t)

```java
List<Integer> nums = Arrays.asList(1,2,3,4,5);
nums.forEach(System.out::println);

// OR
nums.forEach(n -> System.out.println(n));

// OR
Consumer<Integer> cons = new Consumer<>() {
   @Override
   public void accept(Integer n) {
      System.out.println(n);
   }
}
nums.forEach(cons);
```

## Stream API

- Lazy evaluation
- It is always better to use Mutable list for multithreading
- Stream api => Builder Pattern
- Once you use/consume the stream you can't reuse it
  - Prevents data leakage and improves performance
  - When you call any method of Stream class you have consumed it
- stream(): Stream with single thread
- parallelStream(): Stream with multiple threads
- count(): returns number of elements in stream
- sorted(): returns stream of sorted elements
- forEach(): ...
- map(): maps elements in new Stream
- filter(): filters elements
- reduce(): reduces stream

> Predicate\<T\>
> It is a functional interface with method: boolean test(T t);
> In theory, it can be used to validate objects as in Stream.filter(...)

We are only replacing values and not creating duplicates below

```java
// Creates new stream -> filters only odd nums -> sorts filtered values -> squares the elements and then prints them
Stream<Integer> nums = Collections.stream(Arrays.asList(1,34,343,2,3));
nums.stream()
   .filter(n -> n%2 == 1)
   .sorted()
   .map(n -> n * n)
   .forEach(System.out::println);

// The first argument is initial carry (called identity)
// 2nd argument is the function that you perform
// carry is basically output/return value of last function call
int sum = nums.stream()
   .reduce(0, (carry, element) -> carry + element)
```