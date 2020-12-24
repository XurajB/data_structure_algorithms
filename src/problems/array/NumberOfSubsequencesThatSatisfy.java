package problems.array;

import java.util.Arrays;

/**
 * Given an array of integers nums and an integer target.
 * Return the number of non-empty subsequences of nums such that the sum of the minimum and maximum element on it is less or equal to target.
 * Since the answer may be too large, return it modulo 109 + 7.
 */
public class NumberOfSubsequencesThatSatisfy {
    public static void main(String[] args) {
        System.out.println(numSubseq(new int[] {3,5,6,7}, 9));
    }

    // O(N), O(N)
    private static int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = 0;
        int n = nums.length;

        int left = 0;
        int right = n - 1;
        int MOD = (int) 1e9+7;

        int[] pows = new int[n];
        pows[0] = 1;
        // if max and min sum to <= target, there are total 2^n-1 total combinations
        for (int i = 1; i < pows.length; i++) {
            pows[i] = (pows[i-1] * 2) % MOD;
        }

        while (left <= right) {
            if (nums[left] + nums[right] > target) {
                right--;
            } else {
                ans = (ans + pows[right-left]) % MOD;
                left++;
            }
        }

        return ans;
    }
}
