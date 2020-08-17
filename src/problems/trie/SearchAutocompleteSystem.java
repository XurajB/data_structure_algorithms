package problems.trie;

import java.util.*;

/**
 * Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#').
 * For each character they type except '#', you need to return the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.
 * https://leetcode.com/problems/design-search-autocomplete-system/
 */
public class SearchAutocompleteSystem {

    private TrieNode root;
    private StringBuilder current;

    public static void main(String[] args) {
        String[] sentences = new String[] {"i love you", "island","ironman", "i love code"};
        int[] times = new int[] {5, 3, 2, 2};
        SearchAutocompleteSystem searchAutocompleteSystem = new SearchAutocompleteSystem(sentences, times);
        System.out.println(searchAutocompleteSystem.input('i'));
        System.out.println(searchAutocompleteSystem.input(' '));
        System.out.println(searchAutocompleteSystem.input('a'));
        System.out.println(searchAutocompleteSystem.input('#'));
    }

    static class TrieNode {
        int times;
        Map<Character, TrieNode> children;
        public TrieNode() {
            children = new HashMap<>();
            times = 0;
        }
    }

    static class Node {
        String sentence;
        int times;
        public Node(String sentence, int times) {
            this.sentence = sentence;
            this.times = times;
        }
    }

    // O(n * l)
    public SearchAutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        current = new StringBuilder();
        for (int i = 0; i < sentences.length; i++) {
            insert(sentences[i], times[i]);
        }
    }

    // O(l * nlogn), O(n)
    public List<String> input(char c) {
        List<String> ans = new ArrayList<>();
        if (c == '#') {
            insert(current.toString(), 1);
            current = new StringBuilder();
        } else {
            current.append(c);
            List<Node> nodes = lookUp();
            Collections.sort(nodes, (o1, o2) -> {
                if (o1.times == o2.times) {
                    return o1.sentence.compareTo(o2.sentence);
                } else {
                    return o2.times - o1.times;
                }
            });

            int size = Math.min(3, nodes.size());
            for (int i = 0; i < size; i++) {
                ans.add(nodes.get(i).sentence);
            }
        }
        return ans;
    }

    private List<Node> lookUp() {
        TrieNode node = root;
        for (int i = 0; i < current.length(); i++) {
            char c = current.charAt(i);
            if (node.children.get(c) != null) {
                node = node.children.get(c);
            } else {
                return new ArrayList<>();
            }
        }
        List<Node> nodes = new ArrayList<>();
        traverse(node, nodes, current.toString());
        return nodes;
    }

    private void traverse(TrieNode node, List<Node> list, String current) {
        if (node.times > 0) {
            list.add(new Node(current, node.times));
        }
        for (char i: node.children.keySet()) {
            traverse(node.children.get(i), list, current + i);
        }
    }

    private void insert(String sentence, int times) {
        TrieNode node = root;
        for (char c: sentence.toCharArray()) {
            if (!node.children.containsKey(c)) {
                node.children.put(c, new TrieNode());
            }
            node = node.children.get(c);
        }
        node.times += times;
    }
}
