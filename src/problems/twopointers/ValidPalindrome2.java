package problems.twopointers;

/**
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 * https://leetcode.com/problems/valid-palindrome-ii/
 */
public class ValidPalindrome2 {
    public static void main(String[] args) {
        System.out.println(validPalindrome("abca"));
    }
    private static boolean validPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return isPalindrome(i+1, j,s) || isPalindrome(i, j-1, s);
            }
            i++;
            j--;
        }
        return true;
    }

    private static boolean isPalindrome(int start, int end, String s) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
