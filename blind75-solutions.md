# Blind75 Solutions

## Graph

### [Clone Graph](https://leetcode.com/problems/clone-graph/)

- dfs

### [Course Schedule](https://leetcode.com/problems/course-schedule/)

- <b style='color: black; background-color: #FDFF32; padding: 3px; border: 4px solid tomato;'>REWISE: 0</b>
- Cycle in graph indicates course can't be completed
- Use three states for each course
  - 0: Not sure: I haven't started testing whether this course can be completed or not
  - 1: Testing: Currently i am walking through the portion of graph of which this is part.
    - If you encounter course with 1, there is a cycle
  - -1: Completed: Course that has been completed so any course whose prerequisite is this can also be completed

### [Pacific Atlantic Water Flow](https://leetcode.com/problems/pacific-atlantic-water-flow/)

- <b style='color: black; background-color: #FDFF32; padding: 3px; border: 4px solid tomato;'>REWISE: 0</b>
- **Question Things:**

  - Think in reverse, find all cells from pacific ocean from where rain water can enter in it. Since you are doing in reverse, you will move from a cell with LOWER height to a cell with HEIGHT height
  - Do same for other ocean
  - Take intersection of cells of both ocean to get your answer

- **Python things:**

  - `0 <= x < K` is correct python syntax ($#!t)
  - For moving to adjacent cells use following:

    ```python
    directions = ((0, 1), (1, 0), (0, -1), (-1, 0))
    for dr, dc in directions:
      new_r, new_c = r + dr, c + dc
      if 0 <= new_r < ROWS and 0 <= new_c < COLS:
        ...
    ```

### [Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/)

- <b style='color: black; background-color: #FDFF32; padding: 3px; border: 4px solid tomato;'>REWISE: 0</b>
- **Question Things:**
  - Use set
  - Iterate through the set and check if the current number is a start of sequence (i.e there is no other number 1 smaller than it). If it is find the length of sequence (i.e biggest number in this seq)
