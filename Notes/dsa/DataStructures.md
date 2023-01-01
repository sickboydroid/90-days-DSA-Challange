# Data Structures

Collection is a group of things. Every DS is an extension of collection.

## List

It is collection with order and no fixed length.

- ### Array

  - It is a list in which every element has an index.
  - Fast to access element [O(1)]
  - Slow insertion and deletiion [O(n)]

- ### Linked List

  - In general, an element in linked list contains value and reference of next element in list

  - Insertion and deletion is easy but getting position of element is hard. There is no info
  about previous element in list.

- ### Doubly Linked List

- Same as linked list except that each element contains value and of both next and previous elemets.
- This makes it easy to traverse backwards

> _TIP_: Don't forget refrences while inserting or deleting elements.

## Arrays

- Three types of arrays:
   1. __Static Arrays__: Size is determined at compile time
   1. __Dynamically Allocated Arrays__: Size is determined at runtime
   1. __Dynamic arrays__: Size is not fixed.

- Constant time read and write access is prequisite for arrays
- Two dimensional array can be thought of as a plane.
``
arr[x][y]: 1st index for x (row) and 2nd index for y (cols).
``

## Dynamic Arrays

- Great video on [coursera here.](https://www.coursera.org/lecture/data-structures/dynamic-arrays-EwbnV)
- At minimum follow operations:
   1. get(i) => O(1) => Returns element at location i
   1. set(i, val) => O(1) => Sets element at location i
   1. pushback(val) => O(n) => adds an element to the end. It creates a new dynamically allocated array if size = capacity
   1. remove(i) => O(n) => removes element at location i
   1. size() => O(1) => returns current filled size of dynamically allocated array
- At minimum following stores:
   1. arr: pointer/ref to dynamically allocated array
   1. capacity: size of dynamically allocated array
   1. size: Number of elements presently in dynamically allocated array
- In java, `ArrayList` is dynamic array.
