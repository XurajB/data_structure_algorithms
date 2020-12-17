package problems.tree;

import dataStructures.trees.TreeNode;

/**
 * Given the root of a binary tree, the depth of each node is the shortest distance to the root.
 * Return the smallest subtree such that it contains all the deepest nodes in the original tree.
 * A node is called the deepest if it has the largest depth possible among any node in the entire tree.
 * The subtree of a node is tree consisting of that node, plus the set of all descendants of that node.
 */
public class LcaDeepestLeaves {
    TreeNode ans;
    int max = 0;
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        depth(root, 0);
        return ans;
    }

    // O(N), O(H)
    private int depth(TreeNode root, int deep) {
        max = Math.max(max, deep);
        if (root == null) {
            return deep;
        }

        int left = depth(root.left, deep + 1);
        int right = depth(root.right, deep + 1);

        if (left == max && right == max) {
            ans = root;
        }

        return Math.max(left, right);
    }
}
