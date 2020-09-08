package problems.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a pattern and a string str, find if str follows the same pattern.
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
 */
public class WordPattern {
    public static void main(String[] args) {
        System.out.println(wordPattern("abba", "dog cat cat dog"));
    }

    // O(N)
    private static boolean wordPattern(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();

        String[] splits = str.split(" ");
        if (pattern.length() != splits.length) {
            return false;
        }
        Set<String> used = new HashSet<>();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (map.containsKey(c) && !map.get(c).equals(splits[i])) {
                return false;
            }
            if (!map.containsKey(c) && used.contains(splits[i])) {
                return false;
            }
            used.add(splits[i]);
            map.put(c, splits[i]);
        }

        return true;
    }

    // use index
    private static boolean wordPattern2(String pattern, String str) {
        HashMap<String, Integer> map_index = new HashMap<>();
        String[] words = str.split(" ");

        if (words.length != pattern.length())
            return false;

        for (int i = 0; i < words.length; i++) {
            char c = pattern.charAt(i);
            String w = words[i];

            if (!map_index.containsKey(c))
                map_index.put(String.valueOf(c), i);

            if (!map_index.containsKey(w))
                map_index.put(w, i);

            if (!map_index.get(c).equals(map_index.get(w)))
                return false;
        }

        return true;
    }
}
