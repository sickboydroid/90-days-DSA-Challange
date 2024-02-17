# Java Collection Framework

- [Java Collection Framework](#java-collection-framework)
  - [Collection Interface](#collection-interface)
    - [forEach method](#foreach-method)
    - [Stream API](#stream-api)
    - [Stream API extended](#stream-api-extended)
  - [Object Ordering](#object-ordering)
  - [Map](#map)
    - [TreeMap](#treemap)

## Collection Interface

- **Collection** is object that groups multiple units in a single unit
- **Core Collection Interfaces**:
- - Collection -> List, Queue, Deque, Set -> Sorted Set
  - Map -> Sorted Map. Map is not considered a _true collection_.
- For manageability java does not provide different variants of collection types like immutable, fixed-size, etc.
  - Those operations can be achieved via methods like Collections.unmodifiableList() (see [Collections](#collections-class))
  - **UnsupportedOperationException** is thrown incase of invalid operation
- All collection implementations have a **conversion constructor** that accepts a `Collection` argument
- Ways to traverse a collection
  - Using **stream** api
  - Using **for-each** construct
  - Using **iterators**
- Some Important methods in Collection interface:
- `toArray()` and `toArray(T[] arr)` act as a bridge b/w collection framework and arrays (see example)

- Example of `toArray(...)`

```java
Integer[] arr = (Integer[]) list.toArray(); // ClassCasteException thrown because ArrayList keeps all elements in Object array so we cannot convert that to Integer array (see JavaConcepts.Conversions for work-around)
Object[] arr = list.toArray();
Integer[] arr = list.toArray(new Integer[0]);

// if n >= list.size() passed array is filled and arr[list.size()] is set to null if possible
// if n < list.size() then new array is created and returned as above
Integer[] arr = new Integer[n];
list.toArray(arr); // if n < list.size then new array is create and returned,
```

### forEach method

- This method is defined in `Iterable` interface. Since Collection implements that interface, we can use this method on any collection.
- fori loop uses index and enhanced-for-loop uses iterator but both are external loops. On the contrary, forEach method is an internal loop
- It accepts an object of `Consumer` functional interface applies that to every element

```java
List<Integer> nums = Arrays.asList(1,2,3,4,5);
nums.forEach(n -> System.out.println(n));

/* OR */
Consumer<Integer> cons = new Consumer<>() {
   @Override
   public void accept(Integer n) {
      System.out.println(n);
   }
}
nums.forEach(cons);
```

### Stream API

- **Stream** is a sequence of elements (its not a data structure)
- **Aggregate Operation** is an operation that produces a single value from a collection of values. They are of two types:

  1. **Intermediate Operations**
  2. **Terminal Operations**

- **Pipeline** is sequence of aggregate operations

  - In `data.stream().filter().forEach()` is a _pipeline_ and _filter_ & _forEach_ are aggregate operations

- A **stream pipeline** consists of `source` (like an array, generator functions, i/o channel etc), zero or more `intermediate operations` (like filter, map, sort) that produce new streams and a `terminal operation` (like foreach, sum, count, average etc) that produce a non-stream result or a side-effect.
  - **Reduction Operation** is a terminal operation which returns a single value. Thus **count** and **toList** are reduction operation while as **forEach** is not.
  - `reduce(...)` and `collect(...)` are general purpose reduction operations while as operations like `count`, `average`, `sum` are inbuilt reduction operations.
  - `reduce(identity, accumulator)` is used when you want combine the elements of a stream in a single value as `sum` and `average` does
  - `collect(Supplier supplier, BiConsumer accumulator, BiConsumer combiner)` is used when you want transform elements of a stream in a different form as `toList()` does
    - Supplier is the factory function that gives instance of container
    - Accumulator is the function that receives container and new element and adds element in a new container
    - Combiner combines two containers in a single one
- Computations are performed only when **terminal operation** is instantiated

- _Streams_ differ from _iterators_ in many ways like:
  1. Lazy v/s Eager Evaluation
  2. Declarative v/s Imperative
  3. Mutability: Streams don't modify underlying collection while as iterators can
- **IntStream**, **LongStream** and **DoubleStream** are streams that specialized for ints, longs and doubles only. You can get them via `mapToInt`, `mapToLong` and `mapToDouble`.
- Some useful functions:

  - stream(): Stream with single thread
  - parallelStream(): Stream with multiple threads
  - count(): returns number of elements in stream
  - sorted(): returns stream of sorted elements
  - distinct(): returns stream of distinct elements
  - skip(n): returns stream with skipped first n elements
  - forEach(): see above
  - map(): maps elements in new Stream
  - filter(): filters elements
  - reduce(): reduces stream
  - collect(): collects stream
  - toList(): returns stream as a list

- Example of stream api

```java
List<Integer> nums = Arrays.asList(1,34,343,2,3);
nums.stream() // src
   .filter(n -> n%2 == 1) // keep only odd elements // intermediate 1
   .sorted() // sort them // intermediate operation 2
   .map(n -> n * n) // square them // intermediate operation 3
   .forEach(System.out::println); // print them // terminal operation
```

- Example of `reduce` method

```java
int sum = nums.stream()
    .reduce(0, (a, b) -> a + b);
// arg1 is identity and arg2 is accumulator. If 0 elements in stream then identity
// is returned. Also identity is one of inputs of first call of lambda function.
// Accumulator is basically BinaryOperator functional interface which has a
// function that accepts two inputs and returns output.
// Here lambda function behaves as: (0 (identity) 1) returns 1 -> (1 34) 35 ->
// (35 343) 378 -> (378 2) 380 -> (380 3) 383 and this is what is stored in sum
```

- Example of `collect` method

```java
public class MyCollector implements Consumer<Integer> {
    ArrayList<Integer> nums = new ArrayList<>();
    public MyCollector() {}

    @Override
    public void accept(Integer num) {
        nums.add(num);
    }

    public void combine(MyCollector other) {
        nums.addAll(other.nums.getAll());
    }
}

// M-1: Elaborate to see what is actually happening
MyCollector collector = nums.stream()
            .collect(
            new Supplier<MyCollector>() {
                public MyCollector get() { return new MyCollector(); }
            },
            new BiConsumer<MyCollector, Integer>() {
                public void accept(MyCollector collector, Integer num) {
                    collector.collect(num);
                }
            },
            new BiConsumer<MyCollector, MyCollector>() {
                public void accept(MyCollector collector1, MyCollector collector2) {
                    collector1.combine(collector2);
                }
            });
// M-2
MyCollector collector = nums.stream()
                            .collect(MyCollector::new, MyCollector::collect, MyCollector::combine);

// M-3
List<Integer> list = nums.stream().collect(Collectors.toList());

// M-4
List<Integer> list = nums.stream().toList();
```

### Stream API extended

- Infinite Stream
  - `Stream.iterate(100, e -> e+2)`: 100, 102, 104 ...
- **Purity** of function is important for lazy evaluation. They should not rely on outside state or modify external state. In other words they should have no side effects
- Terminal operations **"reduce"** has two forms:
  - reduce and collect
- Collector<T, A, R>:
  - T: object that you are dealing with
  - A: accumulator
  - R: combines different accumulator
- `groupingBy(Function<T, R> classifier, Collector<T,A,R> downstream)`: this recessiveness becomes powerful.

  - **classifier**: function that provides label of mappings
  - **downstream**: collector which is stored as value of each mapping

- mapping(Function mapper, Collector downstream):
  - mapper: maps an input to some other thing
  - downstream: is where the other things are stored
- both `partitioningBy`, `groupingBy`, `mapping`, take a function and a collector i.e that apply function on input and then pass it down to another collector
- `collectingAndThen(Collector Function)` is a different. It applies a function to a collector

- `partitioningBy(Predicate predicate)`: groups stream in two different partitions. It is essentially `groupingBy` with **true/false** as labels
  - counting():
- maxBy and minBy
- **map** vs **mapping**
  - map: used when doing transformation in the stream
  - mapping: used when doing transformation in the middle of the reduce
- **filter(predicate)** vs **filtering(predicate, collector)**:

  - filter: used in stream
  - filtering: used in reduce

- `flatMap(Function<T, Iterator<R>)`: First maps and then flattens it as in `flatMap(e -> List.of(e-1, e+1).stream())` which maps e to {e-1, e+1} but then flattens to 'e-1, e+1'

- **reduce** -- `(Identity, Accumulator, Combiner)`: sum, max, min, reduce, collect
- **collect** -- `(Collector)`: each collector itself takes another collector
- Collectors: **toList**, **toSet**, **toMap**, **toUnmodifiableList**, ..., -- `()`: are some inbuilt collectors
- **partitioningBy**, **groupingBy** -- `(Function Collector)`: groups things into different buckets
- **mapping**, **flatMapping**, **filtering** -- `(Function Collector)`: Do same thing as `map`, `flatMap` and `filter` except they are using with in an reduce operation
- **collectingAndThen** -- `(Collector Function)`: Accepts a collector and performs some operation on it. Like flattening it extracting its size.
- **teeing** -- `(Collector, Collector, BiOperator)`: combines two collectors into one.

- Both filter and map stay within their swimlanes
  - **filter**: filter of`Stream<T>` takes `Predicate<T>` as argument
  - **map**: transforms values. map of `Stream<T>` takes `Function<T, R>` to return `<Stream<R>`
- reduce cuts across the swimlanes. `Stream<T>` **reduce** takes following three parameters:
  - parameter 1 -- `T` (identity)
  - parameter 2 -- `BiFunction<R, T, R>` (accumulator) to produce result of type `R` by accepting an element of type `T` from stream and return value of last BiFunction of type `R`
  - parameter 3 -- `BinaryOperator<R, R>` (combiner) to combine to results of two accumulators into a single. Used especially in parallel streams

```java
      filter    map       reduce
 --------------------- (initial = 0)-------
x1      |                   \                     (swimlane)
-----------------------------\
x2     ->        x2`          +                   (swimlane)
-------------------------------\
x3      |                       \                 (swimlane)
---------------------------------\
x4      ->       x4`              +
                                   \
                                   (gives some concrete value)
```

- **Example of mapping and comparing**

```java
// e.g output: {"Junaid":[12, 14, 2], "Mashroof":[7]}
// first arg of grouping asks it to make buckets with label person.getName() for each element
// mapping is applied to each element and extracts age from person and gives that to another collector toList
people.stream()
      .groupingBy(Person::getName, mapping(Person::getAge, toSet()));

people.stream()
      .collect(minBy(Comparator::comparing(Person::getAge)))
```

- **Example of collectingAndThen**

```java
// before the count in bucket. Modify the collector and then put
people.stream()
      .groupingBy(Person::getName, collectingAndThen(counting(), Long::intValue));
```

- **Example of teeing**

```java
var employeeList = Arrays.asList(
                      new Employee(1, "A", 100),
                      new Employee(2, "B", 200),
                      new Employee(3, "C", 300),
                      new Employee(4, "D", 400));

// result = {MAX: Employee{id=4, salary=400}, MIN: Employee{id=1, salary=100}}
var result = employeeList.stream().collect(
                        Collectors.teeing(
                          Collectors.maxBy(Comparator.comparing(Employee::getSalary)),
                          Collectors.minBy(Comparator.comparing(Employee::getSalary)),
                          (e1, e2) -> {
                            HashMap<String, Employee> map = new HashMap();
                            map.put("MAX", e1.get());
                            map.put("MIN", e2.get());
                            return map;
                        }
));

```

- **Different Reduce example**

```java
people.stream()
      .map(People::getName)
      .reduce(new ArrayList<>(),   // identity: initial value
              (names, name) -> {   // accumulator: adds the incoming elements
                names.add(name);
                return names;
              },
              (names1, names2) -> { // combiner: combines two values (one use case is in parallel streams)
                names1.addAll(names2);
                return names1;
              });

// same as above
people.stream()
      .map(People::getName)
      .collect(Collectors.toList());

// split people in two different collections with true and false labels
Map<Boolean, Person> = people.stream()
      .filter(person -> person.getAge() % 2 == 0)
      .collect(Collectors.partioningBy(person -> person.getAge() % 2 == 0));

// split people into multiple buckets
people.stream()
      .collect(Collectors.groupBy(person -> person.getName()));
```

## Object Ordering

- `Collections.sort(list, comparator)` or `Collections.sort(list)` (if list items implement `Comparable`) for sorting items of collection

## Map

- Implementations:
  1. **HashMap**: Implemented via hash table, no ordering on keys or values
  2. **TreeMap**: Implemented via red-black tree structure, ordered by key
  3. **LinkedHashMap**: preserves the insertion order
  4. **Hashtable**: synchroniszed map

### TreeMap

- Red-black tree is specific type of BST
  - nodes are either red or black
  - root and leaves are black
  - if node is red then children are black
  - all paths from a node to leaves contain same number of black nodes
  - Search O(logn)
  - Insert O(logn)
  - Remove O(logn)
  - Space O(n) (color of tree takes 1 bit)
