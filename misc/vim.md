# Vim

- [Vim](#vim)
  - [Misc](#misc)
  - [Modes](#modes)
  - [Vim for terminal](#vim-for-terminal)
  - [Key bindings](#key-bindings)

## Misc

- [Cheatsheet of vim](https://devhints.io/vim)
- `jobs` command shows background jobs
- `fg jobnumber`: bring background jobs
- The cursor is after character

## Modes

- Three modes:
  - Normal mode, move around
  - Insert mode, edit code
  - Visual mode, select things

## Vim for terminal

- **Buffer**: Temp storage in ram
- `r filename`: reads file and puts in the next line
- switching b/w files is actually switching b/w buffers
  - `e file`: opens new file in new buffer
  - `bp`: buffer previous
  - `bn`: buffer next
  - `bd`: delete buffer
- `0` or `^` to go the beginning of line
- `%s/find/replacement/flags`
- `sort ui`: sorts selected text
- `split`: splits current buffer horizontally
  - `ctrl-ww` to switch focus
  - `vsplit` (vs): split vertically
  - tip: splits helps in cross-referencing if you don't provide file
- `:set number`: enable line numbers
- `vim +<line> filename`: Open file with cursor at **line**
- `vim -o file1 file2`: opens horizontally split
- `vim -O file1 file2`: opens vertically split
- `s`: replace selection
- `tabedit [file]`: edit file in a new tab
- `< = >`: for left indent, auto indent and right indent
- Following operators are applied to a range of text as `operator motion`:
  - `~` or `g~` swap case
    - `g~w`: swap case of word
    - `g~$`: swap case to the end of line
  - `gU`: to uppercase (works same as above)
  - `gU`: to uppercase (works same as above)
  - `gu`: to lowercase (works same as above)

## Key bindings

- `w` move to beginning of next word
- `b` move to beginning of pre word

> Capital versions of above will allow to move WORD by WORD

- `f{character}` moves cursor forward on character
- `F{character}` moves cursor backward on character
- `t{character}` moves cursor forward until character
- You can `;` and `,` to go to next and prev occurrence/ respectively

---

- `0` and `$` move to beginning and end of line
- `^` and `g_` move to first and last non-blank character
- `}` and `{` jump one paragraph down or up
- `ctrl-d` and `ctrl-u` move half page down and up
- `/` and `?` search forward and backward
- `*` and `#` to search for word under cursor forward and backward respectively
- `gd` goto definition
- `gf` goto imported file (goto WORD file)
- `{linenumber}gg` goto linenumber
- `%` goto matching parenthesis

---
