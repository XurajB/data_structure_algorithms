package algorithms.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Rotate an array to right by k
 */
public class RotateArray {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        rotate(nums, 3);
        System.out.println(Arrays.toString(nums));

        int[] nums2 = {1,2,3,4,5,6,7};
        rotate2(nums2, 3);
        System.out.println(Arrays.toString(nums2));

        int[] nums3 = {1,2,3,4,5,6,7};
        rotate2(nums3, 3);
        System.out.println(Arrays.toString(nums3));
    }

    // brute force - using extra variables
    // Time: O(n * k), space: O(1)
    private static void rotate(int[] nums, int k) {
        int prev, next;
        for (int i = 0; i < k; i++) {
            prev = nums[nums.length - 1];
            for (int j = 0; j < nums.length; j++) {
                next = nums[j];
                nums[j] = prev;
                prev = next;
            }
        }
    }

    // using extra array
    // Time: O(n), space: O(n)
    private static void rotate2(int[] nums, int k) {
        int[] rotated = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            rotated[(i + k) % nums.length] = nums[i];
        }

        // copy back to nums
        for (int j = 0; j < nums.length; j++) {
            nums[j] = rotated[j];
        }
    }

    // using array reverse
    // Time: O(n), space: O(1)
    private static void rotate3(int[] nums, int k) {
        k = k % nums.length; // prevent index overflow
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k -1);
        reverse(nums, k, nums.length - 1);
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
