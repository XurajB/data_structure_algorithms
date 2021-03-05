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

    // no space
    public int longestValidParentheses2(String s) {
        if (s == null) return -1;
        if (s.length() == 0) return 0;

        int sum = 0, res = 0, len = 0, n = s.length();
        // Scan the string from left side, plus 1 for '(' and minus 1 for ')'.
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                sum++;
            } else {
                sum--;
            }

            if (sum < 0) {
                sum = 0;
                len = 0;
            } else {
                len++;
                if (sum == 0) res = Math.max(res, len);
            }
        }
        // Scan again from right side, plus 1 for ')' and minus 1 for '('.
        sum = 0;
        len = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == ')') {
                sum++;
            } else {
                sum--;
            }

            if (sum < 0) {
                sum = 0;
                len = 0;
            } else {
                len++;
                if (sum == 0) res = Math.max(res, len);
            }
        }
        return res;
    }
}
