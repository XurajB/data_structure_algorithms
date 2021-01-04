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
    double diff = Double.MAX_VALUE;
    int val = 0;
    public int closestValue2(TreeNode root, double target) {
        if (root == null) {
            return val;
        }
        double curDiff = Math.abs(root.val - target);
        if (diff > curDiff) {
            diff = curDiff;
            val = root.val;
        }
        if (root.val > target) {
            return closestValue2(root.left, target);
        } else {
            return closestValue2(root.right, target);
        }
    }
}
