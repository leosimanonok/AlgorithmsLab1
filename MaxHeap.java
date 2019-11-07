package avlTree;

import java.util.ArrayList;
import java.util.List;

/*
 * MaxHeap Class written by Leo Simanonok
 */

public class MaxHeap  {
	protected List<Integer> elem;
	protected int size;
	
	/*
	 * Constructor method for the MaxHeap class. Initializes 
	 * size to 0 and creates a new ArrayList to store values.
	 */
	public MaxHeap() {
		
		size = 0;
		elem = new ArrayList<Integer>();
	}
	
	/*
	 * This method makes sure that the value at index i still fulfills 
	 * the max heap property
	 * @param i - index of the value being checked
	 * @return none - just makes changes to the array stored in the maxHeap object
	 */
	public void maxHeapify(int i) {
		int left, right, largerChild, temp;
		if (i > this.size - 1) {
			// making sure i isnt out of bounds of the array
			return;
		}
		
		
		left = 2 * i;
		right = left + 1;
		
		if (left > this.size - 1) {
			// making sure children exist and we aren't going to get an out of bounds error
			return;
		} else if (right > this.size - 1) {
			largerChild = left;
		} else {
			// if right child index if out of range of list, and left isn't, left must be the greater child.
			
		
			if (elem.get(left).compareTo(elem.get(right)) > 0) {
				// comparing the sizes of two existing children
				largerChild = left;
			} else {
				largerChild = right;
			}
		}
		
		if (elem.get(i).compareTo(elem.get(largerChild)) < 0) {
			// if the value at the index of largerChild is greater than that of the parent node at i, they need to be swapped.
			temp = elem.get(i);
			elem.remove(i);
			elem.add(i, elem.get(largerChild - 1));
			elem.remove(largerChild);
			elem.add(largerChild, temp);
		
			maxHeapify(largerChild);
		}
		
	}
	/*
	 * This method takes in a list of integers and makes it into a max heap.
	 * This is accomplished by calling maxHeapify on all the indicies that 
	 * could possibly not be fulfilling the max heap property, namely all 
	 * the values that aren't leaves (mathmatically this is all the indicies 
	 * in the first half of the list, because the tree is assumed to be 
	 * nearly complete).
	 * @param list - a list of integers which will be made into a maxHeap
	 * @return maxHeap - the created max heap is returned
	 */
	public static MaxHeap buildMaxHeap(List<Integer> list) {
		MaxHeap maxHeap = new MaxHeap();
		maxHeap.size = list.size();
		maxHeap.elem = list;
		
		for (int i = maxHeap.size/2; i > 0; i--) {
			maxHeap.maxHeapify(i);
		}
		
		return maxHeap;	
	}
	/*
	 * HeapSort makes the input, and unsorted list, into a maxHeap, and then 
	 * continuously swaps the root of the maxHeap, which will always be the largest 
	 * element in the heap, with the last element in the heap. It then decrements the size of the heap
	 * and calls maxHeap on the root of the heap, which restores the max heap property to the heap.
	 * @param list - list to be sorted
	 * @return heap.elem - the sorted list is stored as the elements of the previously created heap
	 */
	public static List<Integer> heapSort(List<Integer> list) {
		int temp,size;
		MaxHeap heap = buildMaxHeap(list);
		size = heap.size;
		
		for(int i = size - 1; i >= 1; i--) {
			temp = heap.elem.get(1);
			heap.elem.remove(1);
			heap.elem.add(1, heap.elem.get(i - 1));
			heap.elem.remove(i);
			heap.elem.add(temp);
			heap.size--;
			heap.maxHeapify(1);
		}
		return heap.elem;
	}

	public static void main(String[] args) {
		// testing 
		
		
		MaxHeap test;
		List<Integer> testList = new ArrayList<Integer>();
		testList.add(0); // not a part of the list, just want to start at index 1
		testList.add(23);
		testList.add(35);
		testList.add(98);
		testList.add(34);
		testList.add(12);
		testList.add(2);
		testList.add(39);
		testList.add(56);
		testList.add(99);
		
		System.out.println(testList);
		
		test = MaxHeap.buildMaxHeap(testList);
		
		System.out.println(test.elem);
		System.out.print(heapSort(testList));
		
	}

}
