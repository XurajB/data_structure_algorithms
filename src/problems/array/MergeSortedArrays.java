package problems.array;

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
        int p1 = m-1;
        int p2 = n-1;

        int k = m + n - 1;

        // do it from the back to utilize the additional space.
        // doing it from the front will cause overwriting
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[k] = nums1[p1];
                p1--;
            } else {
                nums1[k] = nums2[p2];
                p2--;
            }

            k--;
        }

        // p1 could have ended early
        // so just copy p2
        while (p2 >= 0) {
            nums1[k--] = nums2[p2--];
        }
    }
}
