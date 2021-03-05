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
    // steps: 1: create window sums
    // step2: calculate best indexes on left side
    // step3: calculate best indexes on right side
    // step4: iterate the middle part: calculate max sum using left, middle, right
    // example: [1,2,1,2,6,7,5,1]
    // window sum: [3, 3, 3, 8, 13, 12, 6]
    // left best: [0, 0, 0, 3, 4, 4, 4]
    // right best: [4, 4, 4, 4, 4, 5, 6]
    private static int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] windowSum = new int[n-k+1];

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (i >= k) {
                sum -= nums[i-k]; // remove last item from window
            }
            if (i-k+1 >= 0) {
                windowSum[i-k+1] = sum;
            }
        }

        System.out.println(Arrays.toString(windowSum));

        // we have the interval sum. we need to find i, j, k such that we can maximize w[i] + w[j] + w[k]
        // calculate best indxes on left side
        int[] left = new int[n-k+1];
        int best = 0;
        for (int i = 0; i < left.length; i++) {
            if (windowSum[i] > windowSum[best]) {
                best = i;
            }
            left[i] = best;
        }

        System.out.println(Arrays.toString(left));

        int[] right = new int[n-k+1];
        best = right.length - 1;
        for (int i = right.length - 1; i >= 0; i--) {
            if (windowSum[i] >= windowSum[best]) { // note: >=
                best = i;
            }
            right[i] = best;
        }

        System.out.println(Arrays.toString(right));

        // test all middle intervals
        int[] ans = new int[3];
        int maxSum = 0;
        for (int j = k; j < windowSum.length - k; j++) {

            int x = left[j-k];
            int y = right[j+k];

            int total = windowSum[x] + windowSum[y] + windowSum[j];
            if (total > maxSum) {
                maxSum = total;
                ans[0] = x;
                ans[1] = j;
                ans[2] = y;
            }
        }

        return ans;
    }
}
