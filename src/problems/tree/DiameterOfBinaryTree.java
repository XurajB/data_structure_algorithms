package problems.tree;

import dataStructures.trees.TreeNode;

/**
 * Given a binary tree, you need to compute the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 */
public class DiameterOfBinaryTree {
    int ans;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        helper(root);
        return ans - 1; // we got nodes, path in between is nodes-1
    }
    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left);
        int right = helper(root.right);
        ans = Math.max(ans, left+right+1);
        return Math.max(left, right) + 1;
    }
}
