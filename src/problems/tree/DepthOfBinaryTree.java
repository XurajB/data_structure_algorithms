package problems.tree;

import dataStructures.trees.TreeNode;

/**
 * Find max depth of BT
 */
public class DepthOfBinaryTree {
    static int maxDepth = -1;
    // using top down approach
    private static void height(TreeNode root, int prev) {
        if (root == null) {
            return;
        }
        int cur = prev + 1;
        if (cur > maxDepth) {
            maxDepth = cur;
        }
        height(root.left, cur);
        height(root.right, cur);
    }

    // using bottom up
    private static int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }

    // check if it is balanced
    private static boolean isBalanced(TreeNode root) {
        return heightHelper(root) != -1;
    }

    private static int heightHelper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = heightHelper(root.left);
        int right = heightHelper(root.right);

        if (left == -1 || right == -1) {
            return -1;
        }
        if (Math.abs(left-right) > 1) {
            return -1;
        }
        return 1 + Math.max(left, right);
    }
}
