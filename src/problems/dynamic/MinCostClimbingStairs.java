package problems.dynamic;

/**
 * On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
 * Once you pay the cost, you can either climb one or two steps.
 * You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1.
 */
public class MinCostClimbingStairs {
    public static void main(String[] args) {
        int[] nums = {10,15,20};
        System.out.println(minCostClimbingStairs(nums));
    }

    private static int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];
        dp[0] = cost[0];
        dp[1] = cost[1];

        for (int i = 2; i <= cost.length; i++) {
            int next = i == cost.length ? 0 : cost[i];
            dp[i] = Math.min(dp[i-1] + next, dp[i-2] + next);
        }

        return dp[cost.length];
    }
}
