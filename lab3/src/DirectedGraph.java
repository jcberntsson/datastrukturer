
import java.lang.reflect.Array;
import java.util.*;

public class DirectedGraph<E extends Edge> {

	private List<E>[] edges;

	public DirectedGraph(int noOfNodes) {
		edges = new List[noOfNodes];
		for(int i = 0; i < noOfNodes; i++) {
			edges[i] = (LinkedList<E>)new LinkedList();
		}
	}

	public void addEdge(E e) {
		edges[e.from].add(e);
	}

	public Iterator<E> shortestPath(int from, int to) {
		List<E> shortestPath = null;
		PriorityQueue<CompDijkstraPath<E>> pq = new PriorityQueue<>();
		pq.add(new CompDijkstraPath(from, 0));
		while(!pq.isEmpty()) {
			CompDijkstraPath<E> cdp = pq.poll();
			int node = cdp.getNode();
			List<E> path = cdp.getPath();
			if (!cdp.isVisited()) {
				if (node == to) {
					return shortestPath.iterator();
				} else {
					cdp.setVisited(true);
					for (int i = 0; i < edges[node].size(); i++) {
						E edge = edges[node].get(i);
						int connectedNode = edge.to;
						boolean visited = false;
						for (E e : path) {
							if (e.to == connectedNode) {
								visited = true;
								break;
							}
						}
						if (!visited) {
							pq.add(new CompDijkstraPath<>(cdp, edge));
						}
					}
				}
			}
		}
		return shortestPath != null ? shortestPath.iterator() : null;
	}

	public Iterator<E> minimumSpanningTree() {
		return null;
	}

}
  
