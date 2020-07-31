package problems.math;

/**
 * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
 */
public class AddDigits {
    public static void main(String[] args) {
        System.out.println(addDigits(38));
    }
    // O(LogNum)
    private static int addDigits(int num) {
        int ans = 0;
        if (num < 10) {
            return num;
        }
        while (num >= 10) {
            ans = 0;
            while (num != 0) {
                ans = ans + num%10;
                num = num/10;
            }
            num = ans;
        }
        return ans;
    }

    //////
    // The original number is divisible by 9 if and only if the sum of its digits is divisible by 9
    // O(1)
    private static int addDigits2(int num) {
        if (num == 0) return 0;
        if (num % 9 == 0) return 9;
        return num % 9;
    }
}
