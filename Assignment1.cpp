#include <iostream>
#include <vector>
#include <queue>
#include <unordered_set>

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
        // For undirected graph, add this line:
        // adjList[v].push_back(u);
    }
};

int main() {
    std::cout << "Enter Number of verticies and tuples of edges \n";
    std::cout << "For Example: 5 (1,2) (3,4) (3,5) (4,5)\n";
    int verticies = 5;
    // std::cin >> verticies;

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

    // Printing the edges
    std::cout << "You entered the following edges:" << std::endl;
    for (const auto& edge : edges) {
        std::cout << "(" << edge.x << ", " << edge.y << ")" << std::endl;
    }

    // Create a vector to store points
    std::vector<Point> points;

    // Loop to create vertices
    for (int i = 1; i < verticies + 1; ++i) {
        Point p;  // Create a new Point instance
        p.x = i;  // Assign the x coordinate (assuming it's based on the loop index)
        points.push_back(p);  // Add the point to the vector
    }

    std::cout << "You entered the following points:" << std::endl;
    for (const auto& point : points) {
        std::cout << point.x << std::endl;
    }

    // Create a graph
    Graph g(verticies); // Graph with vertices
    
    return 0;
    }