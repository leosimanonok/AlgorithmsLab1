package avlTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Written by Leo Simanonok and Eli Mendels
 * Class AVLTree is a basic implementaion of Adelson-Velskii and
 * Landis' Balanced Binary Search Tree.
 */



public class AVLTree<E extends Comparable<? super E>> {
	protected Node root;
	protected int size;
	// needed for inOrder traversal of tree
	protected List<E> elem = new ArrayList<E>();
	/**
	 * Construct an empty AVLTree.
	 */
	public AVLTree() {
		// not necessary, but explicit stating root starts at null
		this.root = null; 
		this.size = 0;
		
	}
	
	public List<E> inOrderElements(Node root) {
		
		if (root.left != null) {
			inOrderElements(root.left);		
		}
		
		elem.add(root.element);
		
		if (root.right != null) {
			inOrderElements(root.right);
		}
		return elem;
	}

	/**
	 * Insert the element into this AVLTree.
	 * @param element the element to insert into the tree. Duplicates are
	 * allowed
	 */
	public void insert(E element) {
		this.root = insert(this.root, element);
		this.size++;
	}

	/**
	 * Remove the element from this AVLTree.
	 * @param element the element to remove
	 */
	public void remove(E element) {
		this.root = remove(this.root, element);
		this.size--;
	}

	/**
	 * Check if this tree contains the element.
	 * @return true if this tree contains the element, false otherwise
	 */
	public boolean contains(E element) {
		return contains(this.root, element);
	}

	/**
	 * Return the minimum elemnent in this tree.
	 * @return the mininum element in this tree
	 */
	public E findMin( ) {
		return findMin(this.root);
	}

	/*
	 * A private helper method for insertion.
	 * By taking a Node as a parameter, we can write this method
	 * recursively, continuing to call insert on subtrees until the element
	 * can be inserted.
	 * @param node the root of some subtree of this AVLTree
	 * @param element the element to insert into this subtree
	 */
	protected Node insert(Node node, E element) {
		if(node == null) {
			return new Node(element);
		}
		// if element is less than the value contained by node...
		if(element.compareTo(node.element) < 0) {
			// insert element into the left subtree
			node.left = insert(node.left, element);
		} else {
			// insert element into the right subtree
			node.right = insert(node.right, element);
		}
		if(Math.abs(height(node.left) - height(node.right)) > 1) {
			node = balance(node);
		}
		// update this node's height using the private helper method
		// height().
		node.height = this.height(node);
		return node;
	}

	/*
	 * A private helper method for removal. 
	 * By taking a Node as a parameter, we can write this method
	 * recursively, continuing to call remove on subtrees until the element
	 * is removed.
	 * @param node the root of some subtree of this AVLTree
	 * @param element the element to remove from this subtree
	 */
	protected Node remove(Node node, E element) {
		if (node == null) {
			return node;
		}
		
		//This finds the node to remove by comparing elements
		 if(element.compareTo(node.element) < 0) {
		      // insert element into the left subtree
		      node.left = remove(node.left, element);
		 } else if (element.compareTo(node.element) > 0) {
		      // insert element into the right subtree
		      node.right = remove(node.right, element);
		 } else {
		 //No children
			 if(node.left == null && node.right == null) {
				 node = null;
			 }
			 //One child
			 else if (node.left == null) {
				 node = node.right;
			 } else if (node.right == null) {
					 node = node.left;
				 
			 }
			//Two children, returns the min of the right subtree
			 else {
				 Node temp = findMinNode(node.right);
				 node.element = temp.element;
				 node.right = remove(node.right, node.element);
			 }
			 
		 }
		 if (node == null) {
			 return node;
		 }
		 if(Math.abs(height(node.left) - height(node.right)) > 1) {
				node = balance(node);
		 }
		 
		 node.height = this.height(node);
			return node;
	}

	/*
	 * As for insert and remove, a private helper is used for a recursive
	 * implementation.
	 * @param element the element to search for
	 * @param node the root of the subtree to search in
	 * @return true if this subtree contains the element, false otherwise
	 */
	private boolean contains(Node node, E element) {
		if(node == null) {
			return false;
		}
		if(element == node.element) {
			return true;
		}
		if(element.compareTo(node.element) < 0) {
			return contains(node.left, element);
		} else {
			return contains(node.right, element);
		}
	}

	/*
	 * Return the minimum element in the subtree rooted at node
	 * @param node the root of the subtree
	 * @return the minimum element in this subtree
	 */
	protected E findMin(Node node) {
		//Returns null if input is null
		if(node==null) {
			return null;
		}
		//Recursivly calls and returns the minimum of the left subtree if it exists, 
		//the minimum will be at the most left position in a balanced tree
		if(node.left != null) {
			return findMin(node.left);
		}
		//Else return the node given
		else {
			return node.element;
		}
	}
	
	/*
	 * Return the node which houses the minimum value in the subtree rooted at node
	 * @param node the root of the subtree
	 * @return the node which houses the minimum value in this subtree
	 */
	private Node findMinNode(Node node) {
		/*created this function to return the node which houses the minimum element in the tree
		 * Needed this to complete the remove function, where we need to find the node with 
		 *the min value. Its a copy of findMin, but returns node instead of value.
		 */
		if(node==null) {
			return null;
		}
		//Return the minimum of the left subtree if it exists
		if(node.left != null) {
			return findMinNode(node.left);
		}
		//Else return the node given
		else {
			return node;
		}
	}

