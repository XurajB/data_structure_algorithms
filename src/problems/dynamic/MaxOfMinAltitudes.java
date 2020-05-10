package problems.dynamic;

/**
 * Given a matrix with r rows and c columns, find the maximum score of a path starting at [0, 0] and ending at [r-1, c-1].
 * The score of a path is the minimum value in that path. For example, the score of the path 8 → 4 → 5 → 9 is 4.
 *
 * Don't include the first or final entry.
 * You can only move either down or right at any point in time.
 * https://leetcode.com/discuss/interview-question/383669/
 */
public class MaxOfMinAltitudes {
    public static void main(String[] args) {
        int[][] A = {
                {1, 2, 3},
                {4, 5, 1}
        };
        System.out.println(maximumMinimumPath(A));
    }

    // O(R*C), O(R*C)
    public static int maximumMinimumPath(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;

        int[][] dp = new int[r][c];
        // we are not considering first entry
        dp[0][0] = Integer.MAX_VALUE;

        // fill first row
        for (int i = 1; i < c; i++) {
            dp[0][i] = Math.min(dp[0][i-1], grid[0][i]);
        }

        // fill first column
        for (int i = 1; i < r; i++) {
            dp[i][0] = Math.min(dp[i-1][0], grid[i][0]);
        }

        // rest
        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                if (i == r - 1 && j == c - 1) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]); // last entry is not included
                } else {
                    int score1 = Math.min(dp[i][j-1], grid[i][j]); // left
                    int score2 = Math.min(dp[i-1][j], grid[i][j]); // up
                    dp[i][j] = Math.max(score1, score2);
                }
            }
        }

        return dp[r-1][c-1];
    }
}
