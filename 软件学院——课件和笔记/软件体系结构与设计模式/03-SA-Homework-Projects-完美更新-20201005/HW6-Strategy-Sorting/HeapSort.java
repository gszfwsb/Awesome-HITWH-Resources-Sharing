//==================================================
// Heap sort steps:
// 1) build a heap out of the data set,
// 2) remove the largest item and placing it at the end of the
//     sorted array.
// 3) After removing the largest, item, reconstruct the heap,
//      removes the largest remaining item, and places it in the
//      next open position  from the end of the sorted array.
// This is repeated until there are no items left in the heap and
// the sorted array is full. Two arrays may be used for this
// algorithm, but often, one array can be used to hold both
// the binary heap tree (in the upper part of the tree) and
// the sorted elements (in the lower part of the tree)
//=================================================

public class HeapSort implements SortAlgorithm{

     public int[] sort(int intArray[ ], Context ct) {
		 ct.startExecution();
          for(int i=intArray.length; i>1; i--){
               buildBinaryHeapTree(intArray, i - 1);
               swapLeadingNodeWithLastNode(intArray, i - 1);
          }
          ct.endExecution();
          return intArray;
     }

     public void buildBinaryHeapTree(int array[], int arrayBound){
	       int leftChild, rightChild, biggerChild, temp;
	       int root = (arrayBound-1)/2;

           // Find the bigger child index
           for(int i=root; i>=0; i--) {
                 leftChild = (2*i)+1;
                 rightChild = (2*i)+2;

                 if((leftChild <= arrayBound) && (rightChild <= arrayBound)){
                    if(array[rightChild] >= array[leftChild])
                        biggerChild = rightChild;
                    else
                       biggerChild = leftChild;
                 }
                else{
                    if(rightChild > arrayBound)
                       biggerChild = leftChild;
                   else
                      biggerChild = rightChild;
                }

               //swap the integer contained in the bigger child index
               //with that in the current parent node
               if(array[i] < array[biggerChild]){
                   temp = array[i];
                  array[i] = array[biggerChild];
                  array[biggerChild] = temp;
               }
          }
           return;
	}

	public static void swapLeadingNodeWithLastNode(int array[], int arrayBound){
	      int  temp;
	      temp = array[0];
         array[0] = array[arrayBound];
         array[arrayBound] = temp;
          return;
	}
}