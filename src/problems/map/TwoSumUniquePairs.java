package problems.map;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an int array nums and an int target, find how many unique pairs in the array such that their sum is equal to target.
 * Return the number of pairs.
 * https://leetcode.com/discuss/interview-question/372434
 */
public class TwoSumUniquePairs {
    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 45, 46, 46};
        System.out.println(uniquePairs(nums, 47));
    }

    private static int uniquePairs(int[] nums, int target) {
        Map<Integer, Integer> complementMap = new HashMap<>();

        int count = 0;
        int prevVal = 0;
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (complementMap.containsKey(complement) && complement != prevVal) {
                count++;
                prevVal = complement;
            } else {
                complementMap.put(nums[i], i);
            }
        }

        return count;
    }
}
