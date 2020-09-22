package problems.stack;

import java.util.Stack;

/**
 * You're now a baseball game point recorder.
 *
 * Given a list of strings, each string can be one of the 4 following types:
 *
 * Integer (one round's score): Directly represents the number of points you get in this round.
 * "+" (one round's score): Represents that the points you get in this round are the sum of the last two valid round's points.
 * "D" (one round's score): Represents that the points you get in this round are the doubled data of the last valid round's points.
 * "C" (an operation, which isn't a round's score): Represents the last valid round's points you get were invalid and should be removed.
 */
public class BaseballGame {
    public static void main(String[] args) {
        String[] ops = {"5","2","C","D","+"};
        System.out.println(calPoints(ops));
    }

    // O(N), O(N)
    private static int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack<>();
        for (String op: ops) {
            if (op.equals("+")) {
                // sum of the last two valid rounds
                int top = stack.pop();
                int newTop = top + stack.peek();
                stack.push(top);
                stack.push(newTop);
            } else if (op.equals("C")) {
                // last round's point is invalid
                stack.pop();
            } else if (op.equals("D")) {
                // double point you got last round
                stack.push(2 * stack.peek());
            } else {
                // point you get this round
                stack.push(Integer.valueOf(op));
            }
        }
        int ans = 0;
        for (int score: stack) {
            ans += score;
        }
        return ans;
    }
}
