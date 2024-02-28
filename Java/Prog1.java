import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Prog1 {
    public static void main(String[] args) {
        if (args.length == 0) { // No input file passed
            System.out.println("Usage: java Prog1 <input_file>");
            return;
        }

        // Scan the Input File
        String inputFileName = args[0];
        try {
            Scanner fileScanner = new Scanner(new File(inputFileName));
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
                    edge.start = Integer.parseInt(edgeVertices[0].trim());
                    edge.end = Integer.parseInt(edgeVertices[1].trim());
                    edges.add(edge);
                }

                Graph graph = new Graph(vertices); // Create a graph

                // Create the edges
                for (Edge edge : edges) {
                    graph.addEdge(edge.start, edge.end);
                }

                graph.BFS(0); // Actual Search is called

                System.out.println(); // For separating outputs of different graphs
                lineScanner.close(); // Close open lineScanner
            }
            
            fileScanner.close(); // Close open fileScanner
            
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + inputFileName); // Handle invalid user input
        }
    }
}
