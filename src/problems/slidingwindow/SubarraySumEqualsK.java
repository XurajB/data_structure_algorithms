package problems.slidingwindow;

import java.util.Arrays;

/**
 * Given an array of positive integers, find the contiguous subarray that sums to a given number X.
 * For example, input = [1,2,3,5,2] and X=8, Result = [2,3] // indexes from start to end
 */
public class SubarraySumEqualsK {
    public static void main(String[] args) {
        int[] nums = {1,2,35,5,2};
        System.out.println(Arrays.toString(subarraySumK(nums, 7)));
    }

    // assuming only positive values in input
    // if positive and negative - check Arrays/SubarraySumEqualsK
    private static int[] subarraySumK(int[] nums, int k) {
        int start = 0;
        int end = 0;

        int sum = 0;
        while (end < nums.length) {
            if (sum > k) {
                sum -= nums[start];
                start++;
            } else if (sum < k) {
                sum += nums[end];
                end++;
            } else {
                return new int[] {start, end - 1};
            }
        }

        // for the last element
        if (sum == k) {
            return new int[] {start, end - 1};
        }
        return new int[]{};
    }
}
