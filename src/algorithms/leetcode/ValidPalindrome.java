package algorithms.leetcode;

/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * https://leetcode.com/problems/valid-palindrome/
 */
public class ValidPalindrome {
    public static void main(String[] args) {
        //System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome("0P"));
    }

    private static boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }

        if (s.length() == 0) {
            return true;
        }

        int length = s.length();
        int l = 0;
        int r = length - 1;

        while (l < r) {
            if (!checkValid(s.charAt(l))) {
                l++;
            } else if (!checkValid(s.charAt(r))) {
                r--;
            } else if (getLowerCase(s.charAt(l)) != getLowerCase(s.charAt(r))) {
                return false;
            } else {
                l++;
                r--;
            }
        }

        return true;
    }

    private static char getLowerCase(char a) {
        return Character.toLowerCase(a);
    }

    private static boolean checkValid(char a) {
        return (a >= 'a' && a <= 'z') || (a >= 'A' && a <= 'Z') || Character.isDigit(a);
    }
}
