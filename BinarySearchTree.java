package avlTree;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree <E extends Comparable<? super E>> {
	protected Node root;
	protected int size;
	// needed for inOrder traversal of tree
	protected List<E> elem = new ArrayList<E>();
	/**
	 * Construct an empty BSTree.
	 */
	public BinarySearchTree() {
		// not necessary, but explicit stating root starts at null
		this.root = null; 
		this.size = 0;
	}
	
	protected class Node {
		// since this is a private inner class, and the outer BinarySearchTree class
		// will need to freely modify the connections and update the height
		// of its nodes, the following three variables are not private.
		Node left;
		Node right;
		int height;
		E element; 

		/**
		 * Construct an BSTNode. At instantiation, each node has no
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
	
	/*
	 * public helper method to insert an element into a BST,
	 * calls the protected method which creates a new node
	 * and inserts the node into the tree.
	 * @param element - value to be inserted into BST
	 */
	public void insert(E element) {
		//public helper method
		this.root = insert(this.root, element);
		this.size++;
		
	}
	
	/*
	 * method recursively compares the element to the element of the root, 
	 * if the element is smaller than that of the root, the function is called 
	 * recursively on the left subtree of the node, otherwise the function is called
	 * recursively on the right subtree of the node. When the function is called on a
	 * root which doesnt exist, a new node with the element is inserted into the BST.
	 * @param root - root of tree you wish to insert into
	 * @param element - element you want to insert
	 * @return root - root of new tree with inserted element
	 */
	private Node insert(Node root, E element) {
		if (root == null) {
			root = new Node(element);
			return root;
		}
		
		if (element.compareTo(root.element) > 0) {
			root.right = insert(root.right, element);
		} else {
			root.left = insert(root.left, element);
			
		}
		return root;
	}
	
	/*
	 * public helper method to remove an element from a BST,
	 * calls the protected method which removes the node 
	 * containing the element from the BST
	 * @param element - value to be removed from BST
	 */
	public void remove(E element) {
		// public helper method
		this.root = remove(this.root, element);
		this.size--;
	}
	
	/*
	 * method recursively compares the element to the element of the root, 
	 * if the element is smaller than that of the root, the function is called 
	 * recursively on the left subtree of the node, otherwise the function is called
	 * recursively on the right subtree of the node. When the function is called on a
	 * root whose element is equal to the element we want to remove, the function 
	 * determines how many children the node to be removed has and acts appropriately
	 * @param root - root of tree you wish to insert into
	 * @param element - element you want to insert
	 * @return root - root of new tree with inserted element
	 */
	protected Node remove(Node root, E element) {
		//element is not found
		if (root == null) {
			return root;
		}
		
		if (element.compareTo(root.element) > 0) {
			root.right = remove(root.right, element);
		} else if (element.compareTo(root.element) < 0) {
			root.left = remove(root.left, element);
		} else {
			//element found in a node w/ one child
			if (root.left == null) {
				return root.right;
			} else if (root.right == null) {
				return root.left;
			}
			//element found in a node w/ two children
			root.element = findMinElement(root.right);
			root.right = remove(root.right, root.element);	
		}
		return root;
	}
	
	/*
	 * This method continously recurses through the left nodes 
	 * of a subtree until it finds a node w/o a left child. This
	 * child node will house the smallest element in the subtree, 
	 * which is returned.
	 * @param root - root of subtree of which you want to find the min
	 * @return minElement - min element of the subtree
	 */
	protected E findMinElement(Node root) {
		E minElement = root.element;
		// min element is always going to be the farthest left element in a subtree
		if (root.left != null) {
			minElement = findMinElement(root.left);
		}
		return minElement;
	}
	
	/*
	 * public helper method which calls the protected method
	 * contains, which searches the BST and returns a boolean
	 * which this function then returns
	 * @param element - the element that we want to find in the BST
	 * @return boolean - if the element is in the BST, return true,
	 * else, return false.
	 */
	public boolean contains(E element) {
		// public helper method
		return contains(this.root, element);
	}
	
	/*
	 * method recursively compares the element to the element of the root, 
	 * if the element is smaller than that of the root, the function is called 
	 * recursively on the left subtree of the node, otherwise the function is called
	 * recursively on the right subtree of the node. When the function is called on a
	 * root whose element is equal to the element we are searching for, the function 
	 * returns true. If we get to a node w/ no children and haven't found the element,
	 * return false.
	 * @param root - root of tree you wish to search
	 * @param element - element for which you want to search
	 * @return boolean - true if element is contained in tree, else false.
	 */
	protected boolean contains(Node root, E element) {
		// if no root exists, the element cannot exist in the tree
		if (root == null) {
			return false;
		}
		if (root.element == element) {
			return true;
		}
		// if element is not found in root, compare element to element of root and recursively call 
		// the method on either the right or left sub-tree of the root
		if (element.compareTo(root.element) > 0) {
			return contains(root.right, element);
		} else {
			return contains(root.left, element);
		}
	}
	
}
