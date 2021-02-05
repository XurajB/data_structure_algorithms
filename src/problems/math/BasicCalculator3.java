package problems.math;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 */
public class BasicCalculator3 {
    public static void main(String[] args) {
        String s = "2*(5+5*2)/3+(6/2+8)";
        System.out.println(calculate(s));
    }

    /// using queue
    //////////////////
    private static int calculate(String s) {
        Queue<Character> tokens = new LinkedList<>();
        for (char c: s.toCharArray()) {
            if (c != ' ') {
                tokens.offer(c);
            }
        }
        tokens.offer('+');
        return calculate(tokens);
    }

    private static int calculate(Queue<Character> tokens) {
        char lastSign = '+';
        int num = 0;
        int total = 0;
        int prev = 0;

        while (!tokens.isEmpty()) {
            char c = tokens.poll();
            if (Character.isDigit(c)) {
                num = num * 10 + c -'0';
            } else if (c == '(') {
                num = calculate(tokens);
            } else {
                switch (lastSign) {
                    case '+':
                        total += prev;
                        prev = num;
                        break;
                    case '-':
                        total += prev;
                        prev = -num;
                        break;
                    case '*':
                        prev *= num;
                        break;
                    case '/':
                        prev /= num;
                        break;
                }

                if (c == ')') {
                    break; // return from recursion
                }
                lastSign = c;
                num = 0;
            }
        }
        return total + prev;
    }


    // O(N), O(N) - for recursion
    // time can be O(N^2)  if (((((((1+1))))))
    // can use two stacks - one for num and other for sign - O(N)
    private static int calculate2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int result = 0;
        int tempSum = 0;
        char lastSign = '+';

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                continue;
            }
            if (Character.isDigit(c) || c == '(') {
                int num = 0;
                if (c == '(') {
                    // capture start and end of the block
                    // (5 + (5 + 6))
                    int count = 1;
                    int end = i;
                    while (count != 0) {
                        if (s.charAt(end + 1) == '(') {
                            count++;
                        } else if (s.charAt(end + 1) == ')') {
                            count--;
                        }
                        end++;
                    }
                    // recursively calculate the block
                    num = calculate(s.substring(i + 1, end));
                    i = end;
                } else {
                    num = c - '0';
                    while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                        num = num * 10 + s.charAt(i + 1) - '0';
                        i++;
                    }
                }
                if (lastSign == '+') {
                    result += tempSum;
                    tempSum = num;
                } else if (lastSign == '-') {
                    result += tempSum;
                    tempSum = -num;
                } else if (lastSign == '*') {
                    tempSum *= num;
                } else if (lastSign == '/') {
                    tempSum /= num;
                }
            } else {
                lastSign = c;
            }
        }
        return result + tempSum;
    }
}
