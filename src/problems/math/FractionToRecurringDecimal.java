package problems.math;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 * If multiple answers are possible, return any of them.
 */
public class FractionToRecurringDecimal {
    public static void main(String[] args) {
        System.out.println(fractionToDecimal(4,333));
    }

    // O(N)
    private static String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder ans = new StringBuilder();
        // append -
        if (numerator < 0 ^ denominator < 0) {
            ans.append("-");
        }
        // convert to long for cases like: MIN_VALUE/-1 that will overflow
        long top = Math.abs((long) numerator);
        long bottom = Math.abs((long) denominator);
        ans.append(top/bottom);
        long reminder = top % bottom;
        if (reminder == 0) {
            return ans.toString();
        }
        ans.append(".");
        Map<Long, Integer> map = new HashMap<>(); // reminder, index
        map.put(reminder, ans.length());
        while (reminder != 0) {
            reminder = reminder * 10;
            ans.append(reminder / bottom);
            reminder = reminder % bottom;
            if (map.containsKey(reminder)) {
                int index = map.get(reminder);
                ans.insert(index, "(");
                ans.append(")");
                break;
            } else {
                map.put(reminder, ans.length());
            }
        }
        return ans.toString();
    }
}
