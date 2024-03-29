package problems.dynamic;

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed.
 * All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one.
 * Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting the police.
 */
public class HouseRobber2 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        System.out.println(rob(nums));
    }

    private static int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        return Math.max(rob(nums, 0, nums.length - 2), rob(nums, 1, nums.length - 1));
    }

    private static int rob(int[] nums, int lo, int hi) {
        int prev1 = 0;
        int prev2 = 0;

        for (int i = lo; i <= hi; i++) {
            int temp = prev1;
            prev1 = Math.max(prev2+nums[i], prev1);
            prev2 = temp;
        }
        return prev1;
    }

    ////
    public int rob2(int[] nums) {

        int n = nums.length;

        if (n == 1) {
            return nums[0];
        }

        int[] first = new int[n+1];
        first[0] = 0;
        first[1] = nums[0];

        for (int i = 2; i <= n - 1; i++) {
            first[i] = Math.max(first[i-1], first[i-2] + nums[i-1]);
        }

        int[] second = new int[n+1];
        second[0] = 0;
        second[1] = 0;

        for (int i = 2; i <= n; i++) {
            second[i] = Math.max(second[i-1], second[i-2] + nums[i-1]);
        }

        return Math.max(first[n-1], second[n]);
    }

    /////// simplified ^
    public int rob3(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int[] first = new int[n+1];
        first[0] = 0;
        first[1] = nums[0];
        int[] second = new int[n+1];
        second[0] = 0;
        second[1] = 0;

        for (int i = 2; i <= n; i++) {
            first[i] = Math.max(first[i-1], first[i-2] + nums[i-1]);
            second[i] = Math.max(second[i-1], second[i-2] + nums[i-1]);
        }

        return Math.max(first[n-1], second[n]);
    }
}
