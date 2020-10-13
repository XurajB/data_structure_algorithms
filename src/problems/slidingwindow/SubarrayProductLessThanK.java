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
        int i = 0;
        int j = 0;
        int count = 0;
        int prod = 1;

        while (j < nums.length) {
            prod = prod * nums[j];
            while (j >= i && prod >= k) {
                prod = prod / nums[i++];
            }
            count = count + j - i + 1;
            j++;
        }

        return count;
    }
}
