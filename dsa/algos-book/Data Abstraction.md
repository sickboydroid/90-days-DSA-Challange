# Data Abstraction

1. **Static Method:** Primary use -> as functions, i.e compute and return the value
2. **Instance Method:** Primary use -> as data-type operation, i.e examine or change value/state of object

---

1. **Assignment Operation**:
   - Primitive types -> Copy value.
   - Reference types -> Copy reference.
2. Passing arguments to method is equivalent to assignment operation
3. **Arrays** are objects
   - As with strings, java provides special language support for arrays
   - Arrays hold *references* and not *values* -> easily movable

---

1. **Data-type**: Set of values and operations on them
2. **Object/Instance:** It is a result/instance of a class. Classes are like blueprint of objects
3. **Abstract data type (ADT)**: a data type that is defined in terms of its behavior rather than its implementation.
   - For example a Stack. You know the operations (push, pop) but you don't know the implementation (arrays, linked list)
   - This means that an ADT specifies what operations can be performed on its values, but not how those operations are implemented
   - **Concrete data type (CDT)**: Opposite of ADT. Implementation details of how the data is stored in contiguous memory locations is visible to the programmer.
     - If you are creating *public* instance variables then you are exposing your data and thus your data type is not an ADT
     - However it creates a dependence on particular implementation

---

Java and Pointers:
    1. In java there is only one way to create a reference (**new** keyword) and only one way to change a reference (**assignment** statement)
       - Java references are known as *safe pointers*

## Object class

1. `hashCode()`: Calculates a number based on object.
   1. If ob1.equals(ob2) then obj1.hashCode() is same as obj2.hashCode()
   2. However if obj1.equals(obj2) is false then hash codes may or may not be same
2. `toString()`: getClass().getName() + "@" + hashCode();
3. `obj1 == obj2`: Will check if both the variables refer to the same object
4. `compareTo(Object other) from Comparable interface`: Negative value if **current** object is less than other
   - Should be consistent with equals method
   - Use in-built compare methods like `Integer.compare(num1, num2)`
   - Prioritize comparing the most important fields for natural ordering in the class-specific **compareTo** method

```java
// New way of using compareTo
@Override
public int compareTo(Transaction that) {
    return Comparator.comparing(Transaction::amount)
            .thenComparing(Transaction::when)
            .thenComparing(Transaction::who)
            .compare(this, that);
}
```

## String

- MISC: ADT, **concat(String s)**
- `compareTo(str)` compares strings lexicographically. Bigger the magnitude, lesser is the argument lexicographically

## ADTs

Three parts of ADT:

1. *Instance variables* -> *private* -> represent the state of data type
   - If you have *public instance variables* then it is not, by definition, *abstract* and thus not *ADT*
2. *Constructors* -> *public* -> Create object and change default values of instance variables if needed
3. *Instance methods* -> *public* -> represent the behavior of each object 

Variable Kinds:

1. **Parameter variables**: Scope -> entire method
2. **Local variables**: Scope -> Block in which declared
3. **Instance variables**: Scope -> Entire class. *this* keyword for accessing them if ambiguity

Developing an ADT:

1. *Specify an API:* How your ADT will operate. What methods will you use. Applications etc.
2. *Implement a Java class:* First instance methods then constructors and then instance methods
3. *Test clients:* For validation of api. Each instance method must be called at least once
