package problems.slidingwindow;

import java.util.Arrays;

/**
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1.
 * In other words, one of the first string's permutations is the substring of the second string.
 */
public class PermutationInString {
    public static void main(String[] args) {
        System.out.println(checkInclusion("ab", "sadfbade"));
    }
    private static boolean checkInclusion(String s1, String s2) {
        int[] chars1 = new int[26];
        int[] chars2 = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            chars1[s1.charAt(i) - 'a']++;
        }
        int n = s1.length();
        for (int i = 0; i < s2.length(); i++) {
            chars2[s2.charAt(i) - 'a']++;
            if (i >= n) {
                chars2[s2.charAt(i - n) - 'a']--;
            }
            if (Arrays.equals(chars1, chars2)) {
                return true;
            }
        }
        return false;
    }
}
