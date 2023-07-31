# Blind75 Solutions

## Graph

### [Clone Graph](https://leetcode.com/problems/clone-graph/)

- dfs

### [Course Schedule](https://leetcode.com/problems/course-schedule/)

- Cycle in graph indicates course can't be completed
- Use three states for each course
  - 0: Not sure: I haven't started testing whether this course can be completed or not
  - 1: Testing: Currently i am walking through the portion of graph of which this is part.
    - If you encounter course with 1, there is a cycle
  - -1: Completed: Course that has been completed so any course whose prerequisite is this can also be completed
