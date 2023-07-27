# Exceptions and Errors

- Out of multiple `except` blocks, a max of one block will be executed
- `BaseException` is the baseclass of all exceptions (even `Exception` class which represents non-fatal exceptions is its subclass)
- Exceptions which are not subclasses of `Exception` are not typically handled as they indicate that program should in-fact terminate
  - Except `BaseExceptionGroup`, `GeneratorExit`, `KeyboardInterrupt`, `SystemExit` and `Exception` there is no direct subclass of `BaseException` in built-ins of python
- **Finally** is always executed:
  - Exception can occur in `except` block
    - if you raise an exception as `raise TypeError from e` in except block where 'e' is the instance of exception, user will see that the exception you throwed occured becuase of 'e'
  - If exception occurs in `except` or `else` clause then `finally` is first excecuted and exceptions are then re-raised. If `finally` has _return_, _continue_ or _break_ statement then exceptions are not re-raised
  - If `try` block has _break_, _return_ or _continue_ statement then `finally` block will be executed prior to that statement
- **Grouping Exceptions**: (very new)
  - `ExceptionGroup` is used to group multiple unrelated exceptions
  - It is subclass of `Exception` and thus can be handled or raised like a normal exception
  - If `ExceptionGroup` is thrown, you can `except* ExceptionName` to handle a particular exception of the group. Unlike normal except blocks, multiple `except* ExceptionName` can be called. Uncaught exceptions are re-raised at the end

```python
# EXAMPLE: raise an exception
x = -5
if x < 0:
    raise Exception('x should be positive') # cause an Exception
assert (x > 0), 'x should be positive' # cause an AssertionError

# EXAMPLE: catch an exception
try:
    res = x / 0
except ZeroDivisionError as e:
    print(e.message)
    raise # raise exception again
else:
    print('Runs if exception did not occurred')
finally:
    print('Always runs')

# EXAMPLE: custom exception
class FeverError(Exception):
    def __init__(self, message, temp):
        self.message = message
        self.temp = temp
    def __str__(self):
        return self.message

try:
    raise FeverError('Too high temp', 32)
except FeverError as e:
    print(f'Your temp is too high, temp={e.temp}')

# EXAMPLE: exception grouping
def give_me_exceptions():
    excs = [ValueError(), ZeroDivisionError(), TypeError()]
    raise ExceptionGroup('Exceptions raised vai test', excs)

# handle some exceptions of exception group
try:
    give_me_exceptions()
except* ZeroDivisionError:
    print("Handled Zero Division Error")
except* ValueError:
    print("Handled Value Eror")

# handle ExceptionGroup iteself
try:
    give_me_exceptions()
except ExceptionGroup:
    print("Handled all exceptions")
```
