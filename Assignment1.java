import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Point {
    int x;
}

class Edge {
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
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }

    void BFS(int start) {
        Queue<Integer> unvisited = new LinkedList<>();
        boolean[] visited = new boolean[V];

        unvisited.add(start);
        visited[start] = true;
        while (!unvisited.isEmpty()) {
            int current = unvisited.poll();
            System.out.print(current + " ");

            for (int vertex : adjList.get(current)) {
                if (!visited[vertex]) {
                    visited[vertex] = true;
                    unvisited.add(vertex);
                }
            }

            if (unvisited.isEmpty()) {
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

public class Assignment1 {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Assignment1 <input_file>");
            return;
        }

        String filename = args[0];
        try {
            Scanner fileScanner = new Scanner(new File(filename));
            int graphCount = 1;
            while (fileScanner.hasNextLine()) {
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

                // Create a graph
                Graph g = new Graph(vertices);

                for (Edge edge : edges) {
                    g.addEdge(edge.x, edge.y);
                }

                g.BFS(0);

                System.out.println(); // For separating outputs of different graphs
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
        }
    }
}

