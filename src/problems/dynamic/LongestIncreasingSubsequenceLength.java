package problems.dynamic;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 */
public class LongestIncreasingSubsequenceLength {
    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18};
        System.out.println(length(nums));
    }

    // O(N^2), Space: O(N)
    private static int length(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length == 0 || nums.length == 1) {
            return nums.length;
        }

        int length = 0;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            length = Math.max(length, dp[i]);
        }
        return length;
    }
}
