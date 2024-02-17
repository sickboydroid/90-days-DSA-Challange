# OOP Concepts

- [OOP Concepts](#oop-concepts)
  - [Definitions](#definitions)
  - [Variable Scope](#variable-scope)
  - [Class Objects](#class-objects)
  - [Instance Objects](#instance-objects)
  - [Public, Protected and Private Names](#public-protected-and-private-names)
  - [Extras](#extras)
  - [Dataclasses](#dataclasses)
  - [Outside Doc (from corey)](#outside-doc-from-corey)
    - [`__dict__` and type of methods](#__dict__-and-type-of-methods)
    - [Dunder/Magic Methods](#dundermagic-methods)

## Definitions

- [Official doc that explains classes, namespaces, score, attributes, and many more things](https://docs.python.org/3/tutorial/classes.html#python-scopes-and-namespaces)
- **Aliasing** (pass by reference i.e only changes name but object is same) can be ignored for immutable types like _strings_, _numbers_ etc but has a very different effect on mutable objects like _lists_
- A **namespace** is a mapping from names to objects.
  - Usually implemented as python dictionary. There is absolutely no relation between names in two different namespaces
  - They are created at different times and have different life spans.
  - `built-in` namespace is created when interpreter is first started and is never deleted. `built-in` names live in **builtins** module
  - `global` namespace for a module is created when module definition is read in and generally also lasts until interpreter quits
  - Statements executed by the top-level invocation of the interpreter are considered to be part of a module called `__main__`. This means all names you define at global level will be in a namespace named `__main__`
- An **attribute** is simply a name following a dot. e.g `module.funcname`, `obj.data`, `obj.method`, `class.num`, `class.func`
  - They can be read-only or writable
  - Module attributes are writable
  - Writable attributes can be deleted with the del keyword

## Variable Scope

- **Scope** is a textual region where a namespace is directly accessible
  - Assignments and deletions of names always go into **innermost scope**. Same for deletions
  - Import statements and function def bind the module or function name in the current local scope
- Unqualified names are searched in order `LEGB`: **Local**, **Enclosing**, **Global**, **Built-in**
  - innermost scope contains local names
  - scopes of enclosing functions is searched next
  - next-to-last scope is current module's global names
  - outermost scop is built-in names and is searched at last
- **global x** tells python that use global namespace for x. So reassignment, modification, deletion, etc all happen in global namespace and not the local namespace. x does not need to be defined in global scope.
- **nonlocal x** tells python to use namespace of enclosing function for **x**. **x** needs to be defined in enclosing scope (even after the inner function!).
- If we ignore `global and nonlocal` statements then names outside innermost scope are readonly and if you attempt to write readonly names, a new variable in the innermost scope is created.

```python
##############
# Example: searching order of name in namespaces
##############
x, y = 'global x', 'global y'
def outer():
    global x, y
    x= 'local x'
    # test func is enclosing inside_test func. So python will search the names in the order:
    # Local (i.e outer) namespace -> Enclosing (i.e inner) namespace -> Global (i.e __main__) namespace -> Builtin namespace
    def inner():
        global x
        nonlocal z
        x, y, z = 'enclosing x', 'enclosing y', 'enclosing z'
        print(x, y, z)
    z = 'local z'
    inner()
    print(x, y, z)

outer()
print(x, y)

# output
"""
enclosing x enclosing y enclosing z
enclosing x global y enclosing z
enclosing x global y
"""
```

## Class Objects

- Class definitions (`class` statements) and Function definitions (`def` statements) are executed before they have any effect
  - When a class definition is entered a new local namespace is created. Names from function definitions and all assignments to local variables go into this new namespace
- When class definition is left normally at the end, **class object** is created. It is basically a wrapper around the contents of the namespace created by the class definition. It is bound to the scope which was in effect just before class definition was entered. Thus you can use `ClassName.method(???)` without even creating an object, by just importing the class
- Class Objects support two types of **operations**:
  - **attribute referencing**: e.g `ClassName.pi` and `ClassName.func`. Class attributes can also be re-assigned
  - **instantiation**: Calling a class object returns a new **class instance** (sometimes simply called _instance_)

## Instance Objects

- (Assume **x** to be instance of **MyClass** which itself a class object)
- Instance objects provide a single operation:
  - `attribute referencing`: e.g **x.age**, **x.method**
  - Attribute names can of two types: `data attributes` and `methods`. A method is a function that belongs to an object (not specifically instance of class object)
- There is no shorthand for accessing the object's members from its methods. (that is why **self** is first parameter passed to the object)
- `MyClass.func` is a function object and `x.func` is a method object
- The difference b/w method objects and function objects is that instance object to which the method belongs is always passed as the first argument to the method objects. Thus x.f() is same as MyClass.f(x)
- So when non-data attribute of an instance is referenced following things happen internally:
  - First we search for the instance's class
  - Then we check if requested attribute is a valid function name.
  - If it is we create a new object with both function and a reference to the object that called it. This new object is **method object**. It is an abstract object (no implementation only definition)
    - `methodobject.__self__` will give the instance
    - `methodobject.__func__` will give the function
- **Class Variables** are shared by all **instance objects** while as instance variables only belong to a particular instance of a class object

## Public, Protected and Private Names

- **Name mangling**: An identifier of the form `__spam` (at least two leading underscores and at most one trailing underscore) is replaced by `_classname__spam` (any leading underscores are stripped away from class name). Identifier can be anywhere in the class definition
- Names like `_spam` should be treated as a non-public part of the API
- In nutshell, `spam` is **public**, `_spam` is **protected** and `__spam` is **private**

```python
# Example: Name mangling
class Foo:
  __fox = 'i am fox'
  def __bar_():
    print('bar called')
  def __spam__():
    print('spameggs')


print(Foo._Foo__fox) # name mangling in action
Foo.__spam__()       # not in action
Foo._Foo__bar_()     # in action
```

## Extras

- Data attributes can be accessed by methods as well as clients i.e classes are not usable to implement pure abstract data types
- In inheritance we sometimes want to call the method of base class inside the derived class. However we might not be able to do that directly because of overriding. So you can `BaseClassName.method(self, args)` to directly call the base class's method. This can be used by clients as well
- To work with inheritance we have to builtin methods:
  - `isinstance(obj, class)` to check an instance's type
  - `issubclass(subclass, class)` to check class inheritance
- Python supports multiple inheritance i.e `DerivedClassName(Bas1, Base2, ...)`
- You use `DerivedClassName(module.Base1, module.Base2,...)` as well
- MRO is a little complex, ([See here](https://www.python.org/download/releases/2.3/mro/)). However in nutshell, MRO is depth-first, left-to-right, not searching twice in the same class (in case there is an overlap in hierarchy).

## Dataclasses

TODO

## Outside Doc (from corey)

### `__dict__` and type of methods

- `obj.__dict__` is the namespace of an object i.e it is the place (dictionary) where instance variables are stored
- Methods that take first parameter as class are called **Class Methods**
  - One application is that you can use class methods as alternative constructors as show in example. By convention, the names of these constructors starts with **from**
- Methods that do not take class object or instance object as their argument are called **Static Methods**

```python
# Example of using self.__dict__, setattr and getattr
class Book:
    pages = 12 # not in __dict__ as it is not an instance variable
    year = 2007 # same as above
    def __init__(self):
        self.lost = 'This will be lost because of 2nd line'
        self.__dict__ = {'name': 'Harry Potter'}
        self.__dict__['rating'] = 8

    def info(self):
        if self.func:
            self.func()
        return f'Book: {self.name}\nAuthor: {self.author}\nRating: {self.rating}\nPages: {Book.pages}\nYear: {self.year}'

harry_potter = Book()
harry_potter.author = 'J.K. Rowling'
# Here the name of attribute is not valid. It cannot be accessed it with dot notation
harry_potter.__dict__['No. Books'] = 7
setattr(harry_potter, 'Genre', ['fantasy', 'adventure'])
setattr(harry_potter, 'func', lambda: print('I am func! Func was created dynamically'))

# First it will check for harry_potter in instance and then its class and then super class
if hasattr(harry_potter, 'pages'): print('YEP')
if hasattr(Book, 'pages'): print('YEP')
print(harry_potter.info())

```

```python
# Example of static and class method
class Employee:
    def __init__(first, last):
        self.first = first
        self.last = last

    def email(self):
        return self.first + self.last + '@' + 'gmail.com'

    # acts as an alternative constructor
    @classmethod
    def from_string(cls, emp : str):
        first, last = emp.split('-')
        return cls(first, last)

    @staticmethod
    def is_workday(day : int):
        return day % 7 == 0:

emp_data = 'Sick-Boy'
# instantiate using ordinary constructor
first, last = emp_data.split('-')
emp = Employee(first, last)

# instantiate using alternate constructor
emp = Employee.from_string(emp_data)

# static methods don't take any class or instance as their first parameter
emp.is_workday(12)
Employee.is_workday(12)

```

### Dunder/Magic Methods

- These methods help us to emulate built-in behavior of python and also enable us to do operator overloading. They are surrounded by double underscores. `__init__` is also called **dunder init method**
- **Some Dunder Methods:**
  - `__repr__()`: is meant to be an unambiguous **repr**esentation of object
    - Return something which can be copied and pasted to create same object in python
    - Used for debugging and mostly used by developers
  - `__str__()`: is meant to be the readable form of an object
    - Used for end user
    - If dunder str is not implemented, dunder repr will be used instead
  - `__len__()`: is supposed to return length of data

```python
class Fox:
    def __init__(self, name):
        self.name = name

    @property
    def name(self):
        return self.name

    @name.setter
    def name(self, name):
        self.name = names

    @name.deleter
    def name():
        print('Deteling Name!')
        self.name = None

fox = Fox('moulder')
print(fox.name) # getter called
fox.name = 'foxy' # setter called
del fox.name # deleter called
```
