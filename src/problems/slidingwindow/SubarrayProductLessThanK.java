package problems.slidingwindow;

/**
 * Your are given an array of positive integers nums.
 * Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.
 */
public class SubarrayProductLessThanK {
    public static void main(String[] args) {
        int[] nums = {10,5,2,6};
        System.out.println(numSubarrayProductLessThanK(nums, 100));
    }

    private static int numSubarrayProductLessThanK(int[] nums, int k) {
        // since contiguous subarray
        int left = 0;
        int right = 0;

        int prod = 1;
        int count = 0;
        while (right < nums.length) {
            prod = prod * nums[right];
            while (right >= left && prod >= k) {
                prod = prod/nums[left];
                left++;
            }
            count += right - left +1;
            right++;
        }
        return count;
    }
}
