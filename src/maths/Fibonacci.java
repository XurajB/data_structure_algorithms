package maths;

/**
 * Fibonacci series (10) - 0 1 1 2 3 5 8 13 21 34
 */
public class Fibonacci {
    public static void main(String[] args) {
        printFibo(10);
        System.out.println();
        printFiboRecursive(10);
        System.out.println();
        // memoized solution. find 10th element
        System.out.println(fiboMemoize(9, new int[9]));
    }

    private static void printFibo(int num) {
        int previousNumber = 0;
        int nextNumber = 1;

        for (int a = 1; a <= num; a++) {
            System.out.print(previousNumber + " ");

            int sum = previousNumber + nextNumber;
            previousNumber = nextNumber;
            nextNumber = sum;
        }
    }

    private static void printFiboRecursive(int num) {
        for (int i = 0; i < num; i++) {
            System.out.print(fiboRecursive(i) + " ");
        }
    }

    // not very efficient, most of the computations are repetitive
    // O(2^n)
    private static int fiboRecursive(int num) {
        if (num == 0) {
            return 0;
        }
        if (num == 1 || num == 2) {
            return 1;
        }

        return fiboRecursive(num - 2) + fiboRecursive(num - 1);
    }

    // store previously calculated results in an array
    // dynamic programming
    // O(n)
    private static int fiboMemoize(int num, int[] memo) {
        int result;
        if (memo[num - 1] != 0) {
            return memo[num - 1];
        }
        if (num == 0) {
            result = 0;
        } else if (num == 1 || num == 2) {
            result = 1;
        } else {
            result = fiboMemoize(num - 1, memo) + fiboMemoize(num - 2, memo);
        }
        memo[num - 1] = result;
        return result;
    }
}
