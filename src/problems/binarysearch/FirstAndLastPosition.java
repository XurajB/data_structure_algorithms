package problems.binarysearch;

import java.util.Arrays;

/**
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 *
 * If target is not found in the array, return [-1, -1].
 */
public class FirstAndLastPosition {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(searchRange(new int[] {5,7,7,8,8,10}, 8)));
    }

    private static int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[] {-1,-1};
        }
        int leftIndex = findLeftIndex(nums, target);
        if (leftIndex == nums.length || nums[leftIndex] != target) {
            return new int[] {-1,-1};
        }
        int rightIndex = findRightIndex(nums, target, leftIndex);
        return new int[] {leftIndex, rightIndex};
    }

    private static int findLeftIndex(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else if (mid > 0 && nums[mid-1] < target) {
                return mid;
            } else { // >=
                high = mid-1;
            }
        }
        return low;
    }

    private static int findRightIndex(int[] nums, int target, int left) {
        int lo = left;
        int high = nums.length - 1;
        while (lo <= high) {
            int mid = lo + (high-lo)/2;
            if (nums[mid] > target) {
                high = mid - 1;
            } else if (mid < nums.length - 1 && nums[mid+1] > target) {
                return mid;
            } else {
                lo = mid + 1;
            }
        }
        return high;
    }
}
