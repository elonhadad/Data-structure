package il.ac.telhai.ds.trees;

public class BinarySearchTree<T extends Comparable<T>> {

	BstNode root;
	private int size;

	// Binary Search Tree Node
	class BstNode {
		T val;
		BstNode left, right;

		public BstNode(T val) {
			this.val = val;
			left = null;
			right = null;
		}
	}

	public enum Direction {
		LEFT, RIGHT
	}

	// Returns the val given a path from the root.
	// Used for testing. DO NOT DELETE.
	public T getValInPath(Direction... direction) {
		BstNode node = root;
		for (Direction d : direction) {
			if (d.equals(Direction.LEFT) && node.left != null)
				node = node.left;
			else if (d.equals(Direction.RIGHT) && node.right != null)
				node = node.right;
			else
				return null;
		}
		return node.val;
	}

	/**
	 * Constructs an empty BinarySearchTree.
	 */
	public BinarySearchTree() {
		root = null;
		size = 0;
	}

	/**
	 * returns the number of elements in the tree
	 *
	 */
	public int size() {
		return size;
	}

	/**
	 * help function to add a node
	 * @param node
	 * @param val
	 * @return
	 */
	private BstNode addRecursive(BstNode node, T val) {
		if(node == null) {
			return new BstNode(val);
		}
		if(val.compareTo(node.val) < 0) {
			node.left = addRecursive(node.left, val);
		}
		else if(val.compareTo(node.val) > 0) {
			node.right = addRecursive(node.right, val);
		}
		return node;
	}

	/**
	 * Adds the object value to the tree as a leaf according to the parameter.
	 * 
	 * @param val
	 * @return True, if the element was added. Otherwise false.
	 */
	public boolean add(T val) {
		if(contains(val)) {
			return false;
		}
		size++;
		root = addRecursive(root, val);
		return true;
	}

	/**
	 * help function to find min value by given root
	 * @param node
	 * @return
	 */
	private T minValue(BstNode node) {
		T min = node.val;
		while(node.left != null) {
			min = node.left.val;
			node = node.left;
		}
		return min;
	}

	/**
	 * help function to check if BST contains a node
	 * @param node
	 * @param val
	 * @return
	 */
	private BstNode removeRecursive(BstNode node, T val) {
		if (node == null)
			return null;
		if(val.compareTo(node.val) < 0) {
			node.left = removeRecursive(node.left, val);
		}
		else if(val.compareTo(node.val) > 0) {
			node.right = removeRecursive(node.right, val);
		}
		else {
			if(node.left == null) {
				return node.right;
			}
			else if(node.right == null) {
				return node.left;
			}
			node.val = minValue(node.right);
			node.right = removeRecursive(node.right, node.val);
		}
		return node;
	}

	/**
	 * Removes the object in the tree which is equal to the parameter. 
	 * Nothing is done if not found
	 * 
	 * @param val: the object to be looked for in the tree
	 * @return True, if the element was removed. Otherwise false.
	 */
	public boolean remove(T val) {
		if (!contains(val))
			return false;
		size--;
		root = removeRecursive(root, val);
		return true;
	}

	/**
	 * help function that check if BST contains value
	 * @param node
	 * @param val
	 * @return
	 */
	private boolean containsRecursive(BstNode node, T val) {
		if(node == null || size == 0) {
			return false;
		}
		if(val.compareTo(node.val) < 0) {
			return containsRecursive(node.left, val);
		}
		else if(val.compareTo(node.val) > 0) {
			return containsRecursive(node.right, val);
		}
		return true;
	}

	/**
	 * Looks for an object which is equal to the parameter.
	 * 
	 * @param val: the object to be looked for in the tree
	 * @return true if the tree contains this object. Otherwise, return false.
	 */
	public boolean contains(T val) {
		return containsRecursive(root, val);
	}

	/**
	 * function that returns the minimum value in BST
	 * @param node
	 * @return
	 */
	private BstNode findMin(BstNode node) {
		while(node.left != null) {
			node = node.left;
		}
		return node;
	}

	private BstNode inOrderSuccessor(BstNode cRoot, BstNode node, T val) {
		if(cRoot == null) {
			return node;
		}
		if(cRoot.val.compareTo(val) == 0) {
			if(cRoot.right != null) {
				return findMin(cRoot.right);
			}
		}
		else if(val.compareTo(cRoot.val) < 0) {
			node = cRoot;
			return inOrderSuccessor(cRoot.left, node, val);
		}
		else {
			return inOrderSuccessor(cRoot.right, node, val);
		}
		return node;
	}

	/**
	 * Looks for the minimal object in the tree, which is greater than or equal to
	 * the parameter.
	 * 
	 * @param val: the object to be looked for in the tree
	 * @return a reference to the found object.
	 */
	public T findGe(T val) {
		if(contains(val)) {
			return val;
		}
		BstNode successor = inOrderSuccessor(root, null, val);
		if(successor != null) {
			return successor.val;
		}
		return null;
	}
}
