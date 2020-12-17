package problems.dynamic;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a non-empty string, encode the string such that its encoded length is the shortest.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
 *
 * Note:
 *
 * k will be a positive integer and encoded string will not be empty or have extra space.
 * You may assume that the input string contains only lowercase English letters. The string's length is at most 160.
 * If an encoding process does not make the string shorter, then do not encode it. If there are several solutions, return any of them is fine.
 */
public class EncodeWithShortestLength {
    public static void main(String[] args) {
        System.out.println(encode("abbbabbbcabbbabbbc"));
    }

    private static String encode(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        return dfs(s, new HashMap<>());
    }

    private static String dfs(String s, Map<String, String> map) {
        if (s.length() <= 4) {
            return s; // the encoded length has to smaller than s
        }
        if (map.containsKey(s)) {
            return map.get(s);
        }
        String ans = s;

        // pattern's length has to at most half of the original string's length
        for (int i = 0; i < s.length()/2; i++) {
            String pattern = s.substring(0, i+1);
            int times = countPattern(s, pattern);
            if (times * pattern.length() != s.length()) {
                continue; // the total length should be equal
            }
            String candidate = times + "[" + dfs(pattern, map) + "]";
            // the encoded string has to be less than original
            if (candidate.length() < ans.length()) {
                ans = candidate;
            }
        }

        //Input: "aabcaabcd"
        //Output: "2[aabc]d"
        // we searched for exact count of pattern in loop above
        // now see if we can separate left and right and shorten
        // in ^ example, we separated d from rest and exact match found from loop above
        for (int i = 1; i < s.length(); i++) {
            String left = dfs(s.substring(0, i), map);
            String right = dfs(s.substring(i), map);
            String candidate = left + right;
            if (candidate.length() < ans.length()) {
                ans = candidate;
            }
        }

        map.put(s, ans);
        return ans;
    }

    private static int countPattern(String s, String pattern) {
        int times = 0;
        while (s.length() >= pattern.length()) {
            String sub = s.substring(s.length() - pattern.length());
            if (sub.equals(pattern)) {
                times++;
                s = s.substring(0, s.length() - pattern.length());
            } else {
                return times;
            }
        }
        return times;
    }
}
