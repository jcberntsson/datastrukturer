
import java.lang.reflect.Array;
import java.util.*;

public class DirectedGraph<E extends Edge> {
    private List<E>[] edges;

	public DirectedGraph(int nbrOfNodes) {
		edges = (List<E>[]) new List[nbrOfNodes];
        for(int i = 0; i < edges.length; i++){
            edges[i] = new ArrayList<E>();
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

    /**
     * Returns an iterator for the minimum spanning tree of this graph.
     * @return an iterator for the MST
     */
	public Iterator<E> minimumSpanningTree() {
        // Number of components
        int nbrOfCC = edges.length;
        // Using this class to store the MST
        DirectedGraph<E> mst = new DirectedGraph<>(nbrOfCC);
        List<E>[] mstEdges = mst.edges;
        // Create priority queue with a comparator and add all edges to the queue.
        PriorityQueue<E> queue = new PriorityQueue<>(new CompKruskalEdge<>());
        for (List<E> edge : edges) {
            queue.addAll(edge);
        }
        // Loop until all components are connected or the queue is empty
        while(nbrOfCC > 1 && !queue.isEmpty()){
            // Find cheapest
            E cheapest = queue.poll();
            // Check if no cycle created --> Add to MST
            if(mstEdges[cheapest.from] != mstEdges[cheapest.to]){
                // Find the longest of the lists
                List<E> shortest = mstEdges[cheapest.from];
                List<E> longest = mstEdges[cheapest.to];
                if(shortest.size() > longest.size()){
                    List<E> tmp = shortest;
                    shortest = longest;
                    longest = tmp;
                }
                // Add the cheapest to the shortest list
                shortest.add(cheapest);
                // Merge the shortest into the longest list and link the nodes
                for(E e : shortest){
                    longest.add(e);
                    mstEdges[e.from] = longest;
                    mstEdges[e.to] = longest;
                }
                // Decrease number of components
                nbrOfCC--;
            }
        }
        // All nodes should be pointing to the same list, if not the graph wasn't interconnecting from the start.
		return mstEdges[0].iterator();
	}
}
