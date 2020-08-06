package problems.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * Design a data structure that supports the following two operations:
 *
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.
 */
public class AddAndSearchWord {
    TrieNode root;

    /** Initialize your data structure here. */
    public AddAndSearchWord() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!node.children.containsKey(c)) {
                node.children.put(c, new TrieNode());
            }
            node = node.children.get(c);
        }
        node.isEnd = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        // return searchPrefix(word, root);
        TrieNode node = root;
        return search(node, word, 0);
    }

    private boolean search(TrieNode node, String word, int index) {
        if (node == null) {
            return false;
        }
        if (index == word.length()) {
            return node.isEnd;
        }
        char c = word.charAt(index);
        boolean ans = false;
        if (c == '.') {
            for (char ch: node.children.keySet()) {
                if (search(node.children.get(ch), word, index+1)){
                    return true;
                }
            }
            return false;
        } else {
            return search(node.children.get(c), word, index+1);
        }
    }

    static class TrieNode {
        boolean isEnd = false;
        Map<Character, TrieNode> children;
        TrieNode() {
            children = new HashMap<>();
        }
    }
}
