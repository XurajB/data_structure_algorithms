package problems.stack;

import java.util.Stack;

/**
 * Given two sequences pushed and popped with distinct values,
 * return true if and only if this could have been the result of a sequence of push and pop operations on an initially empty stack.
 */
public class ValidateStackSequences {
    public static void main(String[] args) {
        int[] pushed = {1,2,3,4,5};
        int[] popped = {4,5,3,2,1};

        System.out.println(validateStackSequences(pushed, popped));
    }

    // O(N)
    private static boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;

        for (int x: pushed) {
            stack.push(x);
            while (!stack.isEmpty() && i < pushed.length && stack.peek() == popped[i]) {
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();
    }

    // no extra space, use the pushed array as a stack
    private static boolean validateStackSequences2(int[] pushed, int[] popped) {
        int i = 0;
        int j = 0;

        for (int x: pushed) {
            pushed[i++] = x;
            while (i > 0 && pushed[i-1] == popped[j]) {
                j++;
                i--;
            }
        }

        return i == 0;
    }
}