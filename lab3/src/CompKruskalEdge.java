import java.util.Comparator;

/**
 * Package: PACKAGE_NAME
 * Author: Joakim
 * Date: 2015-03-03 - 16:02
 * Purpose:
 */
public class CompKruskalEdge<E extends Edge> implements Comparator<E> {

    @Override
    public int compare(E e1, E e2) {
        if(e1.getWeight() < e2.getWeight())
            return -1;
        else if(e1.getWeight() > e2.getWeight())
            return 1;
        else
            return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
