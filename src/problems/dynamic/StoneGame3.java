package problems.dynamic;

/**
 * Alice and Bob continue their games with piles of stones. There are several stones arranged in a row, and each stone has an associated value which is an integer given in the array stoneValue.
 * Alice and Bob take turns, with Alice starting first. On each player's turn, that player can take 1, 2 or 3 stones from the first remaining stones in the row.
 * The score of each player is the sum of values of the stones taken. The score of each player is 0 initially.
 * The objective of the game is to end with the highest score, and the winner is the player with the highest score and there could be a tie.
 * The game continues until all the stones have been taken.
 * Assume Alice and Bob play optimally.
 * Return "Alice" if Alice will win, "Bob" if Bob will win or "Tie" if they end the game with the same score.
 */
public class StoneGame3 {
    // O(N)
    private static String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        Integer[][] dp = new Integer[n][2];

        int finalScore = helper(stoneValue, 0, 1, dp); // alice id = 1, bob = 0
        if (finalScore > 0) {
            return "Alice";
        } else if (finalScore < 0) {
            return "Bob";
        }
        return "Tie";
    }

    // minimax
    private static int helper(int[] piles, int i, int id, Integer[][] dp) {
        if (i >= piles.length) {
            return 0;
        }
        if (dp[i][id] != null) {
            return dp[i][id];
        }
        int score = id == 1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int sum = 0;
        for (int x = i; x < Math.min(piles.length, i + 3); x++) {
            if (id == 1) {
                sum += piles[x];
                score = Math.max(score, sum + helper(piles, x + 1, id^1, dp));
            } else {
                sum -= piles[x];
                score = Math.min(score, sum + helper(piles, x + 1, id^1, dp));
            }
        }
        dp[i][id] = score;

        return score;
    }
}
