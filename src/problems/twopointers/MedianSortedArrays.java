package problems.twopointers;

import java.util.Arrays;

/**
 * 4. Median of Two Sorted Arrays
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 */
public class MedianSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = {1, 3, 5, 6, 6};
        int[] num2 = {2, 4, 7, 8, 9};

        System.out.println(findMedianSortedArrays(nums1, num2));
    }

    // O(m+n)
    private static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;

        int m = nums1.length;
        int n = nums2.length;

        int[] merge = new int[m + n];

        int k = 0;

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                merge[k] = nums1[i];
                i++;
            } else {
                merge[k] = nums2[j];
                j++;
            }
            k++;
        }

        while (i < nums1.length) {
            merge[k++] = nums1[i++];
        }

        while (j < nums2.length) {
            merge[k++] = nums2[j++];
        }

        double median = 0.0;
        if (merge.length % 2 == 0) {
            median = (merge[merge.length/2] + merge[merge.length/2 - 1])/2.0;
        } else {
            median = (double) merge[merge.length/2];
        }

        return median;
    }
}
