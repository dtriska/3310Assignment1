# Assignment1 Java Program

This Java program reads graph data from an input file and performs a Breadth-First Search (BFS) algorithm on each graph. The program is capable of handling edge inputs across multiple lines.

## How to Compile and Run

### Compilation

1. Open a terminal or command prompt.
2. Navigate to the directory containing the Java source files (`Prog1.java`).
3. Compile the Java files using the `javac` command:


### Execution

1. After successful compilation, run the program using the `java` command followed by the name of the main class (`Prog1`):


Replace `input_file.txt` with the path to your input file containing graph data. Though an example 'input_file.txt' is included.

2. The program will process the graph data from the input file and perform BFS on each graph, printing the traversal results to the console.

## Input File Format

The input file should contain graph data in the following format:


Each edge should be represented as a pair of vertices enclosed in parentheses and separated by a comma, e.g., `(1,2)`.

Example input file:

5 (1,2) (3,4) (3,5) (4,5)
4 (1,2) (2,3) (1,4)

NOTE: All verticies and edges are on the same line for each graph. 


## Output

The program will print the BFS traversal result for each graph, with each graph separated by an empty line.
