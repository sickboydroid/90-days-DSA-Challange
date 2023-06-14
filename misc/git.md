# Git

## Nutshell

- Branches are pointers to specific commit
- `git branch name; git checkout name`: create and switch a branch

## Branching

- **Branch early, and branch often**
- Pointers to specific commit.
- Very light weight (no space is consumed)
- `git branch name` create a new branch
- `git checkout name` switch to name branch
- `git checkout -b name` create and switch to name branch
- `git switch name` similar to `git checkout` but still experimental 

## Merging

- Merging creates a special commit that has two unique parents
- If you go up the commit history and encounter all commits then that commit contains all work of repository
- `git merge otherBranch` will combine the commit/work of otherBranch into curBranch
  - A new commit will be created with the work of both curBranch and otherBranch
  - curBranch (which is a commit pointer) will point to this commit that just happened
  - otherBranch (pointer) wont be effected at all
