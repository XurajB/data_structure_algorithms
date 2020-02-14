package algorithms.leetcode;

import java.util.Arrays;

/**
 * Find First and Last Position of Element in Sorted Array
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class SearchRange {

    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        System.out.println(Arrays.toString(searchRange(nums, 7)));
    }

    // Time: O(3 x log(n)) = O(ln(n)), Space: O(1)
    private static int[] searchRange(int[] nums, int target) {
        int targetPos = findTargetPosition(nums, target);
        if (targetPos == -1) {
            return new int[] {-1, -1};
        }

        int leftPos = findLeftTargetPos(nums, target, targetPos);
        int rightPos = findRightTargetPos(nums, target, targetPos);

        return new int[] {leftPos, rightPos};
    }

    private static int findTargetPosition(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }

        }

        return -1;
    }

    private static int findLeftTargetPos(int[] nums, int target, int targetPos) {
        int low = 0;
        int high = targetPos;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else if (mid > 0 && nums[mid-1] < target) {
                return mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private static int findRightTargetPos(int[] nums, int target, int targetPos) {
        int low = targetPos;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > target) {
                high = mid - 1;
            } else if (mid < nums.length - 1 && nums[mid+1] > target) {
                return mid;
            } else {
                low = mid + 1;
            }
        }

        return high;
    }
}
