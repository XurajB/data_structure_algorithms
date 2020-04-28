package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.
 * It is guaranteed there is at least one word that isn't banned, and that the answer is unique.
 *
 * Words in the list of banned words are given in lowercase, and free of punctuation.
 * Words in the paragraph are not case sensitive.  The answer is in lowercase.
 * https://leetcode.com/problems/most-common-word/
 */
public class MostCommonWord {
    public static void main(String[] args) {

    }
    public String mostCommonWord(String paragraph, String[] banned) {
        // create a set of banned words
        Set<String> set = new HashSet<>();
        for (String str: banned) {
            set.add(str.toLowerCase());
        }

        Map<String, Integer> map = new HashMap<>();

        String answer = "";
        int max = 0;

        paragraph += "."; // for cases where sentence end without special chars

        StringBuilder word = new StringBuilder();
        for (char c: paragraph.toCharArray()) {
            if (Character.isLetter(c)) {
                word.append(Character.toLowerCase(c));
            } else {
                String finalWord = word.toString();
                if (finalWord.length() > 0 && !set.contains(finalWord)) {
                    map.put(finalWord, map.getOrDefault(finalWord, 0 ) + 1);
                    if (map.get(finalWord) > max) {
                        max = map.get(finalWord);
                        answer = finalWord;
                    }
                }
                word = new StringBuilder();
            }
        }

        return answer;
    }
}
