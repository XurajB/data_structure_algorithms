package problems.dynamic;

/**
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
 *
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 */
public class WildcardMatching {
    public static void main(String[] args) {
        System.out.println(isMatch("cb", "?a"));
    }
    // O(m*n), O(m*n)
    // more details on RegularExpressionMatching
    private static boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;

        for (int i = 1; i <= n; i++) {
            dp[0][i] = dp[0][i-1] && p.charAt(i-1) == '*';
        }

        for (int i = 1; i < m+1; i++) {
            for (int j = 1; j < n+1; j++) {
                char curS = s.charAt(i - 1);
                char curP = p.charAt(j - 1);
                // case 1
                if (curS == curP || curP == '?') {
                    dp[i][j] = dp[i-1][j-1];
                } else if (curP == '*') {
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }
            }
        }
        return dp[m][n];
    }
}
