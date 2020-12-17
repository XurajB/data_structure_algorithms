package problems.tree;

import dataStructures.trees.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, flatten it to a linked list in-place.
 */
public class FlattenBinaryTreeToLinkedList {
    // O(N)
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        List<Integer> pre = new ArrayList<>();
        helper(root, pre);
        root.val = pre.get(0);
        for (int i = 1; i < pre.size(); i++) {
            root.right = new TreeNode(pre.get(i));
            root.left = null;
            root = root.right;
        }
    }
    private void helper(TreeNode root, List<Integer> pre) {
        if (root != null) {
            pre.add(root.val);
            helper(root.left, pre);
            helper(root.right, pre);
        }
    }

    //------- SHORTER ----------// O(N), O(N) - recursion
    TreeNode prev = null;
    public void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }
        // post order, so start with right
        flatten2(root.right);
        flatten2(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }

    /// ---- O(N), O(1)
    public void flatten3(TreeNode root) {
        // Handle the null scenario
        if (root == null) {
            return;
        }
        TreeNode node = root;
        while (node != null) {
            // If the node has a left child
            if (node.left != null) {
                // Find the rightmost node
                TreeNode rightmost = node.left;
                while (rightmost.right != null) {
                    rightmost = rightmost.right;
                }
                // rewire the connections
                rightmost.right = node.right;
                node.right = node.left;
                node.left = null;
            }
            // move on to the right side of the tree
            node = node.right;
        }
    }
}
