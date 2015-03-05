import java.util.ArrayList;
import java.util.List;

/**
 * Author(s): Joakim Berntsson & Tim Kerschbaumer
 * Group: 6
 * Laboration: 3
 * Date: 2015-03-03 - 17:01
 * Purpose: A Class that holds data for Dijkstras algorithm.
 */
public class CompDijkstraPath<E extends Edge> implements Comparable<CompDijkstraPath<E>> {
	// Current node
	private int node;
	// Cost of this path
	private double cost;
	// List of all edges visited
	private List<E> path;

	// A static list to keep track of all visited nodes
	private static List<Integer> visited;

	/** Set a node to be visited
	 * @param node the node that has been visited
	 */
	public static void setVisited(int node) {
		visited.add(node);
	}

	/** Return a list of visited nodes.
	 * @return a List of integers
	 */
	public static List<Integer> visitedNodes() {
		return visited;
	}

	/** Constructor for the first case in Dijkstras algorithm.
	 * @param startNode the node to start with
	 */
	public CompDijkstraPath(int startNode) {
		this.node = startNode;
		this.cost = 0;
		this.path = new ArrayList<>();
		visited = new ArrayList<>();
	}

	/** Constructor for all other cases in Dijkstras algoritm.
	 * @param old the old Dijkstra path to be added to the new one
	 * @param path the latest path
	 */
	public CompDijkstraPath(final CompDijkstraPath<E> old, final E path) {
		this.node = path.to;
		this.cost = old.cost + path.getWeight();
		this.path = new ArrayList<>(old.path);
		this.path.add(path);
	}

	/** Returns the path.
	 * @return a list that contains all edges that have been traversed
	 */
	public List<E> getPath() {
		return path;
	}

	/** Returns the node currently visiting.
	 * @return node, an int
	 */
	public int getNode() {
		return node;
	}

	/** Compares the cost of two CompDijkstraPaths.
	 */
	@Override
	public int compareTo(CompDijkstraPath<E> o) {
		if(this.cost > o.cost) {
			return 1;
		} else if(this.cost < o.cost) {
			return -1;
		} else {
			return 0;
		}
	}
}
