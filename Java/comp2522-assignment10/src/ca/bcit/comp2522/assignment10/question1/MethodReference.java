package ca.bcit.comp2522.assignment10.question1;

public class MethodReference {
    public static void consumeIt(){
        System.out.println("This is method reference");
    }

    public static void main(String[] args) {
        // Referring static method
        MyConsumer functionReference = MethodReference::consumeIt;
        // Calling interface method
        functionReference.consume();
    }
}
