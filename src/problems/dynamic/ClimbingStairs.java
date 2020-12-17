package problems.dynamic;

import java.util.Map;

/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 */
public class ClimbingStairs {
    public static void main(String[] args) {
        System.out.println(climbStairs(4));
    }

    // O(N), O(N)
    private static int climbStairs(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    // O(N), O(1)
    private static int climbStairs2(int n) {
        if (n == 1) {
            return 1;
        }
        int first = 1;
        int second = 1;

        for (int i = 2; i <= n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }

        return second;
    }

    ////// O(N), O(N)
    private static int helper(int n, Map<Integer, Integer> map) {
        if (n == 0 || n == 1) {
            return 1;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        }
        int next = helper(n-1, map) + helper(n-2, map);
        map.put(n, next);

        return next;
    }

    /////////
    // top-down with memo
    public int climbStairs3(int n) {
        return count(n, new int[n+1]);
    }

    private int count(int n, int[] counts) {
        if (n == 0 || n == 1) {
            return 1;
        }

        if (counts[n] > 0) {
            return counts[n];
        }

        int ans = count(n-1, counts) + count(n-2, counts);
        counts[n] = ans;

        return ans;
    }
}
