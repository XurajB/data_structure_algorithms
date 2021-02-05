package problems.math;

/**
 * Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.
 * Return the quotient after dividing dividend by divisor.
 * The integer division should truncate toward zero, which means losing its fractional part. For example, truncate(8.345) = 8 and truncate(-2.7335) = -2.
 *
 * Note:
 * Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−2^31,  2^31 − 1].
 * For this problem, assume that your function returns 2^31 − 1 when the division result overflows.
 */
public class DivideTwoIntegers {
    public static void main(String[] args) {
        System.out.println(divide(10, 3));
    }

    // approach 1: repeated subtraction
    // O(N), O(1)
    private static int divide(int dividend, int divisor) {
        // special case: overflow
        // -min / -1 => +min
        // since in java the range is: -2^31 to 2^31-1
        // this will cause overflow because we have one more -ve than +ve
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE; // according to the question
        }

        // we can also convert both nums to positive
        // but since we have more space to work with -ve
        // we are converting them to -ve
        // also Math.abs will overflow for MIN_VALUE
        int negatives = 2;
        if (dividend > 0) {
            negatives--;
            dividend = -dividend; // this is better than doing -1*dividend
        }
        if (divisor > 0) {
            negatives--;
            divisor = -divisor;
        }

        // using first approach, we do repeated subtraction
        int quotient = 0;
        while (dividend - divisor <= 0) {
            quotient--;
            dividend -= divisor;
        }

        // if there was one -ve, then quotient remains -ve
        // otherwise +ve
        if (negatives != 1) {
            quotient = -quotient;
        }

        return quotient;
    }

    private static int HALF_INT_MIN = -1073741824;

    // Repeated exponential searches
    // O(log^2N), O(1)
    private static int divide2(int dividend, int divisor) {
        // linear search is too slow because we only subtract one copy of divisor from the dividend.
        // in this approach we try and subtract multiple copies of divisor each time.
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        /* We need to convert both numbers to negatives.
         * Also, we count the number of negatives signs. */
        int negatives = 2;
        if (dividend > 0) {
            negatives--;
            dividend = -dividend;
        }
        if (divisor > 0) {
            negatives--;
            divisor = -divisor;
        }

        int quotient = 0;
        // coz we converted to -ve
        while (divisor >= dividend) { // dividend - divisor <= 0 will result in overflow
            int multiple = -1;
            int value = divisor;

            // try to double the divisor until it no longer fits into dividend
            // value >= HALF_INT_MIN so that we don't run into same issue like approach 1 for small divisor
            while (value >= HALF_INT_MIN && value + value >= dividend) {
                value += value;
                multiple += multiple;
            }

            quotient += multiple;
            dividend -= value;
        }

        if (negatives != 1) {
            return -quotient;
        }
        return quotient;
    }
}
