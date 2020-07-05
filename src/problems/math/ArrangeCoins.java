package problems.math;

/**
 * You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.
 * Given n, find the total number of full staircase rows that can be formed.
 * n is a non-negative integer and fits within the range of a 32-bit signed integer.
 */
public class ArrangeCoins {
    public static void main(String[] args) {
        System.out.println(arrangeCoins(10));
    }

    // the last row is sum of all elements until n
    // 4th row = 10 coins
    // which can be calculated using sum formula: N(N+1)/2
    // we can do a binary search
    private static int arrangeCoins(int n) {
        long low = 0;
        long high = n;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            long sum = (mid * (mid + 1)) / 2; // sum formula
            if (sum == n) {
                return (int) mid;
            } else if (sum > n) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return (int) high;
    }

    // if n = 5
    // *
    // * *
    // * * (n = 5, stair 3 is not complete so return 2)
    private static int arrangeCoins2(int n) {
        if (n == 0) {
            return 0;
        }
        int sum = 0;
        int row = 0;
        int lastRow = 1;
        while (n >= 1) {
            sum += lastRow;
            lastRow++;
            row++;
            n = n - lastRow;
        }
        return row;
    }
}
