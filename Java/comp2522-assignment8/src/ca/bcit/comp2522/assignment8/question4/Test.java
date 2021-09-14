package ca.bcit.comp2522.assignment8.question4;

public class Test {
    private static class Solution {
        public static int[] solution(int[] A, int K) {
            int n = A.length;
            K = K % n;
            int count = gcd(K, n);
            for (int start = 0; start < count; ++start) {
                int current = start;
                int prev = A[start];
                do {
                    int next = (current + K) % n;
                    int temp = A[next];
                    A[next] = prev;
                    prev = temp;
                    current = next;
                } while (start != current);
            }

            return A;
        }

        public static int gcd(int x, int y) {
            return y > 0 ? gcd(y, x % y) : x;
        }
    }

    void test() {
        int[] A = {1, 2, 3, 4, 5};
        System.out.println("{1, 2, 3, 4, 5} rotated for 3 times:");
        A = Solution.solution(A, 3);
        for (int number : A) {
            System.out.printf("%d ", number);
        }
        System.out.println();

        System.out.println("{1, 2, 3, 4, 5} rotated for 99 times:");
        A = Solution.solution(A, 99);
        for (int number : A) {
            System.out.printf("%d ", number);
        }
        System.out.println();

        int[] B = {100};
        B = Solution.solution(B, 1000);
        System.out.println("{100} rotated for 1000 times:");
        for (int number : B) {
            System.out.printf("%d ", number);
        }
        System.out.println();
    }
}
