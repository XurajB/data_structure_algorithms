package problems.tree;

import dataStructures.trees.TreeNode;

/**
 * Given the root of a binary tree, find the largest subtree, which is also a Binary Search Tree (BST), where the largest means subtree has the largest number of nodes.
 */
public class LargestBstSubtree {
    private int largestBSTSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return helper(root)[2];
    }

    // O(N), O(H)
    private int[] helper(TreeNode root) {
        if (root == null) {
            return new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE, 0}; // min, max, size
        }
        int[] left = helper(root.left);
        int[] right = helper(root.right);

        // check if valid bst, cur node should be > than highest from left, lower than lowest from right
        if (root.val > left[1] && root.val < right[0]) {
            return new int[] {Math.min(root.val, left[0]), Math.max(root.val, right[1]), left[2] + right[2] + 1}; // math.min(val, left[0]) -> look at base case
        } else {
            return new int[] {Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(left[2], right[2])}; // Note: MIN, MAX. we will reset when we see another leaf, until then this BST is invalid
        }
    }
}
