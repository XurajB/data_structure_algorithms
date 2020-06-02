package problems.math;

import java.util.Stack;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
 */
public class BasicCalculator2 {
    public static void main(String[] args) {
        System.out.println(calculate("22 + 2 *  2"));
        System.out.println(calculate2("22 + 2 *  2"));
    }

    // O(N), O(N)
    private static int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int num = 0;
        char lastSign = '+';

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }
            if (!Character.isDigit(c) && c != ' ' || i == s.length() - 1) {
                if (lastSign == '+') {
                    stack.push(num);
                } else if (lastSign == '-') {
                    stack.push(-num);
                } else if (lastSign == '*') {
                    stack.push(stack.pop() * num);
                } else if (lastSign == '/') {
                    stack.push(stack.pop() / num);
                }
                lastSign = c;
                num = 0;
            }
        }
        int total = 0;
        for (Integer i: stack) {
            total += i;
        }
        return total;
    }

    // O(N), O(1)
    private static int calculate2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int sum = 0;
        int tempSum = 0;
        int num = 0;
        int lastSign = '+';

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }
            if (!Character.isDigit(c) && c != ' ' || i == s.length() - 1) {
                if (lastSign == '+') {
                    sum += tempSum;
                    tempSum = num;
                } else if (lastSign == '-') {
                    sum += tempSum;
                    tempSum = -num;
                } else if (lastSign == '*') {
                    tempSum *= num;
                } else if (lastSign == '/') {
                    tempSum /= num;
                }
                lastSign = c;
                num = 0;
            }
        }
        return sum + tempSum;
    }
}
