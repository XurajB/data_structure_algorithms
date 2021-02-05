package problems.dynamic;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 * https://leetcode.com/problems/longest-increasing-subsequence/
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
        for (int i = 0; i < nums.length; i++) {
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

    /*
     * patience sort O(nlogn)
     * Strategy:
     * case 1: if A[i] is smallest among all end candidates of active lists, start new list of length 1
     * case 2: if A[i] is largest among all end candidates of active lists, we will clone the largest active list and extend by A[i]
     * case 3: if A[i] is in between, we will find the list with largest end element that is smaller than A[i], clone and extend by A[i]. discard other lists with same length
     *
     * if we have a table of tails (dp)
     * ex: A[] =  0, 8 , 4, 12, 2, 10, 6, 14, 1
     * A[0] = 0, case 1. There are no tables
     * 0
     * A[1] = 8, case 2. Clone and extend. since we can add to the end of first tail
     * 0
     * 0 8
     * A[2] = 4. case 3. discarded 0,8
     * 0
     * 0 4
     * A[3] = 12. case 2. clone and extend
     * 0
     * 0 4
     * 0 4 12
     * A[4] = 2. case 3, clone 0 and extend to 0,2. discard 0, 4
     * 0
     * 0 2
     * 0 4 12
     * A[5] = 10. case 3 clone and extend
     * 0
     * 0 2
     * 0 2 10
     * A[6] = 6. case 3
     * 0
     * 0 2
     * 0 2 6
     * A[7] = 14, case 2
     * 0
     * 0 2
     * 0 2 6
     * 0 2 6 14
     * A[8] = 1, case 3. clone extend and discard
     * 0
     * 0 1
     * 0 2 6
     * 0 2 6 14
     *
     * Ans: LIS: 4
     */
    private static int length2(int[] nums) {
        int n = nums.length;
        int[] tails = new int[n];

        tails[0] = nums[0]; // initialize first table
        int len = 1; // length of largest IS

        for (int i = 1; i < nums.length; i++) {
            // case 1
            if (nums[i] < tails[0]) {
                // new smallest value
                tails[0] = nums[i];
            }
            // case 2
            else if (nums[i] > tails[len - 1]) {
                // nums[i] wants to extend largest subsequence
                tails[len++] = nums[i];
            }
            // case 3
            else {
                int index = findIndex(tails, 0, len - 1, nums[i]);
                tails[index] = nums[i];
            }
        }
        return len;
    }

    private static int findIndex(int[] tails, int left, int right, int key) {
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (key > tails[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }
}
