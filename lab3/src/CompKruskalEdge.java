import java.util.Comparator;

/**
 * Author(s): Joakim Berntsson & Tim Kerschbaumer
 * Group: 6
 * Laboration: 3
 * Date: 2015-03-03 - 16:02
 * Purpose: A Comparator for edges used in a Kruskal algorithm.
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
}
