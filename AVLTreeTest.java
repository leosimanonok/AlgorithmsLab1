package avlTree;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AVLTreeTest {
	
	@Test
	public void testInsert() {
		
		AVLTree<Integer> avl = new AVLTree<Integer>();
		avl.insert(9);
		avl.insert(5);
		avl.insert(23);
		avl.insert(2);
		//test outer right rotation
		avl.insert(1);
		
		//checking rotation was completed correctly
		assertSame(2, avl.root.left.element);
		assertEquals(1, avl.root.left.height);
		assertEquals(0, avl.root.left.right.height);
		
		//test inner right rotation
		avl.insert(8);
		
		assertSame(5, avl.root.element);
		assertEquals(2, avl.root.height);
		assertSame(9, avl.root.right.element);
		assertSame(8, avl.root.right.left.element);
		assertSame(23, avl.root.right.right.element);
		
		//test outer left rotation
		avl.insert(25);
		avl.insert(27);
		
		assertEquals(3, avl.root.height);
		assertSame(25, avl.root.right.right.element);
		assertSame(23, avl.root.right.right.left.element);
		assertSame(27, avl.root.right.right.right.element);
		assertEquals(1, avl.root.right.right.height);
		
		//test inner left rotation
		avl.insert(24);
		
		assertSame(23, avl.root.right.element);
		assertEquals(2, avl.root.right.height);
		assertSame(25, avl.root.right.right.element);
		assertEquals(1, avl.root.right.right.height);
		assertSame(27, avl.root.right.right.right.element);
		assertSame(24, avl.root.right.right.left.element);
		assertEquals(1, avl.root.right.right.height);
		
	}
	
	
	@Test
	public void testRemove() {
		
		AVLTree<Integer> avl = new AVLTree<Integer>();
		avl.insert(7);
		avl.insert(5);
		avl.insert(2);
		avl.insert(9);
		avl.insert(8);
		avl.insert(3);
		avl.insert(1);
		
		
		assertEquals(7, avl.size);
		assertEquals(2, avl.root.height);
		
		List<Integer> test = avl.inOrderElements(avl.root);
		List<Integer> expected = new ArrayList<Integer>();
		expected.add(1);
		expected.add(2);
		expected.add(3);
		expected.add(5);
		expected.add(7);
		expected.add(8);
		expected.add(9);
		
		for (int i = 0; i < test.size(); i++) {
			assertEquals(expected.get(i), test.get(i));
		}
		
		avl.elem.clear();
		//remove element w 2 children
		avl.remove(2);
		//remove 2, which is at index 1, from our expected list
		expected.remove(1);
		test = avl.inOrderElements(avl.root);
		
		for (int i = 0; i < test.size(); i++) {
			assertEquals(expected.get(i), test.get(i));
		}
		//make sure height has been updated
		assertSame(1, avl.root.left.height);
		
		//checking that rotated items are in the right place
		
		assertSame(3, avl.root.left.element);
		assertEquals(2, avl.root.height);
		
		
		avl.elem.clear();
		//remove element w 1 child
		avl.remove(3);
		//remove 3, which is at index 1
		expected.remove(1);
		test = avl.inOrderElements(avl.root);
		
		for (int i = 0; i < test.size(); i++) {
			assertEquals(expected.get(i), test.get(i));
		}
		
		assertEquals(0, avl.root.left.height);
		assertSame(1, avl.root.left.element);
		assertEquals(2, avl.root.height);
		
		avl.elem.clear();
		//remove element w no children
		avl.remove(9);
		//remove 9, which is at index 4
		expected.remove(4);
		
		test = avl.inOrderElements(avl.root);
		
		for (int i = 0; i < test.size(); i++) {
			assertEquals(expected.get(i), test.get(i));
		}
		
		//making sure that 9 was removed properly
		assertSame(null, avl.root.right.right);
		assertEquals(2, avl.root.height);
	
		
	}

}
