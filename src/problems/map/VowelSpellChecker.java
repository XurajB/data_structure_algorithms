package problems.map;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a wordlist, we want to implement a spellchecker that converts a query word into a correct word.
 * https://leetcode.com/problems/vowel-spellchecker/
 */
public class VowelSpellChecker {
    public static void main(String[] args) {
        String[] wordList = {"KiTe","kite","hare","Hare"};
        String[] queries = {"kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"};
        System.out.println(Arrays.toString(spellchecker(wordList, queries)));
    }

    // O(N * w) = n words in queries, w = average length of words
    private static String[] spellchecker(String[] wordlist, String[] queries) {
        Set<String> words = new HashSet<>(Arrays.asList(wordlist)); // words
        HashMap<String, String> cap = new HashMap<>(); // lower, word
        HashMap<String, String> vowel = new HashMap<>(); // devowel, word
        for (String w : wordlist) {
            String lower = w.toLowerCase(), devowel = lower.replaceAll("[aeiou]", "#"); // replace aeiou chars with #
            cap.putIfAbsent(lower, w);
            vowel.putIfAbsent(devowel, w);
        }
        for (int i = 0; i < queries.length; ++i) {
            if (words.contains(queries[i])) continue;
            String lower = queries[i].toLowerCase(), devowel = lower.replaceAll("[aeiou]", "#");
            if (cap.containsKey(lower)) {
                queries[i] = cap.get(lower);
            } else if (vowel.containsKey(devowel)) {
                queries[i] = vowel.get(devowel);
            } else {
                queries[i] = "";
            }
        }
        return queries;
    }
}
