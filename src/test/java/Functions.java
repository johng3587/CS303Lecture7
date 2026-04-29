import java.util.*;

import com.mymethods.GenericGraph;

public class Functions {

    public static boolean isDigits(String inString) {
        if (inString == null || inString.isEmpty())
            return false;
        for (char c : inString.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }

    public static void example1() {
        System.out.println("EXAMPLE 1: Creating a Graph: Undirected & Unweighted");
        GenericGraph<Integer> graph1 = new GenericGraph<>(false, false);

        graph1.addEdge(0, 1);
        graph1.addEdge(0, 4);
        graph1.addEdge(1, 2);
        graph1.addEdge(1, 3);
        graph1.addEdge(1, 4);
        graph1.addEdge(2, 3);
        graph1.addEdge(3, 4);

        System.out.println("\nGraph Representation (adjacency list): ");
        graph1.printAdjacencyList();

        /*
         * System.out.print("Graph Breadth First Search Order:        ");
         * List<Integer> bfsList = graph1.breadthFirstSearch(0);
         * System.out.println(bfsList);
         * 
         * GenericGraph.DFSOrder<Integer> dfsList = graph1.depthFirstSearch(0);
         * 
         * System.out.print("\nGraph Depth First - Visit Order:         ");
         * System.out.println(dfsList.visitOrder);
         * 
         * System.out.print("Graph Depth First - Finish Order:        ");
         * System.out.println(dfsList.finishOrder);
         * if (graph1.isDirected()){
         * System.out.print("Graph Depth First -Topological Order:   ");
         * System.out.println(dfsList.topologicalOrder);
         * }
         * System.out.println("Does this graph contain a cycle?         " +
         * dfsList.isCyclic);
         * 
         */
    }

    public static void example2() {

        System.out.println("\nExample 2: Creating a Graph: Directed & Unweighted ");

        // List of Edge objects for graph in Figure 28.3a
        String[] names = { "Peter", "Jane", "Mark", "Cindy", "Wendy" };
        int[][] edges = { { 0, 2 }, { 1, 2 }, { 2, 4 }, { 3, 4 } };

        GenericGraph<String> graph1 = new GenericGraph<>(true, false, names, edges);

        System.out.println("\nGraph Representation (adjacency list):  ");
        graph1.printAdjacencyList();
    }

    public static void example3() {

        System.out.println("\nExample 3: Creating a Graph: Directed & Weighted ");

        // List of Edge objects Example from Lecture
        Character[] names = { 'A', 'B', 'C', 'D', 'E' };
        double[][] edges = { { 0, 1, 3.0 }, { 0, 2, 1.0 }, { 0, 3, 7.0 }, { 2, 1, 2.0 }, { 1, 4, 5.0 }, { 2, 4, 4.0 },
                { 3, 4, 1.0 } };

        GenericGraph<Character> graph3 = new GenericGraph<>(true, true, names, edges);

        System.out.println("\nGraph Representation (adjacency list):  ");
        graph3.printAdjacencyList();

    }

    public static void example4() {

        System.out.println("\nExample 4: Creating a Graph: Undirected & Weighted ");

        // List of Edge objects Example from Lecture
        Character[] names = { 'A', 'B', 'C', 'D', 'E' };
        int[][] edges = { { 0, 1, 3 }, { 0, 2, 1 }, { 0, 3, 7 }, { 2, 1, 2 }, { 1, 4, 5 }, { 2, 4, 4 }, { 3, 4, 1 } };

        GenericGraph<Character> graph4 = new GenericGraph<>(false, true, names, edges);

        System.out.println("\nGraph Representation (adjacency list):  ");
        graph4.printAdjacencyList();

    }

    public static void example5() {
        System.out.println("\n\nEXAMPLE 5:  BFS Example (Lecture)");
        GenericGraph<Integer> graph1 = new GenericGraph<>(true, false);

        graph1.addEdge(0, 2);
        graph1.addEdge(1, 0);
        graph1.addEdge(1, 2);
        graph1.addEdge(1, 4);
        graph1.addEdge(1, 5);
        graph1.addEdge(2, 5);
        graph1.addEdge(3, 0);
        graph1.addEdge(3, 1);
        graph1.addEdge(4, 6);
        graph1.addEdge(5, 6);

        System.out.println("\nGraph Representation (adjacency list):  ");
        graph1.printAdjacencyList();

        System.out.print("Graph Breadth First Search Order:       ");
        List<Integer> bfsList = graph1.breadthFirstSearch(3);
        System.out.println(bfsList);

        List<Integer> shortest = graph1.getShortestPathUnweighted(3, 6);
        System.out.println(shortest);

    }

    public static void example6() {

        System.out.println("\nExample 6: Shortest Path: Directed & Unweighted ");

        // List of Edge objects Example from Lecture
        Character[] names = { 'A', 'B', 'C', 'D', 'E', 'F' };
        int[][] edges = { { 0, 1 }, { 1, 0 }, { 1, 4 }, { 2, 5 }, { 3, 0 }, { 3, 5 }, { 4, 3 }, { 4, 2 }, { 5, 4 } };

        GenericGraph<Character> graph1 = new GenericGraph<>(true, false, names, edges);

        System.out.println("\nGraph Representation (adjacency list):  ");
        graph1.printAdjacencyList();

        System.out.print("Graph Breadth First Search Order:       ");
        List<Character> bfsList = graph1.breadthFirstSearch('A');
        System.out.println(bfsList);

        List<Character> shortest = graph1.getShortestPathUnweighted('A', 'F');
        System.out.println(shortest);

    }

    public static void example7() {
        System.out.println("\n\nEXAMPLE 7: Test Shortest Path (Undirected & Unweighted)\n");

        // List of Edge objects Example from Lecture
        Character[] names = { 'S', 'A', 'B', 'C', 'D', 'E' };
        int[][] edges = { { 0, 1 }, { 0, 2 }, { 0, 5 }, { 1, 2 }, { 2, 3 }, { 2, 4 }, { 4, 5 } };

        GenericGraph<Character> graph1 = new GenericGraph<>(false, false, names, edges);

        System.out.println("\nGraph Representation (adjacency list):  ");
        graph1.printAdjacencyList();

        System.out.print("Graph Breadth First Search Order:       ");
        List<Character> bfsList = graph1.breadthFirstSearch('S');
        System.out.println(bfsList);

        List<Character> shortest = graph1.getShortestPathUnweighted('S', 'D');
        System.out.println(shortest);
    }

    public static void example8() {
        System.out.println("\n\nEXAMPLE 8: Test Shortest Path (Undirected & Unweighted)\n");

        // List of Edge objects Example from Lecture
        Character[] names = { 'S', 'A', 'B', 'C', 'D', 'E', 'F' };
        int[][] edges = { { 0, 2 }, { 0, 5 }, { 1, 2 }, { 1, 5 }, { 2, 3 }, { 2, 6 },
                { 3, 4 }, { 4, 5 }, { 5, 6 } };

        GenericGraph<Character> graph1 = new GenericGraph<>(false, false, names, edges);

        System.out.println("\nGraph Representation (adjacency list):  ");
        graph1.printAdjacencyList();

        System.out.print("Graph Breadth First Search Order:       ");
        List<Character> bfsList = graph1.breadthFirstSearch('S');
        System.out.println(bfsList);

        List<Character> shortest = graph1.getShortestPathUnweighted('S', 'D');
        System.out.println(shortest);
    }

    public static void example9() {
        System.out.println("\n\nEXAMPLE 9: Shortest Path (Undirected)\n");
        GenericGraph<Integer> graph1 = new GenericGraph<>(false, false);

        graph1.addEdge(0, 1);
        graph1.addEdge(1, 2);
        graph1.addEdge(1, 4);
        graph1.addEdge(2, 7);
        graph1.addEdge(2, 8);
        graph1.addEdge(4, 10);
        graph1.addEdge(4, 5);
        graph1.addEdge(5, 3);
        graph1.addEdge(5, 6);
        graph1.addEdge(5, 10);
        graph1.addEdge(6, 11);
        graph1.addEdge(7, 6);
        graph1.addEdge(7, 9);
        graph1.addEdge(10, 11);
        graph1.addEdge(8, 12);
        graph1.addEdge(11, 8);

        System.out.println("\nGraph Representation (adjacency list): ");
        graph1.printAdjacencyList();

        System.out.print("Graph Breadth First Search Order:       ");
        List<Integer> bfsList = graph1.breadthFirstSearch(0);
        System.out.println(bfsList);

        System.out.print("Shortest Path Order:                    ");
        List<Integer> r = graph1.getShortestPathUnweighted(0, 12);
        System.out.println(r);
    }

    public static void example10() {
        System.out.println("\n\nEXAMPLE 10: BFS Example (Lecture)");
        Integer[] nodes = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        int[][] edges4 = {
                { 0, 1 }, { 0, 3 },
                { 1, 2 }, { 1, 4 }, { 1, 6 }, { 1, 7 },
                { 2, 3 }, { 2, 8 }, { 2, 9 },
                { 4, 5 }, { 4, 6 }, { 4, 7 }, { 6, 7 } };

        GenericGraph<Integer> graph1 = new GenericGraph<>(false, false, nodes, edges4);

        System.out.println("\nGraph Representation (adjacency list):  ");
        graph1.printAdjacencyList();

        System.out.print("Graph Breadth First Search Order:       ");
        List<Integer> bfsList = graph1.breadthFirstSearch(0);
        System.out.println(bfsList);

        List<Integer> shortest = graph1.getShortestPathUnweighted(8, 7);
        System.out.println(shortest);

    }

    public static void example11() {
        System.out.println("\n\nEXAMPLE 11: DFS Example (Lecture) Undirected & Unweighted");
        Integer[] nodes = { 0, 1, 2, 3, 4, 5, 6 };
        int[][] edges4 = {
                { 0, 1 }, { 0, 2 }, { 0, 3 }, { 0, 4 },
                { 1, 3 }, { 1, 4 },
                { 2, 5 }, { 2, 6 },
                { 3, 4 },
                { 5, 6 } };

        GenericGraph<Integer> graph = new GenericGraph<>(false, false, nodes, edges4);

        System.out.println("\nGraph Representation (adjacency list):  ");
        graph.printAdjacencyList();
        GenericGraph.DFSOrder<Integer> dfsList = graph.depthFirstSearch(0);

        System.out.print("\nGraph Depth First - Visit Order:        ");
        System.out.println(dfsList.visitOrder);

        System.out.print("Graph Depth First - Finish Order:       ");
        System.out.println(dfsList.finishOrder);
        if (graph.isDirected()) {
            System.out.print("Graph Depth First - Topological Order:  ");
            System.out.println(dfsList.topologicalOrder);
        }
    }

    public static void example12() {
        System.out.println("\n\nEXAMPLE 12:  DFS Example (same as BFS Example 5)");
        GenericGraph<Integer> graph = new GenericGraph<>(true, false);

        graph.addEdge(0, 2);
        graph.addEdge(1, 0);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(1, 5);
        graph.addEdge(2, 5);
        graph.addEdge(3, 0);
        graph.addEdge(3, 1);
        graph.addEdge(4, 6);
        graph.addEdge(5, 6);

        System.out.println("\nGraph Representation (adjacency list):  ");
        graph.printAdjacencyList();
        GenericGraph.DFSOrder<Integer> dfsList = graph.depthFirstSearch(3);

        System.out.print("\nGraph Depth First - Visit Order:        ");
        System.out.println(dfsList.visitOrder);

        System.out.print("Graph Depth First - Finish Order:       ");
        System.out.println(dfsList.finishOrder);
        if (graph.isDirected()) {
            System.out.print("Graph Depth First - Topological Order:  ");
            System.out.println(dfsList.topologicalOrder);
        }
    }

    public static void example13() {

        System.out.println("\nExample 13: DFS EXAMPLE: Directed & Unweighted ");

        // List of Edge objects Example from Lecture
        Character[] names = { 'A', 'B', 'C', 'D', 'E' };
        int[][] edges = { { 0, 1 }, { 0, 3 }, { 1, 2 }, { 2, 3 }, { 2, 4 }, { 3, 4 } };

        GenericGraph<Character> graph = new GenericGraph<>(true, false, names, edges);

        System.out.println("\nGraph Representation (adjacency list):  ");
        graph.printAdjacencyList();
        GenericGraph.DFSOrder<Character> dfsList = graph.depthFirstSearch('A');

        System.out.print("\nGraph Depth First - Visit Order:        ");
        System.out.println(dfsList.visitOrder);

        System.out.print("Graph Depth First - Finish Order:       ");
        System.out.println(dfsList.finishOrder);
        if (graph.isDirected()) {
            System.out.print("Graph Depth First - Topological Order:  ");
            System.out.println(dfsList.topologicalOrder);
        }
    }

    public static void example14() {
        System.out.println("\n\nEXAMPLE 14: DFS Example (Lecture) Directed & Unweighted");
        Integer[] nodes = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
        int[][] edges = {
                { 0, 1 }, { 0, 3 }, { 0, 4 }, { 1, 2 }, { 1, 4 }, { 1, 5 },
                { 2, 5 }, { 3, 6 }, { 4, 6 }, { 4, 7 },
                { 5, 7 }, { 6, 8 }, { 7, 8 } };

        GenericGraph<Integer> graph = new GenericGraph<>(true, false, nodes, edges);

        System.out.println("\nGraph Representation (adjacency list):  ");
        graph.printAdjacencyList();
        GenericGraph.DFSOrder<Integer> dfsList = graph.depthFirstSearch(0);

        System.out.print("\nGraph Depth First - Visit Order:        ");
        System.out.println(dfsList.visitOrder);

        System.out.print("Graph Depth First - Finish Order:       ");
        System.out.println(dfsList.finishOrder);
        if (graph.isDirected()) {
            System.out.print("Graph Depth First - Topological Order:  ");
            System.out.println(dfsList.topologicalOrder);
        }
    }

    public static void example15() {
        System.out.println("\nExample 15: BFS & DFS Graph: Directed, Unweighted (same as ex16) ");

        String[] vertices = { "Seattle", "San Francisco", "Los Angeles",
                "Denver", "Kansas City", "Chicago", "Boston", "New York",
                "Atlanta", "Miami", "Dallas", "Houston" };

        int[][] edges = {
                { 0, 1 }, { 0, 3 }, { 0, 5 },
                { 1, 0 }, { 1, 2 }, { 1, 3 },
                { 2, 1 }, { 2, 3 }, { 2, 4 }, { 2, 10 },
                { 3, 0 }, { 3, 1 }, { 3, 2 }, { 3, 4 }, { 3, 5 },
                { 4, 2 }, { 4, 3 }, { 4, 5 }, { 4, 7 }, { 4, 8 }, { 4, 10 },
                { 5, 0 }, { 5, 3 }, { 5, 4 }, { 5, 6 }, { 5, 7 },
                { 6, 5 }, { 6, 7 },
                { 7, 4 }, { 7, 5 }, { 7, 6 }, { 7, 8 },
                { 8, 4 }, { 8, 7 }, { 8, 9 }, { 8, 10 }, { 8, 11 },
                { 9, 8 }, { 9, 11 },
                { 10, 2 }, { 10, 4 }, { 10, 8 }, { 10, 11 },
                { 11, 8 }, { 11, 9 }, { 11, 10 }
        };

        GenericGraph<String> graph1 = new GenericGraph<>(true, false, vertices, edges);

        System.out.println("\nGraph Representation (adjacency list):  ");
        graph1.printAdjacencyList();

        GenericGraph.DFSOrder<String> dfsList = graph1.depthFirstSearch("Chicago");

        System.out.print("\nGraph Depth First - Visit Order:        ");
        System.out.println(dfsList.visitOrder);

        System.out.print("Graph Depth First - Finish Order:       ");
        System.out.println(dfsList.finishOrder);
        if (graph1.isDirected()) {
            System.out.print("Graph Depth First - Topological Order:  ");
            System.out.println(dfsList.topologicalOrder);
        }
        System.out.println("Does this graph contain a cycle?        " + dfsList.isCyclic);

        System.out.print("Graph Breadth First Search Order:       ");
        List<String> bfsList = graph1.breadthFirstSearch("Chicago");
        System.out.println(bfsList);
    }

    public static void example16() {

        System.out.println("\nExample 16: BFS, DFS Graph: Unweighted, Undirected (same as ex15) ");

        String[] vertices = { "Seattle", "San Francisco", "Los Angeles",
                "Denver", "Kansas City", "Chicago", "Boston", "New York",
                "Atlanta", "Miami", "Dallas", "Houston" };

        int[][] edges = {
                { 0, 1 }, { 0, 3 }, { 0, 5 },
                { 1, 2 }, { 1, 3 },
                { 2, 3 }, { 2, 4 }, { 2, 10 },
                { 3, 4 }, { 3, 5 },
                { 4, 5 }, { 4, 7 }, { 4, 8 }, { 4, 10 },
                { 5, 6 }, { 5, 7 },
                { 6, 7 },
                { 7, 8 },
                { 8, 9 }, { 8, 10 }, { 8, 11 },
                { 9, 11 },
                { 10, 11 }
        };

        GenericGraph<String> graph1 = new GenericGraph<>(false, false, vertices, edges);
        System.out.println("\n   The edges for graph1:");
        graph1.printAdjacencyList();

        System.out.println("\nExample 16: Depth First Search ");
        GenericGraph.DFSOrder<String> dfsList = graph1.depthFirstSearch("Chicago");

        System.out.print("\nGraph Depth First - Visit Order:        ");
        System.out.println(dfsList.visitOrder);

        System.out.print("Graph Depth First - Finish Order:       ");
        System.out.println(dfsList.finishOrder);
        if (graph1.isDirected()) {
            System.out.print("Graph Depth First - Topological Order:  ");
            System.out.println(dfsList.topologicalOrder);
        }
        System.out.println("Does this graph contain a cycle?        " + dfsList.isCyclic);

    }

    public static void exampleD1() {
        System.out.println("\n\nEXAMPLE D1: Dijkstra's Algorithm Shortest Path (Directed & Weighted)");

        Integer[] vertices = { 0, 1, 2, 3, 4 };
        int[][] edges = new int[][] {
                { 0, 1, 10 }, { 0, 3, 30 }, { 0, 4, 100 },
                { 1, 2, 50 }, { 2, 4, 10 }, { 3, 2, 20 }, { 3, 4, 60 }
        };

        GenericGraph<Integer> graph = new GenericGraph<>(true, true, vertices, edges);

        GenericGraph.DijkstraResult<Integer> r = graph.dijkstra(0);

        for (int i = 0; i < r.vertices.size(); i++) {
            System.out.println(
                    "Distance from 0 to " +
                            r.vertices.get(i) + " = " +
                            r.distances.get(i));
        }
    }

    public static void exampleD2() {
        System.out.println("\n\nEXAMPLE D2: Dijkstra's Algorithm Shortest Path (Undirected & Weighted)");

        Integer[] vertices = { 0, 1, 2, 3 };
        // List of Edge objects for graph
        int[][] edges = new int[][] {
                { 0, 1, 5 }, { 0, 2, 8 }, { 1, 2, 9 }, { 1, 3, 2 }, { 2, 3, 6 }
        };

        GenericGraph<Integer> graph = new GenericGraph<>(false, true, vertices, edges);
        GenericGraph.DijkstraResult<Integer> r = graph.dijkstra(0);

        for (int i = 0; i < r.vertices.size(); i++) {
            System.out.println(
                    "Distance from 0 to " +
                            r.vertices.get(i) + " = " +
                            r.distances.get(i));
        }
    }

    public static void exampleD3() {
        System.out.println("\n\nEXAMPLE D3: Dijkstra's Algorithm Shortest Path (Undirected & Weighted)");

        Integer[] vertices = { 0, 1, 2, 3 };
        // List of Edge objects for graph
        int[][] edges = new int[][] {
                { 0, 1, 5 }, { 0, 2, 8 }, { 1, 2, 10 }, { 1, 3, 15 }, { 2, 3, 20 }
        };

        GenericGraph<Integer> graph = new GenericGraph<>(false, true, vertices, edges);
        GenericGraph.DijkstraResult<Integer> r = graph.dijkstra(0);

        for (int i = 0; i < r.vertices.size(); i++) {
            System.out.println(
                    "Distance from 0 to " +
                            r.vertices.get(i) + " = " +
                            r.distances.get(i));
        }
    }

    public static void exampleD4() {

        System.out.println("\n\nEXAMPLE D4: Shortest Shortest Path (Undirected & Weighted)\n");
        String[] vertices = { "Seattle", "San Francisco", "Los Angeles",
                "Denver", "Kansas City", "Chicago", "Boston", "New York",
                "Atlanta", "Miami", "Dallas", "Houston" };

        int[][] edges = {
                { 0, 1, 807 }, { 0, 3, 1331 }, { 0, 5, 2097 },
                { 1, 2, 381 }, { 1, 3, 1267 },
                { 2, 3, 1015 }, { 2, 4, 1663 }, { 2, 10, 1435 },
                { 3, 1, 1267 }, { 3, 4, 599 }, { 3, 5, 1003 },
                { 4, 5, 533 }, { 4, 7, 1260 }, { 4, 8, 864 }, { 4, 10, 496 },
                { 5, 6, 983 }, { 5, 7, 787 },
                { 6, 7, 214 },
                { 7, 8, 888 },
                { 8, 9, 661 }, { 8, 10, 781 }, { 8, 11, 810 },
                { 9, 11, 1187 },
                { 10, 11, 239 }
        };

        String startVertex = "Kansas City";
        GenericGraph<String> graph1 = new GenericGraph<>(false, true, vertices, edges);
        GenericGraph.DijkstraResult<String> r = graph1.dijkstra(startVertex);

        for (int i = 0; i < r.vertices.size(); i++) {
            String dest = r.vertices.get(i);
            System.out.println(
                    "Distance from " + startVertex + " to " +
                            r.vertices.get(i) +
                            r.getPathTo(dest) +
                            " = " + r.distances.get(i));
        }

    }

    public static void exampleD5() {
        System.out.println("\n\nEXAMPLE D5: Weighted Graph: Dijkstra's Shortest Path (Undirected & Weighted)\n");

        Integer[] vertices = { 0, 1, 2, 3, 4 };

        int[][] edges = new int[][] {
                { 0, 1, 2 }, { 0, 3, 8 },
                { 1, 2, 7 }, { 1, 3, 3 },
                { 2, 3, 4 }, { 2, 4, 5 },
                { 3, 4, 6 }
        };
        GenericGraph<Integer> graph2 = new GenericGraph<>(false, true, vertices, edges);
        GenericGraph.DijkstraResult<Integer> r = graph2.dijkstra(3);

        for (int i = 0; i < r.vertices.size(); i++) {
            System.out.println(
                    "Distance from 3 to " +
                            r.vertices.get(i) + " = " +
                            r.distances.get(i));
        }
    }

    public static void exampleP1() {
        System.out.println("\n\nEXAMPLE P1: Prims: Minimum Spanning Tree (Undirected & Weighted)\n");
        Integer[] vertices2 = { 0, 1, 2, 3, 4, 5 };

        int[][] edges = new int[][] {
                { 0, 1, 6 }, { 0, 2, 1 }, { 0, 3, 5 },
                { 1, 2, 5 }, { 1, 4, 3 },
                { 2, 3, 5 }, { 2, 4, 6 }, { 2, 5, 4 },
                { 3, 5, 2 }, { 4, 5, 5 }
        };

        GenericGraph<Integer> graph2 = new GenericGraph<>(false, true, vertices2, edges);
        GenericGraph.PrimResult<Integer> pr2 = graph2.prim(0);
        System.out.println("Total weight is " + pr2.getTotalWeight());
        for (GenericGraph.Edge e : pr2.getMstEdges())
            System.out.println(e.getFrom() + "->" + e.getTo() + " w=" + e.getWeight());
    }

    public static void exampleP2() {
        System.out.println("\n\nEXAMPLE P2: Prims: Minimum Spanning Tree (Undirected & Weighted)\n");

        Integer[] vertices = { 0, 1, 2, 3, 4 };
        int[][] edges = new int[][] {
                { 0, 1, 10 }, { 0, 3, 30 }, { 0, 4, 100 },
                { 1, 2, 50 }, { 2, 4, 10 }, { 3, 2, 20 }, { 3, 4, 60 }
        };

        GenericGraph<Integer> graph = new GenericGraph<>(false, true, vertices, edges);
        GenericGraph.PrimResult<Integer> pr3 = graph.prim(0);
        System.out.println("Total weight is " + pr3.getTotalWeight());
        for (GenericGraph.Edge e : pr3.getMstEdges())
            System.out.println(e.getFrom() + "->" + e.getTo() + " w=" + e.getWeight());

    }

    public static void exampleP3() {
        System.out.println("\n\nEXAMPLE P3: Prims: Minimum Spanning Tree (Undirected & Weighted)\n");

        Integer[] vertices3 = { 0, 1, 2, 3, 4, 5, 6 };
        // List of Edge objects for graph
        int[][] edges = new int[][] {
                { 0, 1, 5 }, { 0, 5, 5 },
                { 1, 2, 10 }, { 1, 5, 2 }, { 1, 6, 7 },
                { 2, 3, 8 }, { 2, 4, 10 }, { 2, 6, 7 },
                { 3, 4, 8 }, { 4, 5, 2 }, { 4, 6, 7 }, { 5, 6, 7 },
        };

        GenericGraph<Integer> graph3 = new GenericGraph<>(false, true, vertices3, edges);
        GenericGraph.PrimResult<Integer> pr3 = graph3.prim(0);
        System.out.println("Total weight is " + pr3.getTotalWeight());
        for (GenericGraph.Edge e : pr3.getMstEdges())
            System.out.println(e.getFrom() + "->" + e.getTo() + " w=" + e.getWeight());

    }

    public static void exampleP4() {
        System.out.println("\n\nEXAMPLE P4: Prims: Minimum Spanning Tree (Directed & Weighted)\n");

        Integer[] vertices3 = { 0, 1, 2, 3 };
        // List of Edge objects for graph
        int[][] edges = new int[][] {
                { 0, 1, 5 }, { 0, 2, 8 },
                { 1, 2, 10 }, { 1, 3, 15 }, { 2, 3, 20 }
        };

        GenericGraph<Integer> graph3 = new GenericGraph<>(false, true, vertices3, edges);
        GenericGraph.PrimResult<Integer> pr3 = graph3.prim(0);
        System.out.println("Total weight is " + pr3.getTotalWeight());
        for (GenericGraph.Edge e : pr3.getMstEdges())
            System.out.println(e.getFrom() + "->" + e.getTo() + " w=" + e.getWeight());

    }

    public static void exampleP5() {
        System.out.println("\n\nEXAMPLE P5: Prims: Minimum Spanning Tree (Undirected & Weighted)\n");
        Integer[] vertices2 = { 0, 1, 2, 3, 4 };

        int[][] edges = new int[][] {
                { 0, 1, 2 }, { 0, 3, 8 },
                { 1, 0, 2 }, { 1, 2, 7 }, { 1, 3, 3 },
                { 2, 1, 7 }, { 2, 3, 4 }, { 2, 4, 5 },
                { 3, 0, 8 }, { 3, 1, 3 }, { 3, 2, 4 }, { 3, 4, 6 },
                { 4, 2, 5 }, { 4, 3, 6 }
        };

        GenericGraph<Integer> graph2 = new GenericGraph<>(false, true, vertices2, edges);
        GenericGraph.PrimResult<Integer> pr2 = graph2.prim(1);
        System.out.println("Total weight is " + pr2.getTotalWeight());
        for (GenericGraph.Edge e : pr2.getMstEdges())
            System.out.println(e.getFrom() + "->" + e.getTo() + " w=" + e.getWeight());
    }

    public static void exampleP6() {

        System.out.println("\n\nEXAMPLE P6: Prims: Minimum Spanning Tree (Undirected & Weighted)\n");
        Integer[] vertices4 = { 0, 1, 2, 3, 4 };

        int[][] edges = new int[][] {
                { 0, 1, 2 }, { 0, 3, 6 },
                { 1, 2, 3 }, { 1, 3, 8 }, { 1, 4, 5 },
                { 2, 4, 7 }, { 3, 4, 9 }
        };

        GenericGraph<Integer> graph4 = new GenericGraph<>(false, true, vertices4, edges);
        GenericGraph.PrimResult<Integer> pr4 = graph4.prim(1);
        System.out.println("Total weight is " + pr4.getTotalWeight());
        for (GenericGraph.Edge e : pr4.getMstEdges())
            System.out.println(e.getFrom() + "->" + e.getTo() + " w=" + e.getWeight());
    }

    public static void exampleP7() {

        System.out.println("\n\nEXAMPLE P7: Prims: Minimum Spanning Tree (Undirected & Weighted)\n");
        String[] vertices = { "Seattle", "San Francisco", "Los Angeles",
                "Denver", "Kansas City", "Chicago", "Boston", "New York",
                "Atlanta", "Miami", "Dallas", "Houston" };

        int[][] edges = {
                { 0, 1, 807 }, { 0, 3, 1331 }, { 0, 5, 2097 },
                { 1, 0, 807 }, { 1, 2, 381 }, { 1, 3, 1267 },
                { 2, 1, 381 }, { 2, 3, 1015 }, { 2, 4, 1663 }, { 2, 10, 1435 },
                { 3, 0, 1331 }, { 3, 1, 1267 }, { 3, 2, 1015 }, { 3, 4, 599 },
                { 3, 5, 1003 },
                { 4, 2, 1663 }, { 4, 3, 599 }, { 4, 5, 533 }, { 4, 7, 1260 },
                { 4, 8, 864 }, { 4, 10, 496 },
                { 5, 0, 2097 }, { 5, 3, 1003 }, { 5, 4, 533 },
                { 5, 6, 983 }, { 5, 7, 787 },
                { 6, 5, 983 }, { 6, 7, 214 },
                { 7, 4, 1260 }, { 7, 5, 787 }, { 7, 6, 214 }, { 7, 8, 888 },
                { 8, 4, 864 }, { 8, 7, 888 }, { 8, 9, 661 },
                { 8, 10, 781 }, { 8, 11, 810 },
                { 9, 8, 661 }, { 9, 11, 1187 },
                { 10, 2, 1435 }, { 10, 4, 496 }, { 10, 8, 781 }, { 10, 11, 239 },
                { 11, 8, 810 }, { 11, 9, 1187 }, { 11, 10, 239 }
        };

        GenericGraph<String> graph1 = new GenericGraph<>(false, true, vertices, edges);

        GenericGraph.PrimResult<String> pr = graph1.prim("Seattle");
        System.out.println("Total weight is " + pr.getTotalWeight());
        for (GenericGraph.Edge e : pr.getMstEdges())
            System.out.println(e.getFrom() + "->" + e.getTo() + " w=" + e.getWeight());

    }

}