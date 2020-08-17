package problems.trie;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class StreamOfCharacters {
    private TrieNode root;
    private Deque<Character> stream; // so we can add

    // store the words in trie with reverse order, check the the query from the end
    public StreamOfCharacters(String[] words) {
        root = new TrieNode();
        stream = new ArrayDeque<>();

        for (String word: words) {
            insert(word);
        }
    }

    // O(M) max word length
    public boolean query(char letter) {
        stream.addFirst(letter);

        TrieNode node = root;
        for (char ch: stream) {
            if (node.isEnd) {
                return true;
            }
            if (!node.children.containsKey(ch)) {
                return false;
            }
            node = node.children.get(ch);
        }

        return node.isEnd;
    }

    // O(N * M)
    private void insert(String word) {
        TrieNode node = root;
        String reverse = new StringBuilder(word).reverse().toString();
        for (char c: reverse.toCharArray()) {
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
        TrieNode() {
            children = new HashMap<>();
        }
    }
}
