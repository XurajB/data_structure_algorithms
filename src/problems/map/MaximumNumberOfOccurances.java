package problems.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a string s, return the maximum number of ocurrences of any substring under the following rules:
 *
 * The number of unique characters in the substring must be less than or equal to maxLetters.
 * The substring size must be between minSize and maxSize inclusive.
 */
public class MaximumNumberOfOccurances {
    public static void main(String[] args) {
        System.out.println(maxFreq("aababcaab", 2, 3, 4));
    }

    private static int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        // maxSize is not relevant here because we know number of occurrences is more when we use minSize
        if (s == null || s.isEmpty() || maxLetters == 0) {
            return 0;
        }

        int max = 0;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length() - minSize + 1; i++) {
            String sub = s.substring(i, minSize + i);
            if (isValid(sub, maxLetters)) {
                map.put(sub, map.getOrDefault(sub, 0) + 1);
                max = Math.max(max, map.get(sub));
            }
        }
        return max;
    }

    private static boolean isValid(String s, int size) {
        Set<Character> set = new HashSet<>();
        for (char c: s.toCharArray()) {
            set.add(c);
        }
        return set.size() <= size;
    }
}
