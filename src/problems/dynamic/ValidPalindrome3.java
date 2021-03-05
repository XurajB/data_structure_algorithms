package problems.dynamic;

/**
 * Given a string s and an integer k, find out if the given string is a K-Palindrome or not.
 * A string is K-Palindrome if it can be transformed into a palindrome by removing at most k characters from it.
 */
public class ValidPalindrome3 {

    public static void main(String[] args) {
        System.out.println(isValidPalindrome("abcdeca", 2));
    }

    // check out LongestPalindromicSubstring
    // top down with memo
    // O(N^2)
    private static boolean isValidPalindrome(String s, int k) {
        Integer[][] memo = new Integer[s.length()][s.length()]; // ex: cccdddd
        return helper(s, 0, s.length() - 1, memo) <= k;
    }

    private static int helper(String s, int i, int j, Integer[][] memo) {
        if (i > j) {
            return 0;
        }
        if (memo[i][j] != null) {
            return memo[i][j];
        }

        int count = 0;
        if (s.charAt(i) == s.charAt(j)) {
            count = helper(s, i+1, j-1, memo);
        } else {
            count = 1 + Math.min(helper(s, i+1, j, memo), helper(s, i, j-1, memo));
        }

        memo[i][j] = count;
        return count;
    }

    //////////////////////////////////////////////////////
    // bottom up with dp
    public boolean isValidPalindrome2(String s, int k) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int len = 2; len <= n; len++) {
            for(int i = 0; i+len-1 < n; i++) {
                int j = i+len-1;
                if(s.charAt(i) == s.charAt(j)) dp[i][j] = dp[i+1][j-1];
                else dp[i][j] = Math.min(dp[i+1][j], dp[i][j-1]) + 1;
            }
        }
        return dp[0][n-1] <= k;
    }
}
