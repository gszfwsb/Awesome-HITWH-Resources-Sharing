
public class QuickSort implements SortAlgorithm {
	private int[] intArray;
	private int arrLength;

	public int[] sort(int[] intArray, Context ct) {
        ct.startExecution();

		this.intArray = intArray;
		arrLength = intArray.length;
		quicksort(0, arrLength - 1);

		ct.endExecution();
		return intArray;
	}

	private void quicksort(int low, int high) {
		int i = low, j = high;
		// Get the element from the middle of the array
		int axis = intArray[(low + high) / 2];

		// Divide the array into two patrts
		while (i <= j) {
			// If the current integer from the left part is smaller then the axis
			// element, then we get the next element from the left part
			while (intArray[i] < axis) {
				i++;
			}
			// If the current value from the right part is larger then the axis
			// element then we get the next element from the right list
			while (intArray[j] > axis) {
				j--;
			}

			// If we found a value in the left part that is biggerr than
			// the axis element and if we found a value in the right list
			// that is smaller than the axis element then we must swap the
			// intArray. After this, we increase i and decrease j.
			if (i <= j) {
				   int temp = intArray[i];
				   intArray[i] = intArray[j];
		           intArray[j] = temp;
		           //System.out.println("swap " + intArray[i] + ", " + intArray[j]);
				i++;
				j--;
			}
		}
		// Recursive calls
		if (low < j)
			quicksort(low, j);
		if (i < high)
			quicksort(i, high);

	}
}
