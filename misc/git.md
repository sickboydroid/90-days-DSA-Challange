# Git

## Nutshell

- Branches are pointers to specific commit
- `git branch name; git checkout name`: create and switch a branch
- `merging` and `rebasing` are two ways to combine work from different branches into single branch
  - (you are in **master** branch and want to combine work of **feature_x** work into it)
    1. `git merge feature_x` will create a new merged commit in *master* branch with the work of both the branches into it.
       - *feature_x* branch won't be effected at all
    2. `git rebase feature_x`
- Instead of mentioning whole hash of a commit, you can specify only few characters.

## Misc

- `HEAD` symbolic name for currently checked out commit
  - `Detaching head` means attaching head to a commit rather than a branch
    - Even if the head and branch point to same commit, you can have detached head
- `Relative refs` convenient way of avoiding hashes by moving relative to branch or head
  - `^` move up by one commit. (git checkout master^^^ moves you 3 parents up from master)
  - `~<num>` move up by num commits. (git checkout master~3 moves you 3 parents up from master)
- `git branch -f main dest_ref` (forcefully) reassigns main branch to some other commit. (dest_ref can be another branch, commit hash or relative ref like HEAD~4)

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

## Rebasing

- Rebasing essentially takes a set of commits, "copies" them, and plops them down somewhere else

## Squashing

- Repackage multiple commits into single commit (basically rewrite history)
- `git rebase -i commit_hash` interactively rebase commits into a single one