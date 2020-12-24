package problems.tree;

import dataStructures.trees.TreeNode;

/**
 * Given the root of a binary tree, find the maximum value V for which there exist different nodes A and B where V = |A.val - B.val| and A is an ancestor of B.
 *
 * A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an ancestor of B.
 */
public class MaximumDifferenceBetweenNodeAncestor {
    private static int maxAncestorDiff(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return helper(root, root.val, root.val);
    }

    // O(N)
    private static int helper(TreeNode node, int curMax, int curMin) {
        if (node == null) {
            return curMax - curMin;
        }
        curMax = Math.max(node.val, curMax);
        curMin = Math.min(node.val, curMin);

        int left = helper(node.left, curMax, curMin);
        int right = helper(node.right, curMax, curMin);

        return Math.max(left, right);
    }

    /// different way
    int res = 0;
    private void dfs(TreeNode node, int min, int max) {
        if (node == null) {
            return;
        }

        min = Math.min(node.val, min);
        max = Math.max(node.val, max);

        res = Math.max(res, max - min);

        dfs(node.left, min, max);
        dfs(node.right, min, max);
    }
}
