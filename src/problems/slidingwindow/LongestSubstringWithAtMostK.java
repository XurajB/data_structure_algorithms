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

    private static int lengthOfLongestSubstring(String s, int k) {

        if (s.length() * k == 0) {
            return 0;
        }

        int max = 1;
        int left = 0, right = 0;
        Map<Character, Integer> map = new HashMap<>(); // char, index

        while (right < s.length()) {
            map.put(s.charAt(right), right++);
            if (map.size() == k + 1) {
                int lowestIndex = Collections.min(map.values());
                map.remove(s.charAt(lowestIndex));
                left = lowestIndex + 1;
            }
            max = Math.max(max, right - left);
        }
        return max;
    }

}
