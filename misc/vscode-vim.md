# Vim and Visual Studio

- [Vim and Visual Studio](#vim-and-visual-studio)
  - [Modes](#modes)
  - [Vim for terminal](#vim-for-terminal)
  - [Key bindings](#key-bindings)

## Modes

- Three modes:
  - Normal mode, move around
  - Insert mode, edit code
  - Visual mode, select things
- `ctrl+c` = normal mode
- `ctrl+v` = visual mode
- `i` = insert mode

## Vim for terminal

- Buffer: Temp storage in ram
- r filename: reads file and puts in the next line
- jobs command shows background jobs
- fg jobnumber to bring background jobs
- switching b/w files is actually switching b/w buffers
  - e file: opens new file in new buffer
  - bp: buffer previous
  - bn: buffer next
  - bd: delete buffer
  - badd: adds buffer in bg
- 0  to go the beginning of line
- The curosr is after character
- %s/find/replacement/flags
- sort ui: sorts selected text
- split: splits current buffer horizontally
  - ctrl-ww to switch focus
  - vsplit (vs): split vertically
  - tip: splits helps in cross-referencing if you don't provide file
- :set number: enable line numbers
- vim +line-number filename
- vim -o file1 file2: opens horizontally splitted
- vim -O file1 file2: opens vertically splitted

## Key bindings

- `w` move to beggining of next word
- `b` move to beginning of pre word
- `e` move to end of next word
- `ge` move to end of prev word

> Capital versions of above will allow to move WORD by WORD
---

- `f{character}` moves cursor forward on character
- `F{character}` moves cursor backward on character
- `t{character}` moves cursor foward until character
- ...
- You can `;` and `,` to go to next and prev occurance/ respec.
character is one if one of my fav character as it ics and ius isc

---

- `0` and `$` move to beginning and end of line
- `^` and `g_` move to first and last non-blank character
- `}` and `{` jump one paragraph down or up
- `ctrl-d` and `ctrl-u` move half page down and up
- `/` and `?` search forward and backward
- `*` and `#` to search for word under cursor foward and backward
- `gd` goto definition
- `gf` goto imported file (goto WORD file)
- `{linenumber}gg` goto linenumber
- `%` goto matching parenthesis

---
