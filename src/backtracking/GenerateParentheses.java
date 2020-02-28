package backtracking;

import java.util.ArrayList;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 */
public class GenerateParentheses {
    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }

    // time and space: 4^n  - close to nth catalan number problem.
    private static ArrayList<String> generateParenthesis(int n) {
        ArrayList<String> answer = new ArrayList<>();
        generate(answer, "", 0, 0, n);
        return answer;
    }

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
}
