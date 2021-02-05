package problems.dynamic;

/**
 * Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.
 */
public class LongestPalindromicSubsequence {

    public static void main(String[] args) {
        System.out.println(longestPalindromeSubseq("bbbab"));
    }

    private static int longestPalindromeSubseq(String s) {
        return helper(s, 0, s.length() - 1, new Integer[s.length()][s.length()]);
    }

    // O(N^2)
    private static int helper(String s, int i, int j, Integer[][] memo) {
        if (i > j) {
            return 0;
        }
        if (i == j) {
            return 1; // one length char is valid palindrome
        }
        if (memo[i][j] != null) {
            return memo[i][j];
        }

        int count = 0;
        if (s.charAt(i) == s.charAt(j)) {
            count += 2 + helper(s, i+1, j-1, memo);
        } else {
            count += Math.max(helper(s, i+1, j, memo), helper(s, i, j-1, memo));
        }

        memo[i][j] = count;
        return count;
    }

    /////////////////
    ////
    //////// Bottom up with dp
    // O(N^2)
    private static int longestPalindromeSubseq2(String s) {
        // dp[i][j] = longest palindromic substring of length of substring(i,j)
        // if chari == charj, dp[i][j] = dp[i+1][j-1] + 2 (move two pointers + include two chars)
        // else dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1])

        int[][] dp = new int[s.length()][s.length()];

        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = 1; // one char is valid palindrome
            for (int j = i+1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }

        return dp[0][s.length()];
    }
}
