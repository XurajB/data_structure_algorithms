package problems.dynamic;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 * https://leetcode.com/problems/maximal-square/
 */
public class MaximalSquare {
    public static void main(String[] args) {
        char[][] matrix = {
                {'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}
        };
        System.out.println(maximalSquare(matrix));
    }

    // O(mn), O(mn)
    private static int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int r = matrix.length;
        int c = matrix[0].length;

        int[][] dp = new int[r + 1][c + 1];
        int max = 0;

        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if (matrix[i-1][j-1] == '1') {
                    // dp[i][j] is the lower left corner of the square. Look at three remaining sides for a 1
                    dp[i][j] = Math.min(Math.min(dp[i][j-1], dp[i-1][j]), dp[i-1][j-1]) + 1;
                    max = Math.max(dp[i][j], max);
                }
            }
        }
        return max*max;
    }
}
