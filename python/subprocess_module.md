# subprocess

## subprocess.run(...)

- Method signature:`subprocess.run(args, *, stdin=None, input=None, stdout=None, stderr=None, capture_output=False, shell=False, cwd=None, timeout=None, check=False, encoding=None, errors=None, text=None, env=None, universal_newlines=None, **other_popen_kwargs)`
- Run command described by `args`
- Capture stdout and stderr: `run(stdout=PIPE, stderr=PIPE)` OR `run(capture_output=True)`. Output will in **CompletedProcess.stdout/stderr**
- Capture combined stdout and stderr: `run(stdout=PIPE, stderr=STDOUT)`
- `input`: provide _stdin_. It must a byte sequence (or string if below line is true)
- `shell=True`: specified command will be executed through shell (not recommended but it is not that bad ;)
- `timeout`: kill process after **time secs** and raise **TimeoutExpired** exception after termination of child process
- `check=True`: raise **CalledProcessError** if subprocess returns non-zero error code
- If `encoding` or `errors` are specified or `text` is _true_ then file objects for stdin, stdout and stderr are opened in text mode. By default, file objects are opened in binary mode.
- `env`: mapping that defines the env. vars for the new process. These are used instead of the default behavior of inheriting the current process’ environment. (NOTE: Only your defined vars are kept)

## Other things in subprocess module

1. `subprocess.DEVNULL` > can be used as stdin, stdout or stderr to suppress any input/output.
2. `subprocess.PIPE` > can be used as the stdin, stdout or stderr to capture the respective stream
3. `subprocess.STDOUT` > can be used as the stderr to merge stdout and stderr
4. **subprocess.CompletedProcess**:
   - Returned by `run(...)` function
   - `args`: arguments used to launch the process
   - `returncode`: exit status of child process
   - `stdout`: captured stdout (None, byte seq or string)
   - `stderr`: captured stderr (None, byte seq or string)

## Examples

```python
import subprocess as sp

# pipe output of cat to grep
cp_cat = sp.run('cat main.py'.split(), text=True,
                 capture_output=True, check=True)
cp_grep = sp.run('grep -n'.split(),
                input=cp_cat.stdout, text=True,
                capture_output=True, check=True)
print(cp_grep.stdout)

```
