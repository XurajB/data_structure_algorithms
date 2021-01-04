package problems.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given a circular array (the next element of the last element is the first element of the array),
 * print the Next Greater Number for every element.
 * The Next Greater Number of a number x is the first greater number to its traversing-order next in the array,
 * which means you could search circularly to find its next greater number.
 * If it doesn't exist, output -1 for this number.
 */
public class NextGreaterElement2 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(nextGreaterElements(new int[] {1,2,1})));
    }

    // O(N)
    private static int[] nextGreaterElements(int[] nums) {
        int[] ans = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        int n = nums.length;
        // two times because the array is circular
        // first time for normal case, second time for circular case
        for (int i = 2*n-1; i >= 0; i--) {
            int index = i % n;
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[index]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                ans[index] = -1;
            } else {
                ans[index] = nums[stack.peek()];
            }
            stack.push(index);
        }
        return ans;
    }
}
