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

    private static int minCostClimbingStairs2(int[] cost) {
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];

        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(dp[i-1] + cost[i], dp[i-2] + cost[i]);
        }

        return Math.min(dp[cost.length - 1], dp[cost.length - 2]);
    }

    // O(N), O(1)
    private static int minCostClimbingStairs3(int[] cost) {
        int cost1 = cost[0];
        int cost2 = cost[1];

        for (int i = 2; i < cost.length; i++) {
            int curr = Math.min(cost1 + cost[i], cost2 + cost[i]);
            cost1 = cost2;
            cost2 = curr;
        }

        return Math.min(cost1, cost2);
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
