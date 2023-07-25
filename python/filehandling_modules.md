# File Handling Modules

- [File Handling Modules](#file-handling-modules)
  - [Path class](#path-class)
    - [glob](#glob)
  - [tempfile](#tempfile)
  - [shutil](#shutil)

## Path class

- Windows system but want to manage unix system > `PurePosixPath`
- Unix system but want to manage windows system > `PureWindowsPath`
- Normally you will use > `Path` (_recommended_)
- Path contains methods similar to that of unix commands:
  - `p.mkdir()`, `p.touch()`, `p.is_dir()`, `p.is_file()`, `p.parent`, `p.group()`, `p.owner()`, `p.read_bytes()`, `p.read_text()`, `p.write_bytes()`, `p.write_text()`,
  - `p.parts` attribute will give you a tuple of parts of the file path
  - `p.samefile(other_path)` will tell if both paths point to the same file
  - `p.joinpath('dir1','dir2', 'dir3')` will create a new path `p/dir1/dir2/dir3`. Note that you cannot use `/` or `\` in the paths you are appending
  - `p.iterdir()` will give you a generator which can be used for iterating over files in directory
  - `p.match(str)` test whether path matches the passed **glob style pattern**.
  - `p.symlink_to(target)` and `p.hardlink_to(target)` creates a symbolic link/hard link pointing to _pathname_
  - `p.unlink()` removes **files** and **symlinks**
  - `p.rmdir()` removes an empty **directory**
  - `p.replace(target)` replace the _target_ with _p_. It deletes _p_. _target_ may or may not exist
  - `p.open()` similar to inbuilt `open()` method
  - `p.expanduser()` expands `~` or `~user` into absolute paths
  - `p.glob(str)` glob the path represented by `p` (see [glob](#glob))
  - `p.absolute()` returns absolute path with resolving symlinks and `p.resolve()` returns absolute path with resolved symlinks

```python
# both will give PosixPath as we are on unix system
cur_dir = Path.cwd()
cur_dir = Path('/home/sickboy/python')

sub_dir = cur_dir / 'foo' / 'bar'
sub_dir.glob('**/*.py') # search for python files
```

### glob

- `glob.glob(pattern, root_dir=cwd, recursive=False)` is the main method of this module
  - if `recursive` is _True_ than you can use `**` as described below
  - if `include_hidden` is _True_ then `**` will match hidden directories as well
- **Python globing patterns:**
  - `?` matches zero or one character
  - `*` matches anything
  - `[...]` character set
  - `[!...]` complement of character set
  - `**` will match all directories and files at any level
  - `**/` will matches all directories (but not files) at any level
    - e.g `**/*` matches all directories or files at any level
    - e.g `**/images/**/*.png` matches all png files with images directory in its parent hierarchy. Here is breakdown of syntax
      - `**/` matches all dirs at all levels
      - `images/` matches any sub-directory of name image
      - `**/` matches any sub-directory of directory image
      - `*.png` matches png files
    - e.g `**/images/*.png` will match only those png files whose direct parent is images directory. However images directory itself can be any where
- Files starting with '.' can only be matched by globs also starting with literal '.'
- For literal match of `?`, `[`, `]` or `\*` , wrap it in square brackets.
  - e.g `[?]` will match ? and `[abc-]` will match a or b or c or -
- `glob.escape(pathname)` can do above thing for you
  - e.g `escape('//?/c:/Quo vadis?.txt')` returns `'//?/c:/Quo vadis[?].txt'`

## tempfile

- You can use return object of below functions with context managers
- `tempfile.TemporaryFile()`: Returns file-like object
- `tempfile.NamedTemporaryFile()`: Exactly like above except the file has a visible name in file-system
  - `name` attribute of returned object contains the path
- `tempfile.TemporaryDirectory()`: Secure temp Directory
  - `name` attribute contains the path
  - `cleanup()` can be called to delete all contents
  - As soon as returned object, dir together with its contents is deleted

```python
import tempfile as tmp
from os import path

# when you use TemporaryDirectory class with cxt manager, it returns the name : str of file
# Because cleanup is done automatically
with tmp.TemporaryDirectory() as dirname:
  with open(path.join(dirname, 'test.txt'), 'w') as f:
    f.write('Some temporary data!!')
  with open(path.join(dirname, 'test.txt'), 'r') as f:
    print(f.read())
```

## shutil

- `copyfileobj(fsrc, fdst[, length])`: Copies file-like objects.
- `copyfile(...)`: Copy contents (no metadata) of the src to dest. Dest will be overwritten if exists
- `copymode(...)`: Copy permission bits from src to dst. Everything else is unaffected.
- `copystat(...)`: Copy permission bits, last access time, last modification time, and flags and leave file contents, owner and group unaffected.
- `copy(...)`: Copy the file (contents + permission bits) to the directory or file. Returns the path of newly created/overwritten file
- `copy2(...)`: Same as above except it attempts to copy all metadata (uses copystat(...))
- `copytree(src, dst, ignore=None, copy_function=copy2, symlinks=False, dir_exist_ok=False, ...)`: Copy an entire dir src to dst recursively. src will become dst. Will attempt to preserve metadata

  - If **symlinks=True** symlinks will be also copied otherwise the files they are pointing to will be copied
  - **ignore** must be a callable which takes two arguments, **dir** being visited and a list of its **contents** (as returned by os.listdir()). It will return a subset of its 2nd argument and those dirs/files will not be copied.
    - `shutil.ignore_patterns(*patterns)` can be used to create such callable based on glob-style patterns

- `rmtree(path, onerror=None, ...)`: Delete and entire directory tree
- `move(src, dst)`: Recursively move file or dir to dst
- `disk_usage(path)`: Returns a named tuple as (total, used, free). Path may be file or dir
- `chown(path, user, group)`: Change user and/or group of file/dir
- `which(command, mode=os.F_OK | os.X_OK, path=None)`: Return the path to an executable. If no path is specified, `os.environ()` is used
- `get_terminal_size()`: Returns cols and lines. 1 col is 1 char and 1 line is height of 1 char
- For the following examples, assume **src.txt** and **dest.txt** contain some text while as **dne.txt** does not exist
- **Archiving Operations:**
  - `make_archive(basename, format, root_dir='.', base_dir='.', dry_run=False)`: Format can be zip, tar, gztar, bztar or xztar.
    - `root_dir` is the root of basename.format archive. Paths in archive will be relative to this directory
    - `dry_run` only log what will happen
    - `base_dir` directory where we start archiving from. It is give relative to `root_dir`
  - `get_archive_formats()`: get list of supported formats
  - `unpack_archive(filename, extract_dir='.', format=USE-EXTENSION)`: Unpack filename in the target dir extract_dir
  - `get_unpack_formats()`: get list of supported unpacking formats

```python
import shuil

# append text of one file to the other except first line
with open('src.txt', 'r') as fsrc:
    fsrc.readline() # Move current file position to the next line
    with open('dest.txt', 'a') as fdest: # append file instead of overwritting
        shutil.copyfileobj(fsrc, fdest)

# copytree which uses ignore optional argument to log copied dirs
def log(path, names):
    print(f'Copying directory {path} which contains {len(names)} file(s)')
    return [] # nothing will be ignored

print(shutil.copytree('earth', 'mars', dirs_exist_ok=True, ignore=log))

# rmtree example
def remove_readonly(funcrm, path, _):
  "Clear the readonly bit and reattempt the removal"
  os.chmod(path, stat.S_IWUSR)
  funcrm(path)

shutil.rmtree('src', onerror=remove_readonly)

### make_archive example ####
# tmp directory structure
$ tree tmp
tmp
└── root
    └── structure
        ├── content
            └── please_add.txt
        └── do_not_add.txt

# tar above directory
from shutil import make_archive
import os
make_archive(
    'myarchive',
    'tar',
    root_dir='tmp/root',
    base_dir='structure/content',
)

# contents of above tar
$ un-tar-gime myarchive.tar
structure/content/
structure/content/please_add.txt
#############################
```
