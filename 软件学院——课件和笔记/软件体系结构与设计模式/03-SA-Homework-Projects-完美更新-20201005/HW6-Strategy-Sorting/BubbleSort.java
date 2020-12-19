
//====================================================== //
//Main idea is that we consider the elements in an integer array
//to be bubbles£¬with smaller elements less weighted and thus
//will go upper
//Algorithm: suppose that the array to be sorted is a[100]:
//a[99],a[98],a[97],a[96],...,a[3],a[2],a[1],a[0]
//compare a[99] and a[98]£¬if a[99]< a[98],then swap a[99]and a[98]
//compare a[98] and a[97], if a[98]< a[97],then swap a[98]and a[97]
//finally£¬compare a[1] and a[0]
//After this processing step, the smallest element goes to the a[0]£»
//Then we process the array a second time the same way, the 2nd
//smallest goes to a[1].When we process the 2nd time, it is not
// necessary to consider a[0]. Generally, in the k-th process, it is
// not necessary to consider
//                    a[k-1],a[k-2],... a[0]
//because they have already been in correct order
//=======================================================//

public class BubbleSort implements SortAlgorithm {

   public int[] sort(int[] intArray, Context ct){

      ct.startExecution();
      for(int i = intArray.length; --i >= 0;)
         for(int j = 0; j < i; j++){
            if(intArray[j] > intArray[j + 1]){

               //exchange intArray[j+1] with intArray[j]
               int T = intArray[j];
               intArray[j] = intArray[j + 1];
               intArray[j + 1] = T;
            }
         }
      ct.endExecution();
      return intArray;
   }
}