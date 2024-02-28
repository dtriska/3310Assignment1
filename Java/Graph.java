import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// Graph class
class Graph {
    private int numVertices; // Number of vertices
    private ArrayList<ArrayList<Integer>> adjacencyList; // Adjacency list

    // Constructor
    Graph(int vertices) {
        numVertices = vertices;
        adjacencyList = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; ++i)
            adjacencyList.add(new ArrayList<>());
    }

    // Function to add an edge to the graph
    void addEdge(int start, int end) {
        // Quick solution for offset in indexing
        start--;
        end--;
        adjacencyList.get(start).add(end);
        adjacencyList.get(end).add(start);
    }

    // Breadth-First-Search
    void BFS(int start) {
        Queue<Integer> unvisited = new LinkedList<>(); // Standard Queue
        boolean[] visited = new boolean[numVertices]; // visited array where index corresponds to each vertex

        // Initialize Array and Queue
        unvisited.add(start); 
        visited[start] = true;

        int connectedComponents = 1;

        while (!unvisited.isEmpty()) { // Will continue search until nothing left in queue
            int current = unvisited.poll(); // Poll is essentially Java equivalent of popping from top
            System.out.print((current + 1) + " "); // Refix indexing offset for printing output

            for (int vertex : adjacencyList.get(current)) { // Traverses Adjacency List
                if (!visited[vertex]) { // If not already visited
                    visited[vertex] = true;
                    unvisited.add(vertex);
                }
            }

            if (unvisited.isEmpty()) { // If current Graph has no more connections
                for (int i = 1; i < numVertices; ++i) {
                    if (!visited[i]) {
                        System.out.println();
                        unvisited.add(i);
                        visited[i] = true;
                        connectedComponents += 1;
                        break;
                    }
                }
            }
        }
        
        System.out.println("\n" + connectedComponents + " connected components");
    }
}
