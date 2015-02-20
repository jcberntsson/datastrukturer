package datastructures;

import testSortCol.CollectionWithGet;

/**
 * Author(s): Joakim Berntsson & Tim Kerschbaumer
 * TDA416
 * Group: 6
 * Laboration: 2.2
 */

/**
 * A class that represents a SplayTree. This class extends a BinarySearchTree and
 * implements the CollectionWithGet interface. The splaying is only done in the Overrided find method.
 * This method is also called by the implemented get method. Adding and deleting does not splay the tree.
 */
public class SplayTree<E extends Comparable<? super E>> extends BinarySearchTree<E> implements CollectionWithGet<E> {

	/**
	 * Find the first occurence of an element
	 * that is equal to the argument with respect to the natureal order.
	 * (compareTo() == 0)
	 *
	 * @param e The element to compare to
	 * @return An element in the collection satisfying compareTo() == 0
	 * or null if the element does not exist.
	 */
	@Override
	public E get(E e) {
		Entry r = find(e, root);
		return r == null ? null : r.element;
	}


	/**
	 * Finds an Entry matching the parameter elem using binary search.
	 * The method also splays the tree so the found element is the new root.
	 * If the element is not found the trees last elements is splayed to the root.
	 *
	 * @param elem the element to find the corresponding entry.
	 * @param t    where in the three to start the search (root)
	 * @return null if not entry was found otherwise the new root (The entry found).
	 */
	@Override
	protected Entry find(E elem, Entry t) {
		Entry tmp;
		if (t == null) {
			return null;
		} else {
			int jfr = elem.compareTo(t.element);
			if (jfr < 0) {
				if (t.left == null) {
					// Splay t
					splay(t);
					return null;
				} else {
					tmp = find(elem, t.left);
				}
			} else if (jfr > 0) {
				if (t.right == null) {
					// Splay t
					splay(t);
					return null;
				} else {
					tmp = find(elem, t.right);
				}
			} else {
				tmp = t;
				splay(t);
			}
			return tmp == null ? null : root;
		}
	}

	/* This determines which splaying methods to use and calls them
	   (zig, zigzag, etc) until the Entry t is the new root
	 */
	private void splay(Entry t) {
		Entry parent = t.parent;
		if (parent != null) {
			Entry grandParent = parent.parent;
			if (grandParent == null) {
				// Zig or Zag
				if (parent.left == t) {
					// T left child to parent --> Zag
					zag(parent);
				} else {
					// T right child to parent --> Zig
					zig(parent);
				}
				splay(parent);
			} else {
				if (parent.left == t) {
					// T left child to parent --> Zag
					if (grandParent.left == parent) {
						// parent left child to grandparent --> ZagZag
						zagZag(grandParent);
					} else {
						// parent right child to grandparent --> ZagZig
						zagZig(grandParent);
					}
				} else {
					// T right child to parent --> Zig
					if (grandParent.left == parent) {
						// parent left child to grandparent --> ZigZag
						zigZag(grandParent);
					} else {
						// parent right child to grandparent --> ZigZig
						zigZig(grandParent);
					}
				}
				splay(grandParent);
			}
		}
	}

	/* The splay method ZAG
			   x'                 y'
			  / \                / \
			 y'  C   -->        A   x'
			/ \                    / \
		   A   B                  B   C
	 */
	private void zag(Entry x) {
		Entry y = x.left;
		E temp = x.element;
		x.element = y.element;
		y.element = temp;
		x.left = y.left;
		if (x.left != null)
			x.left.parent = x;
		y.left = y.right;
		y.right = x.right;
		if (y.right != null)
			y.right.parent = y;
		x.right = y;
	}


	/* The splay method ZIG
			   x'                 y'
			  / \                / \
			 A   y'  -->        x'  C
				/ \            / \
			   B   C          A   B
	 */
	private void zig(Entry x) {
		Entry y = x.right;
		E temp = x.element;
		x.element = y.element;
		y.element = temp;
		x.right = y.right;
		if (x.right != null)
			x.right.parent = x;
		y.right = y.left;
		y.left = x.left;
		if (y.left != null)
			y.left.parent = y;
		x.left = y;
	}

	/* The splay method ZIG ZAG
			   x'                  z'
			  / \                /   \
			 y'  D   -->        y'    x'
			/ \                / \   / \
		   A   z'             A   B C   D
			  / \
			 B   C
	 */
	private void zigZag(Entry x) {
		Entry y = x.left,
				z = x.left.right;
		E e = x.element;
		x.element = z.element;
		z.element = e;
		y.right = z.left;
		if (y.right != null)
			y.right.parent = y;
		z.left = z.right;
		z.right = x.right;
		if (z.right != null)
			z.right.parent = z;
		x.right = z;
		z.parent = x;
	}

	/* The splay method ZAG ZIG
			   x'                  z'
			  / \                /   \
			 A   y'   -->       x'    y'
				/ \            / \   / \
			   z'   D          A   B C   D
			  / \
			 B   C
	 */
	private void zagZig(Entry x) {
		Entry y = x.right,
				z = x.right.left;
		E e = x.element;
		x.element = z.element;
		z.element = e;
		y.left = z.right;
		if (y.left != null)
			y.left.parent = y;
		z.right = z.left;
		z.left = x.left;
		if (z.left != null)
			z.left.parent = z;
		x.left = z;
		z.parent = x;
	}

	/* The splay method ZAG ZAG
			   x'             z'
			  / \            /  \
			 y' D   -->     A   y'
			/ \	               / \
		   z' C               B  x'
		  / \                   / \
		 A  B                  C  D
	 */
	private void zagZag(Entry x) {
		// X child is y
		Entry y = x.left;
		// Element that should become root
		Entry z = x.left.left;
		// Grandparent/root element
		E e = x.element;
		// Switch place between x and z
		x.element = z.element;
		z.element = e;
		// For readability
		Entry tmp = x;
		x = z;
		z = tmp;
		// Change nodes
		y.left = x.right;
		if (y.left != null) {
			y.left.parent = y;
		}
		x.right = z.right;
		if (x.right != null) {
			x.right.parent = x;
		}
		z.left = x.left;
		if (z.left != null) {
			z.left.parent = z;
		}
		x.left = y.right;
		if (x.left != null) {
			x.left.parent = x;
		}
		y.right = x;
		z.right = y;
	}

	/* The splay method ZIG ZIG
			   x'                  z'
			  / \                /   \
			 A   y'   -->       y'    D
				/ \            / \
			   B   z'         x'  C
				  / \        / \
				 C   D      A   B
	 */
	private void zigZig(Entry x) {
		Entry y = x.right;
		Entry z = y.right;
		// Switch places with z and x
		E e = x.element;
		x.element = z.element;
		z.element = e;
		// For easier readability
		Entry tmp = z;
		z = x;
		x = tmp;
		// Switch places with A,B,C,D
		z.right = x.right;
		if (x.right != null) {
			x.right.parent = z;
		}
		y.right = x.left;
		if (x.left != null) {
			x.left.parent = y;
		}
		x.right = y.left;
		if (y.left != null) {
			y.left.parent = x;
		}
		x.left = z.left;
		if (z.left != null) {
			z.left.parent = x;
		}
		// Move y,x to the left side of the tree
		z.left = y;
		y.left = x;
	}
}
