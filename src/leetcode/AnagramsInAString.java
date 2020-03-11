package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/
 */
public class AnagramsInAString {
    public static void main(String[] args) {
        System.out.println(findAnagrams("cbaebabacd", "abc"));
    }

    // O(Sn + Pn), O(1)
    private static List<Integer> findAnagrams(String s, String p) {
        int ns = s.length(), np = p.length();
        if (ns < np) return new ArrayList<>();

        int [] pCount = new int[26];
        int [] sCount = new int[26];
        // build reference array using string p
        for (char ch : p.toCharArray()) {
            pCount[ch - 'a']++;
        }

        List<Integer> output = new ArrayList<>();
        // sliding window on the string s
        for (int i = 0; i < ns; ++i) {
            // add one more letter
            // on the right side of the window
            sCount[s.charAt(i) - 'a']++;
            // remove one letter
            // from the left side of the window
            if (i >= np) {
                sCount[s.charAt(i - np) - 'a']--;
            }
            // compare array in the sliding window
            // with the reference array
            if (Arrays.equals(pCount, sCount)) {
                output.add(i - np + 1);
            }
        }
        return output;
    }
}
