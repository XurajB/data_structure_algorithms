package problems.math;
/**
 * Factorial: 5! = 5 * 4 * 3 * 2 * 1 = 120
 * n! = n * n-1 * n-2 * .. 1
 */
public class Factorial {

    public static void main(String[] args) {
        System.out.println("Factorial: " + factorial(5));
        System.out.println("Factorial recursive: " + factorialRecursive(5));
    }

    private static long factorial(int num) {
        if (num <= 0) {
            throw new ArithmeticException("Can't negative number or zero");
        }

        long value = 1;

        for (int i = 1; i <= num; i++) {
            value = value * i;
        }

        return  value;
    }

    private static long factorialRecursive(int num) {
        if (num <= 0) {
            throw new ArithmeticException("Can't negative number or zero");
        }

        return num == 1 ? 1 : factorialRecursive(num - 1) * num;
    }
}
