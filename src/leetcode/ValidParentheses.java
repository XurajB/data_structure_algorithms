package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 20. Valid Parentheses
 * https://leetcode.com/problems/valid-parentheses/
 */
public class ValidParentheses {
    public static void main(String[] args) {
        System.out.println(isValid("({[]})"));
        System.out.println(isValid(")"));
        System.out.println(isValid("({[]]})"));
        System.out.println(isValid("()"));
    }

    // Time: O(n), space: O(n) in stack
    private static boolean isValid(String input) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();

        map.put(']', '[');
        map.put('}', '{');
        map.put(')', '(');
        for (char c: input.toCharArray()) {
            if (map.containsValue(c)) {
                stack.push(c);
            } else if (stack.isEmpty() || stack.pop() != map.get(c)) {
                return false;
            }
        }

        return stack.isEmpty();
    }
}
