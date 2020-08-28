package problems.sorting;

import java.util.Arrays;

/**
 * Given two strings s and t , write a function to determine if t is an anagram of s.
 */
public class ValidAnagram {
    public static void main(String[] args) {
        System.out.println(isAnagram("carr", "racr"));
    }

    // O(max(m,n))
    private static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] freq1 = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq1[s.charAt(i) - 'a']++;
        }
        int[] freq2 = new int[26];
        for (int i = 0; i < t.length(); i++) {
            freq2[t.charAt(i) - 'a']++;
        }
        return Arrays.toString(freq1).equals(Arrays.toString(freq2));
        // return Arrays.equals(freq1, freq2);
    }

    // (nLogn)
    private static boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }
}
