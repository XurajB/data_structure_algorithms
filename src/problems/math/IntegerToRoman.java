package problems.math;

/**
 * Convert integer to roman
 * https://leetcode.com/problems/integer-to-roman/
 */
public class IntegerToRoman {
    public static void main(String[] args) {
        System.out.println(intToRoman2(2000));
    }

    private static String intToRoman2(int num) {

        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();

        int current = 0;
        while (current < nums.length) {
            int divider = nums[current];
            int times = num / divider;
            num = num % divider;
            while (times >= 1) {
                sb.append(romans[current]);
                times--;
            }
            current++;
        }

        return sb.toString();
    }
}
