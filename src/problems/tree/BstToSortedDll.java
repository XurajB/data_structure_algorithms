package problems.tree;

/**
 * Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.
 * https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
 */
public class BstToSortedDll {
    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }


    private Node first = null;
    private Node last = null;

    private Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        helper(root);
        last.right = first;
        first.left = last;
        return first;
    }

    // DFS, In-order traversal
    private void helper(Node node) {
        if (node != null) {
            helper(node.left);
            if (last != null) {
                node.left = last;
                last.right = node;
            } else {
                first = node;
            }
            last = node;
            helper(node.right);
        }
    }
}
