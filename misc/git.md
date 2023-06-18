# Git

- [Git](#git)
  - [Nutshell](#nutshell)
  - [Misc](#misc)
  - [Branching](#branching)
  - [Merging](#merging)
  - [Rebasing](#rebasing)
  - [Reversing changes](#reversing-changes)
  - [Cherry picking](#cherry-picking)
  - [Interactive rebasing](#interactive-rebasing)

## Nutshell

- Branches are pointers to specific commit
- `git branch name; git checkout name`: create and switch a branch
- `merging` and `rebasing` are two ways to combine work from different branches
  - (you are in **master** branch and want to combine work of **feature_x** work into it, *feature_x* branch won't be effected at all in both operations)
    1. `git merge feature_x` will create a new merged commit in *master* branch with the work of both the branches into it.
    2. `git rebase feature_x` will rebase the master branch
- Instead of mentioning whole hash of a commit, you can specify only few characters.

## Misc

- `HEAD` symbolic name for currently checked out commit
  - `Detaching head` means attaching head to a commit rather than a branch
    - Even if the head and branch point to same commit, you can have detached head
- `Relative refs` convenient way of avoiding hashes by moving relative to branch or head
  - `^` move up by one commit. (git checkout master^^^ moves you 3 parents up from master)
  - `~<num>` move up by num commits. (git checkout master~3 moves you 3 parents up from master)
- `git branch -f main dest_ref` (forcefully) reassigns main branch to some other commit. (dest_ref can be another branch, commit hash or relative ref like HEAD~4)
- If there conflicts in any command, you can use `--continue` after fixing them to continue the operation.
  - For example if cherry-picking caused some conflicts and you fixed those conflicts, run `git cherry-pick --continue` to continue cherry-picking
- Operations that modify commit history (e.g Interactive rebasing) should generally be avoided on commits that have already been pushed to a remote repository.

## Branching

- **Branch early, and branch often**
- Pointers to specific commit.
- Very light weight (no space is consumed)
- `git branch name` create a new branch
- `git checkout name` switch to name branch
- `git checkout -b name` create and switch to name branch
- `git switch name` similar to `git checkout` but still experimental 

## Merging

- Merging creates a special commit that has changes of two or more branches and has two or more parents
- If you go up the commit history and encounter all commits then that commit contains all work of repository
- `git merge otherBranch` will combine the commit/work of otherBranch into curBranch
  - A new commit will be created with the work of both curBranch and otherBranch
  - curBranch (which is a commit pointer) will point to this commit that just happened
  - otherBranch (pointer) wont be effected at all

## Rebasing

- Rebasing essentially takes a set of commits, "copies" them, and plops them down somewhere else
  - MORE TECHNICALLY: Rebasing involves moving the branch pointer to a new base commit and replaying the commits on top of it, creating new commits.

---

- In example below, **feature_branch** and **master** branch had common ancestor **F**. After a while both branches progressed. Lets say you want to incorporate changes of **master** into **feature_branch**.
  - You run `git rebase master` which tells vim to make base commit of feature_branch as **master** (i.e H). Vim copies all commits of *feature_branch* that are not in *master* and then replays them on top of the latest commit in *master*

**Before the rebase**:
<pre>
          A---B---C---D  feature_branch (before rebase)
         /
 ---E---F---G---H  master
</pre>
**After the rebase**:
<pre>
                   A'--B'--C'--D'  feature_branch (after rebase)
                  /
---E---F---G---H  master
</pre>

## Reversing changes

- `git reset` and `git revert` are used to reverse changes
- `git reset HEAD^` will remove the current commit as if it never happened
  - May find problems with remote repos as it rewrites history
- `git revert HEAD^` will add a new commit such that it exactly reverses the current commit
  - In other words, current state repo will be exactly the same as of HEAD^ but the commit at will not be removed

## Cherry picking

- `git cherry-pick <commit1> <commit2> ...` apply the selected commits to current branch
  - New commits will be created with the changes from the cherry-picked commits.

## Interactive rebasing

- `git rebase -i ref` open interactive rebasing commits
  - **ref** can be a commit hash of reference such as `main^`

- ### Pick (p)

  - Leaves the commit as it is

- ### Squash (s)

  - Repackage multiple commits into single commit (basically rewrites commit history)

- ### Reword (r)

  - Modify commit messages

- ### Reorder

  - Reorder commits
  - Simple move the lines up and below