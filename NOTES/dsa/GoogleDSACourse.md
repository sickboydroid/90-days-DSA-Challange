# Time complexity

- Usually denoted by O(n) where the n = input size
- Before calculating time complexity be sure to ask the case
  - Worst case (mostly)
  - Best case
  - Average case (real world case)

> e.g: O(n) means that some number of computations are performed on each element of input

- In list of python:
  - len => O(1)
  - append => O(1)
  - get slice => O(k)
  - set slice => O(k+n)

> Because n is used to represent number of elements in input array, k is used to represent the
> number of elements to be sliced here.

## Data Structures

Collection is a group of things. Every DS is an extension of collection.

## List

It is collection with order and no fixed length.

### Array

- It is a list in which every element has an index.
- Fast to access element [O(1)]
- Slow insertion and deletiion [O(n)]

### Linked List

- In general, an element in linked list contains value and reference of next element in list

- Insertion and deletion is easy but getting position of element is hard. There is no info
  about previous element in list.

### Doubly Linked List

- Same as linked list except that each element contains value and of both next and previous elemets.
- This makes it easy to traverse backwards

> _TIP_: Don't forget refrences while inserting or deleting elements.
