package dataStructures.trie;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TrieAutoComplete {

    static class TrieNode {
        char data;
        LinkedList<TrieNode> children;
        TrieNode parent;
        boolean isEnd;

        TrieNode(char c) {
            data = c;
            children = new LinkedList<>();
            isEnd = false;
        }

        TrieNode getChild(char c) {
            if (children != null)
                for (TrieNode eachChild : children)
                    if (eachChild.data == c)
                        return eachChild;
            return null;
        }

        List<String> getWords() {
            List<String> list = new ArrayList<>();
            if (isEnd) {
                list.add(toString());
            }

            if (children != null) {
                for (TrieNode child : children) {
                    if (child != null) {
                        list.addAll(child.getWords());
                    }
                }
            }
            return list;
        }

        public String toString() {
            if (parent == null) {
                return "";
            } else {
                return parent.toString() + new String(new char[] {data});
            }
        }
    }

    private TrieNode root;

    TrieAutoComplete() {
        root = new TrieNode(' ');
    }

    private void insert(String word) {
        if (search(word))
            return;

        TrieNode current = root;
        TrieNode pre ;
        for (char ch : word.toCharArray()) {
            pre = current;
            TrieNode child = current.getChild(ch);
            if (child != null) {
                current = child;
                child.parent = pre;
            } else {
                current.children.add(new TrieNode(ch));
                current = current.getChild(ch);
                current.parent = pre;
            }
        }
        current.isEnd = true;
    }

    private boolean search(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            if (current.getChild(ch) == null)
                return false;
            else {
                current = current.getChild(ch);
            }
        }
        return current.isEnd;
    }

    private List autocomplete(String prefix) {
        TrieNode lastNode = root;
        for (int i = 0; i< prefix.length(); i++) {
            lastNode = lastNode.getChild(prefix.charAt(i));
            if (lastNode == null)
                return new ArrayList<>();
        }

        return lastNode.getWords();
    }

    public static void main(String[] args) {
        TrieAutoComplete t = new TrieAutoComplete();
        t.insert("amazon");
        t.insert("amazon prime");
        t.insert("amazing");
        t.insert("amazing spider man");
        t.insert("amazed");
        t.insert("alibaba");
        t.insert("ali express");
        t.insert("ebay");
        t.insert("walmart");
        List a = t.autocomplete("amaz");
        for (Object o : a) {
            System.out.println(o);
        }
    }
}
