import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Point { // Each vertex
    int x;
}

class Edge { // Each Edge Connection
    int x;
    int y;
}

// Graph class
class Graph {
    private int V; // Number of vertices
    private ArrayList<ArrayList<Integer>> adjList; // Adjacency list

    // Constructor
    Graph(int vertices) {
        V = vertices;
        adjList = new ArrayList<>(V);
        for (int i = 0; i < V; ++i)
            adjList.add(new ArrayList<>());
    }

    // Function to add an edge to the graph
    void addEdge(int u, int v) {
        // Quick solution for offset in indexing
        u--;
        v--;
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }

    void BFS(int start) { // Breadth - First - Search
        Queue<Integer> unvisited = new LinkedList<>(); // Standard Queue
        boolean[] visited = new boolean[V]; // visited array where index corrsponds to each vertex

        // Initialize Array and Queue
        unvisited.add(start); 
        visited[start] = true;

        while (!unvisited.isEmpty()) { // Will continue search until nothing left in queue
            int current = unvisited.poll(); // Poll is essentially Java equivalent of poping from top
            System.out.print((current + 1) + " "); // Refix indexing offset for printing output

            for (int vertex : adjList.get(current)) { // Travereses Adjacency List
                if (!visited[vertex]) { // If not already in visited
                    visited[vertex] = true;
                    unvisited.add(vertex);
                }
            }

            if (unvisited.isEmpty()) { // If current Graph has no more connections
                for (int i = 1; i < V; ++i) {
                    if (!visited[i]) {
                        System.out.println();
                        unvisited.add(i);
                        visited[i] = true;
                        break;
                    }
                }
            }
        }
    }
}

public class Prog1 {
    public static void main(String[] args) {
        if (args.length == 0) { // No input file passed
            System.out.println("Usage: java Prog1 <input_file>");
            return;
        }

        // Scan the Input File
        String filename = args[0];
        try {
            Scanner fileScanner = new Scanner(new File(filename));
            int graphCount = 1; 
            while (fileScanner.hasNextLine()) { // For each line in input file
                String line = fileScanner.nextLine();
                System.out.println("Graph " + graphCount++);

                Scanner lineScanner = new Scanner(line);
                int vertices = lineScanner.nextInt();

                ArrayList<Edge> edges = new ArrayList<>();
                while (lineScanner.hasNext()) {
                    String edgeString = lineScanner.next();
                    edgeString = edgeString.replaceAll("[\\(\\)]", "");
                    String[] edgeVertices = edgeString.split(",");
                    Edge edge = new Edge();
                    edge.x = Integer.parseInt(edgeVertices[0].trim());
                    edge.y = Integer.parseInt(edgeVertices[1].trim());
                    edges.add(edge);
                }

                Graph g = new Graph(vertices); // Create a graph

                // Create the edges
                for (Edge edge : edges) {
                    g.addEdge(edge.x, edge.y);
                }

                g.BFS(0); // Actual Search is called

                System.out.println(); // For separating outputs of different graphs
                lineScanner.close(); // Close open lineScanner
            }
            
            fileScanner.close(); // Close open fileScanner
            
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename); // Handle invalid user input
        }
    }
}


