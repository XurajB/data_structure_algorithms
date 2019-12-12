package maths;

/**
 * Fibonacci series (10) - 0 1 1 2 3 5 8 13 21 34
 */
public class Fibonacci {
    public static void main(String[] args) {
        printFibo(10);
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
}
