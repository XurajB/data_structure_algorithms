package problems.math;

import java.util.HashMap;
import java.util.Map;

/**
 * Convert integer to roman
 * https://leetcode.com/problems/integer-to-roman/
 */
public class IntegerToRoman {
    public static void main(String[] args) {
        System.out.println(intToRoman(2000));

        System.out.println(intToRoman2(2000));
    }

    private static String intToRoman(int num) {
        Map<Integer, String> map = new HashMap<>();

        map.put(1, "I");
        map.put(4, "IV");
        map.put(5, "V");
        map.put(9, "IX");
        map.put(10, "X");
        map.put(40, "XL");
        map.put(50, "L");
        map.put(90, "XC");
        map.put(100, "C");
        map.put(400, "CD");
        map.put(500, "D");
        map.put(900, "CM");
        map.put(1000, "M");

        StringBuilder sb = new StringBuilder();

        int x = num;

        while (x >= 0) {
            if (map.get(x) != null) {
                sb.append(map.get(x));
                x = num - x;
                num = x;
            } else {
                x--;
            }

        }

        return sb.toString();
    }

    private static String intToRoman2(int num) {

        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();
        int curNum = num;
        int current = 0;

        while (current < nums.length) {
            int divider = nums[current];
            int times = curNum / divider;
            curNum = curNum % divider;
            while (times >= 1) {
                sb.append(romans[current]);
                times--;
            }
            current++;
        }

        return sb.toString();
    }
}
