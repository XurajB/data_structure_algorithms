package problems.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The abbreviation of a word is a concatenation of its first letter,
 * the number of characters between the first and last letter, and its last letter. If a word has only two characters, then it is an abbreviation of itself.
 */
public class UniqueWordAbbreviation {
    Map<String, Set<String>> map;

    public UniqueWordAbbreviation(String[] dictionary) {
        map = new HashMap<>();
        for (String word: dictionary) {
            String abr = getAbbr(word);
            if (!map.containsKey(abr)) {
                map.put(abr, new HashSet<>());
            }
            map.get(abr).add(word);
        }
    }

    private String getAbbr(String word) {
        if (word.length() <= 2) {
            return word;
        }
        return word.charAt(0) + Integer.toString(word.length() - 2) + word.charAt(word.length() - 1);
    }

    public boolean isUnique(String word) {
        String abr = getAbbr(word);

        if (map.containsKey(abr)) {
            return map.get(abr).size() == 1 && map.get(abr).contains(word);
        } else {
            return true;
        }

    }
}
