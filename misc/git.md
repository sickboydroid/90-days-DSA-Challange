# Git

- [Git](#git)
  - [Misc](#misc)
  - [Branching](#branching)
  - [Merging](#merging)
  - [Rebasing](#rebasing)
  - [Reversing changes](#reversing-changes)
  - [Cherry picking](#cherry-picking)
  - [Interactive rebasing](#interactive-rebasing)
  - [Tags](#tags)
  - [Relative referencing](#relative-referencing)
  - [Small Topics](#small-topics)
    - [gitignore](#gitignore)
  - [Remote](#remote)
    - [`git fetch`](#git-fetch)
    - [`git pull`](#git-pull)
    - [`git push`](#git-push)

## Misc

- Practice more: [Learn Git Branching](https://learngitbranching.js.org/)
- Common mistakes: [Oh Shit Git!!](https://ohshitgit.com/)
- Man Tutorial: `man gittutorial`
- Branches are pointers to specific commit
- Instead of mentioning whole hash of a commit, you can specify only few characters.
- If there conflicts in any command, you can use `--continue` after fixing them to continue the operation.
  - For example if cherry-picking caused some conflicts and you fixed those conflicts, run `git cherry-pick --continue` to continue cherry-picking
- Operations that modify commit history (e.g Interactive rebasing) should generally be avoided on commits that have already been pushed to a remote repository.
- `merging` and `rebasing` are two ways to combine work from different branches
  - (you are in **master** branch and want to combine work of **feature_x** work into it, *feature_x* branch won't be effected at all in both operations)
    1. `git merge feature_x` will create a new merged commit in *master* branch with the work of both the branches into it.
    2. `git rebase feature_x` will rebase the master branch
- `HEAD` symbolic name for currently checked out commit
  - `Detaching head` means attaching head to a commit rather than a branch
    - Even if the head and branch point to same commit, you can have detached head
- `@` alone is a short to *HEAD*
  - `<refname>@{<date>}` e.g *master@{yesterday}* or *master@{2 months 1 week 3 hours ago}*

## Branching

- **Branch early, and branch often**
- Branches are pointers to specific commit.
- Very light weight (no space is consumed)
- `git branch name` create a new branch
- `git checkout name` switch to name branch
- `git checkout -b name` create and switch to name branch
- `git switch name` similar to `git checkout` but still experimental
- `git branch -f main dest_ref` (forcefully) reassigns main branch to some other commit. (dest_ref can be another branch, commit hash or relative ref like HEAD~4)

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
- `git rebase <new_base_ref> <ref>` will rebase the ref on new_base_ref

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

## Tags

- Tags (aka anchors) act as (permanent) milestones. Contrary to tags, branches are always changing and temporary
- `git tag v1.0 <ref>` will create a tag at ref. If ref not given then HEAD will be used
- `git describe <ref>` will give you output of the form `<tag>_<num_commits>_g<hash>` where `tag` is the closest ancestor tag from which your ref or commit with hash `ref` is `num_commits` away.

## Relative referencing

- `Relative refs` convenient way of avoiding hashes by moving relative to branch or head
- `^` move up by one commit. (git checkout master^^^ moves you 3 parents up from master)
  - `<ref>^0` refers to the ref itself
  - `<ref>^1` or `<ref>^` moves refers to the first parent of ref
  - `<ref>^n` refers to the nth parent of ref
- `~<num>` move up by num commits. (git checkout master~3 moves you 3 parents up from master)
  - It follows only the first parent
- Some commits posses multiple parents (like the one created during merging).

---

- Here is an example. Both commit nodes B and C are parents of commit node A. Parent commits are ordered left-to-right. Thus B is first parent and C is 2nd parent of A

<pre>
           G   H   I   J
            \ /     \ /
             D   E   F
              \  |  / \
               \ | /   |
                \|/    |
                 B     C
                  \   /
                   \ /
                    A

           A =      = A^0
           B = A^   = A^1     = A~1
           C =      = A^2
           D = A^^  = A^1^1   = A~2
           E = B^2  = A^^2
           F = B^3  = A^^3
           G = A^^^ = A^1^1^1 = A~3
           H = D^2  = B^^2    = A^^^2  = A~2^2
           I = F^   = B^3^    = A^^3^
           J = F^2  = B^3^2   = A^^3^2
</pre>

## Small Topics

### gitignore

- Every gitignore can effect only files at or below its level
- `/dir/file` */* refers to the dir of gitignore
- `/dir/*.txt` matches all text files recursively in dir directory
- `*.txt` matches all text files recursively in directory gitignore

---

- `!` -> negates pattern
- `#` -> comment

## Remote

- `git clone` creates local copy of remote repository
- Remote branches are special branches that reflect the state of remote repository. You cannot commit on them directly as then they won't reflect remote changes. You cannot assign them to some different commit either i.e `git branch -f origin/main <commit_hash>` will fail (or create a new local branch which will be diff from remote branch)
  - They are special in the sense that when you check them out, git puts you in *detached head* state
  - This is done so that you don't directly make any public changes
  - `<remote_name>/<branch_name>` is the *required* naming convention for remote branches
- By default when you clone repo, `origin` name is given the remote

### `git fetch`

- Performs two steps:
  1. Downloads commits that our local representation of remote does not have
  2. Updates out remote branches (i.e for example which commit `origin/main` points to)
- It essentially synchronizes our local copy of remote repo with actual remote repo

### `git pull`

- Performs two steps:
  1. Run *git fetch*
  2. Merge whichever branch you downloaded with local corresponding branch

### `git push`

- Publish your changes to remote
- If the remote has changed, i.e you have done work on the older version of repo, you can run `git fetch; git rebase origin/main main; git push` or shorthand `git pull --rebase; git push`.
  - You can also do `git fetch; git merge origin/main main; git push` ro shorthand `git pull; git push`
