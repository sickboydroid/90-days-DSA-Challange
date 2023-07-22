# Python decorators

## First-Class and Higher-Order functions

- A language is said to have **first-class functions** if it treats functions like an ordinary object i.e they can be passed as arguments, assigned to variables and returned from another function
- A **higher-order function** is a function which takes one or more functions as input and returns a new function

## Closures

- Corey Schafer on Closures: [Youtube Video](https://youtu.be/swU3c34d2NQ)
- If you have a class with `__init__()` and one extra method, it is better to use closure for increasing readability and reducing code without compromising efficiency
- **Def:** Closures in python are functions that capture and remember the environment in which they were created i.e a function that accesses variables from its containing (enclosing) function's scope, even after the containing function has finished executing
  - This concept is only applicable for languages which have first-class functions

```python
def add(x, y):
    res = x + y
    def print_res():
        print(res)
    return print_res

res_printer = add(3, 2)
# will print res even thought it is outside of its scope (which was add function that finished a while ago)
res_printer()
```

## Decorators

- **Some applications:**
  1. Logging how many times a function is called
  2. Calculating time took by a function to execute
- In the example below, you can see which decorator will be called first and which will end first. In nutshell:
  - **First** decorator will **start first**
  - **Second** decorator will **start second** and so on till actual function
  - **First** decorator will **end last**
  - **Second** decorator will **end second last** and so on.

```python
# template for function decorator
def decorator(func):
    def wrapper(*args, **kwargs):
        # do something ...
        result = func(*args, **kwargs)
        # do something ...
        return result
    return wrapper

# template for class decorator
class Decorator:
    def __init__(self, func):
        self.func = func
    # equivalent to wrapper method of function decorator
    def __call__(self, *args, **kwargs):
        # do something ...
        result = self.func(*args, **kwargs)
        # do something ...
        return result

# applying decorator
@decorator
@Decorator
def display_snake(size, name='python'):
    print(f'{name} is of {size}m')

# APPLYING DECORATOR IS EQUIVALENT TO FOLLOWING
display_snake = decorator(Decorator(display_snake))

```

- In above example, if you print out the info about **func** in `decorator.wrapper` it will say it is `__call__` function rather than `display_snake`. To overcome this issue, decorate all functions with `functools.wraps(wrapped-function)` decorator. This helps to preserve the info about original function when you apply multiple decorators
