package problems.bfsdfs;

import java.util.Arrays;

/**
 * Given a non-empty array nums containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 */
public class PartitionEqualSubsetSum {
    public static void main(String[] args) {
        System.out.println(canPartition(new int[] {1,5,11,5}));
    }

    // O(m*n)
    private static boolean canPartition(int[] nums) {
        int k = 2; // m
        int sum = Arrays.stream(nums).sum();
        int target = sum / k; // n
        if (sum % k != 0) {
            return false; // not possible
        }
        int n = nums.length;
        Boolean[][] memo = new Boolean[n][target+1];
        return dfs(nums, target, 0, memo);
    }

    private static boolean dfs(int[] nums, int target, int index, Boolean[][] memo) {
        if (target == 0) {
            return true;
        }
        if (index == nums.length || target < 0) {
            return false;
        }
        if (memo[index][target] != null) {
            return memo[index][target];
        }
        boolean result = dfs(nums, target - nums[index], index+1, memo) || dfs(nums, target, index+1, memo); // decide to choose it or not. choosing it will reduce target by num.
        memo[index][target] = result;
        return result;
    }
}
