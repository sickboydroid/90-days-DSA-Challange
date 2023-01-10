# Leetcode Log

## 171. Linked List Cycle

__Approach 1:__ Use HashMap
  
    - TC: O(n)
    - SC: O(n)

__Approach 2:__ [Floyd's Cycle Detection Algorithm](https://youtu.be/jcZtMh_jov0)
    - Uses two pointers, slow and fast pointer
    - Slow steps one node at a time and Fast steps two nodes at a time
    - Case 1:
      - If there is a loop then the fast pointer gets trapped in it
      - Slowly the slow pointer also reaches it and both stay in loop.
      - At some point both pointers point to the same node and thus we found the loop
    - Case 2:
      - If there is no loop then fast pointer reaches the end and we return
    - Finding node where loop starts
      - TC: O()
      - SC: O(1)
