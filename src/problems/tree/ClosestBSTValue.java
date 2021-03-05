package problems.tree;

import dataStructures.trees.TreeNode;

/**
 * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
 */
public class ClosestBSTValue {
    // O(logN)
    public int closestValue(TreeNode root, double target) {
        int ans = root.val; // best ans so far
        while (root != null) {
            if (Math.abs(target - root.val) < Math.abs(target - ans)){
                ans = root.val;
            }
            if (root.val > target) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return ans;
    }

    //////////////////////////////
    // recursive
    private void helper(TreeNode root, double target, TreeNode[] ans) {
        if (root == null) {
            return;
        }
        if (Math.abs(ans[0].val - target) > Math.abs(root.val - target)) {
            ans[0] = root;
        }
        if (target < root.val) {
            helper(root.left, target, ans);
        } else {
            helper(root.right, target, ans);
        }
    }
}
