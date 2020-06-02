package problems.tree;

import dataStructures.trees.TreeNode;

/**
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 */
public class KthSmallestElementInBst {
    int smallest = 0;
    int k = 0; // this needs to be outside of recursion
    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        inOrder(root);
        return smallest;
    }

    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        k--;
        if (k == 0) {
            smallest = root.val;
            return;
        }
        inOrder(root.right);
    }
}
