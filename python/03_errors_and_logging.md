# Exceptions, Errors and Logging

- [Exceptions, Errors and Logging](#exceptions-errors-and-logging)
  - [Exceptions and Errors](#exceptions-and-errors)
  - [Logging](#logging)

## Exceptions and Errors

- Out of multiple `except` blocks, a max of one block will be executed
- `BaseException` is the baseclass of all exceptions (even `Exception` class which represents non-fatal exceptions is its subclass)
- Exceptions which are not subclasses of `Exception` are not typically handled as they indicate that program should in-fact terminate
  - Except `BaseExceptionGroup`, `GeneratorExit`, `KeyboardInterrupt`, `SystemExit` and `Exception` there is no direct subclass of `BaseException` in built-ins of python
- **Finally** is always executed:
  - Exception can occur in `except` block
    - if you raise an exception as `raise TypeError from e` in except block where 'e' is the instance of exception, user will see that the exception you throwed occured becuase of 'e'
  - If exception occurs in `except` or `else` clause then `finally` is first excecuted and exceptions are then re-raised. If `finally` has *return*, *continue* or *break* statement then exceptions are not re-raised
  - If `try` block has *break*, *return* or *continue* statement then `finally` block will be executed prior to that statement
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

## Logging

- [Official tutorial on logging](https://docs.python.org/3/howto/logging.html)
- Different components:
  - **Loggers** send *log records* to **Handlers** which determine which log records to propagate and where to propagate. Finally **Formatters** specify the layout of log record in the final output.
  - Note that logger itself can filter some log events.
  - **LogRecord** instance holds up everything about a log event
- **Logger** instance is used to perform the logging.
  - `logging.basicConfig(format, filename, filemode, level)` configures the *root* logger
  - **Loggers** are arranged in a namespace hierarchy. Thus *scan.pdf* and *scan.txt* are children of *scan* logger
  - Child loggers propagate messages up to the handlers associated with their ancestor loggers. Thus `scan.pdf.info()` will also call `scan.info()`. Setting `propagate` attribute of logger to *False* will disable this behavior
  - Highest parent is **root** logger. `Logging.[debug][info][error]()` all call the respective methods of *root* logger
- **Logger** has two type of methods:
  1. *Configuration*: which are `setLevel()`, `addHandler()` & `removeHandler()`, `addFilter()` & `removeFilter()`
  2. *Message sending*: like `exception()`, `error()`, `log()`, `info()` and so on
     - `exception()` method prints a stack trace of exception that you pass
     - `log()` method takes a *level* arg which can be used for custom logging levels
- `logging.Formatter.__init__(fmt=None, datafmt=None, style='%)` where style can `%`, `{`, or `$`. If style is *%* then message format string (i.e `fmt` option) uses `%{<dict key>}s` styled string substitution (e.g **%s(msg)s** will be substituted by the log event's message). See [other attributes](https://docs.python.org/3/library/logging.html#logrecord-attributes)
- **Configuring Logging**: There are three methods:
  1. Creating loggers, handlers, formatters and using their config methods
  2. Creating a logging config file and using `logging.config.fileConfig(filepath)` function
  3. *(recommended)* Creating a *dictionary* of config and passing it to the `logging.config.dictConfig(config)`
     - It is recommended because you can populate the *dictionary* directly using json, YAML, python code and so on

```python
logging.getLogger('animal')

# both lines create/access the same logger 'animal.human'
human = logging.getLogger('animal').getChild('human')
human = logging.getLogger('animal.human')

# Since human is child of animal logger, all handlers of animal logger will receive this event
# thus if human saves logs in 'human.log' and animal saves logs in 'animal.log', both files will a new log entry
human.warning('Save water! Save earth!!')
```
