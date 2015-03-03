import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by: Tim Kerschbaumer
 * Project: lab3
 * Date: 15-03-03
 * Time: 16:02
 */
public class CompDijkstraPath<E extends Edge> implements Comparable<CompDijkstraPath<E>> {
	private int node;
	private double cost;
	private List<E> path;
	private boolean visited = false;

	public CompDijkstraPath(int node, double cost) {
		this.node = node;
		this.cost = cost;
		this.path = new ArrayList<>();
	}

	public CompDijkstraPath(CompDijkstraPath<E> old, E path) {
		this.node = path.to;
		this.cost = old.getCost() + cost;
		this.path = old.getPath();
		this.path.add(path);
	}

	public List<E> getPath() {
		return path;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public boolean isVisited() {
		return visited;
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
