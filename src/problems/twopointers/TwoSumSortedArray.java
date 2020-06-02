package problems.twopointers;

import java.util.Arrays;

/**
 * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
 */
public class TwoSumSortedArray {
    public static void main(String[] args) {
        int[] numbers = {2,7,11,15};
        System.out.println(Arrays.toString(twoSum(numbers, 9)));
    }
    private static int[] twoSum(int[] numbers, int target) {
        int low = 0;
        int high = numbers.length - 1;
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum == target) {
                return new int[] {low+1, high+1}; // answers are non zero based (req)
            } else if (sum > target) {
                high--;
            } else {
                low++;
            }
        }
        return new int[] {-1, -1};
    }
}
