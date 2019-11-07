package avlTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.lang.Math;

/*
 * Written by Leo Simanonok
 * Performance tester class tests the two search trees we have studied and the three sorting methods
 */

public class PerformanceTest {
	
	/*
	 * this method finds the average time it takes to search a BST of a given size
	 * @param sizeOfTree - the size of the BST you want to search
	 * @param numTrees - the number of BSTs you want to average over
	 * @param numSearches - the number of searches you want to perform on each BST
	 * @return avgTime - the average time it takes for each search
	 */
	protected static double avgBSTSearchTime(int sizeOfTree, int numTrees, int numSearches) {
		Random r = new Random();
		double avgTime;
		long totalTime = 0;
		
		for (int i = 0; i < numTrees; i++) {
			// creates a numTree amount of BSTs
			BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
			for (int j = 0; j < sizeOfTree; j++) {
				// inserts sizeOfTree number of random ints into bst
				bst.insert(r.nextInt(1000000000));
			}
			for (int k = 0; k < numSearches; k++) {
				
				
				long t_i = System.nanoTime();
				bst.contains(r.nextInt(1000000000)); //searches for a random number
				long t_f = System.nanoTime();
				
				if (i > numTrees/2) { //initial searches take much longer than rest, so we only average the second half of the trees created
					
					totalTime += (t_f - t_i);
				}
				
				
			}
			
			bst.elem.clear();
		}
		
		avgTime = ( (double) totalTime)/(numTrees/2 * numSearches);
		
		return avgTime;
	}
	
	/*
	 * this method finds the average time it takes to search a AVL tree of a given size
	 * @param sizeOfTree - the size of the AVL tree you want to search
	 * @param numTrees - the number of AVL trees you want to average over
	 * @param numSearches - the number of searches you want to perform on each AVL tree
	 * @return avgTime - the average time it takes for each search
	 */
	protected static double avgAVLSearchTime(int sizeOfTree, int numTrees, int numSearches) {
		Random r = new Random();
		double avgTime;
		long totalTime = 0;
		
		for (int i = 0; i < numTrees; i++) {
			// creates a numTree amount of AVLs
			AVLTree<Integer> avl = new AVLTree<Integer>();
			
			for (int j = 0; j < sizeOfTree; j++) {
				// inserts sizeOfTree number of random ints into AVL
				avl.insert(r.nextInt(1000000000));
			}
			
			for (int k = 0; k < numSearches; k++) {
				long t_i = System.nanoTime();
				avl.contains(r.nextInt(1000000000));//searches for a random number
				long t_f = System.nanoTime();
				if (i > numTrees/2) { //initial searches take much longer than rest, so we only average the second half of the trees created
					
					totalTime += (t_f - t_i);
				}
			}
			avl.elem.clear();
		}
		
		
		avgTime = ( (double) totalTime)/(numTrees/2 * numSearches);
		
		return avgTime;
	}
	
	/*
	 * This method returns the average amount of time it takes a mergeSort algorithm to sort a list of a given size
	 * @param sizeOfList - size of the list you want to sort
	 * @param numLists - the number of lists you want to average over
	 * @return avgTime - the average time it takes to sort a list of the input size
	 */
	protected static double avgMergeSortTime(int sizeOfList, int numLists) {
		Random r = new Random();
		double avgTime;
		long totalTime = 0;
		int testList[] = new int[sizeOfList];
		
		for (int i = 0; i < numLists; i++) {
			// creates a numLists amount of randomly generated lists
			
			for (int j = 0; j < sizeOfList; j++) {
				//inserts sizeOfList number of elements into each list
				testList[j] = r.nextInt(1000000);
			}
			long t_i = System.nanoTime();
			Sorter.mergeSort(testList, 0, testList.length - 1);
			long t_f = System.nanoTime();
			if (i > numLists/2) { //initial sorts take much longer than rest, so we only average the second half of the sorts
				totalTime += (t_f - t_i);
			}
		}
		

		
		avgTime = ( (double) totalTime)/(numLists/2);
		
		return avgTime;
	}
	
	/*
	 * This method returns the average amount of time it takes a quickSort algorithm to sort a list of a given size
	 * @param sizeOfList - size of the list you want to sort
	 * @param numLists - the number of lists you want to average over
	 * @return avgTime - the average time it takes to sort a list of the input size
	 */
	protected static double avgQuickSortTime(int sizeOfList, int numLists) {
		Random r = new Random();
		double avgTime;
		long totalTime = 0;
		int testList[] = new int[sizeOfList];
		
		for (int i = 0; i < numLists; i++) {
			// creates a numLists amount of randomly generated lists
			
			for (int j = 0; j < sizeOfList; j++) {
				//inserts sizeOfList number of elements into each list
				testList[j] = r.nextInt(1000000);
				
			}
			long t_i = System.nanoTime();
			Sorter.quickSort(testList, 0, testList.length - 1);
			long t_f = System.nanoTime();
			if (i > numLists/2) {
				//initial sorts take much longer than rest, so we only average the second half of the sorts
				totalTime += (t_f - t_i);
			}
		}
		

		
		avgTime = ( (double) totalTime)/(numLists/2);
		
		return avgTime;
	}
	
