import java.util.ArrayList;
import java.util.List;

/**
 * Created by: Tim Kerschbaumer
 * Project: lab3
 * Date: 15-03-03
 * Time: 16:02
 */
// TODO behöver skrivas om lite men funkar
public class CompDijkstraPath<E extends Edge> implements Comparable<CompDijkstraPath<E>> {
	private int node;
	private double cost;
	private List<E> path;

	// TODO detta ska göras på bättre sätt, ville bara få det att funka
	private static List<Integer> visited;

	public static void addVisited(int node) {
		visited.add(node);
	}

	public static List<Integer> visitedNodes() {
		return visited;
	}

	public CompDijkstraPath(int node, double cost) {
		this.node = node;
		this.cost = cost;
		this.path = new ArrayList<>();
		visited = new ArrayList<>();
	}

	public CompDijkstraPath(final CompDijkstraPath<E> old, E path) {
		this.node = path.to;
		this.cost = old.getCost() + path.getWeight();
		this.path = new ArrayList<>(old.path);
		this.path.add(path);
	}

	public List<E> getPath() {
		return path;
	}


	public double getCost() {
		return cost;
	}

	public int getNode() {
		return node;
	}

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
