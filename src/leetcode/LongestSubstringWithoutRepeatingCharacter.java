package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class LongestSubstringWithoutRepeatingCharacter {
    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
    }

    // O(n), space: O(n)
    public static int lengthOfLongestSubstring(String s) {
        int left = 0;
        int right = 0;

        Set<Character> set = new HashSet<>();
        int max = 0;
        while (right < s.length()) {
            if (!set.contains(s.charAt(right))) {
                set.add(s.charAt(right++));
            } else {
                set.remove(s.charAt(left++));
            }
            max = Math.max(max, right - left);
        }

        return max;
    }
}
