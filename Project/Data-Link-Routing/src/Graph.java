import java.util.*;

class Graph {
    private final int vertices; // Number of nodes
    private final List<List<Edge>> adjacencyList; // Adjacency list representation of the graph

    // Constructor to initialize the graph
    public Graph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    // Add an edge to the graph (bidirectional)
    public void addEdge(int src, int dest, int weight) {
        adjacencyList.get(src).add(new Edge(dest, weight));
        adjacencyList.get(dest).add(new Edge(src, weight));
    }

    // Dijkstra's Algorithm to find the shortest paths from source node
    public void dijkstra(int source) {
        // Create a priority queue to store the minimum distance nodes
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.distance));
        int[] distances = new int[vertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.node;

            // Traverse through all adjacent nodes
            for (Edge edge : adjacencyList.get(u)) {
                int v = edge.dest;
                int weight = edge.weight;

                // Relax the edge
                if (distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                    pq.add(new Node(v, distances[v]));
                }
            }
        }

        // Print the shortest distance to each node
        printSolution(distances);
    }

    // Utility method to print the solution
    private void printSolution(int[] distances) {
        System.out.println("Shortest distances from the source node:");
        for (int i = 0; i < vertices; i++) {
            if (distances[i] == Integer.MAX_VALUE) {
                System.out.println("Node " + i + " is unreachable.");
            } else {
                System.out.println("Node " + i + " distance: " + distances[i]);
            }
        }
    }

    // Helper class to represent an edge (destination node, weight)
    private static class Edge {
        int dest;
        int weight;

        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    // Helper class to represent a node with its distance
    private static class Node {
        int node;
        int distance;

        public Node(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        // A = 0
        // B = 1
        // C = 2
        // D = 3
        // E = 4
        // F = 5

        // Example graph with 6 nodes (0 to 5)
        Graph graph = new Graph(6);

        // Add edges (bidirectional)
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 2, 2);

        graph.addEdge(1, 0, 2);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 6);
        graph.addEdge(1, 4, 2);

        graph.addEdge(2, 0, 2);
        graph.addEdge(2, 1, 3);
        graph.addEdge(2, 3, 5);
        graph.addEdge(2, 5, 3);

        graph.addEdge(3, 5, 1);
        graph.addEdge(3, 2, 5);
        graph.addEdge(3, 1, 6);
        graph.addEdge(3, 4, 4);

        graph.addEdge(4, 1, 6);
        graph.addEdge(4, 3, 4);

        graph.addEdge(5, 3, 1);
        graph.addEdge(5, 2, 3);

        graph.dijkstra(0);
    }
}
