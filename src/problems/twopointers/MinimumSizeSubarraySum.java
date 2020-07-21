package problems.twopointers;

/**
 * Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
 */
public class MinimumSizeSubarraySum {
    public static void main(String[] args) {
        int[] nums = {2,3,1,2,4,3};
        System.out.println(minSubArrayLen(7, nums));
    }

    // note the question says positive integers
    // O(N), O(1)
    private static int minSubArrayLen(int s, int[] nums) {
        int i = 0;
        int j = 0;
        int sum = 0;

        int ans = Integer.MAX_VALUE;
        while (i < nums.length) {
            sum += nums[i++];

            while (sum >= s) {
                sum -= nums[j++];
                ans = Math.min(ans, i - j + 1);
            }
        }

        if (ans == Integer.MAX_VALUE) {
            return 0;
        }
        return ans;
    }
}
