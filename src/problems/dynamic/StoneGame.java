package problems.dynamic;

import java.util.Arrays;

/**
 * Alex and Lee play a game with piles of stones.  There are an even number of piles arranged in a row,
 * and each pile has a positive integer number of stones piles[i].
 *
 * The objective of the game is to end with the most stones.  The total number of stones is odd, so there are no ties.
 *
 * Alex and Lee take turns, with Alex starting first.  Each turn, a player takes the entire pile of stones from either the beginning or the end of the row.
 * This continues until there are no more piles left, at which point the person with the most stones wins.
 *
 * Assuming Alex and Lee play optimally, return True if and only if Alex wins the game.
 */
public class StoneGame {
    public static void main(String[] args) {
        int[] piles = {5,3,4,5};
        System.out.println(stoneGame(piles));
    }

    /**
     * This is a minimax problem. each player plays optimally to win but we can't greedily choose the optimal strategy
     * so we need to try all strategies. Alex will win if score(Alex) >= score(Lee), which means score(Alex) - score(Lee) >= 0
     * so score is the common parameter. if alex needs to win, then he wants to maximize score. Since lee is also playing optimally
     * he wants to minimize the score to -ve so he can win. In a current state - if Alex's turn then he can choose either left or right
     * but can't greedily pick so he tries both of them. without dp the complexity is O(2^n). with dp: O(n^2)
     */
    private static boolean stoneGame(int[] piles) {
        int n = piles.length;
        Integer[][][] dp = new Integer[n+1][n+1][2];

        return helper(0, n - 1, piles, 1, dp) >= 0; // score >= 0 means Alex wins. ID 1 = alex, 0 = lee
    }

    private static int helper(int i, int j, int[] piles, int id, Integer[][][] dp) {
        if (i >= j) {
            return 0;
        }
        if (dp[i][j][id] != null) {
            return dp[i][j][id];
        }
        int next = Math.abs(id - 1);
        if (id == 1) {
            dp[i][j][id] = Math.max(piles[i] + helper(i + 1, j, piles, next, dp), piles[j] + helper(i, j - 1, piles, next, dp)); // maximize
        } else {
            dp[i][j][id] = Math.min(-piles[i] + helper(i + 1, j, piles, next, dp), -piles[j] + helper(i, j - 1, piles, next, dp)); // minimize
        }
        return dp[i][j][id];
    }

    // O(N^2), O(N^2)
    private static boolean stoneGame1(int[] piles) {
        int n = piles.length;
        int dp[][][] = new int[n][n][2]; // i, j, {score of alex, lee}

        // initialize with alex's score if he picks first
        for (int i = 0; i < n; i++) {
            dp[i][i] = new int[] {piles[i], 0};
        }

        for (int k = 1; k < n; k++) {
            // piles [i,..,j]
            for (int i = 0; i + k < n; i++) {
                int j = i + k;

                // score if alex picks piles[i]
                int pickLeftScore = piles[i] + dp[i+1][j][1];
                // score if alex picks piles[j]
                int pickRightScore = piles[j] + dp[i][j-1][1];

                if (pickLeftScore > pickRightScore) {
                    int leeScore = dp[i+1][j][0];
                    dp[i][j] = new int[] {pickLeftScore, leeScore};
                } else {
                    int leeScore = dp[i][j-1][0];
                    dp[i][j] = new int[] {pickRightScore, leeScore};
                }
            }
        }

        return dp[0][n-1][0] > dp[0][n-1][1];
    }

    // simple two pointer.
    // this question more likely is looking for dp or minimax solution
    // O(N), O(1)
    private static boolean stoneGame2(int[] piles) {
        int i = 0;
        int j = piles.length - 1;

        int alex = 0;
        int lee = 0;

        boolean alexStarts = true;

        while (i < j) {
            if (alexStarts) {
                if (piles[i] >= piles[j]) {
                    alex += piles[i];
                    lee += piles[j];
                } else {
                    alex += piles[j];
                    lee += piles[i];
                }
            } else {
                if (piles[i] >= piles[j]) {
                    lee += piles[i];
                    alex += piles[j];
                } else {
                    lee += piles[j];
                    alex += piles[i];
                }
            }
            i++;
            j--;

        }
        return alex > lee;
    }
}
