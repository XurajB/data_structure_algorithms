package leetcode;

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
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (wordMark[j] && set.contains(s.substring(j, i))) {
                    wordMark[i] = true;
                    break;
                }
            }
        }
        return wordMark[s.length()];
    }
}
