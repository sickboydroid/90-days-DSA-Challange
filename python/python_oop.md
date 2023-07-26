# OOP Concepts

- `obj.attribute` will first check if the instance of class has this attribute and then check if the class has this attribute
  - Inside the class, if you have class variables you can either access them using self (i.e instance) or class name. The former will allow user to give their own value to the attribute
- `obj.__dict__` is the namespace of an object i.e it is the place (dictionary) where instance variables are stored
- Methods that take first parameter as class are called **Class Methods**. On application is that you can use class methods as alternative constructors as show in example. By convention, the names of these constructors starts with **from**
- Methods that do not take class or instance as their argument are called **Static Methods**
- `isinstance(obj, class)`: returns true if obj is an instance of $class$ or any parent of $class$
- `issubclass(subclass, class)`: returns true if $subclass$ is subclass of $class$

```python
############################
# Example of using self.__dict__, setattr and getattr
############################
class Book:
    pages = 12 # not in __dict__ as it is not an instance variable
    year = 2007 # ...
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
# see the name of attribute, invalid?? You cannot access it with dot notation
harry_potter.__dict__['No. Books'] = 7
setattr(harry_potter, 'Genre', ['fantasy', 'adventure'])
setattr(harry_potter, 'func', lambda: print('I am not a method, but an attribute!!!'))

# First it will check for harry_potter instance and then its class and then super class
if hasattr(harry_potter, 'pages'): print('YEP')
if hasattr(Book, 'pages'): print('YEP')
print(harry_potter.__dict__)
print(harry_potter.info())

############################
# Example of alternative constructor and a static method
############################
class Employee:
    def __init__(first, last):
        self.first = first
        self.last = last

    def email(self):
        return self.first + self.last + '@' + 'gmail.com'

    @classmethod
    def from_string(cls, emp : str):
        first, last = emp.split('-')
        return cls(first, last)

    @staticmethod
    def is_workday(day : int):
        return day % 7 == 0:

emp_data = 'Junaid-Ashraf'
# instantiate using oridinary constructor
first, last = emp_data.split('-')
emp = Employee(first, last)

# instantiate using alternate constrcutor
emp = Employee.from_string(emp_data)

# static methods don't take any class or instance as their first parameter
# that is what @staticmethod decorator helps us to filter
emp.is_workday(12)
Employee.is_workday(12)

```

## Dunder Methods

- These methods help us to emulate built-in behaviour of python and also enable us to do operator overloading
- These methods are surrounded by double underscores. **init** is also called dunder init method
- `__repr__()`: called when we run repr() on object
  - Meant to be an unambigious repr of object
  - Used for debugging and mostly used by developers
  - return something which can be copied and pasted to create same object in python
- `__str__()`: called when we run str() on object
  - Meant to be for end user
  - Readable form of object
  - if dunder str is not implemeneted, repr will be used instead
- thus **repr** and **str** are the two dunder methods that change how our classes/objects are displayed
- `__add__()`, `__sub__()`, `__mul__()`, `__eq__()` and so on are dunder methods for operator overloading/emulating airthmetic
- `__len__()` is another dunder method

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

## python doc

- Each class instance have attributes attached to it for maintaining its state and it has methods for modifying its state
- They are created at runtime and CAN BE modified even after their creation
- Classes are themselves objects
- **i think** python calls names as references
- Aliasing can be ignored for immutable types like stirngs numbers etc but has a very different effect on mutable objects like lists
  - because python is pass-by-reference
- There is no shorthand for accessing the object's memebrs from its methods. (that is why self is first parameter)
- Everything is public but `_spam` is by convention private
- **namespace** is a mapping from names to objects. Mostly implemented as python dict. e.g built-in names, global names in module and local names in a function and set of attributes of an object. There is absolutely no relation between names in two different namespaces
  - Created at different times and have different life spans
  - built-in namespace is created when interpreter is first started and is never deleted
  - global namespace for a module is created when module definition is read in and normally they last until interpreter quits
- **attribute** name following a dot. module.funcname, here funcname is attr of module. obj.data, here attr is data
  - They can read-only or writable
  - Module attributes are writable
  - Writable attributes can be deleted with the del keyword
