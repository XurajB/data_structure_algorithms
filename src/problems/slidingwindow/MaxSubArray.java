package problems.slidingwindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Find the contiguous subarray within an array, A of length N which has the largest sum.
 * https://www.interviewbit.com/problems/max-sum-contiguous-subarray/
 * https://leetcode.com/problems/maximum-subarray/
 */
public class MaxSubArray {
    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>(Arrays.asList(5,-3,-5));
        System.out.println(maxSubArray(A));
    }

    // time: O(n)
    // Kadane's algorithm
    public static int maxSubArray(final List<Integer> A) {
        int max = A.get(0);
        int localSum = A.get(0);

        for (int i = 1; i < A.size(); i++) {
            localSum += A.get(i);
            localSum = Math.max(localSum, A.get(i));
            max = Math.max(max, localSum);
        }

        return max;
    }

    // divide and conquer
    // O(nlogn), O(logn)
    private static int maxSubarray2(int[] nums) {
        // we can divide the array into half and calculate max from each half
        // left, right, and the sum can fall into the mid section so we also calculate mid max
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return helper(nums, 0, nums.length - 1);
    }

    private static int helper(int[] nums, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            int leftSum = helper(nums, left, mid);
            int rightSum = helper(nums, mid+1, right);
            int midSum = midArray(nums, left, right, mid);

            // return max from all three
            return Math.max(Math.max(leftSum, rightSum), midSum);
        } else {
            // in case left = right
            return nums[left];
        }
    }

    private static int midArray(int[] nums, int left, int right, int mid) {
        int leftSum = Integer.MIN_VALUE;
        int rightSum = Integer.MIN_VALUE;

        int sum = 0;
        // sum from mid to left
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            leftSum = Math.max(leftSum, sum);
        }

        sum = 0;
        // sum from mid to right
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            rightSum = Math.max(rightSum, sum);
        }

        return leftSum + rightSum;
    }
}
