package problems.dynamic;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 */
public class JumpGame {
    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        System.out.println(canJump(nums));
    }

    // backtrack// O(2^n) // TLE
    private static boolean canJump(int[] nums) {
        return helper(nums, 0);
    }

    private static boolean helper(int[] nums, int current) {
        if (current == nums.length - 1) {
            return true;
        }

        // max we can go
        int max = Math.min(current+nums[current], nums.length - 1);
        // one optimization we can make is check the next from back. that means we always try to make the biggest jum first so we can reach end as soon as possible.
        // same complexity
        // for (int next = max; next > current; next--;)
        for (int next = current+1; next <= max; next++) {
            if (helper(nums, next)) {
                return true;
            }
        }
        return false;
    }

    // dynamic - top-down (optimized backtracking)
    // O(N^2)
    private static boolean canJump2(int[] nums) {
        Boolean[] dp = new Boolean[nums.length];
        dp[nums.length - 1] = true; // last one can reach itself
        return helper2(nums, 0, dp);
    }

    private static boolean helper2(int[] nums, int current, Boolean[] dp) {
        if (dp[current] != null) {
            return dp[current];
        }

        // max we can reach
        int max = Math.min(current + nums[current], nums.length - 1);
        for (int next = current + 1; next <= max; next++) {
            if (helper2(nums, next, dp)) {
                dp[current] = true; // we can reach the end from current
                return true;
            }
        }

        dp[current] = false;
        return false;
    }

    // greedy. O(N)
    private static boolean canJump3(int[] nums) {
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            // if you can't reach i, then you can't move forward
            if (max < i) {
                return false;
            }
            max = Math.max(max, nums[i] + i);
        }
        return true;
    }
}
