
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

	// TODO inte direkt optimerad men funkar
	public Iterator<E> shortestPath(int from, int to) {
		PriorityQueue<CompDijkstraPath<E>> pq = new PriorityQueue<>();
		pq.add(new CompDijkstraPath<E>(from, 0));

		while(!pq.isEmpty()) {
			CompDijkstraPath<E> cdp = pq.poll();
			int node = cdp.getNode();
			if (!CompDijkstraPath.visitedNodes().contains(node)) {
				if (node == to) {
					return cdp.getPath().iterator();
				} else {
					CompDijkstraPath.addVisited(node);
					for (int i = 0; i < edges[node].size(); i++) {
						E edge = edges[node].get(i);
						if (!CompDijkstraPath.visitedNodes().contains(edge.to)) {
							pq.add(new CompDijkstraPath<>(cdp, edge));
						}
					}
				}
			}
		}
		return null;
	}

	public Iterator<E> minimumSpanningTree() {
		return null;
	}

}
  
