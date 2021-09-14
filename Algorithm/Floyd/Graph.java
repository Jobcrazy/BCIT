/*
 * COMP 3761, Summer 2021, Lab 10
 *
 * Student Name: Hang Liu
 * Student ID: A01173804
 *
 * This program tests floyd algorithms
 */

import java.util.HashMap;

public class Graph {
    private int[][] matrix = null;
    private int[][] floydMatrix = null;
    private int vertices = 0;
    private String[] vertexNames = null;
    private HashMap<String, Integer> mapVertextName = null;
    private boolean directed = false;

    public Graph(int V, String[] names, boolean bDirected) {
        // Initialize the Graph object
        // V: the number of vertices in the graph
        if (0 >= V || null == names || V != names.length) {
            return;
        }

        mapVertextName = new HashMap<>();
        directed = bDirected;
        vertexNames = names;
        vertices = V;

        for (int vertex = 0; vertex < V; ++vertex) {
            mapVertextName.put(names[vertex], vertex);
        }

        // Initialize the 2d matrix and set all the elements to 0
        matrix = new int[V][V];
    }

    @Override
    public String toString() {
        // Format the graph as a matrix string
        return matrixToString(matrix);
    }

    public void addEdge(String u, String v, int weight) {
        // Add an edge for two vertices
        // u: the first vertex
        // v: the second vertex
        if (null == mapVertextName.get(u) || null == mapVertextName.get(v)) {
            // Return if u or v is not a valid vertex
            return;
        }

        // Reset the floyd matrix
        floydMatrix = null;

        // Add the edge
        matrix[mapVertextName.get(u)][mapVertextName.get(v)] = weight;

        if (!directed) {
            // Add another edge if it's a directed graph
            matrix[mapVertextName.get(v)][mapVertextName.get(u)] = weight;
        }
    }

    private String matrixToString(int [][]rawMatrix){
        // Format the graph as a matrix string
        // rawMatrix: a 2D array represents a graph
        String result = "";

        result += "\t";
        for (int vertex = 0; vertex < vertices; ++vertex){
            result += vertexNames[vertex] + "\t";
        }
        result += "\n";

        for (int row = 0; row < vertices; ++row) {
            result += vertexNames[row] + "\t";
            for (int column = 0; column < vertices; ++column) {
                if (rawMatrix[row][column] == Integer.MAX_VALUE){
                    result += "inf" + "\t";
                }else{
                    result += rawMatrix[row][column] + "\t";
                }
            }
            result += "\n";
        }
        return result;
    }

    private int weightAdd(int firstWeight, int secondWeight) {
        // Add two weights and return the result
        // firstWeight: the first weight
        // secondWeight: the second weight
        if (firstWeight == Integer.MAX_VALUE ||
                secondWeight == Integer.MAX_VALUE) {
            // return infinity if any od the weight is infinity
            return Integer.MAX_VALUE;
        }

        // return the sum of the first weight and the second weight
        return firstWeight + secondWeight;
    }

    public void floyd() {
        // Apply the floyd algorithm to the graph
        if (null != floydMatrix) {
            // Do nothing if there is already a result
            return;
        }

        // Copy original matrix
        floydMatrix = new int[vertices][vertices];
        for (int row = 0; row < vertices; ++row) {
            for (int column = 0; column < vertices; ++column) {
                if (0 == matrix[row][column] && row != column) {
                    floydMatrix[row][column] = Integer.MAX_VALUE;
                } else {
                    floydMatrix[row][column] = matrix[row][column];
                }
            }
        }

        // Compute all pairs shortest path
        for (int vertex = 0; vertex < vertices; ++vertex) {
            for (int row = 0; row < vertices; ++row) {
                for (int column = 0; column < vertices; ++column) {
                    int newWeight = weightAdd(floydMatrix[row][vertex],
                            floydMatrix[vertex][column]);
                    if (newWeight < floydMatrix[row][column]) {
                        floydMatrix[row][column] = newWeight;
                    }
                }
            }
        }
    }

    public String floydString(){
        // Format the floyd Matrix result
        return matrixToString(floydMatrix);
    }
}
