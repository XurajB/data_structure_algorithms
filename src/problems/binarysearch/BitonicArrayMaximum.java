package problems.binarysearch;

/**
 * Find the maximum value in a given Bitonic array. An array is considered bitonic if it is monotonically increasing and then monotonically decreasing.
 * Example: Input: [1, 3, 8, 12, 4, 2], Output: 12
 * First half is increasing, second half is decreasing
 */
public class BitonicArrayMaximum {
    public static void main(String[] args) {
        int[] nums = {1,3,8,12,4,2};
        System.out.println(findMax(nums));
    }

    private static int findMax(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low)/2;
            if (nums[mid] > nums[mid+1]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return nums[low];
    }
}
