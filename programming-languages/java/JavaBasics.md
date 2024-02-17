# Java Basics

- [Java Basics](#java-basics)
  - [Definitions and MISC](#definitions-and-misc)
  - [Variables and Literals](#variables-and-literals)
  - [Arrays](#arrays)
  - [Operators](#operators)
  - [break and continue Statement](#break-and-continue-statement)
  - [Classes](#classes)
    - [Access Level Modifiers](#access-level-modifiers)
    - [Class Members](#class-members)
    - [Nested Classes](#nested-classes)
  - [Enum](#enum)

## Definitions and MISC

- **Software Objects:** Conceptually Similar to real-word objects
  - Expose behavior via **methods** and store state in **fields**
  - Instance of a class & Object are same thing
- **Class:** A class is the blueprint from which individual objects are created
- **Data Encapsulation:** Hiding internal state and requiring all interaction to be performed through an object's method.
- **Interface:** A group of related methods with empty bodies
- **Statically-typed** means type of variables is known at compile type
- **synthetic constructs** allow java compilers to implement new java language features without changes to the JVM.
  - Remember weird inner class names? Those are syndetic constructs created by java compiler from source code.

---

- `switch` supports strings as cases as well. Only make sure that input string is not null

## Variables and Literals

- **Type of variables:** _variable_ means any type of variable. _Field_ means instance or class variables only. Also type's fields, methods, and nested types are collectively called its **members**
  1. **Instance Variables** (Non-Static Fields)
  2. **Class Variables** (Static Field)
  3. **Local Variables** (only variable and not field)
  4. **Parameter** (only variable and not field)
- Eight primitive types:
  1. **byte** 8-bit [-128, 127]
  2. **short** 16-bit [-32768, 32767] or [-2^15, 2^15 - 1]
  3. **int** 32-bit [-2.1B, 2.1B]. For unsigned version using Integer class
  4. **long** 64-bit [-2^63, 2^63 - 1]
  5. **float** 32-bit complicated range
  6. **double** 64-bit complicated range
  7. **boolean** represents 1 bit of data but size isn't precisely defined
  8. **char** single 16-bit unicode character. [0, 65,535]
- **Literal** is the source code representation of a fixed value. It is possible to assign literal to a variable of primitive type. Strings are actually objects but one may thing of them as primitives.

  - _Integer literal_ can expressed in decimal, hexadecimal or binary number system
  - _Integer literal_ is of type long if it ends with `L` or `l`
  - _Floating-point literal_ is of type float if it ends with `F` or `f`.
  - Floating-point types can be expressed using `E` or `e` notation as well
    - `1.234e2` and `123.4` are same
  - Special escape sequences for char and String literals are:

    - `\b` (backspace) `\f` (form feed), `\r` (carriage return), `\"` (double quote) and so on

  - `null` is also a literal
  - **Class literal** is formed by taking a name and appending **.class** like `boolean.class`, `myObject.class`. This refers to the object (of type Class) that represents the type itself
  - From java 7, **underscore** can appear any where b/w digits of numeric literals like `1234_5678`, `3.14_15F`, `0xFF_01_33`.

## Arrays

- Syntax `int[] nums = {1,2,3}` can be used only while declaring and initializing an array at the same time. It cannot be used while returning or assigning value to already declared array etc. There you have to use `new int[] {1,2,3}` syntax.
- `System.arraycopy(src, srcpos, dest, destPos, length)` can be used to copy arrays efficiently as it is a native method.
- Useful methods of `Arrays` class:
  - `equals(arr1, arr2)`
  - `binarySearch(arr, target)`
  - `fill(arr, value)`
  - `stream(arr)`

## Operators

- The operators in the following table are listed according to precedence order. The closer to the top of the table an operator appears, the higher its precedence. Operators on the same line have equal precedence.
- `==` for equality of primitives and enum constants
- `instanceof` is for type comparison. It is used to test if an object is an instance of a class, an instance of a subclass, or an instance of a class that implements a particular interface.
  - Since generics are only compile time things, `obj instanceof Collection<?>` and `obj instanceof Collection` are same. Also `obj instanceof Collection<String>` is incorrect

```java
-----------------------------------------
postfix                 expr++ expr--
unary                   ++expr --expr +expr -expr ~ !
multiplicative          * / %
additive                + -
shift                   << >> >>>
relational              < > <= >= instanceof
equality                == !=
bitwise AND             &
bitwise exclusive OR    ^
bitwise inclusive OR    |
logical AND             &&
logical OR              ||
ternary                 ? :
assignment              = += -= *= /= %= &= ^= |= <<= >>= >>>=
-----------------------------------------
```

## break and continue Statement

- `break` and `continue` statements have two forms: labeled and unlabeled

```java
// labelled break example
outer:
for(int i = 0; i < 5; i++) {
    System.out.println("going in"); // printed once
    for(int j = 0; j < 5; j++)
        break outer;
    System.out.println("going out"); // never reached
}

// labelled continue example
outer:
for(int i = 0; i < 5; i++) {
    System.out.println("going in"); // printed 5 times
    for(int j = 0; j < 5; j++) // always iterates once only
        continue outer;
    System.out.println("going out"); // never reached
}
```

## Classes

- `Method signature` is comprised of two components: `method's name` and `parameter types`.
- `Method overloading` means that methods within the same class can have the same name if they have different parameter lists.
- **Parameters** refer to the list of variables in a method declaration while as **arguments** are the actual values that are passed in when the method is invoked.
- `varargs` (e.g print(String... strs)) construct can be used to pass an arbitrary number of values to a method. It is just a shortcut of creating an array.
- An object is eligible for garbage collection when there are no more references to that object. References that are held in a variable are usually dropped when the variable goes out of scope or you can explicitly drop an object reference by setting the variable to the special value null.
- Overridden method can return the subclass of whatever original superclass method was returning. This technique is called **covariant return type** and it means that the return type is allowed to vary in the same direction as the subclass.

  - You also can use interface names as return types. In this case, the object returned must implement the specified interface.

- If **A** class cannot call constructor of **B** class then it cannot directly create **B** objects. However you can provide some factory methods in **A** to allow creation.
- `this` is a reference to the current object. From within a _constructor_ it can be used to call another constructor of the same class. Calling constructor explicitly this way is called an **explicit constructor invocation**
  - If present, the invocation of another constructor must be the first line in the constructor.

```java
public class Main {
  public Main(int a) {
    // You cannot use Main(a, a*a)
    this(a, a*a);
  }

  public Main(int a, int b) {
    ...
  }
}
```

### Access Level Modifiers

- Outer classes can have two types of access modifiers
  - `public class` is visible to all classes everywhere
  - `(no-modifier) class` is visible within its own package. It is also called package-private
- `private` fields are accessible only within its own class
- At member level you can have four types of access modifiers:
  - `public` fields can be accessed by any object that can access the class
  - `protected` modifier specifies that the member can only be accessed within its own package and by subclass in another package
  - `no modifier` implies that member can be accessed by any class in the same package
  - `private` specifies that the member can only be accessed in its own class.
    - `private` modifier can be used for **Nested classes** as they are members

> NOTE: Outer classes can be `public` or `package private` only while as nested classes (being members) can be `private`, `public`, `protected`, or `package private`

### Class Members

- **static members** belong to a class rather than to the instance of class. For this reason, **static fields** are also called **class variables**
- If you need to use logic for initializing class variables then you can use **static-initialization blocks** or **private static methods**. The former (SIB) are called in the order they appear in the source code (See example).
- Similarly if you need to use logic for initializing instance variables then you can use **initializer blocks**, **final methods** or **constructors**
  - At compile time, all initializer blocks are copied into every constructor of your class. Thus they are a way to share code b/w multiple constructors.
  - `final` methods cannot be overridden by subclass

> Note: `private final void method()` and `private void method()` are both equivalent as subclass cannot private methods so using final is of no use

- Lifecycle of class

```java
public class Test {
    static { System.out.println("static-initializer block 1"); }

    final static int staticSum = findSum();

    static { System.out.println("static-initializer block 2"); }

    { System.out.println("instance block 2"); }

    final int instanceSum = findSum2();

    { System.out.println("instance block 1"); }

    public Test() { System.out.println("constructor 1"); }

    private static int findSum() {
        System.out.println("in static method");
        return 29;
    }

    protected final int findSum2() {
        System.out.println("in final method");
        return 29;
    }
}

/**
========> output of new Test() <==========
static-initializer block 1
in static method
static-initializer block 2
instance block 2
in final method
instance block 1
constructor 1
==========================================
**/
```

### Nested Classes

- Nested classes are divided into two categories:

  1. **Non-static** nested classes are called **inner classes**. It is associated with an instance of its enclosing class.

     - Instance of inner class exists within an instance of the outer class i.e to instantiate inner class we must first instantiate the outer class (see example)
     - It cannot define any static members. It is because inner classes are associated with an instance of its enclosing class so it might create confusion about which instance of OuterClass that static variable belongs to.
     - Serialization of inner classes is strongly discouraged. It is because of something called **synthetic constructs** (see [Definitions](#definitions-and-misc))
     - Two special types of inner classes:
       1. **Local classes**: classes declared within a method body
          - Local class and anonymous classes can only access local variables that are declared final or effectively final (i.e value does not change of initialization)
       2. **Anonymous Classes** classes declared within a method body without name
          - An anonymous class definition is an expression and thus must be a part of statement.
          - It cannot have a constructor

  2. **static nested classes** is another type of nested class

- If a declaration of a type in a particular scope (such as an inner class or a method definition) has the same name as another declaration in the enclosing scope, then the declaration shadows the declaration of the enclosing scope. You cannot refer to a shadowed declaration by its name alone. (see example below)

```java
public class OuterClass {
  int x = 0;
  public class InnerClass {
    int x = 1;
    void shadowTest(int x) {
            System.out.println(x); // outputs passed x
            System.out.println(this.x); // output 1
            System.out.println(OuterClass.this.x); // outputs 0
        }
  }

  public static class StaticNestedClass {}
}

OuterClass outer = new OuterClass();
OuterClass.StaticNestedClass staticNested = new OuterClass.StaticNestedClass(); // instantiating Static nested class
StaticNestedClass staticNested = new StaticNestedClass(); // if you used static import
OuterClass.InnerClass inner = outer.new InnerClass(); // instantiating inner class
```

## Enum

- enum is a special class that represents a group of constants.
  - These constants are nothing but instances of that class. At a time only one instance of constant exists in JVM so you can use == operator for comparison.
  - enum cannot extend other classes as it implicitly extends java.lang.Enum but it can implement interfaces.
  - You can override methods for each individual enum constant. However if you create a method (e.g uniqueMethod in example below), you won't be able to call it outside that constants scope because you will only be able to get instance of Direction and not LEFT.
- The enum class body can include methods, fields, classes etc just like ordinary class. It can have abstract methods which (as expected) must be implemented by all constants.
  - ordinal() method of Enum returns index (starting from 0) of enum constant as defined in source code
  - values() method of Enum returns array containing all values in order they were declared.
  - Constructor of enum must be either private or package-private. We cannot call the enum constructor, it is automatically called when you create constants
- Another special thing about enums is that they can be used in switch case statements

```java
Direction.LEFT.isHard(); // true
Direction.RIGHT.isHard(); // false
Direction.LEFT.getHardnessLevel(); // 1
Direction.RIGHT.getHardnessLevel(); // 0 i.e default value
Direction.LEFT.uniqueMethod(); // Compile Error because Direction has no uniqueMethod even though LEFT has
Direction.staticMethod(); // staticMethod belongs to class and not instances
Direction.valueOf("LEFT"); // returns Direction.LEFT
Direction.LEFT.toString(); // returns "LEFT"
//Direction.LEFT.id = 13; // making fields final prevents these kind of things!!
Direction.LEFT.id; // is 1
Direction.RIGHT.id; // is 2

public enum Direction {
  LEFT(1) {
      final int id = 12; // only accessible in this scope Direction.LEFT.id will still be 1

      // abstract method must be overridden
      @Override public boolean isHard() { return true; }

      // methods can be overridden
      @Override public int getHardnessLevel() { return 1; }

      // Cannot be called from outside this scope as you will always get instance of Direction.
      public void uniqueMethod() {}
  },

  RIGHT(2) {
      @Override public boolean isHard() { return false; }
  };

  public final int id;
  private final int hiddenField = 12; // cannot be accessed outside this class

  public Direction() {} // compile error
  Direction(int id) {this.id = id;}

  public abstract boolean isHard();
  public int getHardnessLevel() { return 0; }
  public static void staticMethod() {}
}
```

```java
Direction direction = direction.LEFT;
switch(direction) {
  case LEFT: // No need to use Direction.LEFT
     break;
  case RIGHT:
     break;
}

```
