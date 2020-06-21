package dataStructures.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * A trie is a tree-like data structure whose nodes store the letters of an alphabet.
 * By structuring the nodes in a particular way, words and strings can be retrieved from the structure by traversing down a branch path of the tree.
 */
public class MyTrie {

    private final TrieNode root;

    MyTrie() {
        root = new TrieNode();
    }

    public static void main(String[] args) {
        MyTrie trie = new MyTrie();
        trie.insert("amazon");
        trie.insertRecursive("amazing");
        trie.insert("amaze");
        trie.insert("amazement");
        trie.insert("ap");
        trie.insert("app");
        trie.insert("apple");
        trie.insert("apples");

        System.out.println(trie.search("amazon"));
        System.out.println(trie.search("amaz"));
        System.out.println(trie.search("app"));
        System.out.println(trie.searchRecursive("appp"));
        System.out.println(trie.delete("app"));
        System.out.println(trie.delete("appl"));
    }

    // O(l) - l is length of word
    private void insert(String word) {
        TrieNode current = root;
        for (char c: word.toCharArray()) {
            TrieNode node = current.children.get(c);
            if (node == null) {
                node = new TrieNode();
                current.children.put(c, node);

            }
            current = node;

        }
        current.isEnd = true;
    }

    private void insertRecursive(String word) {
        insertRecursive(root, word, 0);
    }

    private void insertRecursive(TrieNode current, String word, int index) {
        if (index == word.length()) {
            current.isEnd = true;
            return;
        }
        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);

        if (node == null) {
            node = new TrieNode();
            current.children.put(ch, node);
        }
        insertRecursive(node, word, index + 1);
    }

    private boolean search(String word) {
        TrieNode current = root;
        for (char c: word.toCharArray()) {
            TrieNode node = current.children.get(c);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return current.isEnd;
    }

    private boolean searchRecursive(String word) {
        return searchRecursive(root, word, 0);
    }

    private boolean searchRecursive(TrieNode current, String word, int index) {
        if (index == word.length()) {
            return current.isEnd;
        }

        TrieNode node = current.children.get(word.charAt(0));
        // does not exist
        if (node == null) {
            return false;
        }
        return searchRecursive(node, word, index+1);
    }

    private boolean delete(String word) {
        return delete(root, word, 0);
    }

    private boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.isEnd) {
                return false; // not found
            }
            current.isEnd = false; //mark as false so they don't come up in search
            // return if we should delete the empty node
            return current.children.size() == 0;
        }

        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        if (node == null) {
            return false;
        }

        boolean shouldDeleteNode = delete(node, word, index+1);

        if(shouldDeleteNode) {
            current.children.remove(ch);
            return current.children.size() == 0;
        }

        return false;
    }

    public static class TrieNode {
        // keep track of children
        // an efficient solution could be to use array with size 26 - but only lowercase 1-z
        Map<Character, TrieNode> children;
        // is this the end of word
        boolean isEnd;

        TrieNode() {
            children = new HashMap<>();
            isEnd = false;
        }
    }
}
