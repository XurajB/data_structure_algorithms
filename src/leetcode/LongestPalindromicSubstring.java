package leetcode;

/**
 * 5. Longest Palindromic Substring
 * https://leetcode.com/problems/longest-palindromic-substring/
 */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        //System.out.println(longestPalindrome("aracecarr"));
        System.out.println(longestPalindrome2("aracecarr"));
    }

    // Brute - O(N^3), space 1
    private static String longestPalindrome(String s) {
        String result = "";
        for (int x = 0; x <= s.length(); x++) {
            for (int y = x; y <= s.length(); y++) {
                String sub = s.substring(x, y);
                if (isPalindrome(sub)) {
                    if (sub.length() > result.length()) {
                        result = sub;
                    }
                }
            }
        }
        return result;
    }

    private static boolean isPalindrome(String x) {
        StringBuilder sb = new StringBuilder();
        for (int i = x.length() - 1; i >= 0; i--) {
            sb.append(x.charAt(i));
        }

        return (x.equals(sb.toString()));
    }

    // O(N^2), space 1
    private static String longestPalindrome2(String s) {
        if (s == null || s.length() < 1) return "";

        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandFromMiddle(s, i, i); // for cases like - racecar where we have one e so have to compare with same char
            int len2 = expandFromMiddle(s, i, i+1); // for cases like abba
            int len = Math.max(len1, len2);

            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private static int expandFromMiddle(String s, int left, int right) {
        if (s == null || left > right) return 0;
        while (left >= 0 && right < s.length() &&
                s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }
}
