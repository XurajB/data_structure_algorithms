package problems.dynamic;

/**
 * Given a rectangular pizza represented as a rows x cols matrix containing the following characters: 'A' (an apple) and '.' (empty cell) and given the integer k.
 * You have to cut the pizza into k pieces using k-1 cuts.
 * For each cut you choose the direction: vertical or horizontal, then you choose a cut position at the cell boundary and cut the pizza into two pieces.
 * If you cut the pizza vertically, give the left part of the pizza to a person.
 * If you cut the pizza horizontally, give the upper part of the pizza to a person. Give the last piece of pizza to the last person.
 *
 * Return the number of ways of cutting the pizza such that each piece contains at least one apple. Since the answer can be a huge number, return this modulo 10^9 + 7.
 */
public class NumberOfWaysOfCuttingAPizza {
    public static void main(String[] args) {
        String[] pizza = {"A..","AAA","..."};
        System.out.println(ways(pizza, 3));
    }

    // O(k*m*n*(m+n)), O(k*m*n)
    // calculate prefix sum then recursively cut vertically and cut horizontally until k-1 times.
    // there will be repeated cases, so use dp to save previous state
    private static int ways(String[] pizza, int k) {
        int m = pizza.length;
        int n = pizza[0].length();
        // calculate prefix sum
        int[][] preSum = new int[m+1][n+1];
        for (int r = m - 1; r >= 0; r--) {
            for (int c = n - 1; c >=0; c--) {
                preSum[r][c] = preSum[r][c+1] + preSum[c][r+1] - preSum[r+1][c+1] + (pizza[r].charAt(c) == 'A' ? 1:0);
            }
        }

        // dfs
        Integer[][][] dp = new Integer[k][m][n];
        return dfs(m, n, k-1, 0, 0, dp, preSum);
    }

    private static int dfs(int m, int n, int k, int r, int c, Integer[][][] dp, int[][] preSum) {
        if (preSum[r][c] == 0) {
            return 0; // remaining piece has not apple -> invalid
        }
        if (k == 0) {
            return 1; // we found valid ways to cut k-1 times
        }
        // check cache
        if (dp[k][r][c] != null) {
            return dp[k][r][c];
        }

        int ans = 0;

        // cut horizontally
        for (int nr = r + 1; nr < m; nr++) {
            // only cut if we have upper piece at least 1 apple, otherwise move to next row
            if (preSum[r][c] - preSum[nr][c] > 0) {
                ans = (ans + dfs(m, n, k-1, nr, c, dp, preSum)) % 1_000_000_007;
            }
        }

        // cut vertically
        for (int nc = c + 1; nc < n; nc++) {
            if (preSum[r][c] - preSum[r][nc] > 0) {
                ans = (ans + dfs(m, n, k-1, r, nc, dp, preSum)) % 1_000_000_007;
            }
        }

        dp[k][r][c] = ans;
        return ans;
    }
}
