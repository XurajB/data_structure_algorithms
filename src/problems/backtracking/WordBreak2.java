package problems.backtracking;

import java.util.*;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
 */
public class WordBreak2 {
    public static void main(String[] args) {
        String s = "pineapplepenapple";
        List<String> wordDict = new ArrayList<>(Arrays.asList("apple", "pen", "applepen", "pine", "pineapple"));
        System.out.println(dfs(s, wordDict, new HashMap<>()));
    }

    // O(n ^ 2), space:
    private static List<String> dfs(String s, List<String> wordDict, Map<String, LinkedList<String>> cache) {
        // cache for memoization purpose (not to prevent dupes)
        if (cache.containsKey(s)) {
            // fore cases like: apple, penapple
            // we already went through that path earlier
            return cache.get(s);
        }
        LinkedList<String> res = new LinkedList<>();
        for (String word: wordDict) {
            if (s.startsWith(word)) {
                String next = s.substring(word.length());
                if (next.isEmpty()) { // we reached end of s (start adding the word and back)
                    res.add(word);
                    continue;
                }
                List<String> sublist = dfs(next, wordDict, cache);
                for (String sub: sublist) {
                    // sublist can have multiple elements in case like - apple pen apple, applepen apple
                    // so we add pine in front of both
                    res.add(word + " " + sub);
                }

            }
        }
        cache.put(s, res);
        return res;
    }
}
