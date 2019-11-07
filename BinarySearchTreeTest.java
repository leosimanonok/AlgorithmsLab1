package avlTree;

import static org.junit.Assert.*;

import org.junit.Test;

public class BinarySearchTreeTest {
		
	
	@Test
	public void testInsert() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.insert(1);
		bst.insert(2);
		bst.insert(3);
		bst.insert(4);
		
		assertSame(1, bst.root.element);
		assertSame(2, bst.root.right.element);
		assertSame(3, bst.root.right.right.element);
		assertSame(4, bst.root.right.right.right.element);
		
		bst.insert(-5);
		bst.insert(-7);
		bst.insert(-3);
		
		assertSame(-5, bst.root.left.element);
		assertSame(-7, bst.root.left.left.element);
		assertSame(-3, bst.root.left.right.element);
	}

	@Test
	public void testRemove() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		
		bst.insert(5);
		bst.insert(1);
		bst.insert(10);
		bst.insert(-4);
		bst.insert(8);
		bst.insert(17);
		bst.insert(19);
		bst.insert(7);
		bst.insert(9);
		
		assertSame(9, bst.root.right.left.right.element);
		// remove node w/ no children
		bst.remove(9);
		assertSame(null, bst.root.right.left.right);
		
		// remove node w/ only right child
		assertSame(17, bst.root.right.right.element);
		bst.remove(17);
		assertSame(19, bst.root.right.right.element);
		
		// remove node w/ only left child
		
		assertSame(1, bst.root.left.element);
		bst.remove(1);
		assertSame(-4, bst.root.left.element);
		
		//remove node w/ two children
		assertSame(5, bst.root.element);
		bst.remove(5);
		assertSame(7, bst.root.element);
		assertSame(-4, bst.root.left.element);
		assertSame(10, bst.root.right.element);
		
	}

	@Test
	public void testContains() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.insert(1);
		bst.insert(7);
		bst.insert(9);
		bst.insert(5);
		bst.insert(3);
		bst.insert(-67);
		
		
		assertTrue(bst.contains(3));
		assertFalse(bst.contains(22));
	}

}
