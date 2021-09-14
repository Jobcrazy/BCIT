package ca.bcit.comp2522.assignment8.question6;

public class Question6 {
    interface Update {
        // There must be only one abstract method if we want to use lambda
        void notify(String updateMessage);
    }

    private static class MyTest {
        // If we want to use lambda, then the parameter must be an interface
        public static void testNotify(Update update) {
            update.notify("Hello");
        }
    }

    public void test() {
        System.out.println("---Question 6---");

        // Test functional interface and Lambda here
        MyTest.testNotify(updateMessage -> {
            System.out.println("Receive update message from the game server: "
                    + updateMessage);
        });
    }
}
