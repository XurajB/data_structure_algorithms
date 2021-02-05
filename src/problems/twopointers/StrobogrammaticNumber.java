package problems.twopointers;

import java.util.HashMap;
import java.util.Map;

/**
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * Write a function to determine if a number is strobogrammatic. The number is represented as a string.
 */
public class StrobogrammaticNumber {
    public static void main(String[] args) {
        System.out.println(isStrobogrammatic("659"));
    }

    // O(N), O(1)
    private static boolean isStrobogrammatic(String num) {
        if (num.isEmpty()) {
            return false;
        }
        int i = 0;
        int j = num.length() - 1;
        Map<Character, Character> map = new HashMap<>();
        map.put('8', '8');
        map.put('9', '6');
        map.put('0', '0');
        map.put('6', '9');
        map.put('1', '1');
        while (i <= j) {
            char c1 = num.charAt(i);
            char c2 = num.charAt(j);
            if (map.containsKey(c1)) {
                if (map.get(c1) != c2) {
                    return false;
                }
            } else {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
