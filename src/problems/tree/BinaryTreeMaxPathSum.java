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

    // max path sum. not tree sum: so we need to get max from left and right and see if left+right+val creates max
    // if there are more path to explore, get the max out of left and right and continue;
    private int helper(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        int left = Math.max(helper(treeNode.left), 0); // for neg values. we don't want to keep -ve because that will make result below smaller. ex: 2, -1
        int right = Math.max(helper(treeNode.right), 0);
        int total = left + right + treeNode.val;

        max = Math.max(max, total);
        return treeNode.val + Math.max(left, right);
    }
}
