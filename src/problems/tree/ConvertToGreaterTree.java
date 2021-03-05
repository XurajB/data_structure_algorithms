package problems.tree;

import dataStructures.trees.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given the root of a Binary Search Tree (BST),
 * convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.
 */
public class ConvertToGreaterTree {
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        helper(root);
        return root;
    }
    int sum = 0;
    private void helper(TreeNode root) {
        if (root == null) {
            return;
        }
        helper(root.right);
        sum += root.val;
        root.val = sum;
        helper(root.left);
    }

    ////////////
    // iterative
    public TreeNode convertBST2(TreeNode root) {
        Deque<TreeNode> dq = new ArrayDeque<>();
        int sum = 0;
        TreeNode node = root;
        while (!dq.isEmpty() || root != null) {
            while (root != null) {
                dq.push(root);
                root = root.right;
            }
            root = dq.pop();
            sum += root.val;
            root.val = sum;

            root = root.left;
        }
        return node;
    }
}
