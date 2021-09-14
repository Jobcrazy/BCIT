/*
 * COMP 3761, Summer 2021, Lab 7
 *
 * Student Name: Hang Liu
 * Student ID: A01173804
 *
 * This program tests graph algorithms
 */

public class Algorithm {
    public static void testUndirectedGraph() {
        // Build some undirected graphs and print them as matrix

        // Build graph one
        Graph graphOne = new Graph(5, false);
        graphOne.addEdge(0, 1);
        graphOne.addEdge(0, 3);
        graphOne.addEdge(0, 4);
        graphOne.addEdge(1, 2);
        graphOne.addEdge(1, 4);
        graphOne.addEdge(2, 3);
        graphOne.addEdge(3, 4);

        // Print graph one as matrix
        System.out.println("Graph one is as below:");
        System.out.print(graphOne);

        // Print the degree of vertex 0 in graph one
        System.out.printf("Graph One - Vertex 0 degree: %d\n\n",
                graphOne.degree(0));

        // Build graph two
        Graph graphTwo = new Graph(4, false);
        graphTwo.addEdge(0, 1);
        graphTwo.addEdge(1, 2);
        graphTwo.addEdge(2, 3);

        // Print graph two as matrix
        System.out.println("Graph two is as below:");
        System.out.print(graphTwo);

        // Print the degree of vertex 3 in graph two
        System.out.printf("Graph Two - Vertex 3 degree: %d\n\n",
                graphTwo.degree(3));

        // Build graph three
        Graph graphThree = new Graph(6, false);
        graphThree.addEdge(0, 2);
        graphThree.addEdge(0, 4);
        graphThree.addEdge(1, 3);
        graphThree.addEdge(1, 5);
        graphThree.addEdge(2, 4);
        graphThree.addEdge(3, 5);

        // Print graph three as matrix
        System.out.println("Graph three is as below:");
        System.out.print(graphThree);

        // Print the degree of vertex 2 in graph three
        System.out.printf("Graph three - Vertex 2 degree: %d\n\n"
                , graphThree.degree(2));
    }

    public static void testDirectedGraph() {
        // Build a directed graph and print it
        Graph directedGraphOne = new Graph(5, true);
        directedGraphOne.addEdge(0, 0);
        directedGraphOne.addEdge(0, 4);
        directedGraphOne.addEdge(1, 2);
        directedGraphOne.addEdge(1, 4);
        directedGraphOne.addEdge(2, 0);
        directedGraphOne.addEdge(2, 3);
        directedGraphOne.addEdge(3, 1);
        directedGraphOne.addEdge(3, 2);
        directedGraphOne.addEdge(4, 3);

        // Print graph three as matrix
        System.out.println("Directed Graph one is as below:");
        System.out.print(directedGraphOne);

        // Print the degrees of vertex 1 in the directed graph
        System.out.printf("Directed Graph One - Vertex 1 in degree: %d\n",
                directedGraphOne.inDegree(1));
        System.out.printf("Directed Graph One - Vertex 1 out degree: %d\n\n",
                directedGraphOne.outDegree(1));
    }

    public static void main(String[] args) {
        // Test three undirected graphs
        testUndirectedGraph();

        // Test a directed graph
        testDirectedGraph();

        // Test traversal on graph one
        Graph graphOne = new Graph(8, false);
        graphOne.addEdge(0, 1);
        graphOne.addEdge(0, 2);
        graphOne.addEdge(0, 4);
        graphOne.addEdge(1, 3);
        graphOne.addEdge(1, 5);
        graphOne.addEdge(2, 3);
        graphOne.addEdge(2, 6);
        graphOne.addEdge(3, 7);
        graphOne.addEdge(4, 5);
        graphOne.addEdge(4, 6);
        graphOne.addEdge(5, 7);
        graphOne.addEdge(6, 7);

        // Test DFS algorithm on graph one
        System.out.println("DFS traversal of graph one:");
        graphOne.DFS();
        System.out.println();

        // Test BFS algorithm on graph one
        System.out.println("BFS traversal of graph one:");
        graphOne.BFS();
        System.out.println();

        // Test traversal on graph two
        Graph graphTwo = new Graph(8, false);
        graphTwo.addEdge(0, 1); //ab
        graphTwo.addEdge(0, 4); //ae
        graphTwo.addEdge(0, 5); //af
        graphTwo.addEdge(1, 5); //bf
        graphTwo.addEdge(1, 6); //bg
        graphTwo.addEdge(2, 3); //cd
        graphTwo.addEdge(2, 6); //cg
        graphTwo.addEdge(3, 7); //dh
        graphTwo.addEdge(4, 5); //ef
        graphTwo.addEdge(6, 7); //gh

        // Test DFS algorithm on graph two
        System.out.println("DFS traversal of graph two:");
        graphTwo.DFS();
        System.out.println();

        // Test BFS algorithm on graph one
        System.out.println("BFS traversal of graph two:");
        graphTwo.BFS();
        System.out.println();
    }
}