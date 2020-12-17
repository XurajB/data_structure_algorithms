package problems.slidingwindow;

import java.util.Arrays;

/**
 * Given a string s and an integer k, return the length of the longest substring of s such that the frequency of each character in this substring is greater than or equal to k.
 */
public class LongestSubstringWithAtLestKRepeating {
    public static void main(String[] args) {
        System.out.println(longestSubstring("aaabb", 3));
    }
    private static int longestSubstring(String s, int k) {
        int longest = 0;
        // sanity check
        if(s == null || s.isEmpty()) {
            return longest;
        }

        final int L = s.length();
        if (k < 2) return L;
        final char[] CHS = s.toCharArray();
        int[] freq = new int[26];
        for(int i = 1; i <= 26; ++i){
            Arrays.fill(freq, 0);

            int lo = 0, hi = 0, distinct = 0;
            while(hi < L){
                boolean isValid = true;
                if(freq[CHS[hi] - 'a']++ == 0)
                    ++distinct;

                while(distinct > i){
                    if(--freq[CHS[lo] - 'a'] == 0)
                        --distinct;

                    ++lo;
                }

                for(int num : freq) {
                    if (num > 0 && num < k) {
                        isValid = false;
                    }
                }
                if(isValid)
                    longest = Math.max(longest, hi - lo + 1);

                ++hi;
            }
        }

        return longest;
    }
}
