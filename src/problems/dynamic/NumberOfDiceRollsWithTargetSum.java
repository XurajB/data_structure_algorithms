package problems.dynamic;

/**
 * You have d dice, and each die has f faces numbered 1, 2, ..., f.
 *
 * Return the number of possible ways (out of fd total ways) modulo 10^9 + 7 to roll the dice so the sum of the face up numbers equals target
 * https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/
 */
public class NumberOfDiceRollsWithTargetSum {
    public static void main(String[] args) {
        System.out.println(numRollsToTarget(2, 6, 12));
    }

    // we need dp to accumulate possible ways upto current dice,target
    // O(d * f * target), O(d*target)
    private static int numRollsToTarget(int d, int f, int target) {
        int MOD = (int)Math.pow(10, 9) + 7;

        if (target < d || target > d * f) {
            // target cannot be less than number of dice, because each dice has a min face of 1
            // target cannot be greater than d * f
            return 0;
        }

        long[][] dp = new long[d + 1][target + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= d; i++) { // number of dice
            for (int j = 0; j <= target; j++) { // target
                for (int k = 1; k <= f; k++) { // number of faces
                    if (j >= k) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - k]) % MOD;
                    } else {
                        break;
                    }
                }
            }
        }
        return (int)dp[d][target];
    }
}
