package leetcode;

import java.util.Stack;

/**
 * Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj.
 * Design an algorithm that takes a list of n numbers as input and checks whether there is a 132 pattern in the list.
 * https://leetcode.com/problems/132-pattern/
 */
public class Find132Pattern {
    public static void main(String[] args) {
        int[] nums = new int[] {3,1,4,2};
        System.out.println(find132pattern(nums));
    }

    // o(n)
    private static boolean find132pattern1(int[] nums) {
        int max = Integer.MIN_VALUE; // previous max. for 1 3 2 pattern
        Stack<Integer> stack = new Stack<>(); // max stack
        // maintain max stack, anything less than the max on the left fulfills 132 pattern
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() > nums[i]) {
                max = stack.pop();
            }
            if (nums[i] > max) {
                stack.push(max);
            }
            if (nums[i] < max) {
                return true;
            }
        }
        return false;
    }

    // O(n^2), O(1)
    private static boolean find132pattern(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 1; i++) {
            min = Math.min(min, nums[i]);
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i] && min < nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
