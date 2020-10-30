package problems.tree;

import dataStructures.trees.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Design an algorithm to encode an N-ary tree into a binary tree and decode the binary tree to get the original N-ary tree.
 * An N-ary tree is a rooted tree in which each node has no more than N children. Similarly, a binary tree is a rooted tree in which each node has no more than 2 children.
 * There is no restriction on how your encode/decode algorithm should work.
 * You just need to ensure that an N-ary tree can be encoded to a binary tree and this binary tree can be decoded to the original N-nary tree structure.
 * Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See following example).
 */
public class EncodeNaryToBinaryTree {
    // there are two ways we can do this
    // make the first child as left node and all its siblings as right to that node.. or vice versa
    // O(N), O(D)
    public TreeNode encode(Node root) {
        if (root == null) {
            return null;
        }

        TreeNode newRoot = new TreeNode(root.val);
        if (root.children.size() > 0) {
            Node firstChild = root.children.get(0);
            newRoot.left = encode(firstChild);
        }

        TreeNode sibling = newRoot.left;
        for (int i = 1; i < root.children.size(); i++) {
            sibling.right = encode(root.children.get(i));
            sibling = sibling.right;
        }

        return newRoot;
    }

    // Decodes your binary tree to an n-ary tree.
    public Node decode(TreeNode root) {
        if (root == null) {
            return null;
        }

        Node newRoot = new Node(root.val, new ArrayList<Node>());
        TreeNode sibling = root.left;
        while (sibling != null) {
            newRoot.children.add(decode(sibling));
            sibling = sibling.right;
        }

        return newRoot;
    }

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }
        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
}
