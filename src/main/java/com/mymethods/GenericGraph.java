package com.mymethods;

import java.util.*;

public class GenericGraph<V> extends AbstractGraph<V> {

    // Graph Attributes
    private final boolean directed;
    private final boolean weighted;

    // Default Constructor
    public GenericGraph(boolean directed, boolean weighted) {
        this.directed = directed;
        this.weighted = weighted;
    }

    // Overloaded Constructor
    //PRE:  accepts the settings for directed & weighted
    //      accepts vertice & edge arrays 
    //POST: sets directed & undirected
    //      adds vertices to adjList
    //      adds edges to adjList 
    public GenericGraph(boolean directed, boolean weighted,
            V[] initialVertices, int[][] initialEdges) {
        this.directed = directed;
        this.weighted = weighted;

        // Add vertices
        for (V v : initialVertices) {
            addVertex(v);
        }
        // Add edges
        for (int[] e : initialEdges) {
            if (e.length < 2) {
                throw new IllegalArgumentException("Each edge must have at least from and to indices");
            }

            int fromIndex = e[0];
            int toIndex = e[1];

            V from = initialVertices[fromIndex];
            V to   = initialVertices[toIndex];

            if (weighted) {
                if (e.length != 3) {
                    throw new IllegalArgumentException("Weighted edges must include a weight");
                }
                addEdge(from, to, e[2]);
            } else {
                addEdge(from, to);
            }
        }

    }

    
    // Overloaded Constructor
    //PRE:  accepts the settings for directed & weighted
    //      accepts vertice & edge arrays 
    //POST: sets directed & undirected
    //      adds vertices to adjList
    //      adds edges to adjList 
    public GenericGraph(boolean directed, boolean weighted,
            V[] initialVertices, double[][] initialEdges) {
        this.directed = directed;
        this.weighted = weighted;

        // Add vertices
        for (V v : initialVertices) {
            addVertex(v);
        }
        // Add edges
        for (double[] e : initialEdges) {
            if (e.length < 2) {
                throw new IllegalArgumentException("Each edge must have at least from and to indices");
            }

            int fromIndex = (int) e[0];
            int toIndex = (int)e[1];

            V from = initialVertices[fromIndex];
            V to   = initialVertices[toIndex];

            if (weighted) {
                if (e.length != 3) {
                    throw new IllegalArgumentException("Weighted edges must include a weight");
                }
                addEdge(from, to, e[2]);
            } else {
                addEdge(from, to);
            }
        }

    }
    
    //PRE:  none
    //POST: returns status of directed
    @Override public boolean isDirected() { return directed; }

    //PRE:  none
    //POST: returns status of weighted    
    @Override public boolean isWeighted() { return weighted; }


    //@Override
    //PRE:  accepts from & to vertices
    //POST: both vertices must exist
    //      if edge does not exist, throw illegal arguement
    //      if edge exists, return weight
    public double getWeight(V from, V to) {
        // Works for both weighted + unweighted (unweighted edges will be 1.0)
        requireVertex(from);
        requireVertex(to);

        Double w = adjList.get(from).get(to);
        if (w == null) throw new IllegalArgumentException("Edge not found: " + from + " -> " + to);
        return w;
    }

    //PRE:  none
    //POST: prints all edges for every vertex

    public void printEdges() {
        System.out.println("Graph edges:");
        List<V> verts = vertices(); 

        for (V from : verts) {
            for (Map.Entry<V, Double> toEntry : adjList.get(from).entrySet()) {
                V to = toEntry.getKey();
                double w = toEntry.getValue();

                // Skip mirrored edges in undirected graphs (print one direction only)
                if (!directed && verts.indexOf(from) > verts.indexOf(to)) continue;

                if (weighted) {
                    System.out.println(from + " -> " + to + " (weight " + w + ")");
                } else {
                    System.out.println(from + " -> " + to);
                }
            }
        }
    }

    //PRE:  none
    //POST: prints adjacency list
    public void printAdjacencyList() {
        List<V> verts = vertices();

        for (V v : verts) {
            System.out.print(v + " -> [");

            boolean first = true;

            // if you want neighbors printed in the same order as verts:
            for (V n : verts) {
                if (containsEdge(v, n)) {
                    if (!first) System.out.print(", ");

                    if (weighted) System.out.print(n + "(" + getWeight(v, n) + ")");
                    else System.out.print(n);

                    first = false;
                }
            }
            System.out.println("]");
        }
    }

