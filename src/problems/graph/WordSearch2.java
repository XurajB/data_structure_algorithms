package problems.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once in a word.
 * https://leetcode.com/problems/word-search-ii/
 */
public class WordSearch2 {
    public static void main(String[] args) {
        char[][] board =
            {
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
            };
        String[] words = {"oath","pea","eat","rain"};
        System.out.println(findWords(board, words));
    }

    public static List<String> findWords(char[][] board, String[] words) {
        List<String> ans = new ArrayList<>();
        if (board == null && board.length == 0) {
            return ans;
        }
        // build the trie using the provided list of words
        TrieNode root = buildTrie(words);
        // dfs on each character (or backtrack)
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, ans, root, i, j);
            }
        }
        return ans;
    }

    private static void dfs(char[][] board, List<String> ans, TrieNode parent, int i, int j) {
        char c = board[i][j];
        if (c == '#') {
            return;  // visited node
        }
        TrieNode node = parent.children.get(c);
        if (node == null) {
            return;
        }
        if (node.isEnd) {
            ans.add(node.word);
            node.isEnd = false; // to remove duplicates
        }
        board[i][j] = '#'; //mark as visited
        // recursively look into all four directions
        if (i > 0) {
            dfs(board, ans, node, i - 1, j); //up
        }
        if (j > 0) {
            dfs(board, ans, node, i, j - 1); //left
        }
        if (i < board.length - 1) {
            dfs(board, ans, node, i + 1, j); //down
        }
        if (j < board[0].length - 1) {
            dfs(board, ans, node, i, j + 1); //right
        }
        board[i][j] = c; // set as unvisited so we can backtrack
    }

    private static TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();

        for (String s: words) {
            TrieNode node = root;
            for (char c: s.toCharArray()) {
                if (node.children.get(c) == null) {
                    node.children.put(c, new TrieNode());
                }
                node = node.children.get(c);
            }
            node.word = s; // store word at the leaf
            node.isEnd = true;
        }
        return root;
    }

    private static class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEnd = false;
        String word = null; // so we don't need to use stringbuilder to build the word char by char
        public TrieNode() {
            children = new HashMap<>();
        }
    }
}
