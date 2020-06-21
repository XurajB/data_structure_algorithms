package problems.array;

import java.util.Arrays;

/**
 * Given an array of integers A and a pivot, rearrange A in the following order:
 * [Elements less than pivot, elements equal to pivot, elements greater than pivot]
 *
 * Same problem: given marbles with Red, White or Blue (0, 1, 2). sort them by Red, white, blue. Pivot = 1
 */
public class DutchNationalFlag {

    public static void main(String[] args) {
        dutchNationalFlag();
    }

    // O(N)
    private static void dutchNationalFlag() {
        int[] nums = {5,2,4,4,6,4,4,3};
        int pivot = 4;

        int low = 0;
        int high = nums.length - 1;

        int i = 0;
        while (i <= high) {
            if (nums[i] < pivot) {
                swap(nums, low, i);
                low++;
                i++;
            } else if (nums[i] > pivot) {
                swap(nums, high, i);
                high--;
            } else {
                i++;
            }
        }

        System.out.println(Arrays.toString(nums));
    }
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
