# Parsing Command Line Arguments

## argparse module

- `cp -r SRC DEST`: **-r** is optional argument while as **SRC** and **DEST** are positional arguments
- `add_argument(...)` method is used to add command-line options
- What can you pass to `add_argument()` function? [Here it is!!](https://docs.python.org/3/library/argparse.html#quick-links-for-add-argument). Here are some things:

  - `nargs`: number of arguments that should be consumed (default: 1). It can be an `interger`, `'+'`, `'*'` or `'?'`
  - `type`: type (takes callable) to which argument values should be converted (default: str)
  - `choices`: allowed values
  - `dest`: name of the argument in object returned by `parse_args()`
  - `action`: It can one of following:
    1. `store`: store argument's value as a string
    2. `store_true`: flag is true when added
    3. `store_false`: flag is false when added
    4. `store_const`: flag is _const_ when added. Value of that constant is set by `const`
    5. `append`: appends each argument value to the list. e.g `-f 1 -f 2 --foo 3` will result in ['1','2','3'].
    6. `append_const`: appends constant for each occurance of argument. e.g `-f --foo -f -f` will result in ['12','12','12'] if `const='12`
    7. `count`: counts number of times a keyword/optional argument occurs. e.g `-vv -v --verbose` will result in 5 (if `default=0`)
    8. `help`: if set then it will act as `-h` or `--help` for the program
    9. `version`: shows version (provided by `version` keyword argument) and exits. e.g `parse_args(..., action='version', version='2.3.4V')`
  - `const` holds constant values for various `actions` (not read from command line)
  - `default`: default value for argument
  - `required`: positional arguments are ALWAYS required
  - `help`: brief description of argument
  - `metavar`: placeholder string for argument of an optional argument

- `parser.add_mutually_exclusive_group()`: allows us to specify options that cannot be used together
- `parse_args(['--foo', 'FOO', 'main.py'])`: You can pass a list of arguments here instead of from command line. (may be useful while debugging)

```python
parser = argparse.ArgumentParser(
                    prog='ProgramName',
                    description='What the program does',
                    epilog='Text at the bottom of help')

# p.a stands for parser.add_argument
parser.add_argument('filename')              # positional argument (MUST BE SUPPLIED)
p.a('-c', '--count', type=int)               # option that takes an int value
p.a('-e', '--exclude', action='store_true')  # on/off flag

p.a('-f', '--files', nargs='+', type=str) # you can pass 1 or more arguments
p.a('-d', '--child', action='append', type=str, help='mention your children') # you can use --child multiple times
p.a('--version', action='version', version='%(prog)s 12.3.3rc1') #

p.a('-n', '--atm-pin', choices=range(0,11), type=int, help='one digit atm pin') # accepts an atm pin of single digit

# accepts users name which will be stored in parser.parse_args().ownername
# and not in parser.parse_args().username
p.a('-u', '--username', metavar='PC OWNER NAME', dest='ownername', required=True)

# only one option out of the two can be provided at time
group = parser.add_mutually_exclusive_group()
group.add_argument('-v', '--verbose', action='count', default=0)
group.add_argument('-q', '--quite')

# e.g input
parser.parse_args('filename -c 12 --files f1 f2 f3 -d sick -d boy -n 2 -vvvv'.split())
```
