package problems.binarysearch;

/**
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:
 *
 * [4,5,6,7,0,1,2] if it was rotated 4 times.
 * [0,1,2,4,5,6,7] if it was rotated 7 times.
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 *
 * Given the sorted rotated array nums, return the minimum element of this array.
 */
public class FindMinimumInSortedRotated {
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        if (nums.length == 1) {
            return nums[0];
        }
        // no rotation
        // 1 2 3 4 5
        // vs
        // 4 5 1 2 3
        if (nums[low] < nums[high]) {
            return nums[low];
        }
        int n = nums.length;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            // 5 in above case
            if (mid < n-1 && nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }
            if (mid > 0 && nums[mid] < nums[mid - 1]) {
                return nums[mid];
            }
            // mid is larger than num[0] which
            // means same sorted part 1 2 3 4 5
            if (nums[mid] > nums[0]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
