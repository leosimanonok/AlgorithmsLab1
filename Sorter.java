package avlTree;

/*
 * Sorter Class written by Leo Simanonok
 */

public class Sorter {
	
	/*
	 * Helper method for merger sort, which re-merges lists.
	 * @param list[] - array that is being sorted
	 * @param left - index of first element in subarray
	 * @param middle - index of middle element in subarray
	 * @param right - index of last element of subarray
	 */
	private static void merge(int list[], int left, int middle, int right) {
		int tempList[] = new int[right - left + 1];
		
		int i = left, j = middle + 1, k = 0;
		
		while (i <= middle && j <= right) {
			if (list[i] <= list[j]) {
				tempList[k] = list[i];
				k++;
				i++;
			} else {
				tempList[k] = list[j];
				k++;
				j++;
			}
		}
		
		while (i <= middle ) {
			tempList[k] = list[i];
			k++;
			i++;
		}
		
		while (j <= right) {
			tempList[k] = list[j];
			k++;
			j++;
		}
		
		for (i = left; i <= right; i++) {
			list[i] = tempList[i - left];
		}
	}
	
	/*
	 * Method which recursively splits list in half and puts the list in order as the sub lists are merged
	 * @param list - unsorted list
	 * @param left - index of first element of sublist
	 * @param right - index of last element of sublist
	 */
	public static void mergeSort(int list[], int left, int right) {
		if (left < right) {
		
			int middle;
			middle = (right + left) / 2;
		
			mergeSort(list, left, middle);
			mergeSort(list, middle + 1, right);
		
			merge(list, left, middle, right);
		}
		
	}
	
	/*
	 * Helper method for quick sort. This method partitions the list into the elements greater than the pivot element, and those less than the 
	 * pivot element.
	 * @param list - unsorted list
	 * @param low - index of first element of sub list
	 * @param high - element of last element of sub list
	 * @return i+1 - index where the splitting took place
	 */
	public static int splitList(int list[], int low, int high) {
		int pivot, i, temp;
		
		pivot = list[high];
		i = low - 1;
		for (int j = low; j < high; j++) {
			if (list[j] < pivot) {
				i++;
				temp = list[i];
				list[i] = list[j];
				list[j] = temp;
			}
		}
		
		temp = list[i+1];
		list[i+1] = list[high];
		list[high] = temp;
		return i+1;
	}
	
	/*
	 * Method for sorting a list that recrursively partitions the list around a pivot element
	 * @param list - unsorted list
	 * @param low - index of first element of sublist
	 * @param high - index of last element of sublist
	 */
	public static void quickSort(int list[], int low, int high) {
		int splitIndex;
		if (low < high) {
			splitIndex = splitList(list, low, high);
			quickSort(list, low, splitIndex - 1);
			quickSort(list, splitIndex + 1, high);
			
		}
	}
}
