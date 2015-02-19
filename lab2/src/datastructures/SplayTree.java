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

    protected Entry find(E elem, Entry t) {
	    Entry element = super.find(elem, t);
	    splay(element);
	    return element;
    }

    private void splay(Entry t){
        Entry parent = t.parent;
        if(parent != null){
            Entry grandParent = parent.parent;
            if(grandParent == null){
                // Zig or Zag
                if(parent.left == t){
                    // T left child to parent --> Zag
                }else{
                    // T right child to parent --> Zig
                }
            }else{
                if(parent.left == t){
                    // T left child to parent --> Zag
                    if(grandParent.left == parent){
                        // parent left child to grandparent --> ZagZag
                    }else{
                        // parent right child to grandparent --> ZagZig
                    }
                }else{
                    // T right child to parent --> Zig
                    if(grandParent.left == parent){
                        // parent left child to grandparent --> ZigZag
                    }else{
                        // parent right child to grandparent --> ZigZig
                    }
                }
            }

        }
    }

    /* Zigzig
               x'                  z'
              / \                /   \
             A   y'   -->       y'    D
                / \            / \
               B   z'         x'  C
                  / \        / \
                 C   D      A   B
     */
    private void zigZig(Entry x){
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
        if(x.right != null)
            x.right.parent = z;
        y.right = x.left;
        if(x.left != null)
            x.left.parent = y;
        x.right = y.left;
        if(y.left != null)
            y.left.parent = x;
        x.left = z.left;
        if(z.left != null)
            z.left.parent = x;
        // Move y,x to the left side of the tree
        z.left = y;
        y.left = x;
    }

    /**
     * Testing method for zigZig(Entry x). Should be removed in final version.
     */
    public void testZigZig(){
        zigZig(root);
    }

	public void testZagZag() {
		zagZag(root);
	}

    /* Rotera 1 steg i h�gervarv, dvs zag
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
    } //   rotateRight

    /* Rotera 1 steg i v�nstervarv, dvs zig
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
    } //   rotateLeft

    /* Rotera 2 steg i h�gervarv, dvs ZAG ZIG
               x'                  z'
              / \                /   \
             y'  D   -->        y'    x'
            / \                / \   / \
           A   z'             A   B C   D
              / \
             B   C
     */
    private void zagZig(Entry x) {
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
    }  //  doubleRotateRight

    /* Rotera 2 steg i v�nstervarv, dvs ZIG ZAG
               x'                  z'
              / \                /   \
             A   y'   -->       x'    y'
                / \            / \   / \
               z'   D          A   B C   D
              / \
             B   C
     */
    private void zigZag(Entry x) {
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
    } //  doubleRotateLeft

	private void zagZag(Entry g) {
		// X parent
		Entry p = g.left;
		// Element that should become root
		Entry x = g.left.left;
		// Grandparent/root element
		E e = g.element;
		// Switch place between grandparent and X
		g.element = x.element;
		x.element = e;

		Entry tmp = g;
		g = x;
		x = tmp;

		p.left = g.right;
		if(p.left != null) {
			p.left.parent = p;
		}
		g.right = x.right;
		if(g.right != null) {
			g.right.parent = g;
		}

		x.right = x.left;
		if(x.right != null) {
			x.right.parent = x;
		}

		x.left = g.left;
		if(x.left != null) {
			x.left.parent = x;
		}
		g.left = p.right;
		if(g.left != null) {
			g.left.parent = g;
		}
		p.right = g;
		g.parent = p;
	}
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        // Include description of the structure
        sb.append("Structure: Root followed by its right subtree and then its left.\nLevels are divided with double spacing.\n");
        traverseTree(sb, root, 0);
        return sb.toString();
    }

    private void traverseTree(StringBuilder sb, Entry localRoot, int depth){
        // Add spacing indicating the level the entry is on.
        for(int i = 0; i < depth; i++)
            sb.append("  ");
        // Add the entry's element
        sb.append(localRoot.element).append("\n");

        // Continue traversing the tree
        if(localRoot.right != null){
            traverseTree(sb, localRoot.right, depth + 1);
        }
        if(localRoot.left != null){
            traverseTree(sb, localRoot.left, depth + 1);
        }
    }
}
