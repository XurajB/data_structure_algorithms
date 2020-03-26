package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 *
 * Note: The input string may contain letters other than the parentheses ( and ).
 * https://leetcode.com/problems/remove-invalid-parentheses/
 */
public class RemoveInvalidParentheses {
    public static void main(String[] args) {
        System.out.println(removeInvalidParentheses("(a)())()"));
    }

    private static List<String> removeInvalidParentheses(String s) {
        List<String> answer = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            String removed = "";
            if (s.charAt(i) == '(' || s.charAt(i) == ')') {
                if (i == 0) {
                    removed = s.substring(1);
                } else if (i == s.length() -1) {
                    removed = s.substring(0, s.length() - 1);
                } else {
                    removed = s.substring(0, i) + s.substring(i + 1);
                }
            }
            if (isValid(removed) && !answer.contains(removed)) {
                answer.add(removed);
            }
        }

        return answer;
    }

    private static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push('(');
            } else if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
