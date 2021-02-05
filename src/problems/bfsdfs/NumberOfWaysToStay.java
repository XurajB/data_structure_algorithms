package problems.bfsdfs;

import java.util.HashMap;
import java.util.Map;

/**
 * You have a pointer at index 0 in an array of size arrLen. At each step, you can move 1 position to the left,
 * 1 position to the right in the array or stay in the same place  (The pointer should not be placed outside the array at any time).
 * Given two integers steps and arrLen, return the number of ways such that your pointer still at index 0 after exactly steps steps.
 *
 * Since the answer may be too large, return it modulo 10^9 + 7.
 */
public class NumberOfWaysToStay {
    public static void main(String[] args) {
    }

    // O(steps*arrLen)
    public int numWays(int steps, int arrLen) {
        int maxPos = Math.min(steps,arrLen);
        long[][] dp = new long[steps+1][maxPos+1];
        dp[0][0] = 1; // stay
        for(int i = 1; i <= steps; i++) {
            for(int j = 0; j < maxPos; j++) {
                // stay: dp[steps-1][pos]
                // right: dp[steps-1][pos+1]
                // left: dp[steps-1][pos-1]
                dp[i][j] = (dp[i-1][j] + dp[i-1][j+1] + (j > 0 ? dp[i-1][j-1] : 0)) % 1000000007;
            }
        }

        return (int)dp[steps][0];
    }

    ////////////////////
    // dfs with memo
    public int numWays2(int steps, int arrLen) {
        return dfs(steps, 0, arrLen, new HashMap<>());
    }
    int MOD = (int)1e9+7;
    private int dfs(int stepsLeft, int pos, int arrLen, Map<String, Integer> map) {
        String key = stepsLeft+","+pos;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        if (pos >= arrLen || pos < 0) {
            return 0;
        }
        if (stepsLeft == 0) {
            // did we return to origin
            if (pos == 0) {
                return 1;
            }
            return 0;
        }

        long total = 0;
        total += dfs(stepsLeft - 1, pos, arrLen, map) % MOD;
        total += dfs(stepsLeft - 1, pos + 1, arrLen, map) % MOD;
        total += dfs(stepsLeft - 1, pos - 1, arrLen, map) % MOD;

        map.put(key, (int)(total % MOD));
        return map.get(key);
    }
}
