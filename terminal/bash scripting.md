# BASH

- [BASH](#bash)
  - [MISC](#misc)
    - [Multi-line commenting](#multi-line-commenting)
    - [heredoc](#heredoc)
    - [Conditionals and Cases](#conditionals-and-cases)
      - [`[ EXP ]`](#-exp-)
      - [`[[EXP]]`](#exp)
    - [Loops](#loops)
    - [Arrays](#arrays)

## MISC

- `type NAME` gives you type of NAME (like built-in, function, alias, etc)
- `(())` enable you to omit dollar sign and allow you to include spaces around operators
- `:(){ : | : & }; :` is fork bomb. DON'T EXECUTE IT

### Multi-line commenting

```bash
: '
These are multi-line commenting.
You can add as many lines as you wish.
'
```

### heredoc

```bash
cat << END
You can output as many lines as you want.
When you are done just type heredoc=delimiter
END
```

### Conditionals and Cases

- `if` just checks the exit state of command

#### `[ EXP ]`

- `[` is a file and synonym to `test` command. `]` is just arg for `]`
- `man test` will give you great manual for this command
- `! EXP` negates an exp
- `-n` len of str is non-zero
- `-z` len of str is zero
- `str1 = str2` & `str1 != str2` are for string comparisions
- `-eq`, `-ge`, `-gt`, `-le`, `-lt` & `-ne` for integer comparisions
- `-d FILE`: FILE exists and is dir
- `-f FILE`: FILE exists and is file
- `-e FILE`: FILE exists

```bash
if [ 3 -lt 4]; then ...
if test 3 -lt 4; then ...
```

#### `[[EXP]]`

- Comparisons operators can be used without escaping
- `-a` and `-o` are simple replaced by && and ||.
- Grouping can be done without escaping
- Supports regular exp as `[[ STR =~ REG_EXP ]]`

```bash
[ 2 -lt 5 -a 3 \> 0] && echo "true"
[[ 2 < 5 && 3 > 5 ]] && echo "true"

[ 2 -lt 5 -a \( 3 \> 0 -a 10 -ge 10 \)] && echo "true"
[[ 2 < 5 && (3 > 0 && 10 >= 10 ) ]] && echo "true"
```

### Loops

- `continue` and `break` can be used normally

```bash
for i in 1 2 3 4 5; do ...
for i in {0..100}; do ...
# {start..end..increment}
for i in {0..100..2}; do ...
for (( i=0; i<=100; i += 2 )); do ...

# Working with files
for file in ~/home/*; do ...

# infinite loop
for (( ; ; ))
```

### Arrays

- Curly brackets are required for accessing arrays

```bash
# Declaring
pets=("cat" "dog" "squirrel" "wolf" "tiger")
pets[2]=squirrels
sports[0]=cricket
sports[1]=soccer
sports[3]=hockey
sports[2]=baseball

echo $pets # equivalent to echo ${pets[0]}
echo ${pets[2]}
echo ${pets[@]} # print all values

# loop through array
for pet in ${pets[@]}; do ...
# loop through indices of array
for i in ${!pets[@]}; do ...
```
