//======================================================
// Main idea of the algorithm
// Start from the beginning of the integer array a[ ] to be sorted
// Compare a[1] and a[0], if a[1] < a[0], then swap them
// generally, compare  a[i] with all the integers  holding in
//                                    a[0], ... ,a[i-1],
// a[i] is the numToBeInserted, start from the right hand side
// of the above partial sequence, whenever  numToBeInserted
// is smaller than anyone, then shift that item 1 position right
// to make room for numToBeInserted to be inserted in...
// Finally the correct position is found and numToBeInserted
// is inserted into that position. Note that all the numbers in front of
// position i will have to be sorted after the assignmnt statement
//                       nums[j] = numToBeInserted
// See code below
//======================================================


/* Example:
   18 19 20 5 1 88 89 90 25 15 14 13
    |
   18 19 20 5 1 88 89 90 25 15 14 13
          |
   18 19 20 5 1 88 89 90 25 15 14 13
               |
   18 19 20 5 1 88 89 90 25 15 14 13
                    |
   5  18 19 20 1 88 89 90 25 15 14 13
                        |
   1  5  18 19 20 88 89 90 25 15 14 13
                              |
   1  5  18 19 20 88 89 90 25 15 14 13
                                   |
   1  5  18 19 20 88 89 90 25 15 14 13
                                         |
   1  5  18 19 20 88 89 90 25 15 14 13
                                               |
   1  5  18 19 20 25 88 89 90 15 14 13
                                                    |
   1  5  15 18 19 20 25 88 89 90 14 13
                                                          |
   1  5  14 15 18 19 20 25 88 89 90 13
                                                               |
   1  5  13 14 15 18 19 20 25 88 89 90
*/

public class InsertSort implements SortAlgorithm {

    public int[] sort(int[] intArray, Context ct){
		ct.startExecution();
       for (int i = 1; i < intArray.length; i++){
          int j = i;
          int numToBeInserted = intArray[i];

          while ((j > 0) && (intArray[j-1] > numToBeInserted) ) {
             intArray[j] = intArray[j-1];
             j--;
          }
          intArray[j] = numToBeInserted;
       }
       ct.endExecution();
       return intArray;
   }
}