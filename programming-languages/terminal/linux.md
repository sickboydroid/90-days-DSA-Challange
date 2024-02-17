# General Linux

- [General Linux](#general-linux)
  - [Misc](#misc)
  - [User and Group Management](#user-and-group-management)
    - [User Management](#user-management)
    - [Group Management](#group-management)
  - [Permission Management](#permission-management)
  - [stat command](#stat-command)
    - [File Mode: Type and Permission](#file-mode-type-and-permission)
  - [Patch and Diff](#patch-and-diff)
    - [Basic Usage](#basic-usage)
    - [Diff](#diff)
    - [Patch](#patch)
  - [Data manipulation](#data-manipulation)
    - [Head and Tail](#head-and-tail)
    - [AWK](#awk)
  - [netcat/nc](#netcatnc)

## Misc

- System wide changes require root privileges
- learn about different dirs

## User and Group Management

### User Management

1. **Adding and deleting users:**
   - `useradd`, `userdel` and `passwd` to add, remove and change password of a user
   - `/etc/default/useradd` contains defaults for useradd command
2. **System User:**
   - `useradd -r` is used to create system users
   - Useful while doing automation or performing tasks in background
   - Generally, login screen only shows users with **uid >= 1000**. System users are given **uid < 1000**
   - System user is not supposed to log in interactively
3. **Modifying User Group:**
   - `usermod` is used to modify an existing user
   - `usermod -aG group-name username`: will make user a member of secondary group
   - `usermod -rG group-name username`: will remove user from a secondary group
   - `usermod -g group-name username`: **(not recommended**) will change a users primary group. You have to other things after this
4. **Important Files:**
   1. `/etc/shadow`: contains info about passwords
      - `username:password_hash:A:B:C:D:E:F:`
      - You can use `chage -l [user]` to view meaning and values of _A,B,C,D,E and F_
      - _A_: After how many days user was added w.r.t epoch
      - _B_: After how many days user can reset his password
   2. `/etc/passwd`: contains info about users
      - `username:x:uid:gid:gecos:homedir:default_shell`
      - _x_: password is hashed (mostly/always x)
      - _gecos_: user information field like users actual name
      - For system users, _default_shell_ is usually _/usr/sbin/nologin_ which just prints 'This account is currently not available.'

### Group Management

1. **Managing Groups:**

   - `groupadd` and `groupdel` for creating and deleting a group
   - `groups [user]` list the groups of which user is member. First one is _primary group_ and all other groups are secondary groups
   - `id user` will give you uid and gid of primary group of user and gid of secondary groups of user

2. **Important Files:**
   - `/etc/groups`: contains info about groups
     - `groupname:password:gid:members`
     - Nobody uses group password and it is insecure as well
3. **Misc**

   - Mostly in linux when you create a user, a group with the same name is created as well and the user is made a member of that group. This is the **primary group** of user
   - You have to re-login in the account after adding a group to get its effect
   - Use of groups can be seen in office. You can create a group of 100 employees and give all of them permissions at the same time
   - Another use can be seen in sshd-config. There you can either specify users via `AllowUsers` or groups via `AllowGroups`. AllowGroups is preferred as a group is easier to manage than individual users

4. **Primary v/s Secondary Groups:**
   - Any group can primary and secondary
   - `primary group`: User can be member of only 1 primary group
   - `secondary group`: User can be member of any number of secondary/supplementary groups
   - In layman terms, Specifies a group that the operating system assigns to files that the user create. All other groups that the user belongs are secondary groups

## Permission Management

- Every file is owned by a user and a group
- Ten Characters Of First Column:

  - First 1: `d` means _dir_, `s` means special _file_, `-` means _regular file_
  - Next 3: Owners permission
  - Next 3: Permission to the primary group of owner
  - Next 3: For all other users

- File Modes:
  - 0: no permission
  - 1: **execute permission**
  - 2: **write permission**
  - 3: (2+1) write and execute
  - 4: **read permission**
  - 5: (4+1) read and execute
  - 6: (4+2) read and write
  - 7: (4+2+1) read, write and execute

## stat command

- `stat` command will give you lot of info about file status
- `stat --printf '...'` you can provide a string for controlling what you want to see

### File Mode: Type and Permission

- File mode is stored in `st_mode`. It is an integer that contains file type (directory, socket, regular file, symbolic link, FIFO, so on) and access permissions (discussed above)
- Two ways to access file type information from `st_mode`:
  - Mask out the rest of things (permission part) and compare file type code alone. `S_IFMT` (FILE TYPE MASK) is a bit mask to mask out file type from `st_mode`
  - Use predicate macro like `S_ISDIR(mode)`, `S_ISFILE(mode)`, etc.

## Patch and Diff

### Basic Usage

```bash
# generate patch file
diff -ur dir_org dir_new > dir.diff

# apply patch
patch < dir.diff

# undo the applied patch
patch -R < dir.diff
```

- If env is too different for patch then it will fail
  - It will give you two files: `*.rej` and `*.org`

### Diff

- `diff [original] [changed]`
- `diff` tool calcs the difference b/w files which is called `patch`
- First line contains lines that differ
- `<` for original and `>` for changed
- `-x PATTERN` exclude files that match PATTERN
- `diff3 file1 file2 file3` compare 3 files/dirs

### Patch

- `patch [orginal] [patchfile]` or `patch < [patchfile]`
- `-b` make backup of original files

## Data manipulation

### Head and Tail

- **HEAD**:

  - `head -19 file` prints first 19 lines
  - `-n NUM` read first n lines
    - If NUM is -ve then print all except for last NUM lines
    - NUM can have suffix like K (1021) and kB (1000). 4kB means 4000

- **TAIL**:
  - `tail -19 file` prints last 19 lines
  - `-n NUM` read last n lines
    - if NUM is followed by + (e.g +4) then output every thing from NUM line (inclusive)
    - NUM can have suffix just like in head
  - `--retry` keep trying to open file
  - `--follow, -f` output appended data (useful in logging)

### AWK

1. ![Awk Work Flow](./assets/AWK_workflow.jpg)
2. **Format**:

   ```awk
   BEGIN {awk-commands} # BEGIN block

   /pattern/ {awk-commands} # BODY block

   END {awk-commands} # END block
   ```

3. **Basics**:

   - `FS` represents separator and by default is **space**. `-F` option can be used to change it
   - $0 represents whole line/record
   - $1 represents first word/field, $2 represents second word/field and so on
   - `awk -f program.awk input_file.txt` runs the awk script **program.awk** on input_file.txt
   - `--dump-variables` dumbs all variables in awkvars.out
   - `--lint` provide lints
   - `--profile` generate pretty-printed version of the program you wrote on command-line
   - `-F SEP` changes values of `FS` variable. FS variable represents the seperator which is by default **space**

4. **Variables**:

   - `ARGC` and `ARGV` contain arg count and args respectively
   - `FILENAME` contains name of file
   - `NF` represents number of fields in current record
   - `NR` represents number of current record. For first record/line it is 1 then 2 and so on

5. **Regexp**:

   - `[^CT]all` matches Xall execpt Call and Tall
   - `Call|Ball` matches Call or Ball
   - `Colou?r` matches Colour and Color
     - ? Zero or more
     - \+ One or more

Examples:

```bash
awk '{print}' file    # Print all lines/records
awk '{print $0}' file # Print all lines/records
awk '{print $1}' file # Print first word/field of each line
awk '/^wow/ {print $2}' file         # Print 2nd word of each line which starts with wow
awk -v name=Junaid '{printf "Your name is %s", name}' file # Prints Your name is Junaid
awk '{print length($2) >= 18 }' file # Print those lines where 2nd column/word has 18 or more letters

# Prints number of times a junaid is found in the file
# Count is increamented when pattern match succeeds
awk '/junaid/{++count} END {print "Count: ", count}' file

# String cancatination
awk 'BEGIN {str1="Hello"; str2=", "; str3=str1 str2 "World"; print str3}'
```

## netcat/nc

Switches:

- `-l`: Listen for incoming connections
- `-v`: Verbose
- `-p ####`: Port
- `-w SEC`: Timeout. IMP
- `-n`: Don't resolve. Provide IP instead of domain
- `-e STR`: Execute commands
- `-z`: Port scan

1. Range of ports can also be used
