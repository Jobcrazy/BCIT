/*
 * COMP 3761, Summer 2021, Lab 8
 *
 * Student Name: Hang Liu
 * Student ID: A01173804
 *
 * This program tests graph algorithms
 */

public class Main {
    public static void main(String[] args) {
        exampleOne();
        exampleTwo();
        exampleThree();
        exampleFour();
        exampleFive();
    }

    private static void exampleOne(){
        System.out.println("-----Example 01---");

        String []vertexNames = new String[]{"a", "b", "c", "d", "e", "f"};
        Graph graph = new Graph(6, vertexNames, true);
        graph.addEdge("a", "b");
        graph.addEdge("a", "e");
        graph.addEdge("a", "f");
        graph.addEdge("b", "c");
        graph.addEdge("d", "b");
        graph.addEdge("d", "c");
        graph.addEdge("e", "d");
        graph.addEdge("f", "c");
        graph.addEdge("f", "e");

        graph.DFS();
        graph.printDFSResult();

        graph.TopologySort();
        graph.printTopologyResult();

        System.out.println();
    }

    private static void exampleTwo(){
        System.out.println("-----Example 02---");

        String []vertexNames = new String[]{"w", "x", "y", "z"};
        Graph graph = new Graph(4, vertexNames, true);
        graph.addEdge("w", "x");
        graph.addEdge("w", "y");
        graph.addEdge("w", "z");
        graph.addEdge("x", "z");
        graph.addEdge("y", "z");

        graph.DFS();
        graph.printDFSResult();

        graph.TopologySort();
        graph.printTopologyResult();

        System.out.println();
    }

    private static void exampleThree(){
        System.out.println("-----Example 03---");

        String []vertexNames = new String[]{
                "socks",
                "underwear",
                "shirt",
                "pants",
                "shoes",
                "belt",
                "suspenders",
                "tie",
                "jacket"
        };
        Graph graph = new Graph(9, vertexNames, true);
        graph.addEdge("socks", "shoes");
        graph.addEdge("underwear", "pants");
        graph.addEdge("pants", "shoes");
        graph.addEdge("pants", "belt");
        graph.addEdge("pants", "suspenders");
        graph.addEdge("shirt", "suspenders");
        graph.addEdge("shirt", "tie");
        graph.addEdge("shirt", "jacket");
        graph.addEdge("belt", "jacket");
        graph.addEdge("suspenders", "jacket");
        graph.addEdge("tie", "jacket");

        graph.DFS();
        graph.printDFSResult();

        graph.TopologySort();
        graph.printTopologyResult();

        System.out.println();
    }

    private static void exampleFour(){
        System.out.println("-----Example 04---");

        String []vertexNames = new String[]{"1", "2", "3", "4", "5", "6"};
        Graph graph = new Graph(6, vertexNames, true);
        graph.addEdge("1", "4");
        graph.addEdge("2", "1");
        graph.addEdge("2", "3");
        graph.addEdge("2", "4");
        graph.addEdge("4", "3");
        graph.addEdge("5", "2");
        graph.addEdge("5", "1");
        graph.addEdge("5", "6");
        graph.addEdge("6", "2");
        graph.addEdge("6", "3");

        graph.DFS();
        graph.printDFSResult();

        graph.TopologySort();
        graph.printTopologyResult();

        System.out.println();
    }

    private static void exampleFive(){
        System.out.println("-----Example 05---");

        String []vertexNames = new String[]{"a", "b", "c", "d", "e"};
        Graph graph = new Graph(5, vertexNames, true);
        graph.addEdge("a", "b");
        graph.addEdge("a", "c");
        graph.addEdge("b", "c");
        graph.addEdge("b", "e");
        graph.addEdge("c", "e");
        graph.addEdge("d", "a");
        graph.addEdge("d", "b");
        graph.addEdge("d", "c");
        graph.addEdge("d", "e");

        graph.DFS();
        graph.printDFSResult();

        graph.TopologySort();
        graph.printTopologyResult();

        System.out.println();
    }
}