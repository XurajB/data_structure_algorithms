package problems.math;

/**
 * Given an integer, write a function to determine if it is a power of two.
 */
public class PowerOf2 {
    public static void main(String[] args) {
        System.out.println(isPowerOfTwo(16));
    }

    /**
     * O(1)
     * x & (-x) = x if power of two
     * power of 2, always have 1 followed by bunch of 0s
     *  4:      0 0 0 0 0 1 0 0
     * -4:      1 1 1 1 1 1 0 0 (invert all elements, add 1 to the end) AKA: 2's complement
     * 4 & -4:  0 0 0 0 0 1 0 0 (equals to x) so power of two
     */
    private static boolean isPowerOfTwo(int n) {
        if (n == 0) {
            return false;
        }
        long x = (long) n;
        return (x & (-x)) == x;
    }

    // O(logN)
    private static boolean isPowerOfTwo2(int n) {
        if (n == 0) {
            return false;
        }
        while (n % 2 == 0) {
            n = n / 2;
        }
        return n == 1;
    }
}
