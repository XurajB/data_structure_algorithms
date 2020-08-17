package problems.dynamic;

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you
 * from robbing each of them is that adjacent houses have security system connected and it will
 * automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting the police.
 */
public class HouseRobber {
    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        System.out.println(rob(nums));
    }

    // O(N)
    private static int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        dp[1] = nums[0];

        // we look at this+grandfather or father - whichever is max
        for (int i = 1; i < nums.length; i++) {
            dp[i+1] = Math.max(dp[i-1]+nums[i], dp[i]);
        }

        return dp[nums.length];
    }

    ////////////////
    //
    // O(1) space
    private static int rob2(int[] nums) {
        int prev1 = 0;
        int prev2 = 0;

        for (int num: nums) {
            int temp = prev1;
            prev1 = Math.max(prev2 + num, prev1);
            prev2 = temp;
        }
        return prev1;
    }
}
