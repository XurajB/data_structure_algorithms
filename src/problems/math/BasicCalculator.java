package problems.math;

import java.util.Stack;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
 * https://leetcode.com/problems/basic-calculator/
 */
public class BasicCalculator {
    public static void main(String[] args) {
        System.out.println(calculate("2 + 5 + ( 3 - 5 ) + 3"));
    }

    private static int calculate(String s) {
        int total = 0;
        int current = 0;
        int sign = 1;

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // case 1 - numbers
            if (Character.isDigit(c)) {
                current = current * 10 + (int) (c - '0');
            }

            // case 2 - +/-
            if (c == '+' || c == '-') {
                // process last operation
                total = total + current * sign;
                current = 0;
                sign = c == '+' ? 1 : -1;
            }

            // case 3 - (
            if (c == '(') {
                stack.push(total);
                stack.push(sign);

                total = 0;
                sign = 1;
            }

            // case 4 - )
            if (c == ')') {
                total = total + current * sign;
                current = 0;

                sign = stack.pop();
                int oldTotal = stack.pop();

                total = total * sign;
                total = total + oldTotal;
            }
        }

        if (current != 0) {
            total = total + current * sign;
        }
        return total;
    }


    ////// different style. similar to BasicCalculator2
    public int calculate2(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        int lastSign = 1;
        int sum = 0;

        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == s.length() - 1) {
                if (s.charAt(i) == '(') {
                    stack.push(lastSign);
                    stack.push(sum);

                    sum = 0;
                    lastSign = 1;
                } else if (s.charAt(i) == ')') {
                    sum += lastSign == 1 ? num : -num;

                    int prevSum = stack.pop();
                    int prevSign = stack.pop();

                    sum = prevSum + (prevSign == 1 ? sum : - sum);
                } else {
                    sum += lastSign == 1 ? num : -num;
                    lastSign = s.charAt(i) == '+' ? 1 : -1;
                }
                num = 0;
            }
        }

        return sum;
    }
}
