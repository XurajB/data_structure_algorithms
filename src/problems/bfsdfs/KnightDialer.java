package problems.bfsdfs;

import java.util.Arrays;

/**
 * The chess knight has a unique movement, it may move two squares vertically and one square horizontally,
 * or two squares horizontally and one square vertically (with both forming the shape of an L)
 * We have a chess knight and a phone pad, the knight can only stand on a numeric cell
 * Given an integer n, return how many distinct phone numbers of length n we can dial.
 * You are allowed to place the knight on any numeric cell initially and then you should perform n - 1 jumps to dial a number of length n. All jumps should be valid knight jumps.
 * As the answer may be very large, return the answer modulo 109 + 7.
 */
public class KnightDialer {
    public static void main(String[] args) {
        System.out.println(knightDialer(5));
    }

    // 10 * N * max(map[i])
    private static int knightDialer(int n) {
        if (n == 0) {
            return 0;
        }
        int[][] map = {{4, 6}, {6, 8}, {7, 9}, {4, 8}, {0, 3, 9}, {}, {0, 1, 7}, {2, 6}, {1, 3}, {2, 4}};
        int MOD = (int)1e9+7;
        int[][] memo = new int[n+1][10];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(memo[i], -1);
        }
        int result = 0;
        for (int i = 0; i < 10; i++) {
            result += helper(n, i, map, MOD, memo);
            result %= MOD;
        }

        return result;
    }

    private static int helper(int n, int i, int[][] map, int mod, int[][] memo) {
        if (n == 1) {
            return 1;
        }
        if (memo[n][i] != -1) {
            return memo[n][i];
        }
        int ans = 0;
        for (int next: map[i]) {
            ans += helper(n-1, next, map, mod, memo);
            ans %= mod;
        }
        memo[n][i] = ans;
        return ans;
    }
}
