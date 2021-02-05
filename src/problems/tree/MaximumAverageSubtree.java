package problems.tree;

import dataStructures.trees.TreeNode;

/**
 * Given the root of a binary tree, find the maximum average value of any subtree of that tree.
 * (A subtree of a tree is any node of that tree plus all its descendants.The average value of a tree is the sum of its values, divided by the number of nodes.)
 * https://leetcode.com/problems/maximum-average-subtree/
 */
public class MaximumAverageSubtree {
    private double max = 0.0;
    public double maximumAverageSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return helper(root)[2];

    }

    private double[] helper(TreeNode root) {
        if (root == null) {
            return new double[] {0,0, Double.MIN_VALUE};
        }

        double[] left = helper(root.left);
        double[] right = helper(root.right);

        double total = left[1] + right[1] + 1;
        double sum = left[0] + right[0] + root.val;

        double max = Math.max(left[2], right[2]);

        return new double[] {sum, total, Math.max(max, sum/total)};
    }
}