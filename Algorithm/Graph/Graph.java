/*
 * COMP 3761, Summer 2021, Lab 7
 *
 * Student Name: Hang Liu
 * Student ID: A01173804
 *
 * This program tests graph algorithms
 */

import java.util.ArrayList;

public class Graph {
    private int[][] matrix = null;
    private int vertices = 0;
    private boolean directed = false;

    public Graph(int V, boolean bDirected) {
        // Initialize the Graph object
        // V: the number of vertices in the graph
        if (0 >= V) {
            return;
        }

        directed = bDirected;
        vertices = V;

        // Initialize the 2d matrix and set all the elements to 0
        matrix = new int[V][V];
    }

    public boolean isDirected() {
        // Return true if the graph is directed, otherwise return false
        return directed;
    }

    @Override
    public String toString() {
        // Return the graph as a matrix string
        String result = "";
        for (int row = 0; row < vertices; ++row) {
            for (int column = 0; column < vertices; ++column) {
                result += "" + matrix[row][column] + " ";
            }
            result += "\n";
        }
        return result;
    }

    public void addEdge(int u, int v) {
        // Add an edge for two vertices
        // u: an integer indicates the first vertex
        // v: an integer indicates the second vertex
        if (u >= vertices || v > vertices) {
            // Return if u or v is not a valid vertex
            return;
        }

        // Add the edge
        matrix[u][v] = 1;

        if (!directed) {
            // Add another edge if it's a directed graph
            matrix[v][u] = 1;
        }
    }

    public int degree(int v) {
        // Compute the degree of a vertex
        // v: an integer indicates a vertex
        if (directed || v >= vertices) {
            // Return -1 if it's a directed graph or v in not a valid vertex
            return -1;
        }

        // compute the degree
        int degree = 0;
        for (int column = 0; column < vertices; ++column) {
            if (0 != matrix[v][column]) {
                // each "1" indicates an edge
                degree++;
            }
        }

        return degree;
    }

    public int inDegree(int v) {
        // Compute the in degree of a vertex
        // v: an integer indicates a vertex
        if (!directed || v >= vertices) {
            // Return -1 if it's an undirected graph or v in not a valid vertex
            return -1;
        }

        // compute the in degree
        int degree = 0;
        for (int row = 0; row < vertices; ++row) {
            if (v == row) {
                // skip if v equals to column
                continue;
            }
            // each "1" indicates an edge
            degree += matrix[row][v];
        }
        return degree;
    }

    public int outDegree(int v) {
        // Compute the in degree of a vertex
        // v: an integer indicates a vertex
        if (!directed || v >= vertices) {
            // Return -1 if it's an undirected graph or v in not a valid vertex
            return -1;
        }

        // compute the out degree
        int degree = 0;
        for (int column = 0; column < vertices; ++column) {
            degree += matrix[v][column];
        }

        return degree;
    }

    private void DFSHelper(int vertex, int[] visited) {
        // Use DFS algorithm to traverse a graph with a vertex
        // vertex: an integer indicates a vertex in a graph
        // visited: an array records all visited vertices
        visited[vertex] = 1;
        System.out.println("visiting vertex " + vertex);

        for (int row = 0; row < vertices; ++row) {
            if (1 == matrix[row][vertex] && 0 == visited[row]) {
                DFSHelper(row, visited);
            }
        }
    }

    public void DFS() {
        // Use dfs algorithm to traverse a graph
        if (0 == vertices) {
            // Return if the graph is empty
            return;
        }

        int[] visited = new int[vertices];
        for (int vertex = 0; vertex < vertices; ++vertex) {
            if (0 == visited[vertex]) {
                // Call the helper function to traverse the graph
                DFSHelper(vertex, visited);
            }
        }
    }

    private void BFSHelper(
            int vertex,
            int[] visited,
            ArrayList<Integer> queue) {
        // Use BFS algorithm to traverse a graph with a vertex
        // vertex: an integer indicates a vertex in a graph
        // visited: an array records all visited vertices
        // queue: an Integer ArrayList records the vertices to traverse
        queue.remove(0); // Remove the first node from the queue

        for (int column = 0; column < vertices; ++column) {
            // Add all unvisited neighbours of vertex to the queue
            if (1 == matrix[vertex][column] && 0 == visited[column]) {
                visited[column] = 1;
                queue.add(column);
            }
        }
    }

    public void BFS() {
        // Use BFS algorithm to traverse a graph
        if (0 == vertices) {
            // Return if the graph is empty
            return;
        }

        int[] visited = new int[vertices];
        ArrayList<Integer> queue = new ArrayList<Integer>();

        // Add the first node to be traversed
        queue.add(0);

        // Keep traversing until the queue is empty
        while (0 != queue.size()) {
            int vertex = queue.get(0);
            visited[vertex] = 1;
            System.out.println("visiting vertex " + vertex);

            // Call the helper function to update the queue
            BFSHelper(vertex, visited, queue);
        }
    }
}
