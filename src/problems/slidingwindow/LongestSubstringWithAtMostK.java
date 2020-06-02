package problems.slidingwindow;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the length of the longest substring T that contains at most k distinct characters
 */
public class LongestSubstringWithAtMostK {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pqpqs", 2));
    }

    // O(N), O(1)
    private static int lengthOfLongestSubstring2(String s, int k) {
        int[] map = new int[256];
        int start = 0, end = 0, maxLen = Integer.MIN_VALUE, counter = 0;

        while (end < s.length()) {
            char c1 = s.charAt(end);
            map[c1]++;
            if (map[c1] == 1) {
                counter++;
            }
            while (counter > k) {
                char c2 = s.charAt(start);
                map[c2]--;
                if (map[c2] == 0) {
                    counter--;
                }
                start++;
            }
            maxLen = Math.max(maxLen, end - start +1);
            end++;
        }

        if (maxLen == Integer.MIN_VALUE) {
            return 0;
        }
        return maxLen;
    }

    private static int lengthOfLongestSubstring(String s, int k) {

        if (s.length() * k == 0) {
            return 0;
        }

        int max = 1;
        int left = 0, right = 0;
        Map<Character, Integer> map = new HashMap<>(); // char, index

        while (right < s.length()) {
            map.put(s.charAt(right), right++);
            if (map.size() > k) {
                // if the window is full (k most unique) remove the lowest index
                // ex: abcar - window 3, we are at r, remove b because a is present later even if it is at the left
                int lowestIndex = Collections.min(map.values());
                map.remove(s.charAt(lowestIndex));
                left = lowestIndex + 1;
            }
            max = Math.max(max, right - left);
        }
        return max;
    }

}
