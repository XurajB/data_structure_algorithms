package problems.trie;

import java.util.*;

/**
 * Given an array of strings products and a string searchWord.
 * We want to design a system that suggests at most three product names from products after each character of searchWord is typed.
 * Suggested products should have common prefix with the searchWord.
 * If there are more than three products with a common prefix return the three lexicographically minimums products.
 *
 * Return list of lists of the suggested products after each character of searchWord is typed.
 * https://leetcode.com/problems/search-suggestions-system/
 */
public class SearchSuggestionsSystem {

    private static TrieNode root = new TrieNode();

    public static void main(String[] args) {
        String[] products = new String[] {"mobile","mouse","moneypot","monitor","mousepad"};
        List<List<String>> ans = suggestedProducts(products, "mouse");
        for (List<String> a: ans) {
            System.out.println(a);
        }
    }

    // time: O(m * nlogn), m - average length of product, nlogn * m for sorting (it includes string comparision + sorting), n * m for building trie
    // space: O(m * n + L * m): m - average length of product, n is total products, L is length of search word
    private static List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> ans = new ArrayList<>();
        if (products == null || searchWord == null) {
            return ans;
        }

        // sort it so we can get lexicographically ordered suggestions
        Arrays.sort(products);

        for (String s: products) {
            insertWord(s);
        }

        for (char c: searchWord.toCharArray()) {
            if (root != null) {
                root = root.children.get(c);
            }
            if (root == null) {
                ans.add(new ArrayList<>());
            } else {
                ans.add(root.suggestions);
            }
        }

        return ans;
    }

    private static void insertWord(String word) {
        TrieNode node = root;
        for (char c: word.toCharArray()) {
            if (!node.children.containsKey(c)) {
                node.children.put(c, new TrieNode());
            }
            node = node.children.get(c);
            if (node.suggestions.size() < 3) {
                node.suggestions.offer(word); // we are adding same word as suggestion as we go into children nodes
            }
        }
    }

    static class TrieNode {
        Map<Character, TrieNode> children;
        LinkedList<String> suggestions;
        public TrieNode() {
            children = new HashMap<>();
            suggestions = new LinkedList<>();
        }
    }
}
