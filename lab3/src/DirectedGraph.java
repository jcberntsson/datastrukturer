
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

	public Iterator<E> minimumSpanningTree() {
        // Kruskals
        int nbrOfCC = edges.length;
        DirectedGraph<E> mst = new DirectedGraph<E>(nbrOfCC);
        PriorityQueue<E> queue = new PriorityQueue<E>(new CompKruskalEdge<Edge>());
        for (List<E> edge : edges) {
            queue.addAll(edge);
        }
        while(nbrOfCC > 1 && !queue.isEmpty()){
            // Find cheapest
            E cheapest = queue.poll();
            System.out.println(cheapest);
            // Check if no cycle created --> Add to MST
            if(mst.edges[cheapest.from] != mst.edges[cheapest.to]){
                // Merge the lists
                List<E> shortest = mst.edges[cheapest.from];
                List<E> longest = mst.edges[cheapest.to];
                if(shortest.size() > longest.size()){
                    List<E> tmp = shortest;
                    shortest = longest;
                    longest = tmp;
                }
                for(E e : shortest){
                    longest.add(e);
                }
                // Add the cheapest
                longest.add(cheapest);
                // Link all nodes to the longest list
                for(E e : longest){
                    mst.edges[e.from] = longest;
                    mst.edges[e.to] = longest;
                }
                // Decrease number of components
                nbrOfCC--;
            }
        }
		return mst.edges[0].iterator();
	}
}
