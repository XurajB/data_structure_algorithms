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

        System.out.println(Arrays.toString(merge(nums1, num2)));
    }

    private static int[] merge(int[] nums1, int[] nums2) {
        int n = nums1.length + nums2.length;
        int[] result = new int[n];

        int i = 0, j = 0, x = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] <= nums2[j]) {
                result[x] = nums1[i];
                i++;
            } else {
                result[x] = nums2[j];
                j++;
            }
            x++;
        }

        while (i < nums1.length) {
            result[x] = nums1[i];
            i++;
            x++;
        }

        while (j < nums2.length) {
            result[x] = nums2[j];
            j++;
            x++;
        }

        return result;
    }
}
