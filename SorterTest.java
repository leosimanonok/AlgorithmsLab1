package avlTree;

import static org.junit.Assert.*;

/*
 * Tester method written by Leo Simanonok
 */

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SorterTest {

	@Test
	public void testHeapSort() {
		// need the 0 bc heap sort assumes first index is index 1
		int test[] = {0, 99, 47, 23, 55, 100, -15, - 4, 6, 17, 15, 29};
		int expected[] = {0, 100, 99, 55, 47, 29, 23, 17, 15, 6, -4, -15};
		List<Integer> testList = new ArrayList<Integer>();
		
		for (int i = 0; i < test.length; i++) {
			testList.add(test[i]);
		}
		
		testList = MaxHeap.heapSort(testList);
		for (int j = 0; j < expected.length; j++) {
			assertSame(expected[j], testList.get(j));
		}
		
	}
	
	@Test
	public void testMergeSort() {
		int test[] = {99, 47, 23, 55, 100, -15, - 4, 6, 17, 15, 29};
		int expected[] = {-15, -4, 6, 15, 17, 23, 29, 47, 55, 99, 100};
		
		Sorter.mergeSort(test, 0, test.length - 1);
		
		for (int j = 0; j < expected.length; j++) {
			assertSame(expected[j], test[j]);
		}
	}

	@Test
	public void testQuickSort() {
		int test[] = {99, 47, 23, 55, 100, -15, - 4, 6, 17, 15, 29};
		int expected[] = {-15, -4, 6, 15, 17, 23, 29, 47, 55, 99, 100};
		
		Sorter.quickSort(test, 0, test.length - 1);
		
		for (int j = 0; j < expected.length; j++) {
			assertSame(expected[j], test[j]);
		}
	}
}
