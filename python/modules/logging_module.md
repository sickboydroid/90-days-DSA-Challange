# Logging

- [Official tutorial on logging](https://docs.python.org/3/howto/logging.html)
- Different components:
  - **Loggers** send _log records_ to **Handlers** which determine which log records to propagate and where to propagate. Finally **Formatters** specify the layout of log record in the final output.
  - Note that logger itself can filter some log events.
  - **LogRecord** instance holds up everything about a log event
- **Logger** instance is used to perform the logging.
  - `logging.basicConfig(format, filename, filemode, level)` configures the _root_ logger
  - **Loggers** are arranged in a namespace hierarchy. Thus _scan.pdf_ and _scan.txt_ are children of _scan_ logger
  - Child loggers propagate messages up to the handlers associated with their ancestor loggers. Thus `scan.pdf.info()` will also call `scan.info()`. Setting `propagate` attribute of logger to _False_ will disable this behavior
  - Highest parent is **root** logger. `Logging.[debug][info][error]()` all call the respective methods of _root_ logger
- **Logger** has two type of methods:
  1. _Configuration_: which are `setLevel()`, `addHandler()` & `removeHandler()`, `addFilter()` & `removeFilter()`
  2. _Message sending_: like `exception()`, `error()`, `log()`, `info()` and so on
     - `exception()` method prints a stack trace of exception that you pass
     - `log()` method takes a _level_ arg which can be used for custom logging levels
- `logging.Formatter.__init__(fmt=None, datafmt=None, style='%)` where style can `%`, `{`, or `$`. If style is _%_ then message format string (i.e `fmt` option) uses `%{<dict key>}s` styled string substitution (e.g **%s(msg)s** will be substituted by the log event's message). See [other attributes](https://docs.python.org/3/library/logging.html#logrecord-attributes)
- **Configuring Logging**: There are three methods:
  1. Creating loggers, handlers, formatters and using their config methods
  2. Creating a logging config file and using `logging.config.fileConfig(filepath)` function
  3. _(recommended)_ Creating a _dictionary_ of config and passing it to the `logging.config.dictConfig(config)`
     - It is recommended because you can populate the _dictionary_ directly using json, YAML, python code and so on

```python
logging.getLogger('animal')

# both lines create/access the same logger 'animal.human'
human = logging.getLogger('animal').getChild('human')
human = logging.getLogger('animal.human')

# Since human is child of animal logger, all handlers of animal logger will receive this event
# thus if human saves logs in 'human.log' and animal saves logs in 'animal.log', both files will a new log entry
human.warning('Save water! Save earth!!')
```
