package problems.binarysearch;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * Find the minimum element. The array may contain duplicates.
 */
public class RotatedArray2 {
    public static void main(String[] args) {
        int[] nums = {2,2,2,0,1};
        System.out.println(findMin(nums));
    }

    // O(N) in worst where all elements are same
    // O(logN)
    private static int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            // 2 2 2 0 1
            if (nums[mid] > nums[high]) {
                low = mid + 1; // min lies in the right
            } else if (nums[mid] < nums[high]) {
                high = mid;
            } else {
                // mid and high are equal
                // when num[mid] == num[hi], we couldn't sure the position of minimum in mid's left or right, so just let upper bound reduce one.
                high--;
            }
        }
        return nums[low];
    }
}
