package dataStructures.trees;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * https://leetcode.com/problems/validate-binary-search-tree/
 * https://www.youtube.com/watch?v=MILxfAbIhrE
 */
public class ValidBst {
    public boolean isValidBST(TreeNode root) {
        return isValid(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isValid(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
    }
}
