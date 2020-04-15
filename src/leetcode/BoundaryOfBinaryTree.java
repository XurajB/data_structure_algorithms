package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root.
 * Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.
 * (The values of the nodes may still be duplicates.)
 * https://leetcode.com/problems/boundary-of-binary-tree/
 */
public class BoundaryOfBinaryTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    List<Integer> bounds = new ArrayList<>();

    // Time: O(N), Space: O(N)
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if (root == null) {
            return bounds;
        }

        // start with root
        bounds.add(root.val);
        // left, left leaves, right leaves, right
        leftBoundary(root.left); // O(N)
        collectLeaves(root.left); // O(N)
        collectLeaves(root.right); // O(N);
        rightBoundary(root.right); // O(N);

        return bounds;
    }

    private void leftBoundary(TreeNode node) {
        if (node == null || (node.right == null && node.left == null)) {
            return;
        }
        bounds.add(node.val); // top down on left side
        if (node.left != null) {
            leftBoundary(node.left);
        } else {
            leftBoundary(node.right);
        }
    }

    private void rightBoundary(TreeNode node) {
        if (node == null || (node.right == null && node.left == null)) {
            return;
        }
        if (node.right != null) {
            rightBoundary(node.right);
        } else {
            rightBoundary(node.left);
        }
        bounds.add(node.val); // bottoms up when collecting boundary on the right side
    }

    private void collectLeaves(TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            bounds.add(node.val);
        }
        collectLeaves(node.left);
        collectLeaves(node.right);
    }
}
