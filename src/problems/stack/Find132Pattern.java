package problems.stack;

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
        if (nums.length < 3) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int[] min = new int[nums.length];
        min[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            min[i] = Math.min(nums[i], min[i - 1]);
        }
        for (int j = nums.length - 1; j >= 0; j--) {
            if (nums[j] > min[j]) {
                while (!stack.isEmpty() && stack.peek() <= min[j]) {
                    stack.pop();
                }
                if (!stack.isEmpty() && stack.peek() < nums[j]) {
                    return true;
                }
                stack.push(nums[j]);
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
