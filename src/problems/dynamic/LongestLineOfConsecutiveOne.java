package problems.dynamic;

/**
 * Given a 01 matrix M, find the longest line of consecutive one in the matrix. The line could be horizontal, vertical, diagonal or anti-diagonal.
 */
public class LongestLineOfConsecutiveOne {
    public static void main(String[] args) {
        int[][] M = {{0,1,1,0},{0,1,1,0},{0,0,0,1}};
        System.out.println(longestLine(M));
    }

    // O(mn), O(4mn)
    private static int longestLine(int[][] M) {
        if (M == null || M.length == 0) {
            return 0;
        }
        int m = M.length;
        int n = M[0].length;

        int[][][] dp = new int[m][n][4]; // 4 ways
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (M[i][j] == 1) {
                    // vertical
                    dp[i][j][0] = (i > 0) ? dp[i-1][j][0] + 1 : 1;
                    count = Math.max(count, dp[i][j][0]);
                    // horizontal
                    dp[i][j][1] = (j > 0) ? dp[i][j-1][1] + 1 : 1;
                    count = Math.max(count, dp[i][j][1]);
                    // diagonal
                    dp[i][j][2] = (i > 0 && j > 0) ? dp[i-1][j-1][2] + 1 : 1;
                    count = Math.max(count, dp[i][j][2]);
                    // anti diagonal
                    dp[i][j][3] = (i > 0 && j < n - 1) ? dp[i-1][j+1][3] + 1 : 1;
                    count = Math.max(count, dp[i][j][3]);
                }

            }
        }
        return count;
    }

    ///
    // another constant space way would be to do 4 loops in 4 directions separately
}
