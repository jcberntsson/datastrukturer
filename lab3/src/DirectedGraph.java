import java.util.*;

/**
 * Author(s): Joakim Berntsson & Tim Kerschbaumer
 * Group: 6
 * Laboration: 3
 * Date: 2015-03-03 - 16:02
 * Purpose: An implementation of a directed graph.
 */
public class DirectedGraph<E extends Edge> {
    /* An array where each index is a node and contains a list of its edges */
    private List<E>[] edges;

    /**
     * Constructs a directed graph with the specified number of nodes.
     * @param nbrOfNodes the number of nodes in the graph
     */
	public DirectedGraph(int nbrOfNodes) {
        // Instantiate the array and fill it with lists.
		edges = (List<E>[]) new List[nbrOfNodes];
        for(int i = 0; i < edges.length; i++){
            edges[i] = new ArrayList<E>();
        }
	}

    /**
     * Add an edge to this graph.
     * @param e the edge to add
     */
	public void addEdge(E e) {
		edges[e.from].add(e);
	}

    /**
     * Returns an iterator for the shortest path between the specified nodes in this graph.
     * @param from the starting node
     * @param to the ending node
     * @return an iterator for the shortest path between from and to or null if no path exists.
     */
	public Iterator<E> shortestPath(int from, int to) {
		PriorityQueue<CompDijkstraPath<E>> pq = new PriorityQueue<>();
		// Add the startnode / startPath to the queue
		pq.add(new CompDijkstraPath<E>(from));

		while(!pq.isEmpty()) {
			// Get the first element from the queue
			CompDijkstraPath<E> cdp = pq.poll();
			int node = cdp.getNode();
			// Check if this node has been visited
			if (!CompDijkstraPath.visitedNodes().contains(node)) {
				// If the node is the ending node, return a iterator for its path.
				if (node == to) {
					return cdp.getPath().iterator();
				} else {
					// Set the node as visited
					CompDijkstraPath.setVisited(node);
					// Loop over all connecting edges to this node
					for (int i = 0; i < edges[node].size(); i++) {
						E edge = edges[node].get(i);
						// If node has not been visited add it + old path to the queue
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
        // Added initial capacity, because java 1.7 doesn't have the new constructors.
        PriorityQueue<E> queue = new PriorityQueue<>(11, new CompKruskalEdge<>());
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
