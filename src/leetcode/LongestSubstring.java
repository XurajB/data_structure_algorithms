package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class LongestSubstring {

    public static void main(String[] args) {
        System.out.println(longestSubstring("abcabcbb"));
        System.out.println(longestSubstring("bbbbb"));
    }

    private static int longestSubstring(String a) {
        int sum = 0, i = 0, j = 0;
        Set<Character> set = new HashSet<>();
        int n = a.length();
        while (i < n && j < n) {
            if (!set.contains(a.charAt(i))) {
                set.add(a.charAt(i++));
                sum = Math.max(sum, i -j);
            } else {
                set.remove(a.charAt(j++));
            }
        }
        return sum;
    }
}
