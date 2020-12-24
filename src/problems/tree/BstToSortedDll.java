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
                first = node; // this will be the smallest element, and not change because last will not be null
            }
            last = node; // smallest node will be first and last since 1 element, but that will change as we navigate
            helper(node.right);
        }
    }

    ///// same thing, different way
    private void inOrder(Node root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);

        if (first == null) {
            first = root;
        } else if (last != null) {
            last.right = root;
            root.left = last;
        }
        last = root;

        inOrder(root.right);
    }
}
