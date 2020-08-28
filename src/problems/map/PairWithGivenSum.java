package problems.map;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a list of positive integers nums and an int target, return indices of the two numbers such that they add up to a target - 30.
 *
 * Conditions:
 *
 * You will pick exactly 2 numbers.
 * You cannot pick the same element twice.
 * If you have multiple pairs, select the pair with the largest number.
 * https://leetcode.com/discuss/interview-question/356960
 */
public class PairWithGivenSum {
    public static void main(String[] args) {
        int[] nums = {20, 50, 40, 25, 30, 10};
        int target = 90;
        System.out.println(Arrays.toString(findPair(nums, target)));
    }

    private static int[] findPair(int[] nums, int target) {
        target = target - 30; // req
        int max = Integer.MIN_VALUE;
        Map<Integer, Integer> map = new HashMap<>(); // complement, index
        int[] ans = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(nums[i])) {
                if (nums[i] > max || complement > max) {
                    ans[0] = i;
                    ans[1] = map.get(nums[i]);
                }
            } else {
                map.put(complement, i);
            }
        }

        return ans;
    }
}
