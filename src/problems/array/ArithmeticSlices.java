package problems.array;

/**
 * A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
 * The function should return the number of arithmetic slices in the array A.
 */
public class ArithmeticSlices {
    public static void main(String[] args) {
        System.out.println(numberOfArithmeticSlices(new int[] {1,2,3,4}));
    }

    private static int numberOfArithmeticSlices(int[] A) {
        int n = A.length;

        int dp = 0;
        int sum = 0;

        for (int i = 2; i < n; i++) {
            if (A[i] - A[i-1] == A[i-1] - A[i-2]) {
                dp++;
                sum += dp;
            } else {
                dp = 0;
            }
        }
        return sum;
    }

    // dp
    public int numberOfArithmeticSlices2(int[] A) {
        int n = A.length;

        int[] dp = new int[n];
        int sum = 0;

        for (int i = 2; i < n; i++) {
            if (A[i] - A[i-1] == A[i-1] - A[i-2]) {
                dp[i] = 1 + dp[i-1];
                sum += dp[i];
            }
        }
        return sum;
    }
}
