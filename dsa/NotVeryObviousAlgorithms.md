# Algos

- [Algos](#algos)
  - [Boyer-Moore Voting Algorithm](#boyer-moore-voting-algorithm)
  - [Sieve of Eratosthenes (For primes)](#sieve-of-eratosthenes-for-primes)
  - [Pascal's triangle](#pascals-triangle)
  - [Reversing Singly Linked List](#reversing-singly-linked-list)
  - [Num to Binary String](#num-to-binary-string)

## Boyer-Moore Voting Algorithm

- Majority element is element which occures more than n/2 times in array
- Element given by this algo is the majority element (if exists)
- You have to check if the array has majority element so TC is O(2n) = O(n)
- It works cause all elements together can make cound -(n/2 - 1) in worst case and majority element will make it still positive for it

> TC: O(n)
> SC: O(1)

```java
public int mooreVotingAlgo(int[] arr) {
   // algo
   int candidateIndex = 0, count = 1;
   for(int i = 1; i < arr.length; i++) {
      if(arr[candidateIndex] != arr[i]) {
         if(--count == 0) {
            candidateIndex = i;
            count = 1;
         }
      } else count++;
   }

   // verification
   // if there is majority element then it is candidateIndex
   int candidateFreq = 0;
   for(int i = 0; i < arr.length; i++) {
      if(arr[i] == arr[candidateIndex])
         candidateFreq++;
   }

   if(candidateFreq > arr.length/2f)
      return candidateIndex;
   return -1;
}
```

## Sieve of Eratosthenes (For primes)

```java
 /*
 * Till bound = 100 million it took less than 1 sec
 * At bound = 1 billion it took 13 sec
 * At bound = 2 billion it took 28 sec
 * */
 public static void printPrimes2(int bound) {
   // Initially nums is filled with false
   // Algorithm we will mark those elements true whose divisor have been found
   boolean[] nums = new boolean[bound + 1];

   // Algorithm: sieve of Eratosthenes
   // For any prime p upto n: p <= sqrt(n)
   for (int divisor = 2; divisor * divisor <= bound; divisor++)
      if (!nums[divisor]) // is divisor composite? then skip
      for (int i = divisor * 2; i <= bound; i += divisor) // Remove multiples of (prime) divisor
      nums[i] = true;

   // Counting number of primes and printing them
   int count = 0;
   for (int i = 2; i <= bound; i++) {
      if (!nums[i]) {
   //    System.out.print(" " + i);
      count++;
      }
  }

  System.out.println("\nThere are total " + count + " prime numbers upto " + bound + " (inclusive)");
 }
```

## Pascal's triangle

```java
/*
 * For nested loop:
 *   element at j = (element at j - 1 in prev row) + (element at j in prev row)
 * */
 public static int[][] pascalsTriangle(int n) {
   int[][] triangle = new int[n][];
   for (int i = 0; i < triangle.length; i++) {
      int[] row = new int[i+1];
      row[0] = 1;
      row[row.length - 1] = 1;
      for (int j = 1; j < row.length - 1; j++) {
         row[j] = triangle[i - 1][j-1] + triangle[i - 1][j];
      }
      triangle[i] = row;
   }
   return triangle;
 }
```

## Reversing Singly Linked List

- Reversing the next pointers
- Every element points to its parent node

**Approach 1:** Iterative:

- Three pointers: prevNode, curNode and nextNode

```java
Node prevNode = null;
Node curNode = head;
Node nextNode = null;
while(curNode != null) {
   nextNode = curNode.next;
   curNode.next = prevNode;
   prevNode = curNode;
   curNode = nextNode;
}
```

**Approach 2:** Recursive:

## Num to Binary String

- Divide by 2 and write remainder in each step
- Reverse remainders to get binary

```java
public static String toBinary(int num) {
   if(num == 1 || num == 0)
         return "" + num;
   return toBinary(num/2) + (num%2);
}
```
