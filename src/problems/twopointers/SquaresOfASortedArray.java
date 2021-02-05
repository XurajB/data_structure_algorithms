package problems.twopointers;

import java.util.Arrays;

/**
 * Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.
 */
public class SquaresOfASortedArray {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(sortedSquares(new int[] {-4,-1,0,3,10})));
    }

    private static int[] sortedSquares(int[] nums) {
        int index = nums.length - 1;
        int i = 0;
        int j = nums.length - 1;
        int[] ans = new int[nums.length];
        while (i <= j) { // note: ==, for case like -7,-3,2,3,11
            if (Math.abs(nums[i]) > Math.abs(nums[j])) {
                ans[index--] = nums[i] * nums[i];
                i++;
            } else {
                ans[index--] = nums[j] * nums[j];
                j--;
            }
        }
        return ans;
    }
}
