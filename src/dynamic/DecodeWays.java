package dynamic;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 * https://leetcode.com/problems/decode-ways/
 */
public class DecodeWays {
    public static void main(String[] args) {
        System.out.println(numDecoding("12"));
        System.out.println(numDecoding("126"));
    }

    private static int numDecoding(String s) {
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;

        for (int i = 1; i <= s.length(); i++) {

            if (s.charAt(i-1)-'0' != 0) {
                dp[i] += dp[i-1];
            }
            if (i >= 2) {
                int value = (s.charAt(i-2) - '0') * 10 + (s.charAt(i-1) - '0');
                if (value >= 10 && value <= 26) {
                    dp[i] += dp[i-2];
                }
            }
        }
        return dp[s.length()];
    }
}
