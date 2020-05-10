package problems.math;

public class RomanToInteger {

    public static void main(String[] args) {
        System.out.println(romanToInteger("MM"));
    }

    private static int romanToInteger(String s) {
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        int result = 0;
        int current = 0;

        while (current < nums.length) {
            if (s.startsWith(romans[current])) {
                result += nums[current];
                s = s.substring(romans[current].length());
            } else {
                current++;
            }
        }

        return result;
    }
}
