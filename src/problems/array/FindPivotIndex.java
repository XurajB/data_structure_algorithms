package problems.array;

import java.util.Arrays;

/**
 * Given an array of integers nums, write a method that returns the "pivot" index of this array.
 * We define the pivot index as the index where the sum of all the numbers to the left of the index is equal to the sum of all the numbers to the right of the index.
 * If no such index exists, we should return -1. If there are multiple pivot indexes, you should return the left-most pivot index.
 */
public class FindPivotIndex {
    public static void main(String[] args) {
        System.out.println(pivotIndex(new int[] {1,7,3,6,5,6}));
    }

    private static int pivotIndex(int[] nums) {
        int sum = 0;
        int leftSum = 0;
        for (int num: nums) {
            sum += num;
        }
        for (int i = 0; i < nums.length; i++) {
            if (leftSum == sum - leftSum - nums[i]) {
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }

    // O(N), O(1)
    private static int pivotIndex2(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        int curSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (curSum == sum - curSum - nums[i]) {
                return i;
            }
            curSum += nums[i];
        }
        return -1;
    }
}
