package leetcode;

import dataStructures.trees.TreeNode;

/**
 * Given the root of a binary tree, find the maximum average value of any subtree of that tree.
 * (A subtree of a tree is any node of that tree plus all its descendants.The average value of a tree is the sum of its values, divided by the number of nodes.)
 * https://leetcode.com/problems/maximum-average-subtree/
 */
public class MaximumAverageSubtree {
    private double max = 0.0;
    public double maximumAverageSubtree(TreeNode node) {
        if (node == null) {
            return max;
        }
        helper(node);
        return max;
    }

    // int[] = {num, sum}
    // pre-order
    // O(N), O(N)
    private int[] helper(TreeNode node) {
        if (node == null) {
            return new int[] {0, 0};
        }
        int sum = node.val;
        int num = 1;

        int[] left = helper(node.left);
        int[] right = helper(node.right);

        num += left[0] + right[0];
        sum += left[1] + right[1];

        max = Math.max(max, (double)sum/(double)num);
        return new int[] {num, sum};
    }
}