	/*
	 * This method returns the average amount of time it takes a heapSort algorithm to sort a list of a given size
	 * @param sizeOfList - size of the list you want to sort
	 * @param numLists - the number of lists you want to average over
	 * @return avgTime - the average time it takes to sort a list of the input size
	 */
	protected static double avgHeapSortTime(int sizeOfList, int numLists) {
		Random r = new Random();
		List<Integer> testList = new ArrayList<Integer>();
		
		double avgTime;
		long totalTime = 0;
		
		for (int i = 0; i < numLists; i++) {
			// creates a numLists amount of randomly generated lists
			
			for (int j = 0; j < sizeOfList; j++) {
				//inserts sizeOfList number of elements into each list
				testList.add(r.nextInt(1000000));
				
			}
			long t_i = System.nanoTime();
			MaxHeap.heapSort(testList);
			long t_f = System.nanoTime();
			testList.clear();
			if (i > numLists/2) {
				//initial sorts take much longer than rest, so we only average the second half of the sorts
				totalTime += (t_f - t_i);
			}

			
		}
		
		avgTime = ( (double) totalTime)/(numLists/2);
		
		return avgTime;
	}

	public static void main(String[] args) {
		// finding the average search time for BST trees ranging from 10 - 1,000,000 elements
		for (int i = 1; i < 7; i++) {
			double treeSize = Math.pow(10, i);
			int treeSizeInt = (int) treeSize;
			if (i != 6) {
				for (int j = 1; j < 6; j++ ) {
					// for all trees less than 1,000,000 elements, get trees from 1-5, eg trees of size 10,20,30,40,50 etc
					double testBST = avgBSTSearchTime(treeSizeInt * j, 100, 50);
					System.out.printf("Average BST search time with %d elements: %.2f nanoseconds\n", treeSizeInt * j, testBST);
				}
			} else {
				double testBST = avgBSTSearchTime(treeSizeInt, 100, 50);
				System.out.printf("Average BST search time with %d elements: %.2f nanoseconds\n", treeSizeInt, testBST);
			}
		}
		
		// finding the average search time for AVL trees ranging from 10 - 1,000,000 elements
		for (int i = 1; i < 7; i++) {
			double treeSize = Math.pow(10, i);
			int treeSizeInt = (int) treeSize;
			if (i != 6) {
				for (int j = 1; j < 6; j++) {
					// for all trees less than 1,000,000 elements, get trees from 1-5, eg trees of size 10,20,30,40,50 etc
					double testAVL = avgAVLSearchTime(treeSizeInt * j, 100, 50);
					System.out.printf("Average AVL search time with %d elements: %.2f nanoseconds\n", treeSizeInt * j, testAVL);
				}
			} else {
			double testAVL = avgAVLSearchTime(treeSizeInt, 100, 50);
			System.out.printf("Average AVL search time with %d elements: %.2f nanoseconds\n", treeSizeInt, testAVL);
			}
		}
		
		// finding the average sort time of merge sort on lists ranging from 10 - 1,000,000 elements
		for (int i = 1; i < 7; i++) {
			double listSize = Math.pow(10, i);
			int listSizeInt = (int) listSize;
			if (i != 6) {
				for (int j = 1; j < 6; j++) {
					// for all lists less than 1,000,000 elements, get lists from 1-5, eg lists of size 10,20,30,40,50 etc
					double testMergeSort = avgMergeSortTime(listSizeInt*j, 50);
					System.out.printf("Average MergeSort run time with %d elements: %.2f nanoseconds\n", listSizeInt*j, testMergeSort);
				}
			} else {
			double testMergeSort = avgMergeSortTime(listSizeInt, 50);
			System.out.printf("Average MergeSort run time with %d elements: %.2f nanoseconds\n", listSizeInt, testMergeSort);
			}
		}
		
		//		// finding the average sort time of quick sort on lists ranging from 10 - 1,000,000 elements
		for (int i = 1; i < 7; i++) {
			double listSize = Math.pow(10, i);
			int listSizeInt = (int) listSize;
			if (i != 6) {
				for (int j = 1; j < 6; j++) {
					// for all lists less than 1,000,000 elements, get lists from 1-5, eg lists of size 10,20,30,40,50 etc
					double testQuickSort = avgQuickSortTime(listSizeInt*j, 50);
					System.out.printf("Average QuickSort run time with %d elements: %.2f nanoseconds\n", listSizeInt*j, testQuickSort);
				}
			} else {
			double testQuickSort = avgQuickSortTime(listSizeInt, 50);
			System.out.printf("Average QuickSort run time with %d elements: %.2f nanoseconds\n", listSizeInt, testQuickSort);
			}
		}
		
		// finding the average sort time of heap sort on lists ranging from 10 - 1,000,000 elements
		for (int i = 1; i < 7; i++) {
			double listSize = Math.pow(10, i);
			int listSizeInt = (int) listSize;
			if (i != 6) {
				for (int j = 1; j < 6; j++) {
					// for all lists less than 1,000,000 elements, get lists from 1-5, eg lists of size 10,20,30,40,50 etc
					double testHeapSort = avgHeapSortTime(listSizeInt*j, 50);
					System.out.printf("Average HeapSort run time with %d elements: %.2f nanoseconds\n", listSizeInt*j, testHeapSort);
				}
			} else {
				double testHeapSort = avgHeapSortTime(listSizeInt, 50);
				System.out.printf("Average HeapSort run time with %d elements: %.2f nanoseconds\n", listSizeInt, testHeapSort);
			}
		}
		
	}
		
	
	
	

}
