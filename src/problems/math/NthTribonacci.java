package problems.math;

/**
 * The Tribonacci sequence Tn is defined as follows:
 * T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
 * Given n, return the value of Tn.
 */
public class NthTribonacci {
    public static void main(String[] args) {
        System.out.println(tribonacci(10));
    }

    private static int tribonacci(int n) {
        if (n <= 2) {
            return n == 0 ? 0 : 1;
        }

        int n0 = 0;
        int n1 = 1;
        int n2 = 1;

        for (int i = 2; i < n; i++) {
            int temp = n0 + n1 + n2;
            n0 = n1;
            n1 = n2;
            n2 = temp;

            System.out.println(n2 + " ");
        }

        return n2;
    }
}
