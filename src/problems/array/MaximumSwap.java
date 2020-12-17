package problems.array;

import java.util.Arrays;
import java.util.Collections;

/**
 * Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.
 */
public class MaximumSwap {
    public static void main(String[] args) {
        System.out.println(maximumSwap(2736));
    }

    private static int maximumSwap(int num) {
        char[] chars = Integer.toString(num).toCharArray();

        int[] last = new int[10];
        for (int i = 0; i < chars.length; i++) {
            last[chars[i] - '0'] = i;
        }

        for (int i = 0; i < chars.length; i++) {
            // check if there exists any number greater than chars[i] (check from chars[i] to 9)
            for (int k = 9; k > chars[i] - '0'; k--) {
                if (last[k] > i) {
                    char temp = chars[i];
                    chars[i] = chars[last[k]];
                    chars[last[k]] = temp;

                    return Integer.parseInt(new String(chars));
                }
            }
        }

        return num;
    }
}
