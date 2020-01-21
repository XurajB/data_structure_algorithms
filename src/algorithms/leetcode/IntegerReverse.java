package algorithms.leetcode;

/**
 * Given a 32-bit signed integer, reverse digits of an integer.
 * https://leetcode.com/problems/reverse-integer/
 */
public class IntegerReverse {

    public static void main(String[] args) {
        System.out.println(reverseInteger(1234));
        System.out.println(reverseInteger(-56343));
        System.out.println(reverseInteger(Integer.MAX_VALUE));
    }

    private static int reverseInteger(int x) {
        int reverse = 0;

        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if ((reverse > Integer.MAX_VALUE / 10) || (reverse == Integer.MAX_VALUE / 10 && pop > 7)) { // MAX_VALUE % 10 = 7
                return 0;
            }
            if ((reverse < Integer.MIN_VALUE / 10) || (reverse == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            reverse = reverse * 10 + pop;
        }

        return reverse;
    }
}
