/*
 * COMP 3761, Summer 2021, Lab 10
 *
 * Student Name: Hang Liu
 * Student ID: A01173804
 *
 * This program tests floyd algorithms
 */

public class Main {
    public static void main(String[] args) {
        exampleOne();
        exampleTwo();
        exampleThree();
        exampleFour();
    }

    private static void printResult(Graph graph){
        System.out.println("Adjacency/edge-weight matrix:");
        System.out.println(graph);

        graph.floyd();
        System.out.println("Results of Floyd's algorithm:");
        System.out.println(graph.floydString());
    }

    private static void exampleOne(){
        System.out.println("-----Example 01---");

        String []vertexNames = new String[]{"1", "2", "3", "4"};
        Graph graph = new Graph(vertexNames.length, vertexNames, true);
        graph.addEdge("1", "2", 1);
        graph.addEdge("1", "4", 1);
        graph.addEdge("2", "3", 1);
        graph.addEdge("3", "4",1);
        graph.addEdge("4", "2", 1);

        printResult(graph);
    }

    private static void exampleTwo(){
        System.out.println("-----Example 02---");

        String []vertexNames = new String[]{"1", "2", "3", "4", "5"};
        Graph graph = new Graph(vertexNames.length, vertexNames, true);
        graph.addEdge("1", "2", 5);
        graph.addEdge("1", "4", 2);
        graph.addEdge("2", "3", 2);
        graph.addEdge("3", "1",3);
        graph.addEdge("3", "5",7);
        graph.addEdge("4", "3",4);
        graph.addEdge("4", "5",1);
        graph.addEdge("5", "1",1);
        graph.addEdge("5", "2",3);

        printResult(graph);
    }

    private static void exampleThree(){
        System.out.println("-----Example 03---");

        String []vertexNames = new String[]{"A", "B", "C", "D", "E", "F"};
        Graph graph = new Graph(vertexNames.length, vertexNames, true);

        graph.addEdge("A", "B", 3);
        graph.addEdge("A", "D", 8);
        graph.addEdge("B", "C", 6);
        graph.addEdge("B", "D", 6);
        graph.addEdge("C", "E", 2);
        graph.addEdge("C", "F", 2);
        graph.addEdge("D", "F", 2);
        graph.addEdge("E", "A", 9);
        graph.addEdge("E", "F", 2);
        graph.addEdge("F", "B", 1);

        printResult(graph);
    }

    private static void exampleFour(){
        System.out.println("-----Example 04---");

        String []vertexNames = new String[]{"p", "q", "r", "s", "t"};
        Graph graph = new Graph(vertexNames.length, vertexNames, true);

        graph.addEdge("p", "r", 6);
        graph.addEdge("p", "s", 3);
        graph.addEdge("q", "p", 3);
        graph.addEdge("r", "s", 2);
        graph.addEdge("s", "r", 1);
        graph.addEdge("s", "q", 1);
        graph.addEdge("t", "q", 4);
        graph.addEdge("t", "s", 2);

        printResult(graph);
    }
}