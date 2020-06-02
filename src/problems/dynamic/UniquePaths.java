package problems.dynamic;

import java.util.Arrays;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * How many possible unique paths are there?
 */
public class UniquePaths {
    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 5));
    }

    // O(m * n), O(m * n)
    private static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        // start row and col will be 1
        for (int[] row: dp) {
            Arrays.fill(row, 1);
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i][j-1] + dp[i-1][j]; // add to and left rows
            }
        }

        return dp[m-1][n-1];
    }
}
