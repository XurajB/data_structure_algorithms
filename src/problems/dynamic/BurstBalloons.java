package problems.dynamic;

/**
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons.
 * If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i.
 * After the burst, the left and right then becomes adjacent.
 * Find the maximum coins you can collect by bursting the balloons wisely.
 */
public class BurstBalloons {
    public static void main(String[] args) {
        int[] nums = {3,1,5,8};
        System.out.println(maxCoins(nums));
    }

    // the sub problems are overlapped, so we can use divide and conquer + memo
    // divide and conquer: for i, we can find max on the left side and right side and sum
    // i = i-1 * i * i+1 + best left + best right
    // top down memoization (vs bottom up dp)
    // O(N^3), O(N^2)
    private static int maxCoins(int[] nums) {
        // reframe the problem
        int n = nums.length + 2;
        int[] newNums = new int[n];
        for (int i = 0; i < nums.length; i++) {
            newNums[i+1] = nums[i];
        }
        newNums[0] = newNums[n-1] = 1;

        // cache the result
        int[][] memo = new int[n][n];

        int ans = helper(memo, newNums, 0, n-1);
        return ans;
    }

    // top-down
    private static int helper(int[][] memo, int[] nums, int left, int right) {
        // no more balloons can be added
        if (left + 1 == right) {
            return 0;
        }

        if (memo[left][right] > 0) {
            return memo[left][right];
        }

        int ans = 0;
        for (int i = left + 1; i < right; i++) {
            ans = Math.max(ans,
                    helper(memo, nums, left, i) + nums[left] * nums[i] * nums[right] + helper(memo, nums, i, right));
        }
        memo[left][right] = ans;
        return ans;
    }
}
