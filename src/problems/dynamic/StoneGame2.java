package problems.dynamic;

/**
 * Alex and Lee continue their games with piles of stones.  There are a number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].
 * The objective of the game is to end with the most stones.
 * Alex and Lee take turns, with Alex starting first.  Initially, M = 1.
 * On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.  Then, we set M = max(M, X).
 * The game continues until all the stones have been taken.
 * Assuming Alex and Lee play optimally, return the maximum number of stones Alex can get.
 */
public class StoneGame2 {
    public static void main(String[] args) {
        int[] piles = {2,7,9,4,4};
        System.out.println(stoneGameII(piles));
    }

    // minimax solution
    private static int stoneGameII(int[] piles) {
        if (piles == null || piles.length == 0) {
            return 0;
        }
        int n = piles.length;
        Integer[][][] dp = new Integer[n+1][n*2 + 1][2]; // i, j, score (alex, lee)
        return helper(piles, 0, 1, 1, dp); // id, alex = 1, lee = 0
    }

    // O(N^2)
    // n * n * 2 space
    private static int helper(int[] piles, int i, int m, int id, Integer[][][] dp) {
        if (i >= piles.length) {
            return 0;
        }
        if (dp[i][m][id] != null) {
            return dp[i][m][id];
        }
        int maxScore = id == 1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int currSum = 0;
        for (int x = i; x < Math.min(piles.length, i + 2 * m); x++) {
            currSum += piles[x];
            int next = helper(piles, x + 1, Math.max(x - i + 1, m), id ^ 1, dp);
            if (id == 1) {
                maxScore = Math.max(maxScore, currSum + next); // maximize
            } else {
                maxScore = Math.min(maxScore, next); // minimize
            }
        }
        return dp[i][m][id] = maxScore;
    }
}
