package problems.twopointers;

/**
 * Created by Xuraj on 12/11/2019.
 * Palindrome: atoyota
 * 12321
 */
public class Palindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome("atoyota"));
        System.out.println(isPalindrome("atoyyota"));
        System.out.println(isPalindrome("suraj"));
        System.out.println(isPalindromeNumber(1000001));
        System.out.println(isPalindromeNumber(12321));
        System.out.println(isPalindromeNumber(120210));
    }

    private static boolean isPalindrome(String text) {
        int i = 0;
        int j = text.length() - 1;
        while (i < j && text.charAt(i) == text.charAt(j)) {
            i++;
            j--;
        }
        return text.length() % 2 == 0 ? i - j == 1 : i - j == 0;
    }

    private static boolean isPalindromeNumber(int number) {
        int reverse = 0;

        int numCopy = number; //so we can compare at last
        while (number != 0) {
            int remainder = number % 10;
            reverse = reverse * 10 + remainder;
            number = number / 10;
        }

        return reverse == numCopy;
    }
}
