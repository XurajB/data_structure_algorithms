package problems.bfsdfs;

import java.util.HashMap;
import java.util.Map;

/**
 * You have a grid of size n x 3 and you want to paint each cell of the grid with exactly one of the three colours:
 * Red, Yellow or Green while making sure that no two adjacent cells have the same colour (i.e no two cells that share vertical or horizontal sides have the same colour).
 * You are given n the number of rows of the grid.
 *
 * Return the number of ways you can paint this grid. As the answer may grow large,
 * the answer must be computed modulo 10^9 + 7.
 */
public class NumberOfWaysToPaint {
    private int MOD = 1000000007;
    public int numOfWays(int n) {
        int[][][][] dp = new int[n + 1][4][4][4]; // number of ways to color the first n rows. 3 colors + 1 empty
        return dfs(0, 0, 0, 0, dp);
    }

    int[] color = new int[] {1,2,3};

    // O(n * 3^3)
    private int dfs(int n, int a0, int b0, int c0, int[][][][] dp) {
        if(n == dp.length - 1) {
            return 1;
        }
        if(dp[n][a0][b0][c0] != 0) {
            return dp[n][a0][b0][c0];
        }

        int count = 0;
        for(int a: color) {
            if(a == a0) {
                continue;
            }
            for(int b : color) {
                if(b == b0 || b == a) {
                    continue;
                }
                for(int c: color) {
                    if(c == c0 || c == b) {
                        continue;
                    }
                    count += dfs(n + 1, a, b, c, dp);
                    count = count % MOD;
                }
            }
        }
        dp[n][a0][b0][c0] = count;
        return dp[n][a0][b0][c0];
    }

    /////////////
    //
    int[] colors = {1,2,3};
    private int helper(int n, int c1, int c2, int c3, int row, Map<String, Integer> map) {
        String key = c1 + "," + c2 + "," + c3 + "," + row;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        if (row == n) {
            return 1;
        }
        int count = 0;
        for (int a: colors) {
            if (a == c1) {
                continue;
            }
            for (int b: colors) {
                if (b == c2 || a == b) {
                    continue;
                }
                for (int c: colors) {
                    if (c == c3 || b == c) {
                        continue;
                    }
                    count = (count + helper(n, a, b, c, row+1, map)) % MOD;
                }
            }
        }
        map.put(key, count);
        return count;
    }
}
