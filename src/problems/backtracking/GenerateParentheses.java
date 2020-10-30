package problems.backtracking;

import java.util.ArrayList;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 */
public class GenerateParentheses {
    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }

    // time and space: 4^n  - close to nth catalan number problem.
    // 2 ^ (2n) = 4^n
    private static ArrayList<String> generateParenthesis(int n) {
        ArrayList<String> answer = new ArrayList<>();
        generate(answer, "", 0, 0, n);
        return answer;
    }
    // this is still backtracking but since we are using string which is immutable we can restore previous state
    // we would remove last char if we were using mutable object like StringBuilder or list..
    private static void generate(ArrayList<String> answer, String current, int open, int close, int max) {
        if (current.length() == max * 2) {
            answer.add(current);
            return;
        }
        if (open < max) {
            generate(answer, current+"(", open+1, close, max);
        }
        if (close < open) {
            generate(answer, current+")", open, close+1, max);
        }
    }

    // if we just want to count
    private static long ans = 0;
    private static void count(long current, long open, long close, long max) {
        if (current == max * 2) {
            ans++;
            return;
        }

        if (open < max) {
            count(current+1, open+1, close, max);
        }
        if (close < open) {
            count(current+1, open, close+1, max);
        }
    }
}
