package algorithms.interviewbit;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 *
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 */
public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        System.out.println(search(nums, 5));
    }

    // O(LogN)
    private static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        // first find the rotation index - which is the index of the smallest number
        int index = findIndex(nums);
        if (nums[index] == target) {
            return index;
        }
        // then do binary search on one of the side
        if (nums[0] < target) {
            return search(nums, 0, index - 1, target);
        } else {
            return search(nums, index, nums.length - 1, target);
        }
    }

    private static int findIndex(int[] nums) {
        int low = 0;
        int n = nums.length - 1;
        int high = n;
        while (low <= high) {
            int mid = low + (high - low)/2;
            int left = (mid - 1 + n) % n;
            if (nums[low] <= nums[high]) {
                return low;
            } else if (nums[left] > nums[mid]) {
                return mid;
            } else if (nums[mid] >= nums[low]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    private static int search(int[] nums, int low, int high, int target) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] >= target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
