package problems.string;

/**
 * Given two strings s and t, return true if they are both one edit distance apart, otherwise return false.
 *
 * A string s is said to be one distance apart from a string t if you can:
 *
 * Insert exactly one character into s to get t.
 * Delete exactly one character from s to get t.
 * Replace exactly one character of s with a different character to get t.
 */
public class OneEditDistance {
    public static void main(String[] args) {
        System.out.println(isOneEditDistance("ab", "acb"));
    }

    // O(N)
    private static boolean isOneEditDistance(String s, String t) {
        int m = s.length();
        int n = t.length();

        for (int i = 0; i < Math.min(m, n); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                // if same length, then only possible way is replace
                if (m == n) {
                    return s.substring(i + 1).equals(t.substring(i + 1));
                } else if (m > n) {
                    // s is longer
                    return s.substring(i+1).equals(t.substring(i));
                } else {
                    // t is longer
                    return s.substring(i).equals(t.substring(i+1));
                }
            }
        }

        // one of them reached end, remaining possibility is deleting last char
        return Math.abs(s.length() - t.length()) == 1;
    }

    ////
    // dp, more general solution
    public boolean isOneEditDistance2(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m+1][n+1];
        dp[0][0] = 0;
        for (int i = 1; i <= s.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= t.length(); j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= s.length(); i++) {
            char schar = s.charAt(i-1);
            for (int j = 1; j <= t.length(); j++) {
                char tchar = t.charAt(j-1);
                if (schar == tchar) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) + 1;
                }
            }
        }
        return dp[m][n] == 1;
    }
}
