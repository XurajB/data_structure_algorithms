package problems.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of unique non negative integers, implement a function that finds the smallest non-negative integers
 * that is not in the array.
 */
public class DifferentNumber {
    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 3};
        System.out.println(differentNumber(nums));
    }

    // nLogn
    private static int differentNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i]) {
                return i;
            }
        }
        return nums.length;
    }

    // n
    private static int differentNumber2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int num: nums) {
            set.add(num);
        }
        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return nums.length;
    }
}
