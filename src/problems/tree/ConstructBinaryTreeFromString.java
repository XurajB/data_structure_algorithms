package problems.tree;

import dataStructures.trees.TreeNode;

/**
 * You need to construct a binary tree from a string consisting of parenthesis and integers.
 * The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis.
 * The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.
 * You always start to construct the left child node of the parent first if it exists.
 */
public class ConstructBinaryTreeFromString {
    private TreeNode str2tree(String s) {
        if (s.isEmpty()) {
            return null;
        }
        return helper(s);
    }

    static int i = 0;
    // in order, O(N)
    private static TreeNode helper(String s) {
        boolean positive = true;
        if (s.charAt(i) == '-') {
           positive = false;
           i++;
        }
        int val = 0;
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            val = val * 10 + s.charAt(i) - '0';
            i++;
        }

        TreeNode node = new TreeNode(positive ? val : -val);

        // go left until we see ) then we return
        // every ) will pop from stack
        if (i < s.length() && s.charAt(i) == '(') {
            i++;
            node.left = helper(s);
        }
        if (i < s.length() && s.charAt(i) == '(') {
            i++;
            node.right = helper(s);
        }

        i++; // for close bracket
        return node;
    }
}
