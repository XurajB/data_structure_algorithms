package problems.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * There is a broken keyboard in which space gets typed when you type the letter 'e'. Given an input string which is the output from the keyboard.
 * A dictionary of possible words is also provided as an input parameter of the method. Return a list of possible actual input typed by the user.
 */
public class BrokenKeyboard {
    public static void main(String[] args) {
        String[] dict = {"can", "canes", "serene", "rene", "sam"};
        BrokenKeyboard keyboard = new BrokenKeyboard();
        System.out.println(keyboard.findStrings("can s r n ", dict));
    }

    private TrieNode root;
    private List<String> findStrings(String input, String[] dict) {
        List<String> ans = new ArrayList<>();
        root = new TrieNode();
        insert(dict);

        search(input, 0, root, new StringBuilder(), ans);

        return ans;
    }

    // backtracking
    private void search(String input, int index, TrieNode node, StringBuilder sb, List<String> ans) {
        char ch;

        // if last char is a space
        if (index == input.length() - 1) {
            ch = input.charAt(index);
            if (ch == ' ') {
                if (node.children.containsKey('e') && node.children.get('e').isEnd) {
                    ans.add(sb.toString() + 'e');
                }
            }
            return;
        }

        ch = input.charAt(index);

        if (ch == ' ') {
            // either a space or char e
            // 1) space
            if (node.isEnd) {
                sb.append(' ');
                search(input, index+1, root, sb, ans);
                sb.deleteCharAt(sb.length() - 1); // backtrack
            }
            // 2) char e
            ch = 'e';
        }

        if (node.children.containsKey(ch)) {
            sb.append(ch);
            search(input, index+1, node.children.get(ch), sb, ans);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private void insert(String[] dict) {
        for (String word: dict) {
            TrieNode node = root;
            for (char c: word.toCharArray()) {
                if (!node.children.containsKey(c)) {
                    node.children.put(c, new TrieNode());
                }
                node = node.children.get(c);
            }
            node.isEnd = true;
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