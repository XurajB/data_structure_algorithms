package problems.slidingwindow;

import java.util.Arrays;

/**
 * Given a string s and an integer k, return the length of the longest substring of s such that the frequency of each character in this substring is greater than or equal to k.
 */
public class LongestSubstringWithAtLestKRepeating {
    public static void main(String[] args) {
        System.out.println(longestSubstring("aaabb", 3));
    }

    // O(26 * n)
    private static int longestSubstring(String s, int k) {
        int longest = 0;
        final int n = s.length();
        // go from 1 to 26 length of unique chars to consider
        for (int i = 1; i <= 26; i++) {
            int[] freq = new int[26];

            int left = 0;
            int right = 0;
            int distinct = 0;

            // standard sliding window
            while (right < n) {
                boolean isValid = true;
                if (freq[s.charAt(right)- 'a']++ == 0) {
                    distinct++;
                }
                while (distinct > i) {
                    freq[s.charAt(left) - 'a']--;
                    if (freq[s.charAt(left) - 'a'] == 0) {
                        distinct--;
                    }

                    left++;
                }
                for (int num : freq) {
                    if (num > 0 && num < k) {
                        isValid = false;
                        break; // if this char freq < k, no point going forward
                    }
                }
                if (isValid) {
                    longest = Math.max(longest, right - left + 1);
                }
                right++;
            }
        }
        return longest;
    }
}
