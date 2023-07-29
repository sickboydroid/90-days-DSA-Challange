# os module

- [os module](#os-module)
  - [Misc](#misc)
  - [File Handling](#file-handling)
  - [Process Management](#process-management)
  - [Working with Pathnames (os.path)](#working-with-pathnames-ospath)
  - [Examples](#examples)

## Misc

- **File descriptors** are small integers corresponding to a file that has been opened by the current process. stdin, stdout and stderr are usually file descriptors 0, 1 and 2 respectively. Further opened files will be assigned 3, 4, 5 and so on.
  - On Unix systems, sockets and pipes can be referenced by file descriptors and names
  - `fileno()` of a file object returns the file descriptor associated with the file
- **Pipe** is a form of redirection. Someone's output (stdout) becomes someone else's input (stdin)
- Usually variant of a of a function in os module that accepts a file descriptor rather than string path starts with **f** (e.g fchdir() for chdir())
- Some functions have variants that start with **l** which by default have `follow_symlinks=False` i.e they operate on symlinks rather than the file it is pointing (e.g os.lchmod() for os.chmod())
- Some functions have variants that end with **b** which returns byte representation of their string counterparts (e.g getcwdb() for getcwd())
- Everything inside os module raises **OSError** (or its subclass)
  - os.error is the alias to os.OSError

## File Handling

1. **Misc**:
   - `os.environ`: environment variables (captured at the time of importing of module)
     - `os.getenv(var_name)` and `os.putenv(var_name, val)` can be used instead of modifying/accessing **os.environ**
   - `os.get_exec_path()`: same as `os.getenv('PATH')`
   - `getpid()`: returns the current process id
   - `os.uname()`: returns information identifying current os
   - `os.strerror(code)`: returns error message corresponding to code e.g for 0 it will return 'Success', for 1 it will return 'Operation not permitted' and so on
2. **Working with File Descriptors (fds) and pipes**:
   - `os.fdopen(fd, mode='r')`: returns an open file object (object that supports read/write) connected to the file descriptor
   - `os.close(fd)` and `os.closerange(fd_low, fd_high)` (fd_high exclusive) are used to close file descriptors
   - `os.dup(fd)` and `os.dup2(fd1, fd2)`: Former will duplicate **fd** and return the new file descriptor. Later will duplicate **fd1** to **fd2** (closing fd2 if open) and return fd2
   - `os.read(fd, n)` and `os.write(fd, str)` read n bytes and write str to the file connected with file descriptor
   - `os.pipe()`: returns a pair of file descriptors (r, w) usable for reading and writing
   - You can create directory descriptors (also called fds for directories) also (see example 3)
   - `os.mkfifo(path, ...)`: create a FIFO (a named pipe) named path. Named pipes are similar to ordinary pipes except these pipes have name rather than just file descriptor. One writes to named pipe and other reads from it.
   - **Inheritable and non-inheritable fds**: By default fds created by python are non-inheritable. It means that they are not available for child processes. (see example 2)
     - `os.get_inheritable(fd)`: returns True if fd is inheritable
     - `os.set_inheritable(fd, inheritable)`: set fd either inheritable or non-inheritable
3. **Working With Files and Dirs**:
   - `os.chdir(path)`: change working directory to path. path can be file descriptor representing a directory
   - `os.getcwd()`: return current working directory
   - `os.chmod(path, mode)`: sets permissions to a file.
     - e.g: `os.chmod('foobar', mode=0o755)` or equivalently `os.chmod('foobar', mode=(stat.S_IRUSR | stat.S_IWUSR | stat.S_IRGRP | stat.S_IROTH)`
   - `os.mkdir(path, mode=0o777, dir_fd=None)` and `os.makedirs(...)`: create directory with numeric mode **mode** (permissions).
   - `os.chroot(path)`: change the root directory of current process to path. chroot in unix is like a **directory jail**. You cannot access files/dirs higher than this path. (i.e it becomes your new root)
   - `os.listdir(path='.')`: return list of entries in the dir give by path
   - `os.scandir(path='.')`: returns an iterator of **os.DirEntry** objects. Suggested over `os.listdir(path='.')` if you need to information about each item in path
     - Can be used context manager (suggested)
   - `os.stat(path)`: get status of file or fd.
     - It returns object os `os.stat_result`. [See doc of os.stat_result](https://docs.python.org/3/library/os.html#os.stat_result)
     - `stat` module defines functions and constants that are useful for extracting information from a `stat structure`
   - `os.truncate(path, length)`: cut-off the file so that it is at most **length** bytes long
   - **Deletion**:
     - `os.removedirs(name)`: delete leaf directory and if it is successfully deleted remove every parent directory mentioned in path until an error occurs. Error will ignored if it did not occur from leaf directory.
       - e.g `os.removedirs('foo/bar/baz')` will first remove **foo/bar/baz** then **foo/bar** and then **foo**. It raises error only if leaf directory (baz) could not be deleted (for ex. when it is not empty)
     - `os.remove(path)` or `os.unlink(path)`: delete the file path (not dir)
     - `os.rmdir(path)`: remove an empty directory
   - **Links**:
     - `os.link(src , dst, src_dir_fs=None, dst_dir_fd=None)`: create a **hard link** pointing **src** and named **dst**.
       - Pass **src_dir_fd** and/or **dst_dir_fd** to supply paths relative which **src** and **dst** are given.
     - `os.symlink(src, dst, target_is_directory=False)`: Create a symbolic link pointing to **src** named **dst**.
       - On windows, **dst** can be a directory
     - `os.readlink(path)`: return a string representing the path to which the symbolic link points
   - **Walking**:
     - `os.walk(top, topdown=True)`: returns _generator_ that yields a tuple `(dirpath, dirnames, filenames)` (includes top). _dirnames_ contains both dirs and symlinks present in _dirpath_. (see example 4)
     - if topdown=True, generate triple for directory before generating for any of its sub-directories
       - Also when topdown=True, you can modify _dirnames_ in place (i.e slice, delete etc) and those dirs won't be searched
     - `os.fwalk(...)` is similar but returns 4-tuple `(dirpath, dirnames, filenames, dirfd)`. Note that dirfd will be replaced after on next iteration so use `os.dup()` if you want to keep them longet

## Process Management

- **Execute a new program**:
  - `os.exec*(file/path, ...)`: execute a new program, replacing the current process. (see example 6)
    - `l` (os.execl\*()) and `v` (os.execv\*()) varients differ in how arguments are passed to exec\*() function.
    - `p` (os.exec\*p\*()) varients use the PATH env variable to locate the program **file**
    - `e` (os.exec\*e()) varients accept the **env** parameter (mapping to define the env vars for the new process).
- `os.popen(cmd, mode='r', buffering=-1)`: open a pipe to or from command **cmd**. Returns an open file object connected to pipe which can be read or written depending on **mode** (see example 7)

## Working with Pathnames (os.path)

- All functions either accept only strings or only bytes. The returned result is also of same type if a path or file name is returned.
- Most of the functions don't need path to exist
- `abspath(path)`: returns absolute path. It is equivalent to `join(os.getcwd(), path)`
  - `isabs(path)`: will tell if the path is absolute or relative
    - On unix, path is absolute if it begins with **/**
    - On windows, path is absolute if it begins with a drive letter
- `relpath(path, start=os.curdir)`: return the relative path from the **start** dir
- `commonpath(paths)`: return the longest command sub-path
- `expanduser(path)`: **~** or **~user** is replaced by user's home directory
- `expandvars(path)`: `$name` or `{$name}` are replaced by the value of env variable **name**
- `getsize(path)`: returns size in bytes
- `islink(path)`: is the path a symbolic link?
- `join(path, *paths)`: join one or more paths intelligently. Returns a single string
- `samefile(path1, path2)`: do both paths refer the same file?
- `sameopenfile(fd1, fd2)`: do both file descriptors refer the same file?

## Examples

```python
######################

# EXAMPLE 1: File descriptors
# disable stdout/stderr
stdout = os.fdopen(1) # or os.close(1)
stderr = os.fdopen(2) # or os.close(2)
stdout.close()
stderr.close()
print('Hello, world')
raise Exception('Will not be printed')

######################

# EXAMPLE 2: Inheriting file descriptors
f = open('hello.txt')
os.set_inheritable(f.fileno(), True) # set inheritable
# You can read the above file descriptor (assume to be 3) from another process like below
subprocess.run('python3', close_fds=False)
>>> import os
>>> os.fdopen(3,'r').readline()

######################

# EXAMPLE 3: Creating file descriptors for directories
dir_fd = os.open('foo/bar/dir', os.DIRECTORY)
# Create a directory inside foo/bar/dir rather than cwd
os.mkdir('inside_foobardir', dir_fd=dir_fd)

######################
```

```python
# EXAMPLE 4: Walking dir tree
from os.path import join, getsize
# displays size of each directory under 'foo' except 'CVS' dir
for root, dirs, files in os.walk('foo/'):
    root_size = sum(getsize(join(root, name))  for name in files)
    print(f '{root} consumes {root_size} bytes and has {len(files)} inside')
    if 'CVS' in dirs:
        print('ignored CVS')
        dirs.remove('CVS')  # don't visit CVS directories
```

```python
########################
# EXAMPLE 6: Using os.exec*(...)
# all do the same thing i.e they print `Hello`
os.execl('/usr/bin/echo', 'echo', 'Hello')
os.execlpe('/usr/bin/echo', 'echo','Hello', {"PATH": '/usr/bin'})
os.execlp('echo', 'echo', 'Hello')
os.execlpe('echo', 'echo', 'Hello', {"PATH": '/usr/bin'})

os.execv('/usr/bin/echo', ['echo', 'Hello'])
os.execve('/usr/bin/echo', ['echo', 'Hello'], {"PATH": '/usr/bin'})
os.execvp('echo', ['echo', 'Hello'])
os.execvpe('echo', ['echo', 'Hello'], ={"PATH": '/usr/bin'})

#########################
# EXAMPLE 7: Using os.pope(...) to pipe to or from commands
pipe = popen('cat > hello.txt', mode='w')
pipe.write('This text was written from python\n')
pipe.flush()
pipe.close()
```
