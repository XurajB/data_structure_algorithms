package problems.string;

import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 */
public class LongestValidParentheses {
    public static void main(String[] args) {
        System.out.println(longestValidParentheses(")()()())"));
    }

    private static int longestValidParentheses(String s) {
        int max = 0;
        Stack<Integer> stack = new Stack<>(); // index
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    max = Math.max(max, i-stack.peek());
                }
            }
        }
        return max;
    }
}
