# Merge Sort

Actual code

```java
   /*
    * Time Complexity: Theta(nlogn)
    *
    * It sorts 100 million integers in just 30 secs!!!
    * */
public void mergeSort(int[] arr) {
    int arrLen = arr.length;
    if(arrLen == 1)
        return;

    // leftUpperBound is not included
    int leftUpperBound = arrLen/2;
    // rightLowerBound is included
    int rightLowerBound = leftUpperBound;

    int[] leftHalf = Arrays.copyOfRange(arr, 0, leftUpperBound);
    int[] rightHalf = Arrays.copyOfRange(arr, leftUpperBound, arrLen);

    mergeSort(leftHalf);
    mergeSort(rightHalf);

    merge(arr, leftHalf,rightHalf);
}

public void merge(int[] mergedArr, int[] leftHalf, int[] rightHalf) {
    int lenLeftHalf = leftHalf.length;
     int lenRightHalf = rightHalf.length;
    int iMergedArr = 0, iLeftHalf = 0, iRightHalf = 0;
    while(iLeftHalf < lenLeftHalf && iRightHalf < lenRightHalf) {
        if(leftHalf[iLeftHalf] <= rightHalf[iRightHalf]) // change <= to >= to reverse order of sorted array
            mergedArr[iMergedArr++] = leftHalf[iLeftHalf++];
        else
            mergedArr[iMergedArr++] = rightHalf[iRightHalf++];
    }
    while(iLeftHalf < lenLeftHalf)
        mergedArr[iMergedArr++] = leftHalf[iLeftHalf++];
    while(iRightHalf < lenRightHalf)
        mergedArr[iMergedArr++] = rightHalf[iRightHalf++];
}
```
