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
            return new int[] {-1, -1};
        }
        int left = findLeft(nums, target);
        if (left == nums.length || nums[left] != target) {
            return new int[] {-1, -1};
        }
        int right = findLeft(nums, target+1) - 1; // can cause overflow
        return new int[] {left, right};
    }

    private static int findLeft(int[] nums, int target) {
        int i = 0;
        int j = nums.length; // note
        while (i < j) {
            int mid = i + (j-i)/2;
            if (nums[mid] >= target) {
                j = mid;
            } else {
                i = mid + 1;
            }
        }
        return i;
    }

    ////////////////////////
    // better
    ///////////////////////
    private static int[] searchRange2(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[] {-1, -1};
        }
        int left = find(nums, target, true);
        if (left == nums.length || nums[left] != target) {
            return new int[] {-1, -1};
        }
        int right = find(nums, target, false) - 1;
        return new int[] {left, right};
    }

    private static int find(int[] nums, int target, boolean left) {
        int i = 0;
        int j = nums.length; // note
        while (i < j) {
            int mid = i + (j-i)/2;
            if (nums[mid] > target || left && nums[mid] == target) {
                j = mid;
            } else {
                i = mid + 1;
            }
        }
        return i;
    }
}
