package problems.array;

import java.util.Arrays;

/**
 * In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.
 * Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.
 * Return the result as a list of indices representing the starting position of each interval (0-indexed).
 * If there are multiple answers, return the lexicographically smallest one.
 */
public class MaximumSumOf3NonOverlapping {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSumOfThreeSubarrays(new int[] {1,2,1,2,6,7,5,1}, 2)));
    }

    // O(N)
    // we need 3 non overlapping intervals, with max sum of all 3 intervals.
    // if the middle interval is: [i, i+k-1], the left interval will be: [0, i-1] and right will be be [i+k, n-1]
    // in order to get lexicographical smallest order, always select the left most one
    private static int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int maxSum = 0;

        int[] sum = new int[n+1];
        int[] posLeft = new int[n]; // starting index for the left interval in range [0, i]
        int[] posRight = new int[n]; // starting index for the right interval in range [i+k, n-1]

        int[] ans = new int[3]; // 3 indexes

        for (int i = 0; i < n; i++) {
            sum[i+1] = sum[i] + nums[i];
        }

        posLeft[k-1] = 0;
        int leftMax = sum[k] - sum[0];
        for (int i = k; i < n; i++) {
            int diff = sum[i+1] - sum[i+1-k]; // i+1 coz sum is n+1
            if (diff > leftMax) {
                posLeft[i] = i+1-k; // start index of the max interval so far
                leftMax = diff;
            } else {
                posLeft[i] = posLeft[i-1];
            }
        }

        posRight[n-k] = n-k;
        int rightMax = sum[n] - sum[n-k];
        for (int i = n-k-1; i >= 0; i--) {
            int diff = sum[i+k]-sum[i];
            if (diff >= rightMax) { // Note: >= for right side
                posRight[i] = i;
                rightMax = diff;
            } else {
                posRight[i] = posRight[i+1];
            }
        }

        // test all middle intervals
        // k <= i <= n-2k
        for (int i = k; i <= n - 2*k; i++) {
            int left = posLeft[i-1];
            int right = posRight[i+k];

            int total = (sum[i+k]-sum[i]) + (sum[left + k] - sum[left]) + (sum[right + k] - sum[right]);
            if (total > maxSum) {
                maxSum = total;
                ans[0] = left;
                ans[1] = i;
                ans[2] = right;
            }
        }

        return ans;
    }
}
