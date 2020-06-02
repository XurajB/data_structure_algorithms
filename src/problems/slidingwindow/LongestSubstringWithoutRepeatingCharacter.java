package problems.slidingwindow;

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

    // O(N), O(1)
    public static int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int start = 0;
        int end = 0;
        int max = Integer.MIN_VALUE;
        int[] map = new int[128];
        int count = 0;
        while (end < s.length()) {
            char c = s.charAt(end);
            map[c]++;
            if (map[c] > 1) {
                count++;
            }
            if (count > 0) {
                char c2 = s.charAt(start);
                map[c2]--;
                if (map[c2] > 0) {
                    count--;
                }
                start++;
            }
            max = Math.max(max, end - start + 1);
            end++;
        }
        return max;
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
