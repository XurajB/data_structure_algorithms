package problems.string;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Given a list of words, each word consists of English lowercase letters.
 * Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.  For example, "abc" is a predecessor of "abac".
 * A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.
 * Return the longest possible length of a word chain with words chosen from the given list of words.
 */
public class LongestStringChain {
    public static void main(String[] args) {
        String[] words = {"a","b","ba","bca","bda","bdca"};
        System.out.println(longestStrChain(words));
    }

    // O(n*w)
    private static int longestStrChain(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
        int ans = 0;
        // sort words by their length
        Arrays.sort(words, Comparator.comparingInt(String::length));
        // start from sorter words
        // during the loop, for each word - iterate though its length and remove one char at a time and see if we have it in our dp
        // map is used to cache the chain length
        HashMap<String, Integer> map = new HashMap<>(); // word, chain length
        for (String word: words) {
            // each word has 1 chain
            map.put(word, 1);
            for (int i = 0; i < word.length(); i++) {
                StringBuilder sb = new StringBuilder(word);
                sb.deleteCharAt(i);
                String next = sb.toString();
                // only add longest chain so far to the current word
                // we want to find out longest possible
                if (map.containsKey(next) && map.get(next) + 1 > map.get(word)) {
                    map.put(word, map.get(next) + 1);
                }
            }
            ans = Math.max(ans, map.get(word));
        }

        return ans;
    }
}
