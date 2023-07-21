# File Handling Modules

- [File Handling Modules](#file-handling-modules)
  - [Path class](#path-class)
    - [glob](#glob)

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
