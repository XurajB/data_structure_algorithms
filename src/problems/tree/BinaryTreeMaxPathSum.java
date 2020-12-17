package problems.tree;

import dataStructures.trees.TreeNode;

/**
 * Given a non-empty binary tree, find the maximum path sum.
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 */
public class BinaryTreeMaxPathSum {
    private int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        helper(treeNode);
        return max;
    }
    private int helper(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        int left = Math.max(helper(treeNode.left), 0); // for neg values
        int right = Math.max(helper(treeNode.right), 0);
        int total = left + right + treeNode.val;

        max = Math.max(max, total);
        return treeNode.val + Math.max(left, right);
    }
}
