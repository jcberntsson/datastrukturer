package datastructures;

import testSortCol.CollectionWithGet;

/**
 * Created by: Tim Kerschbaumer & Joakim Berntsson
 * Group: 6
 * Project: lab2.2
 * Date: 15-02-19
 * Time: 12:52
 */
public class SplayTree<E extends Comparable<? super E>> extends BinarySearchTree<E> implements CollectionWithGet<E> {

    @Override
    public E get(E e) {
        return null;
    }

	/* Rotera 1 steg i h�gervarv, dvs
			   x'                 y'
			  / \                / \
			 y'  C   -->        A   x'
			/ \                    / \
		   A   B                  B   C
	 */
	private void rotateRight( Entry x ) {
		Entry   y = x.left;
		E    temp = x.element;
		x.element = y.element;
		y.element = temp;
		x.left    = y.left;
		if ( x.left != null )
			x.left.parent   = x;
		y.left    = y.right;
		y.right   = x.right;
		if ( y.right != null )
			y.right.parent  = y;
		x.right   = y;
	} //   rotateRight

	/* Rotera 1 steg i v�nstervarv, dvs
			   x'                 y'
			  / \                / \
			 A   y'  -->        x'  C
				/ \            / \
			   B   C          A   B
	 */
	private void rotateLeft( Entry x ) {
		Entry  y  = x.right;
		E temp    = x.element;
		x.element = y.element;
		y.element = temp;
		x.right   = y.right;
		if ( x.right != null )
			x.right.parent  = x;
		y.right   = y.left;
		y.left    = x.left;
		if ( y.left != null )
			y.left.parent   = y;
		x.left    = y;
	} //   rotateLeft

	/* Rotera 2 steg i h�gervarv, dvs
			   x'                  z'
			  / \                /   \
			 y'  D   -->        y'    x'
			/ \                / \   / \
		   A   z'             A   B C   D
			  / \
			 B   C
	 */
	private void doubleRotateRight( Entry x ) {
		Entry   y = x.left,
				z = x.left.right;
		E       e = x.element;
		x.element = z.element;
		z.element = e;
		y.right   = z.left;
		if ( y.right != null )
			y.right.parent = y;
		z.left    = z.right;
		z.right   = x.right;
		if ( z.right != null )
			z.right.parent = z;
		x.right   = z;
		z.parent  = x;
	}  //  doubleRotateRight

	/* Rotera 2 steg i v�nstervarv, dvs
			   x'                  z'
			  / \                /   \
			 A   y'   -->       x'    y'
				/ \            / \   / \
			   z   D          A   B C   D
			  / \
			 B   C
	 */
	private void doubleRotateLeft( Entry x ) {
		Entry  y  = x.right,
				z  = x.right.left;
		E      e  = x.element;
		x.element = z.element;
		z.element = e;
		y.left    = z.right;
		if ( y.left != null )
			y.left.parent = y;
		z.right   = z.left;
		z.left    = x.left;
		if ( z.left != null )
			z.left.parent = z;
		x.left    = z;
		z.parent  = x;
	} //  doubleRotateLeft
}