	/*
	 * Balance the subtree rooted at this node. 
	 * @param node the root of the subtree to balance
	 * @return the new root of the balanced subtree
	 */
	private Node balance(Node node) {
		// Determine which type of rotation should be used and call on it.
		// NOTE: Don't forget to update the heights of the nodes after you
		// manipulate the subtree. Implementor's perogative on best way to
		// maintain correct heights.
		// STUBBED

		//How we figure out which subtree needs to be balanced
		int balanceFactor = height(node.right) - height(node.left);

		if (balanceFactor > 0) {
			// tells us if subtree is right or left heavy
			if(height(node.right.right) - height(node.right.left) > 0) {
				//Tells us if we are inside or outside heavy
				node = singleRotateWithRightChild(node);
			} else {
				node = doubleRotateWithRightChild(node);
			}
		
		} else if (balanceFactor < 0) {
			if(height(node.left.right) - height(node.left.left) > 0) {
				node = doubleRotateWithLeftChild(node);
			} else {
				node = singleRotateWithLeftChild(node); 
			}
		
		}
		//returns root of subtree after balance
		return node;
	}
	
	/*
	 * Perform a single rotation for left outside case.
	 * @param node the root of the subtree to rotate
	 * @return the new root of this subtree
	 */
	private Node singleRotateWithLeftChild(Node node) {
		Node root = null; // node that will become root
		Node temp = null; // temp to store the opposite inside node, which will be lost otherwise
		root = node.left; 
		temp = root.right;
		root.right = node;
		node.left = temp;
		
		// resets the height of the two nodes that change height
		node.height = Math.max(height(node.left), height(node.right))+1;
		root.height = Math.max(height(node.left), height(node.right))+1; 
		return root;
	}

	/*
	 * Perform a single rotation for right outside case.
	 * @param node the root of the subtree to rotate
	 * @return the new root of this subtree
	 */
	private Node singleRotateWithRightChild(Node node) {
		Node root = null; // node that will become root
		Node temp = null; // temp to store the opposite inside note, which will be lost otherwise
		root = node.right; 
		temp = root.left;
		root.left = node;
		node.right = temp;
		
		// resets the height of the two nodes that change height
		node.height = Math.max(height(node.left), height(node.right))+1;
		root.height = Math.max(height(node.left), height(node.right))+1; 
		return root;
		
	}

	/*
	 * Perform a double rotation for left inside case.
	 * @param node the root of the subtree to rotate
	 * @return the new root of this subtree
	 * Double Right
	 */
	private Node doubleRotateWithLeftChild(Node node) {
		// first a single rotation is performed on left child
		node.left = singleRotateWithRightChild(node.left);
		// then the original root node is rotated the other way
		node = singleRotateWithLeftChild(node);
		return node;
	}

	/*
	 * Perform a double rotation for right inside case.
	 * @param node the root of the subtree to rotate
	 * @return the new root of this subtree
	 * Double Left
	 */
	private Node doubleRotateWithRightChild(Node node) {
		// first a single rotation is performed on the right child
		node.right = singleRotateWithLeftChild(node.right);
		// then the original root node is rotated the other way
		node = singleRotateWithRightChild(node);
		return node;
	}

	/*
	 * Private helper method to calculate the height of a node. A node's
	 * height is the larger of its left and right subtree's heights plus
	 * one. To make this calculation consistent and easy, we define
	 * height of an empty subtree is -1.
	 * @param node the node to calculate the height of
	 * @return its height as determined by the heights of its subtrees
	 */
	private int height(Node node) {
		if(node==null) {
			return -1;
		}
		// if the left child is null, its height is -1, otherwise, retrieve
		// its height
		int leftHeight = (node.left == null ? -1 : node.left.height);
		// same
		int rightHeight = (node.right == null ? -1 : node.right.height);		

		return Math.max(leftHeight, rightHeight) + 1;
	}
	
	protected class Node {
		// since this is a private inner class, and the outer AVLTree class
		// will need to freely modify the connections and update the height
		// of its nodes, the following three variables are not private.
		Node left;
		Node right;
		int height;
		E element; 

		/**
		 * Construct an AVLTreeNode. At instantiation, each node has no
		 * children and therefore a height of 0.
		 * @param element the element that this node contains
		 */
		public Node(E element) {
			this.left = null;
			this.right = null;
			this.height = 0;
			this.element = element;
		}
	}
    

	
	public static void main(String[] args) {
		
		AVLTree<Integer> avl = new AVLTree<Integer>();
		
		
		avl.insert(7);
		avl.insert(5);
		avl.insert(2);
		avl.insert(9);
		avl.insert(8);
		avl.insert(3);
		//avl.insert(1);

		System.out.println(avl.root.element);
		System.out.println(avl.root.left.element);
		System.out.println(avl.root.right.element);
		System.out.println(avl.root.left.right.element);
		System.out.println(avl.root.right.right.element);
		System.out.println(avl.root.right.left.element);
		
		List<Integer> test = avl.inOrderElements(avl.root);
		System.out.println(test);
		//System.out.println(avl.root.left.left.left.element);
		
		//avl.remove(7);
		/*
		System.out.println(avl.root.element);
		System.out.println(avl.root.left.element);
		System.out.println(avl.root.right.element);
		System.out.println(avl.root.left.right.element);
		System.out.println(avl.root.right.right.element);
		*/
	}
	
}

