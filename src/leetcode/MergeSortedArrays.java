package leetcode;

import java.util.Arrays;

/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * https://leetcode.com/problems/merge-sorted-array/
 */
public class MergeSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,0,0,0};
        int[] nums2 = {2,5,6};
        merge(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));

        // Other ways to do this - copy nums1 until m to a new array and use nums1 for the merge
    }

    // Time: O(m+n), space: O(1)
    private static void merge(int[] nums1, int m, int[] nums2, int n) {
        int p = m + n - 1;
        int p1 = m - 1;
        int p2 = n - 1;

        while (p1 >= 0 && p2 >= 0) {
            nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];
        }

        // p2 could have ended early
        // we already have elements from p1
        while (p2 >= 0) {
            nums1[p--] = nums2[p2--];
        }
    }
}
