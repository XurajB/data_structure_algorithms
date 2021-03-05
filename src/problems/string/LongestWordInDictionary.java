package problems.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a list of strings words representing an English Dictionary, find the longest word in words that can be built one character at a time by other words in words.
 * If there is more than one possible answer, return the longest word with the smallest lexicographical order.
 * If there is no answer, return the empty string.
 */
public class LongestWordInDictionary {
    public static void main(String[] args) {
        System.out.println(longestWord(new String[] {"w","wo","wor","worl", "world"}));
    }

    private static String longestWord(String[] words) {
        Arrays.sort(words);
        Set<String> built = new HashSet<>();
        String res = "";
        for (String w : words) {
            if (w.length() == 1 || built.contains(w.substring(0, w.length() - 1))) {
                res = w.length() > res.length() ? w : res;
                built.add(w);
            }
        }
        return res;
    }
}
