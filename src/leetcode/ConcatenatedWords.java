package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
 * https://leetcode.com/problems/concatenated-words/
 */
public class ConcatenatedWords {
    private static TrieNode root = new TrieNode();

    public static void main(String[] args) {
        String[] words = new String[] {"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
        System.out.println(findAllConcatenatedWordsInADict(words));
    }

    public static List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> ans = new ArrayList<>();
        if (words == null || words.length == 0) {
            return ans;
        }
        for (String word: words) {
            addWord(word);
        }
        for (String word: words) {
            if (isConcatenated(word, 0, 0)) {
                ans.add(word);
            }
        }
        return ans;
    }

    // dfs
    private static boolean isConcatenated(String word, int index, int countConcatenatedWords) {
        if (index == word.length()) {
            return countConcatenatedWords >= 2;
        }
        TrieNode ptr = root;
        for (int i = index; i < word.length(); i++) {
            if (ptr.children.get(word.charAt(i)) == null) {
                return false;
            }
            ptr = ptr.children.get(word.charAt(i));
            if (ptr.isEnd) {
                if (isConcatenated(word, i + 1, countConcatenatedWords + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void addWord(String word) {
        TrieNode node = root;
        for (char c: word.toCharArray()) {
            if (!node.children.containsKey(c)) {
                node.children.put(c, new TrieNode());
            }
            node = node.children.get(c);
        }
        node.isEnd = true;
    }

    static class TrieNode {
        boolean isEnd = false;
        Map<Character, TrieNode> children;
        public TrieNode() {
            children = new HashMap<>();
        }
    }
}
