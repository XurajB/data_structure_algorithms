package problems.dynamic;

import java.util.*;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * https://leetcode.com/problems/word-break/
 */
public class WordBreak {
    public static void main(String[] args) {
        String s = "applepenapple";
        String[] wordDict = {"apple", "pen"};
        System.out.println(wordBreak(s, Arrays.asList(wordDict)));
    }

    // O(n^2). if we consider substring n, then O(n^3), space: O(n)
    private static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        boolean[] wordMark = new boolean[s.length()+1];

        wordMark[0] = true;
        for (int i = 1; i <= s.length(); i++) { // i<= because substring
            for (int j = 0; j < i; j++) {
                if (wordMark[j] && set.contains(s.substring(j, i))) {
                    wordMark[i] = true;
                    break;
                }
            }
        }
        return wordMark[s.length()];
    }

    ////// dfs
    public boolean wordBreak2(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        return canBreak(s, dict, new HashMap<>());
    }

    private boolean canBreak(String s, Set<String> dict, Map<String, Boolean> map) {
        if (s.length() == 0) {
            return true;
        }
        if (map.containsKey(s)) {
            return map.get(s);
        }
        for (String word: dict) {
            if (s.startsWith(word)) {
                boolean result = canBreak(s.substring(word.length()), dict, map);
                if (result) {
                    map.put(s, true);
                    return true;
                }
            }
        }
        map.put(s, false);
        return false;
    }
}
