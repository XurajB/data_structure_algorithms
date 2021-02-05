package problems.string;

import java.util.ArrayDeque;
import java.util.Deque;

public class ValidParenthesisString {
    public static void main(String[] args) {
        System.out.println(checkValidString("()*(()*"));
    }

    // (*)) = true, we can assume * as open that makes it balanced
    // )*() = false, for the first ), there is no ( or * to balance it
    // *()) = * can balance the last )
    // **()) = one of the starts will balance last bracket
    // *( = false, ( does not have corresponding ), open should come before close or *
    // (* = true
    // using last example, position is important
    private static boolean checkValidString(String s) {
        Deque<Integer> open = new ArrayDeque<>();
        Deque<Integer> star = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                open.push(i);
            } else if (c == '*') {
                star.push(i);
            } else {
                if (!open.isEmpty()) {
                    open.pop();
                } else if (!star.isEmpty()) {
                    star.pop();
                } else {
                    return false;
                }
            }
        }

        // so far we balanced close brackets, now need to balance open brackets
        // if there are open brackets left, and there could be star on left or right. only star after open is valid
        // since we are parsing from left to right, the stacks have indices in ascending order
        while (!open.isEmpty() && !star.isEmpty()) {
            if (open.peek() < star.peek()) {
                open.pop();
                star.pop();
            } else {
                return false;
            }
        }

        return open.isEmpty();
    }

    ///////////////////////
    // using above algorithm, we can use variables instead of stack
    // low: take * as ) if there are ( not matched
    // high: take * as ( any time
    private static boolean checkValidString2(String s) {
        int low = 0;
        int high = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                low++;
                high++;
            } else if (s.charAt(i) == ')') {
                if (low > 0) {
                    low--;
                }
                high--;
            } else {
                if (low > 0) {
                    low--;
                }
                high++;
            }
            if (high < 0) {
                return false;
            }
        }

        return low == 0;
    }
}