- Statements executed by the top-level invocation of the interpreter are considered to be part of a module called `__main__`. This means that any vars you create, methods you define will be in a namespace named `__main__`
- Buit-in names live in builtins module
- **Scope** is a textual region where a namespace is directly accessible
- There beauitiful article [fuck me](https://docs.python.org/3/tutorial/classes.html#python-scopes-and-namespaces)
- Assignments to names always go into innermost scope. Same for deletions, `del x` will remove the binding of x from innermost scope. Import statements and func def bind the module or function name in the local scope
- Variables can be declared in 3 different scopes:
  - local: inside func
  - global: outside func
  - non-local: nested func

```python
name = 'sickboy' # global
def hello():
    num = 5 # local

```

## Variable Scope

- LEGB: Local, Enclosing, Global, Built-in
  ^ ^ ^ ^
  functions nested-fns global in-lang
  keyword/ module
- innermost scope: contains local names
- scopes of enclosing functions:
- next-to-last scope: current module's global names
- outermost scop: built-in names
- **global x** tells python that use global namespace for x. So reassignment, modification, deletion, etc all happen to enclosing namespace and not the local namespace. x does not need to be defined in global scope.
- **nonlocal x** tells python that use namespace of enclosing function for x. X need to be defined in enclosing scope.
- names outsode an enclosing function are readonly. If you attempt to write them a new local variable in the innermost scope is created
- Class definitions (`class` statements) and Function definitions (`def` statements) are executed before they any effect
  - when a class def is entered a new local namespace is created. Names from function definitions and all assignments to local variables go into this new namespace
- when class definition is left normally at the end, `class object` is create. It is basically a wrapper around the contents of the namespace created by the class definition. It is bound to the scope which was in effect just before class definition was entered with name `ClassName`. Thus you can use `ClassName.method(???)` without even creating an object, by just importing a class

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

- Class Objects support two types of operations:
  - `attribute references`: obj.pi and obj.method, returning integer and a function respectively. Class attributes can also be reassgined
  - `instantiation`: Calling a class object returns a new instance of the class

## Instance Objects

- (Assume x to be instance object or class instance or class instance object of MyClass which is a class object)
- Instance objects a single operation:
  - `attribute referencing`
- Kinds of valid attribute names: `data attributes` and `methods`. A method is a fucntion that belongs to an object (not object to class instances)
- `MyClass.f` is a function object and `x.f` is a method object
- The difference b/w method objects and function objects is when called. Instance object to which the method belongs is always passed as the first argument to the method objects. Thus x.f() is same as MyClass.f(x)
- So when non-data attribute of an instance is referenced, first we search for the instance's class and then check if attribute is a valid function name. If it is we create a new object with both function and a reference to the object that called it. This new object is method object. It is an abstract object. So there is no implementation but only definition.
  - `methodobject.__self__` will give the instance
  - `methodobject.__func__` will give the function

## Class and Instance Variables

- Class Vars are shared by all instance objects
- Instance variables only belong to a particular instance of a class object

## Footnotes

- Data attributes can be accessed by methods as well clients i.e classes are not usable to implement pure abstract data types
- In heritance we sometimes want to call the method of base class rather than derived class inside the derived class. However we might not be able to do that directly because of overriding. So you can `BaseClassName.method(self, args)` to directly call the base class's method. This can be used by clients as well
- To work with inheritance we have to builtin methods:
  - `isinstance(obj, class)` to check an instance's type
  - `issubclass(subclass, class)` to check class inheritance
- Python supports multiple inheritance i.e `DerivedClassName(Bas1, Base2, ...)`
- You use `DerivedClassName(module.Base1, module.Base2,...)` as well
- MRO is a little complex, ([See here](https://www.python.org/download/releases/2.3/mro/)). In nutshell, MRO is depth-first, left-to-right, not searching twice in the same class (in case there is an overlap in hierarchy).

## Private Variables (convention only!!)

- Names like `_spam` should be treated as a non-public part of the API
- **Name mangling**: An identifier of the form `__spam` (at least two leading underscores and at most one trailing underscore) is replaced by `_classname__spam` (any leading underscores are stripped away from class name). identifier can be anywhere in the class definition
- `spam` is public, `_spam` is protected and `__spam` is private
- Anything that can be done using generators can be done using class based iterators
  - Neat things is that `__iter__()` and `__next__()` are automatically created in generators. Also execution state, local variables are automatically saved and resumed without any manual effort contrary to class iterators. Also StopIteration is raised automatically
