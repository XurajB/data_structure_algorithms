package problems.trie;

import java.util.*;

/**
 * Design a search autocomplete system for a search engine
 * https://leetcode.com/problems/design-search-autocomplete-system/
 */
public class DesignSearchAutocompleteSystem {
    public static void main(String[] args) {
        // we have two problems.implementations of this
        // one using hashmap and another using trie
    }

    static class MyComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            if (o1.times == o2.times) {
                return o1.sentence.compareTo(o2.sentence);
            }
            return o2.times - o1.times;
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

    // using hashmap
    static class AutoCompleteSystem1 {
        Map<String, Integer> map;
        StringBuilder current;

        // O(l * n), l - average length of sentence, n - number of sentences
        public AutoCompleteSystem1(String[] sentences, int[] times) {
            map = new HashMap<>();
            current = new StringBuilder();
            for (int i = 0; i < sentences.length; i++) {
                map.put(sentences[i], times[i]);
            }
        }

        // O(nLogn)
        public List<String> input(char c) {
            List<String> ans = new ArrayList<>();

            if (c == '#') {
                map.put(current.toString(), map.getOrDefault(current.toString(), 0) + 1);
                current = new StringBuilder();
            } else {
                List<Node> nodes = new ArrayList<>();
                for (String s: map.keySet()) {
                    if (s.indexOf(current.toString()) == 0) {
                        nodes.add(new Node(s, map.get(s)));
                    }
                }

                Collections.sort(nodes, new MyComparator());
                for (int i = 0; i < Math.min(3, nodes.size()); i++) {
                    ans.add(nodes.get(i).sentence);
                }
            }
            return ans;
        }
    }

    // reason we are not saving top 3 in trie node is when an existing top 4 node is searched it becomes top 3, how do you update every node?
    // O(n * l)
    static class AutoCompleteSystem2 {
        private TrieNode root;
        private StringBuilder current;

        static class TrieNode {
            int times;
            Map<Character, TrieNode> children = new HashMap<>();
        }

        public AutoCompleteSystem2(String[] sentences, int[] times) {
            root = new TrieNode();
            current = new StringBuilder();
            for (int i = 0; i < sentences.length; i++) {
                insert(sentences[i], times[i]);
            }
        }

        public void insert(String sentence, int times) {
            TrieNode node = root;
            for (char c: sentence.toCharArray()) {
                if (node.children.get(c) == null) {
                    node.children.put(c, new TrieNode());
                }
                node = node.children.get(c);
            }
            node.times += times;
        }

        public List<String> input(char c) {
            List<String> ans = new ArrayList<>();
            if (c == '#') {
                insert(current.toString(), 1);
                current = new StringBuilder();
            } else {
                current.append(c);
                List<Node> nodes = lookUp();

                Collections.sort(nodes, new MyComparator());
                for (int i = 0; i < Math.min(3, nodes.size()); i++) {
                    ans.add(nodes.get(i).sentence);
                }
            }
            return ans;
        }

        private List<Node> lookUp() {
            List<Node> list = new ArrayList<>();
            TrieNode node = root;
            for (int i = 0; i < current.length(); i++) {
                if (node.children.get(current.charAt(i)) == null) {
                    return new ArrayList<>();
                }
                node = node.children.get(current.charAt(i));
            }
            traverse(node, list, current.toString());
            return list;
        }

        private void traverse(TrieNode node, List<Node> list, String current) {
            if (node.times > 0) {
                list.add(new Node(current, node.times));
            }
            for (char i: node.children.keySet()) {
                traverse(node.children.get(i), list, current + i);
            }
        }
    }
}
