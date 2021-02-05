package problems.tree;

import dataStructures.trees.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 */
public class ValidateBST {
    public static void main(String[] args) {
        // I have both recursive and iterative solutions below
    }

    TreeNode prev = null; // can't put in param, object
    private boolean isValid(TreeNode root) {
        if (root == null) {
            return true;
        }
        return validate(root);
    }
    // validate using inorder, previous node cannot be greater than current
    // O(N)
    private boolean validate(TreeNode node) {
        if (node == null) {
            return true;
        }
        boolean left = validate(node.left);
        if (prev != null && prev.val >= node.val) {
            return false;
        }
        prev = node;
        return validate(node.right) && left;
    }

    // iterative inorder
    // O(N)
    private boolean isValid2(TreeNode root) {
        if (root == null) {
            return true;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode prev = null;
        while (root != null || stack.size() > 0) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (prev != null && prev.val >= root.val) {
                return false;
            }
            prev = root;
            root = root.right;
        }
        return true;
    }

    ///////
    TreeNode prev2;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST(root.left)) {
            return false;
        }
        if (prev2 != null && prev2.val >= root.val) {
            return false;
        }
        prev2 = root;
        return isValidBST(root.right);
    }
}
