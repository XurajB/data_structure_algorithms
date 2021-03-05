package problems.backtracking;

import java.util.Arrays;

/**
 * Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.
 */
public class PartitionToKEqualSum {
    public static void main(String[] args) {
        int[] nums = {4,3,2,3,5,2,1};
        System.out.println(canPartitionKSubsets(nums, 4));
    }

    private static boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();

        int target = sum / k;
        if (sum % k != 0) {
            return false; // not possible
        }

        // visited so we know we are not using same number twice (for backtracking)
        return canPartition(nums, k, target, 0, 0, new boolean[nums.length]);
    }

    // O(n * 2^n) (we are doing recursion for n elements, choosing true/false (2) for each backtrack for subset of k times
    // O(n) ~  visited + stack O(2.n)
    private static boolean canPartition(int[] nums, int k, int target, int curSum, int index, boolean[] visited) {
        if (curSum > target) {
            return false;
        }

        if (k == 0) {
            return true;
        }

        if (curSum == target) {
            // we do the same process k-1 times
            return canPartition(nums, k-1, target, 0, 0, visited);
        }

        for (int i = index; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                if (canPartition(nums, k, target, curSum + nums[i], i + 1, visited)) {
                    return true;
                }
                visited[i] = false;
            }
        }

        return false;
    }
}
