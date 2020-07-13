package problems.array;

import java.util.Arrays;

/**
 * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
 * The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
 * You may assume the integer does not contain any leading zero, except the number 0 itself.
 * Input: [1,2,3]
 * Output: [1,2,4]
 */
public class PlusOne {
    public static void main(String[] args) {
        int[] digits = {9,9,9};
        System.out.println(Arrays.toString(plusOne(digits)));
    }

    private static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            // if digit is 9, make it a 0
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                // increment by one and return
                digits[i]++;
                return digits;
            }
        }
        // we are here because all numbers are 9 so we need to add 1 in front
        // we don't need to copy because ans is all 0s by default
        int[] ans = new int[digits.length + 1];
        ans[0] = 1;
        return ans;
    }
}
