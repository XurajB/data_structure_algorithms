package dataStructures.trie;

/**
 * Trie data structure
 */
public class Trie {

    private TrieNode root;

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("bad");
        trie.insert("sad");
        trie.insert("mad");

        System.out.println(trie.searchWithDot("mad"));
        System.out.println(trie.searchWithDot("s..d"));
    }

    public Trie() {
        root = new TrieNode();
    }

    // insert
    public void insert(String word) {
        TrieNode node = root;
        for (char c: word.toCharArray()) {
            if (!node.containsKey(c)) {
                node.put(c, new TrieNode());
            }
            node = node.get(c);
        }
        node.setEnd();
    }

    public TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (char c: word.toCharArray()) {
            if (node.containsKey(c)) {
                node = node.get(c);
            } else {
                return null;
            }
        }
        return node;
    }

    public TrieNode searchPrefixWithDot(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c == '.') {
                String newWord = word.substring(i + 1);
                for (TrieNode n: node.getChildren()) {
                    if (n != null) {
                        if (searchPrefixWithDot(newWord, n)) {
                            return n;
                        }
                    }
                }
                return null;
            } else if (node.containsKey(c)) {
                node = node.get(c);
            } else {
                return null;
            }
        }
        return node;
    }

    private boolean searchPrefixWithDot(String word, TrieNode node) {
        if (node == null) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c == '.') {
                String newWord = word.substring(i + 1);
                for (TrieNode n: node.getChildren()) {
                    if (n != null) {
                        if (searchPrefixWithDot(newWord, n)) {
                            return true;
                        }
                    }
                }
                return false;
            } else if (node.containsKey(c)) {
                node = node.get(c);
            } else {
                return false;
            }
        }
        return node.isEnd();
    }

    public boolean searchWithDot(String word) {
        TrieNode node = searchPrefixWithDot(word);
        if (word.contains(".")) {
            return node != null;
        } else return node != null && node.isEnd();
    }

    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }

    static class TrieNode {
        private static final int R = 26;
        private TrieNode[] links;
        private boolean isEnd = false;

        TrieNode() {
            links = new TrieNode[R];
        }

        public boolean containsKey(char c) {
            return links[c - 'a'] != null;
        }

        public void put(char c, TrieNode node) {
            links[c - 'a'] = node;
        }

        public TrieNode get(char c) {
            return links[c - 'a'];
        }

        public void setEnd() {
            isEnd = true;
        }

        public boolean isEnd() {
            return isEnd;
        }

        public TrieNode[] getChildren() {
            return links;
        }
    }
}
