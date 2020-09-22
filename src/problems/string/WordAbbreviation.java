package problems.string;

import java.util.*;

/**
 * Given an array of n distinct non-empty strings, you need to generate minimal possible abbreviations for every word following rules below.
 *
 * Begin with the first character and then the number of characters abbreviated, which followed by the last character.
 * If there are any conflict, that is more than one words share the same abbreviation, a longer prefix is used instead of only the first character until
 * making the map from word to abbreviation become unique. In other words, a final abbreviation cannot map to more than one original words.
 * If the abbreviation doesn't make the word shorter, then keep it as original.
 */
public class WordAbbreviation {
    public static void main(String[] args) {
        // can't do i6l and in5l because 16l denotes both
        System.out.println(wordsAbbreviation(Arrays.asList("like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion")));
    }

    // O(c^2), c = total num of chars
    // idea is do the abbreviation and check if we have dupes
    // if dupes, continue to expand prefix until no dupes
    private static List<String> wordsAbbreviation(List<String> dict) {
        int n = dict.size();

        String[] ans = new String[n];
        int[] prefix = new int[n];

        // calculate abbrev for all words
        for (int i = 0; i < n; i++) {
            ans[i] = abbrev(dict.get(i), 0);
        }

        // check for dupes
        for (int i = 0; i < n; i++) {
            // do this until we have no dupes
            while (true) {
                Set<Integer> dupes = new HashSet<>(); // store index
                // compare with rest
                for (int j = i + 1; j < n; j++) {
                    if (ans[j].equals(ans[i])) {
                        dupes.add(j);
                    }
                }
                if (dupes.isEmpty()) {
                    break;
                }
                dupes.add(i);
                for (int d: dupes) {
                    prefix[d]++; // increment prefix
                    ans[d] = abbrev(ans[d], prefix[d]);
                }
            }
        }

        return Arrays.asList(ans);
    }

    ////////////////////////
    /// HashMap + Tie
    // O(c), c = total chars
    private static List<String> wordsAbbreviation2(List<String> dict) {
        String[] ans = new String[dict.size()];

        // we are going to group all the conflicted words and resolve them using trie
        // "internal", "interval" => grouped by "i6l" initially
        Map<String, List<IndexedWord>> groups = new HashMap<>(); // abbrv, list of words with same abbrv
        for (int i = 0; i < dict.size(); i++) {
            String sb = abbrev(dict.get(i), 0);
            if (!groups.containsKey(sb)) {
                groups.put(sb, new ArrayList<>());
            }
            groups.get(sb).add(new IndexedWord(i, dict.get(i)));
        }

        // go throught the words that have conflict
        for (String key: groups.keySet()) {
            List<IndexedWord> group = groups.get(key);

            // build trie, with count of words that have same prefix
            TrieNode trie = buildTrie(group);

            // for each word in the group, we find the word with count 1
            // that means at the position only current word has that specific char
            for (IndexedWord w: group) {
                TrieNode cur = trie;
                int i = 0;
                for (char c: w.word.toCharArray()) {
                    if (cur.count == 1) {
                        break;
                    }
                    cur = cur.children.get(c);
                    i++;
                }
                ans[w.index] = abbrev(w.word, i-1);
            }
        }
        return Arrays.asList(ans);
    }

    private static TrieNode buildTrie(List<IndexedWord> group) {
        TrieNode root = new TrieNode();
        for (IndexedWord w: group) {
            TrieNode node = root;
            for (char c: w.word.toCharArray()) {
                if (!node.children.containsKey(c)) {
                    node.children.put(c, new TrieNode());
                }
                node = node.children.get(c);
                node.count++;
            }
        }
        return root;
    }

    // abbrev from i
    private static String abbrev(String word, int i) {
        int n = word.length();
        // if remaining part is 3 or less, return the word because reducing will give same length
        if (n - i <= 3) {
            return word;
        }
        // chars upto i + middle number + last char
        return word.substring(0, i+1) + (n - i - 2) + word.charAt(n - 1);
    }

    static class IndexedWord {
        int index;
        String word;
        IndexedWord(int i, String w) {
            this.index = i;
            this.word = w;
        }
    }

    static class TrieNode {
        int count = 0;
        Map<Character, TrieNode> children;
        TrieNode() {
            children = new HashMap<>();
        }
    }
}