    //PRE:  Provide the starting vertex
    //POST: verify starting vertex
    //      return a list containing nodes visited in BFS order
    public List<V> breadthFirstSearch(V start) {
        requireVertex(start);

        List<V> visited = new ArrayList<>();
        Queue<V> queue  = new ArrayDeque<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            V current = queue.poll(); // dequeue

            for (V neighbor : neighborsOf(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor); // enqueue
                }
            }
        }
        return visited;
    }

    //PRE:  accepts a starting and ending vertex
    //POST: will return a List of Vertices representing the shortest path
    public List<V> getShortestPathUnweighted(V start, V target) {
        requireVertex(start);
        requireVertex(target);

        if (weighted) {
            throw new UnsupportedOperationException(
                    "Use Dijkstra for weighted graphs."
            );
        }

        Queue<V> queue = new ArrayDeque<>();
        List<V> visited = new ArrayList<>();
        //Set<V> visited = new HashSet<>();
        Map<V, V> previous = new HashMap<>();

        queue.add(start);
        visited.add(start);
        previous.put(start, null);

        while (!queue.isEmpty()) {
            V current = queue.poll();

            if (current.equals(target)) {
                break;
            }

            for (V neighbor : adjList.get(current).keySet()) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    previous.put(neighbor, current);
                    System.out.println(previous);
                }
            }
        }

        // reconstruct path
        List<V> path = new ArrayList<>();
        if (!visited.contains(target)) {
            return path; // no path exists
        }

        for (Map.Entry<V, V> entry : previous.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        for (V v = target; v != null; v = previous.get(v)) {
            path.add(0, v);
        }

        return path;
    }


    public static class DFSOrder<V> {
        public final List<V> visitOrder = new ArrayList<>();
        public final List<V> finishOrder = new ArrayList<>();
        public final List<V> topologicalOrder = new ArrayList<>();
        public boolean isCyclic;

        public DFSOrder() {
            boolean isCyclic = false;
        }
    }

    public DFSOrder<V> depthFirstSearch(V start) {
        requireVertex(start);

        DFSOrder<V> order = new DFSOrder<>();
        List<V> visiting = new ArrayList<>();
        List<V> visited = new ArrayList<>();

        dfsHelper(start, visiting, visited, order);

        //double check that all vertices have been visited
        //if not, call dfsHelper for the vertices not visited
        for (V v : vertices()) {
            if (!visited.contains(v)) 
                dfsHelper(v, visiting, visited, order);
        } 

         // Topological order = reverse of finishOrder (for DAGs)
        if (!order.isCyclic){
             for (int i = order.finishOrder.size() - 1; i >= 0; i--) 
                order.topologicalOrder.add(order.finishOrder.get(i));
        }
        return order;
    }

    //PRE:  accepts the vertex, the lists of visiting & visited vertexes 
    //      & lists of vertices that need to be updated 
    //POST: this recursive function verifies that the current vertex has
    //      not been visited 

    private void dfsHelper(V current, List<V> visiting, List<V> visited, DFSOrder<V> order) {

        //cycle detected
        if (visiting.contains(current)){
            order.isCyclic = true;
            return;
        }
        if (visited.contains(current))
            return;
        
        visiting.add(current);
        order.visitOrder.add(current);

        for (V neighbor : neighborsOf(current)) {
            if (visiting.contains(neighbor)){
                order.isCyclic = true;
                continue;
            }
            if (!visited.contains(neighbor))
                dfsHelper(neighbor, visiting, visited, order);
        }
        visiting.remove(current);
        visited.add(current);
        order.finishOrder.add(current);
    }

    //Class to hold Dijkstra's algorithm return values

    public static class DijkstraResult<V> {

        //attributes
        public final List<V> vertices;
        public final List<Double> distances;
        public final List<V> previous;

        //overloaded constructor
        public DijkstraResult(List<V> vertices, List<Double> distances, List<V> previous) {
            this.vertices = vertices;
            this.distances = distances;
            this.previous = previous;
        }

        //PRE:  accepts target
        //POST: returns the shortest path from start to target  
        public List<V> getPathTo(V target) {
            List<V> path = new ArrayList<>();

            int index = vertices.indexOf(target);
            if (index == -1 || distances.get(index) == Double.POSITIVE_INFINITY) {
                return path; // no path
            }

            for (V v = target; v != null; v = previous.get(vertices.indexOf(v))) {
                path.add(0, v);
            }
            return path;
        }
    }

    //PRE:  accepts starting vertex
    //POST: performs Dijkstra's algorithm  
    public DijkstraResult<V> dijkstra(V start) {

        //check conditions for Dijkstra's
        if (!weighted) 
            throw new UnsupportedOperationException("Dijkstra requires a weighted graph.");  
        requireVertex(start);

        //initialize working storage
        Set<V> unvisited = new HashSet<>();
        List<Double> distance = new ArrayList<>();
        List<V> previous = new ArrayList<>();
        List<V> vertices = vertices();

        for (V v : vertices) {
            unvisited.add(v);
            distance.add(Double.POSITIVE_INFINITY);
            previous.add(null);
        }

        //set startIndex to distance of 0
        int startIndex = vertices.indexOf(start);       
        distance.set(startIndex, 0.0);
        V current = start;

        //while there are still vertices to visit
        while (!unvisited.isEmpty()) {
            unvisited.remove(current);
            int currentIndex = vertices.indexOf(current);

            //for each neighbor of current that has not been visited
            //get the distance from current to neighbor
            //if that distance is less than the current distance to neighbor
            //update distance & previous values
            for (V neighbor : neighborsOf(current)) {
                if (!unvisited.contains(neighbor)) continue;

                double alt =
                    distance.get(currentIndex) +
                    getWeight(current, neighbor);

                int neighborIndex = vertices.indexOf(neighbor);

                if (alt < distance.get(neighborIndex)) {
                    distance.set(neighborIndex, alt);
                    previous.set(neighborIndex, current);
                }
            }
            
            // find unvisited vertex with smallest distance to check next
            double minDist = Double.POSITIVE_INFINITY;

            for (V v : unvisited) {
                int i = vertices.indexOf(v);
                if (distance.get(i) < minDist) {
                    minDist = distance.get(i);
                    current = v;
                }
            }

            if (current == null) break; // unreachable vertices remain
            
        }

        return new DijkstraResult<>(vertices, distance, previous);
    }



    //for use in Prims
    public static class Edge<V> implements Comparable<Edge> {
        //attributes
        final V from;
        final V to;
        final double weight;

        //overloaded constructor
        Edge(V from, V to, double weight) {
            this.from = from; this.to = to; this.weight = weight;
        }

        //getters
        public V getFrom() { return from; }
        public V getTo()   { return to; }
        public double getWeight() { return weight; }
        
        //definition of how to compare Edges
        @Override
        public int compareTo(Edge other) {
            Double w1 = (Double)this.weight;
            Double w2 = (Double)other.weight;
            int value = w1.compareTo(w2);

            return value;
        }
    }

    //Class holding the results of Prim's algorithm
    public static class PrimResult<V> {

        //attributes
        private final List<Edge<V>> mstEdges;
        private final double totalWeight;

        //overloaded constructor
        public PrimResult(List<Edge<V>> mstEdges, double totalWeight) {
            this.mstEdges = mstEdges;
            this.totalWeight = totalWeight;
        }
        //getters
        public double getTotalWeight(){return totalWeight;}
        public List<Edge<V>> getMstEdges(){return mstEdges;}

    }

    //PRE:   accepts the starting vertex
    //POST:  determines of the graph is weighted & undirected
    //       ensures the starting vertex is in the graph
    //       initializes the lists to set a reference to the vertices and lists for MST & visited set
    //       & priority queue of edges where the edges are ordered by weight
    public PrimResult<V> prim(V start) {

        //check conditions for Prims
        if (!weighted) {
            throw new UnsupportedOperationException("Prim requires a weighted graph.");
        }
        if (directed) {
            throw new UnsupportedOperationException("Prim requires an undirected graph.");
        }
        requireVertex(start);

        //initialize working storage
        List<V> vertices = vertices();
        List<Edge<V>> mst = new ArrayList<>();
        Set<V> visited = new HashSet<>();
        PriorityQueue<Edge<V>> pq = new PriorityQueue<>(Comparator.comparingDouble(Edge::getWeight));

        double totalDistance = 0.0;

        //add starting vertex to visited 
        visited.add(start);

        // add to the PQ the edges out of start
        for (V n : neighborsOf(start)) {
            pq.add(new Edge<>(start, n, getWeight(start, n)));
        }

        //loop until pq is not empty add there are vertices left to visit 
        while (!pq.isEmpty() && visited.size() < vertices.size()) {
            //get shortest edge
            Edge<V> best = pq.poll();

            //set from & to vertices
            V from = best.getFrom();
            V to = best.getTo();

            // skip vertex if it has been visited
            if (visited.contains(to)) continue;

            // add vertex to visited & edge best to minimum spanning tree
            visited.add(to);
            mst.add(best);
            totalDistance += best.getWeight();

            // add adjacent vertices if not visited to pq
            for (V n : neighborsOf(to)) {
                if (!visited.contains(n)) {
                    pq.add(new Edge<>(to, n, getWeight(to, n)));
                }
            }
        }

        return new PrimResult<>(mst, totalDistance);
    }

    public List<V> kahnsAlgorithm() {
        List<V> result = new LinkedList<>();
        Queue<V> queue = new ArrayDeque<>();
        Map<V, Integer> degreeMap = new HashMap<>();

        for (V vertex : degreeMap.keySet()) {
            if (degreeMap.get(vertex) == 0) queue.add(vertex);
        }

        while (!queue.isEmpty()) {
            V current = queue.poll();
            result.add(current);
        }
        return result;
    }
}