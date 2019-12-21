package maths;

import java.util.Arrays;

/**
 * Fibonacci series (10) - 0 1 1 2 3 5 8 13 21 34
 */
public class Fibonacci {
    public static void main(String[] args) {
        printFibo(10);

        int[] ans = new int[10];
        for (int i = 2; i < 10; i++) {
            ans = fibsRecursionDynamic(i, ans);
        }
        System.out.println(Arrays.toString(ans));

        printFibDynamic(10);
    }

    private static void printFibo(int num) {
        int i = 0;
        int j = 1;

        System.out.print(i + " " + j + " ");
        for (int a = 2; a < num; a++) {
            int num1 = i + j;
            i = j;
            j = num1;
            System.out.print(num1 + " ");
        }
    }

    private static int[] fibsRecursionDynamic(int num, int[] answer) {
        answer[0] = 0;
        answer[1] = 1;

        if (answer[num] == 0) {
            answer[num] = answer[num - 1] + answer[num - 2];
        }

        return answer;
    }

    // memoization
    private static void printFibDynamic(int num) {
        int[] answer = new int[num];

        answer[0] = 0;
        answer[1] = 1;

        for (int i = 2; i < num; i++) {
            answer[i] = answer[i - 2] + answer[i - 1];
        }

        System.out.print(Arrays.toString(answer));
    }
}
