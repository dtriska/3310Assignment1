#include <iostream>
#include <vector>
#include <queue>
#include <unordered_set>
#include <limits>

using namespace std;

struct Point{
    int x;
};

struct Edge {
    int x;
    int y;
};

// Graph class
class Graph {
private:
    int V; // Number of vertices
    vector<vector<int>> adjList; // Adjacency list

public:
    // Constructor
    Graph(int vertices) : V(vertices) {
        adjList.resize(V);
    }

    // Function to add an edge to the graph
    void addEdge(int u, int v) {
        adjList[u].push_back(v);
        adjList[v].push_back(u);
    }

    void BFS(int start){
        std::queue<int> unvisited;
        vector<bool> visited(V, false);

        unvisited.push(start);
        visited[start] = true;
        while (!unvisited.empty()){
            int current = unvisited.front();
            cout << current << " ";
            unvisited.pop();

            for (int vertex : adjList[current]){
                if (!visited[vertex]){
                    visited[vertex] = true;
                    unvisited.push(vertex);
                }
            }

            if (unvisited.empty()){
                for (int i = 1; i < V; ++i) {
                    if (!visited[i]) { 
                        std::cout << std::endl;
                        unvisited.push(i);
                        visited[i] = true;
                        break;
                    }
                }
            }
        }
    }
    
};

int main() {
    std::cout << "Enter Number of verticies and tuples of edges \n";
    std::cout << "For Example: 5 (1,2) (3,4) (3,5) (4,5)\n";
    int verticies;
    std::cin >> verticies;

    // Consume the newline character left in the input buffer
    std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');

    std::vector<Edge> edges; // Vector to store edges
    std::string input;
    std::getline(std::cin, input);

    std::string currentEdge;
    for (char c : input) {
        if (isdigit(c) || c == '-') {
            currentEdge += c;
        } else if (c == ',') {
            edges.back().x = std::stoi(currentEdge);
            currentEdge = "";
        } else if (c == ')') {
            edges.back().y = std::stoi(currentEdge);
            currentEdge = "";
        } else if (c == '(') {
            edges.emplace_back();
        }
    }

    // Create a vector to store points
    std::vector<Point> points;

    // Loop to create vertices
    for (int i = 0; i < verticies + 1; i++) {
        Point p;  // Create a new Point instance
        p.x = i;  // Assign the x coordinate (assuming it's based on the loop index)
        points.push_back(p);  // Add the point to the vector
    }

    // Create a graph
    Graph g(verticies); // Graph with vertices

    for (const auto edge : edges){
        g.addEdge(edge.x, edge.y);
    }

    g.BFS(0);
    
    return 0;
    }