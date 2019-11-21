package algorithms;

import java.util.Arrays;

/**
 * Created by Xuraj on 11/12/2019.
 *
 * Algorithms - Strings,
 */
public class Algos1 {
    public void reverseString(String value) {
        if (value == null || value.length() <= 1) {
            return;
        }
        for (int i = value.length() - 1; i >= 0; i--) {
            System.out.print(value.charAt(i));
        }
    }

    public String reverseString2(String text) {
        char[] reverseArray = new char[text.length()];

        int length = text.length();
        for (int i = length - 1; i >= 0; i--) {
            reverseArray[length - i -1] = text.charAt(i);
        }

        return Arrays.toString(reverseArray);
    }
}
