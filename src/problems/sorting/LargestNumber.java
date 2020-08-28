package problems.sorting;

import java.util.Arrays;

/**
 * Given a list of non negative integers, arrange them such that they form the largest number.
 */
public class LargestNumber {
    public static void main(String[] args) {
        int[] nums = {10,2};
        System.out.println(largestNumber(nums));
    }

    static String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = Integer.toString(nums[i]);
        }

        // s1 = 9, s2 = 31
        // which one is largest? 931 or 319
        Arrays.sort(strs, (a, b) -> {
            String s1 = a + b;
            String s2 = b + a;

            return s2.compareTo(s1);
        });

        if (strs[0].equals("0")) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (String str: strs) {
            sb.append(str);
        }

        return sb.toString();
    }
}
