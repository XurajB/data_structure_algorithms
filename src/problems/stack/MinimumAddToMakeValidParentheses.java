package problems.stack;

/**
 * Given a string S of '(' and ')' parentheses, we add the minimum number of parentheses ( '(' or ')', and in any positions ) so that the resulting parentheses string is valid.
 */
public class MinimumAddToMakeValidParentheses {
    public static void main(String[] args) {
        System.out.println(minAddToMakeValid("())"));
    }

    // this is same as min remove to make valid. but here we don't have to construct string
    private static int minAddToMakeValid(String S) {
        int open = 0;
        int close = 0;

        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '(') {
                open++;
            } else if (open > 0) {
                open--;
            } else {
                close++;
            }
        }
        return open + close;
    }
}
