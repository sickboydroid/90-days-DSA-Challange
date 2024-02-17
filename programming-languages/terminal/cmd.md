# CMD

- [CMD](#cmd)
  - [Misc Commands](#misc-commands)
  - [File Manipulation](#file-manipulation)
  - [Tasks and Services](#tasks-and-services)
  - [Getting System and Program Info](#getting-system-and-program-info)

## Misc Commands

- Launch Programmes directly as `notepad.exe`

## File Manipulation

- File names are case insensitive

---

- `D:`: change to D drive
- `cd..`: go one step up in directory
- `cd\`: go to root of file system
- `mkdir`: create directory
- `ren old_name new_name`: rename files and dirs
- `type nul> file_name`: create file

---

- `copy src_dir dest_dir`: copy files
- `move src_dir dest_dir`: move dirs
- `del file_name`: delete file
  - `del dir`: remove all files inside the dir
- `rmdir dir`: delete an empty dir
  - `rmdir /s dir`: remove non-empty dir

## Tasks and Services

- Admin Permission is required for these commands
  - `tasklist`: List all tasks
  - `taskkill /PID PID`: Stop the task with PID
- `net start`: List all services
- `net start NAME`: Start the NAME service
- `net stop NAME`: Stop the NAME service
- `driverquery`: List the installed drivers

## Getting System and Program Info

- `diskpart`: Launches a prompt to work with disks
  - `list disk`: List all physical disk
  - `select disk NUM`: select disk with number NUM
  - `detail disk`: Show details about selected disk
  - `exit`: to get back
- `getmac`: Get physical address of pc
- `systeminfo`: Get info about your system
