package ca.bcit.comp2522.assignment9.question6;

import java.util.Stack;

public class Question6 {
    private final Stack<Character> characterStack = new Stack<>();

    public Boolean isPalindrome(String strToTest) {
        for (int index = 0; index < strToTest.length(); ++index) {
            characterStack.push(strToTest.charAt(index));
        }

        StringBuilder newString = new StringBuilder();
        while (characterStack.size() > 0) {
            newString.append(characterStack.pop());
        }

        return strToTest.equals(newString.toString());
    }

    public void test() {
        System.out.println("-----Question 6-----");

        if (isPalindrome("abcba")) {
            System.out.println("abcba is a palindrome");
        } else {
            System.out.println("abcba is not a palindrome");
        }

        if (isPalindrome("abcabc")) {
            System.out.println("abcabc is a palindrome");
        } else {
            System.out.println("abcabc is not a palindrome");
        }
    }
}
